
//

// 验证是否是数字, 如果值是null, 或空字符串, 报错内容为"不能为空", 不是数字格式则报错, "数字输入不正确"
function validateInteger(arr){
	var msg = "", i = 0;
	for (i = 0; i < arr.length - 1; i += 2) {
		if (arr[i] == null || arr[i].length < 1) {
			// 不能为空
			msg += arr[i + 1] + " \u4e0d\u80fd\u4e3a\u7a7a\n";
		}
		else if (!isInteger(arr[i])) {
				msg += arr[i + 1] + " 数字输入不正确\n";
			}
	}
	
	if (msg.length > 0) {
		window.alert(msg);
		return false;
	}
	return true;
}



// -----------------  in test

var PATTERN_INTEGER = /^\d+$/; // new RegExp("^\\d+$");
function isInteger(value){
	return PATTERN_INTEGER.test(value);
}

var PATTERN_EMAIL = /^[a-zA-Z0-9_-|\.]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
function isEmail(value){
	return PATTERN_EMAIL.test(value);
}

function validateEmail(property, errMsg){
	if(errMsg == null){
		errMsg = "请输入正确的email 地址";
	}

	if(property.value == null || !isEmail(property.value)){
		alert(errMsg);
		property.focus();
		return false;
	}
	return true;
}

// --------------------------------- in use

// use ==, not ===, why?
// undefined === null, result is false; 
// undefined == null, result is true.

// format: [property, errMsg, ...]
function validateRequired(arr, errPrefix, errPostfix) {
	if(errPrefix == null){
		errPrefix = "";
	}
	if(errPostfix == null){
		errPostfix = " 不能为空";
	}

	var msg = "", i = 0, firstErrorPropertyIndex = -1;
	for (i = 0; i < arr.length - 1; i += 2) {
		if (arr[i].value == null || arr[i].value.length < 1) {
			// 不能为空
			msg += errPrefix + arr[i + 1] + errPostfix + "\n";
			if(firstErrorPropertyIndex == (-1)){
				firstErrorPropertyIndex = i;
			}
		}
	}
	
	if (msg.length > 0) {
		window.alert(msg);
		arr[firstErrorPropertyIndex].focus();
	}
	return firstErrorPropertyIndex == (-1);
}

// format: [property1, property2, errMsg, ...]
function validateConfirm(arr, errPrefix, errPostfix) {
	if(errPostfix == null){
		errPrefix = "请确认 ";
	}
	if(errPostfix == null){
		errPostfix = "";
	}
	
	var msg = "", i = 0, firstErrorPropertyIndex = -1;
	for (i = 0; i < arr.length - 1; i += 3) {
		if (arr[i].value != arr[i + 1].value) {
			// 请确认
			msg += errPrefix + arr[i + 2] + errPostfix + "\n";
			if(firstErrorPropertyIndex == (-1)){
				firstErrorPropertyIndex = i;
			}
		}
	}
	
	if (msg.length > 0) {
		window.alert(msg);
		arr[firstErrorPropertyIndex + 1].focus();
	}
	return firstErrorPropertyIndex == (-1);
}

// format: [property, minlength, errMsg, ...]
function validateMinLength(arr, errPrefix, errPostfix) {
	if(errPostfix == null){
		errPrefix = "";
	}
	if(errPostfix == null){
		errPostfix = " 不能少于指定个字符";
	}

	var msg = "", i = 0, firstErrorPropertyIndex = -1;
	for (i = 0; i < arr.length - 1; i += 2) {
		if (arr[i].value == null || arr[i].value.length < arr[i + 1]) {
			// 不能为空
			msg += errPrefix + arr[i + 2] + errPostfix + "\n";
			if(firstErrorPropertyIndex == (-1)){
				firstErrorPropertyIndex = i;
			}
		}
	}
	
	if (msg.length > 0) {
		window.alert(msg);
		arr[firstErrorPropertyIndex].focus();
	}
	return firstErrorPropertyIndex == (-1);
}