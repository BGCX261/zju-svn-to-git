package cn.itcast.bbs.web;

/**
 * 把对象放到作用域中时所使用的key、或是cookie的名字等常量
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public interface WebConstants {

	/** 异常 */
	String REQUEST_KEY_HAS_EXCEPTIONACTION = "cn.itcast.bbs.web.WebConstants.REQUEST_KEY_HAS_EXCEPTIONACTION";
	
	/** 系统设置 */
	String SCOPE_ATTR_SETTINGS = "settings";

	/** 分类的集合 */
	String SCOPE_ATTR_CATEGORIES = "categories";

	/** 当前登陆用户 */
	String SESSION_KEY_CURRENTLY_LOGGED_ON_USER = "SESSION_KEY_CURRENTLY_LOGGED_ON_USER";

	/** 上一次发帖时间 */
	String SESSION_KEY_LAST_POST_TIME = "cn.itcast.bbs.web.WebConstants.SESSION_KEY_LAST_POST_TIME";

	/** 登陆成功后要返回的地址 */
	String SCOPE_ATTR_RETURN_PATH = "returnPath";

	/** 用于自动登陆的cookie的name */
	String COOKIE_NAME_AUTO_LOGIN = "ITCAST_BBS_COOKIE_NAME_AUTO_LOGIN";

}
