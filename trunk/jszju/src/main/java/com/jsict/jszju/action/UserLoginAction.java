/**
 * UserLoginAction.java        2009-11-21 下午02:46:23
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jsict.base.action.BaseListAction;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.jszju.domain.UserInfoDomain;
import com.jsict.jszju.form.UserInfoForm;
import com.jsict.jszju.service.IUserLoginCheckService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class UserLoginAction extends BaseListAction {

	private static final String USERBOX = "userbox";

	private static final String LIST = "list";

	private static final String USERLOGINCHECK = "userLoginCheck";

	private static final String REGISTER = "register";

	private static final String REGISTERCOMMIT = "registercommit";

	private static final String LOGOUT = "logout";

	private static final Integer MAX_AGE = 100000;

	private IUserLoginCheckService userLoginCheckService;

	public void setUserLoginCheckService(
			IUserLoginCheckService userLoginCheckService) {
		this.userLoginCheckService = userLoginCheckService;
	}

	/**
	 * <p>
	 * Description: The doListProcess
	 * </p>
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws ApplicationException
	 * @throws SystemException
	 * @author: caie
	 * @update: [updatedate] [changer][change description]
	 */

	@Override
	public ActionForward doListProcess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ApplicationException, SystemException {
		UserInfoForm formbean = (UserInfoForm) form;
		String actionType = request.getParameter("actionType");
		UserInfoDomain userInfoDomain = formbean.getUserInfodomain();

		if (USERLOGINCHECK.equals(actionType)) {
			String a = request.getSession().getAttribute("authCode").toString();
			String b = request.getParameter("checkCode");
			if (!a.equals(b)) {
				return mapping.findForward("codeError");
			}
			if (userLoginCheckService.checkUserInfo(formbean
					.getItcastUserDomain())) {
				request.setAttribute("domain", userInfoDomain);

				if (userInfoDomain != null) {
					// 如果选择保存登陆信息
					try
					{
					//解决cookie 不能存中文的方案
					//将要保存的值进行URLEncoder.encode(value,"utf-8")编码。
					String namevalue = URLEncoder.encode(formbean.getItcastUserDomain().getLoginName(),"UTF-8");
					Cookie nameCookie = new Cookie("name", namevalue); // 可以使用md5或着自己的加密算法保存
					Cookie passwordCookie = new Cookie("password",
							formbean.getItcastUserDomain().getPassword());
					nameCookie.setPath("/"); // 这个要注意
					nameCookie.setMaxAge(MAX_AGE);
					passwordCookie.setPath("/");
					passwordCookie.setMaxAge(MAX_AGE);
					response.addCookie(nameCookie);
					response.addCookie(passwordCookie);
					}
					catch(UnsupportedEncodingException  e)
					{
						
					}
				}

				return mapping.findForward("success");
			} else {
				return mapping.findForward("error");
			}

		} else if (REGISTER.equals(actionType)) {
			return mapping.findForward("register");
		} else if (REGISTERCOMMIT.equals(actionType)) {
			boolean isUserNameExist = userLoginCheckService
					.isUserNameExist(formbean.getUserInfodomain());
			if (isUserNameExist) {
				return mapping.findForward("registercommit");
			}
			return mapping.findForward("register");

		} else if (LOGOUT.equals(actionType)) {

			Cookie nameCookie = new Cookie("name", "");
			Cookie passwordCookie = new Cookie("password", "");
			nameCookie.setMaxAge(0); // 使cookie失效
			passwordCookie.setMaxAge(0);
			nameCookie.setPath("/"); // 这个不能少
			passwordCookie.setPath("/");
			response.addCookie(nameCookie);
			response.addCookie(passwordCookie);
			return mapping.findForward("logout");
		} else if (USERBOX.equals(actionType)) {

			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("name")) {
						return mapping.findForward("success");
					}
				}
			}
			return mapping.getInputForward();

		} else if (LIST.equals(actionType)) {
			Cookie[] cookies = request.getCookies();
			String adminrole = null;
			boolean flag=false;
			for (Cookie c : cookies) {
				if (c.getName().equals("role") && ((c.getValue().equals("admin"))||(c.getValue().equals("superadmin")))) {
					flag=true;
					break;
				}
			}
			if(!flag)
			{
				return mapping.findForward("nopopedom");
			}
			init(request, formbean);
			return mapping.findForward("list");
		} else if (Consts.ACTION_TYPE_EDIT.equalsIgnoreCase(actionType)) {
			//根据界面传入ID取得对应的domain,进入数据编辑页面
			UserInfoDomain domain = userLoginCheckService.getUserInfo(request
					.getParameter("id"));
			formbean.setUserInfodomain(domain);
			formbean.setActionType(Consts.ACTION_TYPE_UPDATE);
			return mapping.findForward("edit");
		} else if (Consts.ACTION_TYPE_UPDATE.equalsIgnoreCase(actionType)) {
			//点击保存按钮后,如果ACTION_TYPE是update, 则修改数据
			UserInfoDomain domain = formbean.getUserInfodomain();
			userLoginCheckService.updateUserInfo(domain);
			return mapping.findForward("listview");
		} else {

			return mapping.getInputForward();
		}
	}

	private void init(HttpServletRequest request, UserInfoForm form)
			throws SystemException, ApplicationException {
		PagedList<UserInfoDomain> list = userLoginCheckService
				.getUserInfoPagedList(getEntityFilter(), getPageNo(),
						getPageSize());
		form.setResultList(list);
		setPageInfo(request, (PagedList) list); // 设置分页信息
	}

}
