package jp.co.internous.sampleweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.sampleweb.model.domain.MstCategory;
import jp.co.internous.sampleweb.model.domain.MstProduct;
import jp.co.internous.sampleweb.model.form.SearchForm;
import jp.co.internous.sampleweb.model.mapper.MstCategoryMapper;
import jp.co.internous.sampleweb.model.mapper.MstProductMapper;
import jp.co.internous.sampleweb.model.session.LoginSession;

@Controller
@RequestMapping("/sampleweb")
public class IndexController {
	
	@Autowired
	private MstProductMapper productMapper;
	
	@Autowired
	private MstCategoryMapper categoryMapper;
	
	@Autowired
	private LoginSession loginSession;

	@RequestMapping("/")
	public String index(Model model) {
		List<MstProduct> product = productMapper.productAll();
		List<MstCategory> category = categoryMapper.categoryAll();
		if(!loginSession.getLogined() && loginSession.getTmpUserId() == 0) {
			loginSession.setTmpUserId((int)(Math.random() * -1000000000));
		}
			model.addAttribute("products", product);
			model.addAttribute("category", category);
		return "index";
	}
	
	@RequestMapping("/searchItem")
	public String serchitem(SearchForm form, Model model) {
		List<MstCategory> category = categoryMapper.categoryAll();
		List<MstProduct> product = null;
		int categoryId = form.getCategoryId();
		String[] productNames = null;
		//categoryIdのみの場合
		if(categoryId != 0 && productNames == null) {
			product = productMapper.findByCategoryId(categoryId);
		}
		//productNameを受け取った場合
		if(form.getProductName() != "") {
			productNames = form.getProductName().replaceAll("　", " ").replaceAll("\\s+", " ").trim().split(" ");
				if(categoryId == 0) {
					product = productMapper.findByProductName(productNames);
				} else if (categoryId != 0) {
					product = productMapper.findByCategoryIdProductName(categoryId, productNames);
				}
		}
		model.addAttribute("products", product);
		model.addAttribute("category", category);
		return "index";
	}
	
}
