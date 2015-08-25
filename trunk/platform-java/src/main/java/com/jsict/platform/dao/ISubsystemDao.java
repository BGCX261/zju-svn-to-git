/**
 * ISubsystemDao.java 2008-12-27 下午11:21:12 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao;

import java.util.List;

import com.jsict.base.dao.IBaseDao;
import com.jsict.platform.entity.Subsystem;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface ISubsystemDao extends IBaseDao<Subsystem>
{

    List<Subsystem> getAllSubsystems();

}
