<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>回复管理</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript">
			function validateForm(form){
				ItCast.form.submitting();
				return true;
			}
		</script>
	</head>
	<body>

		<!-- ~~~~~~~~~~~~~~~~~~~~~~ 顶部 ~~~~~~~~~~~~~~~~~~~~~~~ -->
		<%@ include file="/WEB-INF/pages/public/header.jspf"%>
		<!-- ~~~~~~~~~~~~~~~~~~~~ 顶部结束 ~~~~~~~~~~~~~~~~~~~~~~~ -->

		<html:form action="/moderate" onsubmit="return validateForm(this);">
			<html:hidden property="method" value="delReply"/>
			<input type="hidden" name="topicId" value="${reply.topic.id }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">

			<!-- ~~~~~~~~~~~~~~~~~~~~~  要操作的回复列表  ~~~~~~~~~~~~~~~~~~~~~~ -->
			<table width="100%" class="show_table">
				<tr align="center">
					<td width="50px"> id </td>
					<td> 所属主题 </td>
					<td> 所占楼层 </td>
					<td> 作者 </td>
					<td> 发表时间 </td>
				</tr>
				<tr align="center">
					<td><html:hidden property="id" value="${reply.id }"/> ${reply.id }</td>
					<td align="left">${reply.topic.title}</td>
					<td>${reply.floor }</td>
					<td><html:link action="/user?method=profile&id=${reply.author.id }">${reply.author.nickname }</html:link> </td>
					<td><fmt:formatDate type="both" pattern="yyyy年MM月dd日, HH:mm:ss" value="${reply.postTime }" /> </td>
				</tr>
			</table>
			<!-- ~~~~~~~~~~~~~~~~~~~~  列表end  ~~~~~~~~~~~~~~~~~~~~~~ -->

			<div style="margin: 10px auto;">
				<b>管理选项</b>
				<table width="100%" class="show_table">
					<tr>
						<td> 操作原因: </td>
						<td>
							<select onchange="this.form.reason.value=this.value" size="8">
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
	
					<tr align="center">
						<td colspan="2">
							<html:submit>提交</html:submit>
						</td>
					</tr>
				</table>
			</div>
		</html:form>
	
	</body>
</html>
