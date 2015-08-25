package cn.itcast.bbs.entities;

/**
 * 性别
 * 
 * @author 传智播客.汤阳光 Apr 24, 2009
 * 
 */
public enum Sex {
	SECRECY("保密"),

	MALE("男"),

	FEMALE("女");

	private final String label;

	private Sex(String label) {
		this.label = label;
	}

	/** 显示的名称 */
	public String getLabel() {
		return this.label;
	}

	/** 所代表的值 */
	public String getValue() {
		return this.name();
	};
}
