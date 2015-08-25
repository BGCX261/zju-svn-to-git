<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ page language="java" contentType="text/html;charset=utf-8"
		pageEncoding="utf-8"%>
	<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
	<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>
	<head>
		<title>浙江大学江苏校友会</title>
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
	</head>
  
 <body style="background-color:#ffffff">
 <div class="left"><img alt="" src="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/img/myjon.gif" /></div>
   <div class="left run_news">
				<marquee scrollAmount=2 width="100%" height=25 direction=up onmouseover=stop() onmouseout=start()>
						<table width="100%" cellpadding="0" cellspacing="0" border="0">
						
							<c:forEach var="notlist" items="${notelist}" varStatus="status" >
							<tr>
								<td height="28" id="roll-line-260101281" width="100%">
									<div class="">
										<div class="f-left">
											<span style="color: red">&middot;</span> ${notlist.time}
											<a
												href="<%=request.getContextPath()%>/linkToView.do?notesview=${notlist.id}"
												title="${notlist.title}" target="_blank"> <span style="">${notlist.title}</span>
											</a>
										</div>
										<div class="clear"></div>
									</div>
								</td>
							</tr>
							</c:forEach>
						</table>
						</marquee>
			</div>
	<div style="clear: both"></div>
			<div class="right">
			<a href="<%=request.getContextPath()%>/schoolFellowNotes.do?actionType=allnotes"
				target="_blank"> 查看所有公告
			</a>
				
			</div>
  </body>
</html>
