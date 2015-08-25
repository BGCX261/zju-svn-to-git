// 增长的索引
var voteItemIndex = 0;
// 数量
var voteCounter = 0;
// 模板
var voteItemTemplate = '';
// voteItemTemplate += '<div id="voteItem_#voteItemIndex#" style="width: 100%">';
// voteItemTemplate += '	<span style="width: 80px">投票项</span>';
// voteItemTemplate += '	<input type="text" name="items" size="55"/>';
// voteItemTemplate += '	<input type="button" class="btnDelVoteItem" onclick="delVoteItem(#voteItemIndex#)" value="删除" disabled="disabled">';
// voteItemTemplate += '	<input type="button" id="btnVoteItemUp" onclick="voteItemUp(#voteItemIndex#)" value="上移">';
// voteItemTemplate += '	<input type="button" id="btnVoteItemDown" onclick="voteItemDown(#voteItemIndex#)" value="下移">';
// voteItemTemplate += '</div>';
voteItemTemplate += '<tr id="voteItem_#voteItemIndex#">';
voteItemTemplate += '    <td>投票选项 <span class="voteOption">#voteCounter#</span></td>';
voteItemTemplate += '    <td><input type="text" name="items" class="input1"/>';
voteItemTemplate += '        <input type="button" class="btnDelVoteItem" onclick="delVoteItem(#voteItemIndex#)" value="删除">';
voteItemTemplate += '        <input type="button" id="btnVoteItemUp" onclick="voteItemUp(#voteItemIndex#)" value="上移">';
voteItemTemplate += '        <input type="button" id="btnVoteItemDown" onclick="voteItemDown(#voteItemIndex#)" value="下移">';
voteItemTemplate += '    </td>';
voteItemTemplate += '</tr>';

/**
 * 添加投票选项
 */
function addVoteItem() {
    voteItemIndex ++;
    voteCounter ++;

    var s = voteItemTemplate.replace(/#voteItemIndex#/g, voteItemIndex).replace(/#voteCounter#/g, voteCounter);
    $("#voteItemFields").append(s);
    if (voteCounter > 2) {
        $(".btnDelVoteItem").removeAttr("disabled");
    } else {
        $(".btnDelVoteItem").attr("disabled", "disabled");
    }
}

/**
 * 删除投票选项
 * @param index
 */
function delVoteItem(index) {
    $("#voteItem_" + index).remove();
    voteCounter = voteCounter - 1;
    if (voteCounter <= 2) {
        $(".btnDelVoteItem").attr("disabled", "disabled");
        // alert("最后 2 个投票项不能删除");
    }

    refreshVoteItemSequenceNumber();
}

/**
 * 上移投票项
 * @param index
 */
function voteItemUp(index) {
    var item = $("#voteItem_" + index);
    var other = item.prev(); // 上面的元素
    other.before(item); // 如果已在最上面, 则没有改变, 不会报错.

    refreshVoteItemSequenceNumber();
}

/**
 * 下移投票项
 * @param index
 */
function voteItemDown(index) {
    var item = $("#voteItem_" + index);
    var other = item.next(); // 下面的元素
    other.after(item); // 如果已在最下面, 则没有改变, 不会报错.

    refreshVoteItemSequenceNumber();
}

/**
 * 重新指定投票项的序号，应在删除、上移或下移后调用这个方法，以保证序号是正确的
 */
function refreshVoteItemSequenceNumber() {
    $(".voteOption").each(function(index) {
        this.innerHTML = index + 1;
    });
}

/**
 * 验证投票表单
 * @param form
 */
function validateVoteForm(form) {
    if (!ItCast.validateRequired([form.title, "投票的主题"])) {
        return false;
    }

    var validItemCount = 0;
    var voteItems = $("input[name^=item]"); // 以'item('开头的input元素
    voteItems.each(function(index) {
        if ($.trim(this.value).length > 0) {
            validItemCount ++;
        }
    });

    if (validItemCount < 2) {
        alert("投票项不能少于少2个");
        return false;
    }

    $("input[type=submit]").attr("disabled", "disabled");
    $("input[type=submit]").val("正在提交, 请稍候...");
    return true;
}
