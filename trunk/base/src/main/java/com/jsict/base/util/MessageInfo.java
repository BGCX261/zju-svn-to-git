package com.jsict.base.util;

public class MessageInfo
{

    private String id = null;

    private String[] params = null;

    private String message = null;

    public MessageInfo(String id)
    {
        this.id = id;
        this.params = new String[0];
    }

    public MessageInfo(String id, String[] params)
    {
        this.id = id;
        this.params = params;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String[] getParams()
    {
        return params;
    }

    public void setParams(String[] params)
    {
        this.params = params;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {

        String message = MessageReader.getProperty(id);
        for (int i = 0; i < this.params.length; i++)
        {
            String param = params[i];
            message = message.replaceAll("%" + i, param);
        }
        return message;
    }
}
