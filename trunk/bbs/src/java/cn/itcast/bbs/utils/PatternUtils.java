package cn.itcast.bbs.utils;

import java.util.regex.Pattern;

/**
 * @author 传智播客.汤阳光 Jul 7, 2008
 */
public class PatternUtils {
	
	public static final String EMAIL = "^[a-zA-Z0-9_\\.]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
	public static final String IP = "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])(\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])){3}$";
	
	public static boolean isEmail(String email) {
		if(email == null){
			return false;
		}
		return match(EMAIL, email);
	}
	
	public static boolean match(String pattern, String str) {
		if (str == null) {
			return false;
		}
		return Pattern.compile(pattern).matcher(str).find();
	}
	
	public static void main(String[] args) {
		String email = "aa._@a_a.a";
		boolean b = PatternUtils.match(PatternUtils.EMAIL, email);
		System.out.println(b);
		
		String ip = "127.0.0.100";
		b = match(IP, ip);
		System.out.println(b);
	}
	
}
