
function checkLoginName(loginName){
	if(loginName.length == 0){
		return;
	}
	
	var url = baseUrl + '/user.do?method=isLoginNameRegistered';
	var data = {loginName: loginName};
	var callback = function(result){
		var msg = "";
		if("true" == result){
			msg = "<font color='red'>很遗憾，用户名【" + loginName + "】已经被使用了，请更换</font>";
		}else{
			msg = "恭喜你，用户名【" + loginName + "】可以使用";
		}
		$("#loginNameMsg").html(msg);
	}

	$.get(url, data, callback);
}

function validateRegisterUserForm(form){
	var arr1 = [
		form.loginName, "登录名",
		form.nickname, "昵称",
		form.email, "电子邮件地址",
		form.password, "密码"
	];
	
	var arr2 = [
		form.password, form.password2, "密码"
	];
	
	var result = validateRequired(arr1)
				&& validateEmail(form.email)
				&& validateConfirm(arr2);
	
	if(!result){
		return false;
	}
	
	submitting();
	return true;
}


function validateEditUserForm(form){
	var arr1 = [
		form.nickname, "昵称",
		form.email, "电子邮件地址",
	];

	var result = validateRequired(arr1) && validateEmail(form.email);
	
	if(!result){
		return false;
	}
	
	submitting();
	return true;
}