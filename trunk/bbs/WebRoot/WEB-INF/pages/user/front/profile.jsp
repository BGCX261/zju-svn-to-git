<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
 
<html>
	<head>
		<title>${user.nickname }的个人资料</title>
	</head>
	<body>

		<!-- ~~~~~~~~~~~~~~~~~~~~~~ 顶部 ~~~~~~~~~~~~~~~~~~~~~~~ -->
		<%@ include file="/WEB-INF/pages/public/header.jspf"%>

		<div style="margin: auto 30px;">
			<html:link action="/index">${settings.name } 论坛首页</html:link> → 
			用户信息
		</div>

		<div style="margin: 30px">
			<div style="text-align: center; font-size: 16px"><b>浏览 [${user.nickname }] 的个人资料</b></div>

			<div style="float: left; width: ${settings.avatarMaxWidth + 10 }px;">
				头像<br>
				<div style="border: 1px solid orange; width: ${settings.avatarMaxWidth }px; height: ${settings.avatarMaxHeight }px">
					<c:if test="${not empty user.avatar }">
						<html:img page="/upload/avatars/${user.avatar }" />
					</c:if>
				</div>
				<hr>
				状态:
				<html:img page="/images/common/${user.online ? 'icon_online' : 'icon_offline' }.gif" />
			</div>

			<div>
				<b>关于 ${user.nickname }</b>
				<hr>
				加入时间:
				<fmt:formatDate type="date" pattern="yyyy年MM月dd日" value="${user.registerTime }" />
				<br>
				发表主题: ${user.topicCount }
				<br>
				总文章数: ${user.articleCount }
				<br>
				查询:
				<html:link action="/search?method=search&matchType=1" paramId="keywords"  paramName="user" paramProperty="nickname"> 查找 ${user.nickname } 发表的所有文章</html:link>
				<br>
				<br>
				来自: ${user.comeFrom }
				<br>
				性别: ${user.sex eq 0 ? '保密' : (user.sex eq 1 ? '男' : '女') }
				<br>
				生日: <fmt:formatDate type="date" pattern="yyyy年MM月dd日" value="${user.birthday }" />
			</div>

		</div>

		<div style="float: right">
			<!-- ~~~~~~~~~~~~ 跳转到版面 ~~~~~~~~~~~~~ -->
			<%@ include file="/WEB-INF/pages/public/selectJumpTo.jspf"%>
		</div>

		<br>
		<br>
		<br>

			<!-- ~~~~~~~~~~~~~~~  底部  ~~~~~~~~~~~~~ -->
			<%@ include file="/WEB-INF/pages/public/bottom.jspf"%>
	</BODY>
</HTML>

