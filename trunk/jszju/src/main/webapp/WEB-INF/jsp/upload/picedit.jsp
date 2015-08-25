<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/js.jsp"%>
<%@ include file="/WEB-INF/jsp/share/css.jsp"%>
<%@ include file="/WEB-INF/jsp/showDialog.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="fck"%>
<jsp:useBean id="fileHanderListForm" scope="session"
	type="com.jsict.jszju.form.FileHanderListForm" />
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script language="JavaScript" type=text/JavaScript>

     function doBack()
     {
     	 location.href='fileView.do?actionType=cancel';
     } 
     function doSave()
     {
     	document.forms[0].submit();
     }
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.validate.post.js"></script>

		<title>图片列表</title>
	</head>
	<body>

		<html:form action="/fileView.do" method="post"
			target="_self">
			<html:hidden property="actionType" />

			<div class="mainContent">

				<div class="warp">
					<span class="title">图片列表</span>
					<fieldset class="inputStyle3">
						<label>
							<span>图片标题：</span>
							<html:text property="entity.title" />
							&nbsp;&nbsp;*
						</label>
						<label>
							<span>是否显示：</span>
							<html:text property="entity.display" />
						</label>
						<label>
							<span>所在模块：</span>
							<html:text property="entity.dep" />
						</label>
						<label class="Triple">
							<span>链接地址：</span>
							<html:text property="entity.linkaddress" />
						</label>
						<label class="Triple">
							<span>图片内容：</span>
							<html:text property="entity.discrption" />
						</label>
					</fieldset>
				</div>

				<div class="bcC">
					<input value="保存" class='btn' type="button"
						onclick="if(!confirm('确认保存修改?'))return;doSave()" />
					&nbsp;&nbsp;
					<input value="返回" class='btn' type="button" onclick="doBack()" />
				</div>
			</div>
		</html:form>

	</body>
</html>