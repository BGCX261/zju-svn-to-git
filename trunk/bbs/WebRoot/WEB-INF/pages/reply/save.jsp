<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>发表回复</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/front/article.js"></script>
	</head>
	<body>

		<!-- 顶部 -->
		<%@ include file="/WEB-INF/pages/public/header.jspf"%>

		<!-- 左侧菜单 -->
		<%@ include file="/WEB-INF/pages/public/leftmenu.jspf"%>

		<!-- 发表回复表单 -->
		<html:form action="/article">
			<html:hidden property="method" value="${articleForm.id gt 0 ? 'editReply' : 'addReply' }" />
			<html:hidden property="totalPage" value="${param.totalPage}" />
			<html:hidden property="pageNum" value="${param.pageNum}" />
			<html:hidden property="topicId" />
			<html:hidden property="id" />

			<html:textarea property="content" />
			<%@ include file="/WEB-INF/pages/public/fckeditor.jspf"%>
			<br>

			<html:image page="/images/button_publish.gif"></html:image>
		</html:form>

		<!--底部-->
		<%@ include file="/WEB-INF/pages/public/footer.jspf"%>
	</body>
</html>
