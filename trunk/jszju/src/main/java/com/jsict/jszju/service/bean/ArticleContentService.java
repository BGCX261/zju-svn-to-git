/**
 * ArticleContentService.java        2009-11-8 下午01:52:25
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.service.bean;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.jsict.base.BaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.ConvertUtil;
import com.jsict.base.util.ListUtil;
import com.jsict.base.util.StringUtil;
import com.jsict.jszju.constants.CodeKey;
import com.jsict.jszju.domain.ArticleContentDomain;
import com.jsict.jszju.entity.Article;
import com.jsict.jszju.repository.IArticleContentRepository;
import com.jsict.jszju.service.IArticleContentService;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
@Transactional
public class ArticleContentService extends BaseService implements
		IArticleContentService {

	private IArticleContentRepository articleContentRepository;

	@Required
	public void setArticleContentRepository(
			IArticleContentRepository articleContentRepository) {
		this.articleContentRepository = articleContentRepository;
	}

	/**
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
			throws ApplicationException, SystemException {
		PagedList<ArticleContentDomain> listDomain = new PagedList<ArticleContentDomain>();
		PagedList<Article> listEntity = this.articleContentRepository
				.getArticleContentPagedListByChannelId(filter, pageNo, pageSize,channelId);
		for (Article entity : listEntity) {
			ArticleContentDomain articleContentDomain = new ArticleContentDomain();
			ConvertUtil.entity2domain(entity, articleContentDomain);
			listDomain.add(articleContentDomain);
		}
		ListUtil.clonePagedInfo(listDomain, listEntity);
		return listDomain;
	}
	
	public PagedList<ArticleContentDomain> getArticleContentPagedList(
			EntityFilter filter, Integer pageNo, Integer pageSize)
			throws ApplicationException, SystemException {
		PagedList<ArticleContentDomain> listDomain = new PagedList<ArticleContentDomain>();
		PagedList<Article> listEntity = this.articleContentRepository
				.getArticleContentPagedList(filter, pageNo, pageSize);
		for (Article entity : listEntity) {
			ArticleContentDomain articleContentDomain = new ArticleContentDomain();
			ConvertUtil.entity2domain(entity, articleContentDomain);
			listDomain.add(articleContentDomain);
		}
		ListUtil.clonePagedInfo(listDomain, listEntity);
		return listDomain;
	}

	public ArticleContentDomain getArticleContent(String id)
			throws ApplicationException, SystemException {
		ArticleContentDomain domain = new ArticleContentDomain();
		Article	entity=new Article();
		if (StringUtil.isLong(id)) {
			 entity = articleContentRepository.getArticleContent(Long
					.parseLong(id));
			ConvertUtil.entity2domain(entity, domain);
		}
		return domain;
	}

	public void addArticleContent(ArticleContentDomain domain)
			throws SystemException, ApplicationException {
		Article entity = new Article();
        if(ConvertUtil.domain2entity(domain, entity))
        {
        }
        entity = this.articleContentRepository.saveArticleContent(entity);
    }
	
	public void disableArticleContent(String id) throws SystemException,
	ApplicationException {
        Article entity = this.articleContentRepository
                .getArticleContent(Long.parseLong(id));
        entity.setStatus(CodeKey.UNAVAILABLE); // 设置状态为废弃
        entity.setId(Integer.parseInt(String.valueOf(entity.getId())));
        articleContentRepository.saveArticleContent(entity);
    }
	
	public void updateArticleContent(ArticleContentDomain domain)
			throws SystemException, ApplicationException {
        if(!StringUtil.isLong(domain.getId()))
        {
        }
        Article entity = this.articleContentRepository
                .getArticleContent(Long.parseLong(domain.getId()));
        if(ConvertUtil.domain2entity(domain, entity))
        {
        }
        articleContentRepository.saveArticleContent(entity);
    }

}
