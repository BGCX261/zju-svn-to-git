<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/pages/public/commons.jspf"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>搜索</title>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/datepicker/ui.datepicker.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/datepicker/zh-cn.js"> </script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery/datepicker/ui.datepicker.css">
	</head>

	<body>

		<!-- ~~~~~~~~~~~~~~~~~~~~~~ 顶部 ~~~~~~~~~~~~~~~~~~~~~~~ -->
		<%@ include file="/WEB-INF/pages/public/header.jspf"%>
		<!-- ~~~~~~~~~~~~~~~~~~~~ 顶部结束 ~~~~~~~~~~~~~~~~~~~~~~~ -->

		<div style="margin: auto 40px">
			<span><html:link action="/index">${settings.name } 论坛首页</html:link> </span> →
			<span>搜索文章</span>
		</div>

		<div style="margin: 50px; text-align: center">
			<html:form action="/search" method="GET">
				<html:hidden property="method" value="search" />
				<html:hidden property="pageNum" />

				<div>
					<html:text property="keywords" size="50"></html:text>
					<html:submit>搜索</html:submit>
				</div>
				<div>
					<html:radio property="matchType" value="0"></html:radio>
					帖子搜索
					<html:radio property="matchType" value="1"></html:radio>
					按作者搜索
				</div>

				<hr>

				<table>
					<tr>
						<td>
							<span style="height: 30px">在这些版面中查询 (可多选)</span>
							<br>
							<html:select property="forumIds" size="10" style="width: 200px" multiple="multiple" onchange="checkThisField(this)">
								<option selected="selected">
									所有版面
								</option>
								<c:forEach items="${categories}" var="c">
									<optgroup label="${c.name }">
										<c:forEach items="${c.forums}" var="f">
											<option value="${f.id }">
												${f.name }
											</option>
										</c:forEach>
									</optgroup>
								</c:forEach>
							</html:select>
						</td>
						<td valign="top">
							<span style="height: 30px">时间范围:</span>
							<br>
							开始时间
							<br>
							<html:text property="fromDate" styleId="datepicker1"></html:text>
							<br>
							结束时间
							<br>
							<html:text property="toDate" styleId="datepicker2"></html:text>
							
						<script type="text/javascript">
							$("#datepicker1").datepicker();
							$("#datepicker2").datepicker();
						</script>
						</td>
					</tr>
				</table>

			</html:form>
		</div>

		<div align="right">
			<!-- ~~~~~~~~~~~~~~~~~ 跳转到版面 ~~~~~~~~~~~~~~~~~~ -->
			<%@ include file="/WEB-INF/pages/public/selectJumpTo.jspf"%>
		</div>

		<!-- ~~~~~~~~~~~~~~~  底部  ~~~~~~~~~~~~~ -->
		<%@ include file="/WEB-INF/pages/public/bottom.jspf"%>
		<!-- ~~~~~~~~~~~~~~~  底部end  ~~~~~~~~~~~~~ -->

		<SCRIPT type="text/javascript">
		// 全部禁止不能和其它选项一起选中
		function checkThisField(field)
		{
			if (field.options[0].selected) {
				for (i = 1; i < field.length; i++) {
					field.options[i].selected = false;
				}
			} 
			else {
				field.options[0].selected = false;
			}
		
			if (field.selectedIndex == -1) {
				field.options[0].selected = true;
			}
		}
		
		// 至少要选择一项, 默认选择"全部禁止"
		form = document.forms[0];
		for (i = 0; i < form.length; i++) {
			if ((form.elements[i].type == "select-multiple") && (form.elements[i].selectedIndex == -1)) {
				form.elements[i].options[0].selected = true;
			}
		}
		</SCRIPT>
	</body>
</html>
