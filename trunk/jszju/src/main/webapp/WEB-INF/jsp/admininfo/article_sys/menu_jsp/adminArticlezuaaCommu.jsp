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
            function doSelect(channelId) {
                document.forms[0].actionType.value = "<%=Consts.ACTION_TYPE_SELECT%>";
                document.forms[0].action = "ArticleContentEdit.do?chaid="+channelId;
                document.forms[0].submit();
            }
            
            function doEdit(id) {
                document.forms[0].actionType.value = "<%=Consts.ACTION_TYPE_EDIT%>";
                document.forms[0].action = "ArticleContentEdit.do?id="+id;
                document.forms[0].submit();
            }
            
            function doView(id)
            {
                document.forms[0].actionType.value = "<%=Consts.ACTION_TYPE_VIEW%>";
                document.forms[0].action = "ArticleContentEdit.do?id="+id;
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
                document.forms[0].action = "ArticleContentEdit.do?id="+id;
                document.forms[0].submit();
                
            }
            
        </script>
        
<head>
<title>总会通讯文章列表</title>
</head>
<body>
    <html:form action="/ArticleContentEdit.do" method="post" target="_self">
    <input type="hidden" name="actionType" />
    <input type="hidden" name="menuParam" value="${param.menuParam}" />
	<div class="mainContent">
	    <!-- start #mainContent -->
		<div class="warp">
		    <span class="title">总会通讯文章搜索</span>
		
		    <fieldset class="inputStyle">
		        <label>
		            <span>文章标题：</span>
		            <input type="text" name="filter_title_like" value="${param.filter_title_like}" />
		        </label>
		        <label>
		            <span>属性：</span>
		            <input type="text" name="filter_attribute_like" value="${param.filter_attribute_like}" />
		        </label>
		        <label>                  
		            <span>状态：</span>
		            <input type="text" name="filter_status_like" value="${param.filter_status_like}" />
		        </label>
		        <!-- 
		        <label class="Double">
		        
				<span>发布时间：</span>
				<input id="filter_inputData" name="filter_inputData" value="${param['filter_inputData']}"/>
					 value="${param['filter_inputData']}"  readonly="true" type="text" onClick="WdatePicker()" class="Wdate"
					
			</label>/>-->
		        <div class="bcR">
		            <input class="btn" onclick="doSelect(${resultList[0].channelId })" type="button" value="查询" />
		        </div>
		    </fieldset>
		</div>
		<div class="warp">
	        <span class="title">总会通讯文章列表</span>
	
	        <table id="senfeTable" class="tableStyle">
	        <tr>
	            <th width="5%">ID</th>
	            <th width="20%">标题</th> 
	            <th width="15%">属性</th>
	            <th width="15%">发布时间</th>
	            <th width="5%">录入员</th>
	            <th width="5%">显示否</th>
	            <th width="5%">总点击率</th>
	            <th width="15%">状态</th>
	            <th width="15%">操作</th>
	        </tr>
	        <tbody>
	        <logic:notEmpty name="articleContentListForm" property="resultList">
	            <logic:iterate id="domain" name="articleContentListForm" property="resultList">
	            <c:if test="${domain.status!=380002}" >
	            <tr>
	                <td>
	                        <bean:write name="domain" property="id" />
	                   
	                </td>
	                <td><a href="#" onclick="doView(<bean:write name="domain" property="id" />)">
	                	<bean:write name="domain" property="title" /> </a></td>
	                <td><bean:write name="domain" property="attribute" /></td>
	                <td><bean:write name="domain" property="inputData" /></td>
	                <td><bean:write name="domain" property="inputAdmin" /></td>
	                <td><bean:write name="domain" property="isView" /></td>
	                <td><bean:write name="domain" property="visitTime" /></td>
	                 <td><bean:write name="domain" property="status" /></td>
	                <td>
	                   <a href="#" onclick=doEdit(<bean:write name="domain" property="id" />)>编辑</a>&nbsp;&nbsp;
                       <c:if test="${domain.status == 200006}">
                           <a href="#" onclick=doDisable(<bean:write name="domain" property="id" />)>废弃</a>
                       </c:if>
	                </td>
	            </tr>
	            </c:if>
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