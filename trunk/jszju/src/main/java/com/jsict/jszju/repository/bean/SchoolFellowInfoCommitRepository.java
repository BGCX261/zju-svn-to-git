/**
 * SchoolFellowInfoCommitRepository.java        2009-11-27 下午10:53:59
 * caie
 * 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.jszju.repository.bean;

import java.util.List;

import com.jsict.base.BaseRepository;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.dao.IBbsTopicDao;
import com.jsict.jszju.dao.ISchoolFellowInfoCommitDao;
import com.jsict.jszju.entity.ItcastTopic;
import com.jsict.jszju.entity.Schoolfellowhelp;
import com.jsict.jszju.repository.ISchoolFellowInfoCommitRepository;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: caie@jsict.com">作者中文名</a>
 * @version 1.0
 */
public class SchoolFellowInfoCommitRepository extends BaseRepository implements
		ISchoolFellowInfoCommitRepository {

	private ISchoolFellowInfoCommitDao schoolFellowInfoCommitDao;
	
	private IBbsTopicDao bbsTopicDao;
	
	public void setBbsTopicDao(IBbsTopicDao bbsTopicDao) {
		this.bbsTopicDao = bbsTopicDao;
	}

	public void setSchoolFellowInfoCommitDao(
			ISchoolFellowInfoCommitDao schoolFellowInfoCommitDao) {
		this.schoolFellowInfoCommitDao = schoolFellowInfoCommitDao;
	}

	/**
	 * <p>Description: The getSchoolFelloHelp</p>
	 * @param id
	 * @return
	 * @author: caie
	 * @update: [updatedate] [changer][change description]
	 */

	public Schoolfellowhelp getSchoolFelloHelp(Long id) {

		Integer id1 = Integer.parseInt(String.valueOf(id));
		return schoolFellowInfoCommitDao.get(id1);
	}

	/**
	 * <p>Description: The getSchoolFelloHelpPagedList</p>
	 * @param tf
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @author: caie
	 * @update: [updatedate] [changer][change description]
	 */

	public PagedList<Schoolfellowhelp> getSchoolFelloHelpPagedList(
			EntityFilter tf, Integer pageNo, Integer pageSize) {
		return schoolFellowInfoCommitDao.getPagedList(tf, pageNo, pageSize);
	}

	/**
	 * <p>Description: The saveSchoolFelloHelp</p>
	 * @param schoolfellowhelp
	 * @return
	 * @author: caie
	 * @update: [updatedate] [changer][change description]
	 */

	public Schoolfellowhelp saveSchoolFelloHelp(
			Schoolfellowhelp schoolfellowhelp) {

		return schoolFellowInfoCommitDao.save(schoolfellowhelp);
	}

	public List<ItcastTopic> getItcastTopic(String forumid)
			throws ApplicationException, SystemException {
		
		return bbsTopicDao.getItcastTopic(forumid);
	}

}
