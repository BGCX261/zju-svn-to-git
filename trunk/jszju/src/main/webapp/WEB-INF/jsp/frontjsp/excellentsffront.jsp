<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ page language="java" contentType="text/html;charset=utf-8"
		pageEncoding="utf-8"%>
	<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
	<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>
	<head>
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
  <div>
  <h5>求是群芳</h5>
					<div >
					<c:set var="count" value="0" />
				<c:forEach var="fList" items="${filelist}">
				<c:if test="${count<1}">
						<c:if test="${fList.display eq 1}">
						<c:if test="${fList.dep eq 3}">
							<c:set var="count" value="${count+1}" />
						<a href="${fList.linkaddress}"  target="_blank"><img
							src="<%=request.getContextPath()%>/upload/${fList.filename}"
							alt="${fList.title}" border="0" /> </a>
						<a href="${fList.linkaddress}"  target="_blank"> ${fList.discrption}</a>
							
						</c:if>
						</c:if>
						</c:if>
				</c:forEach>
						</div>
									</div>
  </body>
</html>
