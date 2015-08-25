package cn.itcast.bbs.entities.privilege;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户组
 * 
 * @author 传智博客.汤阳光 Jun 19, 2008
 */
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name; // 名称
	private String description;// 描述
	private Set<Role> roles = new HashSet<Role>(0);// 拥有的权限

	public Group() {
	}

	// ----------------

	/**
	 * 
	 * @return 所拥有的角色中所包含的所有许可
	 */
	public Set<Permission> getAllPermissions() {
		Set<Permission> permissions = new HashSet<Permission>();
		for (Role role : roles) {
			permissions.addAll(role.getPermissions());
		}
		return permissions;
	}

	// ----------------

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		final Group other = (Group) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("[Group: ")//
				.append("id=").append(id)//
				.append(",name=").append(name)//
				.append(",description=").append(description)//
				.append("]")//
				.toString();
	}
}
