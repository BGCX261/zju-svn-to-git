package cn.itcast.bbs.service.article.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bbs.entities.article.Category;
import cn.itcast.bbs.exception.checked.ItcastNotEmptyException;
import cn.itcast.bbs.service.article.CategoryService;
import cn.itcast.bbs.service.base.BaseService;

/**
 * @author 传智播客.汤阳光 Jun 6, 2008 5:46:23 PM
 */
@Service("categoryService")
@Transactional(readOnly = true)
public class CategoryServiceImpl extends BaseService implements CategoryService {

	public Category getCategory(int id) {
		return categoryDao.get(id);
	}

	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Transactional
	public void addNew(Category category) {
		categoryDao.save(category);
		category.setOrder(category.getId());
	}

	@Transactional
	public void deleteCategory(int id) throws ItcastNotEmptyException {
		Category c = categoryDao.get(id);
		if (c.getForums().size() > 0) {// 如果分类含有版面, 不能删除
			throw new ItcastNotEmptyException("分类[id=" + c.getId() + ", name=" + c.getName() + "]中含有" + c.getForums().size() + "个版面，不能删除");
		}
		categoryDao.delete(id);
	}

	/**
	 * 修改分类的名称
	 */
	@Transactional
	public void updateCategory(Category category) {
		categoryDao.update(category);
	}

	@Transactional
	public boolean changeOrder(int id, boolean isUp) {
		Category c = categoryDao.get(id);
		Category other = isUp ? categoryDao.getPreviousByOrder(c) : categoryDao.getNextByOrder(c);

		// 最上面的分类不能上移, 最下面的分类不能下移
		if (other == null) {
			return false;
		}

		// 4. 交换显示顺序
		int tempOrder = c.getOrder();
		c.setOrder(other.getOrder());
		other.setOrder(tempOrder);
		return true;
	}

}
