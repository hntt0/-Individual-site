package jp.co.internous.sampleweb.controller;

import java.util.Arrays;
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

@Controller
@RequestMapping("/sampleweb")
public class IndexController {
	
	@Autowired
	private MstProductMapper productMapper;
	
	@Autowired
	private MstCategoryMapper categoryMapper;

	@RequestMapping("/")
	public String index(Model model) {
		List<MstProduct> product = productMapper.productAll();
		List<MstCategory> category = categoryMapper.categoryAll();
			model.addAttribute("products", product);
			model.addAttribute("category", category);
		return "index";
	}
	
	@RequestMapping("/searchItem")
	public String serchitem(SearchForm form, Model model) {
		List<MstCategory> category = categoryMapper.categoryAll();
		List<MstProduct> product = null;
		int categoryId = form.getCategoryId();
		String[] productName = null;
		if(categoryId != 0 && productName == null) {
			System.out.println(categoryId);
			product = productMapper.findByCategoryId(categoryId);
		}
		if(form.getProductName() != "") {
			productName = form.getProductName().replaceAll("　", " ").replaceAll("\\s+", " ").split(" ");
			List<String> productNames = Arrays.asList(productName);
//			for(String productName : productNames) {
			System.out.println(productNames.get(0));
				if(categoryId == 0) {
					product = productMapper.findByProductName(productNames);
				} else if (categoryId != 0) {
					product = productMapper.findByCategoryIdProductName(categoryId, productNames);
				}
//			}
		}
		model.addAttribute("products", product);
		model.addAttribute("category", category);
		return "index";
	}
	
}
