package cn.itcast.bbs.web.struts;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.ByteArrayConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.bbs.ExecutionContext;
import cn.itcast.bbs.cfg.SystemGlobals;
import cn.itcast.bbs.entities.Sex;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.article.Topic.TopicStatus;
import cn.itcast.bbs.entities.article.Topic.TopicType;
import cn.itcast.bbs.exception.runtime.ItcastException;
import cn.itcast.bbs.service.UserService;
import cn.itcast.bbs.web.WebConstants;
import cn.itcast.bbs.web.helper.WebCommonHelper;
import cn.itcast.bbs.web.helper.WebUserHelper;
import cn.itcast.bbs.web.struts.utils.ActionFormDate;
import cn.itcast.bbs.web.struts.utils.ActionFormDateConverter;
import cn.itcast.bbs.web.struts.utils.EnumConverter;
import cn.itcast.bbs.web.utils.CookieUtils;

/**
 * @author 传智播客.汤阳光 Jun 6, 2008
 */
@SuppressWarnings("serial")
public class ActionServlet extends org.apache.struts.action.ActionServlet {
	protected static final Log log = LogFactory.getLog(ActionServlet.class);
	protected UserService userService;

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// 1, 设置编码
		request.setCharacterEncoding("utf-8");

		// 2, 检查自动登录
		if (!WebCommonHelper.isLoggedOn(request)) {
			Cookie cookie = CookieUtils.getCookie(request, WebConstants.COOKIE_NAME_AUTO_LOGIN);
			checkAutoLogin(request, cookie);
			// FIXME 不需要登陆匿名用户, 因为用不着?
			// if (!checkAutoLogin(request, cookie)) { // 如果未自动登录成功, 就登录匿名用户
			// WebUserHelper.login(request, SystemGlobals.getSettings().getAnonymousUser());
			// }
		}

		// 3, 对于登录用户每一次访问都要 更新其最后访问的时间 与最后一次访问时所用的IP地址
		User user = WebCommonHelper.getLoggedOnUser(request);
		if (user != null) {
			user.setLastVisitTime(new Date());
			user.setLastVisitIpAddr(request.getRemoteAddr());
			userService.updateUserInfo(user);
		}

		// -----------------------------------------------

		// --- 准备ForumContext数据
		ExecutionContext.set(new ExecutionContext(request, response));

		// 执行请求
		super.process(request, response);
	}

	/**
	 * 检查自动登录的cookie,如果通过,就登录用户
	 * 
	 * @param request
	 * @param cookie
	 * @return 是否自动登录成功
	 */
	private boolean checkAutoLogin(HttpServletRequest request, Cookie cookie) {
		if (cookie == null || cookie.getValue() == null) {
			return false;
		}

		try {
			String[] valueTokens = cookie.getValue().split("_");
			int userId = Integer.parseInt(valueTokens[0]);
			long expiryTime = Long.parseLong(valueTokens[1]);

			if (System.currentTimeMillis() > expiryTime) { // 过期不能自动登录
				return false;
			}

			User user = userService.getUser(userId);
			if (cookie.getValue().equals(user.getAutoLoginAuthKey())) {
				WebUserHelper.login(request, user);
			}
			return true;

		} catch (RuntimeException e) {
			e.printStackTrace();
			log.warn(e.getMessage() + ", 可能是自动登录的Cookie的格式不正确【cookie.value=" + cookie.getValue() + "】");
			return false;
		}
	}

	/**
	 * 初始化
	 */
	@Override
	public void init() throws ServletException {
		super.init();

		ServletContext application = getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		userService = (UserService) ac.getBean("userService");

		// --- 初始化系统设置
		String applicationPath = getServletContext().getRealPath(""); // 应用程序的路径
		String defaultConfigFileLocation = getDefaultConfigPath(getServletContext());
		SystemGlobals.init(applicationPath, defaultConfigFileLocation, ac); // initialize

		// --- 设置Settings
		application.setAttribute(WebConstants.SCOPE_ATTR_SETTINGS, SystemGlobals.getSettings());

		// --- 设置常量
		preEnumValuesInApplicationScope(getServletContext());

		// --- 注册转换器
		// 重新注册byteArray的converter,为了指定defaultValue为null, 而预注册的默认值是byte[0]
		// 这样就保证了在BeanUtils.copyProperties(destUser, origUser)时, 如果origUser.avatar为null, destUser.avatar也应为null.
		ConvertUtils.register(new ByteArrayConverter(null), byte[].class);
		ConvertUtils.register(new ActionFormDateConverter(), ActionFormDate.class);
		// ConvertUtils.register(new SexConverter(), Sex.class);
		// ConvertUtils.register(new TopicTypeConverter(), TopicType.class);
		// ConvertUtils.register(new TopicStatusConverter(), TopicStatus.class);
		ConvertUtils.register(new EnumConverter(Sex.class), Sex.class);
		ConvertUtils.register(new EnumConverter(TopicType.class), TopicType.class);
		ConvertUtils.register(new EnumConverter(TopicStatus.class), TopicStatus.class);

		// HttpSessionManager.invalidateAll(); //FIXME 有的可能是序列化的
	}

	/**
	 * 获取bbs的默认的配置文件的路径, 可以在web.xml中配置, key为'defaultConfigLocation'.<br>
	 * 配置的路径要以'/'开头, 代表classpath根路径<br>
	 * 如果没有配置, 则默认为'/bbs/default.properties'
	 * 
	 * @param application
	 * @return
	 */
	protected String getDefaultConfigPath(ServletContext application) {
		String configFile = getServletContext().getInitParameter("defaultConfigLocation"); //
		if (configFile == null) { // 如果没有配置, 则有一个默认的路径
			configFile = "/bbs/default.properties";
		}

		URL url = this.getClass().getResource(configFile);
		if (url == null) {
			throw new ItcastException("找不到指定的配置文件：" + configFile);
		}

		try {
			return URLDecoder.decode(url.getPath(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new ItcastException(e);
		}
	}

	/**
	 * 把枚举中的值转为LabelValueBean并放到一个List中, 然后把这个List放到application作用域中
	 * 
	 * @param application
	 */
	public void preEnumValuesInApplicationScope(ServletContext application) {
		// --- Sex
		List<LabelValueBean> sexes = new ArrayList<LabelValueBean>();
		for (Sex e : Sex.values()) {
			sexes.add(new LabelValueBean(e.getLabel(), e.getValue()));
		}
		application.setAttribute("sexes", sexes);

		// --- TopicType
		List<LabelValueBean> topicTypes = new ArrayList<LabelValueBean>();
		for (TopicType e : TopicType.values()) {
			topicTypes.add(new LabelValueBean(e.getLabel(), e.getValue()));
		}
		application.setAttribute("topicTypes", topicTypes);

		// --- TopicStatus
		List<LabelValueBean> topicStatuses = new ArrayList<LabelValueBean>();
		for (TopicStatus e : TopicStatus.values()) {
			topicStatuses.add(new LabelValueBean(e.getLabel(), e.getValue()));
		}
		application.setAttribute("topicStatuses", topicStatuses);
	}
}
