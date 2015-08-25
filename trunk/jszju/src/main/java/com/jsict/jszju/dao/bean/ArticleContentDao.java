/**
 * ArticleContentDao.java        2009-11-8 下午02:03:18
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.dao.bean;

import com.jsict.base.dao.BaseDao;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.util.Op;
import com.jsict.jszju.dao.IArticleContentDao;
import com.jsict.jszju.entity.Article;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class ArticleContentDao extends BaseDao<Article> implements IArticleContentDao {

    @SuppressWarnings("unchecked")
	public PagedList<Article> getArticleContentPagedListByChannelId(
			EntityFilter tf, Integer pageNo, Integer pageSize, String channelId) {
		EntityFilter ef = new EntityFilter();
		ef.addFilter("article.channelId", Op.EQUAL, channelId);
        String hql = "from Article article";
        return executeQuery(hql, ef,pageNo,pageSize);
    }

}
