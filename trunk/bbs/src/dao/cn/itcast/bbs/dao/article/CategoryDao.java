package cn.itcast.bbs.dao.article;

import cn.itcast.bbs.dao.base.GenericDao;
import cn.itcast.bbs.entities.article.Category;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:49:13 PM
 */
public interface CategoryDao extends GenericDao<Category> {

	/**
	 * 按order排序后的列表中，排在指定的Category上面的那个Category；如果已经在最上面了，返回null.
	 * 
	 * @param category
	 * @return
	 */
	Category getPreviousByOrder(Category category);

	/**
	 * 按order排序后的列表中，排在指定的Category下面的那个Category；如果已经在最下面了，返回null.
	 * 
	 * @param category
	 * @return
	 */
	Category getNextByOrder(Category category);
}
