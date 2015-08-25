<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>添加/修改 角色信息</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/admin/role.js"></script>
		<style type="text/css">
	        .perm_cb{
	            width: 95px;
	        }
	    </style>
	</head>
	<body>

		<!-- 显示错误(如果有) -->
		<%@include file="/WEB-INF/pages/public/showErrors.jspf"%>

		<html:form focus="name" action="/admin/role" onsubmit="return validateRoleForm(this)">
			<html:hidden property="method" value="${roleForm.id gt 0 ? 'edit' : 'add'}" />
			<html:hidden property="id" />
			
			<table class="form" style="width: 450px;">
		        <tr>
		            <td colspan="3" class="title">添加新角色</td>
		        </tr>
		        <tr>
		            <td>上级角色</td>
		            <td><html:select property="parentId" styleClass="input1">
							<html:option value="0">--- 请选择上级 ---</html:option>
							<html:optionsCollection name="roles" value="id" label="name"/>
					   	</html:select>
		            </td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>角色名称</td>
		            <td><html:text property="name" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>角色描述</td>
		            <td><html:textarea property="description" styleClass="textarea1"/></td>
		            <td>&nbsp;</td>
		        </tr>
		        <tr>
		            <td>设置权限</td>
		            <td width="300px">
		            	<c:forEach items="${permissionGroups }" var="permissionGroup">
							<c:forEach items="${permissionGroup.permissions}" var="permission"><span class="perm_cb">
								<html:multibox property="permissionsId" value="${permission.id}"/>${permission.name}</span>
							</c:forEach>
						</c:forEach>
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