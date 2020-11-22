package jp.co.internous.sampleweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.sampleweb.model.domain.MstProduct;
import jp.co.internous.sampleweb.model.mapper.MstProductMapper;
import jp.co.internous.sampleweb.model.session.LoginSession;

@Controller
@RequestMapping("/sampleweb/product")
public class ProductController {
	
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private MstProductMapper productMapper;

	@RequestMapping("/{id}")
	public String index(@PathVariable("id") int id, Model model) {
		MstProduct product = productMapper.findById(id);
		model.addAttribute("loginSession", loginSession);
		model.addAttribute("product", product);
		return "product_detail";
	}
	
}
