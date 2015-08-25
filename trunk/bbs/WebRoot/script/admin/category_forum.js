function validateCategoryForm(form){
    var result = ItCast.validateRequired([form.name, "分类名称"]);
	if(result){
		return ItCast.submitting(form);
	}
	return false;
}

function validateForumForm(form){
	var result = ItCast.validateRequired([form.name, "版面名称"]);
    if(result){
        return ItCast.submitting(form);
    }
	return false;
}

function deleteCategory(name, emptyForums){
	if(!emptyForums){
		window.alert("子版面的数量不为0, 不能删除!!");
		return false;
	}
	
	var msg = "您将要删除版面[" + name + "], 这是一个顶级版面\n";
	msg += "跟这个版面有关的信息都将被删除 !!\n";
	msg += "要继续吗 ?";
	return window.confirm(msg);
}

function deleteForum(name, totalTopics, totalArticles){
	var msg = "您将要删除版面[" + name + "], 这是一个二级版面\n";
	msg += "跟这个版面有关的信息都将被删除, 包括帖子 !!\n";
	msg += "这个版面下有 " + totalTopics + " 个主题, 共 " + totalArticles + " 篇文章 !\n";
	msg += "要继续吗 ?";
	return window.confirm(msg);
}

