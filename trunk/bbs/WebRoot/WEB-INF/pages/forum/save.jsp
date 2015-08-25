<%@ page language="java" pageEncoding="utf-8" %>

<html>
	<head>
		<title>添加/修改 版面信息</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/admin/category_forum.js"></script>
	</head>
	<body>

		<!-- 显示错误(如果有) -->
		<%@include file="/WEB-INF/pages/public/showErrors.jspf"%>

		<html:form focus="name" action="/admin/forum" onsubmit="return validateForumForm(this)">
			<html:hidden property="method" value="${forumForm.id gt 0 ? 'edit' : 'add'}" />
			<html:hidden property="id" />
			<table class="form">
		        <tr><td colspan="3"  class="title">添加新版面</td></tr>
		        <tr>
		            <td>所属分类</td>
		            <td><html:select property="categoryId" styleClass="input1">
		                	<html:optionsCollection name="categories" value="id" label="name"/>
		                </html:select>
		            </td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>版面名称</td>
		            <td><html:text property="name" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>版面描述</td>
		            <td><html:textarea property="description" styleClass="textarea1"/></td>
		            <td>&nbsp;</td>
		        </tr>
		        <tr><td align="center" colspan="3"><input type="submit" value="提 交" ></td></tr>
		    </table>
		</html:form>
	</body>
</html>
