<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ page language="java" contentType="text/html;charset=utf-8"
		pageEncoding="utf-8"%>
	<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
	<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>校友合作项目发布系统</title>
<script src="<%=request.getContextPath()%>/front_res/jquery.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/front_res/front.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/cms_member/css/whole.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/cms_member/css/layout.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type=text/JavaScript>

     function doSave()
     {
     if(document.getElementsByName("schoolFellowForHelpDomain.title")[0].value=="")
     	{
     		alert("信息标题不能为空");
     		return false;
     	}
     	document.forms[0].actionType.value="forhelp"
     	document.forms[0].submit();
     }
</script>
</head>

<body>
<!--页头Begin-->
<div class="top cbody">
	<div class="toplogo">
		<img src="<%=request.getContextPath()%>/img/headimg2.jpg" />
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
	  <li class="thisclass"><a >校友合作项目发布中心</a></li>
	</ul>
</div>
<!--导航栏End--><!--页头End-->

<!--主体Begin-->
<div class="page_row">
<html:form action="/schoolfellowinfocommit.do" method="post"
			target="_self">
			<html:hidden property="actionType" />
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
	<tr>
		<td width="20%" height="30" align="right" bgcolor="#F9F9F9">标题：</td>
	  <td width="80%" bgcolor="#FFFFFF">&nbsp;
	 <html:text property="schoolFellowForHelpDomain.title" />&nbsp;&nbsp;*
	  </td>
	</tr>
	<tr>
	<td width="20%" height="30" align="right" bgcolor="#F9F9F9">内容：</td>
	  <td width="80%" bgcolor="#FFFFFF">&nbsp;
	  <html:textarea rows="10" property="schoolFellowForHelpDomain.content" cols="100%"/></td>
	</tr>
	<tr>
	  <td height="40" colspan="2" align="center" bgcolor="#FFFFFF">
	  <input value="保存" class='btn' type="button"
						onclick="if(!confirm('确认保存修改?'))return;doSave()" />
	  </td>
	</tr>
  </table>
</html:form>
</div>
<!--主体End-->
</body>
</html>