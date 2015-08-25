<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'save.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <label>
            <span>用户ID：</span>
	        ${ domain.identifyno}
   </label>
        <label>
        	<span>用户名：</span>
            ${ domain.name}
        </label>  
        <label>
        	<span>年龄：</span>
        	${domain.age }
        </label>
         <label>
        	<span>性别：</span>
        	${domain.sex}
        </label>
  </body>
</html>
