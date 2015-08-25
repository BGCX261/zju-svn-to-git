package com.jsict.base.util;

import java.util.ResourceBundle;

public class MessageReader
{

    private static ResourceBundle rb = null;

    public static void init()
    {

        rb = ResourceBundle.getBundle("messages");
    }

    public static String getProperty(String id)
    {
        return rb.getString(id);
    }
}
