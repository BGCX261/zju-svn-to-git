<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>${topic.title }_${settings.name }</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
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

		<div id="contbox"> 
			<div class="cont1">
				<a href="javascript:copyToClipBoard()" title="复制本帖网址到剪帖板">复制本帖地址</a>
		 		<!-- ~~~~~~~~~ 显示分页有关信息 ~~~~~~~~~~ -->
		 		<%@ include file="/WEB-INF/pages/public/pagination.jspf"%>
			</div>

			<div class="cont2">
				<div class="title">
					<h2><span class="topictitle"><c:if test="${'LOCKED' eq topic.status.value }">【已锁定】</c:if>${topic.title }
						<span class="countinfo">阅读[${topic.viewCount }] 回复[${topic.replyCount }]</span></span>
					
						<!-- 发新贴与回复链接 -->
						<span class="newartlink">
							<html:link action="/article?method=addTopicUI&forumId=${topic.forum.id}">
							<html:img page="/images/button_addNewTopic.gif" align="absMiddle"/></html:link>
							<html:link action="/article?method=addReplyUI&topicId=${topic.id }&totalPage=${page.totalPage }">
							<html:img page="/images/button_addNewReply.gif" align="absMiddle"/></html:link>
						</span>
					</h2>
				</div>
		
				<!-- ============== 文章列表 =============== -->
				<div class="showart">
					<c:if test="${page.currentPage eq 1 }"><!-- 显示主题 -->
						<div class="authorinfo"><!-- 作者信息 -->
							<img src="<html:rewrite action='/user?method=showAvatar&id=${topic.author.id}'/>" onerror="showDefaultAvatar(this)">
							<br>主题数:${topic.author.topicCount }
							<br>文章数:${topic.author.articleCount }
						</div>
		
						<div class="artinfo">
							<h2><strong>楼主</strong>
								<html:link action="/user?method=profile&id=${topic.author.id }" target="_blank">
									<c:out value="${topic.author.nickname}"></c:out></html:link>
								<fmt:formatDate pattern="yy年MM月dd日 HH:mm" value="${topic.postTime }" />
							</h2>
		
							<!-- 显示投票 -->
							<%@ include file="/WEB-INF/pages/vote/show_vote.jspf"%>
		
							<!-- 显示文章内容 -->
							<p>${topic.content }</p>
		
							<!-- 显示附件 -->
							<c:set var="article" value="${topic}" scope="page"></c:set>
							<%@ include file="/WEB-INF/pages/attachment/show_attachment.jspf"%>
		
							<c:if test="${topic.editCount gt 0 }">
								这篇文章被编辑了 ${topic.editCount } 次. 最近一次更新是在
								<fmt:formatDate pattern="yy年MM月dd日 HH:mm" value="${topic.lastEditTime }" />
							</c:if>
		
							<!-- 管理 -->
							<fieldset style="color: #52a2ff">
								<legend>管理</legend>
								<a title="引用本楼文章" href="javascript:quoteArticle(${topic.id })">引用</a>
								<html:link action="/article?method=editTopicUI&id=${topic.id}&pageNum=${param.pageNum }">编辑</html:link>
								<html:link action="/vote?method=addUI&topicId=${topic.id }">添加投票</html:link>
								<html:link action="/attachment?method=addUI&articleId=${topic.id}&topicId=${topic.id }&pageNum=${param.pageNum }">添加附件</html:link>
								
								<html:link action="/moderate?method=delTopicsUI&ids=${topic.id }&pageNum=${param.pageNum }">删除</html:link>
								<html:link action="/moderate?method=moveTopicsUI&ids=${topic.id }&pageNum=${param.pageNum }">移动</html:link>
							
								<html:form action="/moderate">
									<html:hidden property="method" value="changeTopicType" />
									<html:hidden property="id" value="${topic.id}" />
									设置主题类型为: <html:select property="topicType" value="${topic.type }">
										<html:optionsCollection name="topicTypes" value="value" label="label" />
									</html:select>
									<html:submit>提交</html:submit>
								</html:form>
								
								<html:form action="/moderate">
									<html:hidden property="method" value="changeTopicStatus" />
									<html:hidden property="id" value="${topic.id}" />
									设置主题状态为: <html:select property="topicStatus" value="${topic.status }">
										<html:optionsCollection name="topicStatuses" value="value" label="label" />
									</html:select>
									<html:submit>提交</html:submit>
								</html:form>
							</fieldset>
						</div>
					</c:if>
				</div>
		
				<!-- ========= 显示回复列表 ========= -->
				<c:forEach items="${page.items}" var="reply" varStatus="state">
				<div class="showart">
					<div class="authorinfo"><!-- 作者信息 -->
							<img src="<html:rewrite action='/user?method=showAvatar&id=${reply.author.id}'/>" onerror="showDefaultAvatar(this)">
							<br>主题数:${reply.author.topicCount }
							<br>文章数:${reply.author.articleCount }
					</div>
		
					<div class="artinfo">
						<h2><strong>${reply.floor }楼</strong>
							<html:link action="/user?method=profile&id=${reply.author.id }" target="_blank">
								<c:out value="${reply.author.nickname}"></c:out></html:link>
							<fmt:formatDate pattern="yy年MM月dd日 HH:mm" value="${reply.postTime }" />
						</h2>
							
						<!-- 显示文章内容 -->
						<p>${topic.content }</p>
	
						<!-- 显示附件 -->
						<c:set var="article" value="${topic}" scope="page"></c:set>
						<%@ include file="/WEB-INF/pages/attachment/show_attachment.jspf"%>
	
						<c:if test="${topic.editCount gt 0 }">
							这篇文章被编辑了 ${topic.editCount } 次. 最近一次更新是在
							<fmt:formatDate pattern="yy年MM月dd日 HH:mm" value="${topic.lastEditTime }" />
						</c:if>
			
						<!-- 管理 -->
						<fieldset style="color: #52a2ff">
							<legend>管理</legend>
							<a title="引用本楼文章" href="javascript:quoteArticle(${a.id })">引用</a>
							<html:link action="/article?method=editReplyUI&id=${a.id}&pageNum=${param.pageNum }">编辑</html:link>
							<html:link action="/moderate?method=delReplyUI&id=${a.id }&pageNum=${param.pageNum }">删除</html:link>
							<html:link action="/attachment?method=addUI&articleId=${a.id}&topicId=${topic.id }&pageNum=${param.pageNum }">添加附件</html:link>
						</fieldset>
					</div>
				</div>
				</c:forEach>
		
				<!-- 显示分页信息 -->
				<%@ include file="/WEB-INF/pages/public/pagination.jspf"%>
		
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
			</div>
		</div>
		
		<!--底部-->
		<%@ include file="/WEB-INF/pages/public/footer.jspf"%>
	</BODY>
</HTML>
