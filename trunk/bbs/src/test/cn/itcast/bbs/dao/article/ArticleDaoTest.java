package cn.itcast.bbs.dao.article;

import org.junit.Test;

import cn.itcast.bbs.entities.article.Article;
import cn.itcast.bbs.test.base.BaseTest;

public class ArticleDaoTest extends BaseTest{

	@Test
	public void testSave() {
		Article a = new Article();
		a.setContent("just article");
		articleDao.save(a);
	}

	@Test
	public void testGet() {
		Article a = articleDao.get(1);
		System.out.println(a);
	}

}
