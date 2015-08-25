/**
 * MessagesDomain.java 2008-11-27 下午09:25:34 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.domain;

import com.jsict.base.util.Consts;
import com.jsict.base.util.Text;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class MessagesDomain
{
    // Fields    

    private String id;

    private UserAccountDomain userAccountByToid;

    private UserAccountDomain userAccountByFromid;

    private String title;

    private String content;

    private String status;

    private String sendtime;

    private String readtime;

    private String readnotice;

    private String replyFlag;

    private String fromDeleteFlag;

    private String toDeleteFlag;

    @Text
    public String getFromDeleteFlag()
    {
        return fromDeleteFlag;
    }

    public void setFromDeleteFlag(String fromDeleteFlag)
    {
        this.fromDeleteFlag = fromDeleteFlag;
    }

    @Text
    public String getToDeleteFlag()
    {
        return toDeleteFlag;
    }

    public void setToDeleteFlag(String toDeleteFlag)
    {
        this.toDeleteFlag = toDeleteFlag;
    }

    @Text
    public String getReplyFlag()
    {
        return replyFlag;
    }

    public void setReplyFlag(String replyFlag)
    {
        this.replyFlag = replyFlag;
    }

    @Text
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public UserAccountDomain getUserAccountByToid()
    {
        return userAccountByToid;
    }

    public void setUserAccountByToid(UserAccountDomain userAccountByToid)
    {
        this.userAccountByToid = userAccountByToid;
    }

    public UserAccountDomain getUserAccountByFromid()
    {
        return userAccountByFromid;
    }

    public void setUserAccountByFromid(UserAccountDomain userAccountByFromid)
    {
        this.userAccountByFromid = userAccountByFromid;
    }

    @Text
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Text
    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Text
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Text(format = Consts.DATE_TIME_FORMAT)
    public String getSendtime()
    {
        return sendtime;
    }

    public void setSendtime(String sendtime)
    {
        this.sendtime = sendtime;
    }

    @Text(format = Consts.DATE_TIME_FORMAT)
    public String getReadtime()
    {
        return readtime;
    }

    public void setReadtime(String readtime)
    {
        this.readtime = readtime;
    }

    @Text
    public String getReadnotice()
    {
        return readnotice;
    }

    public void setReadnotice(String readnotice)
    {
        this.readnotice = readnotice;
    }

}
