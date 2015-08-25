/**
 * IAssignableRoleDao.java 2009-1-7 下午09:45:37 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao;

import java.io.Serializable;
import java.util.List;

import com.jsict.base.dao.IBaseDao;
import com.jsict.platform.entity.AssignableRole;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface IAssignableRoleDao extends IBaseDao<AssignableRole>
{

    List<AssignableRole> getAssignableRole(Serializable subsystemId,
            Serializable roleId);

}
