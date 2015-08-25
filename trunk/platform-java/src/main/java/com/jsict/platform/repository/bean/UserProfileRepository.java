/**
 * IUserProfileRepository.java 2008-11-10 下午03:06:57 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.repository.bean;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.BaseRepository;
import com.jsict.platform.dao.IUserProfileDao;
import com.jsict.platform.repository.IUserProfileRepository;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class UserProfileRepository extends BaseRepository implements
        IUserProfileRepository
{
    private IUserProfileDao userProfileDao;

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param userProfileDao
     *            The userProfileDao to set.
     */
    @Required
    public void setUserProfileDao(IUserProfileDao userProfileDao)
    {
        this.userProfileDao = userProfileDao;
    }
}
