package cn.itcast.bbs.logic.helper;

import org.apache.commons.lang.StringUtils;

import cn.itcast.bbs.entities.User;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public class UserHelper {

	/**
	 * 用户名为anonymous的为匿名用户
	 * 
	 * @param user
	 * @return 是否是匿名用户
	 */
	public static boolean isAnonymousUser(User user) {
		return "anonymous".equals(user.getLoginName());
	}

	/**
	 * 用户名为superman的为匿名用户
	 * 
	 * @param user
	 * @return 是否为超级管理员用户
	 */
	public static boolean isSuperman(User user) {
		return "superman".equals(user.getLoginName());
	}

	/**
	 * 有效长度为大于0并且小于等于45个字符<br>
	 * 有效字符为中文，英文字母，数字，下划线
	 * 
	 * @param name
	 * @return 是否为有效的用户名
	 */
	public static boolean isValidUsername(String name) {

		// 用户名不能为空
		if(StringUtils.isBlank(name)){
			return false;
		}

		// 去掉用户名两端的空格并转为小写
		name = name.trim().toLowerCase();

		// 用户名长度不能超过45个字符
		if (name.length() > 45) {
			return false;
		}

		// 验证用户名中是否包含不允许的字符
		// 允许使用的字符为: 中文，英文字母，数字，下划线
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (c < 128) {
				if ((c < 'a' || c > 'z') //
						&& (c < '0' || c > '9') //
						&& c != '_') {
					return false;
				}
			}
		}

		return true;
	}

}
