/**
 * 
 */
package com.jsict.jszju.dao;

import java.util.List;

import com.jsict.base.dao.IBaseDao;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.jszju.entity.ItcastTopic;

/**
 * @author Administrator
 *
 */
public interface IBbsTopicDao extends IBaseDao<ItcastTopic> {
	
	public List<ItcastTopic> getItcastTopic(String forumid) throws ApplicationException, SystemException;

}
