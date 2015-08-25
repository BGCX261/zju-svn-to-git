<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
	<%@ include file="/WEB-INF/jsp/share/js.jsp"%>
<%@ include file="/WEB-INF/jsp/share/css.jsp"%>
<%@ include file="/WEB-INF/jsp/showDialog.jsp" %>
	<%@ page language="java" contentType="text/html;charset=utf-8"
		pageEncoding="utf-8"%>
	<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>
	<%@ page import="com.jsict.jszju.action.MenuAction"%>
	<head>
		<title>查看图片</title>
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
		<script type="text/javascript">
       
</script>
	</head>
<body xmlns="">
<!--主体下Begin-->
 <html:form action="/fileView.do" method="post" target="_self">
    <input type="hidden" name="actionType" />
    </html:form>
		<div class="page_row">
			<div class="page_main_msg left">
				<div class="list_bar" style="width: 680px;">
					<a >首页</a> &gt;
					<a >查看图片</a>
				</div>
				<div class="left_row">
					<img
						src="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/img/banner.jpg" />
				</div>
				<div class="left_row">
					<div class="list pic_news" style="margin-top: 5px">
								<img src="${picentity.path}" />
								<div>
								${picentity.discrption }
								</div>
					</div>
				</div>
			</div>
			 
		<!--主体下End-->


<div class="right">
				<div class="list news_history">
					<div class="list_bar">
						// 以往新闻标题检索
					</div>
					<c:set var="count1" value="0" />
					<c:forEach var="dyList" items="${filelist}">
					<c:if test="${count1<15}">
					<c:set var="count1" value="${count1+1}" />
						<div class="list_content">
							<div class="c1-body">
								<div class="" style="padding: 2px 0px; text-align: left;">
									&middot;
									<a
										href="<%=request.getContextPath()%>/fileView.do?actionType=viewpiccontent&picviewId=${dyList.id}"
										target="_blank"><span style="">${dyList.title }</span>
									</a>
								</div>
							</div>
						</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
  <div style="clear: both"></div>
  </div>
</body>
</html>
