package cn.itcast.bbs.service.log.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bbs.entities.log.ExceptionLog;
import cn.itcast.bbs.entities.log.OperationLog;
import cn.itcast.bbs.service.base.BaseService;
import cn.itcast.bbs.service.log.LogService;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
@Service("logService")
@Transactional(readOnly = true)
public class LogServiceImpl extends BaseService implements LogService {

	public ExceptionLog getExceptionLog(int id) {
		return exceptionLogDao.get(id);
	}

	public OperationLog getOperationLog(int id) {
		return operationLogDao.get(id);
	}

	@Transactional
	public void saveExceptionLog(ExceptionLog elog) {
		exceptionLogDao.save(elog);
	}

	@Transactional
	public void saveOperationLog(OperationLog olog) {
		operationLogDao.save(olog);
	}

}
