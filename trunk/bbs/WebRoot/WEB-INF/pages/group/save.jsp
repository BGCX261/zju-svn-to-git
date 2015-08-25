<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>添加/修改 组信息</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/admin/group.js"></script>
		<style type="text/css">
	        .role_cb {
	            width: 95px;
	        }
	    </style>
	</head>
	<body>

		<!-- 显示错误(如果有) -->
		<%@include file="/WEB-INF/pages/public/showErrors.jspf"%>

		<html:form focus="name" action="/admin/group" onsubmit="return validateGroupForm(this)">
			<html:hidden property="method" value="save" />
			<html:hidden property="id" />
			
			<table class="form">
		        <tr>
		            <td colspan="3" class="title">添加新群组</td>
		        </tr>
		        <tr>
		            <td>群组名称</td>
		            <td><html:text property="name" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>群组描述</td>
		            <td><html:textarea property="description" styleClass="textarea1"/></td>
		            <td>&nbsp;</td>
		        </tr>
		        <tr>
		            <td>拥有角色</td>
		            <td width="300px">
		            	<html:select property="rolesId" multiple="true" size="5" style="width: 300px; margin: 0px;">
							<html:optionsCollection name="roles" value="id" label="name"/>
						</html:select>
		            </td>
		            <td>&nbsp;</td>
		        </tr>
		        <tr>
		            <td align="center" colspan="3"><html:submit value="提 交" /></td>
		        </tr>
		    </table>
		</html:form>
	</body>
</html>