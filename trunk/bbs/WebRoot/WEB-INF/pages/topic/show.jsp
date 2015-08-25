<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>${topic.title }_${settings.name }</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<link rel="stylesheet" type="text/css" href="${baseUrl }/style/itcastbbs_front_topic_show.css" charset="utf-8">
		<script type="text/javascript" src="${baseUrl }/script/front/article.js"></script>

		<script type="text/javascript" src="${baseUrl}/widgets/fckeditor/fckeditor.js"></script>
		<script type="text/javascript">
		    var quoteArticleUrl = '<html:rewrite action="/article?method=addReplyUI&topicId=${topic.id }&totalPage=${page.totalPage }"/>';
		    function quoteArticle(quotedArticleId){
		    	window.location.href = quoteArticleUrl + "&quotedArticleId=" + quotedArticleId;
		    }
		    
		    function copyToClipBoard(){
		    	var title = '${topic.title}';
		        var url = '${baseUrl}/article.do?method=showTopic&id=${topic.id }&pageNum=${param.pageNum }';
				var clipBoardContent = title + "\r\n" + url;
				if (window.clipboardData) {
        			window.clipboardData.setData("Text",clipBoardContent);
        		}
				alert("地址已经复制到粘贴板!");
			}
			
			function showDefaultAvatar(img){
				img.src = '<html:rewrite page="/images/defaultAvatar.gif"/>';
			}
			
			function validateQuickReplyForm(form){
				$("#quickPostSubmitImage").attr("disabled", "disabled");
				return true;
			}
			
			function gotoPage(pageNum){
				window.location="?method=showTopic&id=${topic.id}&pageNum=" + pageNum;
			}
		</script>
	</head>
	<body>
		<!-- 顶部 -->
		<%@ include file="/WEB-INF/pages/public/header.jspf"%>
		<a name="top"></a>
		
		<!-- 顶部超链接 -->
		<c:if test="${page.currentPage eq 1 }">
		<!--  主帖显示start  -->
		<div class="mainbox">
		    <h1 class="title">
		    	<c:out value="${topic.title}"/>
		    	<span class="info">阅读[${topic.viewCount }] 回复[${topic.replyCount }]</span>
		    	 <span class="action">
		       <html:link action="/article?method=addTopicUI&forumId=${topic.forum.id}">
				<html:img page="/images/button_addNewTopic.gif" align="absMiddle"/></html:link>
				<html:link action="/article?method=addReplyUI&topicId=${topic.id }&totalPage=${page.totalPage }">
				<html:img page="/images/button_addNewReply.gif" align="absMiddle"/></html:link>
		    </span>
		    </h1>
		    
		    <table>
		        <tbody>
		        <tr>
		            <td class="post_author">
		                <div class="nickname">${topic.author.nickname }</div>
		                <div class="nickname">${topic.author.memberid }</div>
		                <div class="avatar">
		                	<img onerror="showDefaultAvatar(this)" 
		                		src="<html:rewrite action='/user?method=showAvatar&id=${topic.author.id}'/>"/>
		                </div>
		                <div class="info">
		                    主题数：${topic.author.topicCount }<br>
		                    文章数：${topic.author.articleCount }<br>
		                </div>
		            </td>
		            <td class="post_content">
		                <div class="info">
		                    <strong class="floor">楼主</strong>
		                    <span class="post_time">发表于 
		                    	<fmt:formatDate pattern="yy年MM月dd日 HH:mm" value="${topic.postTime }" />
		                    </span>
		                </div>
		                
		                <!-- 显示文章内容 -->
		                <div class="content">${topic.content }</div>
		                 
		                <!-- 显示附件 -->
		                <div class="attachments">
							<c:set var="article" value="${topic}" scope="page"></c:set>
							<%@ include file="/WEB-INF/pages/attachment/show_attachment.jspf"%>
							<c:remove var="article" scope="page"/>
						</div>
		            </td>
		        </tr>
		        <tr>
		            <td class="post_author">&nbsp;</td>
		            <td class="action">
		                <a title="引用本楼回复" href="javascript:quoteArticle(${topic.id })">引用</a> |
		                <a title="回到顶部" href="#top">TOP</a>
		            </td>
		        </tr>
		        </tbody>
		    </table>
		    </div>
		    </c:if>
		<!-- 主帖显示end -->
<div class="mainbox">
				<!-- ========= 显示回复列表 ========= -->
				<c:forEach items="${page.items}" var="reply" varStatus="state">
				 <table>
		        <tbody>
		        <tr>
		            <td class="post_author">
		                <div class="nickname">${reply.author.nickname }</div>
		                <div class="nickname">${topic.author.memberid }</div>
		               <img src="<html:rewrite action='/user?method=showAvatar&id=${reply.author.id}'/>" onerror="showDefaultAvatar(this)">
							<br>主题数:${reply.author.topicCount }
							<br>文章数:${reply.author.articleCount }
		            </td>
		            <td class="post_content">
		                <div class="info">
		                   <h2><strong>${reply.floor }楼</strong>
							<html:link action="/user?method=profile&id=${reply.author.id }" target="_blank">
								<c:out value="${reply.author.nickname}"></c:out></html:link>
							<fmt:formatDate pattern="yy年MM月dd日 HH:mm" value="${reply.postTime }" />
						</h2>
		                </div>
		                
		                <!-- 显示文章内容 -->
		                <div class="content">${reply.content }</div>
		                
		                	<c:if test="${reply.editCount gt 0 }">
							这篇文章被编辑了 ${reply.editCount } 次. 最近一次更新是在
							<fmt:formatDate pattern="yy年MM月dd日 HH:mm" value="${topic.lastEditTime }" />
						</c:if>
		                 
		            </td>
		        </tr>
		        <tr>
		            <td class="post_author">&nbsp;</td>
		            <td class="action">
		                <a title="引用本楼回复" href="javascript:quoteArticle(${reply.id })">引用</a> |
		                <a title="回到顶部" href="#top">TOP</a>
		            </td>
		        </tr>
		        </tbody>
		    </table>
				</c:forEach>
				</div>
		<div class="mainbox action1">
		    	<div class="cont1">
		 		<!-- ~~~~~~~~~ 显示分页有关信息 ~~~~~~~~~~ -->
		 		<%@ include file="/WEB-INF/pages/public/pagination.jspf"%>
			</div>
		</div>
				<html:form action="/article" onsubmit="return validateQuickReplyForm(this)">
					<html:hidden property="method" value="addReply" />
					<html:hidden property="topicId" value="${topic.id }" />
					<html:hidden property="totalPage" value="${page.totalPage }" />
		
					<!-- 进入高级回复页面 -->
					<html:link action="/article?method=addReplyUI&topicId=${topic.id}&totalPage=${page.totalPage }">
						<html:img page="/images/button_userAdvancedReply.gif" />
					</html:link>
		
					<strong>快速回复</strong>
		
					<html:textarea property="content"></html:textarea>
					<script type="text/javascript">
						var editor = new FCKeditor('content');
						editor.BasePath="${baseUrl}/widgets/fckeditor/";
						editor.ToolbarSet="${'simple'}";
						editor.Width="100%";
						editor.Config['EnableAdvanceTable'] = false;
						editor.Config['SkinPath'] = "${baseUrl}/widgets/fckeditor/editor/skins/silver/";
						editor.ReplaceTextarea();
					</script>
		
					<html:image page="/images/button_publish.gif"></html:image>
				</html:form>
			
		
		<!--底部-->
		<%@ include file="/WEB-INF/pages/public/footer.jspf"%>
	</BODY>
</HTML>
