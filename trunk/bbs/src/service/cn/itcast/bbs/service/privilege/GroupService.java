package cn.itcast.bbs.service.privilege;

import java.util.List;

import cn.itcast.bbs.entities.privilege.Group;
import cn.itcast.bbs.exception.checked.ItcastNotEmptyException;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public interface GroupService {

	/**
	 * 
	 * @param id
	 * @return 指定id的Group
	 */
	Group getGroup(int id);

	/**
	 * 
	 * @return 所有组
	 */
	List<Group> findAll();

	/**
	 * 添加组
	 * 
	 * @param group
	 */
	void addNew(Group group);

	/**
	 * 删除指定的(空)组, 不能删除包含用户的组
	 * 
	 * @param id
	 * @throws ItcastNotEmptyException
	 *             删除包含用户的组时抛此异常
	 */
	void deleteGroup(int id) throws ItcastNotEmptyException;

	/**
	 * 更新组的信息
	 * 
	 * @param group
	 */
	void updateGroup(Group group);
}
