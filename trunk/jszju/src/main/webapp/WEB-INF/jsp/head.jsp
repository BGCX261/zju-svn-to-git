<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.jsict.jszju.action.MenuAction"%>
<%@ page import="com.jsict.base.util.Consts" %>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
<head>
<title>head</title>
<meta name="keywords" content="首页" />
<meta name="description" content="首页" />
<script src="<%=request.getContextPath()%>/js/headpage/mouseover.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/front_res/jquery.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/front_res/front.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/whole.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/layout.css" type="text/css" rel="stylesheet" />
<style type="text/css"><!--

<!--
.top_font_2 {	font-family: "&#40657;&#20307;";
	font-size: 12px;
	font-weight: normal;
	color: #666666;
	margin-right:4px;
	margin-top:4px;
}
--></style>
<script type="text/javascript">
function g(o){
	return document.getElementById(o);
}
function HoverLi(m,n,counter){
	for(var i=1;i<=counter;i++){
		g('tb_'+m+i).className='normaltab';
		g('tbc_'+m+i).className='undis';
	}
	g('tbc_'+m+n).className='dis';
	g('tb_'+m+n).className='curr';
}
        function doClick(url)
        {
        	//check messages
        	if(typeof(parent.head.checkMessages)!="undefined")
        	parent.head.checkMessages();
        	document.forms[0].actionType.value = '<%=MenuAction.CLICK%>';
            document.forms[0].url.value = url;
            document.forms[0].submit();
        }
</script> 
</head>

<body>
<html:form method="post" action="/menu.do" target="mainFrame">
    <input type="hidden" name="actionType"/>
    <input type="hidden" name="url"/></html:form>
<!--页头Begin-->
<!--页头Begin-->
<div class="top cbody">
<div class="toplogo"><a href="http://www.jszuaa.com" target="_blank"><img alt="" src="<%=request.getContextPath()%>/img/headimg2.jpg" /></a></div>
<div class="topbanner">&nbsp;</div>
<div class="toplink">
                 <a href="#">高级搜索</a> |
                  <a onclick="javascript:window.external.AddFavorite('http://www.jszjuaa.com');" href="javascript:void(0);">收藏本站</a> 
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 <a href="#">联系我们</a> | 
                 <a onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.jszjuaa.com')" href="javascript:void(0);">设为首页</a>
             </div>
</div>

<!--页头End--><!--导航栏Begin-->
<div class="topmenu cbody1">
<ul>
  <li class="thisclass"><a href="javascript:doClick('/linkToMain.do?actionType=firstpage')"	>首 页</a> </li>
  <li class="thisclass"><a href="<%=request.getContextPath()%>/linkToMain.do?actionType=<%=Consts.ACTION_TYPE_DYNAMIC%>"	target="mainFrame">动态信息</a> </li>
  <li class="thisclass"><a href="javascript:doClick('/linkToMain.do?actionType=file')"	>文件资料</a> </li>
  <li class="thisclass"><a href="javascript:doClick('/linkToMain.do?actionType=zjucommu')"	>校友交流</a> </li>
  <li onmouseover="mouseOver('div_tpys')" onmouseout="mouseOut('div_tpys')" class="thisclass"><a href="#">图片影视</a> </li>
  <li onmouseover="mouseOver('div_fhzc')" onmouseout="mouseOut('div_fhzc')" class="thisclass"><a href="#">分会之窗</a> </li>
  <li class="thisclass"><a href="javascript:doClick('/linkToMain.do?actionType=memberhome')"	>会员之家</a> </li>
  <li class="thisclass"><a target="_blank" href="http://www.jszuaa.com:8022/itcastbbs_beta">校友会BBS</a> </li>
</ul>
</div>
<div onmouseover="mouseOver('div_tpys')" onmouseout="mouseOut('div_tpys')" id="div_tpys" class="tpys">
  <a href="javascript:doClick('/linkToMain.do?actionType=picture')">图片资料</a>
  <a href="javascript:doClick('/linkToMain.do?actionType=video')">影视资料</a>
</div>
<div onmouseover="mouseOver('div_fhzc')" onmouseout="mouseOut('div_fhzc')" id="div_fhzc" class="fhzc">
	<a href="javascript:doClick('/linkToMain.do?actionType=oldzjuer')">老年分会</a>
	<a href="javascript:doClick('/linkToMain.do?actionType=youngzjuer')">青年分会</a>
  	<a href="javascript:doClick('/linkToMain.do?actionType=medical')">医学分会</a>
</div>
<!--导航栏End--><!--页头End-->
<!--搜索栏Begin-->
<script type="text/javascript">
function searchFormSubmit() {
	if(document.getElementById('searchKey').value==''){
		alert('请输入搜索内容');
		return false;
	}
	alert("此功能正在完善中...");
	document.getElementById('searchForm').reset();	
}
</script>

<form id="searchForm" action="/jeecms/ArtiSearch.do" target="_blank">
  <div class="topsearch">
    <div class="title"></div>
    <div id="page_search_left">
      <input type="hidden" name="count" value="10" />
      <input class="inputText" id="searchKey" size="16" onkeypress="if(event.keyCode==13){searchFormSubmit();return false;}" name="searchKey" type="text" />
    </div>
    <div id="page_search_btn"><img src="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/img/topsearch_submit.gif" border="0" onclick="searchFormSubmit();" /></div>
    <div class="hots"><strong>热门关键字：</strong> 校友&nbsp;幸福</div>
    <div id="page_search_right">
      <script>
		<!--var day="";
		var month="";
		var ampm="";
		var ampmhour="";
		var myweekday="";
		var year="";
		mydate=new Date();
		myweekday=mydate.getDay();
		mymonth=mydate.getMonth()+1;
		myday= mydate.getDate();
		year= mydate.getFullYear();
		if(myweekday == 0)
		weekday=" 星期日 ";
		else if(myweekday == 1)
		weekday=" 星期一 ";
		else if(myweekday == 2)
		weekday=" 星期二 ";
		else if(myweekday == 3)
		weekday=" 星期三 ";
		else if(myweekday == 4)
		weekday=" 星期四 ";
		else if(myweekday == 5)
		weekday=" 星期五 ";
		else if(myweekday == 6)
		weekday=" 星期六 ";
		document.write(year+"年"+mymonth+"月"+myday+"日 "+weekday);
		//-->
	  </script>
    </div>
    <div style="clear: both"></div>
  </div>
</form>
<!--搜索栏End-->
</body>
</html>
