package cn.itcast.bbs.dao.privilege.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.dao.privilege.RoleDao;
import cn.itcast.bbs.entities.privilege.Role;

@Repository("roleDao")
@SuppressWarnings("unchecked")
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

	public List<Role> findAllTopLevel() {
		return getSession().createQuery("from Role r where r.parent=null").list();
	}

}
