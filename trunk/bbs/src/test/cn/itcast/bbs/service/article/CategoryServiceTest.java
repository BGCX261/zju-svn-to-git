package cn.itcast.bbs.service.article;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import cn.itcast.bbs.entities.article.Category;
import cn.itcast.bbs.test.base.BaseTest;

public class CategoryServiceTest extends BaseTest {

	@Test
	public void testFindAll() {
		List<Category> categories = categoryService.findAll();
		for (Category c : categories) {
			System.out.println(c);
		}
	}

	@Test
	public void testAddNew() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeOrder() {
		fail("Not yet implemented");
	}

}
