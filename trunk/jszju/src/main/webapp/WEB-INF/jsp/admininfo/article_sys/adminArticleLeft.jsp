
<%@page import="com.jsict.base.util.HttpUtils"%>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp" %>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>

<%@ page import="com.jsict.jszju.action.MenuAction" %>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>

<html>
<head>
    <title>文章内容管理</title>
   
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
<html:form method="post" action="/menu.do" target="articleRightFrame">
    <input type="hidden" name="actionType"/>
    <input type="hidden" name="url"/></html:form>


<div style="float: left" id="my_menu">

<c:forEach  var="menuDomain" items="${menuForm.menuDomainList}">
	<div>
	<span>
	${menuDomain.title }
	</span>

	<!-- 如果第一级菜单上面有链接，创建一个第二级菜单 -->
	<c:forEach var="subMenuDomain" items="${menuDomain.menuList}">
	
	<a href="javascript:doClick('${ subMenuDomain.page}')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img border="0" src="themes/submenu/images/submenu/subarrow_right.gif">
	&nbsp;${subMenuDomain.title }</a>
			<!-- 如果第二级菜单上面有链接，创建一个第三级菜单 -->
			<c:forEach var="subMenuDomain" items="${subMenuDomain.menuList}">
			<a href="javascript:doClick('${ subMenuDomain.page}')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img border="0" src="themes/submenu/images/submenu/subarrow_right.gif">
			&nbsp;${subMenuDomain.title }</a>
			</c:forEach>
	</c:forEach>
	</div>
    
</c:forEach>

</div>

</body>
</html>
