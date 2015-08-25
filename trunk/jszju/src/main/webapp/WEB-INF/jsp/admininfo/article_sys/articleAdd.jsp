<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
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
$(function() {
	$("#submitForm").validate();
});
//改变内容属性时改变缩略图大小
var attrMap = {};
attrMap['1'] = [139,94];
attrMap['2'] = [139,94];
attrMap['3'] = [296,200];
attrMap['4'] = [100,68];
attrMap['5'] = [139,94];
function attrChange(id) {
	$('#zoomWidth1').val(attrMap[id][0]);
	$('#zoomHeight1').val(attrMap[id][1]);
}
</script>
</head>
<body>
<script type="text/javascript">
//上传图片
function upload(n) {	
	var of = $('#uploadFile'+n);
	//检查是否选择了图片
	if(of.val()=='') {
		alert('请选择要上传的图片');
		return;
	}
	//将file移动至上传表单
	$('#fileContent').empty();
	$('#fileContent').append(of);
	//复制一个file放至原处
	$('#ufc'+n).append(of.clone());
	//修改属性
	of.attr('id','');
	of.attr('name','uploadFile');
	//其他表单
	if($('#zoom'+n).attr('checked')) {
		$('#ufZoom').val('true');
	} else {
		$('#ufZoom').val('false');
	}
	$('#ufWidth').val($('#zoomWidth'+n).val());
	$('#ufHeight').val($('#zoomHeight'+n).val());
	$('#uploadNum').val(n);
	
	$('#uploadForm').submit();
}
//剪裁图片
function imgCut(n) {
	if($('#uploadImgPath'+n).val()=='') {
		alert("请先上传图片，再剪裁");
		return;
	}
	var url = "/admin/core/common/ImgAreaSelect.do?imgSrcRoot=http://demo.jeecms.com/res_base/jeecms_com_www/upload&uploadNum="+n+"&imgSrcPath="
		+$("#uploadImgPath"+n).val()+"&zoomWidth="+$("#zoomWidth"+n).val()+"&zoomHeight="+$("#zoomHeight"+n).val();
	window.open(url,"imgcut","height=550, width=1000, top=0, left=0, toolbar=no, menubar=no, scrollbars=auto, resizable=yes,location=no, status=no");
}
//清除图片
function clearImg(n) {
	$('#uploadImgPath'+n).val("");
	$('#preImg'+n).attr("src","");
}
</script>
<form id="uploadForm" action="/admin/core/common/UploadImg.do" method="post" enctype="multipart/form-data" target="hiddenIframe" style="display:none;width:0px;height:0px;">
<span id="fileContent"></span>
<input id="ufZoom" type="hidden" name="zoom"/>
<input id="ufWidth" type="hidden" name="zoomWidth"/>
<input id="ufHeight" type="hidden" name="zoomHeight"/>
<input id="uploadNum" type="hidden" name="uploadNum"/>
<input type="hidden" name="uploadRuleId" value="29636711"/>
</form>
<iframe name="hiddenIframe" frameborder="0" border="0" style="display:none;width:0px;height:0px;"></iframe><div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 文章系统 - 文章管理 - 添加</div>
	<form class="ropt">
		<input type="submit" value="返回列表" onclick="this.form.action='Com_list.do?chnlId=1';"/>
	</form>
	<div class="clear"></div>
</div>
<form method="post" action="Com_save.do" id="submitForm">
<input type="hidden" name="pageNo" value="1"/>
<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
<tr>
<td width="12%" class="pn-flabel pn-flabel-h">所属栏目：</td><td colspan="1" width="38%" class="pn-fcontent"><select name="bean.channel.id"/><option value="2">♀新闻</option><option value="13">&nbsp;├图片</option><option value="27">&nbsp;│└sdf</option><option value="11">&nbsp;├军事</option><option value="12">&nbsp;└评论</option><option value="3">♀娱乐</option><option value="14">&nbsp;├影视</option><option value="15">&nbsp;└音乐</option><option value="4">♀财经</option><option value="16">&nbsp;├股票</option><option value="17">&nbsp;└基金</option><option value="5">♀房产</option><option value="18">&nbsp;├家居</option><option value="19">&nbsp;└健康</option><option value="6">♀体育</option><option value="20">&nbsp;├NBA</option><option value="21">&nbsp;└CBA</option><option value="7">♀科技</option><option value="22">&nbsp;├数码</option><option value="23">&nbsp;└电脑</option><option value="8">♀手机</option><option value="9">♀汽车</option></select>
 <span class="pn-fhelp">请选择末级栏目</span></td><td width="12%" class="pn-flabel pn-flabel-h">文章属性：</td><td colspan="1" width="38%" class="pn-fcontent"><select name="bean.contentCtg.id" onchange="attrChange(this.value);"/><option value="1">普通 attr='1'</option><option value="2">图文 attr='2'</option><option value="3">焦点 attr='3'</option><option value="4">头条 attr='4'</option><option value="5">滚动 attr='5'</option></select>
</td></tr>
<tr><td width="12%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>标题：</td><td colspan="3" width="88%" class="pn-fcontent"><input type="text" maxlength="255" name="bean.title" class="required" size="80" maxlength="255"/></td></tr>
<tr><td width="12%" class="pn-flabel pn-flabel-h">简短标题：</td><td colspan="1" width="38%" class="pn-fcontent"><input type="text" maxlength="255" name="bean.shortTitle" class="pn-fruler" size="35" maxlength="255"/><div class="pn-fhelp">在文章列表中显示，不填写则显示完整标题</div></td><td width="12%" class="pn-flabel pn-flabel-h">标题颜色：</td><td colspan="1" width="38%" class="pn-fcontent">
<select name="bean.titleColor" onchange="this.blur();">
	<option value="">--默认--</option>
	<option value="#000000" style="background-color:#000000"></option>
	<option value="#FFFFFF" style="background-color:#FFFFFF"></option>
	<option value="#FF0000" style="background-color:#FF0000"></option>
	<option value="#FFFF00" style="background-color:#FFFF00"></option>
	<option value="#00FF00" style="background-color:#00FF00"></option>
	<option value="#00FFFF" style="background-color:#00FFFF"></option>
	<option value="#0000FF" style="background-color:#0000FF"></option>
	<option value="#FF00FF" style="background-color:#FF00FF"></option>
	<option value="#808080" style="background-color:#808080"></option>
	<option value="#C0C0C0" style="background-color:#C0C0C0"></option>
	<option value="#800000" style="background-color:#800000"></option>
	<option value="#808000" style="background-color:#808000"></option>
	<option value="#008000" style="background-color:#008000"></option>
	<option value="#008080" style="background-color:#008080"></option>
	<option value="#000080" style="background-color:#000080"></option>
	<option value="#800080" style="background-color:#800080"></option>
</select>
<input type="checkbox" value="true" id="-bean.bold" name="bean.bold"/><label for="-bean.bold">加粗</label>
<div class="pn-fhelp">在文章列表中的样式</div></td></tr>
<tr><td width="12%" class="pn-flabel pn-flabel-h">来源：</td><td colspan="1" width="38%" class="pn-fcontent"><input type="text" maxlength="100" value="" name="bean.origin" maxlength="100"/></td><td width="12%" class="pn-flabel pn-flabel-h">作者：</td><td colspan="1" width="38%" class="pn-fcontent"><input type="text" maxlength="100" value="" name="bean.author" maxlength="100"/></td></tr>
<tr><td width="12%" class="pn-flabel pn-flabel-h">摘要：</td><td colspan="3" width="88%" class="pn-fcontent"><textarea cols="80" rows="3" name="bean.description" maxlength="255"></textarea>
</td></tr>
<tr><td width="12%" class="pn-flabel pn-flabel-h">Tag标签：</td><td colspan="1" width="38%" class="pn-fcontent"><input type="text" maxlength="255" name="bean.tags" size="28" maxlength="255"/> <span class="pn-fhelp">用,分割</span></td><td width="12%" class="pn-flabel pn-flabel-h">阅读权限：</td><td colspan="1" width="38%" class="pn-fcontent"><select name="bean.group.id"/><option value="" selected="selected">不受限制</option><option value="1">普通会员</option></select>
</td></tr>
<tr><td width="12%" class="pn-flabel pn-flabel-h">相关文章：</td><td colspan="1" width="38%" class="pn-fcontent">
<input type="text" maxlength="255" name="bean.relatedIds" size="35" maxlength="255"/><div class="pn-fhelp">填写文章ID，多篇文章用,分割</div></td><td width="12%" class="pn-flabel pn-flabel-h">指定模板：</td><td colspan="1" width="38%" class="pn-fcontent"><select name="bean.tplContent"/><option value="" selected="selected">使用栏目选择的模板</option><option value="/article/default/content.html">/article/default/content.html</option></select>
<div class="pn-fhelp">可以为文章单独指定模板，一般无需选择</div></td></tr>
<tr><td width="12%" class="pn-flabel pn-flabel-h">固顶级别：</td><td colspan="1" width="38%" class="pn-fcontent">
	<select name="bean.topLevel"/><option value="0">不固顶</option><option value="1">固顶1</option><option value="2">固顶2</option><option value="3">固顶3</option><option value="4">固顶4</option><option value="5">固顶5</option><option value="6">固顶6</option></select>
<span class="pn-fhlep">越大级别越高</span>
&nbsp; <select name="topTime"/><option value="0">置顶时间</option><option value="24">置顶一天</option><option value="48">置顶二天</option><option value="72">置顶三天</option><option value="168">置顶一周</option><option value="720">置顶一月</option><option value="2160">置顶三月</option><option value="4320">置顶半年</option><option value="8760">置顶一年</option></select>

</td><td width="12%" class="pn-flabel pn-flabel-h">允许评论：</td><td colspan="1" width="38%" class="pn-fcontent"><input type="radio" id="_bean.allowComment_0" value="true" checked="checked" name="bean.allowComment"/><label for="_bean.allowComment_0">是</label> <input type="radio" id="_bean.allowComment_1" value="false" name="bean.allowComment"/><label for="_bean.allowComment_1">否</label></td>
</tr>
<tr><td width="12%" class="pn-flabel pn-flabel-h">是否草稿：</td><td colspan="1" width="38%" class="pn-fcontent"><input type="radio" id="_bean.draft_0" value="true" name="bean.draft"/><label for="_bean.draft_0">是</label> <input type="radio" id="_bean.draft_1" value="false" checked="checked" name="bean.draft"/><label for="_bean.draft_1">否</label></td>
<td width="12%" class="pn-flabel pn-flabel-h">是否推荐：</td><td colspan="1" width="38%" class="pn-fcontent"><input type="radio" id="_bean.recommend_0" value="true" name="bean.recommend"/><label for="_bean.recommend_0">是</label> <input type="radio" id="_bean.recommend_1" value="false" checked="checked" name="bean.recommend"/><label for="_bean.recommend_1">否</label></td>
</tr>
<tr><td width="12%" class="pn-flabel">内容：</td><td colspan="3" width="88%" class="pn-fcontent">
<script type="text/javascript">
var _fck_bean_content=new FCKeditor('bean.content');
_fck_bean_content.BasePath='/js/fckeditor/';
_fck_bean_content.Config["CustomConfigurationsPath"]="/js/fckeditor/myconfig.js";
_fck_bean_content.Config["ImageBrowser"] = false ;
_fck_bean_content.Config["FlashBrowser"] = false ;
_fck_bean_content.Config["LinkBrowser"] = false ;
_fck_bean_content.Config["MediaBrowser"] = false ;
_fck_bean_content.ToolbarSet='mydefault';
_fck_bean_content.Height=300;
_fck_bean_content.Config["uploadRuleId"]=29636711;
_fck_bean_content.Value="";
_fck_bean_content.Create();
</script>
</td></tr>
<tr><td width="12%" class="pn-flabel">缩略图：</td><td colspan="1" width="38%" class="pn-fcontent">
<input type="text" id="uploadImgPath1" readonly="readonly" name="bean.titleImg" size="24"/> <input type="button" value="剪裁" onclick="imgCut(1);"/> <input type="button" value="清空" onclick="clearImg(1);"/><br/>
<span id="ufc1"><input type="file" id="uploadFile1" size="20"/></span> <input type="button" value="上传" onclick="upload(1);"/><br/>
上传是否压缩图片：<input type="checkbox" id="zoom1" value="true" checked="checked"/>&nbsp;
宽：<input type="text" id="zoomWidth1" value="139" size="3"/> 高：<input type="text" id="zoomHeight1" value="94" size="3"/>
</td><td colspan="2" class="pn-fbutton">
<img id="preImg1" alt="预览区" srcRoot="http://demo.jeecms.com/res_base/jeecms_com_www/upload" style="width:100px;height:70px;background-color:#CCCCCC;border:1px solid #333"/>
</td></tr>
<tr><td width="12%" class="pn-flabel">内容图：</td><td colspan="1" width="38%" class="pn-fcontent">
<input type="text" id="uploadImgPath2" readonly="readonly" name="bean.contentImg" size="24"/> <input type="button" value="剪裁" onclick="imgCut(2);"/> <input type="button" value="清空" onclick="clearImg(2);"/><br/>
<span id="ufc2"><input type="file" id="uploadFile2" size="20"/></span> <input type="button" value="上传" onclick="upload(2);"/><br/>
上传是否压缩图片：<input type="checkbox" id="zoom2" value="true"/>&nbsp;
宽：<input type="text" id="zoomWidth2" value="310" size="3"/> 高：<input type="text" id="zoomHeight2" value="310" size="3"/>
</td><td colspan="2" class="pn-fbutton">
<img id="preImg2" alt="预览区" srcRoot="http://demo.jeecms.com/res_base/jeecms_com_www/upload" style="width:100px;height:70px;background-color:#CCCCCC;border:1px solid #333"/>
</td></tr>
<tr><td width="12%" class="pn-flabel pn-flabel-h">发布时间：</td><td colspan="1" width="38%" class="pn-fcontent"><input type="text" readonly="readonly" name="bean.releaseDate" class="Wdate" size="25" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/><div class="pn-fhelp">不填写则为当前时间</div></td><td width="12%" class="pn-flabel pn-flabel-h">外部链接：</td><td colspan="1" width="38%" class="pn-fcontent"><input type="text" maxlength="255" name="bean.outerUrl" size="30" maxlength="255"/><div class="pn-fhelp">链接直接使用该URL</div></td></tr>
<tr><td width="12%" class="pn-flabel pn-flabel-h">自定义数据：</td><td colspan="3" width="88%" class="pn-fcontent">
数据1：<input type="text" maxlength="255" name="bean.param1" size="20" maxlength="255"/> &nbsp;
数据2：<input type="text" maxlength="255" name="bean.param2" size="20" maxlength="255"/> &nbsp;
数据3：<input type="text" maxlength="255" name="bean.param3" size="20" maxlength="255"/> &nbsp;
<div class="pn-fhelp">多篇文章共享同一模板时，可以通过自定义数据实现内容页个性化</div></td></tr>
<tr><td colspan="4" class="pn-fbutton">
<input type="hidden" name="uploadRuleId" value="29636711"/>
<input type="hidden" name="chnlId" value="1"/>
<input type="submit" value="提交"/> &nbsp; <input type="reset" value="重置"/></td>
</tr></table>
</form>
<script language="javascript">
</script></div>
</body>
</html>
