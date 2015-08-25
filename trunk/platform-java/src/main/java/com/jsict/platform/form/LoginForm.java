package com.jsict.platform.form;

import com.jsict.base.form.BaseForm;

public class LoginForm extends BaseForm {

	private String userId = null;

	private String password = null;

	private String rememberMe = null;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

}
