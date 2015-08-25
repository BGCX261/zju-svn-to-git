/**
 * 
 */
package com.jsict.jszju.form.baseform;

import java.io.Serializable;

import java.util.List;

import com.jsict.base.form.BaseForm;
import com.jsict.jszju.domain.MenuDomain;

/**
 * @author Administrator
 *
 */
public class MenuForm extends BaseForm {
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
