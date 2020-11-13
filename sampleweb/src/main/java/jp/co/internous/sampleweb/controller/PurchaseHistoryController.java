package jp.co.internous.sampleweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.sampleweb.model.domain.dto.PurchaseHistoryDto;
import jp.co.internous.sampleweb.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.sampleweb.model.session.LoginSession;

@Controller
@RequestMapping("/sampleweb/history")
public class PurchaseHistoryController {

	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private TblPurchaseHistoryMapper historyMapper;
	
	@RequestMapping("/")
	public String index(Model model) {
		int userId = loginSession.getUserId();
		List<PurchaseHistoryDto> history = historyMapper.findByUserId(userId);
		model.addAttribute("loginSession", loginSession);
		model.addAttribute("history", history);
		return "purchase_history";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete() {
		int userId = loginSession.getUserId();
		int result = historyMapper.findByDeletehistory(userId);
		return result > 0;
	}
}
