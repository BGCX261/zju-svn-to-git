
<%@page import="com.jsict.base.util.HttpUtils"%><%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp" %>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>

<%@ page import="com.jsict.jszju.action.MenuAction" %>

<html>
<head>
    <title>无锡西站物流</title>
   
      <script type=“text/javascript”>
        document.execCommand("BackgroundImageCache", false, true);
      </script> 
    <%@ include file="/WEB-INF/jsp/share/js.jsp" %>
    <%@ include file="/WEB-INF/jsp/share/css.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/submenu/css/subMenu.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/themes/submenu/js/jquery.subMenu.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/themes/submenu/js/jquery.cookie.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/themes/menu/jquery-ui-themeroller.css" type="text/css" >
	<style>
	body{line-height:1em}
	</style>
    <script language="javascript" src="<%=request.getContextPath()%>/js/xtree.js"></script>
    <script language="javascript">
        function doClick(url)
        {
        	//check messages
        	if(typeof(parent.head.checkMessages)!="undefined")
        	parent.head.checkMessages();
            document.forms[0].actionType.value = '<%=MenuAction.CLICK%>';
            document.forms[0].url.value = url;
            document.forms[0].submit();
        }
        jQuery(document).ready(
       
        function(){
        jQuery("#my_menu").submenu({oneSmOnly:true,speed:200,expandNum:0,savestatus:false});
        
        }
        );
        
        jQuery(document).ready(
       
        function(){
        
        	setInterval("parent._SetMenuFrameHeight(100)",200);        
        }
        );
       
    </script> 
</head>
<body style="margin: 1px 0px 0px 0px">
<html:form method="post" action="/menu.do" target="main">
    <input type="hidden" name="actionType"/>
    <input type="hidden" name="url"/></html:form>


<div style="float: left" id="my_menu">

<c:forEach  var="menuDomain" items="${menuForm.menuDomainList}">
	<div>
	<span>
	${menuDomain.title }
	</span>

	<!-- 如果第一级菜单上面有链接，创建一个第二级菜单 -->
	<c:if test="${fn:length(menuDomain.page)>0||fn:length(menuDomain.menuList)==0}">
	
	<a href="javascript:doClick('${ menuDomain.page}')"><img border="0" src="themes/submenu/images/submenu/subarrow_right.gif">
	&nbsp;${menuDomain.title }</a>
	
	</c:if>
	<c:forEach var="subMenuDomain" items="${menuDomain.menuList}">
	
	<a href="javascript:doClick('${ subMenuDomain.page}')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img border="0" src="themes/submenu/images/submenu/subarrow_right.gif">
	&nbsp;${subMenuDomain.title }</a>
	</c:forEach>
	</div>
    

		
</c:forEach>

</div>

</body>
</html>
