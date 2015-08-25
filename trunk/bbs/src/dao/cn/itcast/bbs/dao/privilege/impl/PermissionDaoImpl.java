package cn.itcast.bbs.dao.privilege.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bbs.dao.base.GenericDaoImpl;
import cn.itcast.bbs.dao.privilege.PermissionDao;
import cn.itcast.bbs.entities.privilege.Permission;

@Repository("permissionDao")
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements PermissionDao {

}
