package cn.itcast.bbs.entities.helper;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.bbs.entities.privilege.Permission;
import cn.itcast.bbs.entities.privilege.Resource;
import cn.itcast.bbs.entities.privilege.Resource.SupportedAction;

public class PrivilegeHelper {
	/**
	 * 列出系统中所有Permission，不是从数据库中取的！
	 * 
	 * @param resource
	 * @return
	 */
	public static List<Permission> getAllPermissionsWithoutIdentifier() {
		List<Permission> permissions = new ArrayList<Permission>();

		for (Resource resource : Resource.values()) {
			for (SupportedAction sa : resource.getSupportedActions()) {
				permissions.add(new Permission(sa.getName(), resource, sa.getAction()));
			}
		}

		return permissions;
	}

	/**
	 * 列出指定Resource对应的所有Permission，不是从数据库中取的！
	 * 
	 * @param resource
	 * @return
	 */
	public static List<Permission> getAllPermissionsByResourceWithoutIdentifier(Resource resource) {
		List<Permission> permissions = new ArrayList<Permission>();

		for (SupportedAction sa : resource.getSupportedActions()) {
			permissions.add(new Permission(sa.getName(), resource, sa.getAction()));
		}

		return permissions;
	}

}
