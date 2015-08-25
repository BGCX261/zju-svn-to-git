<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/showDialog.jsp" %>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
<html>    
    <head>   
    <script src="<%=request.getContextPath()%>/front_res/jquery.js"
			type="text/javascript"></script>
		<link href="<%=request.getContextPath()%>/front_res/front.css"
			type="text/css" rel="stylesheet" />
		<link
			href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/whole.css"
			type="text/css" rel="stylesheet" />
		<link
			href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/layout.css"
			type="text/css" rel="stylesheet" />
    </head>   
     <body >
     			<!--焦点图Begin-->
				<script type="text/javascript">
var width=522;var focus_height=225;var text_height=20;
var swf_height = focus_height+text_height;
var pics='';var links='';var texts='';
<c:set var="count" value="0" />
     <c:forEach var="fc" items='${focuslist}'>
     <c:if test="${count<4}">
     <c:if test="${fc.dep==2}">
     <c:if test="${fc.display eq 1}">
     	<c:set var="count" value="${count+1}" />
    pics+='<%=request.getContextPath()%>/upload/${fc.filename}|';
	links+='|';
	texts+='${fc.title}|';
	</c:if>
	</c:if>
	</c:if>              
    </c:forEach>
pics=pics.substring(0,pics.length-1);
links=links.substring(0,links.length-1);
texts=texts.substring(0,texts.length-1);
document.write('<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="'+width+'" height="'+swf_height+'">');
document.write('<param name="movie" value="<%=request.getContextPath()%>/front_res/com_tag/focus3.swf"/>');
document.write('<param name="quality" value="high"/><param name="bgcolor" value="#F0F0F0"/>');
document.write('<param name="menu" value="false"/><param name="wmode" value="opaque"/>');
document.write('<param name="FlashVars" value="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+width+'&borderheight='+focus_height+'&textheight='+text_height+'"/>');
document.write('<embed src="<%=request.getContextPath()%>/front_res/com_tag/focus3.swf" width="'+width+'" height="'+swf_height+'" FlashVars="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+width+'&borderheight='+focus_height+'&textheight='+text_height+'" menu="false" wmode="opaque" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash"></embed>');
document.write('</object>');
</script>
			<!--焦点图End-->
    </body>   
</html> 