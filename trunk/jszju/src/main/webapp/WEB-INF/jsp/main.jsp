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
		<script type="text/javascript">
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
</script>
	</head>

	<body>
		<!--共同关注Begin-->
		<div class="page_row">
			<iframe
				src="<%=request.getContextPath()%>/schoolFellowNotes.do?actionType=front"
				frameborder="0" style="width: 100%; height: 40px"></iframe>
		</div>
		<!--共同关注End-->
		<!--主体上Begin-->
		<div class="page_row">
			<!--焦点图Begin-->
			<div id="jdt">
				<iframe
					src="<%=request.getContextPath()%>/fileView.do?actionType=focusview"
					frameborder="0" style="width: 534px; height: 245px"></iframe>

			</div>
			<!--焦点图End-->
			<!--本会信息预BEGIN-->

			<div class="list hot_news">
				<div id="tb_0" class="tbtncon">
					<ul>
						<li id="tb_11" onclick="HoverLi(1,1,4);" class="curr">
							本会动态
						</li>
						<li id="tb_12" onclick="HoverLi(1,2,4);">
							青年分会
						</li>
						<li id="tb_13" onclick="HoverLi(1,3,4);">
							医学分会
						</li>
						<li id="tb_14" onclick="HoverLi(1,4,4);">
							老年分会
						</li>
					</ul>
				</div>

				<div class="list_content">
					<div class="dis" id="tbc_11">
						<div class="c1-body">
							<c:set var="count3" value="0" />
							<c:forEach var="fgList" items="${firstpageList}"
								varStatus="status">
								<c:if test="${count3<9}">
									<c:if test="${ fgList.channelId eq 7}">
										<c:if test="${ fgList.isView eq 1}">
											<c:set var="count3" value="${count3+1}" />
											<div class="" style="padding: 3px 0px;">
												<div class="f-left">
													<img
														src="<%=request.getContextPath()%>/front_res/com_tag/head-mark3.gif"
														align="middle" class="img-vm" border="0" />
													<a
														href="<%=request.getContextPath()%>/linkToView.do?actionType=${fgList.id}"
														title="${fgList.title}" target="_blank"> <span
														style="">${fgList.title}</span> </a>
												</div>
												<div class="clear"></div>
											</div>
										</c:if>
									</c:if>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<div class="undis" id="tbc_12">
						<div class="c1-body">
							<c:set var="count4" value="0" />
							<c:forEach var="fgList" items="${firstpageList}"
								varStatus="status">
								<c:if test="${count4<9}">
									<c:if test="${ 30<fgList.channelId and fgList.channelId<37}">
										<c:if test="${ fgList.isView eq 1}">
											<c:set var="count4" value="${count4+1}" />
											<div class="" style="padding: 3px 0px;">
												<div class="f-left">
													<img
														src="<%=request.getContextPath()%>/front_res/com_tag/head-mark3.gif"
														align="middle" class="img-vm" border="0" />
													<a
														href="<%=request.getContextPath()%>/linkToView.do?actionType=${fgList.id}"
														title="${fgList.title}" target="_blank"> <span
														style="">${fgList.title}</span> </a>
												</div>
												<div class="clear"></div>
											</div>
										</c:if>
									</c:if>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<div class="undis" id="tbc_13">
						<div class="c1-body">
							<c:set var="count5" value="0" />
							<c:forEach var="fgList" items="${firstpageList}"
								varStatus="status">
								<c:if test="${count5<9}">
									<c:if test="${ 44<fgList.channelId and fgList.channelId<51}">
										<c:if test="${ fgList.isView eq 1}">
											<c:set var="count5" value="${count5+1}" />
											<div class="" style="padding: 3px 0px;">
												<div class="f-left">
													<img
														src="<%=request.getContextPath()%>/front_res/com_tag/head-mark3.gif"
														align="middle" class="img-vm" border="0" />
													<a
														href="<%=request.getContextPath()%>/linkToView.do?actionType=${fgList.id}"
														title="${fgList.title}" target="_blank"> <span
														style="">${fgList.title}</span> </a>
												</div>
												<div class="clear"></div>
											</div>
										</c:if>
									</c:if>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<div class="undis" id="tbc_14">
						<div class="c1-body">
							<c:set var="count6" value="0" />
							<c:forEach var="fgList" items="${firstpageList}"
								varStatus="status">
								<c:if test="${count6<9}">
									<c:if test="${ 24<fgList.channelId and fgList.channelId<31}">
										<c:if test="${ fgList.isView eq 1}">
											<c:set var="count6" value="${count6+1}" />
											<div class="" style="padding: 3px 0px;">
												<div class="f-left">
													<img
														src="<%=request.getContextPath()%>/front_res/com_tag/head-mark3.gif"
														align="middle" class="img-vm" border="0" />
													<a
														href="<%=request.getContextPath()%>/linkToView.do?actionType=${fgList.id}"
														title="${fgList.title}" target="_blank"> <span
														style="">${fgList.title}</span> </a>
												</div>
												<div class="clear"></div>
											</div>
										</c:if>
									</c:if>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>

			</div>

			<div style="clear: both"></div>
		</div>
		<!--主体上End-->
		<!--主体下Begin-->

		<div class="page_row">
			<div class="page_main_msg left">
				<div class="left_row">
					<img alt=""
						src="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/img/mainpage1.jpg" />
				</div>
				<div class="left_row">
					<div class="list_bar">
						// 校友风采
					</div>
					<iframe
						src="<%=request.getContextPath()%>/fileView.do?actionType=sfview"
						frameborder="0" style="width: 675px; height: 120px"></iframe>
				</div>
				<div style="width: 680px; border-width: 0px;">
					<div class="list cycle_news"
						style="margin-bottom: 5px; width: 320px;">
						<div class="list_bar">
							// <a href="<%=request.getContextPath()%>/viewAllArticle.do?channelId=8" target="_blank"> 浙大要闻</a>
						</div>
						<div class="dis" style="padding-top: 5px;" id="tbc_11">
							<div class="c1-body">
								<c:set var="count2" value="0" />
								<c:forEach var="fgList" items="${firstpageList}"
									varStatus="status">
									<c:if test="${count2<6}">
										<c:if test="${ fgList.channelId eq 8}">
											<c:if test="${ fgList.isView eq 1}">
												<c:set var="count2" value="${count2+1}" />
												<div style="padding-left: 6px; height: 20px;">
													<div class="f-left">
														<img
															src="<%=request.getContextPath()%>/front_res/com_tag/head-mark3.gif"
															align="middle" class="img-vm" border="0" />
														<a
															href="<%=request.getContextPath()%>/linkToView.do?actionType=${fgList.id}"
															title="${fgList.title}" target="_blank"> <span
															style="">${fgList.title}</span> </a>
													</div>
												</div>
											</c:if>
										</c:if>
									</c:if>
								</c:forEach>
							</div>
						</div>
						<div style="clear: both"></div>
					</div>
					<div class="list cycle_news"
						style="margin-bottom: 5px; width: 320px; float: right;">
						<div class="list_bar">
							// <a href="<%=request.getContextPath()%>/viewAllArticle.do?channelId=10" target="_blank"> 各地校友会信息</a>
						</div>
						<div class="dis" style="padding-top: 5px;" id="tbc_11">
							<div class="c1-body">
								<c:set var="count" value="0" />
								<c:forEach var="fgList" items="${firstpageList}"
									varStatus="status">
									<c:if test="${count<6}">
										<c:if test="${fgList.channelId eq 10}">
											<c:if test="${ fgList.isView eq 1}">
												<c:set var="count" value="${count+1}" />
												<div style="padding-left: 6px; height: 20px;">
													<div class="f-left">
														<img
															src="<%=request.getContextPath()%>/front_res/com_tag/head-mark3.gif"
															align="middle" class="img-vm" border="0" />
														<a
															href="<%=request.getContextPath()%>/linkToView.do?actionType=${fgList.id}"
															title="${fgList.title}" target="_blank">${fgList.title}</a>
													</div>
													<div class="clear"></div>
												</div>
											</c:if>
										</c:if>
									</c:if>
								</c:forEach>
							</div>
						</div>
						<div style="clear: both"></div>
					</div>
				</div>
				<div style="width: 680px; border-width: 0px;">
					<div class="list cycle_news"
						style="margin-bottom: 5px; width: 320px;">
						<div class="list_bar">
							// <a href="" target="_blank"> 杰出校友风采</a>
						</div>
						<iframe
							src="<%=request.getContextPath()%>/excellentsf.do?actionType=view"
							frameborder="0" style="width:250px; height: 370px"></iframe>
						<div style="clear: both"></div>
					</div>
					<div class="list cycle_news"
						style="margin-bottom: 5px; width: 320px; float: right;">
						<div class="list_bar">
							// <a href="<%=request.getContextPath()%>/schoolfellowinfocommit.do?actionType=viewallconsult" target="_blank"> 校友咨询与求助</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a
								href="http://www.jszuaa.com:8022/itcastbbs_beta/forum.do?method=show&id=10&page=1"
								target="_blank">我要咨询</a>
						</div>
						<iframe
							src="<%=request.getContextPath()%>/schoolfellowinfocommit.do?actionType=consultview"
							frameborder="0" style="width:250px; height: 170px"></iframe>
						<div class="list_bar">
							// <a href="<%=request.getContextPath()%>/schoolfellowinfocommit.do?actionType=viewallinfo" target="_blank"> 校友合作项目发布</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a
								href="http://www.jszuaa.com:8022/itcastbbs_beta/forum.do?method=show&id=10&page=1"
								target="_blank">我要发布信息</a>
						</div>
						<iframe
							src="<%=request.getContextPath()%>/schoolfellowinfocommit.do?actionType=view"
							frameborder="0" style="width:250px; height: 170px"></iframe>
						<div style="clear: both"></div>
					</div>
				</div>
			</div>
			<div class="page_other_msg right">
				<div class="left_row">
					<img alt=""
						src="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/img/mainpage3.png" />
				</div>

				<div class="left_row">
					<div class="list">
						<div class="list_bar">
							// 用户登录
						</div>
						<div class="list_content">
							<div id="div">
								<iframe
									src="<%=request.getContextPath()%>/userLogin.do?actionType=userbox"
									frameborder="0" style="width: 240px; height: 160px"></iframe>
							</div>
						</div>
					</div>
				</div>
				<div class="left_row">
					<div class="list">
						<div class="list_bar">
							// 校友捐赠信息
						</div>
						<div class="list_content">
							<div id="div">
								<iframe
									src="<%=request.getContextPath()%>/resourcemanage.do?actionType=endow"
									frameborder="0" style="width: 210px; height: 170px"></iframe>
							</div>
						</div>
					</div>
				</div>
				<div class="left_row">
					<div class="list">
						<div class="list_bar">
							// 活动意见征集
						</div>
						<div class="list_content">
							<script language="javascript">
function check_votes(allowCount) {
var voteItems=document.getElementsByName('voteItems');
  var count = 0;
  for(var i=0;i<voteItems.length;i++)
  {
   if(voteItems[i].checked){
     count++;
   }
  }
  if(count==allowCount&&allowCount>1){
   for(var i=0;i<voteItems.length;i++){
     if(!voteItems[i].checked){
       voteItems[i].disabled = true;
     }
   }
   return true;
  }
  else{
    for(var i=0;i<voteItems.length;i++){
       voteItems[i].disabled = false;
    }
  }
  if(count==0){
	  alert("对不起，请至少选择一个投票项！");
	  return false;0
  }
  alert("此功能正在完善中...");
  return false
}

function openwin() {
alert("此功能正在完善中...");
}
</script>

							<form name="votes" action="/jeecms/VoteResult.jspx" method="post"
								target="_blank">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="30" colspan="2" align="left">
											&nbsp;活动意见征集
											<input type="hidden" name="topicId" value="1" />
										</td>
									</tr>
									<tr>
										<td width="12%" height="20" align="right">
											<input type="radio" name="voteItems" value="1" />
										</td>
										<td width="88%" align="left">
											星期六还是去打羽毛球好些吧
										</td>
									</tr>
									<tr>
										<td width="12%" height="20" align="right">
											<input type="radio" name="voteItems" value="2" />
										</td>
										<td width="88%" align="left">
											星期六去腐败吧
										</td>
									</tr>
									<tr>
										<td width="12%" height="20" align="right">
											<input type="radio" name="voteItems" value="3" />
										</td>
										<td width="88%" align="left">
											星期六去参加跳舞咯
										</td>
									</tr>
									<tr>
										<td width="12%" height="20" align="right">
											<input type="radio" name="voteItems" value="4" />
										</td>
										<td width="88%" align="left">
											不知道星期六干嘛、随大流哦
										</td>
									</tr>
									<tr>
										<td width="12%" height="20" align="right">
											<input type="radio" name="voteItems" value="5" />
										</td>
										<td width="88%" align="left">
											星期六宅在家呢
										</td>
									</tr>
									<tr>
										<td height="30" colspan="2" align="center">
											<input type="submit" value="投 票"
												onClick="return check_votes(1)"
												style="border: #ccc 1px solid; background-color: #FFFFFF; font-size: 12px; padding-top: 3px;" />
											&nbsp;
											<input type="button" value="查看结果" onclick="openwin()"
												style="border: #ccc 1px solid; background-color: #FFFFFF; font-size: 12px; padding-top: 3px;" />
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
				<div class="left_row">
					<div class="list">
						<div class="list_bar">
							// 热心校友
						</div>
						<div class="list_content">
						贺文艳
							<div id="div">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div style="clear: both"></div>
		</div>
	</body>
</html>
