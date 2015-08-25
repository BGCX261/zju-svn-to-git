package cn.itcast.bbs.test.base;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.bbs.cfg.SystemGlobals;
import cn.itcast.bbs.dao.article.ArticleDao;
import cn.itcast.bbs.dao.article.ReplyDao;
import cn.itcast.bbs.dao.article.TopicDao;
import cn.itcast.bbs.exception.runtime.ItcastException;
import cn.itcast.bbs.service.UserService;
import cn.itcast.bbs.service.article.ArticleService;
import cn.itcast.bbs.service.article.CategoryService;
import cn.itcast.bbs.service.article.ForumService;
import cn.itcast.bbs.service.article.ReplyService;
import cn.itcast.bbs.service.article.TopicService;

public class BaseTest {
	protected static ApplicationContext ac = new ClassPathXmlApplicationContext("/beans.xml");

	protected ArticleDao articleDao = (ArticleDao) ac.getBean("articleDao");
	protected TopicDao topicDao = (TopicDao) ac.getBean("topicDao");
	protected ReplyDao replyDao = (ReplyDao) ac.getBean("replyDao");

	protected CategoryService categoryService = (CategoryService) ac.getBean("categoryService");
	protected ForumService forumService = (ForumService) ac.getBean("forumService");
	protected ArticleService articleService = (ArticleService) ac.getBean("articleService");
	protected TopicService topicService = (TopicService) ac.getBean("topicService");
	protected ReplyService replyService = (ReplyService) ac.getBean("replyService");
	protected UserService userService = (UserService) ac.getBean("userService");

	@BeforeClass
	public static void beforeClass() {
		SystemGlobals.init("c:/test", getDefaultConfigPath(), ac);
	}

	protected static String getDefaultConfigPath() {
		String configFile = "/bbs/default.properties";

		URL url = BaseTest.class.getResource(configFile);
		if (url == null) {
			throw new ItcastException("找不到指定的配置文件：" + configFile);
		}

		try {
			return URLDecoder.decode(url.getPath(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new ItcastException(e);
		}
	}
}
