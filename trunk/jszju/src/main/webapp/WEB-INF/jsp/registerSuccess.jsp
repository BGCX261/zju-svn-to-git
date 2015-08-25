<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>会员注册 - </title>
<script src="<%=request.getContextPath()%>/front_res/jquery.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/front_res/front.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/cms_member/css/whole.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/cms_member/css/layout.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.form_table{width:400px; border:#cccccc 1px solid; line-height:35px;font-size:12px;}
.form_title{height:28px; line-height:28px; text-align:left; background-image:url(../img/title-bg.gif); color:#d14500; text-indent:10px;}
</style>
<script type="text/javascript">

function doRegister()
     {
     	document.forms[0].actionType.value="registercommit";
     	document.forms[0].submit();
     }
function transferAuto(){
		history.back();
	}
</script>
</head>

<body>
<!--页头Begin-->
<div class="top cbody">
	<div class="toplogo">
		<a href=""><img src="<%=request.getContextPath()%>/img/headimg2.jpg" /></a>
	</div>
	<div class="topbanner">&nbsp;</div>	
</div>
<!--导航栏Begin-->
<div class="topmenu cbody1">
	<ul>
	  <li class="timeclass">
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
      </li>
	  <li class="thisclass"><a href="">会员中心</a></li>
	</ul>
</div>
<!--导航栏End--><!--页头End-->
<table cellspacing="0" cellpadding="0"  align="center" class="form_table">
        <tbody>
          <tr>
            <td align="center" class="form_title" colspan="2" height="10">
            	提示信息
            </td>
          </tr>
          <tr height="60">
          	<td valign="middle" align="center">
          		注册成功，点击<label style="color:red; cursor:hand;" onclick="transferAuto()">此处</label>回到首页。
          	</td>
          </tr>         
        </tbody>
      </table>
</body>
</html>