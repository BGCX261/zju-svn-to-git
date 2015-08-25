<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<title>添加/修改 分类信息</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/admin/category_forum.js"></script>
	</head>
	<body>

		<!-- 显示错误(如果有) -->
		<%@include file="/WEB-INF/pages/public/showErrors.jspf"%>

		<html:form focus="name" action="/admin/category" onsubmit="return validateCategoryForm(this)">
			<html:hidden property="method" value="save" />
			<html:hidden property="id" />
		    <table class="form">
		        <tr><td colspan="3" class="title">添加新分类</td></tr>
		        <tr>
		            <td>分类名称</td>
		            <td><html:text property="name" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr><td align="center" colspan="3"><html:submit value="提 交" /></td></tr>
		    </table>
		</html:form>
	</body>
</html>
