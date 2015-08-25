<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
	<%@ page language="java" contentType="text/html;charset=utf-8"
		pageEncoding="utf-8"%>
	<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
	<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>
<head>
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
  <div class="c1-body">
							<c:forEach var="fhlist" items="${forconsultlist}" varStatus="status" >
									<div class="" style="padding: 3px 0px;">
										<div class="f-left">
											<img
												src="<%=request.getContextPath()%>/front_res/com_tag/head-mark3.gif"
												align="middle" class="img-vm" border="0" />
											<a  href="http://www.jszuaa.com:8022/itcastbbs_beta/article.do?method=showTopic&pageNum=1&id=${fhlist.id }"
												title="${fhlist.title}" target="_blank"> <span style="">${fhlist.title}</span>
											</a>
										</div>
										<div class="clear"></div>
									</div>
							</c:forEach>
						</div>
  </body>
</html>
