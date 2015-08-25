package cn.itcast.bbs.web.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public class CookieUtils {

	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 *            不能为null
	 * @param value
	 *            默认值空字符串
	 * @param maxAge
	 *            默认值0
	 * @param path
	 *            默认值'/'
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, Integer maxAge, String path) {
		if (maxAge == null) {
			maxAge = 0;
		}
		if (value == null) {
			value = "";
		}
		if (path == null) {
			path = "/";
		}

		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath(path);

		response.addCookie(cookie);
	}

	/**
	 * @param request
	 * @param cookieName
	 * @return 指定的cookie
	 */
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(cookieName)) {
					return c;
				}
			}
		}

		return null;
	}
}
