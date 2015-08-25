/**
 * IMessagesDao.java 2008-11-27 下午04:03:31 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao;

import java.io.Serializable;

import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.IBaseDao;
import com.jsict.base.dao.PagedList;
import com.jsict.platform.entity.Messages;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public interface IMessagesDao extends IBaseDao<Messages>
{

    public PagedList<Messages> getIncomingMessagesByUserId(Serializable userId,
            EntityFilter entityFilter, Integer pageNo, Integer pageSize);

    public PagedList<Messages> getOutgoingMessagesByUserId(Serializable userId,
            EntityFilter entityFilter, Integer pageNo, Integer pageSize);

    int getMessagesAmount(Serializable userId, String messageStatus);

    public Messages getUnreadMessagesMaxId(Serializable userId);

}
