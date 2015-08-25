package cn.itcast.bbs;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.privilege.Group;
import cn.itcast.bbs.entities.privilege.Permission;
import cn.itcast.bbs.entities.privilege.Role;
import cn.itcast.bbs.web.helper.WebCommonHelper;

/**
 * 有两个功能:<br>
 * 1, 记录一次请求的执行信息, 主要为request与response.<br>
 * 2, 管理ExecutionContext.
 * 
 * @author 传智播客.汤阳光 May 21, 2009
 * 
 */
public class ExecutionContext {

	private static ThreadLocal<ExecutionContext> userData = new ThreadLocal<ExecutionContext>();

	public static ExecutionContext get() {
		return userData.get();
	}

	public static void set(ExecutionContext executionContext) {
		userData.set(executionContext);
	}

	// -----------------

	private HttpServletRequest request;
	private HttpServletResponse response;

	public ExecutionContext(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * 当前用户的ip地址
	 * 
	 * @return
	 */
	public String getIpAddr() {
		return request.getRemoteAddr();
	}

	/**
	 * 当前的登录用户
	 * 
	 * @return
	 */
	public User getCurrentUser() {
		return WebCommonHelper.getLoggedOnUser(request);
	}

	/**
	 * 当前登录用户的所有的权限
	 */
	public Set<Permission> getCurrentUserPermissions() {
		// FIXME 应放到Session中
		Set<Permission> permissions = new HashSet<Permission>();
		for (Group group : getCurrentUser().getGroups()) {
			for (Role role : group.getRoles()) {
				permissions.addAll(role.getPermissions());
			}
		}
		return permissions;
	}

}
