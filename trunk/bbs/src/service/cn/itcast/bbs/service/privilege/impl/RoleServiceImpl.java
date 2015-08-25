package cn.itcast.bbs.service.privilege.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bbs.entities.privilege.Role;
import cn.itcast.bbs.exception.checked.ItcastNotEmptyException;
import cn.itcast.bbs.service.base.BaseService;
import cn.itcast.bbs.service.privilege.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl extends BaseService implements RoleService {

	public void addNew(Role role) {
		roleDao.save(role);
	}

	public void deleteRole(int id) throws ItcastNotEmptyException {
		Role role = roleDao.get(id);
		if (role == null) {
			return;
		}

		if (role.getChildren().size() > 0) {
			throw new ItcastNotEmptyException("Role[id=" + role.getId() + ",name=" + role.getName() + "]含有子Role，不能删除。");
		}
		roleDao.delete(role);
	}

	public List<Role> findAll() {
		return roleDao.findAll();
	}
	

	public List<Role> findAllTopLevel() {
		return roleDao.findAllTopLevel();
	}

	public Role getRole(int id) {
		return roleDao.get(id);
	}

	public void updateRole(Role role) {
		roleDao.update(role);
	}

	public Set<Role> getRoles(int... rolesId) {
		Set<Role> roles = new HashSet<Role>();
		if(rolesId == null){
			return roles;
		}
		
		for(int id : rolesId){
			Role role = roleDao.get(id);
			if(role != null){
				roles.add(role);
			}
		}
		return roles;
	}

}
