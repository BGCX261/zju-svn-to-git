/**
 * IArticleContentRepository.java        2009-11-8 下午01:57:56
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.repository;

import com.jsict.base.IBaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.jszju.entity.Article;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface IArticleContentRepository extends IBaseRepository {
	
	public PagedList<Article> getArticleContentPagedListByChannelId(EntityFilter tf,
            Integer pageNo, Integer pageSize,String channelId);
	
	public PagedList<Article> getArticleContentPagedList(EntityFilter tf,
            Integer pageNo, Integer pageSize);
	
	public Article getArticleContent(Long id);
	
	public Article saveArticleContent(Article article);

}
