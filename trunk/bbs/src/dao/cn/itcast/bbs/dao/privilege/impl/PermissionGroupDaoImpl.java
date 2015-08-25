package cn.itcast.bbs.dao.privilege.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.dao.privilege.PermissionGroupDao;
import cn.itcast.bbs.entities.privilege.PermissionGroup;

@Repository("permissionGroupDao")
public class PermissionGroupDaoImpl extends GenericDaoImpl<PermissionGroup> implements PermissionGroupDao {

}
