package cn.itcast.bbs.dao.log.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.dao.log.OperationLogDao;
import cn.itcast.bbs.entities.log.OperationLog;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:51:49 PM
 */
@Repository("operationLogDao")
public class OperationLogDaoImpl extends GenericDaoImpl<OperationLog> implements OperationLogDao {

}
