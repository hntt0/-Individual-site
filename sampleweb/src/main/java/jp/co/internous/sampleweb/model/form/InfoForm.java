package jp.co.internous.sampleweb.model.form;

import java.io.Serializable;

public class InfoForm implements Serializable{
	private static final long serialVersionUID = -2612434955472567758L;

	private String userName;
	
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
