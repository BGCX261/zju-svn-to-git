package cn.itcast.bbs.dao.privilege;

import java.util.List;

import cn.itcast.bbs.dao.base.GenericDao;
import cn.itcast.bbs.entities.privilege.Role;

public interface RoleDao extends GenericDao<Role>{

	/**
	 *  查询所有顶级的Role
	 * @return
	 */
	List<Role> findAllTopLevel();

}
