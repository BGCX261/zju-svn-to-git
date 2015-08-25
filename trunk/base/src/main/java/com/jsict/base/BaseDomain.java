package com.jsict.base;

import com.jsict.base.util.Text;

public class BaseDomain
{
    private String id = null;

    private String version = null;

    @Text
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Text
    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

}
