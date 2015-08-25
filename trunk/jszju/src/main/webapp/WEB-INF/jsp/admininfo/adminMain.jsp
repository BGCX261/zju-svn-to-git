<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'adminMain.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  

 <frameset cols="170,*" frameborder="0" border="0" framespacing="0">
	<frame src="<%=request.getContextPath()%>/adminIndex.do?actionType=left" name="leftFrame" noresize="noresize" id="leftFrame" />
	<frame src="<%=request.getContextPath()%>/adminIndex.do?actionType=right" name="rightFrame" id="rightFrame" />
</frameset>
<noframes><body></body></noframes>
</html>
