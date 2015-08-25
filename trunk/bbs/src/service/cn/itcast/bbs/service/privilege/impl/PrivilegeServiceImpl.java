package cn.itcast.bbs.service.privilege.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import cn.itcast.bbs.entities.privilege.Permission;
import cn.itcast.bbs.entities.privilege.PermissionGroup;
import cn.itcast.bbs.service.base.BaseService;
import cn.itcast.bbs.service.privilege.PrivilegeService;

@Service("privilegeService")
public class PrivilegeServiceImpl extends BaseService implements PrivilegeService {

	public Set<Permission> getPermissions(int... permissionsId) {
		Set<Permission> permissions = new HashSet<Permission>();
		if(permissionsId == null){
			return permissions;
		}
		
		for (int id : permissionsId) {
			Permission p = permissionDao.get(id);
			if (p != null) {
				permissions.add(p);
			}
		}
		return permissions;
	}

	public List<PermissionGroup> findAllPermissionGroups() {
		return permissionGroupDao.findAll();
	}

}
