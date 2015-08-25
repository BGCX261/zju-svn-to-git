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
import com.jsict.jszju.entity.Fileinfo;
import com.jsict.jszju.repository.IFileRepository;
import com.jsict.jszju.service.IFileService;

@Transactional
public class FileService extends BaseService implements IFileService {

	private IFileRepository fileRepository;

	@Required
	public void setFileRepository(IFileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

	public void saveFile(Fileinfo entity) throws ApplicationException,
			SystemException {
		this.fileRepository.saveFile(entity);
	}

	public PagedList<Fileinfo> getFilePagedList(EntityFilter filter,
			Integer pageNo, Integer pageSize) throws ApplicationException,
			SystemException {
		PagedList<Fileinfo> listEntity = this.fileRepository.getFilePagedList(
				filter, pageNo, pageSize);
		return listEntity;
	}

	public Fileinfo getFilePic(Integer id) throws ApplicationException,
			SystemException {
		
		Fileinfo entity=this.fileRepository.getFilePic(id);
		return entity;
	}
	
	public PagedList<Fileinfo> getFilePagedListByFileName(EntityFilter tf,
			Integer pageNo, Integer pageSize, String filename) {

		PagedList<Fileinfo> listEntity = this.fileRepository.getFilePagedListByFileName(tf,
				pageNo,pageSize,filename);
		return listEntity;
	}


}
