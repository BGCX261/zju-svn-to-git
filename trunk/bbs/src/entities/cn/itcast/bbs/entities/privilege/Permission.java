package cn.itcast.bbs.entities.privilege;

/**
 * 权限
 * 
 * @author tyg
 * 
 */
public class Permission {

	private int id;
	private String name;
	private Resource resource; // 所操作的资源
	private Action action;// 对资源所做的操作

	public Permission() {
	}

	public Permission(Resource resource, Action action) {
		this.resource = resource;
		this.action = action;
	}

	public Permission(String name, Resource resource, Action action) {
		this.name = name;
		this.resource = resource;
		this.action = action;
	}

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

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((resource == null) ? 0 : resource.hashCode());
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
		final Permission other = (Permission) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("[Permission: ")//
				.append("id=").append(id)//
				.append(",name=").append(name)//
				.append(",resource=").append(resource)//
				.append(",action=").append(action)//
				.append("]")//
				.toString();
	}

}
