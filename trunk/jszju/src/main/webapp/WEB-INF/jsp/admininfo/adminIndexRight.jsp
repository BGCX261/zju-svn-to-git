<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/js.jsp"%>
<%@ include file="/WEB-INF/jsp/share/css.jsp"%>
<%@ include file="/WEB-INF/jsp/showDialog.jsp" %>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
<head>
<title>后台欢迎页-right</title>
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
<script src="/core_res/js/admin.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/fckeditor/fckeditor.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript"></script>
<script type="text/javascript">

function checkspace(checkstr) {
  var str = '';
  for(i = 0; i < checkstr.length; i++) {
    str = str + ' ';
  }
  return (str == checkstr);
}

function reset(it)
{
	document.forms[0].reset();
}

function check()
{
	 document.forms[0].actionType.value = "<%=Consts.ACTION_TYPE_RESETPASW%>";
	 document.forms[0].submit();
	 document.forms[0].reset();
}
</script>
</head>

<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 首页 - 个人资料</div>
	<div class="clear"></div>
</div>
<html:form action="/resetAdminPsw.do" method="post" >
<input type="hidden" name="actionType" />
   <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
     <tr>
       <td height="30" colspan="2" align="center">
			个人资料管理	   </td>
       </tr>
     <tr>
       <td width="30%" height="30" align="right" class="f12-white">用户名：${cookie.adminname.value }</td>
     </tr>
     <tr>
       <td height="35">&nbsp;</td>
       <td>&nbsp;</td>
     </tr>
   </table>
   </html:form>
</div>
</body>
</html>