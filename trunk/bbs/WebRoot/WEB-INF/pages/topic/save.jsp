<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>发表主题</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/front/article.js"></script>
	</head>
	<body>

		<!-- 顶部 -->
		<%@ include file="/WEB-INF/pages/public/header.jspf"%>

		<!-- 左侧菜单 -->
		<%@ include file="/WEB-INF/pages/public/leftmenu.jspf"%>

		<!-- 发表主题表单 -->
		<html:form action="/article" onsubmit="return validateTopicForm(this)">
			<html:hidden property="method" value="${articleForm.id gt 0 ? 'editTopic' : 'addTopic' }" />
			<html:hidden property="id" />
			<html:hidden property="forumId" />
			标题 <html:text property="title" />
			<br>
			<html:textarea property="content" />
			<%@ include file="/WEB-INF/pages/public/fckeditor.jspf"%>
			<html:image page="/images/button_publish.gif"></html:image>
		</html:form>

		<!--底部-->
		<%@ include file="/WEB-INF/pages/public/footer.jspf"%>
	</body>
</html>
