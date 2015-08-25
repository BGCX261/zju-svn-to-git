package cn.itcast.bbs.service.base;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.itcast.bbs.dao.ConfigDao;
import cn.itcast.bbs.dao.UserDao;
import cn.itcast.bbs.dao.article.ArticleDao;
import cn.itcast.bbs.dao.article.AttachmentDao;
import cn.itcast.bbs.dao.article.CategoryDao;
import cn.itcast.bbs.dao.article.DeletedArticleDao;
import cn.itcast.bbs.dao.article.ForumDao;
import cn.itcast.bbs.dao.article.ReplyDao;
import cn.itcast.bbs.dao.article.TopicDao;
import cn.itcast.bbs.dao.article.VoteDao;
import cn.itcast.bbs.dao.article.VoteItemDao;
import cn.itcast.bbs.dao.article.VoteRecordDao;
import cn.itcast.bbs.dao.log.ExceptionLogDao;
import cn.itcast.bbs.dao.log.OperationLogDao;
import cn.itcast.bbs.dao.privilege.GroupDao;
import cn.itcast.bbs.dao.privilege.PermissionDao;
import cn.itcast.bbs.dao.privilege.PermissionGroupDao;
import cn.itcast.bbs.dao.privilege.RoleDao;
import cn.itcast.bbs.dao.search.ArticleIndexDao;
import cn.itcast.bbs.service.article.impl.CategoryServiceImpl;

/**
 * 
 * @author 传智播客.汤阳光 Dec 15, 2008
 */
public abstract class BaseService {
	protected static Log log = LogFactory.getLog(CategoryServiceImpl.class);

	@Resource protected UserDao userDao;
	@Resource protected ConfigDao configDao;

	@Resource protected CategoryDao categoryDao;
	@Resource protected ForumDao forumDao;
	
	@Resource protected ArticleDao articleDao;
	@Resource protected TopicDao topicDao;
	@Resource protected ReplyDao replyDao;
	@Resource protected AttachmentDao attachmentDao;
	@Resource protected DeletedArticleDao deletedArticleDao;

	@Resource protected VoteDao voteDao;
	@Resource protected VoteItemDao voteItemDao;
	@Resource protected VoteRecordDao voteRecordDao;

	@Resource protected GroupDao groupDao;
	@Resource protected RoleDao roleDao;
	@Resource protected PermissionDao permissionDao;
	@Resource protected PermissionGroupDao permissionGroupDao;

	@Resource protected OperationLogDao operationLogDao;
	@Resource protected ExceptionLogDao exceptionLogDao;

	@Resource protected ArticleIndexDao articleIndexDao;
}
