package com.jsict.base.context;

public class StrutsContext extends ThreadLocalContext
{
    public final static String ACTION_TYPE = "actionType";

    public static String getActionType()
    {
        return String.valueOf(getAttribute(ACTION_TYPE));
    }

    public static void setActionType(String actionType)
    {
        setAttribute(ACTION_TYPE, actionType);
    }
}
