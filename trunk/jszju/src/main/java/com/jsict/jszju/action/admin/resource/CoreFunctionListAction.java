/**
 * CoreFunctionListAction.java        2009-11-30 下午06:23:37
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.action.admin.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jsict.base.action.BaseListAction;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class CoreFunctionListAction extends BaseListAction {

	public static final String MEMBERMANAGE = "memberManage";

	public static final String ADMINROLEMANAGE = "adminRoleManage";

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
		
		String actionType = request.getParameter("actionType");
		
		if (MEMBERMANAGE.equals(actionType)) {
			return mapping.findForward(MEMBERMANAGE);
		}
		else if (ADMINROLEMANAGE.equals(actionType)) {
			return mapping.findForward(ADMINROLEMANAGE);
		}
		return mapping.getInputForward();

	}
}
