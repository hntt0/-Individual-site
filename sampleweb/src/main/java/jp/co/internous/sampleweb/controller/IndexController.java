package jp.co.internous.sampleweb.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jp.co.internous.sampleweb.model.domain.MstProduct;
import jp.co.internous.sampleweb.model.mapper.MstProductMapper;

@Controller
@RequestMapping("/sampleweb")
public class IndexController {
	
	@Autowired
	private MstProductMapper productMapper;

	@RequestMapping("/")
	public String index(Model model) {
		List<MstProduct> product = productMapper.productAll();
			model.addAttribute("products", product);
		return "index";
	}
	
}
