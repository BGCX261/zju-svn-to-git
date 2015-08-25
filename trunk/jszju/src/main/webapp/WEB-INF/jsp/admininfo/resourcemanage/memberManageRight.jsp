<%@ page language="java" contentType="text/html;charset=utf-8"
    import="com.jsict.base.util.Consts"%>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/js.jsp"%>
<%@ include file="/WEB-INF/jsp/share/css.jsp"%>
<%@ include file="/WEB-INF/jsp/showDialog.jsp" %>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
        <script language="JavaScript" type=text/JavaScript>
            function doSelect() {
                document.forms[0].actionType.value = "<%=Consts.ACTION_TYPE_SELECT%>";
                document.forms[0].submit();
            }
            
            function doEdit(id) {
                document.forms[0].actionType.value = "<%=Consts.ACTION_TYPE_EDIT%>";
                document.forms[0].action = "userLogin.do?id="+id;
                document.forms[0].submit();
            }
             function doEdit(id) {
                document.forms[0].actionType.value = "<%=Consts.ACTION_TYPE_EDIT%>";
                document.forms[0].action = "userLogin.do?id="+id;
                document.forms[0].submit();
            }
            
        </script>
        
<head>
<title>校友会会员信息列表</title>
</head>
<body>
    <html:form action="/userLogin.do" method="post" target="_self">
    <input type="hidden" name="actionType" />
    <input type="hidden" name="menuParam" value="${param.menuParam}" />
	<div class="mainContent">
	    <!-- start #mainContent -->
		<div class="warp">
		    <span class="title">校友会会员搜索</span>
		
		    <fieldset class="inputStyle">
		        <label>
		            <span>姓名：</span>
		            <input type="text" name="filter_name_like" value="${param.filter_name_like}" />
		        </label>
		        <div class="bcR">
		            <input class="btn" onclick="doSelect()" type="button" value="查询" />
		        </div>
		    </fieldset>
		</div>
		<div class="warp">
	        <span class="title">校友会会员信息列表</span>
	
	        <table id="senfeTable" class="tableStyle">
	        <tr>
	            <th width="10%">用户名</th>
	            <th width="10%">真实姓名</th>
	            <th width="10%">年龄</th> 
	            <th width="10%">性别</th>
	            <th width="10%">联系方式</th>
	            <th width="10%">活跃度</th>
	            <th width="20%">公司</th>
	            <th width="10%">审核状态</th>
	            <th width="10%">操作</th>
	        </tr>
	        <tbody>
	        <logic:notEmpty name="userInfoForm" property="resultList">
	            <logic:iterate id="domain" name="userInfoForm" property="resultList">
	            <tr>
	                <td><bean:write name="domain" property="name" /></td>
	                <td><bean:write name="domain" property="realname" /></td>
	                <td><bean:write name="domain" property="age" /></td>
	                <td><bean:write name="domain" property="sex" /></td>
	                <td><bean:write name="domain" property="cellphone" /></td>
	                <td><bean:write name="domain" property="activitypoint" /></td>
	                <td><bean:write name="domain" property="company" /></td>
	                <td><bean:write name="domain" property="checkstatus" /></td>
	                <td>
	                   <a href="#" onclick=doEdit(<bean:write name="domain" property="id" />)>编辑</a>&nbsp;&nbsp;
	                </td>
	            </tr>
	            </logic:iterate>
	        </logic:notEmpty>
	        </tbody>
	        </table>
	        <div class="tablePage">
	            <jsp:include page="/WEB-INF/jsp/share/paging.jsp" />
	        </div>
	    </div>
    <!-- end #mainContent -->
    </div>
</html:form>

</body>
</html:html>