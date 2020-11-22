package jp.co.internous.sampleweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import jp.co.internous.sampleweb.model.domain.MstUser;
import jp.co.internous.sampleweb.model.domain.TblCart;
import jp.co.internous.sampleweb.model.form.UserForm;
import jp.co.internous.sampleweb.model.mapper.MstUserMapper;
import jp.co.internous.sampleweb.model.mapper.TblCartMapper;
import jp.co.internous.sampleweb.model.session.LoginSession;

@RestController
@RequestMapping("/sampleweb/auth")
public class AuthController {
	
	private Gson gson = new Gson();
	
	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired
	private TblCartMapper cartMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	@RequestMapping("/login")
	public String login(@RequestBody UserForm f) {
		MstUser user = userMapper.findByUserNameAndPassword(f.getUserName(), f.getPassword());
		int tmpUserId = loginSession.getTmpUserId();
		// 仮IDでカート追加されていれば、本ユーザーIDに更新する。
		if (user != null && tmpUserId != 0) {
			int count = cartMapper.findCountByUserId(tmpUserId);
			if (count > 0) {
				cartMapper.updateUserId(user.getId(), tmpUserId);
				List<TblCart> tempItem = cartMapper.findByUserIdMax(user.getId());
				for(TblCart Item : tempItem) {
					int itemCount = cartMapper.findCountByUserIdAndProuductId(user.getId(), Item.getProductId());
					if(itemCount > 1) {
						cartMapper.update(Item);
						cartMapper.deleteById(Item.getId());
					}
				}
			}
		}
		
		if (user != null) {
			loginSession.setTmpUserId(0);
			loginSession.setLogined(true);
			loginSession.setUserId(user.getId());
			loginSession.setUserName(user.getUserName());
			loginSession.setPassword(user.getPassword());
		} else {
			loginSession.setLogined(false);
			loginSession.setUserId(0);
			loginSession.setUserName(null);
			loginSession.setPassword(null);
		}
		
		return gson.toJson(user);
	}
	
	@RequestMapping("/logout")
	public String logout() {
		loginSession.setTmpUserId(0);
		loginSession.setLogined(false);
		loginSession.setUserId(0);
		loginSession.setUserName(null);
		loginSession.setPassword(null);
		return "";
	}
	
	@RequestMapping("/resetPassword")
	@ResponseBody
	public String resetpassword(@RequestBody UserForm userForm) {
		String userName = userForm.getUserName();
		String password = userForm.getPassword();
		String newPassword = userForm.getNewPassword();
		String result = "";
		MstUser duplicate = userMapper.findByUserNameAndPassword(userName, loginSession.getPassword());
		if(!(duplicate.getPassword().equals(password))) {
			result = "現在のパスワードが正しくありません。";
		} else if(duplicate.getPassword().equals(newPassword)) {
			result = "現在パスワードと同一文字列が入力されました。";
		} else if(!(newPassword.equals(userForm.getNewPasswordConfirm()))) {
			result = "新しいパスワードと確認用パスワードが一致しません。";
		} else {
			userMapper.updatePassword(userName, newPassword);
			loginSession.setPassword(newPassword);
			result = "パスワードが再設定されました。";
		}
		return result;
	}
}