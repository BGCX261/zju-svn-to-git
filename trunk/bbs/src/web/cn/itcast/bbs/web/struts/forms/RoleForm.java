package cn.itcast.bbs.web.struts.forms;

import cn.itcast.bbs.web.struts.forms.base.BaseActionForm;

@SuppressWarnings("serial")
public class RoleForm extends BaseActionForm {
	private String name;
	private String description;

	private int parentId;
	private int[] permissionsId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int[] getPermissionsId() {
		return permissionsId;
	}

	public void setPermissionsId(int[] permissionsId) {
		this.permissionsId = permissionsId;
	}

}
