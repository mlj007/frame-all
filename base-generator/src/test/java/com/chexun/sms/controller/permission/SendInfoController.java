package  com.chexun.sms.controller.permission;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chexun.base.common.util.string.StringUtils;
import com.chexun.base.framework.core.controller.BaseController;
//import com.chexun.partner.constant.CoreConstant;
import com.chexun.base.framework.core.entity.PageEntity;
//import com.chexun.partner.web.back.controllers.system.SysBaseController;
import com.chexun.sms.model.permission.SendInfo;
//import com.chexun.partner.model.system.SysUser;
import com.chexun.sms.service.permission.SendInfoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manage/permission/sendinfo")
public class SendInfoController extends BaseController {

	private static final Logger logger = Logger.getLogger(SendInfoController.class);

	@Autowired
	private SendInfoService sendInfoService;

	// 路径
	private String toList = "/permission/sendinfo_list.httl";// 列表页
	private String toAdd = "/permission/sendinfo_add.httl";// 添加页面
	private String toEdit = "/permission/sendinfo_edit.httl";// 修改页

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, SendInfo query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
			if (query == null) {
				query = new SendInfo();
			}
			List<SendInfo> list = sendInfoService.getSendInfoPage(query, this.getPage());
			modelAndView.addObject("query", query);
			modelAndView.addObject("sendInfoList", list);
			modelAndView.addObject("page", this.getPage());
		} catch (Exception e) {
			logger.error("SendInfoController.listAll", e);
			//return new ModelAndView(setExceptionRequestAdmin(request, e));
		}

		return modelAndView;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toAdd);
		try {
		} catch (Exception e) {
			logger.error("SendInfoController.toAdd", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView toEdit(Long id,HttpServletRequest request) {
		ModelAndView modelAndView =new ModelAndView(toEdit);
		try {
			SendInfo sendInfo = sendInfoService.getSendInfoById(id);
			modelAndView.addObject(sendInfo);
		} catch (Exception e) {
			logger.error("SendInfoController.toEdit", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public RedirectView save(SendInfo sendInfo, HttpServletRequest request) {
		try {
			//SysUser seuser = (SysUser) this.getSessionAttribute(request, CoreConstant.SYS_USER_SESSION_NAME);
			//if(StringUtils.isNotEmpty(sendInfo.getId())){
				//sendInfo.setMtime(new Date());
				//if (seuser != null) {
					//sendInfo.setMuser(String.valueOf(seuser.getId()));
				//}
				//sendInfoService.updateSendInfoByObj(sendInfo);
			//}else{
				//sendInfo.setCtime(new Date());
				//sendInfo.setMtime(new Date());
				//if (seuser != null) {
					//sendInfo.setCuser(String.valueOf(seuser.getId()));
					//sendInfo.setMuser(String.valueOf(seuser.getId()));
				//}
				sendInfoService.addSendInfo(sendInfo);
			//}
		} catch (Exception e) {
			logger.error("SendInfoController.edit", e);
		}
		return new RedirectView("/manage/permission/sendinfo/list");
		
	}

	@RequestMapping("/delete")
	public RedirectView delete(String ids, HttpServletRequest request, SendInfo query, @ModelAttribute("page") PageEntity page,RedirectAttributes attr) {
		RedirectView rv = new RedirectView("/manage/permission/sendinfo/list");
		String[] idArray = ids.split(",");
		SendInfo sendInfo = new SendInfo();
		try {// 软删除状态设置为2
			for (String id : idArray) {
				if (!"".equals(id)) {
					sendInfo.setId(Long.valueOf(id));
					//sendInfo.setStatus(SendInfo.DELETE_STATUS);
					this.sendInfoService.updateSendInfoByObj(sendInfo);
				}
			}
			//attr.addAttribute("query", query);
			//attr.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("SendInfoController.delete", e);
		}
		return rv;
	}
}
