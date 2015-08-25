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
	<style type="text/css">
<!--
#demo {
	background: #FFF;
	overflow: hidden;
	width: 675px;
}

#demo img {
	border: 3px solid #F2F2F2;
}

#indemo {
	float: left;
	width: 800%;
}

#demo1 {
	float: left;
}

#demo2 {
	float: left;
}
-->
</style>
 
    </head>   
     <body >
     		<div id="demo">
			<div id="indemo">
				<div id="demo1">
				<c:set var="count" value="0" />
				<c:forEach var="fList" items="${filelist}">
				<c:if test="${count<6}">
						<c:if test="${fList.display eq 1}">
						<c:if test="${fList.dep eq 1}">
							<c:set var="count" value="${count+1}" />
						<a href="${fList.linkaddress}" title="${fList.title}" target="_blank"><img
							src="<%=request.getContextPath()%>/upload/${fList.filename}"
							alt="${fList.title}" border="0" /> </a>
						</c:if>
						</c:if>
						</c:if>
				</c:forEach>
				</div>
				<div id="demo2"></div>
			</div>
		</div>
		<script>
<!--
var speed=10; //数字越大速度越慢
var tab=document.getElementById("demo");
var tab1=document.getElementById("demo1");
var tab2=document.getElementById("demo2");
tab2.innerHTML=tab1.innerHTML;
function Marquee(){
if(tab2.offsetWidth-tab.scrollLeft<=0)
tab.scrollLeft-=tab1.offsetWidth
else{
tab.scrollLeft++;
}
}
var MyMar=setInterval(Marquee,speed);
tab.onmouseover=function() {clearInterval(MyMar)};
tab.onmouseout=function() {MyMar=setInterval(Marquee,speed)};
-->
</script>
    </body>   
</html> 