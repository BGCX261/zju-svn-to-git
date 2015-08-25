<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<head>
<title>浙江大学江苏校友会</title>
<meta content="text/html; charset=gb2312" http-equiv="Content-Type" />
<meta name="keywords" content="首页" />
<meta name="description" content="首页" />
<script src="<%=request.getContextPath()%>/front_res/jquery.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/front_res/front.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/whole.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/res_base/jeecms_com_www/default/article/css/layout.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
function g(o){
	return document.getElementById(o);
}
function HoverLi(m,n,counter){
	for(var i=1;i<=counter;i++){
		g('tb_'+m+i).className='normaltab';
		g('tbc_'+m+i).className='undis';
	}
	g('tbc_'+m+n).className='dis';
	g('tb_'+m+n).className='curr';
}
</script>
</head>

<body>
<!--页脚Begin-->
<div class="foot">
<div class="foot_pic"><img height="50" alt="" width="200" src="<%=request.getContextPath()%>/img/headimg1.jpg" /></div>
<div class="left foot_msg"><a target="_blank" href="http://www.zju.edu.cn">浙江大学</a> - 
<a target="_blank" href="http://zuaa.zju.edu.cn">校友总会</a> - 
<a target="_blank" href="http://www.zjuhz.com/">杭州校友会</a> -  
<a target="_blank" href="http://www.wxzdr.com">无锡校友会</a> - 
<a target="_blank" href="http://www.zjush.com">上海校友会 </a>- -
<a target="_blank" href="http://www.sz-zd.com.cn">苏州校友会</a> - 
<a target="_blank" href="http://www.jszuaa.com">关于我们</a> - <a target="_blank" href="<%=request.getContextPath()%>/adminLogin.do?">管理员登录</a><br />
江苏省浙大校友会办公电话：84809884　联系地址：南京市中山东路301号54号楼二层北侧  邮编：210002<br />
Powered by <a target="_blank" href="http://www.zju.edu.cn">ZJUER</a> 江苏浙江大学校友会 Copyright ? 2009 www.jszuaa.com, All Rights Reserved . 
</div>
<div style="clear: both"></div>
</div><!--页脚End-->
</body>
</html>
