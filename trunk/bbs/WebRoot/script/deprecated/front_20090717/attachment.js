
function validateAttachmentForm(form){
	submitting(form);
	return true;
}

var attachmentTotal = 0;// 文件增长的索引
var attachmentCounter = 0;// 文件的数量

var attachmentTemplate = '';// 模板
attachmentTemplate += '<div id="fileDiv_#attachmentCounter#">';
attachmentTemplate += '	<div>';
attachmentTemplate += '		<span style="width: 80px">文件名</span>';
attachmentTemplate += '		<input type="file" size="45" name="file(#attachmentCounter#)" />';
attachmentTemplate += '		<input type="button" onclick=delAttachmentField("#attachmentCounter#") value="删除"/>';
attachmentTemplate += '	</div>';
attachmentTemplate += '	<div id="fileDiv_part2_#attachmentCounter#">';
attachmentTemplate += '		<span style="width: 80px">文件说明</span>';
attachmentTemplate += '		<textarea name="desc(#attachmentCounter#)" rows="3" cols="50"></textarea>';
attachmentTemplate += '	</div>';
attachmentTemplate += '	<hr>';
attachmentTemplate += '</div>';

// 添加
function addAttachmentField() {
	var s = attachmentTemplate.replace(/#attachmentCounter#/g, attachmentTotal);
	$("#attachmentFields").append(s);
	attachmentTotal = attachmentTotal + 1;
	attachmentCounter = attachmentCounter + 1;
	if (attachmentCounter == attachmentLimit) {
		$("#btnAddFile").attr("disabled", "disabled");
	}
}

// 删除
function delAttachmentField(index) {
	$("#fileDiv_" + index).remove();
	$("#fileDiv_part2_" + index).remove();
	attachmentCounter = attachmentCounter - 1;
	$("#btnAddFile").removeAttr("disabled");
}