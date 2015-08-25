/**
 * ICommentHanderRepository.java        2009-11-24 下午11:36:05
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */


package com.jsict.jszju.repository;

import com.jsict.base.IBaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.entity.Fileinfo;
import com.jsict.jszju.entity.Schoolfellowinfo;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public interface IFileRepository extends IBaseRepository {
	
	public void saveFile(Fileinfo entity) throws ApplicationException, SystemException;
	
	public PagedList<Fileinfo> getFilePagedList(EntityFilter tf,
            Integer pageNo, Integer pageSize);
	
	public Fileinfo getFilePic(Integer id)
	throws ApplicationException, SystemException;
	
	public PagedList<Fileinfo> getFilePagedListByFileName(EntityFilter tf, Integer pageNo,
			Integer pageSize,String filename);

}
