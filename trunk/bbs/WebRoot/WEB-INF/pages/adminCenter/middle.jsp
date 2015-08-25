<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/jquery.js"></script>
		<script type="text/javascript">
			$(document.body).toggle(function(){
					var frameset = window.parent.document.getElementById("framesetCols");
					frameset.cols = "0,17px,*";
					$("#txtMiddleTitle").text(4);
				}, function(){
					var frameset = window.parent.document.getElementById("framesetCols");
					frameset.cols = "200,17px,*";
					$("#txtMiddleTitle").text(3);
				}
			);
		</script>
	</head>
	<body style="background-color: #7b9ad6; margin: 0px;padding: 0px;">
		<div style="font-family: Webdings;position: absolute; top: 40%" id="txtMiddleTitle">3</div>
	</body>
</html>
