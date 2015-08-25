package cn.itcast.bbs.web.struts.actions.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itcast.bbs.entities.article.Category;
import cn.itcast.bbs.entities.article.Forum;
import cn.itcast.bbs.entities.privilege.Action;
import cn.itcast.bbs.entities.privilege.Privilege;
import cn.itcast.bbs.entities.privilege.Resource;
import cn.itcast.bbs.exception.checked.ItcastNotEmptyException;
import cn.itcast.bbs.web.struts.action.base.BaseAdminAction;
import cn.itcast.bbs.web.struts.forms.ForumForm;

/**
 * @author 传智博客.汤阳光 Jun 11, 2008
 */
@Controller("/admin/forum")
@Privilege(resource = Resource.SYSTEM, action = Action.SYSTEM_MANAGE)
public class ForumAction extends BaseAdminAction {

	/**
	 * 添加新版面页面
	 */
	public ActionForward addUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setCategoriesInRequestScope(request);
		return mapping.findForward("save");
	}

	/**
	 * 添加新版面
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (validateFailed(form, mapping, request)) { // validate form bean
			return addUI(mapping, form, request, response);
		}

		ForumForm forumForm = (ForumForm) form;
		Category category = categoryService.getCategory(forumForm.getCategoryId());

		Forum forum = new Forum();
		forum.setName(forumForm.getName());
		forum.setDescription(forumForm.getDescription());
		forum.setCategory(category);

		forumService.addNew(forum);
		return mapping.findForward("showCategories");
	}

	/**
	 * 修改版面信息页面
	 */
	public ActionForward editUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ForumForm forumForm = (ForumForm) form;
		Forum f = forumService.getForum(forumForm.getId());

		// 第一次到修改页面时才需要准备数据,出错后转发过来的应显示上次的错误输入
		if (forumForm.getCategoryId() == 0) {
			forumForm.setName(f.getName());
			forumForm.setDescription(f.getDescription());
			forumForm.setCategoryId(f.getCategory().getId());
		}
		
		setCategoriesInRequestScope(request);
		return mapping.findForward("save");
	}

	/**
	 * 修改版面信息
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (validateFailed(form, mapping, request)) { // validate form bean
			return editUI(mapping, form, request, response);
		}

		ForumForm forumForm = (ForumForm) form;
		Forum forum = forumService.getForum(forumForm.getId());
		Category category = categoryService.getCategory(forumForm.getCategoryId());

		forum.setName(forumForm.getName());
		forum.setDescription(forumForm.getDescription());
		forum.setCategory(category);
		
		forumService.updateForum(forum);
		return mapping.findForward("showCategories");
	}

	/**
	 * 删除版面
	 */
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ForumForm forumForm = (ForumForm) form;
		try {
			forumService.deleteForum(forumForm.getId());
			return mapping.findForward("showCategories");
		} catch (ItcastNotEmptyException e) {
			addError(request, "delete", "版面中含有主题, 不能删除.", false);
			ActionForward af = mapping.findForward("showCategories");
			return new ActionForward(af.getPath(), false); // 要转发
		}
	}

	/**
	 * 上移/下移
	 */
	public ActionForward changeOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ForumForm forumForm = (ForumForm) form;
		boolean isUp = getBoolParam(request, "isUp");
		forumService.changeOrder(forumForm.getId(), isUp);
		return mapping.findForward("showCategories");
	}

}
