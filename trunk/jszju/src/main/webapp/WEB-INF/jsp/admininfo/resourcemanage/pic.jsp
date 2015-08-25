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
                document.forms[0].action = "resourcemanageEdit.do?id="+id;
                document.forms[0].submit();
            }
            

            function doAdd() 
            {
                document.forms[0].actionType.value = "uploadpic";
                document.forms[0].submit();
            }
            
        </script>
        
<head>
<title>本会新闻文章列表</title>
</head>
<body>
    <html:form action="/resourcemanage.do" method="post" target="_self">
    <input type="hidden" name="actionType" />
    <input type="hidden" name="menuParam" value="${param.menuParam}" />
	<div class="mainContent">
	    <!-- start #mainContent -->
		<div class="warp">
		    <span class="title">图片搜索</span>
		
		    <fieldset class="inputStyle">
		        <label>
		            <span>图片名：</span>
		            <input type="text" name="filter_name_like" value="${param.filter_name_like}" />
		        </label>
		        <div class="bcR">
		            <input class="btn" onclick="doSelect()" type="button" value="查询" />
		        </div>
		    </fieldset>
		</div>
		<div class="warp">
	        <span class="title">图片情况列表</span>
	
	        <table id="senfeTable" class="tableStyle">
	        <tr>
	            <th width="10%">ID</th>
	            <th width="10%">图片名</th>
	            <th width="10%">所属模块</th> 
	            <th width="35%">图片路径</th>
	            <th width="5%">是否显示</th>

	        </tr>
	        <tbody>
 	        <logic:notEmpty name="picListFrom" property="resultList">
	            <logic:iterate id="domain" name="picListFrom" property="resultList">
	            <tr>
	                <td>
	                        <bean:write name="picListFrom" property="id" />
	                   
	                </td>
	                <td><bean:write name="domain" property="name" /></td>
	                <td><bean:write name="domain" property="dep" /></td>
	                <td><bean:write name="domain" property="path" /></td>
	                <td><bean:write name="domain" property="display" /></td>
	                <td>
	                   <!-- <a href="#" onclick=doEdit(<bean:write name="domain" property="id" />)>编辑</a>&nbsp;&nbsp; -->
	                </td>
	            </tr>
	            </logic:iterate>
	        </logic:notEmpty>
	        </tbody>
	        </table>
	        <div class="tablePage">
	            <jsp:include page="/WEB-INF/jsp/share/paging.jsp" />
	        </div>
	        <div class="bcL">
	            <input type="button" onClick="doAdd()" class="btn" value="新建" />
	        </div>
	    </div>
    <!-- end #mainContent -->
    </div>
</html:form>

</body>
</html:html>