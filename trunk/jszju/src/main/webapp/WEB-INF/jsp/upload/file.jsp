<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/js.jsp"%>
<%@ include file="/WEB-INF/jsp/share/css.jsp"%>
<%@ include file="/WEB-INF/jsp/showDialog.jsp"%>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>
<html>
	<head>
		<script src="<%=request.getContextPath()%>/front_res/jquery.js"
			type="text/javascript"></script>
		<link href="<%=request.getContextPath()%>/front_res/front.css"
			type="text/css" rel="stylesheet" />
		<link
			href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/whole.css"
			type="text/css" rel="stylesheet" />
		<link
			href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/layout.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript">
function check()
{
	var str = document.forms[0].file1.value;
	var arr=str.split(".");
	if(arr[1]=="jpg" | arr[1]=="gif" | arr[1]=="png" |arr[1]=="jpeg"  ){
		document.forms[0].submit();
	}
	else
		alert("文件格式不对，请上传图片文件！");
}
</script>


	</head>
	<body style="background-color: #ffffff;">
		<html:form action="/file.do" method="post"
			enctype="multipart/form-data">
			<table align="center" cellspacing="3px"
				style="border: 1px solid #cccccc;">
				<tbody align="right">
					<tr>
						<td align="center" colspan="2" height="10"></td>
					</tr>
					<tr>
						<td align="right" width="31%" height="30">
							图片标题：
						</td>
						<td align="left" width="69%" style="">
							<html:text property="title" value="title" title="标题不能为空"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right" height="30">
							选择图片：
						</td>
						<td align="left">
							<html:file property="file1"></html:file>
						</td>
					</tr>
					<tr>
						<td align="right" height="30">
							选择模块：
						</td>
						<td align="left">
							<html:radio property="dep" value="1">校友风采</html:radio>
							<html:radio property="dep" value="2">焦点图片</html:radio>
							<html:radio property="dep" value="3">杰出校友</html:radio>
						</td>
					</tr>
					<tr>
						<td align="right" height="30">
							是否显示：
						</td>
						<td align="left">
							<html:radio property="display" value="1">显示</html:radio>
							<html:radio property="display" value="0">不显示</html:radio>
						</td>
					</tr>
					<tr>
						<td align="right" width="31%" height="30">
							<span>内容：</span>
							</td>
							<td align="left">
							<html:textarea property="discrption"></html:textarea>
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" value="上传" onclick="check()" align="right" />
						</td>
					</tr>
				</tbody>
			</table>

		</html:form>
	</body>
</html>
