
/**
 * 提交表单时把表单的提交按钮变为不可用.
 *
 * @param form 要进行提交的表单
 */
function submitting(form){
	var btnSubmit = null;
	if(typeof(form) == "undefined"){
		btnSubmit = $("input[type=submit]");
	} else {
		btnSubmit = $(form).find("input[type=submit]");
	}
	
	btnSubmit.attr("disabled", "disabled");
	btnSubmit.val("正在提交, 请稍候...");
}

/*----------------------------------------------------------------------------\
 |                       ItCast Utils  0.1                                     |
 |-----------------------------------------------------------------------------|
 |  ItCast Class									      						  |
 | 																			  |
 |-----------------------------------------------------------------------------|
 | Created 2009-06-15 itcast.tangYangGuang.                                    |
 \----------------------------------------------------------------------------*/

var ItCast = function(baseUrl) {
    this.baseUrl = baseUrl;
};

ItCast.version = "20090615";

/*----------------------------------------------------------------------------\
 |  form utils   								      						  |
 \----------------------------------------------------------------------------*/

/**
 * 提交表单时把表单的提交按钮变为不可用.
 *
 * @param form 要进行提交的表单
 */
ItCast.submitting = function submitting(form) {
    var btnSubmit = null;
    btnSubmit = form ? $("input[type=submit]") : $(form).find("input[type=submit]");

    btnSubmit.attr("disabled", "disabled");
    btnSubmit.val("正在提交, 请稍候...");
    return true;
};

/*----------------------------------------------------------------------------\
 |  validate     								      						  |
 \----------------------------------------------------------------------------*/
ItCast.patterns = new Object();

/**
 * 验证是否为数字
 * @param value
 */
ItCast.isInteger = function isInteger(value) {
    return ItCast.patterns.integer.test(value);
};
ItCast.patterns.integer = /^\d+$/; // new RegExp("^\\d+$");

/**
 * 验证是否为邮箱
 * @param value
 */
ItCast.isEmail = function isEmail(value) {
    return ItCast.patterns.email.test(value);
};
ItCast.patterns.email = /^[a-zA-Z0-9_-|\.]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;

/**
 * 验证元素值不能为空。如果为空，则弹出擎告框，并使一个出错的元素得到焦点。
 * @param arr        要验证的元素的数组, 格式为 [ 元素1, 元素1代表的名称 [, 元素2, 元素2代表的名称 [...]] ]
 * @param errPrefix  错误的前缀, 默认为 "请输入"
 * @param errPostfix 错误消息的后缀, 默认为 空字符串
 * @return 验证是否通过
 */
ItCast.validateRequired = function(arr, errPrefix, errPostfix) {
    if (errPrefix == null) {
        errPrefix = "请输入\"";
    }
    if (errPostfix == null) {
        errPostfix = "\"";
    }

    var msg = "", i = 0, firstErrorPropertyIndex = -1;
    for (i = 0; i < arr.length - 1; i += 2) {
        if (arr[i].value == null || $.trim(arr[i].value).length < 1) {
            // 不能为空
            msg += errPrefix + arr[i + 1] + errPostfix + "\n";
            if (firstErrorPropertyIndex == (-1)) {
                firstErrorPropertyIndex = i;
            }
        }
    }

    if (msg.length > 0) {
        window.alert(msg);
        arr[firstErrorPropertyIndex].focus();
    }
    return ( firstErrorPropertyIndex == (-1) );
}

/**
 * 验证email地址
 * @param email
 * @param message
 */
ItCast.validateEmail = function( email, message) {
    if ( !isEmail(email.value) ) {
        alert(message ? message : "请输入正确的 Email 地址");
        email.focus();
        return false;
    }

    return true;
}


/**
 * 验证两次输入的密码是否一致
 * @param arr
 * @param message    错误消息
 */
// format: [property1, property2, errMsg, ...]
ItCast.validateConfirmPassword = function(passwd1, passwd2, message) {
    if (passwd1.value != passwd2.value) {
        alert(message ? message : "两次输入的密码不一致");
        passwd2.focus();
        return false;
    }

    return true;
}
