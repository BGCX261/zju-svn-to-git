
function validateGroupForm(form){
	var result = validateRequired([form.name, "名称"]);	
	if(result){
		$("input[type=submit]").attr("disabled", "disabled");
		$("input[type=submit]").val("正在提交, 请稍候...");
		return true;
	}
	return false;
}

function deleteRole(name){
	var msg = "您将要删角色[" + name + "]\n";
	msg += "要继续吗 ?";
	return window.confirm(msg);
}