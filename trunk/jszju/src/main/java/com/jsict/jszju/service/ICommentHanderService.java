/**
 * ICommentHanderService.java        2009-11-24 下午11:29:06
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.service;

import com.jsict.base.IBaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.domain.CommentDomain;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface ICommentHanderService extends IBaseService {
	
	public void saveCommentContent(CommentDomain domain) throws ApplicationException, SystemException;
	
	public PagedList<CommentDomain> getCommentPageList(EntityFilter ef,Integer pageNo, Integer pageSize)throws ApplicationException, SystemException;

}
