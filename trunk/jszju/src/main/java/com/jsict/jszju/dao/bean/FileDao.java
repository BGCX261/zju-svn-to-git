
package com.jsict.jszju.dao.bean;

import com.jsict.base.dao.BaseDao;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.util.Op;
import com.jsict.jszju.dao.IFileDao;
import com.jsict.jszju.entity.Fileinfo;


public class FileDao extends BaseDao<Fileinfo> implements IFileDao {

	 @SuppressWarnings("unchecked")
	public PagedList<Fileinfo> getFilePagedListByFileName(EntityFilter tf,
			Integer pageNo, Integer pageSize, String filename) {
		EntityFilter ef = new EntityFilter();
		ef.addFilter("fileinfo.filename", Op.EQUAL, filename);
        String hql = "from Fileinfo fileinfo";
        return executeQuery(hql, ef,pageNo,pageSize);
	}

}
