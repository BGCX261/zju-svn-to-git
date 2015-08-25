<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userLoginSuccess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/form.css" type="text/css" rel="stylesheet" />
 </head>
  
  <body style="padding-top:0px;" topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">
  <table style="width:210px;" cellspacing="0" cellpadding="0"  align="center" class="form_table">
        <tbody>
          <tr>
            <td align="center" class="form_title" colspan="2" height="10">
            	提示信息
            </td>
          </tr>
          <tr height="60">
          	<td valign="middle" align="center">
          		成功退出
          	</td>
          </tr>         
        </tbody>
      </table>
  </body>
</html>
