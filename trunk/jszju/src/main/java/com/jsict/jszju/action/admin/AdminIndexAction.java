/**
 * AdminIndexAction.java        2009-10-30 下午04:58:57
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
public class AdminIndexAction extends BaseAction {
	
	public static final String AINITIAL = "ini";

	public static final String ARTICLECOLUMN = "articleColumn";

	public static final String ARTICLECONTENT = "articleContent";
	
	public static final String RESOURCEMANAGE = "resourcemanage";
	
	public static final String COREFUNCTION = "corefunction";


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

		if (AINITIAL.equals(actionType)) {
			return mapping.findForward(AINITIAL);
		} else if (ARTICLECOLUMN.equals(actionType)) {
			return mapping.findForward(ARTICLECOLUMN);
		} else if (ARTICLECONTENT.equals(actionType)) {
			return mapping.findForward(ARTICLECONTENT);
		}else if (RESOURCEMANAGE.equals(actionType)) {
			return mapping.findForward(RESOURCEMANAGE);
		}  
		else if (COREFUNCTION.equals(actionType)) {
			return mapping.findForward(COREFUNCTION);
		} 
		else {
			return mapping.getInputForward();
		}
	}

}
