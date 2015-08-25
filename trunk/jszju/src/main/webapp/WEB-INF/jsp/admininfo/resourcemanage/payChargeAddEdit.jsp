<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/share/shareInHeader.jsp"%>
<%@ include file="/WEB-INF/jsp/share/js.jsp" %>
<%@ include file="/WEB-INF/jsp/share/css.jsp" %>
<%@ include file="/WEB-INF/jsp/showDialog.jsp" %>
<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld" %>
        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="JavaScript" type=text/JavaScript>
     
     function doBack()
     {
     	 location.href='resourcemanageEdit.do?actionType=cancel';
     } 
     function doSave()
     {
     	document.forms[0].submit();
     }
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.post.js"></script>
        
<title>新建捐赠会费信息</title>
</head>
<body>

    <html:form action="/resourcemanageEdit.do" method="post" target="_self">
    <html:hidden property="paychargeDomain.id" />
    <html:hidden property="actionType"/>

    <div class="mainContent">
    
    <div class="warp">
      <span class="title">缴纳会员会费登记</span>
      <fieldset class="inputStyle3">
    	<label>
            <span>会员姓名：</span>
            <html:text property="paychargeDomain.name" />
        </label>
        <label>
            <span>会费金额：</span>
            <html:text property="paychargeDomain.money" value="0"/>&nbsp;&nbsp;*
        </label>
         <label>
            <span>捐赠金额：</span>
            <html:text property="paychargeDomain.contribute" value="0"/>&nbsp;&nbsp;*
        </label>
        </fieldset>
    </div>
    
    <div class="bcC">
        <input value="保存" class='btn' type="button" onclick="if(!confirm('确认保存修改?'))return;doSave()" />&nbsp;&nbsp;
        <input value="返回" class='btn' type="button" onclick="doBack()" />
    </div>
    </div>
</html:form>

</body>
</html>