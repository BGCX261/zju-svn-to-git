/**
 * LoginOnCheckAction.java        2009-8-25 下午09:30:49
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jsict.base.action.BaseAction;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.domain.UserInfoDomain;
import com.jsict.jszju.form.UserInfoForm;
import com.jsict.jszju.service.IUserInfoService;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">蔡锷</a>
 * @version 1.0
 */
public class UserinfoSaveAction extends BaseAction {
	
	private IUserInfoService userInfoService;
	
	public void setUserInfoService(IUserInfoService UserInfoService) {
		this.userInfoService = UserInfoService;
	}


	@Override
	protected ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ApplicationException, SystemException {
		
		UserInfoForm userInfoForm=(UserInfoForm)form;
		UserInfoDomain userInfodomain=userInfoForm.getUserInfodomain();
		userInfoService.saveUserInfo(userInfodomain);
		request.setAttribute("domain", userInfodomain);
		return mapping.findForward("save");
	}


	

}
