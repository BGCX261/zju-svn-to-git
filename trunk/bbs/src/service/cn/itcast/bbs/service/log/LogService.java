package cn.itcast.bbs.service.log;

import cn.itcast.bbs.entities.log.ExceptionLog;
import cn.itcast.bbs.entities.log.OperationLog;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public interface LogService {

	/**
	 * 获取操作日志
	 * 
	 * @param id
	 * @return
	 */
	OperationLog getOperationLog(int id);

	/**
	 * 获取异常日志
	 * 
	 * @param id
	 * @return
	 */
	ExceptionLog getExceptionLog(int id);

	/**
	 * 新增操作日志
	 * 
	 * @param olog
	 */
	void saveOperationLog(OperationLog olog);

	/**
	 * 新增异常日志
	 * 
	 * @param elog
	 */
	void saveExceptionLog(ExceptionLog elog);

}
