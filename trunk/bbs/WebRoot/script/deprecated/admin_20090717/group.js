
function validateGroupForm(form){
	var result = validateRequired([form.name, "名称"]);	
	if(result){
		$("input[type=submit]").attr("disabled", "disabled");
		$("input[type=submit]").val("正在提交, 请稍候...");
		return true;
	}
	return false;
}

function deleteGroup(name){
	var msg = "您将要删除组[" + name + "]\n";
	msg += "跟这个组有关的信息都将被删除 !!\n";
	msg += "要继续吗 ?";
	return window.confirm(msg);
}