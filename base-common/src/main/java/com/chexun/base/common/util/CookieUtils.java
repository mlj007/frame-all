package com.chexun.base.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	// ---1天过期
	//private static final int COOKIE_USER_TIME = 3600 * 24;
	//private static final String COOKIE_DOMAIN = ".maintenance.chexun.com";

	/**
	 * 
	 * 将cookie转化为map
	 * 
	 * @return:Map<String,Object>
	 */
//	public static Map<String, Object> getMaps(HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				map.put(cookie.getName(), cookie.getValue());
//
//			}
//		}
//		return map;
//	}

	/**
	 * 
	 * 清除本站所有cookie
	 * 
	 * @return:void
	 */
//	public static void clear(HttpServletRequest request, HttpServletResponse response) {
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				cookie.setDomain(COOKIE_DOMAIN);
//				cookie.setMaxAge(0);
//				cookie.setPath("/");
//				response.addCookie(cookie);
//			}
//		}
//	}

	/**
	 * 
	 * 获取cookie的某一个值
	 * 
	 * @return:Object
	 */
//	public static String getCookieValue(HttpServletRequest request, String name) {
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				if (cookie.getName().equals(name)) {
//					try {
//						return URLDecoder.decode(cookie.getValue(), "UTF-8");
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}
//					// return URLDecoder.decode(cookie.getValue());
//				}
//			}
//		}
//		return null;
//	}

	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	public static void remove(HttpServletResponse response,String domain, String name) {
		Cookie cookie = new Cookie(name, null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		//cookie.setDomain(COOKIE_DOMAIN);
		cookie.setDomain(domain);
		response.addCookie(cookie);
	}

//	public static void add(HttpServletResponse rsp, Cookie cookie) {
//		cookie.setDomain(COOKIE_DOMAIN);
//		cookie.setPath("/");
//		cookie.setMaxAge(COOKIE_USER_TIME);
//		rsp.addCookie(cookie);
//	}
//	
//	public static void del(HttpServletResponse rsp, Cookie cookie) {
//		cookie.setDomain(COOKIE_DOMAIN);
//		cookie.setPath("/");
//		rsp.addCookie(cookie);
//	}
//	
	public static void add(HttpServletResponse rsp, Cookie cookie,String domain,int maxTime) {
		//cookie.setDomain(COOKIE_DOMAIN);
		cookie.setDomain(domain);
		cookie.setPath("/");
		cookie.setMaxAge(maxTime);
		rsp.addCookie(cookie);
	}

	
	//根据名称获取cookie值
	public static String getCookieValue(HttpServletRequest request,String key){
		Cookie[] cookie=request.getCookies();
		try{
			if (cookie != null) {
				for (int i = 0; i < cookie.length; i++) {
					if (cookie[i].getName().equalsIgnoreCase(key)) {
						//System.out.println(java.net.URLDecoder.decode(cookie[i].getValue(),"UTF-8"));
						//System.out.println();
						return   java.net.URLDecoder.decode(cookie[i].getValue(),"UTF-8").trim();
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}
