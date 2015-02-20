<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="description" content="IT干货。JAVA私塾在线学习社区（1000人群329232140）">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="JAVA私塾在线学习社区（1000人群329232140）">
<meta content="_csrf" name="csrf-param">
<meta content="gqpb25GFirs5rHHq/KylANDoUyNTk46Ey0Dng=" name="csrf-token">
<title>登录-IT干货技术分享网</title>
<link rel="icon" href="#">
<link href="<%=path %>/static/css/min.css" rel="stylesheet" type="text/css" media="all">
<script type="text/javascript" src="<%=path %>/static/js/plugin/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/analytics.js"></script>
<script type="text/javascript">
function formCheck() {
	var account = jQuery("#account").val(), password = jQuery("#password").val(), security_code = $('#security_code').val();
	var email_reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
	password_reg = /^.*[A-Za-z0-9\\w_-]+.*$/;
	if (account == "") {
		jQuery(".alert.alert-error").show();
		jQuery("#j_msg").text("账号不能为空");
		jQuery("#account").focus();
		return false;
	} else if (account.length < 6) {
		jQuery(".alert.alert-error").show();
		jQuery("#j_msg").text("账号长度至少6个字符");
		jQuery("#account").focus();
		return;
	} else {
		jQuery(".alert.alert-error").hide();
	}
	if (password == "") {
		jQuery(".alert.alert-error").show();
		jQuery("#j_msg").text("密码不能为空");
		jQuery("#password").focus();
		return;
	} else if (password.length < 6) {
		jQuery(".alert.alert-error").show();
		jQuery("#j_msg").text("密码长度至少6个字符");
		jQuery("#password").focus();
		return;
	} else if (!password_reg.test(password)) {
		jQuery(".alert.alert-error").show();
		jQuery("#j_msg").text("密码含有特殊字符");
		jQuery("#password").focus();
		return;
	} else {
		jQuery(".alert.alert-error").hide();
	}
	if (security_code == '') {
		jQuery(".alert.alert-error").show();
		jQuery("#j_msg").text("验证码不能为空。");
		jQuery("#security_code").focus();
		return;
	} else {
		jQuery(".alert.alert-error").hide();
	}
	jQuery(".btn").attr("onclick", "");
	jQuery(".btn").val("登录中...");
	jQuery(".alert.alert-error").hide();
	jQuery.post('<%=path %>/user/signin',jQuery('#signin_form').serialize(),function(data){
		if (data.status == '0000') {
			window.location.href = '<%=path %>/user/' + data.appendInfo;
		} else {
			jQuery(".alert.alert-error").show();
			jQuery("#j_msg").text(data.message);
			jQuery(".btn").attr("onclick", "formCheck();");
			jQuery(".btn").val("登录");
		}
	});
}

//更换验证码
function refreshCaptcha() {
    document.getElementById("img_captcha").src="<%=path%>/verificationCode/kaptcha.jpg?t=" + Math.random();  
}
</script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<div id='main'>
	<div id='sidebar'>
		<div class='panel'>
			<div class='header'> <span class='col_fade'>关于</span> </div>
			<div class="inner">
				<p>干货网</p>  
				<p>在这里你可以：</p>
				<ul>
					<li>向别人提出你遇到的问题</li>
					<li>帮助遇到问题的人</li>
					<li>分享自己的知识</li>
					<li>和其它人一起进步</li>
				</ul>
			</div>
		</div>
    </div>
	<div id="content">
		<div class="panel">
			<div class="header">
				<ul class="breadcrumb">
				  <li><a href="<%=path %>">主页</a><span class="divider">/</span></li>
				  <li class="active">登录</li>
				</ul>
			</div>
			<div class="inner">
				<div class="alert alert-error" style="display: none">
				  <strong id="j_msg"></strong>
				</div>
				<form id="signin_form" class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="account">用户名</label>
						<div class="controls">
							<input class="input-xlarge" id="account" name="account" size="30" type="text">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">密码</label>
						<div class="controls">
							<input class="input-xlarge" id="password" name="password" size="30" type="password">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="security_code">验证码</label>
						<div class="controls">
							<input class="input-mini" id="security_code" name="securityCode" size="4" type="text">
							<img id="img_captcha" alt="验证码" src="<%=path%>/verificationCode/kaptcha.jpg">
							<a href="javascript:refreshCaptcha();">看不清？</a>
						</div>
					</div>
					<div class="form-actions">
						<input type="button" class="btn" value="登录" onclick="formCheck();">
						<a id="forgot_password" href="#">忘记密码了?</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div id='backtotop'>回到顶部</div>
<%@ include file="../common/footer.jsp" %>
<div id="sidebar-mask"></div>
</body>
<script type="text/javascript">
jQuery(document).keyup(function(e){
	var e = e || event, keycode = e.which || e.keyCode;
	if (keycode == 13) {
		jQuery(".btn").trigger("click");
	}
});
</script>
</html>