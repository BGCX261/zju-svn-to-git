<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/css.jsp" %>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
	<script language="JavaScript" type=text/JavaScript>
        function doBack()
        {
            location.href='schoolFellowNotes.do?actionType=cancel';
        }
	</script>
        
<head>
<title>校友会公告通知文章</title>
</head>
<body>

    <div class="warp">
    <span class="title">校友会公告通知文章</span>
        <fieldset class=inputStyle3>
        <label>
            <span>校友会公告通知ID：</span>
	        ${ domain.id}
        </label>
        <label>
            <span>校友会公告通知标题：</span>
            ${ domain.title}
        </label>  
        <label>
            <span>校友会公告通知时间：</span>
            ${domain.time}
        </label>
 		<label>
 			<span>显示否：</span>
 			${domain.isview}
 		</label>
          <label>
            <span>点击数量：</span>
            ${ domain.visittime}
        </label>  
        <br>
        <label>
            <span>内容：</span>
            ${ domain.content}
        </label>   
    </fieldset>
    </div>
    
    <div class="bcC">
        <input value="返回" class='btn' type="button" onclick="doBack()" />
    </div>

</body>
</html:html>