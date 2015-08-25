package cn.itcast.bbs.dao;

import cn.itcast.bbs.dao.base.GenericDao;
import cn.itcast.bbs.entities.QueryResult;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.privilege.Group;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:49:41 PM
 */
public interface UserDao extends GenericDao<User> {

	/**
	 * @param loginName
	 * @return 完全匹配指定名字的用户
	 */
	User findByLoginName(String loginName);

	/**
	 * 
	 * @param name
	 * @param password
	 * @return 使用指定用户名和密码的用户
	 */
	User findByLoginNameAndPassword(String loginName, String password);

	/**
	 * 
	 * @param email
	 * @return 使用指定email的用户
	 */
	User findByEmail(String email);

	/**
	 * 查询登录名中包含指定字符串的用户
	 * 
	 * @param loginNamePart
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	QueryResult<User> findByLoginNamePart(String loginNamePart, int firstResult, int maxResult);

	/**
	 * 查询指定组中用户
	 * 
	 * @param group
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	QueryResult<User> findByGroup(Group group, int firstResult, int maxResult);

	/**
	 * @return 用户总数量
	 */
	// int getTotal();
}
