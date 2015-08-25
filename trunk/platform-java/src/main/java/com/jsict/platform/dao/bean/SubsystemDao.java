/**
 * SubsystemDao.java 2008-12-27 下午11:22:36 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao.bean;

import java.util.List;

import com.jsict.base.dao.BaseDao;
import com.jsict.platform.dao.ISubsystemDao;
import com.jsict.platform.entity.Subsystem;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class SubsystemDao extends BaseDao<Subsystem> implements ISubsystemDao
{
    private final static String QUERY_ALL_SUBSYSTEMS = "from Subsystem order by id";

    public List<Subsystem> getAllSubsystems()
    {
        return super.executeQuery(QUERY_ALL_SUBSYSTEMS);
    }

}
