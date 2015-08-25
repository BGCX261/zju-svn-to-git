package cn.itcast.bbs.web;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import cn.itcast.bbs.cfg.SystemGlobals;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.logic.helper.UserHelper;

/**
 * 记录在线用户
 * 
 * @author 传智播客.汤阳光 Apr 30, 2009
 * 
 */
public class OnlineUsersManager implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final OnlineUsersManager INSTANCE = new OnlineUsersManager();

	private OnlineUsersManager() {}

	public static OnlineUsersManager getInstance() {
		return INSTANCE;
	}

	private final Map<HttpSession, User> cache = new Hashtable<HttpSession, User>();

	/**
	 * 登记HttpSession与User
	 * 
	 * @param httpSession
	 * @param user
	 */
	public void register(HttpSession httpSession, User user) {
		if (user == null) {
			user = SystemGlobals.getSettings().getAnonymousUser();
		}
		cache.put(httpSession, user);
	}

	/**
	 * 移除Session记录
	 * 
	 * @param httpSession
	 */
	public void remove(HttpSession httpSession) {
		cache.remove(httpSession);
	}

	/**
	 * 当前登录的用户(只包含登录用户)
	 * 
	 * @return
	 */
	public Set<User> getOnlineUsers() {
		// 使用Set是为了去掉重复的用户(有的是打开两个浏览器并且都登录)
		Set<User> users = new HashSet<User>();
		for (User user : cache.values()) {
			// 匿名用户不算是会员
			if (user != null && !UserHelper.isAnonymousUser(user)) {
				users.add(user);
			}
		}
		return users;
	}

	/**
	 * 在线(用户)的数量(包含注册用户与匿名用户)
	 * 
	 * @return
	 */
	public int getOnlineThreadsCount() {
		// 一个session对应一个用户, 所以使用keys的size
		return cache.keySet().size();
	}

}
