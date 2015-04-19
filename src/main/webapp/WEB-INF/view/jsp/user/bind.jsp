<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="description" content="IT干货技术分享网-修改信息">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="IT干货技术分享网-修改信息">
<meta content="_csrf" name="csrf-param">
<meta content="gqpb25GFirs5rHHq/KylANDoUyNTk46Ey0Dng=" name="csrf-token">
<title>完善资料-IT干货技术分享网</title>
<link rel="icon" href="#">
<link href="<%=path %>/static/css/min.css" rel="stylesheet" type="text/css" media="all">
<script type="text/javascript" src="<%=path %>/static/js/plugin/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/analytics.js"></script>
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101210014" data-redirecturi="http://www.itganhuo.cn/qc_callback.html" charset="utf-8"></script>
<script type="text/javascript">
function formCheck() {
	var account = jQuery("#account").val(), security_code = $('#security_code').val();
	var email_reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
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

	if (security_code == '') {
		jQuery(".alert.alert-error").show();
		jQuery("#j_msg").text("验证码不能为空。");
		jQuery("#security_code").focus();
		return;
	} else {
		jQuery(".alert.alert-error").hide();
	}
	jQuery(".btn").attr("onclick", "");
	jQuery(".alert.alert-error").hide();
	jQuery.post('<%=path %>/user/qqSignin',jQuery("#register_form").serialize(),function(data) {
		if (data.status == '0000') {
			window.location.href = '<%=path %>/user/' + data.appendInfo;
		} else {
			jQuery(".alert.alert-error").show();
			jQuery("#j_msg").text(data.message);
			jQuery(".btn").attr("onclick", "formCheck();");
		}
	});
}
jQuery(document).keyup(function(e) {
	var e = e || event, keycode = e.which || e.keyCode;
	if (keycode == 13) {
		jQuery(".btn").trigger("click");
	}
});

//更换验证码
function refreshCaptcha() {
    document.getElementById("img_captcha").src="<%=path%>/verificationCode/kaptcha.jpg?t=" + Math.random();  
}
</script>
</head>
<body>
<script type="text/javascript">
if(QC.Login.check()){
    //检查本次登录进来的用户是否有绑定过本地账号，如果有则帮助它自动登录，否则就走下面的流程。
    QC.Login.getMe(function (openId, accessToken) {
        var paras = {};

        $('input[name="openId"]').val(openId);
        $('input[name="accessToken"]').val(accessToken);

        QC.api("get_user_info", paras)
        .success(function (s) {
            $('input[name="nickname"]').val(s.data.nickname);
            var imgSrc = (s.data.figureurl_2 == null || s.data.figureurl_2 == '') ? s.data.figureurl_qq_1 : s.data.figureurl_2;
            $('#j_photo').attr('src', imgSrc);
        })
        .error(function (f) {
            alert("获取用户信息失败！");
            window.location.href = '/';
        });
    });
} else {
    //如果是用户自己访问本页面则统一跳转到登录页面
    window.location.href = '<%=path%>/user/signin';
}
</script>
<%@ include file="../common/header.jsp" %>
<div id='main'>
	<div id='sidebar'>
		<div class='panel'>
			<div class='header'> <span class='col_fade'>关于</span> </div>
			<div class="inner">
				<p>IT干货</p>  
				<p>在这里你可以：</p>
				<ul>
					<li>分享工作经验帮助遇到问题的人</li>
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
				  <li class="active">完善资料</li>
				</ul>
			</div>
            <div class="inner">
                <div class="alert alert-error" style="display: block">
                    <strong id="j_msg">系统检测到你已通过第三方成功登录，现在请注册一个本地账号与之关联。</strong>
                </div>
                <form id="register_form" class="form-horizontal">
                    <input type="hidden" name="type" value="2">
                    <input type="hidden" name="openId" value="">
                    <input type="hidden" name="accessToken" value="">
                    <div class="control-group" style="padding-left: 30%;"><img id="j_photo" src="" alt="头像"></div>
                    <div class="control-group">
                        <label class="control-label" for="account">填个登录账号</label>
                        <div class="controls">
                            <input class="input-xlarge" id="account" name="account" size="30" type="text">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="nickname">要用这个昵称吗？</label>
                        <div class="controls">
                            <input class="input-xlarge" id="nickname" name="nickname" size="30" type="text">
                        </div>
                    </div>
					<div class="control-group">
						<label class="control-label" for="security_code">验证码</label>
						<div class="controls">
							<input class="input-mini" id="security_code" name="securityCode" size="4" type="text">
							<img id="img_captcha" alt="验证码" src="<%=path%>/verificationCode/kaptcha.jpg" style="height: 30px; width: 120px">
							<a href="javascript:refreshCaptcha();">看不清？</a>
						</div>
					</div>
					<div class="form-actions">
						<input type="button" class="btn" value="确定" onclick="formCheck();">
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
</html>