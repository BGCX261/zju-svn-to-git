package cn.itcast.bbs.web.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public class WebAppUtils {

	/**
	 * @param request
	 * @return 应用的路径，如 http://localhost:8080/itcastForum/
	 */
	public static String getBaseUrl(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

		return basePath;
	}

}
