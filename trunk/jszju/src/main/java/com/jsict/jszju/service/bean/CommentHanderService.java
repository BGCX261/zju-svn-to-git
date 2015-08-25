/**
 * CommentHanderService.java        2009-11-24 下午11:29:49
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
import com.jsict.jszju.domain.CommentDomain;
import com.jsict.jszju.entity.Comment;
import com.jsict.jszju.repository.ICommentHanderRepository;
import com.jsict.jszju.service.ICommentHanderService;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
@Transactional
public class CommentHanderService extends BaseService implements
		ICommentHanderService {

	private ICommentHanderRepository commentHanderRepository;

	@Required
	public void setCommentHanderRepository(
			ICommentHanderRepository commentHanderRepository) {
		this.commentHanderRepository = commentHanderRepository;
	}

	public void saveCommentContent(CommentDomain domain)
			throws ApplicationException, SystemException {
		Comment entity = new Comment();
		ConvertUtil.domain2entity(domain, entity);
		this.commentHanderRepository.saveCommentContent(entity);
	}

	public PagedList<CommentDomain> getCommentPageList(EntityFilter ef,
			Integer pageNo, Integer pageSize) throws ApplicationException,
			SystemException {
		PagedList<CommentDomain> listDomain = new PagedList<CommentDomain>();
		PagedList<Comment> listEntity = this.commentHanderRepository
				.getCommentPageList(ef, pageNo, pageSize);
		for (Comment entity : listEntity) {
			CommentDomain sfDomain = new CommentDomain();
			ConvertUtil.entity2domain(entity, sfDomain);
			listDomain.add(sfDomain);
		}
		ListUtil.clonePagedInfo(listDomain, listEntity);
		return listDomain;
	}

}
