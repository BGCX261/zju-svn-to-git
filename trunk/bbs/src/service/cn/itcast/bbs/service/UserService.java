package cn.itcast.bbs.service;

import cn.itcast.bbs.entities.PageInfo;
import cn.itcast.bbs.entities.User;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public interface UserService {

	/**
	 * @param id
	 * @return 指定id的用户
	 */
	User getUser(int id);

	/**
	 * 用户名不区分大小写, 用户名ABC和abc是相同的
	 * 
	 * @param name
	 * @return 指定的用户名是否已被注册
	 */
	boolean isLoginNameRegistered(String loginName);

	/**
	 * @param email
	 * @return TODO 指定的email地址是否已被注册
	 */
	boolean isEmailRegistered(String email);

	/**
	 * 使用用户名和密码查询一个唯一的用户<br>
	 * 用户名不区分大小写, 用户名ABC和abc是相同的
	 * 
	 * @param loginName
	 * @param password
	 *            明文密码
	 * @return
	 */
	User findByLoginNameAndPassword(String loginName, String password);

	/**
	 * FIXME 添加新用户 (如果未指定组, 则属于默认用户组)<br>
	 * 
	 * @param user
	 * @return 是否添加了用户
	 */
	void addNew(User user);

	/**
	 * 更新用户信息 (不会修改loginName, 不会修改密码)
	 * 
	 * @param user
	 */
	void updateUserInfo(User user);

	/**
	 * 更新密码 (只更新密码, 不会修改其它信息)
	 * 
	 * @param user
	 */
	public void updateUserPassword(User user);

	/**
	 * 更改用户所属的组
	 * 
	 * @param userId
	 * @param newGroupIds
	 *            新组的id
	 */
	public void changeGroups(int userId, int[] newGroupIds);

	/**
	 * @param pageNum
	 * @return 指定的一段用户数据
	 */
	PageInfo<User> findAllUsers(int pageNum);

	/**
	 * @param groupId
	 * @param pageNum
	 * @return 属于指定组的用户
	 */
	PageInfo<User> findUsersByGroup(int groupId, int pageNum);

	/**
	 * @param loginNamePart
	 * @param pageNum
	 * @return 用户名中包含指定字符的用户
	 */
	PageInfo<User> findUsersByLoginNamePart(String loginNamePart, int pageNum);

	/**
	 * 锁定指定用户<br>
	 * 不会锁定超级管理员用户, 也不会锁定anonymous用户
	 * 
	 * @param id
	 */
	void lockUser(int id);

	/**
	 * 解锁指定用户
	 * 
	 * @param id
	 */
	void unlockUser(int id);

}
