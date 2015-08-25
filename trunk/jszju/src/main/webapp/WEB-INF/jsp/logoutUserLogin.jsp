<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
<html>
  <head>
    
<title>请登陆后再阅读文章</title>
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/form.css" type="text/css" rel="stylesheet" />
<script language="JavaScript" type=text/JavaScript>
     function doLogin()
     {
     	document.forms[0].actionType.value="userLoginCheck";
     	document.forms[0].submit();
     }
     function doRegister()
     {
     	var link = "userLogin.do?actionType=register";
		window.open(link,"_blank");
     }
     function reloadSafeCode(){
     	document.getElementById("safecode").src = "<%=request.getContextPath() %>/servlet/ImageServlet";
	 }
</script>
</head>
  
  <body >
  <!-- 用户登录Begin -->
  
<html:form  action="/userLogin.do" method="post">
 <html:hidden property="userInfodomain.id" />
   <html:hidden property="actionType"/>
      <table cellspacing="0" cellpadding="0"  align="center" class="form_table">
        <tbody>
          <tr>
            <td align="center" class="form_title" class="form_title" colspan="2" height="10">
            	提示信息：系统检测到您还未登录本站,登陆后继续访问
            </td>
          </tr>
          <tr>
            <td align="right" width="31%" height="30">用户名：</td>
            <td align="left" width="69%" style="text">
            <html:text styleClass="input" property="userInfodomain.name" title="用户名不能为空"></html:text>
            </td>
          </tr>
          <tr>
            <td align="right" height="30">密　码：</td>
            <td align="left">
            <html:password styleClass="input" property="userInfodomain.password" title="密码不能为空"></html:password>
            </td>
          </tr>
          <tr>
            <td align="right" height="30">验证码：</td>
            <td align="left">
            <input class="input" id="checkCode" title="请填写验证码" style="width:5em;" name="checkCode" type="text" />
            <img onclick="reloadSafeCode()" title="点击更换" id="safecode" src="<%=request.getContextPath() %>/servlet/ImageServlet"/>
            </td>            
          </tr>
          <tr>
            <td align="center" colspan="2" height="30">
            <input type="image" src="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/cms_member/img/login.gif" onclick="doLogin()" /> 
			&nbsp;&nbsp;&nbsp;&nbsp;
             <img name="reg" onclick="doRegister()" style="cursor: pointer" alt="" src="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/cms_member/img/reg.gif" /></td>
          </tr>
        </tbody>
      </table>
    </html:form>
  <!-- 用户登录End -->
  </body>
</html>
