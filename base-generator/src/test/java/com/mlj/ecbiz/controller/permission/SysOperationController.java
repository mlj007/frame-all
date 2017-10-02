package  com.mlj.ecbiz.controller.permission;

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
import com.mlj.ecbiz.model.permission.SysOperation;
//import com.chexun.partner.model.system.SysUser;
import com.mlj.ecbiz.service.permission.SysOperationService;

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
@RequestMapping("/manage/permission/sysoperation")
public class SysOperationController extends BaseController {

	private static final Logger logger = Logger.getLogger(SysOperationController.class);

	@Autowired
	private SysOperationService sysOperationService;

	// 路径
	private String toList = "/permission/sysoperation_list.httl";// 列表页
	private String toAdd = "/permission/sysoperation_add.httl";// 添加页面
	private String toEdit = "/permission/sysoperation_edit.httl";// 修改页

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, SysOperation query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
			if (query == null) {
				query = new SysOperation();
			}
			List<SysOperation> list = sysOperationService.getSysOperationPage(query, this.getPage());
			modelAndView.addObject("query", query);
			modelAndView.addObject("sysOperationList", list);
			modelAndView.addObject("page", this.getPage());
		} catch (Exception e) {
			logger.error("SysOperationController.listAll", e);
			//return new ModelAndView(setExceptionRequestAdmin(request, e));
		}

		return modelAndView;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toAdd);
		try {
		} catch (Exception e) {
			logger.error("SysOperationController.toAdd", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView toEdit(Long id,HttpServletRequest request) {
		ModelAndView modelAndView =new ModelAndView(toEdit);
		try {
			SysOperation sysOperation = sysOperationService.getSysOperationById(id);
			modelAndView.addObject(sysOperation);
		} catch (Exception e) {
			logger.error("SysOperationController.toEdit", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public RedirectView save(SysOperation sysOperation, HttpServletRequest request) {
		try {
			//SysUser seuser = (SysUser) this.getSessionAttribute(request, CoreConstant.SYS_USER_SESSION_NAME);
			//if(StringUtils.isNotEmpty(sysOperation.getId())){
				//sysOperation.setMtime(new Date());
				//if (seuser != null) {
					//sysOperation.setMuser(String.valueOf(seuser.getId()));
				//}
				//sysOperationService.updateSysOperationByObj(sysOperation);
			//}else{
				//sysOperation.setCtime(new Date());
				//sysOperation.setMtime(new Date());
				//if (seuser != null) {
					//sysOperation.setCuser(String.valueOf(seuser.getId()));
					//sysOperation.setMuser(String.valueOf(seuser.getId()));
				//}
				sysOperationService.addSysOperation(sysOperation);
			//}
		} catch (Exception e) {
			logger.error("SysOperationController.edit", e);
		}
		return new RedirectView("/manage/permission/sysoperation/list");
		
	}

	@RequestMapping("/delete")
	public RedirectView delete(String ids, HttpServletRequest request, SysOperation query, @ModelAttribute("page") PageEntity page,RedirectAttributes attr) {
		RedirectView rv = new RedirectView("/manage/permission/sysoperation/list");
		String[] idArray = ids.split(",");
		SysOperation sysOperation = new SysOperation();
		try {// 软删除状态设置为2
			for (String id : idArray) {
				if (!"".equals(id)) {
					sysOperation.setId(Long.valueOf(id));
					//sysOperation.setStatus(SysOperation.DELETE_STATUS);
					this.sysOperationService.updateSysOperationByObj(sysOperation);
				}
			}
			//attr.addAttribute("query", query);
			//attr.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("SysOperationController.delete", e);
		}
		return rv;
	}
}
