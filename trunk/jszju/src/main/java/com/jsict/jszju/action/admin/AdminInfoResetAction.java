/**
 * AdminInfoResetAction.java        2009-11-14 下午01:33:44
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.action.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
import com.jsict.jszju.action.admin.ArticleContentListAction.ArticleCotentListComparator;
import com.jsict.jszju.domain.AdminInfoDomain;
import com.jsict.jszju.domain.AdminInfoResetDomain;
import com.jsict.jszju.domain.ArticleContentDomain;
import com.jsict.jszju.entity.Admininfo;
import com.jsict.jszju.form.AdminInfoResetListForm;
import com.jsict.jszju.service.IAdminInfoCheckService;
import com.jsict.jszju.service.IAdminResetInfoService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class AdminInfoResetAction extends BaseListAction {

	public static final String LIST = "list";

	private IAdminInfoCheckService adminInfoCheckService;

	public void setAdminInfoCheckService(
			IAdminInfoCheckService adminInfoCheckService) {
		this.adminInfoCheckService = adminInfoCheckService;
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

		AdminInfoResetListForm adminInfoResetForm = (AdminInfoResetListForm) form;
		String actionType = adminInfoResetForm.getActionType();
		AdminInfoResetDomain adminInfoResetDomain = new AdminInfoResetDomain();
		ActionForward forward = new ActionForward();

		if (Consts.ACTION_TYPE_RESETPASW.equalsIgnoreCase(actionType)) {

			if ((adminInfoCheckService.getAdmininfoList().get(0).getName()
					.equalsIgnoreCase(adminInfoResetDomain.getName()))
					&& adminInfoCheckService.getAdmininfoList().get(0)
							.getPassword().equalsIgnoreCase(
									adminInfoResetDomain.getPassword())) {
				AdminInfoResetDomain domain = new AdminInfoResetDomain();
				domain = adminInfoResetForm.getAdminInfoResetDomain();
				AdminInfoDomain adminInfoDomain = new AdminInfoDomain();
				adminInfoDomain = adminInfoCheckService.getAdmininfoList().get(
						0);
				adminInfoDomain.setPassword(domain.getNewpassword1());
				adminInfoDomain.setName(domain.getName());
				adminInfoCheckService.saveAdmininfo(adminInfoDomain);
				request.setAttribute("adminInfoDomain", adminInfoDomain);
				forward = mapping.getInputForward();
				return forward;
			}

			else {
				forward = mapping.findForward("oldpswerror");
				return forward;
			}
		} else if (LIST.equals(actionType)) {
			Cookie[] cookies = request.getCookies();
			String adminrole = null;
			for (Cookie c : cookies) {
				if (c.getName().equals("role") && c.getValue().equals("superadmin")) {
					PagedList<AdminInfoDomain> listDomain = new PagedList<AdminInfoDomain>();
					listDomain = adminInfoCheckService.getAdmininfoPagedList(
							getEntityFilter(), getPageNo(), getPageSize());
					adminInfoResetForm.setResultList(listDomain);
					setPageInfo(request, (PagedList) listDomain); //设置分页信息
					return mapping.findForward(LIST);
				} 
			}
			return mapping.findForward("nopopedom");
		}

		else
			return mapping.getInputForward();

	}

}
