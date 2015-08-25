package com.jsict.base.exception;

import java.util.List;

import com.jsict.base.util.MessageInfo;
import org.apache.struts.action.ActionForward;

/**
 * 
 * @author qipf
 * 
 */
public class ApplicationException extends Exception
{

    private static final long serialVersionUID = 1L;

    private String forward;

    private MessageInfo messageInfo = null;
    
    private List<MessageInfo> errorList = null;

    public ApplicationException()
    {
        super();
    }

    public ApplicationException(String id)
    {
        super();
        this.messageInfo = new MessageInfo(id);
    }

    public ApplicationException(String id, String[] params)
    {
        super();
        this.messageInfo = new MessageInfo(id, params);
    }

    public ApplicationException(String id, String[] params, String forward)
    {
        super();
        this.messageInfo = new MessageInfo(id, params);
        this.forward = forward;
    }

    public ApplicationException(List<MessageInfo> errorList)
    {
        super();
        this.errorList = errorList;
    }

    public MessageInfo getMessageInfo()
    {
        return messageInfo;
    }

    public List<MessageInfo> getErrorList()
    {
        return errorList;
    }

    public void setErrorList(List<MessageInfo> errorList)
    {
        this.errorList = errorList;
    }

    public String getForward()
    {
        return forward;
    }
}
