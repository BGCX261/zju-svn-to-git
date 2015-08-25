<%@ page language="java" pageEncoding="utf-8"%>

<html>
	<head>
		<title>添加附件</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/front/attachment.js"></script>
		<script type="text/javascript">
	        var attachmentLimit = ${settings.attachmentMaxAmount };// 最多允许的附件的数量
	        $(document).ready(function () {
	            for (var i = 0; i < attachmentLimit; i++) {
	                addAttachmentField();
	            }
	            $("#btnAddFile").attr("disabled", "disabled");
	        });
	    </script>
	</head>
	<body>
		<!-- 顶部 -->
		<%@ include file="/WEB-INF/pages/public/header.jspf"%>

		<!-- 左侧菜单 -->
		<%@ include file="/WEB-INF/pages/public/leftmenu.jspf"%>
   
		<html:form action="/attachment" onsubmit="return validateAttachmentForm(this)" enctype="multipart/form-data">
			<html:hidden property="pageNum" value="${param.pageNum}" />
			<html:hidden property="topicId" value="${param.topicId}" />
			<html:hidden property="method" value="add" />
			<html:hidden property="articleId" />
			
			<table class="form" style="width: 600px;">
		        <tr>
		            <td colspan="3" class="title">添加附件</td>
		        </tr>
		        <tr>
		            <td>说明</td>
		            <td>1，没有选择文件表示没有附件<br>
		                2，文件的大小不能超过1M<br>
		                3，一次最多只能添加3个附件<br>
		            </td>
		        </tr>
		        <tr>
		            <td colspan="3" align="right" class="border_top">
		                <input type="button" id="btnAddFile" value="新增附件" onclick="addAttachmentField()">
		            </td>
		        </tr>
		        <tbody id="attachmentFields"></tbody>
		        <tr>
		            <td align="center" colspan="3"><html:submit value="提 交" /></td>
		        </tr>
		    </table>
		</html:form>

		<!--底部-->
		<%@ include file="/WEB-INF/pages/public/footer.jspf"%>
	</body>
</html>