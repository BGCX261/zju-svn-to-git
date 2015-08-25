package cn.itcast.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.ConfigDao;
import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.entities.Config;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:51:49 PM
 */
@Repository("configDao")
public class ConfigDaoImpl extends GenericDaoImpl<Config> implements ConfigDao {

	public Config findByName(String name) {
		String hql = "from Config c where c.name=?";
		return (Config) findUniqueResult(hql, name);
	}

}
