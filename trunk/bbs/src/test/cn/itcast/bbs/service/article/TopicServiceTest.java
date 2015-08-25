package cn.itcast.bbs.service.article;

import java.io.FileInputStream;

import org.junit.Test;

import cn.itcast.bbs.entities.article.Topic;
import cn.itcast.bbs.test.base.BaseTest;

public class TopicServiceTest extends BaseTest {

	@Test
	public void testAddNew() {
		Topic topic = new Topic();
		topic.setTitle("Test topic addNew");
		topic.setContent("Test topic addNew.");

		topicService.addNew(topic);
	}

	public static void main(String[] args)throws Exception {
		new FileInputStream("C:/Program Files/a.txt");
	}

}
