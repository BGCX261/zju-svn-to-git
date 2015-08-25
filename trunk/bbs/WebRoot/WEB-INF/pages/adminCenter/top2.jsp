<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript">                  
		//<!--           
		function showTime(id){           
			var now = new Date();           
			var hours = now.getHours();        
			var minutes = now.getMinutes();         
			var seconds = now.getSeconds();         
		
			var sign = "AM";      
			if (hours > 12){           
				sign = "PM";           
				hours -= 12;           
			}   
			if (minutes <= 9){     
				minutes = "0" + minutes;
			}
			if (seconds <= 9){
				seconds = "0" + seconds;
			}

			var txtTime = hours + ":" + minutes + ":" + seconds + " " + sign;
			document.getElementById(id).innerHTML = txtTime;

			setTimeout("showTime('" + id + "')",1000)           
		}           

		function showDate(id){
			var today=new Date();           
			var days=  ["星期日",           
						"星期一",           
						"星期二",           
						"星期三",           
						"星期四",           
						"星期五",           
						"星期六" ];        
			document.getElementById(id).innerHTML = today.getYear() + "年"//
				+ (today.getMonth()+1) + "月"//
				+ today.getDate() + "日 "//
				+ days[today.getDay()];
		}
			//-->           
		</script>
	</head>
	<body style="background-color: #ced7ff; text-align: left; font-color: white; font-size: 12px; padding: 0px; margin: 4px;"> 
		&nbsp;&nbsp;
		<span id="txtDate"></span>&nbsp;&nbsp;<span id="txtTime"></span>
		<script type="text/javascript">showDate("txtDate"); showTime("txtTime");</script>
	</body>
</html>
