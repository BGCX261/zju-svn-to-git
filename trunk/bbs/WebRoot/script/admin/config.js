function changeAvatarSize(txt){
		if(txt.value < 20){
			alert("设置值的最小值是20, 设为20 或重新设置");
			txt.value = 20;
		}
		if(txt.value > 500){
			alert("设置值的最大值是500, 设为500 或重新设置");
			txt.value = 500;
		}
	
		if(txt.name == "cfg.avatarMaxWidth"){
			document.getElementById("avatarPreview").style.width = txt.value + "px";
		}
		else
			if(txt.name == "cfg.avatarMaxHeight"){
				document.getElementById("avatarPreview").style.height = txt.value + "px";
			}
}

function validateSettingsForm(form){
	var arr = [
		form["cfg.name"], "论坛名称",
		form["cfg.description"], "论坛描述",
	];
	
	if(! validateRequired(arr)){
		return false;
	}
	
	var intArr = [
		form["cfg.usersPerPage"], "每页显示用户数量",
		form["cfg.topicsPerPage"], "每页显示主题数量",
		form["cfg.repliesPerPage"], "每页显示文章数量",
		form["cfg.searchResultPerPage"], "每页显示搜索结果数量",
		
		form["cfg.postDelay"], "两次发帖的间隔时间(秒)",
		
		form["cfg.avatarMaxFileSize"], "头像文件的最大大小",
		form["cfg.avatarMaxWidth"], "头像的最大宽度(像素)",
		form["cfg.avatarMaxHeight"], "头像的最大高度(像素)",
	];
				
	if(! validateRequired(intArr)){
		return false;
	}

	$("input[type=submit]").attr("disabled", "disabled");
	$("input[type=submit]").val("正在提交, 请稍候...");
	return true;
}