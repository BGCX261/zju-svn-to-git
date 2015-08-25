/**
 * 
 */
package com.jsict.jszju.domain;

import java.util.List;

/**
 * @author Administrator
 *
 */
public class MenuDomain {
    private Integer id = null;

    private String page = null;

    private String title = null;

    private String description = null;

    private List<MenuDomain> menuList = null;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getPage()
    {
        return page;
    }

    public void setPage(String page)
    {
        this.page = page;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<MenuDomain> getMenuList()
    {
        return menuList;
    }

    public void setMenuList(List<MenuDomain> menuList)
    {
        this.menuList = menuList;
    }
}
