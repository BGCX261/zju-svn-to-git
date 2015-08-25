<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ page language="java" contentType="text/html;charset=utf-8"
		pageEncoding="utf-8"%>
	<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
	<%@ page import="com.jsict.jszju.action.MenuAction"%>
	<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>
	<head>
		<title>阅读文章</title>
		<script src="<%=request.getContextPath()%>/front_res/jquery.js"
			type="text/javascript"></script>
		    <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.metadata.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.selectboxes.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.ext.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/paging.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/foreground_table.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/date.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/iframe.js"></script>
		<link href="<%=request.getContextPath()%>/front_res/front.css"
			type="text/css" rel="stylesheet" />
		<link
			href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/whole.css"
			type="text/css" rel="stylesheet" />
		<link
			href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/layout.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript">
		
		window.onload = function() {
		window.frames["commnet_iframe"].document.getElementById("articleId").value = '${id}';
		window.frames["commnet_view"].document.getElementById("commentArticleId").value = '${id}';
			}
function g(o){
	return document.getElementById(o);
}
function HoverLi(m,n,counter){
	for(var i=1;i<=counter;i++){
		g('tb_'+m+i).className='normaltab';
		g('tbc_'+m+i).className='undis';
	}
	g('tbc_'+m+n).className='dis';
	g('tb_'+m+n).className='curr';
}
        function doClick(url)
        {
        	//check messages
        	if(typeof(parent.head.checkMessages)!="undefined")
        	parent.head.checkMessages();
        	document.forms[0].actionType.value = '<%=MenuAction.CLICK%>';
            document.forms[0].url.value = url;
            document.forms[0].submit();
        }
       
</script>
	</head>

	<body>
		<html:form method="post" action="/menu.do" target="mainFrame">
			<input type="hidden" name="actionType" />
			<input type="hidden" name="url" />
		</html:form>
		<!--主体下Begin-->
		<div class="page_row">
			<div class="page_main_msg left">
				<div class="left_row">
					<div class="list pic_news">
						<div class="list_bar">
							<a href="www.jszuaa.com" target="_parent">首页</a> &gt;
							正文
						</div>
						<div class="ctitle ctitle1">
							<c:forEach var="articleList" items="${List}">
								<c:if test="${articleList.id==id}">
      						${articleList.title}
      					</c:if>
							</c:forEach>
						</div>
						<div class="ctitleinfo">
							作者:&nbsp;&nbsp;&nbsp;发布日期：${articleList.time}
							10:22:16&nbsp;&nbsp;&nbsp;查看次数：909 次
						</div>
						<div class="intr">
							<span class="intr_front">摘 要：</span>
						</div>
						<div class="pbox">
							<p>
								<c:forEach var="articleList" items="${List}">
									<c:if test="${articleList.id==id}">
      								${articleList.content}
      							</c:if>
								</c:forEach>
							</p>
						</div>
						<div class="page_no">
							<div class="pg-3">
								<span class="total">共1页</span>
								<span class="disabled"> |< 首页</span><span class="disabled">
									< 上一页</span>
								<span class="current">1</span>
								<span class="disabled"> 下一页 ></span><span class="disabled">
									尾页 >|</span>
							</div>
						</div>
						<div class="arti_ref">
							【上一篇】:
							<span>没有了</span> &nbsp;&nbsp; 【下一篇】:
							<span>没有了</span>
						</div>
						<iframe	id="commnet_iframe"
							src="<%=request.getContextPath()%>/comment.do?actionType=comment"
							style="width: 100%; height: 80%"></iframe>

					</div>
				</div>
			</div>

			<div class="page_other_msg right">
				<div class="left_row">
					<div class="list">
						<div class="list_bar">
							// 用户登录
						</div>
						<div class="list_content">
							<div id="div">
								<iframe
									src="<%=request.getContextPath()%>/userLogin.do?actionType=userbox"
									frameborder="0" style="width: 210px; height: 150px"></iframe>
							</div>
						</div>
					</div>
				</div>
				    <div class="left_row">
      <div class="list">
        <div class="list_bar">// 最新留言</div>
       <iframe  id="commnet_view" src="<%=request.getContextPath()%>/comment.do?actionType=commentview"
									frameborder="0" style="width: 260px; height: 800px"></iframe>
      </div>
    </div>

			</div>
			<div style="clear: both;"></div>
		</div>
		<!--主体下End-->

	</body>
</html>
