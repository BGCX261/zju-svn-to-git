<%@ page language="java" pageEncoding="utf-8"%>

<html>
	<head>
		<title>修改附件信息</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/front/attachment.js"></script>
	</head>
	<body>
		<!-- 顶部 -->
		<%@ include file="/WEB-INF/pages/public/header.jspf"%>

		<!-- 左侧菜单 -->
		<%@ include file="/WEB-INF/pages/public/leftmenu.jspf"%>
   
		<html:form action="/attachment">
			<html:hidden property="method" value="edit" />
			<html:hidden property="topicId" value="${param.topicId}"/>
			<html:hidden property="pageNum" value="${param.pageNum}"/>
			<html:hidden property="id" />
			
			<html:text property="fileName" readonly="true"></html:text><br>
			<html:textarea property="description"></html:textarea>			
			<html:submit value="提 交" />
		</html:form>
		
		<small>只能修改附件的说明</small>
		
		<!--底部-->
		<%@ include file="/WEB-INF/pages/public/footer.jspf"%>
	</body>
</html>