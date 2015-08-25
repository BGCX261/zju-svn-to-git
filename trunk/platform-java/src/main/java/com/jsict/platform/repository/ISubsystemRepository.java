/**
 * IRoleRepository.java 2008-11-10 下午03:06:57 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.repository;

import java.io.Serializable;
import java.util.List;

import com.jsict.base.IBaseRepository;
import com.jsict.platform.entity.Subsystem;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * .
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface ISubsystemRepository extends IBaseRepository
{

    List<Subsystem> getAllSubsystems();

    Subsystem getSubsystem(Serializable subsystemCode);

}
