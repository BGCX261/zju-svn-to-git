<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>增加投票</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/front/vote.js"></script>
		<script type="text/javascript">
	        $(document).ready(function () {
	        	$("input[name=closeTime]").datepicker();

				<%-- 回显上次(验证失败之前)的错误输入 --%>
				<c:forEach items="${voteForm.items }" var="item">
					addVoteItem();
					$("input[name=items]:last").val("${item }");
				</c:forEach>
				
				for(i = voteCounter; i < 5; i++){ 
					addVoteItem(); 
				}
	        });
	    </script>
	</head>
	<body>
		<!-- 顶部 -->
		<%@ include file="/WEB-INF/pages/public/header.jspf"%>

		<!-- 左侧菜单 -->
		<%@ include file="/WEB-INF/pages/public/leftmenu.jspf"%>

		<html:form action="/vote" onsubmit="return validateVoteForm(this);">
			<html:hidden property="method" value="add" />
			<html:hidden property="topicId" />
			
		    <table class="form" style="width: 600px;">
		        <tr>
		            <td colspan="2" class="title">添加投票</td>
		        </tr>
		        <tr>
		            <td>说明</td>
		            <td>1，空白的投票选项内容是无效的<br>
		                2，应至少有2个有效的投票选项<br>
		                3，到达投票关闭时间后，此投票将被关闭，任何人不可再对此进行投票<br>
		            </td>
		        </tr>
		        <tr>
		            <td class="border_top">投票主题</td>
		            <td class="border_top"><html:text property="title" styleClass="input1"/></td>
		        </tr>
		        <tr>
		            <td>单选或多选</td>
		            <td>
		            	<html:radio property="multiple" value="false">单选</html:radio>
						<html:radio property="multiple" value="true">多选</html:radio>
		            </td>
		        </tr>
		
		        <tr>
		            <td>投票结束时间</td>
		            <td>
		                <html:text property="closeTime" />
		                <span class="desc">(点击文本框选择日期)</span>
		            </td>
		        </tr>
		
		        <tr>
		            <td class="border_top">投票选项</td>
		            <td align="right" class="border_top">
		                <input type="button" onclick="addVoteItem()" value="新增投票选项" style="padding-right: 50px;">
		            </td>
		        </tr>
		
		        <tbody id="voteItemFields"></tbody>
		
		        <tr>
		            <td align="center" colspan="3"><html:submit value="提 交"/></td>
		        </tr>
		    </table>
			<html:submit>提交</html:submit>
		</html:form>

		<!--底部-->
		<%@ include file="/WEB-INF/pages/public/footer.jspf"%>
	</body>
</html>
