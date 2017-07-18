package  com.mlj.ecbiz.controller.product;

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
import com.mlj.ecbiz.model.product.ProductInfo;
//import com.chexun.partner.model.system.SysUser;
import com.mlj.ecbiz.service.product.ProductInfoService;

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
@RequestMapping("/manage/product/productinfo")
public class ProductInfoController extends BaseController {

	private static final Logger logger = Logger.getLogger(ProductInfoController.class);

	@Autowired
	private ProductInfoService productInfoService;

	// 路径
	private String toList = "/product/productinfo_list.httl";// 列表页
	private String toAdd = "/product/productinfo_add.httl";// 添加页面
	private String toEdit = "/product/productinfo_edit.httl";// 修改页

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, ProductInfo query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
			if (query == null) {
				query = new ProductInfo();
			}
			List<ProductInfo> list = productInfoService.getProductInfoPage(query, this.getPage());
			modelAndView.addObject("query", query);
			modelAndView.addObject("productInfoList", list);
			modelAndView.addObject("page", this.getPage());
		} catch (Exception e) {
			logger.error("ProductInfoController.listAll", e);
			//return new ModelAndView(setExceptionRequestAdmin(request, e));
		}

		return modelAndView;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toAdd);
		try {
		} catch (Exception e) {
			logger.error("ProductInfoController.toAdd", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView toEdit(Long id,HttpServletRequest request) {
		ModelAndView modelAndView =new ModelAndView(toEdit);
		try {
			ProductInfo productInfo = productInfoService.getProductInfoById(id);
			modelAndView.addObject(productInfo);
		} catch (Exception e) {
			logger.error("ProductInfoController.toEdit", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public RedirectView save(ProductInfo productInfo, HttpServletRequest request) {
		try {
			//SysUser seuser = (SysUser) this.getSessionAttribute(request, CoreConstant.SYS_USER_SESSION_NAME);
			//if(StringUtils.isNotEmpty(productInfo.getId())){
				//productInfo.setMtime(new Date());
				//if (seuser != null) {
					//productInfo.setMuser(String.valueOf(seuser.getId()));
				//}
				//productInfoService.updateProductInfoByObj(productInfo);
			//}else{
				//productInfo.setCtime(new Date());
				//productInfo.setMtime(new Date());
				//if (seuser != null) {
					//productInfo.setCuser(String.valueOf(seuser.getId()));
					//productInfo.setMuser(String.valueOf(seuser.getId()));
				//}
				productInfoService.addProductInfo(productInfo);
			//}
		} catch (Exception e) {
			logger.error("ProductInfoController.edit", e);
		}
		return new RedirectView("/manage/product/productinfo/list");
		
	}

	@RequestMapping("/delete")
	public RedirectView delete(String ids, HttpServletRequest request, ProductInfo query, @ModelAttribute("page") PageEntity page,RedirectAttributes attr) {
		RedirectView rv = new RedirectView("/manage/product/productinfo/list");
		String[] idArray = ids.split(",");
		ProductInfo productInfo = new ProductInfo();
		try {// 软删除状态设置为2
			for (String id : idArray) {
				if (!"".equals(id)) {
					productInfo.setId(Long.valueOf(id));
					//productInfo.setStatus(ProductInfo.DELETE_STATUS);
					this.productInfoService.updateProductInfoByObj(productInfo);
				}
			}
			//attr.addAttribute("query", query);
			//attr.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("ProductInfoController.delete", e);
		}
		return rv;
	}
}
