package cn.itcast.bbs.entities;

import java.util.List;

/**
 * 查询结果（分页用）
 * 
 * @author 传智播客.汤阳光 Apr 26, 2009
 * 
 * @param <T>
 */
public class QueryResult<T> {

	private int total;
	private List<T> items;

	public QueryResult(int total, List<T> items) {
		this.total = total;
		this.items = items;
	}

	public int getTotal() {
		return total;
	}

	public List<T> getItems() {
		return items;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

}
