package cn.itcast.bbs.dao.base;

import java.util.List;

import cn.itcast.bbs.entities.QueryResult;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:49:30 PM
 * @param <T>
 */
public interface GenericDao<T> {

	/**
	 * 刷出后清空一级缓存
	 */
	void flushAndClearSession();

	/**
	 * 保存实体
	 * 
	 * @param o
	 */
	int save(T o);

	/**
	 * 删除实体
	 * 
	 * @param o
	 */
	void delete(T o);

	/**
	 * 删除一批实体,
	 * 
	 * @param ids
	 *            要删除的实体的id
	 */
	void delete(int... ids);

	/**
	 * 更新实体
	 * 
	 * @param o
	 */
	void update(T o);
	/**
	 * 更新实体
	 * 
	 * @param o
	 */
	void merge(T o);

	/**
	 * 新增/更新实体
	 * 
	 * @param t
	 */
	void saveOrUpdate(T t);

	/**
	 * 获取指定id的实体
	 */
	T get(int id);

	/**
	 * 获取所有实体
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 查询记录总数
	 * 
	 * @return
	 */
	int getTotal();

	/**
	 * 从索引first开始最多取max条记录
	 * 
	 * @param firstResult
	 * @param maxResult
	 * @return 指定的一段数据
	 */
	QueryResult<T> findAll(int firstResult, int maxResult);

}
