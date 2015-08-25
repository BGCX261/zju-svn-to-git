<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
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

  </head>
  
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： 文章系统 - 文章管理 - 列表</div>
	<form class="ropt" method="post">
		<input type="submit" value="添加文章" onclick="this.form.action='acticleAdd.do';"/>
	</form>
	<div class="clear"></div>
</div>
<form action="Com_list.do" method="post" class="rhead">
<select name="queryInputAdminId"/><option value="">所有管理员</option><option value="1">admin</option><option value="2">test</option><option value="7">admintxt</option><option value="6">super</option></select>
<select name="queryStatus"/><option value="0" selected="selected">所有状态</option><option value="1">草稿</option><option value="2">待审核</option><option value="3">已审核</option><option value="4">退稿</option></select>
<select name="queryContentCtgId"/><option value="">所有属性</option><option value="1">普通</option><option value="2">图文</option><option value="3">焦点</option><option value="4">头条</option><option value="5">滚动</option></select>
<select name="queryTopLevel"/><option value="0" selected="selected">所有固顶</option><option value="1">不固顶</option><option value="2">固顶</option></select>
<select name="queryOrder"/><option value="0" selected="selected">ID降序</option><option value="1">ID升序</option><option value="2">日点击降序</option><option value="3">日点击升序</option><option value="4">周点击降序</option><option value="5">周点击升序</option><option value="6">月点击降序</option><option value="7">月点击升序</option><option value="8">季点击降序</option><option value="9">季点击升序</option><option value="10">年点击降序</option><option value="11">年点击升序</option><option value="12">总点击降序</option><option value="13">总点击升序</option></select>
<input type="checkbox" value="true" id="-queryTopTime" name="queryTopTime"/><label for="-queryTopTime">置顶</label>
<input type="checkbox" value="true" id="-queryDisabled" name="queryDisabled"/><label for="-queryDisabled">禁用</label>
&nbsp;标题：<input type="text" value="" name="queryTitle" size="15"/>
<input type="submit" value="查询"/></form>
<script language="javascript">
var Com_edit = {action:"Com_edit.do"};
var Com_disable = {action:"Com_disable.do"};
var Com_undisable = {action:"Com_undisable.do"};
var Com_delete = {action:"Com_delete.do",msg:"您确定删除吗？"};
function _gotoPage(pageNo) {
	try{
		var tableForm = document.getElementById('tableForm');
		tableForm.pageNo.value = pageNo;
		tableForm.action="Com_list.do";
		tableForm.onsubmit=null;
		tableForm.submit();
	} catch(e) {
		alert('gotoPage(pageNo)方法出错或不存在');
	}
}
function _operate(op,id) {
	if(op.msg && !confirm(op.msg)) {
		return;
	}
	var tableForm = document.getElementById('tableForm');
	tableForm.onsubmit=null;
	tableForm.action=op.action;
	tableForm.id.value = id;
	tableForm.submit();
}
function _validateBatch() {
	var batchChecks = document.getElementsByName('ids');
	var hasChecked = false;
	for(var i=0; i<batchChecks.length; i++) {
		if(batchChecks[i].checked) {
			hasChecked = true;
			break;
		}
	}
	if(!hasChecked) {alert('请选择要操作的数据！')};
	return hasChecked;
}
</script>
<form id="tableForm" method="post" onsubmit="return _validateBatch();">
<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead">
<tr>
	<th width="20px"><input type="checkbox" id="allCheck" value="checkbox" onclick="Pn.checkBox('ids',this.checked);"/></th>
	<th>ID</th>
	<th>标题</th>
	<th>属性</th>
	<th>发布时间</th>
	<th>录入员</th>
	<th>固顶</th>
	<th>总点击</th>
	<th>草稿</th>
	<th>退回</th>
	<th>状态</th>
	<th>操作</th>
</tr>
</thead>
<tbody class="pn-ltbody">
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="83"/></td>
	<td>83</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/review/index.htm" target="_blank">评论</a>]</strong><a class="normal" href="http://demo.jeecms.com/review/83.htm" target="_blank">asdasd</a></td>
	<td>普通</td>
	<td>2009-10-26</td>
	<td></td>
	<td>0</td>
	<td>15</td>
	<td>否</td>
	<td>否</td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'83');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="77"/></td>
	<td>77</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/war/index.htm" target="_blank">军事</a>]</strong><a class="normal" href="http://demo.jeecms.com/war/77.htm" target="_blank">请求通过的文章</a></td>
	<td>普通</td>
	<td>2009-09-27</td>
	<td>admintxt</td>
	<td>0</td>
	<td>92</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>0</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'77');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="76"/></td>
	<td>76</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/review/index.htm" target="_blank">评论</a>]</strong><a class="normal" href="http://demo.jeecms.com/review/76.htm" target="_blank">斯蒂芬</a></td>
	<td>普通</td>
	<td>2009-09-27</td>
	<td>admintxt</td>
	<td>0</td>
	<td>37</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>0</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'76');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="73"/></td>
	<td>73</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/war/index.htm" target="_blank">军事</a>]</strong><a class="normal" href="http://demo.jeecms.com/war/73.htm" target="_blank">我测试我测试我测试</a></td>
	<td>普通</td>
	<td>2009-09-16</td>
	<td></td>
	<td>0</td>
	<td>18</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'73');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="71"/></td>
	<td>71</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/sdf/index.htm" target="_blank">sdf</a>]</strong><a class="normal" href="http://demo.jeecms.com/sdf/71.htm" target="_blank">fds</a></td>
	<td>普通</td>
	<td>2009-09-09</td>
	<td></td>
	<td>0</td>
	<td>23</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'71');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="68"/></td>
	<td>68</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/review/index.htm" target="_blank">评论</a>]</strong><a class="normal" href="http://demo.jeecms.com/review/68.htm" target="_blank">ddd</a></td>
	<td>普通</td>
	<td>2009-08-29</td>
	<td>admin</td>
	<td>0</td>
	<td>345</td>
	<td>否</td>
	<td>否</td>
	<td>终审通过</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'68');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="67"/></td>
	<td>67</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/pic/index.htm" target="_blank">图片</a>]</strong><a class="normal" href="http://demo.jeecms.com/pic/67.htm" target="_blank">fdsagds</a></td>
	<td>普通</td>
	<td>2009-08-29</td>
	<td></td>
	<td>0</td>
	<td>19</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'67');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="66"/></td>
	<td>66</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/pic/index.htm" target="_blank">图片</a>]</strong><a class="normal" href="http://demo.jeecms.com/pic/66.htm" target="_blank">4444</a></td>
	<td>普通</td>
	<td>2009-08-29</td>
	<td></td>
	<td>0</td>
	<td>11</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'66');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="65"/></td>
	<td>65</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/pic/index.htm" target="_blank">图片</a>]</strong><a class="normal" href="http://demo.jeecms.com/pic/65.htm" target="_blank">我要测试</a></td>
	<td>普通</td>
	<td>2009-08-27</td>
	<td></td>
	<td>0</td>
	<td>35</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'65');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="64"/></td>
	<td>64</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/war/index.htm" target="_blank">军事</a>]</strong><a class="normal" href="http://demo.jeecms.com/war/64.htm" target="_blank">fff</a></td>
	<td>普通</td>
	<td>2009-08-20</td>
	<td></td>
	<td>0</td>
	<td>25</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'64');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="63"/></td>
	<td>63</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/pic/index.htm" target="_blank">图片</a>]</strong><a class="normal" href="http://demo.jeecms.com/pic/63.htm" target="_blank">test</a></td>
	<td>普通</td>
	<td>2009-08-15</td>
	<td></td>
	<td>0</td>
	<td>12</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'63');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="62"/></td>
	<td>62</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/review/index.htm" target="_blank">评论</a>]</strong><a class="normal" href="http://demo.jeecms.com/review/62.htm" target="_blank">ffffffffffffccccccccccccfffffffffffffffffffaaaaa…</a></td>
	<td>普通</td>
	<td>2009-07-26</td>
	<td></td>
	<td>0</td>
	<td>84</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'62');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="61"/></td>
	<td>61</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/pic/index.htm" target="_blank">图片</a>]</strong><a class="normal" href="http://demo.jeecms.com/pic/61.htm" target="_blank">dddd</a></td>
	<td>普通</td>
	<td>2009-07-26</td>
	<td></td>
	<td>0</td>
	<td>21</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'61');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="60"/></td>
	<td>60</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/review/index.htm" target="_blank">评论</a>]</strong><a class="normal" href="http://demo.jeecms.com/review/60.htm" target="_blank">test</a></td>
	<td>普通</td>
	<td>2009-07-16</td>
	<td></td>
	<td>0</td>
	<td>38</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'60');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="59"/></td>
	<td>59</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/war/index.htm" target="_blank">军事</a>]</strong><a class="normal" href="http://demo.jeecms.com/war/59.htm" target="_blank">11111</a></td>
	<td>普通</td>
	<td>2009-07-16</td>
	<td></td>
	<td>0</td>
	<td>21</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'59');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="58"/></td>
	<td>58</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/review/index.htm" target="_blank">评论</a>]</strong><a class="normal" href="http://demo.jeecms.com/review/58.htm" target="_blank">12345678</a></td>
	<td>普通</td>
	<td>2009-07-15</td>
	<td></td>
	<td>0</td>
	<td>11</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'58');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="57"/></td>
	<td>57</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/review/index.htm" target="_blank">评论</a>]</strong><a class="normal" href="http://demo.jeecms.com/review/57.htm" target="_blank">我来试试看</a></td>
	<td>普通</td>
	<td>2009-07-15</td>
	<td></td>
	<td>0</td>
	<td>32</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'57');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="55"/></td>
	<td>55</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/pic/index.htm" target="_blank">图片</a>]</strong><a class="normal" href="http://demo.jeecms.com/pic/55.htm" target="_blank">杨龙文章内容测试</a></td>
	<td>普通</td>
	<td>2009-07-13</td>
	<td>admin</td>
	<td>0</td>
	<td>853</td>
	<td>否</td>
	<td>否</td>
	<td>终审通过</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'55');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="54"/></td>
	<td>54</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/pic/index.htm" target="_blank">图片</a>]</strong><a class="normal" href="http://demo.jeecms.com/pic/54.htm" target="_blank">图片新闻（属性1）</a></td>
	<td>普通</td>
	<td>2009-07-09</td>
	<td></td>
	<td>0</td>
	<td>79</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'54');" class="pn-loperator">修改</a>┆</td>
</tr>
<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);" onclick="Pn.LTable.lineSelect(this);">
	<td><input type="checkbox" name="ids" value="52"/></td>
	<td>52</td>
	<td><strong>[<a class="normal" href="http://demo.jeecms.com/pic/index.htm" target="_blank">图片</a>]</strong><a class="normal" href="http://demo.jeecms.com/pic/52.htm" target="_blank">啊客家话快接啊花时间可 俺看打卡机话</a></td>
	<td>普通</td>
	<td>2009-07-03</td>
	<td></td>
	<td>0</td>
	<td>93</td>
	<td>否</td>
	<td><span style="color:red">是</span></td>
	<td>-1</td>
	<td class="pn-lopt"><a href="javascript:_operate(Com_edit,'52');" class="pn-loperator">修改</a>┆</td>
</tr>
</tbody>
</table>
<input type="hidden" name="id"/>
<input type="hidden" name="pageNo" value="1"/>
<input type="hidden" name="chnlId" value="2"/>
<div class="pn-sp">
	<div class="pn-sp-left">共 40 条 &nbsp;每页
	<input type="text" value="20" size="2" onfocus="this.select();" onblur="new Pn.Cookie().set(Pn.Cookie.countPerPage,this.value,10*365*24*60*60);" onkeypress="if(event.keyCode==13){$(this).blur();return false;}"/> 条</div>
	<div class="pn-sp-right">
		<input type="button" value="首 页" onclick="_gotoPage('1');" disabled="disabled"/>&nbsp;
		<input type="button" value="上一页" onclick="_gotoPage('1');" disabled="disabled"/>&nbsp;
		<input type="button" value="下一页" onclick="_gotoPage('2');"/>&nbsp;
		<input type="button" value="尾 页" onclick="_gotoPage('2');"/> &nbsp;
		当前 1/2 页 &nbsp;转到第 <input type="text" id="_goPs" size="2" onfocus="this.select();" onkeypress="if(event.keyCode==13){$('#_goPage').click();return false;}"/> 页
		<input id="_goPage" type="button" value="转" onclick="_gotoPage($('#_goPs').val());"/>
	</div>
	<div class="clear"></div>
</div>
<div class="pn-lbopt">
</div>
</form>
<script language="javascript">
</script></div>
</body>
</html>
