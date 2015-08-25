package cn.itcast.bbs.entities.privilege;

import java.util.ArrayList;
import java.util.List;

/**
 * 对权限进行分类
 * @author tyg
 *
 */
public class PermissionGroup {

	private int id;
	private String name;
	private List<Permission> permissions = new ArrayList<Permission>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
