package jp.co.internous.sampleweb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.sampleweb.model.domain.MstDestination;
import jp.co.internous.sampleweb.model.form.DestinationForm;
import jp.co.internous.sampleweb.model.mapper.MstDestinationMapper;
import jp.co.internous.sampleweb.model.mapper.TblCartMapper;
import jp.co.internous.sampleweb.model.session.LoginSession;

@Controller
@RequestMapping("/sampleweb/destination")
public class DestinationController {
	
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private TblCartMapper tblCartmapper;
	
	@Autowired
	private MstDestinationMapper destinationMapper;
	
	private Gson gson = new Gson();
	
	@RequestMapping("/")
	public String index(Model model) {
		int userId = loginSession.getUserId();
		model.addAttribute("loginSession", loginSession);
		model.addAttribute(userId);
		return "destination";
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public String register(@RequestBody DestinationForm destinationform) {
		int userId = loginSession.getUserId();
		MstDestination destination = new MstDestination(destinationform, userId);
		destinationMapper.insert(destination);
		String result = String.valueOf(destinationMapper.findById(userId));
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete(@RequestBody String destinationId) {
		
		Map<String, String> map = gson.fromJson(destinationId, Map.class);
		String id = map.get("destinationId");

		int result = tblCartmapper.deleteByUserId(Integer.parseInt(id));

		return result > 0;
	}

}
