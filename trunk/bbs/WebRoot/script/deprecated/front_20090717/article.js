function validateTopicForm(form){
	var arr = [
		form.title, "标题",
		// 只有一个图片时, 验证不通过
		// form.content, "内容", 
	];
	
	var result = validateRequired(arr);
	if(!result){
		return false;
	}

	submitting(form);
	return true;
}

function validateUserVoteForm(form){
	if( $(form).find("input:checked").size() == 0 ){
		alert("请至少选择一个项目");
		return false;
	}
	
	submitting(form);				
	return true;
} 