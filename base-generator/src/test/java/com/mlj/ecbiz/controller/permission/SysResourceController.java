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
import com.mlj.ecbiz.model.permission.SysResource;
//import com.chexun.partner.model.system.SysUser;
import com.mlj.ecbiz.service.permission.SysResourceService;

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
@RequestMapping("/manage/permission/sysresource")
public class SysResourceController extends BaseController {

	private static final Logger logger = Logger.getLogger(SysResourceController.class);

	@Autowired
	private SysResourceService sysResourceService;

	// 路径
	private String toList = "/permission/sysresource_list.httl";// 列表页
	private String toAdd = "/permission/sysresource_add.httl";// 添加页面
	private String toEdit = "/permission/sysresource_edit.httl";// 修改页

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, SysResource query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
			if (query == null) {
				query = new SysResource();
			}
			List<SysResource> list = sysResourceService.getSysResourcePage(query, this.getPage());
			modelAndView.addObject("query", query);
			modelAndView.addObject("sysResourceList", list);
			modelAndView.addObject("page", this.getPage());
		} catch (Exception e) {
			logger.error("SysResourceController.listAll", e);
			//return new ModelAndView(setExceptionRequestAdmin(request, e));
		}

		return modelAndView;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toAdd);
		try {
		} catch (Exception e) {
			logger.error("SysResourceController.toAdd", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView toEdit(Long id,HttpServletRequest request) {
		ModelAndView modelAndView =new ModelAndView(toEdit);
		try {
			SysResource sysResource = sysResourceService.getSysResourceById(id);
			modelAndView.addObject(sysResource);
		} catch (Exception e) {
			logger.error("SysResourceController.toEdit", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public RedirectView save(SysResource sysResource, HttpServletRequest request) {
		try {
			//SysUser seuser = (SysUser) this.getSessionAttribute(request, CoreConstant.SYS_USER_SESSION_NAME);
			//if(StringUtils.isNotEmpty(sysResource.getId())){
				//sysResource.setMtime(new Date());
				//if (seuser != null) {
					//sysResource.setMuser(String.valueOf(seuser.getId()));
				//}
				//sysResourceService.updateSysResourceByObj(sysResource);
			//}else{
				//sysResource.setCtime(new Date());
				//sysResource.setMtime(new Date());
				//if (seuser != null) {
					//sysResource.setCuser(String.valueOf(seuser.getId()));
					//sysResource.setMuser(String.valueOf(seuser.getId()));
				//}
				sysResourceService.addSysResource(sysResource);
			//}
		} catch (Exception e) {
			logger.error("SysResourceController.edit", e);
		}
		return new RedirectView("/manage/permission/sysresource/list");
		
	}

	@RequestMapping("/delete")
	public RedirectView delete(String ids, HttpServletRequest request, SysResource query, @ModelAttribute("page") PageEntity page,RedirectAttributes attr) {
		RedirectView rv = new RedirectView("/manage/permission/sysresource/list");
		String[] idArray = ids.split(",");
		SysResource sysResource = new SysResource();
		try {// 软删除状态设置为2
			for (String id : idArray) {
				if (!"".equals(id)) {
					sysResource.setId(Long.valueOf(id));
					//sysResource.setStatus(SysResource.DELETE_STATUS);
					this.sysResourceService.updateSysResourceByObj(sysResource);
				}
			}
			//attr.addAttribute("query", query);
			//attr.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("SysResourceController.delete", e);
		}
		return rv;
	}
}
