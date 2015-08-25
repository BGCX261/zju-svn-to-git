package cn.itcast.bbs.entities.article;

import java.util.HashSet;
import java.util.Set;

/**
 * 分类(顶级版面)
 * 
 * @author 传智博客.汤阳光 Jun 19, 2008
 */
public class Category {

	private int id;
	private String name; // 名称
	private int order;// 显示顺序
	private Set<Forum> forums = new HashSet<Forum>(0); // 子版面

	public Category() {}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getOrder() {
		return order;
	}

	public Set<Forum> getForums() {
		return forums;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setForums(Set<Forum> forums) {
		this.forums = forums;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("[Category: ")//
				.append("id=").append(id)//
				.append(",name=").append(name)//
				.append("]")//
				.toString();
	}

}
