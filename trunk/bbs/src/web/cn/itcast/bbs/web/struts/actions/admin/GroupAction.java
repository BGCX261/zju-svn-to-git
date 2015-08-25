package cn.itcast.bbs.web.struts.actions.admin;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import cn.itcast.bbs.entities.privilege.Action;
import cn.itcast.bbs.entities.privilege.Group;
import cn.itcast.bbs.entities.privilege.Privilege;
import cn.itcast.bbs.entities.privilege.Resource;
import cn.itcast.bbs.entities.privilege.Role;
import cn.itcast.bbs.exception.checked.ItcastNotEmptyException;
import cn.itcast.bbs.utils.tree.TreeNode;
import cn.itcast.bbs.utils.tree.TreeUtils;
import cn.itcast.bbs.web.struts.action.base.BaseAdminAction;
import cn.itcast.bbs.web.struts.forms.GroupForm;

/**
 * @author 传智播客.汤阳光 Jul 7, 2008
 */
@Controller("/admin/group")
@Privilege(resource = Resource.SYSTEM, action = Action.SYSTEM_MANAGE)
public class GroupAction extends BaseAdminAction {

	/**
	 * 组列表
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Group> groups = groupService.findAll();
		request.setAttribute("groups", groups);
		return mapping.findForward("list");
	}

	/**
	 * 添加组页面
	 */
	public ActionForward saveUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GroupForm groupForm = (GroupForm) form;
		if ((groupForm.getId() > 0) && groupForm.getName() == null) {
			Group group = groupService.getGroup(groupForm.getId());
			groupForm.setName(group.getName());
			groupForm.setDescription(group.getDescription());

			int index = 0, rolesId[] = new int[group.getRoles().size()];
			for (Role r : group.getRoles()) {
				rolesId[index++] = r.getId();
			}
			groupForm.setRolesId(rolesId);
		}

		List<? extends TreeNode> allRoles = TreeUtils.shallowCopyAndChangeTreeNodeNameForTreeTextView(roleService.findAllTopLevel());
		request.setAttribute("roles", allRoles);
		return mapping.findForward("save");
	}

	/**
	 * 添加组
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (validateFailed(form, mapping, request)) { // validate form bean
			return saveUI(mapping, form, request, response);
		}

		GroupForm groupForm = (GroupForm) form;
		Group group = groupService.getGroup(groupForm.getId());
		Set<Role> roles = roleService.getRoles(groupForm.getRolesId());

		if (group == null) {
			group = new Group();
			BeanUtils.copyProperties(group, groupForm);
			group.setRoles(roles);
			groupService.addNew(group);
		} else {
			BeanUtils.copyProperties(group, groupForm);
			group.setRoles(roles);
			groupService.updateGroup(group);
		}

		return mapping.findForward("showGroups");
	}

	/**
	 * 删除组
	 */
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GroupForm groupForm = (GroupForm) form;
		try {
			groupService.deleteGroup(groupForm.getId());
			return mapping.findForward("showGroups");
		} catch (ItcastNotEmptyException e) {
			addError(request, "delete", "此组中有用户, 不能删除.", false);
			ActionForward af = mapping.findForward("showGroups");
			return new ActionForward(af.getPath(), false); // 要转发
		}
	}

}
