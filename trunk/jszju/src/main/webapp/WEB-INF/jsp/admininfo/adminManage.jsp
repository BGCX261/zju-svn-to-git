<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>浙江大学江苏校友会网站后台管理 </title>
<link href="<%=request.getContextPath()%>/common_res/css/jquery_validate.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/common_res/css/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/core_res/css/front.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/core_res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/core_res/css/theme.css" rel="stylesheet" type="text/css"/>

<script src="<%=request.getContextPath()%>/common_res/js/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/common_res/js/jquery_validate.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/common_res/js/pony.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/common_res/js/jquery.ui.draggable.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/common_res/js/jquery.alerts.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/core_res/js/front.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/core_res/js/admin.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/fckeditor/fckeditor.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript"></script>
<script type="text/javascript">
var url = location.href;
var index = url.indexOf('jsessionid');
if(index != -1) {
	var sid = url.substr(index+11);
	document.cookie = "JSESSIONID="+sid+";path=/;";
}

function resizeFrame() {
	document.getElementById("mainFrame").style.height = document.body.offsetHeight-document.getElementById("top").offsetHeight-1+"px";
}
$(window).load(function() {
	resizeFrame();
});
$(window).resize(function() {
	resizeFrame();
});

</script>
<style type="text/css">
html{height:100%; overflow:hidden;}
body{height:100%;}
</style>
</head>
<body>
<!--页头Begin-->
<div id="top">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="330"><img src="<%=request.getContextPath()%>/cms_res/img/admin/cms_logo.jpg" border="0" /></td>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="32" align="right" background="<%=request.getContextPath()%>/core_res/img/admin/top_bg.jpg">
		<table border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
          <tr>
          	<td align="left">
				<select onchange="location=this.options[this.selectedIndex].value;">
					<option value="ManageSystem" selected="selected">浙大江苏校友会后台管理系统</option>
				</select>
			&nbsp;
          	</td>
            <td width="17" align="center"><img src="<%=request.getContextPath()%>/core_res/img/admin/msg2.jpg" width="10" height="8" border="0"/></td>
            <td width="80" align="left"><span style="color:#FFF">您好, ${cookie.adminname.value }</span></td>
            <td width="17" align="center"><img src="<%=request.getContextPath()%>/core_res/img/admin/ico5.jpg" border="0"/></td>
            <td width="60" align="left"><a href="<%=request.getContextPath()%>/adminInfoSubmit.do?actionType=adminLogout" class="channel" onclick="return confirm('您确定退出吗？');">退 出</a></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="95" align="right" background="<%=request.getContextPath()%>/core_res/img/admin/sy_bg.jpg">
            	<a href="<%=request.getContextPath()%>/adminArticleContent.do?actionType=ini"  target="mainFrame">首 页</a> &nbsp;
			</td>
			<td width="8"><img src="<%=request.getContextPath()%>/core_res/img/admin/menu_sep.jpg" border="0" /></td>
            <td width="76" align="center" background="<%=request.getContextPath()%>/core_res/img/admin/menu_bg.jpg">
            <a href="<%=request.getContextPath()%>/adminArticleContent.do?" target="mainFrame">文章栏目</a>
            </td>
			<td width="8"><img src="<%=request.getContextPath()%>/core_res/img/admin/menu_sep.jpg" border="0" /></td>
            <td width="76" align="center" background="<%=request.getContextPath()%>/core_res/img/admin/menu_bg.jpg">
            	<a href="<%=request.getContextPath()%>/adminArticleContent.do?actionType=articleContent" target="mainFrame">文章内容</a>
            </td>
			<td width="8"><img src="<%=request.getContextPath()%>/core_res/img/admin/menu_sep.jpg" border="0" /></td>
            <td width="76" align="center" background="<%=request.getContextPath()%>/core_res/img/admin/menu_bg.jpg">
            	<a href="<%=request.getContextPath()%>/adminArticleContent.do?" target="mainFrame">文档维护</a>
            </td>
            <td width="8"><img src="<%=request.getContextPath()%>/core_res/img/admin/menu_sep.jpg" border="0" /></td>
			<td width="76" align="center" background="<%=request.getContextPath()%>/core_res/img/admin/menu_bg.jpg">
            	<a href="<%=request.getContextPath()%>/adminArticleContent.do?" target="mainFrame">模板管理</a>
			</td>
            <td width="8"><img src="<%=request.getContextPath()%>/core_res/img/admin/menu_sep.jpg" border="0" /></td>
			<td width="76" align="center" background="<%=request.getContextPath()%>/core_res/img/admin/menu_bg.jpg">
            	<a href="<%=request.getContextPath()%>/adminArticleContent.do?actionType=resourcemanage" target="mainFrame">资源管理</a>
			</td>
            <td width="8"><img src="<%=request.getContextPath()%>/core_res/img/admin/menu_sep.jpg" border="0" /></td>
			<td width="76" align="center" background="<%=request.getContextPath()%>/core_res/img/admin/menu_bg.jpg">
            	<a href="<%=request.getContextPath()%>/adminArticleContent.do?" target="mainFrame">辅助系统</a>
			</td>
            <td width="8"><img src="<%=request.getContextPath()%>/core_res/img/admin/menu_sep.jpg" border="0" /></td>
			<td width="76" align="center" background="<%=request.getContextPath()%>/core_res/img/admin/menu_bg.jpg">
            	<a href="<%=request.getContextPath()%>/adminArticleContent.do?actionType=corefunction" target="mainFrame">核心功能</a>
			</td>
            <td width="8"><img src="<%=request.getContextPath()%>/core_res/img/admin/menu_sep.jpg" border="0" /></td>
			<td width="76" align="center" background="<%=request.getContextPath()%>/core_res/img/admin/menu_bg.jpg">
            	<a href="<%=request.getContextPath()%>/adminArticleContent.do?" target="mainFrame">网站配置</a>
			</td>
            <td width="8"><img src="<%=request.getContextPath()%>/core_res/img/admin/menu_sep.jpg" border="0" /></td>
			<td width="76" align="center" background="<%=request.getContextPath()%>/core_res/img/admin/menu_bg.jpg">
			</td>
            <td background="<%=request.getContextPath()%>/core_res/img/admin/menu_bg.jpg">&nbsp; </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="99" align="center" background="<%=request.getContextPath()%>/core_res/img/admin/msg_bg.jpg">
    <img src="<%=request.getContextPath()%>/core_res/img/admin/xs.gif" width="99" height="26" border="0"/></td>
    <td background="<%=request.getContextPath()%>/core_res/img/admin/msg_bg.jpg"><!--<a href="javascript:void(0);">关闭左栏</a>-->&nbsp;</td>
    <td width="73" align="center" background="<%=request.getContextPath()%>/core_res/img/admin/msg_bg.jpg">
    【<a href="/" target="_blank">查看首页</a>】</td>
    <td width="28" background="<%=request.getContextPath()%>/core_res/img/admin/msg_bg.jpg">
    <img src="<%=request.getContextPath()%>/core_res/img/admin/tleft.jpg" width="28" height="26" border="0"/></td>
    <td width="140" align="center" background="<%=request.getContextPath()%>/core_res/img/admin/tbg.jpg">
    <script language="JavaScript" type="text/javascript">
		<!--
				var enabled = 0; today = new Date();
				var day; var date;
				if(today.getDay()==0) day = " 星期日"
				if(today.getDay()==1) day = " 星期一"
				if(today.getDay()==2) day = " 星期二"
				if(today.getDay()==3) day = " 星期三"
				if(today.getDay()==4) day = " 星期四"
				if(today.getDay()==5) day = " 星期五"
				if(today.getDay()==6) day = " 星期六"
				date = (today.getFullYear()) + "年" + (today.getMonth() + 1 ) + "月" + today.getDate() + "日" + day +"";
				document.write(date);
		// -->
	  </script></td>
  </tr>
</table>
<div style="border-top:1px solid #1879B0;"></div>
</div>
<!--页头End-->
<!--主体框架Begin-->
<iframe id="mainFrame" name="mainFrame" src="<%=request.getContextPath()%>/adminArticleContent.do?actionType=ini" frameborder="0" scrolling="no" style="width:100%;"></iframe>
<!--主体框架End-->
<div style="border-top:1px solid #1879B0;"></div>
</body>
</html>