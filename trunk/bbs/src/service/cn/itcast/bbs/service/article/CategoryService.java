package cn.itcast.bbs.service.article;

import java.util.List;

import cn.itcast.bbs.entities.article.Category;
import cn.itcast.bbs.exception.checked.ItcastNotEmptyException;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public interface CategoryService {

	/**
	 * @param id
	 * @return 指定id的Category
	 */
	Category getCategory(int id);

	/**
	 * @return 所有的分类的集合
	 */
	List<Category> findAll();

	/**
	 * 添加新分类
	 * 
	 * @param category
	 */
	void addNew(Category category);

	/**
	 * 更新分类信息
	 * 
	 * @param category
	 */
	void updateCategory(Category category);

	/**
	 * 删除指定id的分类, 不能删除包含有版面的分类.
	 * 
	 * @param id
	 * @throws ItcastNotEmptyException
	 *             删除含有版面的分类时抛此异常
	 */
	void deleteCategory(int id) throws ItcastNotEmptyException;

	/**
	 * 上/下移动指定的分类
	 * 
	 * @param id
	 * @param isUp
	 *            true表示向上移动, false表示向下移动
	 * @return 是否移动了. 有两种情况返回false: 1. 最下面的下移; 2. 最上面的上移
	 */
	boolean changeOrder(int id, boolean isUp);

}
