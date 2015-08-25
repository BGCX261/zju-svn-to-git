<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<title>注册新用户</title>
		<%@ include file="/WEB-INF/pages/public/commons.jspf"%>
		<script type="text/javascript" src="${baseUrl }/script/front/user.js"></script>
		<script type="text/javascript">
	        function toggleSelectAutoLoginDays(enable) {
	            $("select[name=autoLoginDays]").attr("disabled", !enable);
	        }
	        $(document).ready(function () {
	            toggleSelectAutoLoginDays($("input[name=autoLogin]").attr("checked"));
	            $("input[name=birthday]").datepicker();
	        });
	    </script>
	</head>
	<body>
	
	<!-- 顶部 -->
	<%@ include file="/WEB-INF/pages/public/header.jspf"%>

	<html:form focus="loginName" action="/user" enctype="multipart/form-data"
			onsubmit="return validateRegisterUserForm(this)">
		<input type="hidden" name="method" value="register" />
		<input type="hidden" name="returnPath" value="${param.returnPath }">

		<table class="form" style="width: 700px;">
		        <tr>
		            <td colspan="3" class="title">基本信息(必填)</td>
		        </tr>
		        <tr>
		            <td width="70px;">登录名</td>
		            <td width="300px;">
		            	<html:text property="loginName" styleClass="input1" onblur="checkLoginName(this.value)" />
		                <span id="loginNameMsg"></span>
		            </td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>密码</td>
		            <td><html:password property="password" redisplay="false" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>确认密码</td>
		            <td><html:password property="password2" redisplay="false" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		         <tr>
		            <td>真实姓名</td>
		            <td><html:text property="realname" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		         <tr>
		            <td>昵称</td>
		            <td><html:text property="nickname" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>电子邮件</td>
		            <td><html:text property="email" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>常用手机</td>
		            <td><html:text property="cellphone" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>毕业专业</td>
		            <td><html:text property="major" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>学历</td>
		            <td><html:text property="education" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>现在职称</td>
		            <td><html:text property="title" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>籍贯</td>
		            <td><html:text property="birthplace" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		         <tr>
		            <td>居住地址</td>
		            <td><html:text property="address" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>邮编</td>
		            <td><html:text property="postcode" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>婚姻状况</td>
		            <td><html:select property="marriage" styleClass="input1">
		            <html:option key="1" value="已婚" />
		            <html:option key="2" value="未婚" /></html:select></td>
		            <td class="red">*</td>
		        </tr>
		         <tr>
		            <td>所在单位</td>
		            <td><html:text property="comeFrom" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td width="70px;">性别</td>
		            <td width="300px;">
			            <html:radio property="sex" value="SECRECY">保密</html:radio>
						<html:radio property="sex" value="MALE">男</html:radio>
						<html:radio property="sex" value="FEMALE">女</html:radio>
		            </td>
		            <td class="red">*</td>
		        </tr>
		        <tr>
		            <td>生日</td>
		            <td><html:text property="birthday" styleClass="input1"/></td>
		            <td class="red">*</td>
		        </tr>
		    </table>
		
		    <table class="form" style="width: 700px;">
		        <tr>
		            <td colspan="3" class="title"> 详细信息(选填)</td>
		        </tr>
		        
		        <tr>
		            <td>QQ号码</td>
		            <td><html:text property="qq" styleClass="input1"/></td>
		            <td class="red">&nbsp;</td>
		        </tr>
		        <tr>
		            <td>MSN</td>
		            <td><html:text property="msn" styleClass="input1"/></td>
		            <td class="red">&nbsp;</td>
		        </tr>
		         <tr>
		            <td>头像图片</td>
		            <td><html:file property="avatarFile" styleClass="input1"/></td>
		            <td>最大像素 ${settings.avatarMaxWidth } * ${settings.avatarMaxHeight },<br>
		                  大小不能超过 ${settings.avatarMaxFileSize } 字节.
		            </td>
		        </tr>
		         <tr>
		            <td>常用交通工具</td>
		            <td><html:text property="car" styleClass="input1"/></td>
		            <td class="red">&nbsp;</td>
		        </tr>
		        <tr>
		            <td>本人特长</td>
		            <td><html:textarea property="speciality" styleClass="textarea1"/></td>
		            <td class="red">&nbsp;</td>
		        </tr>
		        <tr>
		            <td>能为校友提供的资源</td>
		            <td><html:textarea property="resource" styleClass="textarea1"/></td>
		            <td class="red">&nbsp;</td>
		        </tr>
		        <tr>
		            <td>意见与建议</td>
		            <td><html:textarea property="advice" styleClass="textarea1"/></td>
		            <td class="red">&nbsp;</td>
		        </tr>
		        <tr>
		            <td>个性签名</td>
		            <td><html:textarea property="signature" styleClass="textarea1"/></td>
		            <td>最大长度为 255 个字符</td>
		        </tr>
		        <tr>
		            <td>服务条款</td>
		            <td colspan="2"><textarea readonly="readonly" class="textarea1" style="width: 450px;">
		    会员注册协议条款：
1、凡浙江大学毕业的本科生、硕士研究生、博士研究生，凡在浙大正式工作过的教师和管理人员、特聘教授、名誉教授，参加过浙大组织的短期培训、进修或在浙大有工作经历者并经认定，在南京工作或退休后在南京生活居住者，均可在江苏省浙大校友会网站注册，成为注册校友会网站会员，获取校友会网站注册会员的会员号。
2、凡校友在校友会网站注册，必须提供个人真实、有效、完整的相关信息。如发现注册信息虚假、不实、无效，网站管理员有权终身禁止在校友会网站注册，列入校友会注册会员黑名单，谢绝参加由校友会网站组织的正式与非正式活动，不予提供校友会相关资料信息，取消其获得网站提供的奖品、礼品及其它无偿服务的资格。
3、本网站注册校友必须遵守国家有关规定，不得发表、传播涉及色情、暴力、低俗以及其它不良信息与内容，自觉维护社会秩序，维护浙大校友的社会形象。
4、校友会网站对校友的个人信息有保密的义务，相关信息可用于与服务校友、沟通校友之间信息、开展相关活动。
5、注册校友对校友会网站提供的校友会活动信息、其它校友的个人信息有保密的义务，不得随意向非校友提供。注册校友如需要对外提供校友会相关资料与信息，必须经校友会网站管理员、有关校友本人同意，否则后果自负。
未尽事宜，由校友会网站会员部负责解释。
</textarea></td>
		        </tr>
		        <tr>
		            <td align="center" colspan="3">
		            	<html:submit value="同意如下条款并提交" />
						<html:reset value="重填" />
		            </td>
		        </tr>
		    </table>
		</html:form>
		
		<!--底部-->
		<%@ include file="/WEB-INF/pages/public/footer.jspf"%>
	</body>
</html>
