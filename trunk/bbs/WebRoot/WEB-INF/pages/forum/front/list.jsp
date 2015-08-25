<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>江苏省浙江大学校友会论坛首页</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<link rel="stylesheet" type="text/css" href="${baseUrl }/style/itcastbbs_forum_list.css">
	</head>
	<body>

		<!-- 顶部 -->
		<%@ include file="/WEB-INF/pages/public/header.jspf"%>
		
		<div class="portal">
			<!-- 最新推荐文章 -->
			<fieldset>
				<legend>最新推荐主题</legend>
				<ul><c:forEach items="${recommendationTopics }" var="t">
						<li class="cutTail">・<html:link action="/article?method=showTopic&id=${t.id }&pageNum=1"
							styleClass="cutTail">${t.title}</html:link></li>
					</c:forEach></ul>
			</fieldset>

			<!-- 分类与版面列表 -->
			<c:forEach items="${categories }" var="category">
			<div class="sort">
				<!-- 分类名称 -->
				<h2>${category.name }</h2>
				
				<!-- 版面列表 -->
				<div class="mainbox forum_list">
				<table>
					<!-- 版面列表头 -->
					<thead><tr>
						<th class="name">版块</th>
						<td class="nums">主题</td>
						<td class="nums">帖数</td>
						<td class="lastPost">最后发表</td></tr>
					</thead>
					
					<!-- 版面显示 -->
					<c:forEach items="${category.forums }" var="f" varStatus="state">
					<tbody><tr>
						<th class="name">
							<h3><html:link action="/forum?method=show&id=${f.id }&pageNum=1">${f.name }</html:link></h3>
							<!-- 版面描述 -->
							<h5>${f.description }</h5></th>
						<td class="nums">${f.topicCount }</td>
						<td class="nums">${f.articleCount }</td>
						<td class="lastPost">
							<c:if test="${not empty f.lastTopic}">
								<html:link action="/article?method=showTopic&id=${f.lastTopic.id }&pageNum=1">
									${f.lastTopic.title}
								</html:link>
								<cite>By ${f.lastTopic.author.nickname }
								- <fmt:formatDate type="both" pattern="yy年MM月dd日 HH:mm" value="${f.lastTopic.postTime }" /> </cite>
							</c:if>&nbsp;</td>
					</tr></tbody>
					</c:forEach>
				</table></div>
			</div>
			</c:forEach>

			<div class="sort">
				<h2>当前状态</h2>
				<ul><li> 共有 ${topicCount } 个主题 </li>
					<li> 共有 ${articleCount } 篇文章 </li>
					<li> 共有 ${userCount } 位注册会员 </li>
					<li> 目前共有 ${onlineCount } 位朋友在线, 其中有${fn:length(OnlineUsers) }位会员</li>
				</ul>
			</div>
		</div>

		<!-- 底部 -->
		<%@ include file="/WEB-INF/pages/public/footer.jspf"%>
	</body>
</html>
