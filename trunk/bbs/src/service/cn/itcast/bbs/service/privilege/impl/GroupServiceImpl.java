package cn.itcast.bbs.service.privilege.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bbs.entities.privilege.Group;
import cn.itcast.bbs.exception.checked.ItcastNotEmptyException;
import cn.itcast.bbs.service.base.BaseService;
import cn.itcast.bbs.service.privilege.GroupService;

/**
 * @author 传智播客.汤阳光 Jun 6
 */
@Service("groupService")
@Transactional(readOnly = true)
public class GroupServiceImpl extends BaseService implements GroupService {

	public Group getGroup(int id) {
		return groupDao.get(id);
	}

	public List<Group> findAll() {
		return groupDao.findAll();
	}

	@Transactional
	public void addNew(Group group) {
		groupDao.save(group);
	}

	@Transactional
	public void deleteGroup(int id) throws ItcastNotEmptyException {
		Group group = groupDao.get(id);
		if(group == null){
			return;
		}
		
		if (userDao.findByGroup(group, 0, 1).getTotal() > 0) {
			throw new ItcastNotEmptyException("组[id=" + id + ",name=" + group.getName() + "]中含有用户, 不能删除");
		}

		groupDao.delete(group);
	}

	@Transactional
	public void updateGroup(Group group) {
		groupDao.update(group);
	}

}
