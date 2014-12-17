<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="description" content="IT干货技术分享网-会员密码修改">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="IT干货技术分享网-会员密码修改">
<meta content="_csrf" name="csrf-param">
<meta content="gqpb25GFirs5rHHq/KylANDoUyNTk46Ey0Dng=" name="csrf-token">
<link rel="icon" href="#">
<link href="<%=request.getContextPath()%>/css/min.css" rel="stylesheet" type="text/css" media="all">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/analytics.js"></script>
<title>会员注册</title>
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
				  <li><a href="/">主页</a><span class="divider">/</span></li>
				  <li class="active">修改密码</li>
				</ul>
			</div>
			<div class="inner">
				<div class="alert alert-error" style="display: none">
					  <strong id="j_msg">信息有误。</strong>
				</div>
				<form id="updatePassword_form" class="form-horizontal" method="post">
					<input type="hidden" id="account" name="account" value="${user.account}">
					<div class="control-group">
						<label class="control-label" for="originalpassword">请输入原始密码：</label>
						<div class="controls">
							<input class="input-xlarge" id="originalanpassword" name="originalanpassword" size="30" type="password" onblur="check();">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">请您输入新密码：</label>
						<div class="controls">
							<input class="input-xlarge" id="password" name="password" size="30" type="password">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="repassword">确认输入的密码：</label>
						<div class="controls">
							<input class="input-xlarge" id="repassword" size="30" type="password">
						</div>
					</div>
					<font color="red" style="margin-left: 48px;">友情提示：成功修改密码后，系统将发送一封确认邮件到您注册账户所使用的电子邮箱中,以确保信息安全</font>
					<div class="form-actions">
						<input type="button" class="btn" id="submit1" value="提交" onclick="formCheck();">
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	var flag = 1;
	
	function check(){
		$.post("${pageContext.request.contextPath}/user/checkpassword",{
			originalanpassword:$("#originalanpassword").val(),
			account:$('#account').val()
		},
		function(data){
			alert(data);
		 if (data == "noPass") {
		 	flag = 0;
			$(".alert.alert-error").show();
			$("#j_msg").text("原始密码不正确");
			$("#password").focus();
		}else{
			flag = 1;
			$(".alert.alert-error").hide();
		}
		});
	
	}

	function formCheck(){
	var originalanpassword = $("#originalanpassword").val();
	var password = $("#password").val();
	var repassword = $("#repassword").val();
	var password_reg = /^.*[A-Za-z0-9\\w_-]+.*$/;
	$(".alert.alert-error").hide();
	if(originalanpassword == '') {
		$(".alert.alert-error").show();
		$("#j_msg").text("请输入原始密码");
		$("#password").focus();
		return;
	}else if (password == "") {
		$(".alert.alert-error").show();
		$("#j_msg").text("密码不能为空");
		$("#password").focus();
		return;
	}else if (password.length < 6) {
		$(".alert.alert-error").show();
		$("#j_msg").text("密码长度至少6个字符");
		$("#password").focus();
		return;
	} else if (!password_reg.test(password)) {
		$(".alert.alert-error").show();
		$("#j_msg").text("密码含有特殊字符");
		$("#password").focus();
		return;
	} else if (repassword != password) {
		$(".alert.alert-error").show();
		$("#j_msg").text("确认密码与新密码不同");
		$("#repassword").focus();
		return;
	}else {
		$(".alert.alert-error").hide();
	}
		
	if(flag == 1){
	 	$(".btn").attr("onclick", "");
		$(".alert.alert-error").hide();
		$("#updatePassword_form").submit();
	} else {
		$(".alert.alert-error").show();
		$("#j_msg").text("原始密码不正确");
		$("#originalanpassword").focus();
		return;
	}	
	}
</script>
</html>