<%@ page pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>后台管理</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
	</head>

	<frameset border="0" rows="70px,20px,*" border="0" id="framesetRows">
		<frame name="top" src="${baseUrl }/admin/top.do" noresize="noresize" scrolling="no">
		<frame name="top" src="${baseUrl }/admin/top2.do" noresize="noresize" scrolling="no">
		<frameset cols="200px,17px,*" border="0" id="framesetCols">
			<frame name="left" src="${baseUrl }/admin/left.do" noresize="noresize">
			<frame name="middle" src="${baseUrl }/admin/middle.do" noresize="noresize" scrolling="no">
			<frame name="right" src="${baseUrl }/admin.do?method=info" noresize="noresize">
		</frameset>
	</frameset>
</html>
