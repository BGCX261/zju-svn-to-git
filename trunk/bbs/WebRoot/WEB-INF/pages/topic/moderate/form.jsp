<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>文章管理</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript">
			function validateForm(form){
				var n = $("input[name=ids][checked]").size();
				if(n < 1){
					alert("请选择要操作的文章!");
					return false;
				}
				$("input[type=submit]").attr("disabled", "disabled");
				$("input[type=submit]").val("正在提交, 请稍候...");
				return true;
			}
		</script>
	</head>
	<body>

		<!-- 顶部 -->
		<%@ include file="/WEB-INF/pages/public/header.jspf"%>

		<html:form action="/moderate" onsubmit="return validateForm(this);">
			<html:hidden property="method" value="${action }"/>
			<input type="hidden" name="forumId" value="${param.forumId }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">

			<!-- ~~~ 要操作的主题列表 ~~~ -->
			<table>
				<tr align="center">
					<td> &nbsp; </td>
					<td width="50px"> id </td>
					<td> 标题 </td>
					<td> 作者 </td>
					<td> 发表时间 </td>
					<td> 回复数量 </td>
					<td> 所属版面 </td>
				</tr>

				<c:forEach items="${topics }" var="t">
					<tr>
						<td> <html:multibox property="ids" value="${t.id}"></html:multibox> </td>
						<td> ${t.id } </td>
						<td> <html:link action="/article?method=showTopic&id=${t.id }&page=1"> ${t.title}</html:link> </td>
						<td> <html:link action="/user?method=profile&id=${t.author.id }">${t.author.nickname }</html:link> </td>
						<td> <fmt:formatDate pattern="yyyy年MM月dd日, HH:mm:ss" value="${t.postTime }" /> </td>
						<td> ${t.replyCount } </td>
						<td> <html:link action="/forum?method=show&id=${t.forum.id }&page=1">${t.forum.name} </html:link> </td>
					</tr>
				</c:forEach>
			</table>
			<!-- ~~~~~~~~~  要操作的主题列end  ~~~~~~~~~~~~~~~~~~~~~~ -->

		
			<div style="margin: 10px auto;">
				<b>管理选项</b>
				<table>
				
				<%-- 
					<c:if test="${action eq 'moveTopics' }">--%>
						<tr>
							<td width="20%">
								选择目的版块:
							</td>
							<td>
								<html:select property="destForumId">
									<c:forEach items="${categories}" var="c">
										<optgroup label="${c.name }">
											<c:forEach items="${c.forums}" var="f">
												<option value="${f.id }" ${f.id eq param.forumId ? 'selected="selected"' : ''}>
													${f.name }
												</option>
											</c:forEach>
										</optgroup>
									</c:forEach>
								</html:select>
							</td>
						</tr>
					<%-- </c:if> --%>
	
					<tr>
						<td>
							操作原因:
						</td>
						<td>
							<select multiple="multiple" onchange="this.form.reason.value=this.value" size="8">
								<option value=""> 自定义 </option>
								<option> ------ </option>
								<option value="广告帖"> 广告帖 </option>
								<option value="恶意灌水"> 恶意灌水 </option>
								<option value="非法内容"> 非法内容 </option>
								<option value="与版规不符"> 与版规不符 </option>
								<option value="重复发帖"> 重复发帖 </option>
							</select>
							<html:textarea property="reason" rows="8" cols="60"></html:textarea>
						</td>
					</tr>
				</table>
				
				<html:submit>提交</html:submit>
			</div>
		</html:form>
		
		<!--底部-->
		<%@ include file="/WEB-INF/pages/public/footer.jspf"%>
	</body>
</html>
