<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
		<script language="JavaScript" type=text/JavaScript>

     function doCommit()
     {
     	  var word = document.getElementsByName("commentDomain.commentcontent")[0].value;
   		 var num = word.length;
   		 if (num <= 3)
   			 {
    			 alert("对不起，评论至少要３个字符！")
    			 return false;
    		}
    	else if(num>=255)
    	{
    		alert("对不起，评论不能超过255个字符！")
    		return false;
    	}
     	
     	window.parent.frames["commnet_view"].document.getElementById("commentArticleId").value = document.getElementById("articleId").value;
     	document.forms[0].actionType.value="commentup";
     	document.forms[0].submit();
     	document.forms[0].reset();
     }
</script>
	</head>

	<body>
		<html:form action="/comment.do" method="post">
			<input type="hidden" name="actionType" />
			<input type="hidden" id="articleId" name="articleId" />
			<!--文章评论Begin-->
			<div class="comment_list">
				<div class="comment_item">
					<div class="comment_bar">
						<img
							src="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/img/ico_2.gif" />
						【
						<strong>网友评论</strong>】

					</div>
					<div class="comment_content">

						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							bgcolor="#CCCCCC">
							<tr>
								<td width="17%" height="30" align="right" bgcolor="#F9F9F9">
									评论内容：
								</td>
								<td width="83%" bgcolor="#FFFFFF">
									&nbsp;
									<html:textarea property="commentDomain.commentcontent"
										title="请填写评论（3到255个字符）" rows="5" cols="65"></html:textarea>
								</td>
							</tr>
							<tr>
								<td>
									<img name="reg" onclick="doCommit()" style="cursor: pointer"
										alt=""
										src="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/cms_member/img/comment.png" />
								</td>
							</tr>
						</table>

					</div>
				</div>
			</div>
			<!--文章评论End-->
		</html:form>
	</body>
</html>
