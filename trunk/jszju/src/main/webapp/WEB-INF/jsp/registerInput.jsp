<%@ page language="java" contentType="text/html;charset=utf-8"
    import="com.jsict.base.util.Consts"%>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/css.jsp" %>
<%@ include file="/WEB-INF/jsp/share/js.jsp"%>
<%@ include file="/WEB-INF/jsp/showDialog.jsp"%>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
<html >
<head>
<title>新校友注册 -江苏省浙江大学校友会 </title>
<script src="<%=request.getContextPath()%>/front_res/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/PopupCalendar.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/front_res/front.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/cms_member/css/whole.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/cms_member/css/layout.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
$(function() {
	$("#jvForm").validate();
	new JCore.CheckCode($('#checkCode'),'/CheckCode.svl');
	$('#username').focus();
});

function doRegister()
     {
     	if(document.getElementsByName("userInfodomain.name")[0].value=="")
     	{
     		alert("用户名不能为空");
     		return false;
     	}
     	if(document.getElementsByName("userInfodomain.password")[0].value=="")
     	{
     		alert("密码不能为空");
     		return false;
     	}
     	document.forms[0].actionType.value="<%=Consts.ACTION_TYPE_REGISTERCOMMIT%>";
     	document.forms[0].submit();
     }
var oCalendarEn=new PopupCalendar("oCalendarEn");   //初始化控件时,请给出实例名称如:oCalendarEn   
oCalendarEn.Init();   
var oCalendarChs=new PopupCalendar("oCalendarChs"); //初始化控件时,请给出实例名称:oCalendarChs   
oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");   
oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");   
oCalendarChs.oBtnTodayTitle="今天";   
oCalendarChs.oBtnCancelTitle="取消";   
oCalendarChs.Init();   
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
	  <li class="thisclass"><a href="">新校友注册中心</a></li>
	</ul>
</div>
<!--导航栏End--><!--页头End-->

<!--主体Begin-->
<div class="page_row">
 <html:form  action="/userLogin.do" method="post">
  <html:hidden property="actionType"/>
  <html:hidden property="userInfodomain.activitypoint" value="0"/>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC" id="psw">
    <tr>
		<th height="40" colspan="2" bgcolor="#FFFFFF" class="list_bar">新校友注册</th>
	</tr>
	<tr>
		<td width="20%" height="30" align="right" bgcolor="#F9F9F9">用户名：</td>
	  <td width="80%" bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.name" title="用户名不能为空" ></html:text>
	  </td>
	</tr>
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">密　码：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:password style="width:150px;" property="userInfodomain.password" title="密码不能为空" ></html:password></td>
	</tr>
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">确认密码：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:password style="width:150px;" property="userInfodomain.password" title="密码不能为空" ></html:password></td>
	</tr>
	
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">真实姓名：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.realname" ></html:text></td>
	</tr>
		
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">E-mail：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.email" ></html:text></td>
	</tr>
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">常用手机：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.cellphone" ></html:text></td>
	</tr>
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">出生日期：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.birthday" ></html:text></td>
	</tr>
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">浙大毕业专业：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.major" ></html:text></td>
	</tr>
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">浙大毕业年份：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.graduyear" ></html:text></td>
	</tr>	
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">浙大毕业证号：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.number" ></html:text></td>
	</tr>
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">现有学历：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.education" ></html:text></td>
	  </tr>
	  <tr>
	  	<td height="30" align="right" bgcolor="#F9F9F9">职称：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.title" ></html:text></td>
	  </tr>
	  <tr>
	  	<td height="30" align="right" bgcolor="#F9F9F9">籍贯：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.birthplace" ></html:text></td>
	</tr>
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">工作单位及职务：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.company" ></html:text></td>
	</tr>	
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">居住地通讯地址：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.address" ></html:text></td>
	 </tr>
	  <tr>
		<td height="30" align="right" bgcolor="#F9F9F9">邮编：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.postcode" ></html:text></td>
	</tr>
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">性别：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.sex" ></html:text></td>
	</tr>
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">婚姻现状：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.marriage" ></html:text></td>
	</tr>	
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">常用交通工具：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:text property="userInfodomain.car" ></html:text></td>
	</tr>
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">本人特长：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:textarea property="userInfodomain.speciality" /></td>
	</tr>
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">愿意为校友提供何种资源或服务：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:textarea property="userInfodomain.resource"/></td>
	</tr>
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">希望校友提供何种资源或服务：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:textarea property="userInfodomain.help" /></td>
	</tr>
	<tr>
		<td height="30" align="right" bgcolor="#F9F9F9">建议与意见：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	   <html:textarea property="userInfodomain.advice" /></td>
	</tr>
   <tr>
		<td height="30" align="right" bgcolor="#F9F9F9">兴趣爱好：</td>
	  <td bgcolor="#FFFFFF">&nbsp;
	  <html:textarea property="userInfodomain.interests" ></html:textarea></td>
	</tr>
	   <tr>
           <td class="list_bar" align="center" colspan="2" >
           	<input type="image" style="height:21px;" src="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/cms_member/img/register.png" onclick="doRegister()" />
         </td>
          </tr>
	  <tr>
	  <td colspan="2" style="padding:10px;" align="center" bgcolor="#FFFFFF">
	  	<textarea cols="115" rows="10">请遵守我们的协议</textarea>
	  </td>
	</tr>
	 

  </table>
  </html:form>
</div>
<!--主体End-->
<br>
</body>
</html>