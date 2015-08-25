<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>更新系统设置</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/admin/config.js"></script>
	</head>
	<body  style="margin: 10px;">

		<!-- 显示错误(如果有) -->
		<%@include file="/WEB-INF/pages/public/showErrors.jspf"%>

		<div>
			<html:form action="/admin/config" onsubmit="return validateSettingsForm(this)">
				<html:hidden property="method" value="edit" />

				<fieldset>
					<span class="item-name">论坛名称</span>
					<html:text property="cfg.name" size="50" />
					<br>

					<span class="item-name">论坛描述</span>
					<html:text property="cfg.description" size="50"></html:text>
				</fieldset>

				<fieldset>
					<span class="item-name">会员管理页面中每页显示会员人数</span>
					<html:text property="cfg.usersPerPage"></html:text>
					<br>

					<span class="item-name">每页显示主题数</span>
					<html:text property="cfg.topicsPerPage"></html:text>
					<br>

					<span class="item-name">每页显示文章数</span>
					<html:text property="cfg.repliesPerPage"></html:text>
					<br>
					<span class="item-name">每页显示搜索结果数</span>
					<html:text property="cfg.searchResultPerPage"></html:text>
					<br>
					<span class="item-name">每页最多显示的页码数量</span>
					<html:text property="cfg.viewPageCount"></html:text>
				</fieldset>

				<fieldset>
					<span class="item-name">头像文件的最大大小</span>
					<html:text property="cfg.avatarMaxFileSize" />
					字节
					<br>
					<span class="item-name">头像最大宽度</span>
					<html:text property="cfg.avatarMaxWidth" onchange="changeAvatarSize(this)" />
					像素
					<br>
					<span class="item-name">头像最大高度</span>
					<html:text property="cfg.avatarMaxHeight" onchange="changeAvatarSize(this)" />
					像素
					<br>
					<span class="item-name">&nbsp;</span>
					<span id="avatarPreview" style="border: 1px solid orange; margin: 5px;"> 头像大小预览 </span>
				</fieldset>

				<fieldset>
					<span class="item-name">两次文章发表的最小间隔时间</span>
					<html:text property="cfg.postDelay" />
					秒
				</fieldset>

				<fieldset>
					<span class="item-name">一篇文章中可以含有的附件的数量</span>
					<html:text property="cfg.attachmentMaxAmount"></html:text>
					<br>
					<span class="item-name">单个附件的最大大小</span>
					<html:text property="cfg.attachmentMaxFileSize" />
					字节
				</fieldset>

				<html:submit value="提交"></html:submit>
			</html:form>
		</div>

		<script type="text/javascript">
			document.getElementById("avatarPreview").style.width = "${settingsForm.cfg.avatarMaxWidth}px";
			document.getElementById("avatarPreview").style.height = "${settingsForm.cfg.avatarMaxHeight}px";
		</script>

	</body>
</html>