/**
 * 
 */
package com.jsict.jszju.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jsict.base.action.BaseAction;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.form.baseform.MenuForm;

/**
 * @author Administrator
 *
 */
public class MenuAction extends BaseAction {
    public static final String CLICK = "click";

    protected ActionForward process(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
        throws ApplicationException, SystemException
    {
    	System.out.println("----MenuAction开始----");
        MenuForm formbean = (MenuForm) form;
        String actionType = formbean.getActionType();
        System.out.println("----URL 名字----"+formbean.getUrl());
        if(CLICK.equals(actionType))
        {
        	 
            //移除session中的form
            HttpSession session = request.getSession();
            Enumeration<String> attributeNames = session.getAttributeNames();
            System.out.println("----session 名字----");
            while (attributeNames.hasMoreElements())
            {
                String attributeName = attributeNames.nextElement();
                System.out.println("attributeName----" + attributeName);
                if(attributeName.endsWith("Form"))
                {
                    session.removeAttribute(attributeName);
                   
                }
            }
            System.out.println("----MenuAction结束----");
            return new ActionForward(formbean.getUrl(), false);
        }
        else
        {
            return mapping.getInputForward();
        }
    }

}
