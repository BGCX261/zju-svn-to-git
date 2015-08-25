<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>角色列表</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<link rel="stylesheet" type="text/css" href="${baseUrl }/style/itcastbbs_admin.css">
		
		<script type="text/javascript" src="${baseUrl }/script/admin/role.js"></script>
		<script type="text/javascript">
		<!--
		function addRoleUI(){
			window.location.href = "<html:rewrite action='/admin/role?method=addUI'/>";
		}
		//-->
		</script>
	</head>
	<body style="margin: 10px;">

		<!-- 显示错误(如果有) -->
		<%@include file="/WEB-INF/pages/public/showErrors.jspf"%>
	
		<table class="list">
	        <tr align="center" class="title">
	            <th>名称</th>
	            <th>描述</th>
	            <th>上级角色</th>
	            <th>相关操作</th>
	        </tr>
			<c:forEach items="${roles }" var="role">
				<tr>
					<td>${role.name }</td>
					<td>${role.description }</td>
					<td>${role.parent.name }&nbsp;</td>
					<td><html:link action="/admin/role?method=editUI&id=${role.id}">编辑</html:link>
						<html:link action="/admin/role?method=del&id=${role.id }"
							onclick="return deleteRole('${role.name }')">删除</html:link>
					</td>
				</tr>
			</c:forEach>
	        <tr>
	            <td align="right" colspan="4"><input type="button" onclick="addRoleUI()" value="添 加"></td>
	        </tr>
	    </table>

	</body>
</html>
