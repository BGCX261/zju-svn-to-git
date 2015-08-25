package cn.itcast.bbs.dao.log.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.dao.log.ExceptionLogDao;
import cn.itcast.bbs.entities.log.ExceptionLog;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
@Repository("exceptionLogDao")
public class ExceptionLogDaoImpl extends GenericDaoImpl<ExceptionLog> implements ExceptionLogDao {

}
