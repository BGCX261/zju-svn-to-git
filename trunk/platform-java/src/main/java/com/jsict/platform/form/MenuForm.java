package com.jsict.platform.form;

import java.io.Serializable;
import java.util.List;

import com.jsict.base.form.BaseForm;
import com.jsict.platform.domain.MenuDomain;

public class MenuForm extends BaseForm
{
    private Serializable id;

    private String url;

    private List<MenuDomain> menuDomainList;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public List<MenuDomain> getMenuDomainList()
    {
        return menuDomainList;
    }

    public void setMenuDomainList(List<MenuDomain> menuDomainList)
    {
        this.menuDomainList = menuDomainList;
    }

    public Serializable getId()
    {
        return id;
    }

    public void setId(Serializable id)
    {
        this.id = id;
    }

}
