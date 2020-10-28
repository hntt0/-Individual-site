package jp.co.internous.sampleweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.sampleweb.model.mapper.MstDestinationMapper;

@Controller
@RequestMapping("/sampleweb/destination")
public class DestinationController {
		
	@Autowired
	private MstDestinationMapper mstDestination;
	
	@RequestMapping("/")
	public String index() {
		return "destination";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete(@RequestParam("id") int id) {
		int result = 0;
		result = mstDestination.findByUpdate(id);
		return result > 0;
	}

}
