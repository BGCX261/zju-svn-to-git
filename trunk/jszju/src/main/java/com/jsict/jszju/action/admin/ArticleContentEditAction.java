/**
 * ArticleContentEditAction.java        2009-11-7 下午11:03:55
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jsict.base.action.BaseAction;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class ArticleContentEditAction extends BaseAction {
	
	public static final String JSZUAANEWS = "jszuaaNews";

	public static final String ZJUNEWS = "zjuNews";
	
	public static final String ZUAANEWS = "zuaaNews";

	public static final String OTHZUAANEWS = "othzuaaNews";



	/**
	 * <p>Description: The process</p>
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
		String actionType = request.getParameter("actionType");

		if (JSZUAANEWS.equals(actionType)) {
			return mapping.findForward(JSZUAANEWS);
		} else if (ZJUNEWS.equals(actionType)) {
			return mapping.findForward(ZJUNEWS);
		} 
		else if (ZUAANEWS.equals(actionType)) {
			return mapping.findForward(ZUAANEWS);
		} 
		else if (OTHZUAANEWS.equals(actionType)) {
			return mapping.findForward(OTHZUAANEWS);
		} 
		else {
			return mapping.getInputForward();
		}
	}

}
