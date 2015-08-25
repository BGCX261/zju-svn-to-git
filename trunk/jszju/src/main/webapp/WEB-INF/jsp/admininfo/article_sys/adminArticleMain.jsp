<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'adminArticleMain.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
 <frameset cols="200,*" frameborder="0" border="0" framespacing="0">
	<frame src="<%=request.getContextPath()%>/adminArticle.do?actionType=left" name="articleLeftFrame" noresize="noresize" id="leftFrame" />
	<frame src="<%=request.getContextPath()%>/adminArticle.do?actionType=right" name="articleRightFrame" id="rightFrame" />
</frameset>
<noframes><body></body></noframes>
</html>
