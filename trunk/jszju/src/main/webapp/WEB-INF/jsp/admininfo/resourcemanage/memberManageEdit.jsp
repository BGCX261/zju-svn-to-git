<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/js.jsp"%>
<%@ include file="/WEB-INF/jsp/share/css.jsp"%>
<%@ include file="/WEB-INF/jsp/showDialog.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="fck"%>
<jsp:useBean id="userInfoForm" scope="session"
	type="com.jsict.jszju.form.UserInfoForm" />
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script language="JavaScript" type=text/JavaScript>

     function doBack()
     {
     	 location.href='userLogin.do?actionType=list';
     } 
     function doSave()
     {
     	document.forms[0].submit();
     }
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.validate.post.js"></script>

		<title>用户信息管理</title>
	</head>
	<body>

		<html:form action="/userLogin.do" method="post"
			target="_self">
			<html:hidden property="actionType" />

			<div class="mainContent">

				<div class="warp">
					<span class="title">用户信息管理</span>
					<fieldset class="inputStyle3">
						<label>
							<span>用户姓名：</span>
							<html:text property="userInfodomain.name" />
							&nbsp;&nbsp;*
						</label>
						<label>
							<span>用户年龄：</span>
							<html:text property="userInfodomain.age" />
							&nbsp;&nbsp;*
						</label>
						<label>
							<span>用户性别：</span>
							<html:text property="userInfodomain.sex" />
						</label>
						<label>
							<span>用户密码：</span>
							<html:text property="userInfodomain.password" />
						</label>
						<label>
							<span>浙大毕业专业：</span>
							<html:text property="userInfodomain.department" />
						</label>
						<label>
							<span>用户角色权限：</span>
							<html:text property="userInfodomain.role" />
							&nbsp;&nbsp;*
						</label>

						<label>
							<span>用户邮件地址：</span>
							<html:text property="userInfodomain.email" />
						</label>
						<label>
							<span>用户公司：</span>
							<html:text property="userInfodomain.company" />
						</label>
						<label>
							<span>用户活跃度：</span>
							<html:text property="userInfodomain.activitypoint" />
						</label>
						<label>
							<span>用户审核情况：</span>
							<html:text property="userInfodomain.checkstatus" />
						</label>
						<label>
							<span>用户婚姻情况：</span>
							<html:text property="userInfodomain.marriage" />
						</label>
						<label>
							<span>用户真实姓名：</span>
							<html:text property="userInfodomain.realname" />
						</label>
						<label>
							<span>用户联系方式：</span>
							<html:text property="userInfodomain.cellphone" />
						</label>
						<label>
							<span>用户生日：</span>
							<html:text property="userInfodomain.birthday" />
						</label>
						
						<label>
							<span>用户浙大毕业专业：</span>
							<html:text property="userInfodomain.major" />
						</label>
						<label>
							<span>用户毕业年份：</span>
							<html:text property="userInfodomain.graduyear" />
						</label>
						<label>
							<span>用户浙大毕业证号：</span>
							<html:text property="userInfodomain.number" />
						</label>
						<label>
							<span>用户现有学历：</span>
							<html:text property="userInfodomain.education" />
						</label>
						<label>
							<span>用户职称：</span>
							<html:text property="userInfodomain.title" />
						</label>
						<label>
							<span>用户籍贯：</span>
							<html:text property="userInfodomain.birthplace" />
						</label>
						<label>
							<span>用户居住地通讯地址：</span>
							<html:text property="userInfodomain.address" />
						</label>
						<label>
							<span>邮编：</span>
							<html:text property="userInfodomain.postcode" />
						</label>
						<label>
							<span>用户常用交通工具：</span>
							<html:text property="userInfodomain.car" />
						</label>
						<label>
							<span>用户自我介绍：</span>
							<html:text property="userInfodomain.introduceself" />
							&nbsp;&nbsp;*
						</label>
						<label>
							<span>用户兴趣爱好：</span>
							 <html:textarea property="userInfodomain.interests" />
						</label>
						<label>
							<span>用户本人特长：</span>
							 <html:textarea property="userInfodomain.speciality" />
						</label>
						<label>
							<span>愿意为校友提供何种资源或服务：</span>
							 <html:textarea property="userInfodomain.resource" />
						</label>
						<label>
							<span>希望校友提供何种资源或服务：</span>
							 <html:textarea property="userInfodomain.help" />
						</label>
						<label>
							<span>建议与意见：</span>
							 <html:textarea property="userInfodomain.advice" />
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