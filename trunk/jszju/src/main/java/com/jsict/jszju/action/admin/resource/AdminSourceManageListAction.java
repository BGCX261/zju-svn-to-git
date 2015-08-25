/**
 * AdminSourceManageListAction.java        2009-11-25 下午11:12:18
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.action.admin.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.action.BaseListAction;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.jszju.domain.PaychargeDomain;
import com.jsict.jszju.form.PaychargeListFrom;
import com.jsict.jszju.service.IAdminSourceManageService;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class AdminSourceManageListAction extends BaseListAction {

	private IAdminSourceManageService adminSourceManageService;

	@Required
	public void setAdminSourceManageService(
			IAdminSourceManageService adminSourceManageService) {
		this.adminSourceManageService = adminSourceManageService;
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
		ActionForward forward = null;
		PaychargeListFrom listForm = (PaychargeListFrom) form;
		String actionType = request.getParameter("actionType");

		if (Consts.ACTION_TYPE_PAYCHARGE.equals(actionType)) {
			Cookie[] cookies = request.getCookies();
			String adminrole = null;
			boolean flag=false;
			for (Cookie c : cookies) {
				if (c.getName().equals("role") && (c.getValue().equals("typernotes")||(c.getValue().equals("admin"))||(c.getValue().equals("superadmin")))) {
					flag=true;
					break;
				}
			}
			if(!flag)
			{
				return mapping.findForward("nopopedom");
			}
			initPaychargeList(request, listForm);
			forward = mapping.findForward(Consts.ACTION_TYPE_PAYCHARGE);
			

		} else if (Consts.ACTION_TYPE_NEW.equalsIgnoreCase(actionType)) {
			//进入新增数据页面
			forward = mapping.findForward(actionType);
		} else if(Consts.ACTION_TYPE_SELECT.equals(actionType))
        {
			initPaychargeList(request, listForm);
            forward = mapping.findForward(Consts.ACTION_TYPE_BACKTOLIST);
        } 	
		else if(Consts.ACTION_TYPE_ENDOW.equalsIgnoreCase(actionType))
	   	{
	   		PagedList<PaychargeDomain> endowList = adminSourceManageService
			.getPaychargePagedList(getEntityFilter(), null,
					null);
	   		request.setAttribute("endowlist", endowList);
		   forward = mapping.findForward(Consts.ACTION_TYPE_ENDOW);
	   	} 
		else if(Consts.ACTION_TYPE_UPLOADPIC.equalsIgnoreCase(actionType))
	   	{
			Cookie[] cookies = request.getCookies();
			String adminrole = null;
			boolean flag=false;
			for (Cookie c : cookies) {
				if (c.getName().equals("role") && (c.getValue().equals("typernotes")||(c.getValue().equals("admin"))||(c.getValue().equals("superadmin")))) {
					flag=true;
					break;
				}
			}
			if(!flag)
			{
				return mapping.findForward("nopopedom");
			}
			
		   forward = mapping.findForward(Consts.ACTION_TYPE_UPLOADPIC);
	   	} 
		
		else {
			forward = mapping.getInputForward();
		}

		return forward;
	}

	private void initPaychargeList(HttpServletRequest request,
			PaychargeListFrom form) throws SystemException,
			ApplicationException {
		PagedList<PaychargeDomain> list = adminSourceManageService
				.getPaychargePagedList(getEntityFilter(), getPageNo(),
						getPageSize());
		form.setResultList(list);
		setPageInfo(request, (PagedList) list); //设置分页信息
	}

}
