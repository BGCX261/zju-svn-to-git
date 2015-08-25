<%@ page language="java" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/widgets/xtree/xtree.js"></script>
		<link type="text/css" rel="stylesheet" href="${baseUrl }/widgets/xtree/xtree.css">
		<script type="text/javascript">
			function doLogout(){
				var url = '<html:rewrite action="/user.do?method=logout"/>';
				if(window.confirm("您确定要注销吗？")){
					window.parent.location.href = url;
				}
			}	
		</script>
	</head>

	<body style="margin: 10px;">
		<script type="text/javascript">
			var tree = new WebFXTree("导航菜单");
			
			var item0 = tree.add(new WebFXTreeItem("系统管理", "javascript:item0.toggle()"));
			item0.add(new WebFXTreeItem("注销 [${sessionScope['SESSION_KEY_CURRENTLY_LOGGED_ON_USER'].nickname}]", "javascript:doLogout()"));
			item0.add(new WebFXTreeItem("论坛首页", "<html:rewrite action='/index'/>", null, null, null, "_blank"));
			item0.add(new WebFXTreeItem("系统信息", "<html:rewrite action='/admin?method=info'/>", null, null, null, "right"));
			
			var item1 = tree.add(new WebFXTreeItem("论坛管理", "javascript:item1.toggle()"));
			item1.add(new WebFXTreeItem("版面管理", "<html:rewrite action='/admin/category?method=list'/>", null, null, null, "right"));
			item1.add(new WebFXTreeItem("角色管理", "<html:rewrite action='/admin/role?method=list'/>", null, null, null, "right"));
			item1.add(new WebFXTreeItem("群组管理", "<html:rewrite action='/admin/group?method=list'/>", null, null, null, "right"));
			item1.add(new WebFXTreeItem("会员管理", "<html:rewrite action='/admin/user?method=list'/>", null, null, null, "right"));
			item1.add(new WebFXTreeItem("搜索管理", "<html:rewrite action='/admin/search?method=info'/>", null, null, null, "right"));
		
			var item2 = tree.add(new WebFXTreeItem("系统配置", "javascript:item2.toggle()"));
			item2.add(new WebFXTreeItem("系统设置", "<html:rewrite action='/admin/config?method=editUI'/>", null, null, null, "right"));
			item2.add(new WebFXTreeItem("配置文件管理", "<html:rewrite action='/admin/config?method=listFiles'/>", null, null, null, "right"));
			
			document.write(tree);
			tree.expandAll();
		</script>
	</body>
</html>
