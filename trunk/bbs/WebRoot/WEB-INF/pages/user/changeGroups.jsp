<%@ page language="java" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>更新用户所属的群组</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
	</head>
	<body style="margin: 10px;">
		<html:form action="/admin/user?method=changeGroups">
			<html:hidden property="id" />
			<html:hidden property="pageNum" value="${param.pageNum }" />
			<table class="form">
				<tr><td>请选择 "${user.nickname }" 所属的组</td></tr>
				<tr><td class="border_top"">
						<html:select property="groupIds" multiple="multiple" style="width: 200px;" size="10">
							<html:optionsCollection name="groups" value="id" label="name" />
						</html:select>
					</td>
				</tr>
				<tr><td align="center"><html:submit>提 交</html:submit></td></tr>
			</table>
		</html:form>
	</body>
</html>