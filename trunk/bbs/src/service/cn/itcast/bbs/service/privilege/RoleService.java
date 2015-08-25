package cn.itcast.bbs.service.privilege;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bbs.entities.privilege.Role;
import cn.itcast.bbs.exception.checked.ItcastNotEmptyException;

@Transactional
public interface RoleService {

	/**
	 * 查询所有的Role
	 * 
	 * @return
	 */
	List<Role> findAll();

	
	/**
	 * 查询所有的Role
	 * 
	 * @return
	 */
	List<Role> findAllTopLevel();
	
	/**
	 * 获取Role
	 * 
	 * @param id
	 * @return
	 */
	Role getRole(int id);

	/**
	 * 获取Role
	 * 
	 * @param rolesId
	 * @return
	 */
	Set<Role> getRoles(int... rolesId);

	/**
	 * 添加新Role
	 * 
	 * @param role
	 */
	void addNew(Role role);

	/**
	 * 更新Role
	 * 
	 * @param role
	 */
	void updateRole(Role role);

	/**
	 * 删除Role
	 * 
	 * @param id
	 */
	void deleteRole(int id) throws ItcastNotEmptyException;

}
