package cn.itcast.bbs.dao.article;

import cn.itcast.bbs.dao.base.GenericDao;
import cn.itcast.bbs.entities.article.Forum;

/**
 * @author 传智播客.汤阳光 Jun 5, 2008 8:49:27 PM
 */
public interface ForumDao extends GenericDao<Forum> {

	/**
	 * 按order排序后的列表中，排在指定的Forum上面的那个Forum；如果已经在最上面了，返回null.
	 * 
	 * @param forum
	 * @return
	 */
	Forum getPreviousByOrder(Forum forum);

	/**
	 * 按order排序后的列表中，排在指定的Forum下面的那个Forum；如果已经在最下面了，返回null.
	 * 
	 * @param forum
	 * @return
	 */
	Forum getNextByOrder(Forum forum);
}
