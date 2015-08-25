package cn.itcast.bbs.web.struts.actions.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itcast.bbs.entities.PageInfo;
import cn.itcast.bbs.entities.User;
import cn.itcast.bbs.entities.privilege.Action;
import cn.itcast.bbs.entities.privilege.Group;
import cn.itcast.bbs.entities.privilege.Privilege;
import cn.itcast.bbs.entities.privilege.Resource;
import cn.itcast.bbs.web.struts.action.base.BaseAdminAction;
import cn.itcast.bbs.web.struts.forms.UserForm;

/**
 * @author 传智播客.汤阳光 Jul 7, 2008
 */
@Controller("/admin/user")
@Privilege(resource = Resource.SYSTEM, action = Action.SYSTEM_MANAGE)
public class UserAction extends BaseAdminAction {

	/** 用户列表 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm) form;

		List<Group> groups = groupService.findAll();
		PageInfo<User> page = userService.findAllUsers(userForm.getPageNum());
		request.setAttribute("page", page);
		request.setAttribute("groups", groups);
		return mapping.findForward("list");
	}

	/**
	 * 修入用户所属的组
	 */
	public ActionForward changeGroupsUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm) form;

		User user = userService.getUser(userForm.getId());
		List<Group> groups = groupService.findAll();

		// 准备用户所在的组,用于在页面中显示
		int i = 0;
		int[] groupIds = new int[user.getGroups().size()];
		for (Group g : user.getGroups()) {
			groupIds[i++] = g.getId();
		}
		userForm.setGroupIds(groupIds);

		request.setAttribute("user", user);
		request.setAttribute("groups", groups);
		return mapping.findForward("changeGroups");
	}

	public ActionForward changeGroups(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm) form;
		userService.changeGroups(userForm.getId(), userForm.getGroupIds());
		return mapping.findForward("showUsers");
	}

	public ActionForward lock(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm) form;
		userService.lockUser(userForm.getId());
		return mapping.findForward("showUsers");
	}

	public ActionForward unlock(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm) form;
		userService.unlockUser(userForm.getId());
		return mapping.findForward("showUsers");
	}
}
