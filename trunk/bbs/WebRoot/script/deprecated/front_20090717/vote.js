
// 增长的索引
var voteItemIndex = 0;
// 数量
var voteCounter = 0;
// 模板
var voteItemTemplate = '';
voteItemTemplate += '<div id="voteItem_#voteItemIndex#" style="width: 100%">';
voteItemTemplate += '	<span style="width: 80px">投票项</span>';
voteItemTemplate += '	<input type="text" name="items" size="55"/>';
voteItemTemplate += '	<input type="button" class="btnDelVoteItem" onclick="delVoteItem(#voteItemIndex#)" value="删除" disabled="disabled">';
voteItemTemplate += '	<input type="button" id="btnVoteItemUp" onclick="voteItemUp(#voteItemIndex#)" value="上移">';
voteItemTemplate += '	<input type="button" id="btnVoteItemDown" onclick="voteItemDown(#voteItemIndex#)" value="下移">';
voteItemTemplate += '</div>';

// 添加
function addVoteItem() {
	var s = voteItemTemplate.replace(/#voteItemIndex#/g, voteItemIndex);
	$("#voteItemFields").append(s);
	voteItemIndex ++;
	voteCounter ++;
	if(voteCounter > 2){
		$(".btnDelVoteItem").removeAttr("disabled");
	}
}

// 删除
function delVoteItem(index) {
	$("#voteItem_" + index).remove();
	voteCounter = voteCounter - 1;
	if(voteCounter <= 2){
		$(".btnDelVoteItem").attr("disabled", "disabled");
		// alert("最后 2 个投票项不能删除");
	}
}

// 上移
function voteItemUp(index) {
	var item = $("#voteItem_" + index);
	var other = item.prev(); // 上面的元素
	other.before(item); // 如果已在最上面, 则没有改变, 不会报错.
}

// 下移
function voteItemDown(index) {
	var item = $("#voteItem_" + index);
	var other = item.next(); // 下面的元素
	other.after(item); // 如果已在最下面, 则没有改变, 不会报错.
}

function validateVoteForm(form){
	// 空白的投票主题是无效的
	if ( $.trim($("input[name=title]").val()).length == 0 ){
		alert("请输入投票的主题");
		$("input[name=title]").focus();
		return false;
	}
	
	var validItemCount = 0;
	var voteItems = $("input[name^=item]"); // 以'item('开头的input元素
	voteItems.each(function(index){
		if( $.trim(this.value).length > 0 ){
			validItemCount ++;
		}
	});
	
	if( validItemCount < 2){
		alert("投票项不能少于少2个");
		return false;
	}
	
	$("input[type=submit]").attr("disabled", "disabled");
	$("input[type=submit]").val("正在提交, 请稍候...");
	return true;
}