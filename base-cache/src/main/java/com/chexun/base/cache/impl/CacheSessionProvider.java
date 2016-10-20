package com.chexun.base.cache.impl;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.chexun.base.cache.SessionCache;
import com.chexun.base.cache.SessionProvider;
import com.chexun.base.cache.util.FileUtils;
import com.chexun.base.cache.util.RequestUtils;
import com.chexun.base.cache.util.UUID32;
import com.chexun.base.common.util.CookieUtils;


public class CacheSessionProvider implements SessionProvider, InitializingBean {
	public static final String CURRENT_SESSION = "_current_session";
	public static final String CURRENT_SESSION_ID = "_current_session_id";
	public static final String JSESSION_COOKIE = "MEM_JSESSIONID";
	private SessionCache sessionCache;
	private String propertieFile;
	private Boolean useMemcached = true;//是否使用第三方缓存保存HttpSession,在配置文件project.properties中以userMemcached配置
	private int sessionTimeout = 10080;//登录session过期时间（分钟），在配置文件project.properties中以session配置
	private String domain = "chexun.com";//域名，主要影响cookie的域，在配置文件project.properties中以domain配置

	@SuppressWarnings("unchecked")
	public Serializable getAttribute(HttpServletRequest request,String name) {
		if (useMemcached) {
			// 为了避免同一个请求多次获取缓存session，所以将缓存session保存至request中，在同一次request生命周期内，只需要从第三方缓存中读取一次。
			Map<String, Serializable> session = (Map<String, Serializable>) request.getAttribute(CURRENT_SESSION);
			//System.out.println("3------url:" + request.getRequestURL());
//			String url = request.getRequestURL().toString();
			if (session != null) {
//				System.out.println("3------session is not null" + ",url:" + url);
//				Serializable s = session.get(name);
//				if(s == null){
//					System.out.println("3------s is null" + ",url:" + url);
//				}else{
//					System.out.println("3------s is not null" + ",url:" + url);
//				}
				return session.get(name);
			}else{
//				System.out.println("3------session is null" + ",url:" + url);
			}

			//在第三方缓存中保存登录Session的key值，来源有两种，request中，或者cookie中。
			String root = (String) request.getAttribute(CURRENT_SESSION_ID);
			if (root == null) {
				root = RequestUtils.getRequestedSessionId(request);
			}
//			System.out.println("3------root:" + root + ",url:" + url);
			
			if (StringUtils.isBlank(root)) {
				request.setAttribute(CURRENT_SESSION, new HashMap<String, Serializable>());
				return null;
			}
			session = sessionCache.getSession(root);
			
			if (session != null) {
				request.setAttribute(CURRENT_SESSION_ID, root);
				request.setAttribute(CURRENT_SESSION, session);
//				Serializable s = session.get(name);
//				if(s == null){
//					System.out.println("32------s is null" + ",url:" + url);
//				}else{
//					System.out.println("32------s is not null" + ",url:" + url);
//				}
				return session.get(name);
			} else {
//				System.out.println("32------session is null" + ",url:" + url);
				return null;
			}
		} else {
			return (Serializable) request.getSession().getAttribute(name);
		}
	}

	@SuppressWarnings("unchecked")
	public void setAttribute(HttpServletRequest request, HttpServletResponse response,String name, Serializable value) {
		if (useMemcached) {
			Map<String, Serializable> session = (Map<String, Serializable>) request.getAttribute(CURRENT_SESSION);
			String root;
			if (session == null) {
				root = RequestUtils.getRequestedSessionId(request);
				if (root != null && root.length() == 32) {
					session = sessionCache.getSession(root);
				}
				if (session == null) {
					session = new HashMap<String, Serializable>();
					do {
						root = UUID32.get();
					} while (sessionCache.exist(root));
					response.addCookie(createCookie(response,root));
				}
				request.setAttribute(CURRENT_SESSION, session);
				request.setAttribute(CURRENT_SESSION_ID, root);
			} else {
				root = (String) request.getAttribute(CURRENT_SESSION_ID);
				if (root == null) {
					do {
						root = UUID32.get();
					} while (sessionCache.exist(root));
					response.addCookie(createCookie(response,root));
					request.setAttribute(CURRENT_SESSION_ID, root);
				}
			}
//			if(value == null){
//				System.out.println("00------session add name:" + name + ",value:null,url:" + request.getRequestURL());
//			}else{
//				System.out.println("00------session add name:" + name + ",value:" + value+ ",url:" + request.getRequestURL());
//			}
			session.put(name, value);
			sessionCache.setSession(root, session, sessionTimeout);
		} else {
			request.getSession().setAttribute(name, value);
		}
	}

	public String getSessionId(HttpServletRequest request, HttpServletResponse response) {
		String root = (String) request.getAttribute(CURRENT_SESSION_ID);
		if (root != null) {
			return root;
		}
		root = RequestUtils.getRequestedSessionId(request);
		if (root == null || root.length() != 32 || !sessionCache.exist(root)) {
			do {
				root = UUID32.get();
			} while (sessionCache.exist(root));
			sessionCache.setSession(root, new HashMap<String, Serializable>(), sessionTimeout);
			response.addCookie(createCookie(response,root));
		}
		request.setAttribute(CURRENT_SESSION_ID, root);
		return root;
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		if (useMemcached) {
			request.removeAttribute(CURRENT_SESSION);
			request.removeAttribute(CURRENT_SESSION_ID);
			String root = RequestUtils.getRequestedSessionId(request);
			if (!StringUtils.isBlank(root)) {
				sessionCache.clear(root);
				// Cookie cookie = createCookie(response, null);
				// cookie.setMaxAge(0);
				// response.addCookie(cookie);
				Cookie[] cookies = request.getCookies();
				for (Cookie cookie : cookies) {
					CookieUtils.remove(response,domain,cookie.getName());
				}
			}
		} else {
			request.getSession().invalidate();
		}
	}

	private Cookie createCookie(HttpServletResponse response,String value) {
		Cookie cookie = new Cookie(JSESSION_COOKIE, value);
		CookieUtils.add(response, cookie,domain,60*sessionTimeout);
		return cookie;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(sessionCache);
		InputStream is;
		if("".equals(propertieFile)||null==propertieFile){
			is = FileUtils.getClassInputStream(CacheSessionProvider.class, "/project.properties");
		}else{
			is = FileUtils.getClassInputStream(CacheSessionProvider.class, propertieFile);
		}
		java.util.Properties properties = new java.util.Properties();
		properties.load(is);
		String session = properties.getProperty("session");
		String dmn = properties.getProperty("domain");
		this.sessionTimeout = (session != null && !session.equals("")) ? Integer.parseInt(session) : 30;
		Boolean usecached = Boolean.valueOf(properties.getProperty("userMemcached"));
		if (usecached != null) {
			useMemcached = usecached;
		}
		if(dmn != null && ! "".equals(dmn)){
			this.domain = dmn;
		}
	}

	public void setSessionCache(SessionCache sessionCache) {
		this.sessionCache = sessionCache;
	}

	public void setUseMemcached(Boolean useMemcached) {
		this.useMemcached = useMemcached;
	}

	/**
	 * 设置session过期时间
	 * @param sessionTimeout
	 *            分钟
	 */
	public void setSessionTimeout(int sessionTimeout) {
		Assert.isTrue(sessionTimeout > 0);
		this.sessionTimeout = sessionTimeout;
	}

	public String getPropertieFile() {
		return propertieFile;
	}

	public void setPropertieFile(String propertieFile) {
		this.propertieFile = propertieFile;
	}
}
