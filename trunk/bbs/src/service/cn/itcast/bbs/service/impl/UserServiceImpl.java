package cn.itcast.bbs.service.impl;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bbs.cfg.SystemGlobals;
import cn.itcast.bbs.entities.PageInfo;
import cn.itcast.bbs.entities.QueryResult;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.privilege.Group;
import cn.itcast.bbs.exception.runtime.ItcastException;
import cn.itcast.bbs.logic.helper.UserHelper;
import cn.itcast.bbs.service.UserService;
import cn.itcast.bbs.service.base.BaseService;

/**
 * @author 传智播客.汤阳光 Date 2008-4-7
 */
@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseService implements UserService {

	public User getUser(int id) {
		return userDao.get(id);
	}

	public boolean isLoginNameRegistered(String loginName) {
		// 去掉用户名两端的空格并转为小写
		loginName = loginName.trim().toLowerCase();
		return userDao.findByLoginName(loginName) != null;
	}

	public boolean isEmailRegistered(String email) {
		return userDao.findByEmail(email) != null;
	}

	public User findByLoginNameAndPassword(String loginName, String password) {
		// 转为小写
		loginName = loginName.toLowerCase();
		// 使用明文密码的MD5摘要
		password = DigestUtils.md5Hex(password);
		return userDao.findByLoginNameAndPassword(loginName, password);
	}

	@Transactional
	public void addNew(User user) throws ItcastException {
		if (user.getPassword() == null) {
			throw new ItcastException("参数不正确, 用户的密码为null");
		}

		// 去掉用户名两端的空格并转为小写
		String loginName = user.getLoginName().trim().toLowerCase();

		// 用户名不能相同
		if (userDao.findByLoginName(loginName) != null) {
			throw new ItcastException("已有用户名为【" + loginName + "】的用户");
		}

		// 设置基本信息
		user.setLoginName(loginName); // 保存为小写的用户名
		user.setPassword(DigestUtils.md5Hex(user.getPassword())); // 使用md5摘要作为密码
		user.setActive(true); // 新注册即可使用
		user.setDeleted(false);
		user.setRegistrationTime(new Date());
		String idmarriage=new String();
		String idsex=new String();
		String postn=new String();
		String postn1=user.getPostcode();
		String bday=new String();
		bday=user.getBirthday().toString().replace("-", "").substring(2,8);
		if(user.getSex().getValue().equals("MALE"))
		{
			idsex="1";
		}
		else if(user.getSex().getValue().equals("FEMALE"))
		{
			idsex="2";
		}
		else
		{
			idsex="3";
		}
		if(user.getMarriage().equals("未婚"))
		{
			idmarriage="1";
		}
		else
		{
			idmarriage="2";
		}
		if(postn1.length()!=6)
		{
			postn1="210008";
		}
		postn=postn1.substring(2, 6);
		
		String membernumber=new String();
		membernumber=idsex+idmarriage+postn+bday;
		user.setMemberid(membernumber);

		// 未分组的用户有一个默认的组
		if (user.getGroups().size() == 0) {
			Group defaultUserGroup = SystemGlobals.getSettings().getDefaultUserGroup();
			user.getGroups().add(defaultUserGroup);
		}

		// 保存用户
		userDao.save(user);
	}

	@Transactional
	public void updateUserInfo(User user) {
		User pristine = userDao.get(user.getId());
		userDao.flushAndClearSession();
		
		String loginName = pristine.getLoginName();
		String passwordDigest = pristine.getPassword();

		try {
			BeanUtils.copyProperties(pristine, user);
		} catch (Exception e) {
			throw new ItcastException(e);
		}

		pristine.setLoginName(loginName); // 不更新loginName
		pristine.setPassword(passwordDigest); // 不更新密码
		userDao.merge(pristine);
	}

	@Transactional
	public void updateUserPassword(User user) {
		if (user.getPassword() == null) {
			throw new ItcastException("密码为null，参数不正确.");
		}

		// 只更新密码
		User pristine = userDao.get(user.getId());
		String passwordDigest = DigestUtils.md5Hex(user.getPassword());
		pristine.setPassword(passwordDigest);
	}

	@Transactional
	public void changeGroups(int userId, int[] newGroupIds) {
		User user = userDao.get(userId);
		user.getGroups().clear(); // 先清除以前所属的组

		if (newGroupIds == null) {
			return;
		}

		for (int groupId : newGroupIds) {
			Group group = groupDao.get(groupId);
			user.getGroups().add(group);
		}
	}

	public PageInfo<User> findAllUsers(int pageNum) {
		int pageSize = SystemGlobals.getSettings().getUsersPerPage();
		int firstResult = PageInfo.calculateFirstResult(pageNum, pageSize);

		QueryResult<User> qr = userDao.findAll(firstResult, pageSize);
		return PageInfo.populate(qr, pageNum, pageSize);
	}

	public PageInfo<User> findUsersByGroup(int groupId, int pageNum) {
		Group group = groupDao.get(groupId);
		int pageSize = SystemGlobals.getSettings().getUsersPerPage();
		int firstResult = PageInfo.calculateFirstResult(pageNum, pageSize);

		QueryResult<User> qr = userDao.findByGroup(group, firstResult, pageSize);
		return PageInfo.populate(qr, pageNum, pageSize);
	}

	public PageInfo<User> findUsersByLoginNamePart(String loginNamePart, int pageNum) {
		int pageSize = SystemGlobals.getSettings().getUsersPerPage();
		int firstResult = PageInfo.calculateFirstResult(pageNum, pageSize);

		QueryResult<User> qr = userDao.findByLoginNamePart(loginNamePart, firstResult, pageSize);
		return PageInfo.populate(qr, pageNum, pageSize);
	}

	@Transactional
	public void lockUser(int id) {
		User user = userDao.get(id);

		// 不能锁定超级管理员和匿名用户
		if (!UserHelper.isSuperman(user) && !UserHelper.isAnonymousUser(user)) {
			user.setActive(false);
		}
	}

	@Transactional
	public void unlockUser(int id) {
		User user = userDao.get(id);
		user.setActive(true);
		user.setActivationKey(null);
		userDao.update(user);
	}
	

}
