/**
 * SubsystemRepository.java 2008-12-27 下午11:23:42 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.repository.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.BaseRepository;
import com.jsict.platform.dao.ISubsystemDao;
import com.jsict.platform.entity.Subsystem;
import com.jsict.platform.repository.ISubsystemRepository;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class SubsystemRepository extends BaseRepository implements
        ISubsystemRepository
{
    ISubsystemDao subsystemDao;

    @Required
    public void setSubsystemDao(ISubsystemDao subsystemDao)
    {
        this.subsystemDao = subsystemDao;
    }

    public List<Subsystem> getAllSubsystems()
    {
        return subsystemDao.getAllSubsystems();
    }

    public Subsystem getSubsystem(Serializable subsystemCode)
    {
        return subsystemDao.get(String.valueOf(subsystemCode));
    }

}
