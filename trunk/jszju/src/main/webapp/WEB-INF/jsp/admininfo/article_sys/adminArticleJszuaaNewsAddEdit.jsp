<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/js.jsp"%>
<%@ include file="/WEB-INF/jsp/share/css.jsp"%>
<%@ include file="/WEB-INF/jsp/showDialog.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="fck"%>
<jsp:useBean id="articleContentForm" scope="session"
	type="com.jsict.jszju.form.ArticleContentForm" />
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>
<%
Cookie[] cookies = request.getCookies();
		String chalId = null;
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("chalId")) {
					chalId = c.getValue();
					break;
				}
			}
		}
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script language="JavaScript" type=text/JavaScript>

     function doBack()
     {
     	 location.href='ArticleContentEdit.do?actionType=cancel';
     } 
     function doSave()
     {
     if(document.getElementsByName("domain.title")[0].value=="")
     	{
     		alert("文章标题不能为空");
     		return false;
     	}
     	if(document.getElementsByName("domain.attribute")[0].value=="")
     	{
     		alert("文章属性：是否审核通过，不能为空");
     		return false;
     	}
     	if(document.getElementsByName("domain.status")[0].value=="")
     	{
     		alert("文章状态，不能为空");
     		return false;
     	}
     	if(document.getElementsByName("domain.channelId")[0].value=="")
     	{
     		alert("文章所属栏目，不能为空");
     		return false;
     	}
     	document.forms[0].submit();
     }
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.validate.post.js"></script>

		<title>新建新闻文章</title>
	</head>
	<body>

		<html:form action="/ArticleContentAddEdit.do" method="post"
			target="_self">
			<html:hidden property="domain.id" />
			<html:hidden property="actionType" />

			<div class="mainContent">

				<div class="warp">
					<span class="title">新建新闻文章</span>
					<fieldset class="inputStyle3">
						<label>
							<span>文章标题：</span>
							<html:text property="domain.title" />
							&nbsp;&nbsp;*
						</label>
						<label>
							<span>文章属性：</span>
							<html:text property="domain.attribute" value="200006"/>
							&nbsp;&nbsp;*
						</label>
						<label>
							<span>录入员：</span>
							<html:text property="domain.inputAdmin" />
						</label>
						<label>
							<span>显示否：</span>
							<html:text property="domain.isView" />
						</label>
						<label>
							<span>被点击次数：</span>
							<html:text property="domain.visitTime" value="0" />
						</label>
						<label>
							<span>状态：</span>
							<html:text property="domain.status" value="200006"/>
							&nbsp;&nbsp;*
						</label>


						<label>
							<span>栏目ID：</span>
							<html:text property="domain.channelId" />
							&nbsp;&nbsp;*
						</label>
						<label>
							<span>作者：</span>
							<html:text property="domain.author" />
						</label>
						<label class="Triple">
							<span>内容：</span>
							<div class="fckInput">
								<fck:editor instanceName="domain.content" width="100%"
									height="400px">
									<jsp:attribute name="value">
                  						  <bean:write name="articleContentForm"
											property="domain.content" filter="false" />
              						 </jsp:attribute>
								</fck:editor>
							</div>
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