<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
<%@page import="java.net.URLDecoder"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>会员已登录 </title>
<script src="<%=request.getContextPath()%>/front_res/jquery.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/front_res/front.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/cms_member/css/whole.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/cms_member/css/layout.css" rel="stylesheet" type="text/css"/>
	<script language="JavaScript" type=text/JavaScript>
     function doLogout()
     {
     	document.forms[0].actionType.value="logout";
     	document.forms[0].submit();
     }
     function member()
     {
     	alert("此功能待完善中...");
     }
</script>
</head>

<body>
  <html:form  action="/userLogin.do" method="post">
   <html:hidden property="actionType"/>
   </html:form>
<table width="98%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="40" colspan="2" align="center" class="f12b-red">登 录 成 功 ！</td>
  </tr>
  <tr>
    <td width="44%" height="30" align="right">用户名：</td>
    <td width="56%"><%Cookie[] allcookie=request.getCookies();
for(int i=0;i<allcookie.length;i++)
{
String idx=allcookie[i].getName();
if(idx.equals("name"))
{
	String value=allcookie[i].getValue();
	value = java.net.URLDecoder.decode(value,"UTF-8");
	out.print(value);
	break;
}

} %></td>
  </tr>
  <tr>
    <td height="30" align="right">级　别：</td>
    <td>普通会员</td>
  </tr>
  <tr>
    <td height="30" colspan="2" align="center">
	【<a href="javascript:member()" >会员中心</a>】&nbsp;&nbsp;
	【<a href="javascript:doLogout()">安全退出</a>】</td>
  </tr>
</table>
</body>
</html>