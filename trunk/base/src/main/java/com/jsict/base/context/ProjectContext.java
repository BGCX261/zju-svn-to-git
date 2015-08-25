package com.jsict.base.context;

import java.util.ArrayList;
import java.util.List;

import com.jsict.base.util.MessageInfo;

/**
 * 
 * @author qipf
 * 
 */
public class ProjectContext extends ThreadLocalContext
{

    public static final String CONTEXT_KEY_ERRORLIST = "errorList";

    public static final String CONTEXT_KEY_USERID = "userId";

    public static final String CONTEXT_SUBSYSTEMCODE = "subsystemCode";

    @SuppressWarnings("unchecked")
    public static List<MessageInfo> getErrorList()
    {
        List<MessageInfo> errorList = (List<MessageInfo>) getAttribute(CONTEXT_KEY_ERRORLIST);
        if(errorList == null)
        {
            errorList = new ArrayList<MessageInfo>();
            ProjectContext.setErrorList(errorList);
        }

        return errorList;
    }

    public static void setErrorList(List<MessageInfo> errorList)
    {
        setAttribute(CONTEXT_KEY_ERRORLIST, errorList);
    }

    public static String getUserId()
    {
        return (String) getAttribute(CONTEXT_KEY_USERID);
    }

    public static void setUserId(String userId)
    {
        setAttribute(CONTEXT_KEY_USERID, userId);
    }

    public static void setSubsystemCode(String subsystemCode)
    {
        setAttribute(CONTEXT_SUBSYSTEMCODE, subsystemCode);
    }

    public static String getSubsystemCode()
    {
        return (String) getAttribute(CONTEXT_SUBSYSTEMCODE);
    }

    public static void freeContext()
    {
        removeAttribute(CONTEXT_KEY_ERRORLIST);
    }

}
