<%@ page language="java" import="cn.itganhuo.app.common.pool.ConstantPool,cn.itganhuo.app.common.utils.StringUtil,cn.itganhuo.app.entity.User" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
User um = (User) session.getAttribute(ConstantPool.USER_TEMP_INFO);
// if (um == null || !StringUtil.hasText(um.getId())) {
//     out.print("八戒你又开始捣乱了");
//     return;
// }
// String account = um.getAccount();
String account = "";
int index = account.lastIndexOf("@") + 1;
String mail_url = "http://mail." + account.substring(index, account.length());
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="IT干货技术分享网-注册成功跳转页面">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="IT干货技术分享网-注册成功跳转页面">
<meta content="_csrf" name="csrf-param">
<meta content="gqpb25GFirs5rHHq/KylANDoUyNTk46Ey0Dng=" name="csrf-token">
<link rel="icon" href="#">
<link href="<%=path %>/static/css/min.css" rel="stylesheet" type="text/css" media="all">
<script type="text/javascript" src="<%=path %>/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/analytics.js"></script>
<title>注册成功跳转页面-IT干货技术分享网</title>
</head>
<body>
<%@ include file="../common/header.jsp" %>

<div id='main'>
	<div id="content" style="margin-right: 0px;">
		<div class="panel">
			<div class="header">
				<ul class="breadcrumb">
				  <li>请在24小时内点击邮件中的链接继续完成注册</li>
				</ul>
			</div>
			<div class="inner">
				<p>邮件已发送到邮箱<%=account %></p>
				<span class="tracking-ad" data-mod="popu_29"> <a href="<%=mail_url %>" class="btn-primary check-mail" target="_blank">立即查收邮件</a> </span> 
			<div class="inner">
		</div>
		</div></div>
	</div>
</div>
<%@ include file="../common/footer.jsp" %>	
</body>
</html>