<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%--
<div
        style="width: 99%; position: relative; vertical-align: middle; height: 24px; font-family: sans-serif; font-size: 10pt;">
    <div
            style="text-align: left; width: 40%; display: inline; left: 0; position: absolute; vertical-align: middle; height: 99%;">
        ${firstResult} &nbsp;-&nbsp; ${lastResult} &nbsp;/&nbsp; ${totalCount}
    </div>

    <div
            style="text-align: right; display: inline; width: 49%; right: 0; position: absolute; vertical-align: middle; height: 99%">
        ${currentPageNo}
        <input type="hidden" id="currentPageNo" value="${currentPageNo}">
        &nbsp;/&nbsp; ${totalPages}
        <input type="hidden" id="totalPages" value="${totalPages}">
        &nbsp;&nbsp;
        <a href="#" onclick="_prev('<%=request.getParameter("actionType") %>');">&lt;</a>
        <input type="text" id="pageNo" name="pageNo"
               style="width: 20px; height: 12px; vertical-align: middle;"
               value="<%=StringUtils.trimToEmpty( request.getParameter("pageNo")) %>">
        <a href="#" onclick="_next('<%=request.getParameter("actionType") %>');">&gt;</a>
        <input onclick="_goto('<%=request.getParameter("actionType") %>');" style="float: none;" value="GO" type="button" class="btn"/>
    </div>
    <input type="hidden" name="pagesize" value="10">
</div>
--%>
<%
    String actionType = "";
    if (request.getParameter("actionType") != null)
    {
        actionType = request.getParameter("actionType");
    }
    if (request.getAttribute("actionType") != null)
    {
        actionType = request.getAttribute("actionType").toString();
    }
%>
<div class="page">
    <div class="Pleft">记录：${firstResult} - ${lastResult} / ${totalCount}</div>
    <div class="Pright">
        <a href="#" onClick="_prev('<%=actionType%>');" class="arrowL">&nbsp;</a>
        <input type="text" id="pageNo" name="pageNo" value="<%=StringUtils.trimToEmpty( request.getParameter("pageNo")) %>" class="pageinput">
        <a href="#" onClick="_next('<%=actionType%>');" class="arrowr">&nbsp;</a>
        <input onClick="_goto('<%=actionType%>');" style="float: none;" value="GO" type="button" class="bun_mini bun_mini_sp"/>
    </div>
    <div class="Pright" style="padding: 4px">
       页码：${currentPageNo} / ${totalPages}
    </div>
    <input type="hidden" id="currentPageNo" value="${currentPageNo}">
    <input type="hidden" id="totalPages" value="${totalPages}">
    <input type="hidden" name="pagesize" value="10">
</div>
