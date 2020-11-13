package jp.co.internous.sampleweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.sampleweb.model.domain.MstUser;
import jp.co.internous.sampleweb.model.mapper.MstUserMapper;
import jp.co.internous.sampleweb.model.session.LoginSession;

@Controller
@RequestMapping("/sampleweb/mypage")
public class MyPageController {
	
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private MstUserMapper mstUserMapper;
	
	@RequestMapping("/")
	public String index(Model model) {
		String userName = loginSession.getUserName();
		String userPassword = loginSession.getPassword();
		/* ユーザー情報を取得 */
		MstUser mstUser = mstUserMapper.findByUserNameAndPassword(userName, userPassword);
		model.addAttribute("loginSession", loginSession);
		model.addAttribute("mstUser", mstUser);
		return "my_page";
	}
	

}
