<%@ page language="java" pageEncoding="utf-8"%>

<html>
	<head>
		<title>版面列表</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/admin/category_forum.js"></script>
		<script type="text/javascript">
		function addCategory(){
			window.location="<html:rewrite action='/admin/category?method=saveUI' />";
		}
		</script>
	</head>
	<body style="margin: 10px;">

		<!-- 显示错误(如果有) -->
		<%@include file="/WEB-INF/pages/public/showErrors.jspf"%>

		<table class="list">
		    <tr align="center" class="title">
		        <th>名称</th>
		        <th>主题数</th>
		        <th>文章数</th>
		        <th>相关操作</th>
		    </tr>
		    
		    <c:forEach items="${categories }" var="category">
			    <tr>
			        <td colspan="3" style="color: blue; font-weight: bold;">${category.name }</td>
			        <td><html:link action="/admin/category?method=saveUI&id=${category.id}">编辑</html:link>
						<html:link action="/admin/category?method=del&id=${category.id }"
							onclick="return deleteCategory('${ccategoryname }', ${empty category.forums })">删除</html:link>
						<html:link action="/admin/category?method=changeOrder&id=${category.id }&isUp=true">上移</html:link>
						<html:link action="/admin/category?method=changeOrder&id=${category.id }&isUp=false">下移</html:link>
						<html:link action="/admin/forum?method=addUI&categoryId=${category.id }">添加子版面</html:link>
			        </td>
			    </tr>
			    <c:forEach items="${category.forums }" var="forum">
				    <tr>
				        <td>${forum.name }<br>
				                       描述: ${forum.description }
				        </td>
				        <td>${forum.topicCount }</td>
				        <td>${fforum.articleCount }</td>
				        <td><html:link action="/admin/forum?method=editUI&id=${forum.id}">编辑</html:link>
							<html:link onclick="return deleteForum('${forum.name }', ${forum.topicCount }, ${forum.articleCount })"
								action="/admin/forum?method=del&id=${forum.id }">删除</html:link>
							<html:link action="/admin/forum?method=changeOrder&id=${forum.id }&isUp=true">上移</html:link>
							<html:link action="/admin/forum?method=changeOrder&id=${forum.id }&isUp=false">下移</html:link>
				        </td>
				    </tr>
			    </c:forEach>
		    </c:forEach>
		    
		    <tr>
		        <td align="right" colspan="4">
		            <input type="button" onclick="addCategory()" value="新增分类">
		        </td>
		    </tr>
		</table>

	</body>
</html>
