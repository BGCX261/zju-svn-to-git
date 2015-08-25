function validateTopicForm(form){
	var arr = [
		form.title, "标题"
		// 只有一个图片时, 验证不通过
		// , form.content, "内容",
	];
	
	var result = ItCast.validateRequired(arr);
	if(result){
        return ItCast.submitting(form);
    }
	return false;
}

/**
 * 验证进行投票的表单
 * @param form
 */
function validateUserVoteForm(form){
	if( $(form).find("input:checked").size() == 0 ){
		alert("请至少选择一个投票选项");
		return false;
	}
	
	return ItCast.submitting(form);
} 