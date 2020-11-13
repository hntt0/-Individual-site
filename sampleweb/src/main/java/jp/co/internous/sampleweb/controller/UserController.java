package jp.co.internous.sampleweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.sampleweb.model.domain.MstUser;
import jp.co.internous.sampleweb.model.form.UserForm;
import jp.co.internous.sampleweb.model.mapper.MstUserMapper;
import jp.co.internous.sampleweb.model.session.LoginSession;

@Controller
@RequestMapping("/sampleweb/user")
public class UserController {
	
	@Autowired
	private MstUserMapper usermapper;
	
	@Autowired
	private LoginSession loginSession;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("loginSession", loginSession);
		return "register_user";
	}
	
	
	@PostMapping("/register")
	@ResponseBody
	public boolean register(@RequestBody UserForm form) {
		MstUser user = new MstUser(form);
		int count = usermapper.insert(user);
        return count > 0;
    }
	
	@PostMapping("/duplicatedUserName")
	@ResponseBody
	public boolean duplicatedUserName(@RequestParam String userName) {	
		int count = usermapper.findCountByUserName(userName);
		return count > 0;
	}
}
