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
                document.forms[0].actionType.value = "<%=Consts.ACTION_TYPE_NEW%>";
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
		    <span class="title">缴纳会费会员搜索</span>
		
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
	        <span class="title">缴纳会费会员情况列表</span>
	
	        <table id="senfeTable" class="tableStyle">
	        <tr>
	            <th width="10%">ID</th>
	            <th width="25%">姓名</th> 
	            <th width="25%">会费</th>
	            <th width="25%">捐款</th>
	            <th width="15%">操作</th>
	        </tr>
	        <tbody>
	        <logic:notEmpty name="paychargeListFrom" property="resultList">
	            <logic:iterate id="domain" name="paychargeListFrom" property="resultList">
	            <tr>
	                <td>
	                        <bean:write name="domain" property="id" />
	                   
	                </td>
	                <td><bean:write name="domain" property="name" /></td>
	                <td><bean:write name="domain" property="money" /></td>
	                <td><bean:write name="domain" property="contribute" /></td>
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
	        <div class="bcL">
	            <input type="button" onClick="doAdd()" class="btn" value="新建" />
	        </div>
	    </div>
    <!-- end #mainContent -->
    </div>
</html:form>

</body>
</html:html>