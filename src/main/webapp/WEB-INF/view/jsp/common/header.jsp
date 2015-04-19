<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class='navbar'>
  <div class='navbar-inner'>
    <div class='container'>
		<a class='brand' href='<%=request.getContextPath() %>/'><img src="<%=request.getContextPath() %>/static/imgs/logo.png" title="IT干货技术分享网站" width="127px" height="25px"/></a>
		<shiro:guest>
			<ul class='nav pull-right'>
				<li><a href='<%=request.getContextPath() %>/user/signin'>登录</a></li>
				<li><a href='<%=request.getContextPath() %>/user/register'>注册</a></li>
			</ul>
		</shiro:guest>
		<shiro:user>
			<ul class='nav pull-right'>
				<li><a href='<%=request.getContextPath() %>/user/signin'>退出</a></li>
				<li><a href='<%=request.getContextPath() %>/user/<shiro:principal/>'><shiro:principal/></a></li>
			</ul>
		</shiro:user>
		<a class="btn btn-navbar" id="responsive-sidebar-trigger"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a>
	</div>
  </div>
</div>