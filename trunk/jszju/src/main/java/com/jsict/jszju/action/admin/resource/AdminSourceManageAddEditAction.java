/**
 * AdminSourceManageAddEditAction.java        2009-11-25 下午11:12:40
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.action.admin.resource;

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
import com.jsict.jszju.domain.ArticleContentDomain;
import com.jsict.jszju.domain.PaychargeDomain;
import com.jsict.jszju.form.ArticleContentForm;
import com.jsict.jszju.form.PaychargeListFrom;
import com.jsict.jszju.service.IAdminSourceManageService;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class AdminSourceManageAddEditAction extends BaseAction {
	
	private IAdminSourceManageService adminSourceManageService;
	@Required
	public void setAdminSourceManageService(
			IAdminSourceManageService adminSourceManageService) {
		this.adminSourceManageService = adminSourceManageService;
	}

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

		PaychargeListFrom paychargeListFrom = (PaychargeListFrom) form;
	        String actionType = paychargeListFrom.getActionType();

	        ActionForward forward = new ActionForward();
	        if(Consts.ACTION_TYPE_NEW.equalsIgnoreCase(actionType))
	        {
	            //进入新增数据页面
	        	PaychargeDomain domain = new PaychargeDomain();
	        	paychargeListFrom.setPaychargeDomain(domain);
	        	paychargeListFrom.setActionType(Consts.ACTION_TYPE_INSERT);
	            forward = mapping.getInputForward();
	        }
	        else if(Consts.ACTION_TYPE_EDIT.equalsIgnoreCase(actionType))
	        {
	        	 //根据界面传入ID取得对应的domain,进入数据编辑页面
	        	PaychargeDomain domain = adminSourceManageService.getPaycharge(
	                    request.getParameter("id"));
	        	paychargeListFrom.setPaychargeDomain(domain);
	        	paychargeListFrom.setActionType(Consts.ACTION_TYPE_UPDATE);
	            forward = mapping.getInputForward();
	        }
	        else if(Consts.ACTION_TYPE_INSERT.equalsIgnoreCase(actionType))
	        {
	            //点击保存按钮后,如果ACTION_TYPE是insert, 则插入数据
	        	PaychargeDomain domain = paychargeListFrom.getPaychargeDomain();
	        	adminSourceManageService.addPaycharge(domain);
	            forward = mapping.findForward(Consts.ACTION_TYPE_BACKTOLIST);
	        }
	        else if(Consts.ACTION_TYPE_UPDATE.equalsIgnoreCase(actionType))
	        {
	          //点击保存按钮后,如果ACTION_TYPE是update, 则修改数据
	        	PaychargeDomain domain = paychargeListFrom.getPaychargeDomain();
	        	adminSourceManageService.updatePaycharge(domain);
	            forward = mapping.findForward(Consts.ACTION_TYPE_BACKTOLIST);
	        }
	        else if(Consts.ACTION_TYPE_CANCEL.equalsIgnoreCase(actionType))
	        {
	            forward = mapping.findForward(Consts.ACTION_TYPE_BACKTOLIST);
	        }
	        return forward;
	    }

}
