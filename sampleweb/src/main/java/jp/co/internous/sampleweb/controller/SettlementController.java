package jp.co.internous.sampleweb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.sampleweb.model.domain.MstDestination;
import jp.co.internous.sampleweb.model.mapper.MstDestinationMapper;
import jp.co.internous.sampleweb.model.mapper.TblCartMapper;
import jp.co.internous.sampleweb.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.sampleweb.model.session.LoginSession;

@Controller
@RequestMapping("/sampleweb/settlement")
public class SettlementController {
	
	
	@Autowired
	private TblCartMapper tblCartmapper;
	
	@Autowired
	private LoginSession loginSession;

	@Autowired
	private TblPurchaseHistoryMapper  historymapper;
	
	@Autowired
	private MstDestinationMapper mstDestination;
	
	Gson gson = new Gson();
	
	@RequestMapping("/")
	public String index(Model model) {
		int userId = loginSession.getUserId();
		List<MstDestination> destinations = mstDestination.findByUserId(userId);
		model.addAttribute("loginSession", loginSession);
		model.addAttribute("destinations", destinations);
		return "settlement";
	}
	
	@RequestMapping("/complete")
	@ResponseBody
	public boolean complete(@RequestBody String destinationId) {
		Map<String, String> map = gson.fromJson(destinationId, Map.class);
		String id = map.get("destinationId");
		
		int userId = loginSession.getUserId();
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("destinationId", id);
		parameter.put("userId", userId);
		int insertCount =  historymapper.insert(parameter);
		
		int deleteCount = 0;
		if (insertCount > 0) {
			deleteCount = tblCartmapper.deleteByUserId(userId);
		}
		return deleteCount == insertCount;
	}
}
