package com.jsict.platform.action;

import java.util.Enumeration; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.action.BaseAction;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Consts;
import com.jsict.platform.form.MenuForm;
import com.jsict.platform.service.IAuthorizationService;
import com.jsict.platform.util.MenuExtractor;

public class MenuAction extends BaseAction
{
    public static final String CLICK = "click";

    private IAuthorizationService authorizationService;

    private String platformCode;

    protected ActionForward process(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws ApplicationException, SystemException
    {
        MenuForm formbean = (MenuForm) form;
        String actionType = formbean.getActionType();
        HttpSession session = request.getSession();

        if(CLICK.equals(actionType))
        {
            //移除session中的form
           
            Enumeration<String> attributeNames = session.getAttributeNames();
            while (attributeNames.hasMoreElements())
            {
                String attributeName = attributeNames.nextElement();
                if(attributeName.endsWith("Form"))
                {
                    session.removeAttribute(attributeName);
                }
            }

            return new ActionForward(formbean.getUrl(), false);
        }
        else
        {
            formbean.setMenuDomainList(MenuExtractor.extract(
                authorizationService
                        .getRootPermissionsForCurrentUser(platformCode), null));
            session.setAttribute("menuForm", formbean);
            if(Consts.ACTION_TYPE_DYNAMIC.equalsIgnoreCase(actionType))
	        {
			   return  mapping.findForward(Consts.ACTION_TYPE_DYNAMIC);
	        }
            else if(Consts.ACTION_TYPE_FILE.equalsIgnoreCase(actionType))
	        {
			   return  mapping.findForward(Consts.ACTION_TYPE_FILE);
	        }
            else if(Consts.ACTION_TYPE_ZJUCOMMU.equalsIgnoreCase(actionType))
	        {
			   return  mapping.findForward(Consts.ACTION_TYPE_ZJUCOMMU);
	        }
            else if(Consts.ACTION_TYPE_PIC.equalsIgnoreCase(actionType))
	        {
			   return  mapping.findForward(Consts.ACTION_TYPE_PIC);
	        }
            else if(Consts.ACTION_TYPE_SUBZJU.equalsIgnoreCase(actionType))
	        {
			   return  mapping.findForward(Consts.ACTION_TYPE_SUBZJU);
	        }
            else if(Consts.ACTION_TYPE_MEMBERHOME.equalsIgnoreCase(actionType))
	        {
			   return  mapping.findForward(Consts.ACTION_TYPE_MEMBERHOME);
	        }
            else if(Consts.ACTION_TYPE_FIRSTPAGE.equalsIgnoreCase(actionType))
	        {
			   return  mapping.findForward(Consts.ACTION_TYPE_FIRSTPAGE);
	        }
            
            
            else if(Consts.ACTION_TYPE_PICTURE.equalsIgnoreCase(actionType))
		   	{
            	return mapping.findForward(Consts.ACTION_TYPE_PICTURE);
		   	}
		   else if(Consts.ACTION_TYPE_VIDEO.equalsIgnoreCase(actionType))
		   	{
			   return mapping.findForward(Consts.ACTION_TYPE_VIDEO);
		   	}
		   else if(Consts.ACTION_TYPE_OLDZJUER.equalsIgnoreCase(actionType))
		   	{
			   return  mapping.findForward(Consts.ACTION_TYPE_OLDZJUER);
		   	}
		   else if(Consts.ACTION_TYPE_YOUNGZJUER.equalsIgnoreCase(actionType))
		   	{
			   return  mapping.findForward(Consts.ACTION_TYPE_YOUNGZJUER);
		   	}
		   else if(Consts.ACTION_TYPE_MEDICAL.equalsIgnoreCase(actionType))
		   	{
			   return mapping.findForward(Consts.ACTION_TYPE_MEDICAL);
		   	}
            return mapping.getInputForward();
        }
    }

    @Required
    public void setAuthorizationService(
            IAuthorizationService authorizationService)
    {
        this.authorizationService = authorizationService;
    }

    @Required
    public void setPlatformCode(String platformCode)
    {
        this.platformCode = platformCode;
    }

}
