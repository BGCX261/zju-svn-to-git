<%@ page language="java" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>搜索管理</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/admin/search.js"> </script>
	</head>
	<body style="margin: 10px;">

		索引位置: ${settings.luceneIndexPath }
		<br>
		数据库中总文章数: ${totalArticles }
		<br>
		索引中文件数: ${totalIndexDocs }

		<html:form action="/admin/search" onsubmit="return validateRecreateIndicesForm(this);">
			<html:hidden property="method" value="recreateIndices" />
			<table class="form">
				<tr class="title">
					<td colspan="2">重建索引</td>
				</tr>
				<tr><td>参数</td>
					<td><html:radio property="recreateAll" value="true">
							建立一个全新的索引数据库, 这样将会删除所有存在的索引纪录
						</html:radio><br>
						<html:radio property="recreateAll" value="false">
							指定文章发表的时间范围,创建或更新索引.
						</html:radio></td>
				</tr>
				<tr><td class="border_top">文章发表时间</td>				
					<td class="border_top">
						<html:text property="startDate" /> 至 <html:text property="endDate" />
					</td>
				</tr>
				<tr><td colspan="2" align="center"><html:submit>开 始</html:submit></td></tr>
			</table>
		</html:form>

		<script type="text/javascript">
			$("input[name=startDate]").datepicker();
			$("input[name=endDate]").datepicker();
		</script>
	</body>
</html>
