/**
 * 
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
import com.jsict.jszju.form.baseform.IndexForm;

/**
 * @author Administrator
 * 
 */
public class IndexAction extends BaseAction {
	public static final String INITIAL = "initial";

	public static final String HEAD = "head";

	public static final String SIDE = "side";

	public static final String MAIN = "main";

	public static final String FOOT = "foot";

	protected ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ApplicationException, SystemException {
		IndexForm formbean = (IndexForm) form;
		String actionType = request.getParameter("actionType");

		if (HEAD.equals(actionType)) {
			return mapping.findForward(HEAD);
		} else if (MAIN.equals(actionType)) {
			return mapping.findForward(MAIN);
		} else if (FOOT.equals(actionType)) {
			return mapping.findForward(FOOT);
		} else {
			return mapping.getInputForward();
		}
	}

}
