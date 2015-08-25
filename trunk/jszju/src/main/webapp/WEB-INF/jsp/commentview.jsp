<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<html>
	<head>

		<title>用户留言</title>
		<link
			href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/whole.css"
			type="text/css" rel="stylesheet" />
		<script src="<%=request.getContextPath()%>/front_res/jquery.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/front_res/front.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/layout.css" type="text/css" rel="stylesheet" />
	</head>

	<body style="background-color: #ffffff;">
	 <html:form action="/comment.do" method="post">
      <input type="hidden" id="commentArticleId" name="commentArticleId" />
      </html:form>
        <div class="list_content">
        <c:set var="count" value="0" />
        <c:forEach var="cmlist" items="${commentlist}">
        <c:if test="${count<6}">
        <c:set var="count" value="${count+1}" />
          <div class="msg_title">${ cmlist.userName}:</div>
          <div class="msg_title">${ cmlist.commentTime}:</div>
		  <div class="msg_content">
				 ${ cmlist.commentcontent}
		  </div>
		  </c:if>
		  </c:forEach>
        </div>
	</body>
</html>
