<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/pages/public/commons.jspf"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>搜索结果</title>
		<style type="text/css">
</style>
	</head>

	<body>

		<!-- ~~~~~~~~~~~~~~~~~~~~~~ 顶部 ~~~~~~~~~~~~~~~~~~~~~~~ -->
		<%@ include file="/WEB-INF/pages/public/header.jspf"%>
		<!-- ~~~~~~~~~~~~~~~~~~~~ 顶部结束 ~~~~~~~~~~~~~~~~~~~~~~~ -->

		<div style="margin: auto 40px">
			<span><html:link action="/index">${settings.name } 论坛首页</html:link> </span> →
			<span>搜索结果</span>
		</div>

		<div style="margin: 20px 40px">
			<c:choose>
				<c:when test="${page.totalItems eq 0 }">
					<!-- 搜索结果为0条记录 -->
					没有找到结果. 点击
					<a href="#" onclick="research()">这里</a>
					重新搜索.
					<script type="text/javascript">
					function research(){
						var url = '<html:rewrite action="/search?method=form"/>';
						url += "&matchType=${searchForm.matchType}";
						url += "&keywords=" + encodeURIComponent("${searchForm.keywords}");
						url += "&fromDate=${searchForm.fromDate}";
						url += "&toDate=${searchForm.toDate}";
						window.location.href = url;
					}
					</script>
				</c:when>

				<c:otherwise>
					共有
					<b>${page.totalItems }</b> 条资料符合您搜索的条件
				</c:otherwise>
			</c:choose>
		</div>

		<div style="text-align: center">
			<html:form action="/search" method="GET">
				<html:hidden property="method" value="search" />
				<html:hidden property="pageNum" />
				<div>
					<html:text property="keywords" size="50"></html:text>
					<html:submit>搜索</html:submit>
				</div>
			</html:form>
		</div>

		<!-- ~~~~~~~~~~~~~~~~~~~~~~~~ 显示结果 ~~~~~~~~~~~~~~~~~~~~~ -->

		<!-- 搜索结果多于0条记录 -->
		<c:if test="${page.totalItems gt 0}">
			<div style="margin-right: 40px;">
				<!-- 显示分页有关信息 -->
				<%@ include file="/WEB-INF/pages/public/pagination.jspf"%>
			</div>
			
			<c:forEach items="${page.items }" var="p">
				<div class="show_box">
					<div style="font-size: 12px">
						<html:img page="/images/common/icon_minipost.gif" />
						标题:
						<html:link action="/topic/view?method=show&topicId=${p.topicId }&postId=${p.id }" style="margin-right: 10px">${p.title}</html:link>
						作者:
						<html:link action="/user/switch?method=profile&id=${p.authorId }" style="margin-right: 10px">${p.authorNickname }</html:link>
						发表时间:
						<span style="margin-right: 10px"><fmt:formatDate type="both" pattern="yyyy年MM月dd日, HH:mm:ss" value="${p.postTime }" /> </span>
						<span>论坛:</span>
						<html:link action="/forum/view?method=show&id=${p.forumId }&page=1" style="margin-right: 10px">${p.forumName }</html:link>
					</div>
					<hr>
					<div>
						${p.content }
					</div>
				</div>
			</c:forEach>

			<!-- 显示分页有关信息 -->
			<div style="margin-right: 40px;">
				<%@ include file="/WEB-INF/pages/public/pagination.jspf"%>
				<script type="text/javascript">
					function gotoPage(pageNum){
						document.forms[0].pageNum.value = pageNum;
						document.forms[0].submit();
					}
				</script>
			</div>
		</c:if>

	</body>
</html>
