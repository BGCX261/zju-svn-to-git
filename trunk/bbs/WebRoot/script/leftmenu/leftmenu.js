
/**
 * 显示第二级菜单
 * @param id 用于显示第二级菜单的元素的id
 */
function showSecond(id) {
    var firstimg = $("#first_img_" + id);
    var second = $("#second_" + id);
    second.toggle();
    if (second.css("display") == "none") {
        firstimg.attr("src", baseUrl + "/script/leftmenu/images/icon_menu_02.gif");
    }
    else {
        firstimg.attr("src", baseUrl + "/script/leftmenu/images/icon_menu_03.gif");
    }
}
