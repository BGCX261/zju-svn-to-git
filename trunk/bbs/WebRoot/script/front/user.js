function checkLoginName(loginName) {
    if ($.trim(loginName).length == 0) {
        return;
    }

    var url = baseUrl + '/user.do?method=isLoginNameRegistered';
    var data = {loginName: loginName};
    var callback = function(result) {
        var msg = "";
        if ("true" == result) {
            msg = "<font color='red' style='font-size:12px;'>很遗憾，用户名【" + loginName + "】已经被使用了，请更换</font>";
        } else {
            msg = "恭喜你，用户名【" + loginName + "】可以使用";
        }
        $("#loginNameMsg").html(msg);
    }

    $.get(url, data, callback);
}

/**
 * 验证注册用户表单
 * @param form
 */
function validateRegisterUserForm(form) {
    var arr1 = [ form.loginName, "登录名",
        form.password, "密码" ,
        form.email, "电子邮件地址",
        form.realname, "真实姓名",
        form.cellphone, "常用手机",
        form.major, "毕业专业",
        form.education, "学历",
        form.title, "现在职称",
        form.birthplace, "籍贯",
        form.address, "居住地址",
        form.postcode, "邮编",
        form.comeFrom, "所在单位",
        form.birthday, "出生日期",
        form.nickname, "昵称" ];

    var result = ItCast.validateRequired(arr1)
            && ItCast.validateEmail(form.email)
            && ItCast.validateConfirmPassword(form.password, form.password2);
    if (result) {
        return ItCast.submitting(form);
    }
    return false;
}


function validateEditUserForm(form) {
    var arr1 = [
        form.nickname, "昵称",
        form.email, "电子邮件地址",
    ];

    var result = validateRequired(arr1) && validateEmail(form.email);

    if (!result) {
        return false;
    }

    submitting();
    return true;
}

/**
 * 验证用户登录表单
 * @param form
 */
function validateLoginForm(form) {
    var result = ItCast.validateRequired(//
            [ form.loginName, "登录名", //
                form.password, "密码" ]);
    if (result) {
        return ItCast.submitting(form);
    }
    return false;
}