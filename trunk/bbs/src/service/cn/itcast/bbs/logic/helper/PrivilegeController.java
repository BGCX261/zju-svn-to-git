package cn.itcast.bbs.logic.helper;

import java.util.HashSet;
import java.util.Set;

import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.privilege.Action;
import cn.itcast.bbs.entities.privilege.Group;
import cn.itcast.bbs.entities.privilege.Permission;
import cn.itcast.bbs.entities.privilege.Resource;
import cn.itcast.bbs.entities.privilege.Role;

//	FIXME 权限判断
public class PrivilegeController {

	public static boolean isUserHasPermission(User user, Resource resource, Action action) {
		Set<Permission> allPermissions = getAllPermissions(user);
		Permission permission = new Permission(resource, action);
		return allPermissions.contains(permission);
		// for (Permission p : allPermissions) {
		// if (p.getResource().equals(resource) && p.getAction().equals(action)) {
		// return true;
		// }
		// }
		// return false;
	}

	public static Set<Permission> getAllPermissions(User user) {
		Set<Permission> allPermissions = new HashSet<Permission>();
		if (user == null) {
			return allPermissions;
		}

		for (Group group : user.getGroups()) {
			for (Role role : group.getRoles()) {
				getAllPermissions(role, allPermissions);
			}
		}
		return allPermissions;
	}

	public static Set<Permission> getAllPermissions(Role role, Set<Permission> allPermissions) {
		allPermissions.addAll(role.getPermissions());
		if (role.getParent() != null) {
			getAllPermissions(role.getParent(), allPermissions);
		}

		return allPermissions;
	}
}
