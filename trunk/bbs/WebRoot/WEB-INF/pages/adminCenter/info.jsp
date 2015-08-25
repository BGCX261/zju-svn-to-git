<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<title>系统状态</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
	</head>
	<body style="margin: 10px;">
		
		<table class="list">
			<tr><td>版本信息:</td>
				<td>${settings.version }</td>
			</tr>
			<tr><td>论坛统计信息</td>
				<td>共有 ${topicCount } 个主题, 共有 ${articleCount } 篇文章, 共有 ${userCount } 位注册会员.
					<br>
					目前共有 ${onlineCount } 位朋友在线, 其中有 ${fn:length(onlineUsers) } 位会员.
				</td>
			</tr>
			<tr><td>当前在线会员</td>
				<td><c:forEach items="${onlineUsers }" var="user">
					<html:link action="/user?method=profile&id=${user.id}" >${user.nickname }</html:link>
					</c:forEach>
				</td>
			</tr>
		</table>
		
		<br>
		
		<strong>看谁在线上</strong>
		<table class="list">
			<tr>
				<th> 会员登陆名 </th>
				<th> 会员昵称 </th>
				<th> 最后访问时间 </th>
				<th> 最后一次访问所使用的IP地址 </th>
			</tr>
			<c:forEach items="${onlineUsers}" var="user">
				<tr>
					<td> ${user.loginName} </td>
					<td> <html:link action="/user?method=profile&id=${user.id }">${user.nickname }</html:link> </td>
					<td> <fmt:formatDate value="${user.lastVisitTime }" pattern="yyyy年MM月dd日,HH:mm:ss" /> </td>
					<td> ${user.lastVisitIpAddr } </td>
				</tr>
			</c:forEach>
		</table>

	</body>
</html>