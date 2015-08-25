<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp" %>
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
<head>
<title>浙江大学江苏校友会动态信息</title>
<meta content="text/html; charset=gb2312" http-equiv="Content-Type" />
<meta name="keywords" content="首页" />
<meta name="description" content="首页" />
<script src="<%=request.getContextPath()%>/front_res/jquery.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/front_res/front.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/whole.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/layout.css" type="text/css" rel="stylesheet" />
</head>

<body>
<div class="page_row">
  <div class="page_main_msg left">
    <div class="cycle_news_row">
      
      	<c:forEach var="menuList" items="${picMenuForm.menuDomainList[3].menuList}">
      	<div class="list cycle_news">
          <div class="list_bar">// ${menuList.title }</div>
        	 <c:forEach var="list" items="${picList}">
        	 <c:if test="${menuList.id==list.channelId }">
        	     <div class="list_content">
        	     	<div class="c1-body">
			    		 <div class="" style="padding:3px 0px;">
			     			<div class="f-left">&middot; <span style="">${list.title }</span>
			     			</div>
			     		</div>
					</div>
				</div>
				</c:if>
			</c:forEach>
		</div>
		</c:forEach>
     	 </div>

      <div style="clear: both"></div>
    </div>
  </div>
</body>
</html>
