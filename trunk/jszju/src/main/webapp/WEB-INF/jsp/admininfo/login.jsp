<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/js.jsp" %>
<%@ include file="/WEB-INF/jsp/share/css.jsp" %>
<%@ include file="/WEB-INF/jsp/showDialog.jsp" %>
<head>
<title>后台管理系统登录</title>
<link href="<%=request.getContextPath()%>/common_res/css/jquery_validate.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/common_res/css/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/core_res/css/front.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/core_res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/core_res/css/theme.css" rel="stylesheet" type="text/css"/>

<script src="<%=request.getContextPath()%>/common_res/js/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/common_res/js/jquery_validate.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/common_res/js/pony.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/common_res/js/jquery.ui.draggable.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/common_res/js/jquery.alerts.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/core_res/js/front.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/core_res/js/admin.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/fckeditor/fckeditor.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript"></script>
<style type="text/css">
body {		
	margin:0;
	padding:0;
	font-size:12px;
	background-color:#eeeeee;
}
.input{
	BORDER: #cccccc 1px solid; 
	height:16px;
	width:150px;
}
.version {
	color: #000000;
	font-weight: bold;
	font-size: 25px;
}
</style>
<script type="text/javascript">

function check()
{
if(document.getElementsByName("adminInfoDomain.name")[0].value=="")
     	{
     		alert("用户名不能为空哦");
     		return false;
     	}
     	if(document.getElementsByName("adminInfoDomain.password")[0].value=="")
     	{
     		alert("密码不是空哦");
     		return false;
     	}
	document.forms[0].actionType.value="adminLoginCheck";
	 document.forms[0].submit();
	 document.forms[0].reset();
}
</script>
</head>
<body>
   <html:form action="/adminInfoSubmit.do" method="post" >
    <html:hidden property="actionType"/>
<table border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10%;"> 
  <tr>
   <td><img src="<%=request.getContextPath()%>/cms_res/img/login/login_r1_c1.jpg" /></td>
   <td width="343" background="<%=request.getContextPath()%>/cms_res/img/login/login_r1_c2.jpg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
     <tr>
       <td height="35">&nbsp;</td>
       </tr>
   </table></td>
   <td><img src="<%=request.getContextPath()%>/cms_res/img/login/login_r1_c3.jpg" /></td>   
  </tr>
  <tr>
   <td><img src="<%=request.getContextPath()%>/cms_res/img/login/login_r2_c1.jpg" /></td>
   <td valign="top" background="<%=request.getContextPath()%>/cms_res/img/login/login_r2_c2.jpg">
   <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
     <tr>
       <td height="30" colspan="2" align="center">
			欢迎使用浙江大学江苏校友会网站后台管理系统	   </td>
       </tr>
     <tr>
       <td width="30%" height="30" align="right" class="f12-white">用户名：</td>
       <td width="70%">
       <html:text property="adminInfoDomain.name"  maxlength="16" size="15"  onfocus="this.select();" /></td>
     </tr>
     <tr>
       <td height="30" align="right" class="f12-white">密　码：</td>
       <td>
       <html:password  property="adminInfoDomain.password"   maxlength="16" size="15" onfocus="this.select();" /></td>
     </tr>
     <tr>
       <td height="35">&nbsp;</td>
       <td>&nbsp;</td>
     </tr>
     <tr>
       <td height="30" colspan="2" align="center">
       <a href="javascript:check();">
	   <img src="<%=request.getContextPath()%>/cms_res/img/login/login.gif" /> </a>&nbsp;
	   </td>
       </tr>
   </table>
   </td>
   <td><img src="<%=request.getContextPath()%>/cms_res/img/login/login_r2_c3.jpg" /></td>
  </tr>
</table>
 </html:form>
</body>
</html>