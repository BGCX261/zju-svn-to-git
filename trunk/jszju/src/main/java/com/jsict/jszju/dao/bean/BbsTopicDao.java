/**
 * 
 */
package com.jsict.jszju.dao.bean;

import java.util.ArrayList;
import java.util.List;

import com.jsict.base.dao.BaseDao;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.Op;
import com.jsict.jszju.dao.IBbsTopicDao;
import com.jsict.jszju.entity.ItcastTopic;
import com.jsict.jszju.entity.Userinfo;

/**
 * @author Administrator
 *
 */
public class BbsTopicDao extends BaseDao<ItcastTopic> implements IBbsTopicDao {
	
	 public final static String SQL_TF= "from ItcastTopic itcastTopic where itcastTopic.itcastForum.id = ?";

	public List<ItcastTopic> getItcastTopic(String forumid)
			throws ApplicationException, SystemException {
		
		  Integer fid=Integer.parseInt(forumid);
		  return executeQuery(SQL_TF,fid);
	}
}
