<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<title>修改用户信息</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/front/user.js"> </script>
	</head>
	<body>
		<!-- 顶部 -->
		<%@ include file="/WEB-INF/pages/public/header.jspf"%>

		<html:form focus="loginName" action="/user" enctype="multipart/form-data"
			onsubmit="return validateEditUserForm(this)">
			<html:hidden property="id" />
			<html:hidden property="method" value="edit" />
			<html:hidden property="returnPath" value="${returnPath }" />

	<table class="form" style="width: 700px;">
		        <tr>
		            <td colspan="3" class="title">基本信息(必填)</td>
		        </tr>
		        <tr>
		            <td width="70px;">登录名</td>
		            <td width="300px;">
		            	<html:text property="loginName" styleClass="input1" readonly="true"/>
		            </td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>真实姓名</td>
		            <td><html:text property="realname" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		         <tr>
		            <td>昵称</td>
		            <td><html:text property="nickname" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>电子邮件</td>
		            <td><html:text property="email" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>常用手机</td>
		            <td><html:text property="cellphone" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>毕业专业</td>
		            <td><html:text property="major" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>学历</td>
		            <td><html:text property="education" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>现在职称</td>
		            <td><html:text property="title" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>籍贯</td>
		            <td><html:text property="birthplace" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		         <tr>
		            <td>居住地址</td>
		            <td><html:text property="address" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>邮编</td>
		            <td><html:text property="postcode" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>婚姻状况</td>
		            <td><html:select property="marriage" styleClass="input1">
		            <html:option key="1" value="已婚" />
		            <html:option key="2" value="未婚" /></html:select></td>
		            <td class="red">*</td>
		        </tr>
		         <tr>
		            <td>所在单位</td>
		            <td><html:text property="comeFrom" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		         <tr>
		            <td width="70px;">性别</td>
		            <td width="300px;">
			            <html:radio property="sex" value="SECRECY">保密</html:radio>
						<html:radio property="sex" value="MALE">男</html:radio>
						<html:radio property="sex" value="FEMALE">女</html:radio>
		            </td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>生日</td>
		            <td><html:text property="birthday" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		    </table>
		
		    <table class="form" style="width: 700px;">
		        <tr>
		            <td colspan="3" class="title"> 详细信息(选填)</td>
		        </tr>
		        <tr>
		            <td>QQ号码</td>
		            <td><html:text property="qq" styleClass="input1"/></td>
		            <td class="red">&nbsp;</td>
		        </tr>
		        <tr>
		            <td>MSN</td>
		            <td><html:text property="msn" styleClass="input1"/></td>
		            <td class="red">&nbsp;</td>
		        </tr>
		        <tr>
		            <td>头像图片</td>
		            <td><c:if test="${not empty user.avatar }">
							目前使用的图片 <br>
							<html:img action="/user?method=showAvatar&id=${user.id}" />
							<input type="checkbox" name="deleteCurrentAvatar" value="true">
							删除此头像<br>
						</c:if>
		            	<html:file property="avatarFile" styleClass="input1"/>
		            </td>
		            <td>最大像素 ${settings.avatarMaxWidth } * ${settings.avatarMaxHeight },<br>
		                  大小不能超过 ${settings.avatarMaxFileSize } 字节.
		            </td>
		        </tr>
		          <tr>
		            <td>常用交通工具</td>
		            <td><html:text property="car" styleClass="input1"/></td>
		            <td class="red">&nbsp;</td>
		        </tr>
		        <tr>
		            <td>本人特长</td>
		            <td><html:textarea property="speciality" styleClass="textarea1"/></td>
		            <td class="red">&nbsp;</td>
		        </tr>
		        <tr>
		            <td>能为校友提供的资源</td>
		            <td><html:textarea property="resource" styleClass="textarea1"/></td>
		            <td class="red">&nbsp;</td>
		        </tr>
		        <tr>
		            <td>意见与建议</td>
		            <td><html:textarea property="advice" styleClass="textarea1"/></td>
		            <td class="red">&nbsp;</td>
		        </tr>
		        <tr>
		            <td>个性签名</td>
		            <td><html:textarea property="signature" styleClass="textarea1"/></td>
		            <td>最大长度为 255 个字符</td>
		        </tr>
		        <tr>
		            <td align="center" colspan="3">
		            	<html:submit value="提 交" />&nbsp;&nbsp;&nbsp;&nbsp;
						<html:reset value="重 填" />
		            </td>
		        </tr>
		    </table>
		</html:form>
	</body>
</html>
