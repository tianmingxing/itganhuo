<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>开发日志-IT干货技术分享网</title>
<link rel="stylesheet" href="<%=path%>/css/style.css" />
<link rel="stylesheet" href="<%=path%>/css/responsive.css" />
</head>

<body id="body">

<%@ include file="common/f_header1.jsp" %>
<%@ include file="common/f_header2.jsp" %>

<div class="container">
<div style="height: 300px">
<h3>开发日志</h3>
<h4>2014-11-24</h4>
<p>完成第二期计划的功能</p>
<hr>
<h4>2014-9-15</h4>
<p>IT干货技术分享网诞生了。</p>
</div>
</div>

<%@ include file="common/f_footer.jsp" %>

</body>
</html>
