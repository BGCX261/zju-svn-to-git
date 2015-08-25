<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/css.jsp" %>
        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
	<script language="JavaScript" type=text/JavaScript>
        function doBack()
        {
            location.href='ArticleContentEdit.do?actionType=cancel';
        }
	</script>
        
<head>
<title>本会新闻文章明细</title>
</head>
<body>

    <html:form action="/ArticleContentEdit.do" method="post" target="_self">
    <html:hidden property="domain.id" />
    <input type="hidden" name="actionType"/>

    <div class="warp">
    <span class="title">本会新闻文章明细明细</span>
        <fieldset class=inputStyle3>
        <label>
            <span>文章ID：</span>
	        ${ domain.id}
        </label>
        <label>
            <span>文章标题：</span>
            ${ domain.title}
        </label>  
        <label>
        	<span>文章属性：</span>
        	${domain.attribute }
        </label>
   
        <label>
            <span>发布时间：</span>
            ${domain.inputData}
        </label>
        <label>
            <span>录入员：</span>
            ${ domain.inputAdmin}
        </label>
 		<label>
 			<span>显示否：</span>
 			${domain.isView}
 		</label>
        <label>
            <span>状态：</span>
            ${ domain.status}
        </label>    
         <label>
            <span>栏目ID：</span>
            ${ domain.channelId}
        </label>    
          <label>
            <span>作者：</span>
            ${ domain.author}
        </label>  
          <label>
            <span>点击数量：</span>
            ${ domain.visitTime}
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
    <!-- end #mainContent -->
</html:form>

</body>
</html:html>