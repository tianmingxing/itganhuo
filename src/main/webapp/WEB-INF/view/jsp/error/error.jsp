<%@ page language="java" import="java.util.*,cn.itganhuo.app.entity.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="<%=path %>/css/style.css" />
<link rel="stylesheet" href="<%=path %>/css/responsive.css" />
<title>-IT干货技术分享网</title>
</head>
<body id="body">
	<%@ include file="../common/f_header1.jsp"%>
	<%@ include file="../common/f_header2.jsp"%>

	<div class="container wrap">
		<div class="row inner edge">
			<h1>八戒，你又调皮了不是！</h1>
			<p><a href="javascript:alert('懒的上链接，自己单击左上角logo吧！^_^')">果断回首页</a></p>
			<div style="display: none">${errorMsg }</div>
		</div>
	</div>

	<%@ include file="../common/f_footer.jsp"%>
	<a id="backtop" class="mobi-hide hidden" href="#body">回顶部</a>
</html>