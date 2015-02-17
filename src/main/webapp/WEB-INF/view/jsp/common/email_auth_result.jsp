<%@ page language="java" import="java.util.*,cn.itganhuo.app.common.utils.StringUtil" pageEncoding="UTF-8"%>
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
<link rel="icon" href="#">
<link href="<%=path%>/static/css/min.css" rel="stylesheet" type="text/css" media="all">
<script type="text/javascript" src="<%=path%>/static/js/analytics.js"></script>
<title>邮箱认证结果-IT干货技术分享网</title>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	
<div id='main'>
	<div id="content" style="margin-right: 0px;">
		<div class="panel">
			<div class="header">
				<ul class="breadcrumb">
				  <li>认证结果</li>
				</ul>
			</div>
			<div class="inner">
				<p><%=StringUtil.unicode2Str(request.getAttribute("msg").toString())%></p>
				<p><a href="<%=path%>/user/signin">登录</a>&nbsp;&nbsp;<a href="<%=path%>/">回到首页</a></p>
			</div>
		</div>
	</div>
</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>
