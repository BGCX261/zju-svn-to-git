package cn.itcast.bbs.dao;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.bbs.entities.QueryResult;
import cn.itcast.bbs.entities.User;

public class UserDaoTest {
	private UserDao userDao;

	public UserDaoTest() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("/beans.xml");
		userDao = (UserDao) ac.getBean("userDao");
	}

	@Test
	public void testSave() {
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setLoginName("test" + System.currentTimeMillis());
			user.setNickname("user for test");
			user.setActive(true);
			user.setPassword(DigestUtils.md5Hex("test"));
			userDao.save(user);
		}
	}

	@Test
	public void testGetByLoginNamePart() {
		String loginNamePart = "test";
		QueryResult<User> qr = userDao.findByLoginNamePart(loginNamePart, 0, 10);
		for (User user : qr.getItems()) {
			System.out.println(user);
		}
	}

}
