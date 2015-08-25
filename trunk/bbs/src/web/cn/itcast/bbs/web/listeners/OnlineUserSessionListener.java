package cn.itcast.bbs.web.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.itcast.bbs.web.OnlineUsersManager;

/**
 * @author 传智播客.汤阳光 June 6
 */
public class OnlineUserSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		OnlineUsersManager.getInstance().register(event.getSession(), null);
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		OnlineUsersManager.getInstance().remove(event.getSession());
	}

}
