
package com.jsict.jszju.dao;

import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.IBaseDao;
import com.jsict.base.dao.PagedList;
import com.jsict.jszju.entity.Fileinfo;


public interface IFileDao extends IBaseDao<Fileinfo> {
	
	public PagedList<Fileinfo> getFilePagedListByFileName(EntityFilter tf, Integer pageNo,
			Integer pageSize,String filename);

}
