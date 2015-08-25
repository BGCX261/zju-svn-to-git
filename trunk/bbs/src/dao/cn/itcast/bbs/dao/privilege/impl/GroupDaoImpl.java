package cn.itcast.bbs.dao.privilege.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.dao.privilege.GroupDao;
import cn.itcast.bbs.entities.privilege.Group;

/**
 * @author 传智播客.汤阳光 Jun 6, 2008 11:19:20 AM
 */
@Repository("groupDao")
public class GroupDaoImpl extends GenericDaoImpl<Group> implements GroupDao {

}
