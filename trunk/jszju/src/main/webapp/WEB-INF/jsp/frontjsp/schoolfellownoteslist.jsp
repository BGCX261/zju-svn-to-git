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
                document.forms[0].action = "schoolFellowNotes.do?id="+id;
                document.forms[0].submit();
            }
            
            function doView(id)
            {
                document.forms[0].actionType.value = "<%=Consts.ACTION_TYPE_VIEW%>";
                document.forms[0].action = "schoolFellowNotes.do?id="+id;
                document.forms[0].submit();
            }           

            function doAdd() 
            {
                document.forms[0].actionType.value = "<%=Consts.ACTION_TYPE_NEW%>";
                document.forms[0].submit();
            }
            
            function doDisable(id)
            {
                if(!confirm("确认废弃？"))
                {
                    return;
                }
                document.forms[0].actionType.value = "<%=Consts.ACTION_TYPE_DISABLE%>";
                document.forms[0].action = "schoolFellowNotes.do?id="+id;
                document.forms[0].submit();
                
            }
            
        </script>
        
<head>
<title>校友会公告通知列表</title>
</head>
<body>
    <html:form action="/schoolFellowNotes.do" method="post" target="_self">
    <input type="hidden" name="actionType" />
    <input type="hidden" name="menuParam" value="${param.menuParam}" />
	<div class="mainContent">
	    <!-- start #mainContent -->
		<div class="warp">
		    <span class="title">校友会公告通知搜索</span>
		
		    <fieldset class="inputStyle">
		        <label>
		            <span>公告标题：</span>
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
	            <th width="30%">标题</th> 
	            <th width="20%">发布时间</th>
	            <th width="15%">是否显示</th>
	            <th width="15%">总点击率</th>
	            <th width="15%">操作</th>
	        </tr>
	        <tbody>
	        <logic:notEmpty name="schoolFellowNotesListForm" property="resultList">
	            <logic:iterate id="domain" name="schoolFellowNotesListForm" property="resultList">
	            <tr>
	                <td>
	                        <bean:write name="domain" property="id" />
	                   
	                </td>
	                <td><a href="#" onclick="doView(<bean:write name="domain" property="id" />)">
	                	<bean:write name="domain" property="title" /> </a></td>
	                <td><bean:write name="domain" property="time" /></td>
	                <td><bean:write name="domain" property="isview" /></td>
	                <td><bean:write name="domain" property="visittime" /></td>
	                <td>
	                   <a href="#" onclick=doEdit(<bean:write name="domain" property="id" />)>编辑</a>&nbsp;&nbsp;
                           <a href="#" onclick=doDisable(<bean:write name="domain" property="id" />)>删除</a>
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