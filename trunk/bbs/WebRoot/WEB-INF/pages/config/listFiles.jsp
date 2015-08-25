<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<title>配置文件管理</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
	</head>
	<body  style="margin: 10px;">

		<div>
			<b>配置文件</b>
			<table class="list">
				<tr>
					<td>
						文件名
					</td>
					<td>
						文件路径
					</td>
				</tr>

				<c:forEach items="${files}" var="file">
					<tr>
						<td>
							<font color="blue">${file.name }</font>
						</td>
						<td>
							${file.path }
						</td>
					</tr>
				</c:forEach>

				<tr>
					<td colspan="2" align="right">
						<html:link action="/admin/config?method=reloadFiles">
							重新加载配置文件</html:link>
					</td>
				</tr>

			</table>
		</div>

	</body>
</html>