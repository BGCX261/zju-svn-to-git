<%@ page language="java" contentType="text/html;charset=utf-8"
    import="com.jsict.base.util.Consts"%>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/js.jsp"%>
<%@ include file="/WEB-INF/jsp/share/css.jsp"%>
<%@ include file="/WEB-INF/jsp/showDialog.jsp" %>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
<html>
    <body onload="error(errorCode)">
        
    </body>
<script type="text/javascript">
var errorCode = '<s:property value="#errorCode"/>';
function error(errorCode){
    if(errorCode=="1")
    alert("上传文件格式不符");
    else if(errorCode=="2")
    alert("上传文件大小不符");
    else
    alert(errorCode);
	//parent.success(fileName);
}
</script>
</html>
