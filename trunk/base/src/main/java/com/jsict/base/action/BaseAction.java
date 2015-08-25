package com.jsict.base.action;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.jsict.base.context.ProjectContext;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.security.User;
import com.jsict.base.util.MessageInfo;
import com.jsict.base.util.WebSettings;

/**
 * 
 * @author qipf
 * 
 */
public abstract class BaseAction extends Action
{

    public final ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
        ActionForward moveTo = null;
        System.out.println("----BaseAction开始----");
        System.out.println("----moveTo----"+moveTo);

        try
        {
        	System.out.println("----mapping----"+mapping);
            moveTo = process(mapping, form, request, response);
            
            System.out.println("----moveTo----"+moveTo);
            HttpSession session = request.getSession();
            Enumeration<String> attributeNames = session.getAttributeNames();
            System.out.println("----session 分割线----");
            while (attributeNames.hasMoreElements())
            {
                String attributeName = attributeNames.nextElement();
                System.out.println("attributeName----" + attributeName);
            }
            System.out.println("----BaseAction结束----");
            return moveTo;
            
            
        }
        catch (ApplicationException e)
        {
            List<MessageInfo> errorList = ProjectContext.getErrorList();
            MessageInfo messageInfo = e.getMessageInfo();
            if(messageInfo != null)
            {
                errorList.add(messageInfo);
            }
            request.setAttribute("errorList", errorList);
            String forward = e.getForward();
            if(forward == null)
            {
                moveTo = mapping.getInputForward();
            }
            else
            {
                moveTo = mapping.findForward(forward);
            }

            return moveTo;

        }
        catch (SystemException e)
        {
            return moveTo;
        }
        finally
        {
            ProjectContext.freeContext();
        }
    }

    protected abstract ActionForward process(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws ApplicationException,
        SystemException;

    protected Serializable getUserId()
    {
        User user = getUser();

        return user == null ? null : user.getUserId();
    }

    protected User getUser()
    {
        SecurityContext context = SecurityContextHolder.getContext();
        return (User) context.getAuthentication().getPrincipal();

    }
}
