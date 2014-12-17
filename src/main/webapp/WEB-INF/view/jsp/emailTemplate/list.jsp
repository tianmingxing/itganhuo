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
		</div>
		<div>
			<table border="1px">
				<tr align="center">
					<thead>模版类型</thead>
					<thead>模版名称</thead>
					<thead>是否可用</thead>
					<thead>创建时间</thead>
					<thead>模板内容</thead>
				</tr>
			<c:forEach items="${emailTemplateList}" var="template">
				<tr>
					<td>${template.type}</td>
					<td>${template.name}</td>
					<td>${template.isDel}</td>
					<td>${template.postDate}</td>
					<td>${template.content}</td>
				</tr>
				</c:forEach>
			</table>
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
</script>