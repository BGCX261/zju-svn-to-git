package cn.itcast.bbs.web.struts.actions.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itcast.bbs.entities.article.Category;
import cn.itcast.bbs.entities.privilege.Action;
import cn.itcast.bbs.entities.privilege.Privilege;
import cn.itcast.bbs.entities.privilege.Resource;
import cn.itcast.bbs.exception.checked.ItcastNotEmptyException;
import cn.itcast.bbs.web.struts.action.base.BaseAdminAction;
import cn.itcast.bbs.web.struts.forms.CategoryForm;

/**
 * @author 传智播客.汤阳光 Jun 6, 2008
 */
@Controller("/admin/category")
@Privilege(resource = Resource.SYSTEM, action = Action.SYSTEM_MANAGE)
public class CategoryAction extends BaseAdminAction {

	/**
	 * 显示所有分类与版面的列表
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setCategoriesInRequestScope(request);
		return mapping.findForward("list");
	}

	/**
	 * 新分添加页面
	 */
	public ActionForward saveUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CategoryForm categoryForm = (CategoryForm) form;
		Category category = categoryService.getCategory(categoryForm.getId());
		if (category != null) {
			categoryForm.setName(category.getName());
		}
		return mapping.findForward("save");
	}

	/**
	 * 分类添加
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (validateFailed(form, mapping, request)) { // validate form bean
			return mapping.findForward("save");
		}

		CategoryForm categoryForm = (CategoryForm) form;
		Category category = categoryService.getCategory(categoryForm.getId());
		if (category == null) {
			category = new Category();
			category.setName(categoryForm.getName());
			categoryService.addNew(category);
		} else {
			category.setName(categoryForm.getName());
			categoryService.updateCategory(category);
		}
		return mapping.findForward("showCategories");
	}

	/**
	 * 分类删除
	 */
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CategoryForm categoryForm = (CategoryForm) form;
		try {
			categoryService.deleteCategory(categoryForm.getId());
			return mapping.findForward("showCategories");
		} catch (ItcastNotEmptyException e) {
			addError(request, "delete", "此分类含有子版面, 不能删除; 如要删除, 请先删除此分类中所有的子版面", false);
			ActionForward af = mapping.findForward("showCategories");
			return new ActionForward(af.getPath(), false); // 要转发
		}
	}

	/**
	 * 上移/下移
	 */
	public ActionForward changeOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryForm categoryForm = (CategoryForm) form;
		boolean isUp = getBoolParam(request, "isUp");
		categoryService.changeOrder(categoryForm.getId(), isUp);
		return mapping.findForward("showCategories");
	}

}
