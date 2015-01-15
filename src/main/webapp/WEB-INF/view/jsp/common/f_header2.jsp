<%@ page language="java" pageEncoding="UTF-8"%>
<header class="site-header">
    <div class="container inner clearfix">
        <div class="head-name">
            <a href="<%=request.getContextPath() %>/" title="IT干货技术分享网">
                <span class="head-logo">ItGanHuo.CN</span>
            </a>
        </div>
        <div class="head-search">
<%--            <form id="search" action="<%=request.getContextPath() %>/search">--%>
                <input type="text" id="wd" class="form-control text-27 input-search" name="q" autocomplete="off" spellcheck="false" placeholder="搜索主题">
                <button class="btn-search" type="submit" onclick="window.open('http://www.baidu.com/s?wd=site:www.itganhuo.cn '+document.getElementById('wd').value)">搜索</button>
<%--            </form>--%>
        </div>
        <ul class="head-nav">
            <li class="current"><a href="#">技术分享</a></li>
        </ul>
        <div class="head-misc">
            <span>真心不错，我也来</span> <a class="btn btn-primary action" href="<%=request.getContextPath() %>/user/share">+分享</a>
        </div>
    </div>
</header>