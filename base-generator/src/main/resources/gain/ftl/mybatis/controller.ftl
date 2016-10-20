package  ${packageName?replace('/','.')}.controller.${mypackageName};

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
import ${packageName?replace('/','.')}.model.${mypackageName}.${voClassName};
//import com.chexun.partner.model.system.SysUser;
import ${packageName?replace('/','.')}.service.${mypackageName}.${voClassName}Service;

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
@RequestMapping("/manage/${mypackageName}/${voClassName?lower_case}")
public class ${voClassName}Controller extends BaseController {

	private static final Logger logger = Logger.getLogger(${voClassName}Controller.class);

	@Autowired
	private ${voClassName}Service ${voClassName?uncap_first}Service;

	// 路径
	private String toList = "/${mypackageName}/${voClassName?lower_case}_list.httl";// 列表页
	private String toAdd = "/${mypackageName}/${voClassName?lower_case}_add.httl";// 添加页面
	private String toEdit = "/${mypackageName}/${voClassName?lower_case}_edit.httl";// 修改页

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, ${voClassName} query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
			if (query == null) {
				query = new ${voClassName}();
			}
			List<${voClassName}> list = ${voClassName?uncap_first}Service.get${voClassName}Page(query, this.getPage());
			modelAndView.addObject("query", query);
			modelAndView.addObject("${voClassName?uncap_first}List", list);
			modelAndView.addObject("page", this.getPage());
		} catch (Exception e) {
			logger.error("${voClassName}Controller.listAll", e);
			//return new ModelAndView(setExceptionRequestAdmin(request, e));
		}

		return modelAndView;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toAdd);
		try {
		} catch (Exception e) {
			logger.error("${voClassName}Controller.toAdd", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView toEdit(Long id,HttpServletRequest request) {
		ModelAndView modelAndView =new ModelAndView(toEdit);
		try {
			${voClassName} ${voClassName?uncap_first} = ${voClassName?uncap_first}Service.get${voClassName}ById(id);
			modelAndView.addObject(${voClassName?uncap_first});
		} catch (Exception e) {
			logger.error("${voClassName}Controller.toEdit", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public RedirectView save(${voClassName} ${voClassName?uncap_first}, HttpServletRequest request) {
		try {
			//SysUser seuser = (SysUser) this.getSessionAttribute(request, CoreConstant.SYS_USER_SESSION_NAME);
			//if(StringUtils.isNotEmpty(${voClassName?uncap_first}.getId())){
				//${voClassName?uncap_first}.setMtime(new Date());
				//if (seuser != null) {
					//${voClassName?uncap_first}.setMuser(String.valueOf(seuser.getId()));
				//}
				//${voClassName?uncap_first}Service.update${voClassName}ByObj(${voClassName?uncap_first});
			//}else{
				//${voClassName?uncap_first}.setCtime(new Date());
				//${voClassName?uncap_first}.setMtime(new Date());
				//if (seuser != null) {
					//${voClassName?uncap_first}.setCuser(String.valueOf(seuser.getId()));
					//${voClassName?uncap_first}.setMuser(String.valueOf(seuser.getId()));
				//}
				${voClassName?uncap_first}Service.add${voClassName}(${voClassName?uncap_first});
			//}
		} catch (Exception e) {
			logger.error("${voClassName}Controller.edit", e);
		}
		return new RedirectView("/manage/${mypackageName}/${voClassName?lower_case}/list");
		
	}

	@RequestMapping("/delete")
	public RedirectView delete(String ids, HttpServletRequest request, ${voClassName} query, @ModelAttribute("page") PageEntity page,RedirectAttributes attr) {
		RedirectView rv = new RedirectView("/manage/${mypackageName}/${voClassName?lower_case}/list");
		String[] idArray = ids.split(",");
		${voClassName} ${voClassName?uncap_first} = new ${voClassName}();
		try {// 软删除状态设置为2
			for (String id : idArray) {
				if (!"".equals(id)) {
					${voClassName?uncap_first}.setId(Long.valueOf(id));
					//${voClassName?uncap_first}.setStatus(${voClassName}.DELETE_STATUS);
					this.${voClassName?uncap_first}Service.update${voClassName}ByObj(${voClassName?uncap_first});
				}
			}
			//attr.addAttribute("query", query);
			//attr.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("${voClassName}Controller.delete", e);
		}
		return rv;
	}
}
