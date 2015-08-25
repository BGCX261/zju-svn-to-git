package cn.itcast.bbs.service.article.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bbs.entities.QueryResult;
import cn.itcast.bbs.entities.article.Forum;
import cn.itcast.bbs.entities.article.Topic;
import cn.itcast.bbs.exception.checked.ItcastNotEmptyException;
import cn.itcast.bbs.service.article.ForumService;
import cn.itcast.bbs.service.base.BaseService;

/**
 * @author 传智播客.汤阳光 Jun 6, 2008 5:48:24 PM
 */
@Service("forumService")
@Transactional(readOnly = true)
public class ForumServiceImpl extends BaseService implements ForumService {

	public Forum getForum(int id) {
		return forumDao.get(id);
	}

	@Transactional
	public void addNew(Forum f) {
		forumDao.save(f);
		f.setOrder(f.getId());
	}

	@Transactional
	public void deleteForum(int id) throws ItcastNotEmptyException {
		Forum forum = forumDao.get(id);
		QueryResult<Topic> qr = topicDao.findByForum(forum, 0, 1);
		if (qr.getTotal() > 0) {
			throw new ItcastNotEmptyException("版面中含有主题, 不能删除!");
		}
		forumDao.delete(id);
	}

	@Transactional
	public void updateForum(Forum forum) {
		forumDao.update(forum);
	}

	@Transactional
	public boolean changeOrder(int id, boolean isUp) {
		Forum f = forumDao.get(id);
		Forum other = isUp ? forumDao.getPreviousByOrder(f) : forumDao.getNextByOrder(f);

		// 最上面的分类不能上移, 最下面的分类不能下移
		if (other == null) {
			return false;
		}

		// 交换显示顺序
		int tempOrder = f.getOrder();
		f.setOrder(other.getOrder());
		other.setOrder(tempOrder);
		return true;
	}

}
