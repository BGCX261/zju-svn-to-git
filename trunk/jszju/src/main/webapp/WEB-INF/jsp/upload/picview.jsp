<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/css.jsp" %>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>
        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
	<script language="JavaScript" type=text/JavaScript>
        function doBack()
        {
            location.href='fileView.do?actionType=cancel';
        }
	</script>
        
<head>
<title>图片列表明细</title>
</head>
<body>

    <html:form action="/fileView.do" method="post" target="_self">
    <input type="hidden" name="actionType"/>

    <div class="warp">
    <span class="title">图片列表明细</span>
        <fieldset class=inputStyle3>
        <label>
            <span>图片ID：</span>
	        ${ entity.id}
        </label>
        <label>
            <span>图片标题：</span>
            ${ entity.title}
        </label>  
        <label>
        	<span>所在模块：</span>
        	${entity.dep }
        </label>
   
        <label>
            <span>是否显示：</span>
            ${entity.display}
        </label>
        <label>
            <span>文件名：</span>
            ${ entity.filename}
        </label>
 		<label>
 			<span>图片路径：</span>
 			${entity.path}
 		</label>
        <label>
            <span>链接地址：</span>
            ${ entity.linkaddress}
        </label>   
        <label>
            <span>图片内容：</span>
            ${ entity.discrption}
        </label>    
    </fieldset>
    </div>
    
    <div class="bcC">
        <input value="返回" class='btn' type="button" onclick="doBack()" />
    </div>
    <!-- end #mainContent -->
</html:form>

</body>
</html:html>