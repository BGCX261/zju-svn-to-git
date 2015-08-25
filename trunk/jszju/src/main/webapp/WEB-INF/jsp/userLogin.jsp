<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
<html>
  <head>
    
<title>My JSP 'userLogin.jsp' starting page</title>
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/whole.css" type="text/css" rel="stylesheet" />
<script language="JavaScript" type=text/JavaScript>
     function doLogin()
     {
     	if(document.getElementsByName("itcastUserDomain.loginName")[0].value=="")
     	{
     		alert("用户名不能为空");
     		return false;
     	}
     	if(document.getElementsByName("itcastUserDomain.password")[0].value=="")
     	{
     		alert("用户名不能为空");
     		return false;
     	}
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
  
  <body style="background-color:#ffffff;">
  <!-- 用户登录Begin -->
<html:form  action="/userLogin.do" method="post">
 <html:hidden property="itcastUserDomain.id" />
   <html:hidden property="actionType"/>
       <table align="center" cellspacing="3px" style="border:1px solid #cccccc;">
        <tbody>
          <tr>
            <td align="center" colspan="2" height="10"></td>
          </tr>
          <tr>
            <td align="right" width="31%" height="30">用户名：</td>
            <td align="left" width="69%" style="text">
            <html:text property="itcastUserDomain.loginName" title="用户名不能为空"></html:text>
            </td>
          </tr>
          <tr>
            <td align="right" height="30">密　码：</td>
            <td align="left">
            <html:password property="itcastUserDomain.password" title="密码不能为空"></html:password>
            </td>
          </tr>
          <tr>
            <td align="right" height="30">验证码：
             </td>
            <td align="left">
            <input class="input" id="checkCode" title="请填写验证码" size="3" name="checkCode" type="text" />
             <img onclick="reloadSafeCode()" title="点击更换" id="safecode" src="<%=request.getContextPath() %>/servlet/ImageServlet"/></td>
          </tr>
          <tr>
            <td align="center" colspan="2" height="30"><input type="image" src="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/cms_member/img/login.gif" onclick="doLogin()" /> &nbsp;
             <a href="http://www.jszuaa.com:8022/itcastbbs_beta/user.do?method=registerUI" target="_blank"><img name="reg"  style="cursor: pointer" alt="" src="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/cms_member/img/reg.gif" /></a></td>
          </tr>
        </tbody>
      </table>
    </html:form>
  <!-- 用户登录End -->
  </body>
</html>
