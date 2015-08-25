/**
 * 
 */
package com.jsict.jszju.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jsict.base.action.BaseListAction;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.jszju.domain.AdminInfoDomain;
import com.jsict.jszju.form.AdminInfoForm;
import com.jsict.jszju.service.IAdminInfoCheckService;

/**
 * @author Administrator
 *
 */
public class AdminInfoListAction extends BaseListAction {
	
	private IAdminInfoCheckService adminInfoCheckService;
	
	public void setAdminInfoCheckService(
			IAdminInfoCheckService adminInfoCheckService) {
		this.adminInfoCheckService = adminInfoCheckService;
	}

	/* (non-Javadoc)
	 * @see com.jsict.base.action.BaseListAction#doListProcess(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward doListProcess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ApplicationException, SystemException {  
		
		AdminInfoForm  adminform=(AdminInfoForm)form;
		String actionType=adminform.getActionType();
		ActionForward  forward=new ActionForward();
		if(Consts.ACTION_TYPE_NEW.equalsIgnoreCase(actionType))
	        {
	            //进入新增数据页面
	        	
			AdminInfoDomain domain = new AdminInfoDomain();
			adminform.setAdminInfoDomain(domain);
			adminform.setActionType(Consts.ACTION_TYPE_INSERT);
			forward = mapping.findForward("edit");
	        }
	        else if(Consts.ACTION_TYPE_EDIT.equalsIgnoreCase(actionType))
	        {
	        	 //根据界面传入ID取得对应的domain,进入数据编辑页面
	        	AdminInfoDomain domain = adminInfoCheckService.getAdminInfo(
	                    request.getParameter("id"));
	        	adminform.setAdminInfoDomain(domain);
	        	
	        	adminform.setActionType(Consts.ACTION_TYPE_UPDATE);
	        	forward = mapping.findForward("edit");
	        }
	        else if(Consts.ACTION_TYPE_INSERT.equalsIgnoreCase(actionType))
	        {
	            //点击保存按钮后,如果ACTION_TYPE是insert, 则插入数据
	        	AdminInfoDomain domain = adminform.getAdminInfoDomain();
	        	adminInfoCheckService.insertAdmininfo(domain);
	            forward = mapping.findForward("list");
	        }
	        else if(Consts.ACTION_TYPE_UPDATE.equalsIgnoreCase(actionType))
	        {
	          //点击保存按钮后,如果ACTION_TYPE是update, 则修改数据
	        	AdminInfoDomain domain = adminform.getAdminInfoDomain();
	        	adminInfoCheckService.updateAdminInfo(domain);
	            forward = mapping.findForward("list");
	        }
	        else if(Consts.ACTION_TYPE_CANCEL.equalsIgnoreCase(actionType))
	        {
	            forward = mapping.findForward(Consts.ACTION_TYPE_CANCEL);
	        }
	        return forward;
	        	
			}
	
	

}
