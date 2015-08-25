package cn.itcast.bbs.service.article;

import cn.itcast.bbs.entities.article.Forum;
import cn.itcast.bbs.exception.checked.ItcastNotEmptyException;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public interface ForumService {

	/**
	 * @param id
	 * @return 指定id的Forum
	 */
	Forum getForum(int id);

	/**
	 * 添加新版面<br>
	 * 
	 * @param c
	 */
	void addNew(Forum forum);

	/**
	 * 删除指定id的版面, 不能删除含有主题的版面
	 * 
	 * @param id
	 * @throws ItcastNotEmptyException
	 *             删除含有主题的版面时抛此异常
	 */
	void deleteForum(int id) throws ItcastNotEmptyException;

	/**
	 * 更新版面的信息
	 * 
	 * @param forum
	 */
	void updateForum(Forum forum);

	/**
	 * 上/下移动指定的版面
	 * 
	 * @param id
	 * @param up
	 *            true表示向上移动, false表示向下移动
	 */
	boolean changeOrder(int id, boolean up);

}
