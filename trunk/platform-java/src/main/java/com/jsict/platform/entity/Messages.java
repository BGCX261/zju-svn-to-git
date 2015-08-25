package com.jsict.platform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Messages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MESSAGES")
public class Messages implements java.io.Serializable
{

    // Fields    

    private Long id;

    private UserAccount userAccountByToid;

    private UserAccount userAccountByFromid;

    private String title;

    private String content;

    private String status;

    private Date sendtime;

    private Date readtime;

    private String readnotice;

    private String replyFlag;

    private String fromDeleteFlag;

    private String toDeleteFlag;

    // Constructors

    /** default constructor */
    public Messages()
    {
    }

    /** minimal constructor */
    public Messages(Long id, UserAccount userAccountByToid,
            UserAccount userAccountByFromid)
    {
        this.id = id;
        this.userAccountByToid = userAccountByToid;
        this.userAccountByFromid = userAccountByFromid;
    }

    /** full constructor */
    public Messages(Long id, UserAccount userAccountByToid,
            UserAccount userAccountByFromid, String content, String status,
            Date sendtime, Date readtime, String readnotice, String replyFlag)
    {
        this.id = id;
        this.userAccountByToid = userAccountByToid;
        this.userAccountByFromid = userAccountByFromid;
        this.content = content;
        this.status = status;
        this.sendtime = sendtime;
        this.readtime = readtime;
        this.readnotice = readnotice;
        this.replyFlag = replyFlag;
    }

    // Property accessors
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TOID", nullable = false)
    public UserAccount getUserAccountByToid()
    {
        return this.userAccountByToid;
    }

    public void setUserAccountByToid(UserAccount userAccountByToid)
    {
        this.userAccountByToid = userAccountByToid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FROMID", nullable = false)
    public UserAccount getUserAccountByFromid()
    {
        return this.userAccountByFromid;
    }

    public void setUserAccountByFromid(UserAccount userAccountByFromid)
    {
        this.userAccountByFromid = userAccountByFromid;
    }

    @Column(name = "TITLE", length = 200)
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Column(name = "MSGCONTENT", length = 4000)
    public String getContent()
    {
        return this.content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Column(name = "STATUS", length = 6)
    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SENDTIME", length = 7)
    public Date getSendtime()
    {
        return this.sendtime;
    }

    public void setSendtime(Date sendtime)
    {
        this.sendtime = sendtime;
    }

    @Column(name = "READTIME", length = 7)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getReadtime()
    {
        return this.readtime;
    }

    public void setReadtime(Date readtime)
    {
        this.readtime = readtime;
    }

    @Column(name = "READNOTICE", precision = 22, scale = 0)
    public String getReadnotice()
    {
        return this.readnotice;
    }

    public void setReadnotice(String readnotice)
    {
        this.readnotice = readnotice;
    }

    @Column(name = "REPLYFLAG", precision = 22, scale = 0)
    public String getReplyFlag()
    {
        return replyFlag;
    }

    public void setReplyFlag(String replyFlag)
    {
        this.replyFlag = replyFlag;
    }

    @Column(name = "FROMDELETEFLAG", precision = 22, scale = 0)
    public String getFromDeleteFlag()
    {
        return fromDeleteFlag;
    }

    public void setFromDeleteFlag(String fromDeleteFlag)
    {
        this.fromDeleteFlag = fromDeleteFlag;
    }

    @Column(name = "TODELETEFLAG", precision = 22, scale = 0)
    public String getToDeleteFlag()
    {
        return toDeleteFlag;
    }

    public void setToDeleteFlag(String toDeleteFlag)
    {
        this.toDeleteFlag = toDeleteFlag;
    }

}