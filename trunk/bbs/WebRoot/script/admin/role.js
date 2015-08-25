/**
 * 验证添加角色表单
 * @param form
 */
function validateRoleForm(form){
	var result = ItCast.validateRequired([form.name, "角色名称"]);
	if(result){
		return ItCast.submitting(form);
	}
	return false;
}

/**
 * 删除角色
 * @param name
 */
function deleteRole(name){
	var msg = "您将要删角色[" + name + "]\n";
	msg += "要继续吗 ?";
	return window.confirm(msg);
}