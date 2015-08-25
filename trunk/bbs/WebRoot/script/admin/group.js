/**
 * 验证添加群组表单
 * @param form
 */
function validateGroupForm(form){
	var result = ItCast.validateRequired([form.name, "群组名称"]);
	if(result){
		return ItCast.submitting(form);
	}
	return false;
}

/**
 * 删除群组
 * @param name
 */
function deleteGroup(name){
	var msg = "您将要删除组[" + name + "]\n";
	msg += "跟这个组有关的信息都将被删除 !!\n";
	msg += "要继续吗 ?";
	return window.confirm(msg);
}