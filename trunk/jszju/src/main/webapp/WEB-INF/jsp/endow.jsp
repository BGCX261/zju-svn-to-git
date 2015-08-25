<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
  <head>
    
<title>捐赠信息</title>
<script src="<%=request.getContextPath()%>/front_res/jquery.js"
			type="text/javascript"></script>
		<link href="<%=request.getContextPath()%>/front_res/front.css"
			type="text/css" rel="stylesheet" />
		<link
			href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/whole.css"
			type="text/css" rel="stylesheet" />
		<link
			href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/layout.css"
			type="text/css" rel="stylesheet" />
</head>
  
  <body style="background-color:#ffffff;">
  <marquee scrollAmount=2 width=200 height=160 direction=up onmouseover=stop() onmouseout=start()>
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
		
		<c:forEach var="ednowList" items="${endowlist}" >
		<tr><td height="28">
		<div class="" style="padding:2px 0px;">
			<div class="f-left">
					<img src="<%=request.getContextPath()%>/front_res/com_tag/head-mark3.gif" align="middle" class="img-vm" border="0"/> 
							<span style="">${ednowList.name }：会费${ednowList.money }元&nbsp;&nbsp;捐赠${ednowList.contribute }元</span>
			</div>
		<div class="clear">
	</div>
</div>
		</td></tr>
		</c:forEach>
		</table>
		</marquee> 
  </body>
</html>
