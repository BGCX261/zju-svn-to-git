package cn.itcast.bbs.entities;



/**
 * @author 传智播客.汤阳光 Jun 6, 2008 3:16:10 PM
 */
public class ConfigFile {
	
	private String name;
	private String path;
	private long size;
	
	public ConfigFile() {}
	
	public ConfigFile(String name, String path, long size) {
		this.name = name;
		this.path = path;
		this.size = size;
	}
	
	public long getSize() {
		return size;
	}
	
	public void setSize(long size) {
		this.size = size;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
}
