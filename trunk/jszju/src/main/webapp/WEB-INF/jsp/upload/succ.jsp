<%@ page language="java" contentType="text/html;charset=utf-8"
    import="com.jsict.base.util.Consts"%>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/js.jsp"%>
<%@ include file="/WEB-INF/jsp/share/css.jsp"%>
<%@ include file="/WEB-INF/jsp/showDialog.jsp" %>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
<html>
    <head>
        <title>上传成功</title>
    </head>
    <body onload="test(fileName)">
        上传成功!<br>
		文件标题:<s:property value=" + title"/><br>
		文件为：<img src="<s:property value="'/ictmap/upload/' + uploadFileName"/>"/><br>
    </body>
<script type="text/javascript">
var fileName = '<s:property value="finalPath"/>';
function test(fileName){

	parent.success(fileName);
}
</script>
</html>
