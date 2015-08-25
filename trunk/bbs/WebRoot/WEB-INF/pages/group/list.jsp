<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>组列表</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/admin/group.js"></script>
		<script type="text/javascript">
		<!--
		function addGroupUI(){
			window.location.href = "<html:rewrite action='/admin/group?method=saveUI'/>";
		}
		//-->
		</script>
	</head>
	<body style="margin: 10px;">

		<!-- 显示错误(如果有) -->
		<%@include file="/WEB-INF/pages/public/showErrors.jspf"%>

		<table class="list">
		    <tr align="center" class="title">
		        <th>群组名称</th>
		        <th>群组描述</th>
		        <th>相关操作</th>
		    </tr>
		   
		    <c:forEach items="${groups }" var="group">
				<tr><td>${group.name } </td>
					<td>${group.description } </td>
					<td><html:link action="/admin/group?method=saveUI&id=${group.id}">编辑</html:link>
						<html:link action="/admin/group?method=del&id=${group.id }" onclick="return deleteGroup('${group.name }')">删除</html:link>
					</td>
				</tr>
			</c:forEach>
		
		    <tr>
		        <td align="right" colspan="4"><input type="button" onclick="addGroupUI()" value="添加新群组"></td>
		    </tr>
		</table>
	</body>
</html>