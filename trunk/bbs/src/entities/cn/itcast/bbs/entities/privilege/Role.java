package cn.itcast.bbs.entities.privilege;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.itcast.bbs.exception.runtime.ItcastException;
import cn.itcast.bbs.utils.tree.TreeNode;

@SuppressWarnings("serial")
public class Role implements TreeNode, Serializable, Cloneable {
	private int id;
	private String name;
	private String description;
	private Set<Permission> permissions = new HashSet<Permission>();

	private Role parent;
	private Set<Role> children = new HashSet<Role>();

	public Role() {
	}

	public Role(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Role deepClone() {
		try {
			Role copy = (Role) super.clone();
			Set<Role> childrenCopy = new HashSet<Role>();
			for (Role c : this.children) {
				childrenCopy.add(c.deepClone());
			}
			copy.setChildren(childrenCopy);

			return copy;
		} catch (CloneNotSupportedException e) {
			throw new ItcastException(e);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Role other = (Role) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getParent() {
		return parent;
	}

	public void setParent(Role parent) {
		this.parent = parent;
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

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<Role> getChildren() {
		return children;
	}

	public void setChildren(Set<Role> children) {
		this.children = children;
	}

}
