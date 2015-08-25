package cn.itcast.bbs.service.privilege;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bbs.entities.privilege.Permission;
import cn.itcast.bbs.entities.privilege.PermissionGroup;

@Transactional
public interface PrivilegeService {

	/**
	 * 获取Permission
	 * 
	 * @param permissionsId
	 * @return
	 */
	Set<Permission> getPermissions(int... permissionsId);

	/**
	 * 查询所有的许可组（权限）
	 * 
	 * @return
	 */
	List<PermissionGroup> findAllPermissionGroups();

}
