
function validateRecreateIndicesForm(form){
	if($("input[name=recreateAll][value=false]").attr("checked") == true){
		if($("input[name=startDate]").val().length == 0){
			alert("请选择时间范围的开始时间 ！");
			$("input[name=startDate]").focus();
			return false;
		} 
		else if ($("input[name=endDate]").val().length == 0){
			alert("请选择时间范围的结束时间 ！");
			$("input[name=endDate]").focus();
			return false;
		}
	}
	else {
		var msg = "您选择的是建立一个全新的索引数据库,\n";
		msg += "这样将会删除所有存在的纪录!!\n";
		msg += "要继续吗?";
		if( ! window.confirm(msg) ){
			return false;
		}
	}

	$("input[type=submit]").attr("disabled", true);
	$("input[type=submit]").val("正在创建索引...");
	return true;
}

