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
import cn.itcast.bbs.entities.privilege.Permission;
import cn.itcast.bbs.entities.privilege.PermissionGroup;
import cn.itcast.bbs.entities.privilege.Privilege;
import cn.itcast.bbs.entities.privilege.Resource;
import cn.itcast.bbs.entities.privilege.Role;
import cn.itcast.bbs.exception.checked.ItcastNotEmptyException;
import cn.itcast.bbs.utils.tree.TreeNode;
import cn.itcast.bbs.utils.tree.TreeUtils;
import cn.itcast.bbs.web.struts.action.base.BaseAction;
import cn.itcast.bbs.web.struts.forms.RoleForm;

@Controller("/admin/role")
@Privilege(resource = Resource.SYSTEM, action = Action.SYSTEM_MANAGE)
public class RoleAction extends BaseAction {

	/**
	 * 列表
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Role> roles = roleService.findAll();
		request.setAttribute("roles", roles);
		return mapping.findForward("list");
	}

	/**
	 * 添加页面
	 */
	public ActionForward addUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<? extends TreeNode> allRoles = TreeUtils.shallowCopyAndChangeTreeNodeNameForTreeTextView(roleService.findAllTopLevel());
		List<PermissionGroup> permissionGroups = privilegeService.findAllPermissionGroups();
		
		request.setAttribute("roles", allRoles);
		request.setAttribute("permissionGroups", permissionGroups);
		return mapping.findForward("save");
	}

	/**
	 * 添加
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (validateFailed(form, mapping, request)) { // validate form bean
			return addUI(mapping, form, request, response);
		}

		RoleForm roleForm = (RoleForm) form;
		Role parent = roleService.getRole(roleForm.getParentId());
		Set<Permission> permissions = privilegeService.getPermissions(roleForm.getPermissionsId());

		Role role = new Role();
		BeanUtils.copyProperties(role, roleForm);
		role.setParent(parent);
		role.setPermissions(permissions);
		roleService.addNew(role);

		return mapping.findForward("showRoles");
	}

	/**
	 * 修改页面
	 */
	public ActionForward editUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RoleForm roleForm = (RoleForm) form;
		Role role = roleService.getRole(roleForm.getId());
		if (roleForm.getName() == null) {
			roleForm.setName(role.getName());
			roleForm.setDescription(role.getDescription());

			if (role.getParent() != null) {
				roleForm.setParentId(role.getParent().getId());
			}

			int index = 0, permissionsId[] = new int[role.getPermissions().size()];
			for (Permission p : role.getPermissions()) {
				permissionsId[index++] = p.getId();
			}
			roleForm.setPermissionsId(permissionsId);
		}

		List<? extends TreeNode> allRoles = TreeUtils.shallowCopyAndChangeTreeNodeNameForTreeTextView(roleService.findAllTopLevel());
		TreeUtils.removeTreeNodeAndChildrenFromAllTreeNodes(allRoles, role);
		request.setAttribute("roles", allRoles);
		
		List<PermissionGroup> permissionGroups = privilegeService.findAllPermissionGroups();
		request.setAttribute("permissionGroups", permissionGroups);
		return mapping.findForward("save");
	}

	/**
	 * 更新
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (validateFailed(form, mapping, request)) { // validate form bean
			return mapping.findForward("save");
		}

		RoleForm roleForm = (RoleForm) form;
		Role role = roleService.getRole(roleForm.getId());
		Role parent = roleService.getRole(roleForm.getParentId());
		Set<Permission> permissions = privilegeService.getPermissions(roleForm.getPermissionsId());

		BeanUtils.copyProperties(role, roleForm);
		role.setParent(parent);
		role.setPermissions(permissions);

		roleService.updateRole(role);
		return mapping.findForward("showRoles");
	}

	/**
	 * 删除
	 */
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RoleForm roleForm = (RoleForm) form;
		try {
			roleService.deleteRole(roleForm.getId());
			return mapping.findForward("showRoles");
		} catch (ItcastNotEmptyException e) {
			addError(request, "delete", "此解色中含有子角色, 不能删除.", false);
			ActionForward af = mapping.findForward("showRoles");
			return new ActionForward(af.getPath(), false); // 要转发
		}
	}

}
