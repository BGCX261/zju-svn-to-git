package cn.itcast.bbs.entities;

/**
 * 系统配置, 配置项的key不能重复
 * 
 * @author 传智播客.汤阳光 Jun 25, 2008
 */
public class Config {
	/**
	 * 
	 * @author 传智播客.汤阳光 Apr 25, 2009
	 * 
	 */
	public static class ConfigNames {}

	private int id;
	private String name;// 配置项名称
	private String value;// 配置项值

	public Config() {}

	public Config(String name) {
		this.name = name;
	}

	public Config(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("[Config: ")//
				.append("id=").append(id)//
				.append(",name=").append(name)//
				.append(",value=").append(value)//
				.append("]")//
				.toString();
	}
}
