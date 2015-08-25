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
                document.forms[0].action = "fileView.do?id="+id;
                document.forms[0].submit();
            }
            
            function doView(id)
            {
                document.forms[0].actionType.value = "<%=Consts.ACTION_TYPE_VIEW%>";
                document.forms[0].action = "fileView.do?id="+id;
                document.forms[0].submit();
            }           

            
            function doDisable(id)
            {
                if(!confirm("确认废弃？"))
                {
                    return;
                }
                document.forms[0].actionType.value = "<%=Consts.ACTION_TYPE_DISABLE%>";
                document.forms[0].action = "fileView.do?id="+id;
                document.forms[0].submit();
                
            }
            
        </script>
        
<head>
<title>图片列表</title>
</head>
<body>
    <html:form action="/fileView.do" method="post" target="_self">
    <input type="hidden" name="actionType" />
    <input type="hidden" name="menuParam" value="${param.menuParam}" />
	<div class="mainContent">
	    <!-- start #mainContent -->
		<div class="warp">
		    <span class="title">图片搜索</span>
		
		    <fieldset class="inputStyle">
		        <label>
		            <span>图片模块：</span>
		            <input type="text" name="filter_dep_eq" value="${param.filter_dep_eq}" />
		        </label>
		        <label>
		            <span>图片标题：</span>
		            <input type="text" name="filter_title_like" value="${param.filter_title_like}" />
		        </label>
		        <div class="bcR">
		            <input class="btn" onclick="doSelect()" type="button" value="查询" />
		        </div>
		    </fieldset>
		</div>
		<div class="warp">
	        <span class="title">本会新闻文章列表</span>
	
	        <table id="senfeTable" class="tableStyle">
	        <tr>
	            <th width="5%">ID</th>
	            <th width="5%">模块</th>
	            <th width="20%">标题</th>
	            <th width="5%">显示否</th>
	            <th width="10%">文件名</th>
	             <th width="25%">路径</th> 
	            <th width="20%">链接地址</th>
	             <th width="10%">操作</th>
	            
	        </tr>
	        <tbody>
	        <logic:notEmpty name="fileHanderListForm" property="resultList">
	            <logic:iterate id="domain" name="fileHanderListForm" property="resultList">
	            <tr>
	                <td><a href="#" onclick="doView(<bean:write name="domain" property="id" />)">
	                        <bean:write name="domain" property="id" /></a>
	                </td>
	                <td>
	                	<bean:write name="domain" property="dep" /> </td>
	                <td><bean:write name="domain" property="title" /></td>
	                <td><bean:write name="domain" property="display" /></td>
	                <td><bean:write name="domain" property="filename" /></td>
	                <td><bean:write name="domain" property="path" /></td>
	                <td><bean:write name="domain" property="linkaddress" /></td>
	                <td>
	                   <a href="#" onclick=doEdit(<bean:write name="domain" property="id" />)>编辑</a>&nbsp;&nbsp;
                           <a href="#" onclick=doDisable(<bean:write name="domain" property="id" />)>废弃</a>
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