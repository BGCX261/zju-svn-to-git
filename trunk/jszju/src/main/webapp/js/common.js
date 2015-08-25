var windowOnloadFunArr = [];
function addOnloadEvent(event)
{
    windowOnloadFunArr[windowOnloadFunArr.length] = event;
}

window.onload = function()
{
    for (var i = 0; i < windowOnloadFunArr.length; i++)
    {
        var event = windowOnloadFunArr[i];
        event.call();
    }
};

/**
 * 方法名:checkID 验证身份证号是否合法
 * 参数:id 要验证的身份证号
 * 返回值: true or false
 * 方法说明:验证身份证号是否合法
 * 调用示例如下:checkID(id)
 */	
function checkID(id){
      var idNum = id;
      var errors=new Array(
       "验证通过",
       "身份证号码位数不对",
       "身份证含有非法字符",
       "身份证号码校验错误",
       "身份证地区非法"
      );
      //身份号码位数及格式检验
         var re;
         var len = idNum.length;
         //身份证位数检验
         if(len != 15 && len != 18){
         alert(errors[1]);
       	 return false;
            }else if(len == 15){
             re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);
            }else{
             re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})([0-9xX])$/);
            }
              var area={11:"北京",12:"天津",13:"河北",14:"山西",
       15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",
       32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",
       37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",
       45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",
       53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",
       64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",
       91:"国外"}

        var idcard_array = new Array();
        idcard_array = idNum.split("");
        
        //地区检验
        if(area[parseInt(idNum.substr(0,2))]==null) {
         alert(errors[4])
         return false;
        }
        
        //出生日期正确性检验
          var a = idNum.match(re);
        
          if (a != null){
            if (len==15){
              var DD = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]);
              var flag = DD.getYear()==a[3]&&(DD.getMonth()+1)==a[4]&&DD.getDate()==a[5];
            }
            else if(len == 18){
              var DD = new Date(a[3]+"/"+a[4]+"/"+a[5]);
              var flag = DD.getFullYear()==a[3]&&(DD.getMonth()+1)==a[4]&&DD.getDate()==a[5];
            }
        
            if (!flag) {
            alert("身份证出生日期不对！");
             return false;
            }
            
            //检验校验位
            if(len == 18){
              S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
              + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
              + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
              + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
              + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
              + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
              + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
              + parseInt(idcard_array[7]) * 1
              + parseInt(idcard_array[8]) * 6
              + parseInt(idcard_array[9]) * 3 ;
            
              Y = S % 11;
              M = "F";
              JYM = "10X98765432";
              M = JYM.substr(Y,1);//判断校验位
            
              //检测ID的校验位
              if(M == idcard_array[17]){
               return true;
              }
              else{
               alert(errors[3]);
               return false;
              }
       }
         
          }else{
          	alert(errors[2]);
            return false;
          }
          return true;
}


//检查两个日期或时间比较，gujun2004－12－9修改
//date1起始日期、date2截止日期
function dateCompare(date1,date2,msg)
{
  	if(date1 != "" && date2 != ""){
  		if(date1 > date2){
            		alert(msg);
       			return false;
  		}
      		else{
                	return true;
      		}
  	}
      	else{
        	return true;
      	}
}
//获得客户端系统日期
function getClientSysDate()
{
 var sysDate,year,month,date;
 sysDate = new Date();
 year = String(sysDate.getFullYear());
 month = String(sysDate.getMonth()+1);
 date = String(sysDate.getDate());
 if(month.length<2)
  month = "0"+month;
 if(date.length<2)
  date = "0"+date;
 return(year+"-"+month+"-"+date);
}

//获取客户端系统时间
function getClientSysTime()
{
 var hour,minute,second,sysDate;
 sysDate = new Date();
 hour = String(sysDate.getHours());
 minute = String(sysDate.getMinutes());
 second = String(sysDate.getSeconds());
 if(hour.length<2)
  hour = "0"+hour;
 if(minute.length<2)
  minute = "0"+minute;
 if(second.length<2)
  second = "0"+second;
 return(hour+":"+minute+":"+second);
}


//获得grid中的radio或checkbox的值，如果是radio则返回一个字符串，如果是checkbox则返回多个字符串，中间用逗号隔开；
function getCheckedValue(objName)
{
  	var objChk;
  	var CheckedValue = "";
  	var j=1;
  	for(var i=0;i<getElementLen(objName);i++)
  	{
          	objChk=getObj(objName,i);
          	if(objChk.checked&&(!objChk.disabled))
          	{
                  	if(j==1){
                  		CheckedValue = objChk.value;
                  	}
                  	else{
                    		CheckedValue = CheckedValue + "," + objChk.value;
                  	}
                  	j = j + 1;
          	}
  	}
  	return CheckedValue;
}

//获得被选中radio或checkbox的索引,如果是radio则返回一个索引，如果是checkbox则返回索引串，中间用逗号隔开；
function getCheckedIndex(objName)
{
  	var objChk
  	var j = 1;
        var indexValue = "";
  	for(var i=0;i<getElementLen(objName);i++){
          	objChk=getObj(objName,i);
          	if(objChk.checked&&(!objChk.disabled)){
			if(j==1){
                  		indexValue = i;
                      	}
                  	else{
                    		indexValue = indexValue + "," + i;
                  	}
                  	j = j + 1;
          	}
  	}
      	return indexValue;
}

//获得页面中组件的值；
function getObjValue(objName)
{
	var obj = eval("document.all('" + objName + "')");
        if(obj == null) return "null";
      	return obj.value.trim();
}


//获得页面中id的innerHTML值,如果innerHTML值中包含label则取label中的innerHTML值；
function getObjInnerHtml(objName)
{
  	var innelHtmlValue = "";
	var obj = eval("document.all('" + objName + "')");
      	if(!(typeof(obj) == "object")) return '';
      	if((obj.innerHTML).indexOf("LABEL") < 0){
                innelHtmlValue = obj.innerHTML;
      	}
        else{
          	var i = (obj.innerHTML).indexOf(">",0);
              	var j = (obj.innerHTML).indexOf("</",0);
              	innelHtmlValue = (obj.innerHTML).substr(i+1,j-i-1);
        }
      	return innelHtmlValue;
}

//获得页面table中被选择行中表格的字段值的值，如果是单选则返回一个字符串，如果是多选则返回多个字符串，中间用逗号隔开；；
function getCellValue(checkObjName,cellObjName)
{
  	var objChk;
  	var cellValue = "";
      	var indexValue;

      	indexValue = getCheckedIndex(checkObjName) + "";
      	if(indexValue != "" && indexValue.indexOf(",") < 0){
                cellValue = getObjInnerHtml(cellObjName + indexValue);
      	}
      	else if(indexValue != "" && indexValue.indexOf(",") > 0){
         	var indexCount =  indexValue.indexOf(",");
              	var indexAarry = indexValue.split(",");
              	var j = 1;

              	for(var i=0;i<indexCount+1;i++){
                        if(j==1){
                  		cellValue = getObjInnerHtml(cellObjName + indexAarry[i]);
                      	}
                  	else{
                    		cellValue = cellValue + "," + getObjInnerHtml(cellObjName + indexAarry[i]);
                  	}
                  	j = j + 1;
              	}

      	}
        return cellValue;
}

//获得页面中select组件的显示值；
function getSelectObjName(objName)
{
	var obj = eval("document.all('" + objName + "')");
      	var selfSelectedIndex = obj.selectedIndex;
      	selectObjName = obj[selfSelectedIndex].innerHTML;
      	if(selectObjName == "请选择"){
                selectObjName = "&nbsp;";
      	}
      	return selectObjName;
}

//获得页面中组件的类型；
function getObjType(objName)
{
	var obj = eval("document.all('" + objName + "')");
      	return obj.type;
}

//获得页面中组件的显示值，目前专为便捷增加修改；
function getObjDisplayValue(objName)
{
	var typeValue = getObjType(objName);
      	var displayValue;
      	if(typeValue == "select-one"){
                displayValue = getSelectObjName(objName);
      	}
      	else{
        	displayValue = getObjValue(objName);
      	}
      	return displayValue;
}

//赋给页面中组件的值；
function setObjValue(objName,objNameValue)
{
	var obj = eval("document.all('" + objName + "')");
	if(!(typeof(obj)=="object"))return '';
	obj.value = objNameValue;
}

//使得grid中的radio都不被选中；
function setNoChecked(objName)
{
  var objChk
  for(var i=0;i<getElementLen(objName);i++)
  {
          objChk=getObj(objName,i);
          objChk.checked = false;
  }
}


//组合选中对象的post值
function getPostValue(objName){
  var objChk;
  var chkPostValue;
  var j=1;
  for(var i=0;i<getElementLen(objName);i++)
  {
          objChk=getObj(objName,i);
          if(objChk.checked&&(!objChk.disabled))
          {
                  if(j==1){
                  	chkPostValue = objName + "=" + objChk.value;
                  }
                  else{
                    	chkPostValue = chkPostValue + "&" + objName + "=" + objChk.value;
                  }
                  j = j + 1;
          }
  }
  return chkPostValue;
}


//根据选中的记录删除行
function deleteRows(objName){
  var objChk;
  for(var i=0;i<getElementLen(objName);i++)
  {
          objChk=getObj(objName,i);
          if(objChk.checked&&(!objChk.disabled))
          {
                   eval("document.all.dataRow"+i).style.display = "none";
          }
  }
  return "1";
}

//////////////////////////////////////////////////////////////////////////////
//得到From中所有元素的NAME和值，并组成GET请求中要求的[name]=[value]&...[name]=[value]串
//////////////////////////////////////////////////////////////////////////////
function getAlldata(obj){
    var data = "";
    for (i=0; i<obj.length; i++ ){
    	if ( obj(i).type != "submit" && obj(i).type != "reset" && obj(i).type != "button"){
        	if(!("type" in obj(i))){
        		continue;
	        }
            if( obj(i).type == "select-multiple"){
            	
                for(j=0; j<obj(i).length;j++){
                    if (obj(i).options[j].selected ){
                        data = data+obj(i).name+"="+replaceStr(obj(i).options[j].value)+"&";
                    }
                }
            }else if(obj(i).type =="radio" || obj(i).type =="checkbox"){
                if (obj(i).checked){
                    data=data+obj(i).name+"="+replaceStr(obj(i).value)+"&";

                }
            }else{
                data=data+obj(i).name+"="+replaceStr(obj(i).value)+"&";
            }
        }
    }
    return data.substr(0, data.length-1);
}

/**
 * 方法名:getCaret
 * 参数:this 要获得当前输入对象的引用
 * 返回值:数字
 * 方法说明:取当前光标的位置
 * 调用示例如下:getCaret(this)
 */
function getCaret(textbox)
{
 var control = document.activeElement;
 textbox.focus();
 var rang = document.selection.createRange();
 rang.setEndPoint("StartToStart",textbox.createTextRange());
 control.focus();
 return rang.text.length;
}

/**
 * 方法名:getChineseName
 * 参数:this 要获得当前输入对象的引用
 * 返回值:字符串
 * 方法说明:用来获得当前输入对象前面对应的中文名称
 * 调用示例如下:getChineseName(this)
 */
function getChineseName(objIn){
	var objParentTd = objIn.parentElement;
	if(objParentTd.tagName != "TD"){
		 objParentTd = objIn.parentElement.parentElement;
	}
	if((objParentTd.previousSibling != undefined) ||(objParentTd.previousSibling != null)){
		return objParentTd.previousSibling.innerText;
	}
	else{
		return "";
	}
}


/**
 * 方法名:validDocStatus
 * 参数:无
 * 返回值:页面状态值true 或者 false
 * 方法说明:当页面以POST方式进行提交时,可以通过此方法,判断页面状态
 * 调用示例如下:Var status = validDocStatus(); Status 为 true or false
 */
function validDocStatus()
{
  if(document.readyState=="loading")
  {
     alert("程序正在处理，请等待程序处理结束！");
     return false;
  }
  return true;
}

//格式化金额数据.
function formatNumber(srcStr,nAfterDot){
    var srcStr,nAfterDot;
    var resultStr,nTen;
    srcStr = ""+srcStr+"";
    strLen = srcStr.length;
    dotPos = srcStr.indexOf(".",0);
    if(srcStr==''){
        resultStr='0.'
        for(var k=0;k<nAfterDot;k++){
            resultStr=resultStr+'0';
        }
        return resultStr;
    }
    if (dotPos == -1){
        resultStr = srcStr+".";
        for (i=0;i<nAfterDot;i++){
            resultStr = resultStr+"0";
        }
        return resultStr;
    }
    else{
        if ((strLen - dotPos - 1) >nAfterDot){
            nAfter = dotPos + nAfterDot + 1;
            nTen =1;
            for(j=0;j<nAfterDot;j++){
                nTen = nTen*10;
            }
            resultStr = Math.round(parseFloat(srcStr)*nTen)/nTen;

            var dotPos2=(""+resultStr+"").indexOf(".",0);

            return resultStr;
        }
        else{
            resultStr = srcStr;
            for (i=0;i<(nAfterDot - strLen + dotPos + 1);i++){
                resultStr = resultStr+"0";
            }
            return resultStr;
        }
    }
}

//去除空格
String.prototype.trim = function()
{
    return this.replace(/(^[\s|　]*)|([\s|　]*$)/g, "");
}
//截取字符串前后的空字符

	//去左空格;
function ltrim(s){
 return s.replace( /^\s*/, "");
}
//去右空格;
function rtrim(s){
 return s.replace( /\s*$/, "");
}
//左右空格;
function trim(s){
 return rtrim(ltrim(s));
}
//截取字符串前后的空字符回车符和换行符
function trimNC(strValue)
{
	var  i=0;
	var intLen;
	if (strValue.length == 0)return ""
	while (i == 0)
	  {
	    i = strValue.indexOf("\r",0);
	    strValue=strValue.replace("\r","")
	  }
    i=0;
	while (i == 0)
	  {
	    i = strValue.indexOf(" ",0);
	    strValue=strValue.replace(" ","")
	  }
    i=0;
	while (i == 0)
	  {
	    i = strValue.indexOf("\n",0);
	    strValue=strValue.replace("\n","")
	  }

    intLen = (strValue.length - 1)
    while (strValue.lastIndexOf(" ") == intLen)
      {
        strValue=strValue.substr(0,intLen-1)
        intLen = strValue.length
	  }
	return strValue;
}


//////////////////////////////////////////////////////////////////////////////
//特殊字符串置换
//////////////////////////////////////////////////////////////////////////////
//function replaceString(strdata){
//    str = strdata.replace(/\//g, "//");
//    str = str.replace(/\;/g, "/;");
//    str = str.replace(/:/g, "/:");
//    str = str.replace(/'/g, "/'");
//    str = str.replace(/\[/g, "/[");
//    str = str.replace(/\]/g, "/]");
//    return str;
//}

function replaceStr(str)
{
    str = str.replace(/%/g,"%25");
    str = str.replace(/&/g,"%26");
    str = str.replace(/\n/g,"%0A");
    str = str.replace(/\+/g,"%2B");
    str = str.replace(/#/g,"%23");
    str = str.replace(/=/g,"~");
    //str = str.replace(/</g,"&#60;");
    //str = str.replace(/>/g,"&#62;");
    //str = str.replace(/\\/g,"&#92;");
    //str = str.replace(/</g,"&#60;");
    //str = str.replace(/>/g,"&#62;");
    //str = str.replace(/\"/g,"&#34;");
    //str = str.replace(/ /g,"&nbsp;");

    return str;
}

/**
 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
 * @param String s ,需要得到长度的字符串
 * @return int, 得到的字符串长度
 * @author:陈博
 * @添加日期:2005.06.09
 */
function strLength(s){
	var i,str1,str2,str3,nLen;
	str1 = s;
	nLen = 0;
	for(i=1;i<=str1.length;i++){
		str2=str1.substring(i-1,i)
		str3=escape(str2);
		if(str3.length>3){
			nLen = nLen + 2;
		}
		else{
			nLen = nLen + 1;
		}
	}
	return nLen;
}

//校验Email
function checkEmail(email)
{
     if(email.indexOf("@")<0||email.indexOf(".")<0)
     {
     	alert("请正确输入Email");
         return false;
     }
    return true;
}

//校验手机号码
function checkMobile(mobile)
{
    if(mobile.length!=11)
    {
    	alert("手机号码长度为11位");
        return false;
    }

    if(!checkPInteger(mobile))
    {
    	alert("手机号码应该全为数字组成");
        return false;
    }
    return true;
}

//校验正数
function checkPNumber(str) {
	if(checkNumberPlus(str)) {
		return str.indexOf('-') < 0;
	}
	return false;
}
/**
 * 方法名:checkNumberPlus
 * 参数:str 要校检的字符串
 * 返回值:true or false
 * 方法说明:校检字符串,是不是全部是由数字组成
 * 调用示例如下:checkNumberPlus("232323")
 */
function checkNumberPlus(str) {
    var i;
    var len = str.length;
    var chkStr = "-1234567890.";
    if (len == 1) {
	if (chkStr.indexOf(str.charAt(i)) < 0) {
	    return false;
	}
    } else {
	if ((chkStr.indexOf(str.charAt(0)) < 0) || ((str.charAt(0) == "0")&&(str.charAt(1)!="."))) {
	    return false;
	}
	for (i = 1; i < len; i++) {
	  if (chkStr.indexOf(str.charAt(i)) < 0) {
		return false;
	  }
	}
    }
    return true;
}

/**
 * 方法名:checkInteger
 * 参数:str 要校检的字符串
 * 返回值:true or false
 * 方法说明:校验整数
 * 调用示例如下:checkInteger("2222");
 */
function checkInteger(str,msg) {
    if(checkNumberPlus(str)) {
        return str.indexOf('.') < 0;
    }
	return false;
}

/**
 * 方法名:checkPInteger
 * 参数:str 要校检的字符串
 * 返回值:true or false
 * 方法说明:校验正整数
 * 调用示例如下:checkPInteger("2222")
 */
function checkPInteger(str) {
	if(checkInteger(str)) {
		return str.indexOf('-') < 0;
	}
	return false;
}


//控制输入框中内容为Number
function onlyNumber(obj){
	var checkStr = obj.value;

        if ( !(((window.event.keyCode >= 48) && (window.event.keyCode <= 57))|| (window.event.keyCode == 13) || (window.event.keyCode == 46)|| (window.event.keyCode == 45)))
   	{
     		window.event.keyCode = 0 ;
                return false;
    	}

  	if(window.event!=null){
      		//第一位不允许输入小数点“.”
      		if(checkStr == null || checkStr=="" ){
                	if(window.event.keyCode == 46)
                        	window.event.keyCode = 0 ;
                }
                //如果第一位是负号，第二位禁止输入“.”
                if(checkStr=="-"){
                        if(window.event.keyCode == 46)
                                window.event.keyCode = 0 ;
                }
                //如果第一位输入负号，其他位不允许输入负号“-”
                if(checkStr.indexOf("-")>-1 || checkStr.length>1){
                        if(window.event.keyCode == 45)
                                window.event.keyCode = 0 ;
                }
                //如果已经输入“.”，则其他位不允许输入“.”
                if(checkStr.indexOf(".")>-1){
                        if(window.event.keyCode == 46)
                                window.event.keyCode = 0 ;
                }
  	}
    	return true;
}


/**
 * 方法名:showRow
 * 参数:obj 行对象引用
 * 返回值:无
 * 方法说明:显示表格中的一行
 * 调用示例如下:showRow(obj)
 */
function showRow(obj)
{
   obj.style.display = "";
   return true;
}


/**
 * 方法名:hideRow
 * 参数:obj 行对象引用
 * 返回值:无
 * 方法说明:隐藏表格中的一行
 * 调用示例如下:hideRow(obj)
 */
function hideRow(obj)
{
  obj.style.display = "none";
  return true;
}


/**
 * 方法名:getShowRowCount
 * 参数:obj 表对象
 * 返回值:总行数
 * 方法说明:当前表格中,去掉隐藏行后的总行数
 * 调用示例如下:getShowRowCount(obj)
 */
function getShowRowCount(obj)
{
  var table = eval(obj);
  var maxShow=0;
  for (i=0; i < table.rows.length; i++)
     {
   		var t= table.rows(i);
   		if (t.style.display != "none")
   		  maxShow=maxShow+1;
     }
   return  maxShow;
}



/**
 * 方法名:setFocus
 * 参数:prefix 列名的前缀;id 第几行;col 列名
 * 返回值:无
 * 方法说明:移动光标到表格的指定行的,指定的字段
 * 调用示例如下:setFocus("orderDetail",id,"quantOrder")
 */
function setFocus(prefix,id,col)
{

    var colname ="";
    if((id>=0)&&(prefix!=""))
      colname = prefix+"[" + id + "]." + col;
    else
      colname = col;
    var focusControl = document.forms[0].elements[colname];

    if (typeof(focusControl)!="object")
         return false;
    if (focusControl.type == "hidden")
        return false;
    try{
    focusControl.focus();
    if (focusControl.tagName=="INPUT")
      focusControl.select();
    }
    catch(e){

        }

    return true;
}

/**
 * 方法名:openDialog
 * 参数:WINurl 窗口里页面的url;WINwidth 窗口的宽度;WINheight 窗口的高度;xyPosition 打开窗口位置
 * 返回值:无
 * 方法说明:指定弹出位置打开模态窗口 xyPosition 为 0时, 在屏幕中央打开模态窗口 xyPosition 不为 0 时,在事件源附近打开模态窗口
 * 调用示例如下:openDialog(" http://localhost:6767/t4new/tr/cheque/checkIn/CheckInList.do",400,300,0)
 */
function openDialog(WINurl,WINwidth,WINheight,xyPosition)
{
 if(xyPosition==0)//屏幕中央
   {
    showx = (window.screen.availWidth  - WINwidth)/2;
    showy = (window.screen.availHeight - WINheight)/2;
   }
 else//事件附近
   {
	   showx = event.screenX - event.offsetX - 4 - WINwidth ; // + deltaX;
	   showy = event.screenY - event.offsetY + 18; // + deltaY;
	  }
	newWINwidth = WINwidth + 4 + 18;
	var features =
		'dialogWidth:'  + newWINwidth  + 'px;' +
		'dialogHeight:' + WINheight + 'px;' +
		'dialogLeft:'   + showx     + 'px;' +
		'dialogTop:'    + showy     + 'px;' +
		'directories:no; localtion:no; menubar:no; status=no; toolbar=no;scrollbars:yes; resizable:yes';
	var vDialog = window.showModalDialog(WINurl, " ", features);
	return vDialog;
}

/**
 * 方法名:setInputFocus
 * 参数:objName 要置焦的控件的名字
 * 返回值:无
 * 方法说明:给指定控件置焦点
 * 调用示例如下:setInputFocus("objName")
 */
function setInputFocus(objName){
  	var obj = eval("document.all('" + objName + "')");
    if(obj.style.display == "none") {
    }else {
      obj.focus();
    }

}

/**
*过滤掉控件text的前后空格，并返回新值
*/
function getTrimText(textfield)
{
  var value = textfield.value;
  textfield.value = trim(value);

  return textfield.value;
}

/**
 * 方法名:setMainFocus
 * 参数:col 为要置焦的对象
 * 返回值:无
 * 方法说明:用户按回车键时,设置光标到下一个控件,置焦.
 * 调用示例如下:setMainFocus(col) col为要置焦的对象
 */
function setMainFocus(col)
{
   var keyCode=window.event.keyCode;
   if(keyCode==13||window.event.type=='propertychange')
   {
      var obj=document.getElementById(col);
      if(obj)
      {
          if(obj.tagName=='IMG'){
            obj.click();
          }else{
            obj.focus();
          }
      }
   }
}

/**
 * 方法名:disableTable
 * 参数:obj 要disable的表对象
 * 返回值:无
 * 方法说明:将表格中的所有行,列都disable使用户不能选择
 * 调用示例如下:disableTable(obj) obj为表对象
 */
function disableTable(obj)
{
  var table;
  if (obj  != "")	table = eval(obj);  // Assumes that the obj is THE OBJECT
  else return false;
  if (table == null) return false;;  // Check whether it's an object
  if (table.tagName != "TABLE") return false;;  // Check whether it's a table
  for (i=0; i < table.rows.length; i++) {
        for (j=0; j < table.rows(i).cells.length; j++) {
            disableRow(table.rows(i));
        }
   }
   return true;
}

/**
 * 方法名:disableRow
 * 参数:obj 行对象引用
 * 返回值:无
 * 方法说明:将一行中所有input 控件disable.
 * 调用示例如下:disableRow(obj),obj为行对象引用
 */
function disableRow(obj)
{
  var row= obj;
  var cellchildren;
   for (j=0; j < row.cells.length; j++) {
       cellchildren = row.cells(j).children;
	    for(m=0;m<cellchildren.length;m++)  {
	       var child = cellchildren(m);
	    	if (child.tagName=="INPUT") {
            child.disabled=true;
       		}
       	}
     }
	return true;
}

/**
 * 方法名:clearRow
 * 参数:obj 要清除值的行对象
 * 返回值:无
 * 方法说明:清除一行中所有text域的值
 * 调用示例如下:clearRow(obj),obj为行对象引用
 */
function clearRow(obj)
{
  var row= obj;
  var cellchildren;
   for (j=0; j < row.cells.length; j++) {
       cellchildren = row.cells(j).children;
	    for(m=0;m<cellchildren.length;m++)  {
	       var child = cellchildren(m);
	    	if (child.tagName=="INPUT") {
	        	child.value = "";
       		}
       	}
     }
	return true;
}

/**
 * 方法名:clearTable
 * 参数:obj 要清除值的表对象
 * 返回值:无
 * 方法说明:清除表格中所有text域的值
 * 调用示例如下:clearTable(obj),obj为表对象引用
 */
function clearTable(obj)
{
  var table;
  if (obj  != "")	table = eval(obj);  // Assumes that the obj is THE OBJECT
  else return false;
  if (table == null) return false;;  // Check whether it's an object
  if (table.tagName != "TABLE") return false;;  // Check whether it's a table
  for (i=0; i < table.rows.length; i++) {
        for (j=0; j < table.rows(i).cells.length; j++) {
            clearRow(table.rows(i));
        }
   }
   return true;
}

/**
 * 方法名:enterToTab
 * 参数:无
 * 返回值:无
 * 方法说明:将回车键转换成TAB
 * 调用示例如下:enterToTab()
 */
function enterToTab(){
  if(event.srcElement.type != 'button' && event.srcElement.type != 'textarea' && event.keyCode == 13){
    event.keyCode = 9;
  }
}

/**
 * 方法名:changeCheckboxDisplayValue
 * 参数:obj 对象的控件;checkValue 选中时的值;unCheckValue 不选中时的值
 * 返回值:无
 * 方法说明:改变隐藏控件的值
 * 调用示例如下:changeCheckboxHiddenValue(this,1,0)
 */
function changeCheckboxHiddenValue(obj,checkValue,unCheckValue) {
	var objhidden = document.getElementById(obj.name.replace("checkbox_", ""));
	if(obj.checked == true) {
		objhidden.value = checkValue;
	}
	if(obj.checked == false) {
		objhidden.value = unCheckValue;
	}
}

/**
 * 方法名:Checkbox_OnPropertyChange
 * 参数:obj 对象的控件;checkValue 选中时的值;unCheckValue 不选中时的值
 * 返回值:无
 * 方法说明:改变checkbox的选择状态
 * 调用示例如下:Checkbox_OnPropertyChange(this,1,0)
 */
function Checkbox_OnPropertyChange(obj,checkValue,unCheckValue) {

	var objdisplay ;
    if (obj.name.indexOf("].")>0) {
  	  var strNames = obj.name.split("].");
  	  objdisplay = document.getElementById(strNames[0]+"]."+"checkbox_" + strNames[1]);
    }else{
  	  objdisplay = document.getElementById("checkbox_" + obj.name);
	}
	//var objdisplay = document.getElementById("checkbox_"+obj.name);
	if (obj.value == checkValue) {
		objdisplay.checked = true;
	}
	if (obj.value == unCheckValue) {
		objdisplay.checked = false;
	}

}

/**
 * 方法名:selectToInput
 * 参数:obj 对象的控件
 * 返回值:无
 * 方法说明:当SELECT控件为只读时,使得select控件值与隐藏的input域里的值同步
 * 调用示例如下:selectToInput(this)
 */
function selectToInput(obj) {
  var objOption;
  for (i=0; i< obj.options.length; i++)
    if(obj.options(i).value == obj.value){
      objOption = obj.options(i);
    }
  var objCode = document.getElementById(obj.name.replace("sel_", ""));
  var objName = document.getElementById("dis_" + obj.name.replace("sel_",""));

  objCode.value = objOption.value;
  objName.value = objOption.text;

}

/**
 * 方法名:codeInputToSelect
 * 参数:obj 对象的控件;
 * 返回值:无
 * 方法说明:当SELECT控件为只读时,使得隐藏的input域里的值与select控件值同步
 * 调用示例如下:codeInputToSelect(this)
 */
function codeInputToSelect(obj) {

  var objOption;
  if (obj.name.indexOf("].")>0) {
  	  var strNames = obj.name.split("].");
  	  objSel = document.getElementById(strNames[0]+"]."+"sel_"+strNames[1]);
      objName= document.getElementById(strNames[0]+"]."+"dis_" + strNames[1]);
  }else{
  	  objSel = document.getElementById("sel_"+obj.name);
      objName= document.getElementById("dis_" + obj.name);
  }

  for (i=0; i< objSel.options.length; i++)
    if(objSel.options(i).value == obj.value){
      objOption = objSel.options(i);
    }
	if (objOption != null) {
		objSel.value = objOption.value;
	  objName.value = objOption.text;
	}
	if (objOption == null) {
		alert("隐藏控件的值与下拉框里的值不对应!!! ");
	}

}

/**
 * 方法名:radioInputToRadio
 * 参数:obj 对象的控件;
 * 返回值:无
 * 方法说明:使得隐藏的input域里的值与radiogroup控件值同步
 * 调用示例如下:radioInputToRadio(this)
 */
function radioInputToRadio(obj) {

	var objName = obj.name;
	var objRadios;
	var disObj;

    if (obj.name.indexOf("].")>0) {
  	  var strNames = obj.name.split("].");
  	  objRadios = document.all(strNames[0]+"]."+"radio_" + strNames[1]);
	  disObj = document.getElementById(strNames[0]+"]."+"dis_" + strNames[1]);
    }else{
  	  objRadios = document.all("radio_"+objName);
	  disObj = document.getElementById("dis_"+objName);
	}

	var radioObj;
	var flag = false;
	for(var i=0;i<objRadios.length;i++){
	  radioObj = objRadios(i);
	  if (radioObj.value==obj.value) {
	  	radioObj.checked = true;
	  	disObj.value = radioObj.nextSibling.innerHTML;
	  	flag = true;
	  	break;
	  }else {
	  	//alert("没有批配的值!!!");
	  }
	}
	if (flag == false) {
		alert("没有批配的值!!!");
	}

}

/**
 * 方法名:radioToRadioInput
 * 参数:obj 对象的控件;
 * 返回值:无
 * 方法说明:使得radiogroup控件值与隐藏的input域里的值同步
 * 调用示例如下:radioToRadioInput(this)
 */
function radioToRadioInput(obj) {
	//return true;
	var objName = obj.name;
	var objInput = document.getElementById(objName.replace("radio_",""));

	var disObj;
    if (obj.name.indexOf("].")>0) {
  	  var strNames = obj.name.replace("radio_","").split("].");
  	  disObj = document.getElementById(strNames[0]+"]."+"dis_" + strNames[1]);

    }else{
  	  disObj = document.getElementById("dis_"+objName.replace("radio_",""));
	}
	objInput.value = obj.value;
	disObj.value = obj.nextSibling.innerHTML;
}

/**
 * 方法名:checkboxTocheckboxInput
 * 参数:obj 对象的控件;
 * 返回值:无
 * 方法说明:使得checkboxgroup控件值与隐藏的input域里的值同步
 * 调用示例如下:checkboxTocheckboxInput(this)
 */
function checkboxTocheckboxInput(obj) {

	var objName = obj.name;
	var objInput = document.getElementById(objName.replace("checkbox_",""));

    var disObj;
    if (obj.name.indexOf("].")>0) {
  	  var strNames = obj.name.replace("checkbox_","").split("].");
  	  disObj = document.getElementById(strNames[0]+"]."+"dis_" + strNames[1]);

    }else{
  	  disObj = document.getElementById("dis_"+objName.replace("checkbox_",""));
	}

	var objs = document.all(objName);
	var checkboxObj;
	var strValue = "";
	var strName = "";
	for(var i=0;i<objs.length;i++) {
		checkboxObj = objs(i);
		if (checkboxObj.checked == true) {
			strValue = strValue +","+ checkboxObj.value;
			strName = strName +","+ checkboxObj.nextSibling.innerHTML;
		}
	}

	strValue = strValue.substr(1,strValue.length-1);
	strName = strName.substr(1,strName.length-1);
	objInput.value = strValue;
	disObj.value = strName;
}

/**
 * 方法名:checkboxInputTocheckbox
 * 参数:obj 对象的控件;
 * 返回值:无
 * 方法说明:使得隐藏的input域里的值与checkboxgroup控件值同步
 * 调用示例如下:checkboxInputTocheckbox(this)
 */
function checkboxInputTocheckbox(obj) {
	var objName = obj.name;
	var objs = document.all("checkbox_"+objName);
	var disObj = document.getElementById("dis_"+objName);

    if (obj.name.indexOf("].")>0) {
  	  var strNames = obj.name.split("].");
  	  objs = document.all(strNames[0]+"]."+"checkbox_" + strNames[1]);
	  disObj = document.getElementById(strNames[0]+"]."+"dis_" + strNames[1]);
    }else{
  	  objs = document.all("checkbox_"+objName);
	  disObj = document.getElementById("dis_"+objName);
	}


	var strValues = obj.value.split(',');
	var checkboxObj;
	var flag = false;
	var strName = "";
	for(var j=0;j<objs.length;j++){
		objs(j).checked = false;
	}
	for (var i= 0;i<strValues.length;i++) {
		for(var j=0;j<objs.length;j++){
	    checkboxObj = objs(j);
	    if(strValues[i] == checkboxObj.value) {
	    	checkboxObj.checked = true;
	    	strName = strName + "," + checkboxObj.nextSibling.innerHTML;
	    	//alert(strValues[i]);
	    	break;
	    }
	  }
	}
	strName = strName.substr(1,strName.length-1);
	disObj.value = strName;
	if (flag == false) {
	//	alert("没有批配的值!!!");
	}

}

//校验输入框中内容为合法的Number
function validNumber(obj,colSize,precision){
   return true;
	var checkStr = obj.value;
        if(checkNumber(checkStr) == false) {
          obj.focus();
          return false;
        }
        colSize = parseInt(colSize);
        precision = parseInt(precision);
        var m = checkStr.length;                      //输入值的位置
        var n = checkStr.indexOf(".");                //“.”的位置
        var k = checkStr.indexOf("-");                //“-”的位置
        var j;                                        //整数长度
        var i;                                        //小数长度

	//如果输入值中含有“.”；获取输入值的整数长度；
 	if(n > -1){
           	//如果输入值中含有“－”
           	if(k > -1){
        		j = n - 1;
                      	i = m - n - 1;
           	}
                else{
                  	j = n;
                      	i = m - n - 1;
                }
 	}
 	else{
           	//如果输入值中含有“－”
           	if(k > -1){
  			j = m - 1;
           	}
                else{
                  	j = m;
                }
                i = 0;
 	}
      	//检验输入值的整数长度和小数长度
      	if((j > colSize - precision) || (i > precision)){
                alert(checkStr + "的精度不符，要求整数长度小于等于" + (colSize - precision) + "、小数长度小于等于" + precision + "！");
                obj.focus();
                return false
      	}
        return true;
}


//判断form中文本控件是否满足必输的需求
function validFormText(name,isMustInput) {
  //获取被检验的文本控件的输入值
  TextValue = getObjValue(name);
  //如果该数值控件必输
  if(isMustInput == "false") {
    return true;
  }
  if(isMustInput == "true" && TextValue==""){
     	alert("请输入值！");
        setInputFocus(name);
        return false;
  }
  return true;
}

//验证页面必输项
//msg为输出名称 element为页面元素名称

String.prototype.Trim = function()
{
return this.replace(/(^\s*)|(\s*$)/g, "");
} 

function checkValidate(msg,element)
{
    if(document.getElementsByName(element)[0].value==null||document.getElementsByName(element)[0].value.Trim()=="")
    {
        alert('请输入'+msg) ;
        return false;
    }else
    {
    	return true;
    }
}

/**
 * 方法名:onlyInteger
 * 参数:obj 控件的对象引用
 * 返回值:无 or false
 * 方法说明:数值控件只允许输入数字,和负号,回车
 * 调用示例如下:onlyInteger(this)
 */
function onlyInteger(obj){
	var checkStr = obj.value;
  //alert(checkStr);
  if ( !(((window.event.keyCode >= 48) && (window.event.keyCode <= 57))|| (window.event.keyCode == 13) || (window.event.keyCode == 45))){
       window.event.keyCode = 0 ;
       return false;
  }

  if(window.event!=null){
  	//负号只能在第一位
  	if (window.event.keyCode == 45){
  		if(checkStr!="" && getCaret(obj) != 0) {
  			window.event.keyCode = 0
  			//alert("负号只能在第一位!!!");
  		}
  	}

  }
    return true;
}

/**
 * 方法名:onlyDouble
 * 参数:obj 控件的对象引用
 * 返回值:true or false
 * 方法说明:浮点数值控件只允许输入数字,和负号,回车,小数点
 * 调用示例如下:onlyDouble(this)
 */
function onlyDouble(obj){
	var checkStr = obj.value;

  if ( !(((window.event.keyCode >= 48) && (window.event.keyCode <= 57))|| (window.event.keyCode == 13) || (window.event.keyCode == 46)|| (window.event.keyCode == 45))){
    window.event.keyCode = 0 ;
    return false;
  }

  if(window.event!=null){
  	if(window.event.keyCode == 46 && getCaret(obj) == 0 ){
  		window.event.keyCode = 0
  		//alert("第一位不允许输入小数点“.”!!!");
  	}
  	//负号只能在第一位
  	if (window.event.keyCode == 45){
      if(checkStr!="" && getCaret(obj) != 0) {
  			window.event.keyCode = 0
  			//alert("负号只能在第一位!!!");
  		}
  	}
    //第一位不允许输入小数点“.”
    if(checkStr == null || checkStr=="" ){
      if(window.event.keyCode == 46)
        window.event.keyCode = 0 ;
    }
    //如果第一位是负号，第二位禁止输入“.”
    if(checkStr=="-"){
      if(window.event.keyCode == 46)
        window.event.keyCode = 0 ;
    }

    //如果已经输入“.”，则其他位不允许输入“.”
    if(checkStr.indexOf(".")>-1){
      if(window.event.keyCode == 46)
        window.event.keyCode = 0 ;
    }
  }
  return true;
}


/**
 * 方法名:sumCol
 * 参数:obj 要汇总的表格的名字;prefix 前缀: 要汇总列的列名的前缀;sumColName 汇总列的列名;setColName 存放汇总值的列名;nDec 汇总值的精度
 * 返回值:无
 * 方法说明:汇总指定表格中的指定sumColName的值到setColName
 * 调用示例如下:sumCol("dataTable","orderDetail","quantOrder","totQuantOrder",0)
 */
function sumCol(obj,prefix,sumColName,setColName,nDec)
{
   var colname ="";
   var nRow;		// Various table stats
   var table;				// Table object
   var sumValue = 0;
   var colValue = 0;
	if (obj  != "")
	{
		table = eval(obj);  // Assumes that the obj is THE OBJECT
	}
	if (table == null) return;  // Check whether it's an object
	if (table.tagName != "TABLE") return;  // Check whether it's a table
	nRow = table.rows.length;// Setting the number of rows
	if (nRow < 1) return;// Should have at least 1 row
	// Loop through rows
	for (var i=0; i<nRow; i++)
	{
      if(prefix!="")
      {
        colname = prefix+"[" + i + "]." + sumColName;
      }
      colValue = document.forms[0].elements[colname].value;
      if (checkNumber(colValue))
        {
          if (colValue == "") { colValue =0;}
          sumValue = parseFloat(sumValue) + parseFloat(colValue);
         }
      else
      	{
          sumValue = -1;
          break;
        }
    }
   document.forms[0].elements[setColName].value =  FormatNumber(parseFloat(sumValue),nDec);
}

/**
 * 方法名:sortGrid
 * 参数:obj 要排序的图标对象;fName 要排序的控件的名字;mode 页面的类型是view 还是 edit
 * 返回值: 无
 * 方法说明:对grid 进行页面的当页排序
 * 调用示例如下:sortGrid(this,"userName","view")
 */
function sortGrid(obj,fName,mode) {
   var objGridTable = document.all.dataTable;
   var objAllRows = objGridTable.rows;
   if(obj.alt=='升序') {
     for(var i = 1; i < objGridTable.rows.length;i++) {
       for(var j = 1; j < objGridTable.rows.length-i; j++) {
     	 if(getGridCompareValue(objAllRows,j,fName,mode) > getGridCompareValue(objAllRows,j+1,fName,mode)) {
     	   objGridTable.moveRow(j,j+1);
     	 }
        }
     }
     obj.alt="降序";
     obj.src=ctx + "/images/sortDown.gif";
   }else if(obj.alt=='降序') {
     for(var i = 1; i < objGridTable.rows.length;i++) {
       for(var j = 1; j < objGridTable.rows.length-i; j++) {
         if(getGridCompareValue(objAllRows,j,fName,mode) < getGridCompareValue(objAllRows,j+1,fName,mode)) {
           objGridTable.moveRow(j,j+1);
     	  }
       }
      }
      obj.alt="升序";
      obj.src=ctx + "/images/sortUp.gif";
   }
}

	//获取某一表单元素数组的长度
	function getElementLen(elementName)
	{
	  var obj=document.all(elementName);
	  if(!(typeof(obj)=="object"))return 0;
	  if(obj==null)
	    return 0;
      var len=obj.length;
      if(typeof(len)=="undefined")
      {
	     len=1;
      }
      return len;
	}
	//从表单对象数组中获取表单对象
	function getObj(objName,index)
	{
	  var obj=document.all(objName);
	  if(!(typeof(obj)=="object"))return '';
	  var len=obj.length;
	  if(!(typeof(len)=="undefined"))
	  {
		 //obj=eval("document.all."+objName+"["+index+"]");
		  obj=document.getElementsByName(objName)[ index];//hejin modify 当页面只有一条记录时，可能会返回undefined类型，而不是复选框类型
	  }
	  return obj;
	}
	//全选
	function selectAll(objName)
	{
		var objChk;
		var elementLengh=getElementLen(objName);
		for(var i=0;i<elementLengh;i++)
		{
			objChk=getObj(objName,i);
			if((!objChk.disabled))
			{
				if(objChk.checked)
				{
					objChk.checked=false;
				}
				else
				{
					objChk.checked=true;
				}
			}
		}
	}
	//判断是否有复选框被选择
	function hasSelectedRecord(objName)
	{
		var objChk;
		var elementLengh=getElementLen(objName);
		for(var i=0;i<elementLengh;i++)
		{
			objChk=getObj(objName,i);
			if(objChk.checked&&(!objChk.disabled))
			{
				return true;
			}
		}
		return false;
	}

	//判断是否有复选框被选择
	function hasOneRecord(objName)
	{
		var objChk;
		var returnval=0;
		var j=0;
		var elementLengh=getElementLen(objName);
		for(var i=0;i<elementLengh;i++)
		{
			objChk=getObj(objName,i);
			if(objChk.checked&&(!objChk.disabled))
			{
				j++;
				returnval=objChk.value;
			}
		}
		if (j==1)
			return returnval;
		else
			return 0;
	}

	//新增
	function createRecord(url)
	{
		window.location.href=updateUrl;
	}
	//修改
	function updateRecord(updateUrl)
	{
		window.location.href=updateUrl;
	}
	//删除
	function deleteRecord(deleteUrl,frmDelName,idName)
	{
		var frmDel = eval("document.all."+frmDelName);
		if(hasSelectedRecord(idName))
		{
			if(confirm("确实要删除记录吗？"))
			{
				frmDel.submit();
			}
		}
		else
		{
			alert("请选择需要删除的记录！");
		}
	}

	function returnUpPage(urlStr)
  	{
  	 window.location.href=urlStr;
  	}

	//add by yanglei 20050622
	function backRows(objName)
		{
			var objChk;
			var returnval=0;
			var j=0;
			var elementLengh=getElementLen(objName);
			for(var i=0;i<elementLengh;i++)
			{
				objChk=getObj(objName,i);
				if(objChk.checked&&(!objChk.disabled))
				{
					j++;
					returnval=i;
				}
			}
			if (j==1)
				return returnval;
			else
				return 0;
	}


