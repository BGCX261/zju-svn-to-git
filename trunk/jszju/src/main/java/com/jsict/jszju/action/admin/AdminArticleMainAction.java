/**
 * AdminArticleMainAction.java        2009-11-1 下午04:00:45
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
public class AdminArticleMainAction extends BaseAction {
	
	public static final String MAINRIGHT = "right";

	public static final String MAINLEFT = "left";
	
	public static final String COREFUNCTIONLEFT = "corefunctionleft";
	
	public static final String COREFUNCTIONRIGHT = "corefunctionright";
	
	public static final String RESOURCERIGHT = "resourceright";
	
	public static final String RESOURCELEFT = "resourceleft";



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

		if (MAINRIGHT.equals(actionType)) {
			return mapping.findForward(MAINRIGHT);
		} else if (MAINLEFT.equals(actionType)) {
			return mapping.findForward(MAINLEFT);
		}
		else if (RESOURCERIGHT.equals(actionType)) {
			return mapping.findForward(RESOURCERIGHT);
		}
		else if (RESOURCELEFT.equals(actionType)) {
			return mapping.findForward(RESOURCELEFT);
		}
		else if (COREFUNCTIONRIGHT.equals(actionType)) {
			return mapping.findForward(COREFUNCTIONRIGHT);
		}
		else if (COREFUNCTIONLEFT.equals(actionType)) {
			return mapping.findForward(COREFUNCTIONLEFT);
		}
		else {
			return mapping.getInputForward();
		}
	}

}
