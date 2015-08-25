package com.jsict.platform.exception;

public class LoginFailedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 958368255732645017L;

	public LoginFailedException(){
		super("Login Failed");
	}
}
