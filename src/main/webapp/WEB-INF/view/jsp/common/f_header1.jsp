<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!--[if lt IE 9]>
    <div class="error chromeframe">您的浏览器版本<strong>太旧了</strong>，为了正常地访问网站，请升级您的浏览器 <a target="_blank" href="http://browsehappy.com">立即升级</a></div>
<![endif]-->
<div class="global-nav">
    <nav class="global-nav container">
        <ul class="site-nav hidden-xs">
            <li class="current"><a href="<%=request.getContextPath() %>/">分享</a></li>
<%--            <li class=""><a href="<%=request.getContextPath() %>/tags">标签</a></li>--%>
<%--            <li class=""><a href="<%=request.getContextPath() %>/users">用户</a></li>--%>
<%--            <li class=""><a href="<%=request.getContextPath() %>/badges">徽章</a></li>--%>
<%--            <li class=""><a href="<%=request.getContextPath() %>/events">活动</a></li>--%>
        </ul>
        <shiro:guest>
        	<ul class="user-nav">
            	<li>
                	<a id="login-link" href="<%=request.getContextPath() %>/user/signin">登录</a>
            	</li>
            	<li>
                	<a href="<%=request.getContextPath() %>/user/register">注册</a>
            	</li>
            </ul>
        </shiro:guest>
        <shiro:user>
	        <ul class="user-nav">
	            <li class="has-dropdown">
	                <a class="hidden-xs" href="<%=request.getContextPath() %>/logout">退出</a>
	            </li>
<%--	            <li class="has-dropdown">--%>
<%--	                <a id="msg-link" href="<%=request.getContextPath() %>/user/events">消息</a>--%>
<%--	            </li>--%>
	            <li class="has-dropdown">
	                <a href="<%=request.getContextPath() %>/user/<shiro:principal/>" class="hidden-xs"><shiro:principal/><i class="i-arrow-s"></i></a>
<%--	                <a href="<%=request.getContextPath() %>/u/allen_597382" class="visible-xs">我</a>--%>
<%--	                <ul class="dropdown-item">--%>
<%--	                    <li><a href="<%=request.getContextPath() %>/u/allen_597382">我的主页</a></li>--%>
<%--	                    <li><a href="<%=request.getContextPath() %>/bookmarked">我的收藏</a></li>--%>
<%--	                    <li><a href="<%=request.getContextPath() %>/user/settings">帐号设置</a></li>--%>
<%--	                    <li><a href="<%=request.getContextPath() %>/user/logout">退出</a></li>--%>
<%--	                    <li class="hr"><a href="<%=request.getContextPath() %>/faq">帮助中心</a></li>--%>
<%--	                    <li><a href="http://0x.www.itganhuo.cn">建议反馈</a></li>--%>
<%--	                </ul>--%>
	            </li>
	        </ul>
        </shiro:user>
    </nav>
</div>