<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<title>${settings.name } 登陆</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/front/user.js"></script>
		<script type="text/javascript">
	        function toggleSelectAutoLoginDays(enable) {
	            $("select[name=autoLoginDays]").attr("disabled", !enable);
	        }
	        $(document).ready(function () {
	            toggleSelectAutoLoginDays($("input[name=autoLogin]").attr("checked"));
	        });
	    </script>
	</head>
	<body style="text-align: center">
		
		<!-- 顶部 -->
		<%@ include file="/WEB-INF/pages/public/header.jspf"%>

		<!-- 登录表单 -->
		<html:form action="/user" focus="loginName" onsubmit="return validateLoginForm(this)">
			<html:hidden property="method" value="login" />
			<html:hidden property="returnPath" value="${returnPath }" />

		   <table class="form">
		        <tr>
		            <td colspan="3" class="title">用户登录</td>
		        </tr>
		        <tr>
		            <td>登录名</td>
		            <td><html:text property="loginName" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>密　码</td>
		            <td><html:password property="password" redisplay="false" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>&nbsp;</td>
		            <td><html:checkbox property="autoLogin" onclick="toggleSelectAutoLoginDays(this.checked)"/>
		                自动登录
		              <html:select property="autoLoginDays" style="width: 100px;">
						<html:option value="7">一周</html:option>
						<html:option value="30">一个月</html:option>
						<html:option value="180">半年</html:option>
					  </html:select>
		            </td>
		            <td class="red">&nbsp;</td>
		        </tr>
		        <tr>
		            <td align="center" colspan="3"><input type="submit" value="提 交"></td>
		        </tr>
		    </table>
		</html:form>
		
		<!-- 底部 -->
		<%@ include file="/WEB-INF/pages/public/footer.jspf"%>
	</body>
</html>

