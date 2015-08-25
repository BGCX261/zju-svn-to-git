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
import com.jsict.jszju.entity.Fileinfo;

public interface IFileService extends IBaseService {

	public void saveFile(Fileinfo entity) throws ApplicationException,
			SystemException;

	public PagedList<Fileinfo> getFilePagedList(EntityFilter filter,
			Integer pageNo, Integer pageSize) throws ApplicationException,
			SystemException;

	public Fileinfo getFilePic(Integer id)
			throws ApplicationException, SystemException;
	
	public PagedList<Fileinfo> getFilePagedListByFileName(EntityFilter tf, Integer pageNo,
			Integer pageSize,String filename);

}
