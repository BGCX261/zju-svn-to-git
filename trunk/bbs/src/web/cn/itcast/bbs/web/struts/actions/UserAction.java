package cn.itcast.bbs.web.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.web.WebConstants;
import cn.itcast.bbs.web.helper.WebCommonHelper;
import cn.itcast.bbs.web.helper.WebUserHelper;
import cn.itcast.bbs.web.struts.action.base.BaseAction;
import cn.itcast.bbs.web.struts.forms.UserForm;
import cn.itcast.bbs.web.utils.CookieUtils;

/**
 * @author 传智播客.汤阳光 Jul 7, 2008
 */
@Controller("/user")
public class UserAction extends BaseAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String methodName = request.getParameter(getParameter(mapping, form, request, response));
		if (methodName != null) {
			// 在使用注册和登录的功能时, 如果已经登陆,应转到首页
			if (methodName.startsWith("register") || methodName.startsWith("login")) {
				if (WebCommonHelper.isLoggedOn(request)) {
					return mapping.findForward("index");
				}
			}
		}

		return super.execute(mapping, form, request, response);
	}

	/**
	 * 注册表单页面
	 */
	public ActionForward registerUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("register");
	}

	// /**
	// * TODO 验证码
	// */
	// public ActionForward proofCode(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// response.setContentType("image/jpeg");
	// ConfirmationCodeGenerator codeGenerator = new ConfirmationCodeGenerator();
	// codeGenerator.write(response.getOutputStream());
	// String code = codeGenerator.getCode();
	// request.getSession().setAttribute("proofCode", code);
	// return null;
	// }

	/**
	 * 检测loginName是否已被使用(ajax)
	 */
	public ActionForward isLoginNameRegistered(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm) form;
		Boolean result = userService.isLoginNameRegistered(userForm.getLoginName());
		response.getWriter().write(result.toString());
		return null;
	}

	/**
	 * 注册
	 */
	public ActionForward register(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (validateFailed(form, "validateRegister", mapping, request)) { // 验证表单
			return mapping.findForward("register");
		}

		UserForm userForm = (UserForm) form;

		// FIXME 检查验证码是否正确

		// 用户是否存在
		if (userService.isLoginNameRegistered(userForm.getLoginName())) {
			addError(request, "loginName", "用户名【" + userForm.getLoginName() + "】已存在, 请更换一个", false);
			return mapping.findForward("register");
		}

		User user = new User();
		BeanUtils.copyProperties(user, userForm);
		// 头像
		byte[] avatar = WebUserHelper.handleAvatar(userForm.getAvatarFile());
		user.setAvatar(avatar);

		userService.addNew(user);// 添加用户
		WebUserHelper.login(request, user); // 登陆此用户

		// 返回到注册前访问的页面
		String returnPath = WebCommonHelper.decodeReturnPath(request);
		if (returnPath != null) {
			response.sendRedirect(returnPath);
			return null;
		}

		return mapping.findForward("index");
	}

	/**
	 * 登陆表单
	 */
	public ActionForward loginUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WebCommonHelper.preReturnPath(request);
		return mapping.findForward("login");
	}

	/**
	 * 登陆
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm) form;
		User user = userService.findByLoginNameAndPassword(userForm.getLoginName(), userForm.getPassword());

		if (user == null) { // 登陆失败
			addError(request, "loginName", "用户名或密码不正确", false);
			return mapping.findForward("login");
		}

		// 用户被锁定
		if (!user.isActive()) {
			addError(request, "loginName", "用户已被锁定, 不能登陆, 请与管理理员联系", false);
			return mapping.findForward("login");
		}

		// 密码验证通过, 登陆
		WebUserHelper.login(request, user);

		// 如果选择了自动登录
		if (userForm.isAutoLogin()) {
			long autoLoginDays = userForm.getAutoLoginDays();
			long expiryTime = autoLoginDays * (long) 24 * 3600 * 1000 + System.currentTimeMillis();
			String autoLoginAuthKey = user.getId() + "_" + expiryTime;

			// 1, 保存到服务器端(数据库中)
			user.setAutoLoginAuthKey(autoLoginAuthKey);
			userService.updateUserInfo(user);

			// 2, 保存到客户端(发送Cookie)
			CookieUtils.addCookie(response,//
					WebConstants.COOKIE_NAME_AUTO_LOGIN,//
					autoLoginAuthKey,//
					(int) autoLoginDays * 24 * 3600,//
					"/");
		}

		// 重定向到returnPath指定的地址
		String returnPath = WebCommonHelper.decodeReturnPath(request);
		if (returnPath != null) {
			response.sendRedirect(returnPath);
			return null;
		}

		return mapping.findForward("index");
	}

	/**
	 * 注销(登出)
	 */
	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();

		// 删除自动登陆用的 cookie
		CookieUtils.addCookie(response, WebConstants.COOKIE_NAME_AUTO_LOGIN, "", 0, "/");

		return mapping.findForward("index");
	}

	/**
	 * 用户信息修改页面
	 */
	public ActionForward editUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm) form;
		User user = userService.getUser(userForm.getId());

		// 第一次到修改页面时才需要准备数据,出错后转发过来的不应显示上次的错误输入
		if (userForm.getNickname() == null) {
			BeanUtils.copyProperties(userForm, user);
		}
		request.setAttribute("user", user);

		WebCommonHelper.preReturnPath(request);
		return mapping.findForward("edit");
	}

	/**
	 * 修改用户信息,不修改密码
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (validateFailed(form, "validateEdit", mapping, request)) { // 验证表单
			return editUI(mapping, form, request, response);
		}

		UserForm userForm = (UserForm) form;
		User user = userService.getUser(userForm.getId());
		BeanUtils.copyProperties(user, userForm);

		if (getBoolParam(request, "deleteCurrentAvatar")) {
			user.setAvatar(null);
		}

		byte[] avatar = WebUserHelper.handleAvatar(userForm.getAvatarFile());
		if (avatar != null) {
			user.setAvatar(avatar);
		}

		// 更新用户
		userService.updateUserInfo(user);

		// 同步存在Session中的用户信息
		request.getSession().setAttribute(WebConstants.SESSION_KEY_CURRENTLY_LOGGED_ON_USER, user);

		// 重定向到returnPath指定的地址
		String returnPath = WebCommonHelper.decodeReturnPath(request);
		if (returnPath != null) {
			response.sendRedirect(returnPath);
			return null;
		}

		return mapping.findForward("index");
	}

	/**
	 * 修改密码
	 */
	public ActionForward changePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		throw new UnsupportedOperationException();
		// return mapping.findForward("index");
	}

	/**
	 * 查看用户头像
	 */
	public ActionForward showAvatar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm) form;
		User user = userService.getUser(userForm.getId());

		response.setContentType("image/jpeg");
		if (user.getAvatar() != null) {
			response.getOutputStream().write(user.getAvatar());
		}
		return null;
	}

	/**
	 * 用户信息查看
	 */
	public ActionForward profile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm) form;
		User user = userService.getUser(userForm.getId());

		// 用户是否在线
		// user.setOnline(HttpSessionManager.isUserOnline(user.getId()));

		request.setAttribute("user", user);
		return mapping.findForward("profile");
	}
}
