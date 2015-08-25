/**
 * 
 */
package com.jsict.jszju.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jsict.base.action.BaseAction;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;

/**
 * @author Administrator
 *
 */
public class linkToMainAction extends BaseAction {
	
	/* (non-Javadoc)
	 * @see com.jsict.base.action.BaseAction#process(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ApplicationException, SystemException {
			
			ActionForward forward = new ActionForward();
		   String actionType =  request.getParameter("actionType");
		   if(Consts.ACTION_TYPE_FIRSTPAGE.equalsIgnoreCase(actionType))
	        {
			   forward = mapping.findForward(Consts.ACTION_TYPE_FIRSTPAGE);
	        }
		   else if(Consts.ACTION_TYPE_DYNAMIC.equalsIgnoreCase(actionType))
	        {
			   forward = mapping.findForward(Consts.ACTION_TYPE_DYNAMIC);
	        }
		   else if(Consts.ACTION_TYPE_FILE.equalsIgnoreCase(actionType))
	        {
			   forward = mapping.findForward(Consts.ACTION_TYPE_FILE);
	        }
		   else if(Consts.ACTION_TYPE_ZJUCOMMU.equalsIgnoreCase(actionType))
	        {
			   forward = mapping.findForward(Consts.ACTION_TYPE_ZJUCOMMU);
	        }
		   else if(Consts.ACTION_TYPE_PIC.equalsIgnoreCase(actionType))
	        {
			   forward = mapping.findForward(Consts.ACTION_TYPE_PIC);
	        }
		   else if(Consts.ACTION_TYPE_SUBZJU.equalsIgnoreCase(actionType))
	        {
			   forward = mapping.findForward(Consts.ACTION_TYPE_SUBZJU);
	        }
		   else if(Consts.ACTION_TYPE_MEMBERHOME.equalsIgnoreCase(actionType))
	        {
			   forward = mapping.findForward(Consts.ACTION_TYPE_MEMBERHOME);
	        }

		   
		   else if(Consts.ACTION_TYPE_PICTURE.equalsIgnoreCase(actionType))
		   	{
			   forward = mapping.findForward(Consts.ACTION_TYPE_PICTURE);
		   	}
		   else if(Consts.ACTION_TYPE_VIDEO.equalsIgnoreCase(actionType))
		   	{
			   forward = mapping.findForward(Consts.ACTION_TYPE_VIDEO);
		   	}
		   else if(Consts.ACTION_TYPE_OLDZJUER.equalsIgnoreCase(actionType))
		   	{
			   forward = mapping.findForward(Consts.ACTION_TYPE_OLDZJUER);
		   	}
		   else if(Consts.ACTION_TYPE_YOUNGZJUER.equalsIgnoreCase(actionType))
		   	{
			   forward = mapping.findForward(Consts.ACTION_TYPE_YOUNGZJUER);
		   	}
		   else if(Consts.ACTION_TYPE_MEDICAL.equalsIgnoreCase(actionType))
		   	{
			   forward = mapping.findForward(Consts.ACTION_TYPE_MEDICAL);
		   	}
		   
		   else if(Consts.ACTION_TYPE_VIEWINDEX.equalsIgnoreCase(actionType))
		   	{
			   forward = mapping.findForward(Consts.ACTION_TYPE_VIEWINDEX);
		   	}
		   else if(Consts.ACTION_TYPE_PUTOUTCONSULT.equalsIgnoreCase(actionType))
		   	{
			   Cookie[] cookies = request.getCookies();
				String name = null;
				String password = null;
				if (cookies != null) {
					for (Cookie c : cookies) {
						if (c.getName().equals("name")) {
							
							return forward = mapping.findForward(Consts.ACTION_TYPE_PUTOUTCONSULT);
						}
					}
					return forward = mapping.findForward("error");
				}
			   forward = mapping.findForward("nologin");
		   	}
		   else if(Consts.ACTION_TYPE_PUTOUTPROJECTINFO.equalsIgnoreCase(actionType))
		   	{
			   Cookie[] cookies = request.getCookies();
				String name = null;
				String password = null;
				if (cookies != null) {
					for (Cookie c : cookies) {
						if (c.getName().equals("name")) {
							
							return   forward = mapping.findForward(Consts.ACTION_TYPE_PUTOUTPROJECTINFO);
						}
					}
					return forward = mapping.findForward("error");
				}
			   forward = mapping.findForward("nologin");
		   	
		   	}
		   else
		   {
			   forward = mapping.getInputForward();
		   }
		 
		   return forward;
	}


}
