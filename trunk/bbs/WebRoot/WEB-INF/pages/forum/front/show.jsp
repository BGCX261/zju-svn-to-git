<%@ page language="java" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>${forum.name }_${settings.name }</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<link rel="stylesheet" type="text/css" href="${baseUrl }/style/itcastbbs_forum_show.css">
		<script type="text/javascript">
			function gotoPage(pageNum){
				window.location="?method=show&id=${forum.id}&pageNum=" + pageNum;
			}
		</script>
	</head>
	<body>

		<!-- 顶部 -->
		<%@ include file="/WEB-INF/pages/public/header.jspf"%>

		<!-- 左侧菜单 -->
		<%@ include file="/WEB-INF/pages/public/leftmenu.jspf" %>

		<div id="rightcont">
			<div class="cont1"><span class="foruminfo">
				<!-- 发新帖图片 -->
				<html:link action="/article?method=addTopicUI&forumId=${forum.id}">
					<html:img page="/images/button_addNewTopic.gif" border="0" align="absMiddle"/></html:link>
				主题数：${forum.topicCount } 贴子数：${forum.articleCount }</span>
				
				<!-- 页码显示 -->
				<%@ include file="/WEB-INF/pages/public/pagination.jspf"%>
        	</div>
	
	        <div class="cont2"><!-- 标签页 -->
				<ul><li class="mr">全部</li><!-- 
	                <li class="fmr">零回复</li>
	                <li class="fmr">最新发表</li> --></ul> 
	        </div>
	        
	        <div class="cont3"> 
				<!--主题列表表头-->
	            <h2 class="one">主 题</h2>
	            <h2 class="two">作 者</h2>
	            <h2 class="three">回复/查看</h2>
	            <h2 class="four">最后回复</h2>
	            <h2 class="five">最后回复时间&quot;</h2>
	         </div>
	
			<!-- 主题列表 -->
			<html:form action="/moderate">
			<div class="cont4"><table>
				<tbody><c:forEach items="${page.items}" var="topic" varStatus="status">
					<tr class="${((status.index mod 2) eq 0) ? 'odd' : 'even' }">
						<td class="one cutT"><div class="sel_type_cb">
							<itcastbbs:privilege resource="TOPIC" action="MOVE">
								<input type="checkbox" name="ids" value="${topic.id }">
							</itcastbbs:privilege></div><div class="typeimg">
							<html:img page="/images/topicType/${topic.type}.gif"/></div>
							<c:if test="${fn:length(topic.votes) gt 0}"><font color="blue">[投票]</font></c:if>
							<c:if test="${fn:length(topic.attachments) gt 0}"><font color="blue">[附件]</font></c:if>
							<html:link style="${topic.type}" action="/article?method=showTopic&id=${topic.id }&pageNum=1">${topic.title}</html:link><br></td>
						<td class="two">
							<html:link action="/user?method=profile&id=${topic.author.id }">${topic.author.nickname }</html:link>
						<br></td>
						<td class="three">${topic.replyCount }/${topic.viewCount } <br></td>
						<td class="four">
							<html:link action="/user?method=profile&id=${topic.lastReply.author.id }">${topic.lastReply.author.nickname }</html:link>
						<br></td>
						<td class="five">
							<html:link action="/article?method=showTopic&id=${topic.id }&pageNum=${page.totalPage + 1 }">
							</html:link>
						<br></td>
					</tr>
				</c:forEach>
				</tbody>
			</table></div>
			
			<div class="cont3"> 
				<!--主题列表表头-->
	            <h2 class="one">主 题</h2>
	            <h2 class="two">作 者</h2>
	            <h2 class="three">回复/查看</h2>
	            <h2 class="four">最后回复</h2>
	            <h2 class="five">最后回复时间</h2>
	         </div>
		
			<!-- 发新帖图片 -->
			<html:link action="/article?method=addTopicUI&forumId=${forum.id}">
				<html:img page="/images/button_addNewTopic.gif" border="0"/>
			</html:link>
			
			<!-- 页码显示 -->
			<%@ include file="/WEB-INF/pages/public/pagination.jspf"%>
			
			<itcastbbs:privilege resource="TOPIC" action="MOVE">
			<fieldset>
				<input type="hidden" name="method" id="moderateMethodName"/>
				<input type="hidden" name="forumId" value="${forum.id }"/>
				<legend>文章管理</legend>
					<table class="show_table">
						<tr align="center">
							<td rowspan="2">管理所选项:<br></td>
							<td><html:img page="/images/articleManage/topic_move.gif" /><br></td> 
							<td><html:img page="/images/articleManage/topic_delete.gif" /><br></td>
							<td><html:img page="/images/articleManage/topic_lock.gif" /><br></td> 
							<td>&quot;<html:img page="/images/articleManage/topic_unlock.gif" /><br></td> 
						</tr>
						<tr align="center">
							<td><input type="submit" onclick="return todo('moveTopicsUI');" value="移动"></td>
							<td><input type="submit" onclick="return todo('delTopicsUI');" value="删除"></td>
							<td><input type="submit" onclick="return todo('lockTopicsUI');" value="锁定"></td>
							<td><input type="submit" onclick="return todo('unlockTopicsUI');" value="解锁"></td>
						</tr>
					</table>
					<script type="text/javascript">
						function todo(method){
							var n = $("input[name=ids][checked]").size();
							if(n < 1){
								alert("请选择要操作的主题!");
								return false;
							}
							document.getElementById("moderateMethodName").value = method;
							return true;
						}
					</script>
			</fieldset></itcastbbs:privilege>
			</html:form>
		</div>
		
		<!--底部-->
		<%@ include file="/WEB-INF/pages/public/footer.jspf"%>
	</body>
</html>
