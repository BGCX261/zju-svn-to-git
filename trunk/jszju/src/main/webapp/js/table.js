// JavaScript Document
<!--
//jQuery(document).ready(function()
//{
//    var a = jQuery(".tableStyle");
//    for (var i = 0; i < a.length; i++)
//    {
//        defSenfe(a[i]);
//    }
//});
//window.onload= function()
//{
//    var a = jQuery(".tableStyle");
//    if (a == null)return;
//    for (var i = 0; i < a.length; i++)
//    {
//        defSenfe(a[i]);
//    }
//};

addOnloadEvent(allSenfe);

function allSenfe()
{
    var a = jQuery(".tableStyle");
    if (a == null)return;
    for (var i = 0; i < a.length; i++)
    {
        defSenfe(a[i]);
    }
}

function defSenfe(o)
{
    //senfe("表格名称","奇数行背景","偶数行背景","鼠标经过背景","点击后背景");
    senfe(o, "#fff", "#dce7fb", "#FFFFCC", "#86ba1e");
}

function senfe(o, a, b, c, d)
{
    var t = o.getElementsByTagName("tr");
    for (var i = 1; i < t.length; i++)
    {
        t[i].style.backgroundColor = (t[i].sectionRowIndex % 2 == 0) ? a : b;
        t[i].onclick = function()
        {
            if (this.x != "1")
            {
                this.x = "1";
                this.style.backgroundColor = (this.sectionRowIndex % 2 == 0) ? a : b;
            }
            else
            {
                this.x = "0";
                this.style.backgroundColor = (this.sectionRowIndex % 2 == 0) ? a : b;
            }
        };
        t[i].onmouseover = function()
        {
            if (this.x != "1")this.style.backgroundColor = c;
        };
        t[i].onmouseout = function()
        {
            if (this.x != "1")this.style.backgroundColor = (this.sectionRowIndex % 2 == 0) ? a : b;
        };
    }
}

addOnloadEvent(dlHeight);
function dlHeight()
{
    var tempHeight = 0;
    var s = 0;
    var dls = jQuery("dl");
    if (dls == null)return;
    if (dls.length == 0)return;
    var tempOffsetTop = dls[0].offsetTop;
    for (var i = 0; i < dls.length; i++)
    {
        if (tempOffsetTop != dls[i].offsetTop)
        {
            reSizeRowDl(tempHeight, s, i);
            s = i;
            tempOffsetTop = dls[i].offsetTop;
            tempHeight = 0;
        }
        var dtdd = dls[i].childNodes;
        for (var j = 0; j < dtdd.length; j++)
        {
            var height = jQuery(dtdd[j]).height();
            if (height > tempHeight)
            {
                tempHeight = height;
            }
        }
    }
     reSizeRowDl(tempHeight, s, dls.length);
}

function reSizeRowDl(h, s, e)
{
    var dls = jQuery("dl");
    for (var i = s; i < e; i++)
    {
        var dtdd = dls[i].childNodes;
        for (var j = 0; j < dtdd.length; j++)
        {
            if (jQuery.browser.msie && jQuery.browser.version == 6.0)
            {
                jQuery(dtdd[j])[0].style.height = h + "px";
            }
            jQuery(dtdd[j])[0].style.minHeight = h + "px";
        }
    }
}

addOnloadEvent(labelHeight);
function labelHeight()
{
    var tempHeight = 0;
    var s = 0;
    var labels = jQuery("label");
    if (labels == null)return;
    if (labels.length == 0)return;
    var tempOffsetTop = labels[0].offsetTop;
    for (var i = 0; i < labels.length; i++)
    {
        if (tempOffsetTop != labels[i].offsetTop)
        {
            reSizeRowLabel(tempHeight, s, i);
            s = i;
            tempOffsetTop = labels[i].offsetTop;
            tempHeight = 0;
        }
        var height = jQuery(labels[i]).height();
        if (height > tempHeight)
        {
            tempHeight = height;
        }
    }
     reSizeRowLabel(tempHeight, s, labels.length);
}

function reSizeRowLabel(h, s, e)
{
    var labels = jQuery("label");
    for (var i = s; i < e; i++)
    {
        if (jQuery.browser.msie && jQuery.browser.version == 6.0)
        {
            jQuery(labels[i])[0].style.height = h + "px";
        }
        jQuery(labels[i])[0].style.minHeight = h + "px";
    }
}
//-->