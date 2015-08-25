/**
 * ArticleContentRepository.java        2009-11-8 下午02:00:20
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.repository.bean;

import org.springframework.beans.factory.annotation.Required;

import com.jsict.base.BaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.jszju.dao.IArticleContentDao;
import com.jsict.jszju.entity.Article;
import com.jsict.jszju.repository.IArticleContentRepository;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class ArticleContentRepository extends BaseRepository implements
		IArticleContentRepository {


	private IArticleContentDao articleContentDao;
	
	@Required
	public void setArticleContentDao(IArticleContentDao articleContentDao) {
		this.articleContentDao = articleContentDao;
	}

	/**
	 * <p>Description: The getArticleList</p>
	 * @param tf
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @author: caie
	 * @update: [updatedate] [changer][change description]
	 */

	public PagedList<Article> getArticleContentPagedListByChannelId(EntityFilter tf, Integer pageNo,
			Integer pageSize,String channelId) {
		return articleContentDao.getArticleContentPagedListByChannelId(tf, pageNo, pageSize,channelId);
	}
	
	public PagedList<Article> getArticleContentPagedList(EntityFilter tf, Integer pageNo,
			Integer pageSize) {
		return articleContentDao.getPagedList(tf, pageNo, pageSize);
	}
	
	public Article getArticleContent(Long id) {
		
		Integer id1=Integer.parseInt(String.valueOf(id));
		
		
		return articleContentDao.get(id1);
	}
	


	public Article saveArticleContent(Article article) {
		
		return articleContentDao.save(article);
	}



}
