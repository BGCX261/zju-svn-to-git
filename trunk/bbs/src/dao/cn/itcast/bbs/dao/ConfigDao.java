package cn.itcast.bbs.dao;

import cn.itcast.bbs.dao.base.GenericDao;
import cn.itcast.bbs.entities.Config;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:48:29 PM
 */
public interface ConfigDao extends GenericDao<Config> {
	
	/**
	 * 
	 * @param name
	 * @return 指定名称的配置
	 */
	Config findByName(String name);
}
