/**
 * CommentHanderRepository.java        2009-11-24 下午11:36:33
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.repository.bean;

import com.jsict.base.BaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.dao.ICommentHanderDao;
import com.jsict.jszju.entity.Comment;
import com.jsict.jszju.repository.ICommentHanderRepository;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class CommentHanderRepository extends BaseRepository implements
		ICommentHanderRepository {

	private ICommentHanderDao commentHanderDao;

	public void setCommentHanderDao(ICommentHanderDao commentHanderDao) {
		this.commentHanderDao = commentHanderDao;
	}

	public void saveCommentContent(Comment entity) throws ApplicationException,
			SystemException {
		commentHanderDao.save(entity);
	}

	public PagedList<Comment> getCommentPageList(EntityFilter ef,
			Integer pageNo, Integer pageSize) throws ApplicationException,
			SystemException {
		return commentHanderDao.getPagedList(ef, pageNo, pageSize);
	}

}
