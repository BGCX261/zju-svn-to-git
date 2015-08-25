<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/js.jsp"%>
<%@ include file="/WEB-INF/jsp/share/css.jsp"%>
<%@ include file="/WEB-INF/jsp/showDialog.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="fck"%>
<jsp:useBean id="adminInfoForm" scope="session"
	type="com.jsict.jszju.form.AdminInfoForm" />
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script language="JavaScript" type=text/JavaScript>

     function doBack()
     {
     	 location.href='admininfoRef.do?actionType=cancel';
     } 
     function doSave()
     {
     if(document.getElementsByName("adminInfoDomain.name")[0].value=="")
     	{
     		alert("帐号不能为空");
     		return false;
     	}
     	if(document.getElementsByName("adminInfoDomain.password")[0].value=="")
     	{
     		alert("密码不能为空");
     		return false;
     	}
     	if(document.getElementsByName("adminInfoDomain.role")[0].value=="")
     	{
     		alert("角色不能为空");
     		return false;
     	}
     	if(document.getElementsByName("adminInfoDomain.isable")[0].value=="")
     	{
     		alert("不否可用不能为空");
     		return false;
     	}
     	document.forms[0].submit();
     }
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.validate.post.js"></script>

		<title>新建管理员帐号</title>
	</head>
	<body>

		<html:form action="/admininfoRef.do" method="post"
			target="_self">
			<html:hidden property="actionType" />

			<div class="mainContent">

				<div class="warp">
					<span class="title">新建管理员帐号</span>
					<fieldset class="inputStyle3">
						<label>
							<span>帐号：</span>
							<html:text property="adminInfoDomain.name" />
							&nbsp;&nbsp;*
						</label>
						<label>
							<span>密码：</span>
							<html:text property="adminInfoDomain.password" />
							&nbsp;&nbsp;*
						</label>
						<label>
							<span>角色：</span>
							<html:select property="adminInfoDomain.role" value="input">
							<html:option key="普通管理员" value="admin" />
							<html:option key="超级管理员" value="superadmin" />
							<html:option key="信息录入员1" value="typernotes" />
							<html:option key="信息录入员2" value="typerfile" />
							<html:option key="信息录入员3" value="typemedical" />
							<html:option key="信息录入员4" value="typermember" />
							<html:option key="信息录入员5" value="typerbbs" />
							<html:option key="信息录入员6" value="typerpic" />
							</html:select>
						</label>
						<label>
							<span>是否可用：</span>
							<html:text property="adminInfoDomain.isable" value="y"/>
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