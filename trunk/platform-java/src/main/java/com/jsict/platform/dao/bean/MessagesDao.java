/**
 * MessagesDao.java 2008-11-27 下午04:04:16 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.dao.bean;

import java.io.Serializable;
import java.util.List;

import com.jsict.base.dao.BaseDao;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.platform.constants.CodeKey;
import com.jsict.platform.dao.IMessagesDao;
import com.jsict.platform.entity.Messages;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class MessagesDao extends BaseDao<Messages> implements IMessagesDao
{

    private final static String SQL_MESSAGES_AMOUNT = "select count(*) from Messages where userAccountByToid.id=? and status=? and toDeleteFlag=?";

    private final static String SQL_MESSAGES_MAXID = "from Messages where id in(select max(id) from Messages where userAccountByToid.id=? and status=?)";

    public PagedList<Messages> getIncomingMessagesByUserId(Serializable userId,
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
    {

        Long id = Long.parseLong(String.valueOf(userId));
        entityFilter.addFilter("userAccountByToid.id", id);
        entityFilter.addFilter("toDeleteFlag",
            CodeKey.MESSAGES_DELETE_INDICATOR_NOTDELETED);
        entityFilter.addOrder("sendtime", "desc");
        return super.getPagedList(entityFilter, pageNo, pageSize);
    }

    public PagedList<Messages> getOutgoingMessagesByUserId(Serializable userId,
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
    {

        Long id = Long.parseLong(String.valueOf(userId));
        entityFilter.addFilter("userAccountByFromid.id", id);
        entityFilter.addFilter("fromDeleteFlag",
            CodeKey.MESSAGES_DELETE_INDICATOR_NOTDELETED);
        entityFilter.addOrder("sendtime", "desc");
        return super.getPagedList(entityFilter, pageNo, pageSize);
    }

    public int getMessagesAmount(Serializable userId, String messageStatus)
    {
        return Integer.parseInt(singleResult(SQL_MESSAGES_AMOUNT,
            Long.parseLong(String.valueOf(userId)), messageStatus,
            CodeKey.MESSAGES_DELETE_INDICATOR_NOTDELETED).toString());
    }

    public Messages getUnreadMessagesMaxId(Serializable userId)
    {
        // TODO Auto-generated method stub
        Long id = Long.parseLong(String.valueOf(userId));
        List<Messages> list = executeQuery(SQL_MESSAGES_MAXID, id,
            CodeKey.PLF_MESSAGES_TYPE_UNREAD);
        if(list.size() > 0)
            return list.get(0);

        return null;
    }
}
