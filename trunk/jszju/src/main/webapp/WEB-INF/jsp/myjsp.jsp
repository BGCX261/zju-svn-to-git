<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

	<script language="JavaScript" type="text/javascript">
			function doSave()
			{
		        document.forms[0].submit();
			}
		</script>
		
  </head>
  
  <body>
  	<h1>身份登记</h1>
  	<html:form action="/userinfoSaveAction.do" method="post" target="_self"	>
  	<input type="hidden" name="actionType"/>
  		<table>
  			<tr>
  				<td>用户ID：
  				</td>
  				<td>
  				 <html:text property="userInfodomain.identifyno" />
  				</td>
  			</tr>
  			<tr>
  				<td>用户名：
  				</td>
  				<td>
  				 <html:text property="userInfodomain.name" />
  				</td>
  			</tr>
  			<tr>
  				<td>年龄：
  				</td>
  				<td>
  				 <html:text property="userInfodomain.age" />
  				</td>
  			</tr>
  			<tr>
  				<td>性别：
  				</td>
  				<td>
  				 <html:text property="userInfodomain.sex" />
  				</td>
  			<tr>
  				<td>
  					<input name="savebtn" type="button" onclick="doSave()" value="保存">
  				</td>
  			</tr>
  		</table>
  		</html:form>
  </body>
</html:html>