<%@ page language="java" import="cn.itganhuo.app.entity.User,cn.itganhuo.app.common.utils.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="description" content="IT干货技术分享网-会员信息修改">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="IT干货技术分享网-会员信息修改">
<meta content="_csrf" name="csrf-param">
<meta content="gqpb25GFirs5rHHq/KylANDoUyNTk46Ey0Dng=" name="csrf-token">
<link rel="icon" href="#">
<link href="<%=request.getContextPath()%>/static/css/min.css" rel="stylesheet" type="text/css" media="all">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/analytics.js"></script>
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
				  <li class="active">个人信息</li>
				</ul>
			</div>
			<div class="inner">
				<div class="alert alert-error" style="display: none">
					  <strong id="j_msg">信息有误。</strong>
				</div>
				<form id="update_form" action="<%=request.getContextPath()%>/user/update" class="form-horizontal" method="post">
					<input type="hidden" name="request_token" value="<%=HttpUtil.getToken(session)%>">
					<div class="control-group">
						<label class="control-label" for="account">账号：</label>
						<div class="controls">
							<input type="hidden" name="account" value="${user.account}">${user.account}
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="name">昵称：</label>
						<div class="controls">
							<input class="input-xlarge" id="name" name="name" size="30" type="text" value="${user.nickname}"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="sex">性别：</label>
						<div class="controls">
							<input class="input-xlarge" id="sex" name="sex" size="30" type="radio" value="1" ${(user.sex == 1)?'checked':''}>男&nbsp;&nbsp;
							<input class="input-xlarge" id="sex" name="sex" size="30" type="radio" value="2" ${(user.sex == 2)?'checked':''}>女&nbsp;&nbsp;
							<input class="input-xlarge" id="sex" name="sex" size="30" type="radio" value="3" ${(user.sex == 3)?'checked':''}>保密
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="email">电子邮箱：</label>
						<div class="controls">
							<c:choose>
								<c:when test="${user.email != null}">
									<input class="input-xlarge" id="email" name="email" size="30" value="${user.email}">
									<a href="<%=request.getContextPath()%>/user/emailskip/${user.account}">认证邮箱</a>
								</c:when>
								<c:when test="${user.isValidateEmail == 1}">
									<input type="button" class="input-xlarge" size="30" value="${user.email}">
									<a href="#" style="color: red;">已认证</a>
								</c:when>
								<c:otherwise>
									<input class="input-xlarge" id="email" name="email" size="30" value="">
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="qq">QQ：</label>
						<div class="controls">
							<input class="input-xlarge" id="qq" name="qq" size="30" value="${user.qq}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="phone">手机：</label>
						<div class="controls">
							<input class="input-xlarge" id="phone" name="phone" size="30" value="${user.phone}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="phone">电话：</label>
						<div class="controls">
							<input class="input-xlarge" id="tel" name="tel" size="30" value="${user.tel}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
						</div>
					</div>
					<div>
						<span style="margin-left: 180px;"><a href="${pageContext.request.contextPath}/user/updatePassword">修改密码</a> </span>
						<span style="margin-left: 20px;"><a href="${pageContext.request.contextPath}/user/upload">上传头像</a> </span>
					</div>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugin/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	function formCheck(){
	var email = $("#email").val();
	var name = $("#name").val();
	var email_reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	if (!email_reg.test(email)) {
		$(".alert.alert-error").show();
		$("#j_msg").text("邮箱格式不正确");
		$("#email").focus();
		return;
	} else {
		$(".alert.alert-error").hide();
	}
	$(".btn").attr("onclick", "");
	$(".alert.alert-error").hide();
	$("#update_form").submit();
	}
</script>
</html>