<%@ page language="java" import="cn.itganhuo.app.entity.User,cn.itganhuo.app.common.pool.ConstantPool" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	User um = (User) request.getAttribute("user_model");
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
<link href="<%=path %>/css/min.css" rel="stylesheet" type="text/css" media="all">
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/analytics.js"></script>
<title>IT干货技术分享网-会员中心</title>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<div id='main'>
	<div id='sidebar'>
		<div class="panel">
			<div class="header">
				<span class="col_fade">个人信息</span>
			</div>
			<div class="inner">
				<div class="user_card">
					<div>
						<a class="user_avatar" href="#">
							<img src="<%=path %>/photo/<%=um.getAccount()%>.jpg" title="<%=um.getAccount()%>">
						</a>
						<span class="user_name"><a class="dark" href="#"><%=um.getAccount()%></a></span>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/user/update">设置</a> 
						<div class="board clearfix">
							<div class="floor">
								<a href="#"><span class="big">196</span> 话题收藏</a>
							</div>
							<div class="floor">
								<a href="#"><span class="big">36</span> 关注</a>
								<div class="space"></div>
								<a href="#"><span class="big">52</span> 粉丝</a>
								<div class="space"></div>
								<span class="big">2925</span> 积分
							</div>
							<div class="floor">
								<a href="<%=path %>/user/share" style="background: rgb(0,154,97); color: white; width: 30px; height: 15px;">开始分享</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="content">
	<%--
		<div class="panel">
			<div class="header">
				<ul class="breadcrumb">
					<li><a href="<%=path %>">主页</a><span class="divider">/</span></li>
					<li class="active"><%=um.getAccount()%></li>
				</ul>
			</div>
			<div class="inner userinfo">
				<div class="user_big_avatar">
					<img src="<%=path %>/imgs/03.png" class="user_avatar" title="<%=um.getAccount()%>">
				</div>
				<a class="dark"><%=um.getAccount()%></a>
				<div class="col_fade">
					<span>
						<img src="<%=path %>/imgs/04.png">
						36 关注
						52 粉丝
						2925 积分</span>
				</div>
				<div class="user_profile">
					<ul class="unstyled">
						<li>
							<img class="user_icon" src="<%=path %>/imgs/05.png">
							<a class="dark" href="#" target="_blank">http://#</a>
						</li>
						<li>
							<img class="user_icon" src="<%=path %>/imgs/06.png">
							<a class="dark" href="#" target="_blank">@youxiachai</a>
						</li>		
						<li>
							<img class="user_icon" src="<%=path %>/imgs/07.png">
							<a class="dark" href="#" target="_blank">http://#</a>
						</li>
									
					</ul>
				</div>
				<p class="col_fade">于 2012-9-30 16:07 加入 CNode</p>
			</div>
		</div>
		--%>
		<div class="panel">
			<div class="header">
				<span class="col_fade">最近发布话题</span>
			</div>
			<c:choose>
				<c:when test="${articles != null && articles.size() > 0 }">
					<c:forEach items="${articles}" var="article">
						<div class="cell">
							<a class="user_avatar pull-left" href="#">
								<img src="<%=path %>/photo/<%=um.getAccount()%>.jpg" title="<%=um.getAccount()%>">
							</a>
							<span class="reply_count pull-left">
								<span class="count_of_replies" title="回复数">${article.comments.size() }</span>
								<span class="count_seperator">/</span>
								<span class="count_of_visits" title="点击数">${article.visitor_volume }</span>
							</span>
							<a class="last_time pull-right" href="#">
								<img class="user_small_avatar" src="<%=path %>/photo/${article.user.account}.jpg">
								<span class="last_active_time">${article.post_date }</span>
							</a>
							<div class="topic_title_wrapper">
								<a class="topic_title" href="<%=path %>/article/${article.id}">${article.title }</a>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>赶快发布一个分享吧！</p>
				</c:otherwise>
			</c:choose>
			<div class="cell more">
				<a class="dark" href="#">查看更多»</a>
			</div>
		</div>
		
		<%--
		<div class="panel">
			<div class="header">
				<span class="col_fade">最近参与话题</span>
			</div>
			<div class="cell">
				<a class="user_avatar pull-left" href="#">
					<img src="<%=path %>/imgs/03.png" title="kingapple">
				</a>
				<span class="reply_count pull-left">
					<span class="count_of_replies" title="回复数">10</span>
					<span class="count_seperator">/</span>
					<span class="count_of_visits" title="点击数">376</span>
				</span>
				<a class="last_time pull-right" href="#">
					<img class="user_small_avatar" src="<%=path %>/imgs/03.png">
					<span class="last_active_time">6-12 15:42</span>
				</a>
				<div class="topic_title_wrapper">
					<a class="topic_title" href="#">赶脚樯把狗彻底屏蔽了</a>
				</div>
			</div>
			<div class="cell more">
				<a class="dark" href="#">查看更多»</a>
			</div>
		</div>
		 --%>
		
	</div>
</div>
<div id='backtotop'>回到顶部</div>
<%@ include file="../common/footer.jsp" %>
<div id="sidebar-mask"></div>
</body>
<script type="text/javascript">
</script>
</html>