var isIE = (navigator.appName=="Microsoft Internet Explorer");

function centerWindow(url,args,width,height,modal){
    var options = new Array();
    if(modal){
        options.push("dialogHeight:"+height+"px");
        options.push("dialogWidth:"+width+"px");
        options.push("center:1");
        options.push("help:0");
        options.push("edge:raised");
        options.push("scroll:yes");
        options.push("resizable:0");
        options.push("status:0");
        options.push("unadroned:yes");
        options.push("dialogHide:yes");
        if(modal==true){
            window.showModalDialog(url,args,options.join(";"));
        }else{
            window.showModelessDialog(url,args,options.join(";"));
        }
    }else{
        var left = (window.screen.width-width)/2;
        var top = (window.screen.height-height)/2;
        options.push("height="+height);
        options.push("width="+width);
        options.push("left="+left);
        options.push("top="+top);
        options.push("modal=1");
        options.push("titlebar=yes");
        options.push("menubar=no");
        options.push("toolbar=no");
        options.push("scrollbars=yes");
        options.push("status=no");
        options.push("location=no");
        return window.open(url,'',options.join(","));
    }
}
function redirect(url){
    if(!url || url.length==0){
        return;
    }
    if(isIE){
        if(window.dialogArguments){
            window.dialogArguments.location = url;
        }
    }else{
        if(window.opener){
            window.opener.location = url;
        }
    }
}




