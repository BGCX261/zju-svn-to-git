package cn.itcast.bbs.dao.article;

import java.util.Date;

import org.junit.Test;

import cn.itcast.bbs.entities.article.Reply;
import cn.itcast.bbs.entities.article.Topic;
import cn.itcast.bbs.test.base.BaseTest;

public class ReplyDaoImplTest extends BaseTest {

	@Test
	public void testFindLastReplyByTopic() {
		Topic t = new Topic();
		t.setTitle("xx title");
		t.setContent("xx content");
		t.setPostTime(new Date());
		t.setLastArticlePostTime(new Date());
		topicDao.save(t);

		Reply reply = new Reply();
		reply.setContent("xxxx content");
		reply.setTopic(t);
		reply.setPostTime(new Date());
		t.setLastArticlePostTime(new Date());
		topicDao.update(t);
		replyDao.save(reply);

		Reply r = replyDao.findLastReplyByTopic(t);
		System.out.println(r);
	}

}
