package cn.itcast.bbs.service;

import cn.itcast.bbs.cfg.Settings;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public interface SystemService {

	/**
	 * @return 当前拥有的主题的数量
	 */
	int getTopicCount();

	/**
	 * @return 当前拥有的文章的数量
	 */
	int getArticleCount();

	/**
	 * @return 当前拥有的注册会员的数量
	 */
	int getUserCount();

	/**
	 * 更新系统配置信息<br>
	 * 
	 * @param cfg
	 */
	void updateSettings(Settings cfg);

}
