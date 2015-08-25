var g_blinkswitch=0;
var g_blinkid = 0;
var popupClickedByUser=false;
var popupShowed=false;
var lastMaxMessageId=0;
var checkMessageIntervalMS=10000;//in ms
var checkMessagePerVisit=true;
var checkMessagePeriodically=false;
var showPopup=false;
var hasNewMessage=false;
var newMessageChecked=false;
jQuery(document).ready(
function()
{
	check();
	if(checkMessagePeriodically===true){
		setInterval("check()",checkMessageIntervalMS);
	}
}
);

function checkMessages(){
	
	if(checkMessagePerVisit===true){
		check();
		
	}
}

function check(){
	readData();
}

function blinkNewMsg(){
	/*html1 = "<a href=/msg/ class=n style='margin-top:-5px;'><img src='img/mail/y.gif' onmouseover='javascript:xs(4);' border=0></a>";
	html2 = "<a href=/msg/ class=n style='margin-top:-5px;'><img src='img/mail/g.gif' onmouseover='javascript:xs(4);' border=0></a>";
	jQuery("#mailImgDiv").html(g_blinkswitch % 2 ? html2 : html1);	
	*/
	if(g_blinkswitch%2)
		jQuery("span.mail_img").fadeIn();
	else
		jQuery("span.mail_img").fadeOut();
	
		g_blinkswitch++;
}

function stopBlinkNewMsg()
{
	if (g_blinkid)
	{
		clearInterval(g_blinkid);
		g_blinkid = 0;
		jQuery("span.mail_img").fadeIn();
	}
	popupShowed=false;
}

function clickMessageIcon(){
	newMessageChecked=true;
	stopBlinkNewMsg();
}
function readData(){
//	messagesDwrBean.getTotalMessagesAmount(function(data){
//						jQuery("mail_info").html(""+data);
//						
//					});
	
	
	messagesDwrBean.getUnreadMessagesMaxId(function(data){
		//window.status=lastMaxMessageId;
		if(lastMaxMessageId<data.id){
			//new message
			lastMaxMessageId=data.id;
			hasNewMessage=true;
			newMessageChecked=false;
			checkMsg(data.title,true);
		}else{
			hasNewMessage=false;
			checkMsg(data.title,checkMessagePeriodically===false);
			
		}
		
	});
	
}

function checkMsg(content,shouldShowPopup){
		
	messagesDwrBean.getUnreadMessagesAmount(function(data){
						
						jQuery("span.mail_info").text("("+data+")");
						if(showPopup&&shouldShowPopup&&newMessageChecked==false)
							parent.showMessage("您有"+data+"条未读消息",content);
							
						if(data>0&&(hasNewMessage||newMessageChecked==false)){
							if(g_blinkid==0){
								g_blinkid = setInterval(blinkNewMsg, 1000);
								
								}
						}else{
							stopBlinkNewMsg();
						}
						
					});
	
}

function errh(msg) {

}

dwr.engine.setErrorHandler(errh);
