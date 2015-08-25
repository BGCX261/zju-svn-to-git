/**
 * IArticleContentService.java        2009-11-8 下午01:44:01
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.service;


import com.jsict.base.IBaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.domain.ArticleContentDomain;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface IArticleContentService extends IBaseService {
	/**
	 * 
	 * <p>Description: The getArticleContentPagedList</p>
	 * @param filter
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws ApplicationException
	 * @throws SystemException
	 * @author: caie
	 * @update: [updatedate] [changer][change description]
	 */
	public PagedList<ArticleContentDomain> getArticleContentPagedListByChannelId(
        EntityFilter filter, Integer pageNo, Integer pageSize,String channelId)
    throws ApplicationException, SystemException;
	
	public PagedList<ArticleContentDomain> getArticleContentPagedList(
	        EntityFilter filter, Integer pageNo, Integer pageSize)
	    throws ApplicationException, SystemException;
	
	public ArticleContentDomain getArticleContent(String id) throws ApplicationException, SystemException;
	
    public void addArticleContent(
    		ArticleContentDomain domain) throws SystemException,
        ApplicationException;
    public void disableArticleContent(String id) throws SystemException,
    ApplicationException;
    
    public void updateArticleContent(
    		ArticleContentDomain domain) throws SystemException,
        ApplicationException;

}
