<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
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
	
	setTimeout(transferAuto,3000);
	function transferAuto(){
		history.back();
	}
</script>
</head>

<body>
  <html:form  action="/userLogin.do" method="post">
   <html:hidden property="actionType"/>
   </html:form>
<table width="98%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="40" colspan="2" align="center" class="f12b-red">提示信息:验证码错误！</td>
  </tr>  
  <tr>
    <td height="30" colspan="2" align="center">
	3秒后自动回到登陆界面。<br/>或者点击
	<label style="color:red; cursor:hand;" onclick="transferAuto()">此处</label>重新登陆。
    </td>
  </tr>
</table>
</body>
</html>