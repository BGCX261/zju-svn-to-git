package cn.itcast.bbs.init;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.bbs.dao.UserDao;
import cn.itcast.bbs.dao.privilege.PermissionDao;
import cn.itcast.bbs.dao.privilege.PermissionGroupDao;
import cn.itcast.bbs.dao.privilege.RoleDao;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.helper.PrivilegeHelper;
import cn.itcast.bbs.entities.privilege.Group;
import cn.itcast.bbs.entities.privilege.Permission;
import cn.itcast.bbs.entities.privilege.PermissionGroup;
import cn.itcast.bbs.entities.privilege.Resource;
import cn.itcast.bbs.entities.privilege.Role;
import cn.itcast.bbs.service.UserService;
import cn.itcast.bbs.service.privilege.GroupService;

public class InitSystem {
	protected static ApplicationContext ac = new ClassPathXmlApplicationContext("/beans.xml");
	private static PermissionDao permissionDao = (PermissionDao) ac.getBean("permissionDao");
	private static PermissionGroupDao permissionGroupDao = (PermissionGroupDao) ac.getBean("permissionGroupDao");
	private static RoleDao roleDao = (RoleDao) ac.getBean("roleDao");
	// private static UserDao userDao = (UserDao) ac.getBean("userDao");

	private static UserService userService = (UserService) ac.getBean("userService");
	private static GroupService groupService = (GroupService) ac.getBean("groupService");

	public static void main(String[] args) {

		// --- 建表
		SchemaExport schemaExport = new SchemaExport(new Configuration().configure());
		schemaExport.create(true, true);

		// =============================================

		// --- 添加权限与权限组
		for (Resource resource : Resource.values()) {
			PermissionGroup permissionGroup = new PermissionGroup();
			permissionGroup.setName(resource.getName() + "有关");
			permissionGroup.setPermissions(PrivilegeHelper.getAllPermissionsByResourceWithoutIdentifier(resource));
			permissionGroupDao.save(permissionGroup);
		}

		List<Permission> allPermissions = permissionDao.findAll();

		// --- 添加Role
		Role role1 = new Role();
		role1.setName("普通用户");
		role1.getPermissions().add(getPermissionByName(allPermissions, "发表主题"));
		role1.getPermissions().add(getPermissionByName(allPermissions, "发表回复"));
		role1.getPermissions().add(getPermissionByName(allPermissions, "发表附件"));
		role1.getPermissions().add(getPermissionByName(allPermissions, "发表投票"));
		role1.getPermissions().add(getPermissionByName(allPermissions, "下载附件"));
		role1.getPermissions().add(getPermissionByName(allPermissions, "参与投票"));
		roleDao.save(role1);

		Role role2 = new Role();
		role2.setName("版主");
		role2.setParent(role1);
		role2.getPermissions().add(getPermissionByName(allPermissions, "删除主题"));
		role2.getPermissions().add(getPermissionByName(allPermissions, "修改主题"));
		role2.getPermissions().add(getPermissionByName(allPermissions, "移动主题"));
		role2.getPermissions().add(getPermissionByName(allPermissions, "删除回复"));
		role2.getPermissions().add(getPermissionByName(allPermissions, "修改回复"));
		role2.getPermissions().add(getPermissionByName(allPermissions, "删除附件"));
		role2.getPermissions().add(getPermissionByName(allPermissions, "修改附件"));
		role2.getPermissions().add(getPermissionByName(allPermissions, "删除投票"));
		roleDao.save(role2);

		Role role3 = new Role();
		role3.setParent(role2);
		role3.setName("系统管理员");
		role3.getPermissions().add(getPermissionByName(allPermissions, "后台管理"));
		roleDao.save(role3);

		// ==============================================

		// --- 组
		Group anonymousGroup = new Group();
		anonymousGroup.setName("anonymous");
		anonymousGroup.setDescription("匿名用户组");
		groupService.addNew(anonymousGroup);

		Group adminGroup = new Group();
		adminGroup.setName("admin");
		adminGroup.setDescription("管理员用户组");
		adminGroup.getRoles().add(role3);
		groupService.addNew(adminGroup);

		Group defaultGroup = new Group();
		defaultGroup.setName("default");
		defaultGroup.setDescription("普通用户组");
		defaultGroup.getRoles().add(role1);
		groupService.addNew(defaultGroup);

		// --- 用户
		User anonymousUser = new User();
		anonymousUser.setLoginName("anonymous");
		anonymousUser.setNickname("匿名用户");
		anonymousUser.setPassword("test");
		anonymousUser.setRegistrationTime(new Date());
		anonymousUser.getGroups().add(anonymousGroup);
		userService.addNew(anonymousUser);

		User supermanUser = new User();
		supermanUser.setLoginName("superman");
		supermanUser.setNickname("超级管理员");
		supermanUser.setPassword("test");
		supermanUser.setRegistrationTime(new Date());
		supermanUser.getGroups().add(adminGroup);
		userService.addNew(supermanUser);

		System.out.println("---> 初始化系统数据完毕.");
	}

	public static Permission getPermissionByName(List<Permission> allPermissions, String name) {
		for (Permission p : allPermissions) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		throw new RuntimeException("没有名字为【" + name + "】的Permission.");
	}

	public static void main1(String[] args) {
		System.out.println(DigestUtils.md5Hex("test"));
	}
}
