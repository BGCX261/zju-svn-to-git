<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<head>
<title>资源管理-left</title>
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

<script src="<%=request.getContextPath()%>/fckeditor/fckeditor.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript"></script>
<script type="text/javascript">
$(function(){
	JCore.lmenu('lmenu');
});
</script>
</head>
<body class="lbody">
<ul id="lmenu">
<li><a href="<%=request.getContextPath()%>/resourcemanage.do?actionType=payCharge" target="articleRightFrame">会费缴纳登记</a></li>
<li><a href="<%=request.getContextPath()%>/schoolFellowNotes.do?actionType=notes" target="articleRightFrame">校友会公告</a></li>
<li><a href="<%=request.getContextPath()%>/schoolfellowinfocommit.do?actionType=forhelplist" target="articleRightFrame">校友合作项目发布管理</a></li>
<li><a href="<%=request.getContextPath()%>/schoolfellowinforhelp.do?actionType=consultlist" target="articleRightFrame">校友咨询与求助信息管理</a></li>
<li><a href="<%=request.getContextPath()%>/resourcemanage.do?actionType=payCharge" target="articleRightFrame">投票信息发布</a></li>
<li><a href="<%=request.getContextPath()%>/resourcemanage.do?actionType=uploadpic" target="articleRightFrame">图片上传</a></li>
<li><a href="<%=request.getContextPath()%>/fileView.do?actionType=picedit" target="articleRightFrame">图片编辑</a></li>
<li><a href="<%=request.getContextPath()%>/resourcemanage.do?actionType=payCharge" target="articleRightFrame">音视频上传</a></li>
</ul>
</body>
</html>