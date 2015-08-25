<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.jsict.jszju.action.IndexAction"%>

<html>
	<head>
		<title>查看所有文章</title>
		<%@ include file="/WEB-INF/jsp/share/js.jsp"%>
		<script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/js/iframe.js"></script>
			
		<style>
<!--
* {
	margin: 0;
	padding: 0;
	border: 0;
	outline: 0;
	font-size: 12px;
	vertical-align: middle;
	list-style-type: none;
	border-collapse: collapse;
	border-spacing: 0;
	cursor: default;
}

#container {
	width: 1002px; /* 使用比最大宽度 (800px) 小 20px 的宽度可显示浏览器界面元素，并避免出现水平滚动条 */
	margin: 0 auto; /* 自动边距（与宽度一起）会将页面居中 */
	height: 560px;
}
-->
</style>
		<script type="text/javascript">
        function _SetMenuFrameHeight(minHeight)
        {
            SetHeight('side', minHeight);
        }
        function _SetMenuFrameHeightEX(minHeight, totalMS)
        {
            SetHeightEX('side', minHeight, totalMS);
        }

        function _SetContentFrameHeight(minHeight)
        {
            SetHeight('main', minHeight);
        }
        function _SetContentFrameHeightEX(minHeight, totalMS)
        {
            SetHeightEX('main', minHeight, totalMS);
        }
    </script>
		<Script language="JavaScript">
<!--

window.onresize = resizeDiv;
//短信提示使用(asilas添加)
var divTop,divLeft,divWidth,divHeight,docHeight,docWidth,objTimer,i = 0;
function showMessage(title,content){
	jQuery("#popuptitle").html(title);
	jQuery("#popupcontent").text(content);
	getMsg();
	
}

function getMsg()
{
    try{
    divTop = parseInt(document.getElementById("eMeng").style.top,10)
    divLeft = parseInt(document.getElementById("eMeng").style.left,10)
    divHeight = parseInt(document.getElementById("eMeng").offsetHeight,10)
    divWidth = parseInt(document.getElementById("eMeng").offsetWidth,10)
    docWidth = document.body.clientWidth;
    docHeight = document.body.clientHeight;
    document.getElementById("eMeng").style.top = parseInt(document.documentElement.scrollTop,10) + docHeight + 10;//  divHeight
    document.getElementById("eMeng").style.left = parseInt(document.documentElement.scrollLeft,10) + docWidth - divWidth-20
    document.getElementById("eMeng").style.visibility="visible"
    objTimer = window.setInterval("moveDiv()",10)
    }
    catch(e){}
}
var offset=50;
function resizeDiv()
{
    i+=1
    if(i>300) closeDiv()    //客户想不用自动消失由用户来自己关闭所以屏蔽这句
    try{
    divHeight = parseInt(document.getElementById("eMeng").offsetHeight,10)
    divWidth = parseInt(document.getElementById("eMeng").offsetWidth,10)
    docWidth = document.body.clientWidth;
    docHeight = document.body.clientHeight;
    document.getElementById("eMeng").style.top = docHeight - divHeight + parseInt(document.documentElement.scrollTop,10)+offset
    document.getElementById("eMeng").style.left = docWidth - divWidth + parseInt(document.documentElement.scrollLeft,10)-20
    }
    catch(e){}
}

function moveDiv()
{
    try
    {
    if(parseInt(document.getElementById("eMeng").style.top,10) <= (docHeight - divHeight + parseInt(document.documentElement.scrollTop,10))+offset)
    {
    window.clearInterval(objTimer)
    objTimer = window.setInterval("resizeDiv()",1)
    }
    divTop = parseInt(document.getElementById("eMeng").style.top,10)
    document.getElementById("eMeng").style.top = divTop - 1
    }
    catch(e){}
}
function closeDiv()
{
    document.getElementById('eMeng').style.visibility='hidden';
    if(objTimer) window.clearInterval(objTimer)
}
-->
</Script>
	</head>
	<body>
			<div id="container">
			<!-- ========================= 顶部 ======================== -->
			<iframe frameborder="0" width="1002" height="120" scrolling="no"
				name="headFrame" id="head" onload="SetHeight('head',0);"
				src="<%=request.getContextPath()%>/index.do?actionType=<%=IndexAction.HEAD%>"></iframe>


			<!-- ========================= 主体内容 ======================== -->
			<iframe frameborder="0" width="1002" height="440" scrolling="no"
				name="mainFrame" id="main" onload="SetHeight('main',440);"
				style="float: left;"
				src="<%=request.getContextPath()%>/viewAllArticle.do?actionType=viewarticle&channelId=${channelId }"></iframe>

			<!-- ========================= 底部 ======================== -->
			<iframe frameborder="0" width="1002" height="25" scrolling="no"
				name="footFrame" id="foot" onload="SetHeight('foot',0);"
				src="<%=request.getContextPath()%>/index.do?actionType=<%=IndexAction.FOOT%>"></iframe>

		</div>
	</body>
</html>
