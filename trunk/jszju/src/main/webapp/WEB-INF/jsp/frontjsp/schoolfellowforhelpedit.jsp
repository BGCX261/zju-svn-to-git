<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/js.jsp"%>
<%@ include file="/WEB-INF/jsp/share/css.jsp"%>
<%@ include file="/WEB-INF/jsp/showDialog.jsp"%>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script language="JavaScript" type=text/JavaScript>

     function doBack()
     {
     	 location.href='schoolfellowinfocommit.do?actionType=cancel';
     } 
     function doSave()
     {
     if(document.getElementsByName("schoolFellowForHelpDomain.title")[0].value=="")
     	{
     		alert("信息标题不能为空");
     		return false;
     	}
     	document.forms[0].submit();
     }
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.validate.post.js"></script>

		<title>编辑校友发布合作信息</title>
	</head>
	<body>

		<html:form action="/schoolfellowinfocommit.do" method="post"
			target="_self">
			<html:hidden property="schoolFellowForHelpDomain.id" />
			<html:hidden property="actionType" />

			<div class="mainContent">

				<div class="warp">
					<span class="title">编辑校友发布合作信息</span>
					<fieldset class="inputStyle3">
						<label>
							<span>信息标题：</span>
							<html:text property="schoolFellowForHelpDomain.title" />
							&nbsp;&nbsp;*
						</label>
						<label>
							<span>发布人：</span>
							<html:text property="schoolFellowForHelpDomain.userid" />
							&nbsp;&nbsp;*
						</label>
						<label>
							<span>显示否：</span>
							<html:text property="schoolFellowForHelpDomain.isview" />
						</label>
						<label>
							<span>被访问次数：</span>
							<html:text property="schoolFellowForHelpDomain.visittimes" value="0"/>
						</label>
						<label class="Triple">
							<span>内容：</span>
							<html:textarea rows="4" property="schoolFellowForHelpDomain.content"
								styleClass="Triple" />
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