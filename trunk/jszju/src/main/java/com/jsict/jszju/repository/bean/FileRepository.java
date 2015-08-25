package com.jsict.jszju.repository.bean;

import com.jsict.base.BaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.dao.IFileDao;
import com.jsict.jszju.entity.Fileinfo;
import com.jsict.jszju.repository.IFileRepository;

public class FileRepository extends BaseRepository implements IFileRepository {

	private IFileDao fileDao;

	public void setFileDao(IFileDao fileDao) {
		this.fileDao = fileDao;
	}

	public void saveFile(Fileinfo entity) throws ApplicationException,
			SystemException {
		fileDao.save(entity);
	}

	public PagedList<Fileinfo> getFilePagedList(EntityFilter tf,
			Integer pageNo, Integer pageSize) {

		return fileDao.getPagedList(tf, pageNo, pageSize);
	}

	public Fileinfo getFilePic(Integer id) throws ApplicationException,
			SystemException {
		return this.fileDao.get(id);
	}
	
	public PagedList<Fileinfo> getFilePagedListByFileName(EntityFilter tf,
			Integer pageNo, Integer pageSize, String filename) {
		PagedList<Fileinfo> listEntity = this.fileDao.getFilePagedListByFileName(tf,
				pageNo,pageSize,filename);
		return listEntity;
	}

}
