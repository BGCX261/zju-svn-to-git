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
		<title>查看所有公告</title>
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
 <html:form action="/schoolFellowNotes.do" method="post" target="_self">
    <input type="hidden" name="actionType" />
    <input type="hidden" name="menuParam" value="${param.menuParam}" />
    </html:form>
		<div class="page_row">
			<div class="page_main_msg left">
				<div class="list_bar" style="width: 680px;">
					<a >首页</a> &gt;
					<a >校友会公告</a> &gt;
					<a >查看所有公告</a>
				</div>
				<div class="left_row">
					<img
						src="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/img/banner.jpg" />
				</div>
				<div class="left_row">
					<div class="list pic_news" style="margin-top: 5px">
						 <logic:notEmpty name="schoolFellowNotesListForm" property="resultList">
	            <logic:iterate id="domain" name="schoolFellowNotesListForm" property="resultList">
						<c:if test="${domain.isview eq 1}">
						<div class="list_content">
							<div class="c1-body">
								<div class="c1-bline" style="padding:3px 0px;">
									<div class="f-left">
										<img
											src="<%=request.getContextPath()%>/front_res/com_tag/head-mark4.gif"
											align="middle" class="img-vm" border="0" />
										<a href="<%=request.getContextPath()%>/linkToView.do?notesview=${domain.id}"
											target="_blank"> <span style="">${domain.title}</span>
											</a>
									</div>
									<div class="f-right">
										${domain.time}
									</div>
									<div class="clear"></div>
								</div>
							</div>
						</div>
						</c:if>
						</logic:iterate>
						</logic:notEmpty>
					<div class="tablePage">
	          		  <jsp:include page="/WEB-INF/jsp/share/paging.jsp" />
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
					<c:forEach var="dyList" items="${allnotesList}">
					<c:if test="${count1<15}">
					<c:set var="count1" value="${count1+1}" />
						<div class="list_content">
							<div class="c1-body">
								<div class="" style="padding: 2px 0px; text-align: left;">
									&middot;
									<a
										href="<%=request.getContextPath()%>/linkToView.do?actionType=${dyList.id}"
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
