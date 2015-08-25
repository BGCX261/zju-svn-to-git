package cn.itcast.bbs.web.helper;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.logic.helper.UserHelper;
import cn.itcast.bbs.service.SystemService;
import cn.itcast.bbs.web.OnlineUsersManager;
import cn.itcast.bbs.web.WebConstants;
import cn.itcast.bbs.web.utils.WebAppUtils;

/**
 * Web层通用的工具类
 * 
 * @author 传智播客.汤阳光 Jul 7, 2008
 */
public class WebCommonHelper {
	// private static Log log = LogFactory.getLog(WebCommonHelper.class);

	/**
	 * 向request作用域中存放当前系统的一些信息: 主题数, 文章数, 会员数, 在线统计与在线会员.
	 * 
	 * @param request
	 * @param systemService
	 */
	public static void preSystemStatus(HttpServletRequest request, SystemService systemService) {
		int topicCount = systemService.getTopicCount();
		int articleCount = systemService.getArticleCount();
		int userCount = systemService.getUserCount();
		request.setAttribute("topicCount", topicCount);
		request.setAttribute("articleCount", articleCount);
		request.setAttribute("userCount", userCount);

		int totalOnlineUsers = OnlineUsersManager.getInstance().getOnlineThreadsCount();
		Set<User> onlineUsers = OnlineUsersManager.getInstance().getOnlineUsers();
		request.setAttribute("onlineCount", totalOnlineUsers);
		request.setAttribute("onlineUsers", onlineUsers);
	}

	/**
	 * @param request
	 * @return 是否已登陆用户
	 */
	public static boolean isLoggedOn(HttpServletRequest request) {
		User user = getLoggedOnUser(request);
		return (user != null && !UserHelper.isAnonymousUser(user));
	}

	/**
	 * 获取当前登录用户
	 * 
	 * @param request
	 * @return
	 */
	public static User getLoggedOnUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(WebConstants.SESSION_KEY_CURRENTLY_LOGGED_ON_USER);
		return user;
	}

	/**
	 * 重定向到登陆页面, 并带一个参数: 登陆成功后返回到路径 returnPath<br>
	 * 如果returnPath为null, 则使用本次请求的地址为returnPath
	 * 
	 * @param request
	 * @param response
	 * @param returnPath
	 * @throws IOException
	 */
	public static void toLogin(HttpServletRequest request, HttpServletResponse response, String returnPath) throws IOException {
		response.sendRedirect(request.getContextPath() + "/user/action.do?method=loginUI&returnPath=" + makeReturnPath(request, returnPath));
	}
	
	public static void preReturnPath(HttpServletRequest request){
		String referer = request.getHeader("Referer");
		String returnPath = request.getParameter("returnPath");

		// 如果没有指定returnPath, 则使用referer指定的地址为登陆成功后跳回的页面
		if (returnPath == null && referer != null && referer.startsWith(WebAppUtils.getBaseUrl(request))) {
			returnPath = WebCommonHelper.makeReturnPath(request, referer);
		}

		request.setAttribute(WebConstants.SCOPE_ATTR_RETURN_PATH, returnPath);
	}

	/**
	 * 准备登陆成功后要返回的地址returnPath, 如果传递的参数<code>path</code>为null, 则使用当前请求的地址<br>
	 * 这个地址使用 Base64 编码.
	 * 
	 * @param request
	 * @param path
	 *            要进行处理的地址
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String makeReturnPath(HttpServletRequest request, String path) {
		if (path == null) {
			path = request.getRequestURL().toString();
			Map<String, String[]> params = request.getParameterMap();
			boolean isFirstFlag = true;
			for (Entry<String, String[]> e : params.entrySet()) {
				for (String value : e.getValue()) {
					if (isFirstFlag) {
						path += "?" + e.getKey() + "=" + value;
						isFirstFlag = false;
					} else {
						path += "&" + e.getKey() + "=" + value;
					}
				}
			}
		}

		return new String(Base64.encodeBase64(path.getBytes()));
	}

	/**
	 * 从request中取出登陆成功后要返回的地址，正确解码后返回<br>
	 * 使用 Base64 编码
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String decodeReturnPath(HttpServletRequest request) {
		String returnPath = request.getParameter(WebConstants.SCOPE_ATTR_RETURN_PATH);
		if (returnPath == null) {
			return null;
		}

		if (StringUtils.isNotBlank(returnPath)) {
			byte[] buf = Base64.decodeBase64(returnPath.getBytes());
			String decodedPath = new String(buf);
			return decodedPath;
		}
		return null;
	}
}
