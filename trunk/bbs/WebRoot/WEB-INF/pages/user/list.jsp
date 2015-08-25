<%@ page language="java" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>用户列表</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
	</head>
	<body style="margin: 10px;">

		<!--显示分页有关信息 -->
		<%@ include file="/WEB-INF/pages/public/pagination.jspf" %>
		<script type="text/javascript">
			function gotoPage(pageNum){
				window.location.href = "?method=list&pageNum=" + pageNum;
			}
		</script>

		<html:form action="/admin/user?method=list">
			<table class="list">
				<tr>
					<th> ID </th>
					<th> 会员登陆名 </th>
					<th> 会员昵称 </th>
					<th> 所属群组 </th>
					<th> 锁定状态 </th>
					<th> 相关操作 </th>
				</tr>

				<c:forEach items="${page.items }" var="user">
					<tr>
						<td> ${user.id } </td>
						<td> ${user.loginName } </td>
						<td> ${user.nickname } </td>
						<td> <c:forEach items="${user.groups }" var="group" varStatus="status">
								${group.name } ${status.last ? '' : ', '}
							</c:forEach> </td>
						<td> <font color="red">${user.active ? '' : '锁定'}</font> </td>
						<td> <html:link action='/admin/user?method=changeGroupsUI&id=${user.id}&page=${param.pageNum }'>修改群组</html:link>
							<html:link action="/admin/user?method=lock&id=${user.id }&page=${param.pageNum}">锁定</html:link>
							<html:link action="/admin/user?method=unlock&id=${user.id }&pageNum=${param.pageNum}">解除锁定</html:link></td>
					</tr>
				</c:forEach>

			</table>
		</html:form>

		<strong>超级管理员与匿名用户不能被锁定</strong>

		<!-- jqmWidown div -->
		<%--@ include file="/WEB-INF/pages/public/jqmWindowDiv.jspf"--%>

	</body>
</html>
