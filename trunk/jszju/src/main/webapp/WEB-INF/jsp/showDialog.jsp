<c:if test="${errorList!=null}">

<div class="error" id="errorDivServer">
<c:forEach var="messageInfo" items="${ errorList}">
<li>${messageInfo.message }</li>
</c:forEach>
</div>
<script>
jQuery(document).ready(
function(){
	var errorDivObj=jQuery("#errorDivServer")
	errorDivObj.css("display","none");
	ymPrompt.errorInfo({message:errorDivObj.html()});
	errorDivObj.css("display","none");
}
);
</script>
</c:if> 
