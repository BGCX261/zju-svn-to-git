package com.jsict.platform.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jsict.platform.domain.MenuDomain;
import com.jsict.platform.domain.PermissionDomain;

public class MenuExtractor
{
    public static List<MenuDomain> extract(
            Collection<PermissionDomain> permissionList, MenuDomain parentMenu)
    {
        List<MenuDomain> menuList = new ArrayList<MenuDomain>();
        List<MenuDomain> subList;
        if(permissionList != null && permissionList.size() > 0)
        {
            MenuDomain menuDomain;
            for (PermissionDomain permissionDomain : permissionList)
            {
                menuDomain = new MenuDomain();
                menuDomain.setId(Integer.parseInt(permissionDomain.getId()));
                menuDomain.setPage(permissionDomain.getUrl());
                menuDomain.setTitle(permissionDomain.getName());
                menuDomain.setDescription(permissionDomain.getDescription());

                menuList.add(menuDomain);

                subList = extract(permissionDomain.getChildPermissions(),
                    menuDomain);

                menuDomain.setMenuList(subList);
            }
        }

        if(parentMenu != null)
        {
            parentMenu.setMenuList(menuList);
        }
        return menuList;
    }
}
