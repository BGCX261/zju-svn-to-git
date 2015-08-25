/**
 * CheckAdminLoginAction.java        2009-10-21 下午08:34:05
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.action.admin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.action.BaseAction;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.jszju.domain.AdminInfoDomain;
import com.jsict.jszju.entity.Admininfo;
import com.jsict.jszju.form.AdminInfoForm;
import com.jsict.jszju.service.IAdminInfoCheckService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class CheckAdminLoginAction extends BaseAction {

	private IAdminInfoCheckService adminInfoCheckService;

	private static final Integer ADMINMAX_AGE = 10000;

	private static final String ADMINLOGOUT = "adminLogout";

	private static final String ADMINLOGINCHECK = "adminLoginCheck";

	@Required
	public void setAdminInfoCheckService(
			IAdminInfoCheckService adminInfoCheckService) {
		this.adminInfoCheckService = adminInfoCheckService;
	}

	/**
	 * <p>
	 * Description: The process
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
	protected ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ApplicationException, SystemException {

		AdminInfoForm adminInfoForm = (AdminInfoForm) form;
		String actionType = request.getParameter("actionType");
		AdminInfoDomain adminInfoDomain = adminInfoForm.getAdminInfoDomain();
		Admininfo entity=new Admininfo();
		entity=adminInfoCheckService.isAdminInputExist(adminInfoDomain);
		ActionForward forward = new ActionForward();
		if (ADMINLOGINCHECK.equals(actionType)) {
			if (entity!=null) {
				if (adminInfoDomain != null) {
					// 如果选择保存登陆信息
					Cookie nameCookie = new Cookie("adminname", adminInfoDomain
							.getName()); // 可以使用md5或着自己的加密算法保存
					Cookie passwordCookie = new Cookie("adminpassword",
							adminInfoDomain.getPassword());
					Cookie roleCookie = new Cookie("role",
							entity.getRole());
					nameCookie.setPath("/"); // 这个要注意
					nameCookie.setMaxAge(ADMINMAX_AGE);
					passwordCookie.setPath("/");
					passwordCookie.setMaxAge(ADMINMAX_AGE);
					roleCookie.setPath("/");
					roleCookie.setMaxAge(ADMINMAX_AGE);
					response.addCookie(nameCookie);
					response.addCookie(passwordCookie);
					response.addCookie(roleCookie);
				}

				forward = mapping.findForward("right");
			} else {
				forward = mapping.findForward("error");
			}
		} else if (ADMINLOGOUT.equals(actionType)) {

			Cookie nameCookie = new Cookie("adminname", "");
			Cookie passwordCookie = new Cookie("adminpassword", "");
			Cookie roleCookie = new Cookie("role", "");
			nameCookie.setMaxAge(0); // 使cookie失效
			passwordCookie.setMaxAge(0);
			roleCookie.setMaxAge(0);
			nameCookie.setPath("/"); // 这个不能少
			passwordCookie.setPath("/");
			roleCookie.setPath("/");
			response.addCookie(nameCookie);
			response.addCookie(passwordCookie);
			response.addCookie(roleCookie);
			return mapping.findForward("adminLogout");
		} else {
			forward = mapping.getInputForward();
		}
		return forward;

	}

}
