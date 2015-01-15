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
				  <li class="active">注册</li>
				</ul>
			</div>
			<div class="inner">
				<div class="alert alert-error" style="display: none">
					  <strong id="j_msg">信息有误。</strong>
				</div>
				<form id="insertEmailTemplate_form" class="form-horizontal" method="post">
					<div class="control-group">
						<label class="control-label" for="type">模板类型</label>
						<div class="controls">
							<select id='type'>
								<option value="1" selected>邮件模板</option>
								<option value="2">短信模板</option>
								<option value="3">站内信模板</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="name">模板名称</label>
						<div class="controls">
							<input class="input-xlarge" id="name" name="name" size="30">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="con">模板内容</label>
						<div class="controls">
							<textarea rows="15" cols="50" name="content" id="templateContent"></textarea>
						</div>
					</div>
					<div class="controls">
							<input class="input-xlarge" id="sex" name="sex" size="30" type="radio" value="0" checked>
								正常使用
							</input>
							<input class="input-xlarge" id="sex" name="sex" size="30" type="radio" value="1" >
								逻辑删除
							</input>
						</div>

					<div class="form-actions">
						<input type="button" class="btn" value="提交" onclick="formCheck();">
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
<script type="text/javascript" src="/js/min.js"></script>
<script type="text/javascript">
	function formCheck(){
	var name = $("#name").val();
	var content = $("#templateContent").val();
	if(name == "") {
		$(".alert.alert-error").show();
		$("#j_msg").text("模板名称不能为空");
		$("#name").focus();
		return;
	}else {
		$(".alert.alert-error").hide();
	}
	if(content == "") {
		$(".alert.alert-error").show();
		$("#j_msg").text("模板内容不能为空");
		$("#templateContent").focus();
		return;
	}else {
		$(".alert.alert-error").hide();
	}
	$(".btn").attr("onclick", "");
	$(".alert.alert-error").hide();
	$("#insertEmailTemplate_form").submit();
	}
</script>