<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
	<%@ page language="java" contentType="text/html;charset=utf-8"
		pageEncoding="utf-8"%>
	<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>
	<%@ page import="com.jsict.jszju.action.MenuAction"%>
	<head>
		<title>浙江大学江苏校友会动态信息</title>
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
        function doClick(url)
        {
        	document.forms[0].actionType.value = '<%=MenuAction.CLICK%>';
            document.forms[0].url.value = url;
            document.forms[0].submit();
        }
       
</script>
	</head>
<body xmlns="">
<!--主体下Begin-->
		<div class="page_row">
			<div class="page_main_msg left">
				<div class="list_bar" style="width: 680px;">
					<a >首页</a> &gt;
					<a >文件通讯</a>
				</div>
				<div class="left_row">
					<img
						src="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/img/banner.jpg" />
				</div>
				<div class="left_row">
				<c:forEach var="menuList"
						items="${fileMenuForm.menuDomainList[1].menuList}">
					<div class="list pic_news" style="margin-top: 5px">
						<div class="list_bar">
						//<a href="<%=request.getContextPath()%>/viewAllArticle.do?channelId=${menuList.id }" target="_blank"> ${menuList.title }</a>
						</div>
						<c:set var="count" value="0" />
						 <c:forEach var="file" items="${fileList}">
        	 <c:if test="${menuList.id==file.channelId }">
        	 <c:if test="${count<6}">
								<c:set var="count" value="${count+1}" />
						<div class="list_content">
							<div class="c1-body">
								<div class="c1-bline" style="padding:6px 0px;">
									<div class="f-left">
										<img
											src="<%=request.getContextPath()%>/front_res/com_tag/head-mark4.gif"
											align="middle" class="img-vm" border="0" />
										<a href="<%=request.getContextPath()%>/linkToView.do?actionType=${file.id}"
											target="_blank"> <span style="">${file.title}</span>
											</a>
									</div>
									<div class="f-right">
										${file.inputData}
									</div>
									<div class="clear"></div>
								</div>
							</div>
						</div>
							</c:if>
							</c:if>
							</c:forEach>
					</div>
					</c:forEach>
				</div>
				
			</div>
		<!--主体下End-->


<div class="right">
				<div class="list news_history">
					<div class="list_bar">
						// 以往新闻标题检索
					</div>
					<c:set var="count1" value="0" />
					<c:forEach var="flist" items="${fileList}">
					<c:if test="${count1<15}">
					<c:set var="count1" value="${count1+1}" />
						<div class="list_content">
							<div class="c1-body">
								<div class="" style="padding: 2px 0px; text-align: left;">
									&middot;
									<a
										href="<%=request.getContextPath()%>/linkToView.do?actionType=${flist.id}"
										target="_blank"><span style="">${flist.title }</span>
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
