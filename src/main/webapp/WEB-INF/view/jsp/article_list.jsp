<%@ page language="java" import="java.util.*,cn.itganhuo.app.common.page.*,cn.itganhuo.app.entity.User,cn.itganhuo.app.common.pool.ConstantPool" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8" />
<title>分享-IT干货技术分享网</title>
<link rel="stylesheet" href="<%=path %>/css/style.css" />
<link rel="stylesheet" href="<%=path %>/css/responsive.css" />
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.min.js"></script>
</head>
<body id="body">
<%@ include file="common/f_header1.jsp" %>
<%@ include file="common/f_header2.jsp" %>

<div class="container">
    <div class="row edge">
        <div id="main" class="col-md-9">
            <div class="tab-nav">
                <h2 class="common-title">最新的分享</h2>
                <nav class="sub-tab">
                	<c:choose>
                		<c:when test="${search_type == 2 }">
                			<a href="<%=path %>/articles/1/1">最新的</a>
                			<a class="current" href="<%=path %>/articles/2/1">热门的</a>
                			<a href="<%=path %>/articles/3/1">未被发现的</a>
                		</c:when>
                		<c:when test="${search_type == 3 }">
                			<a href="<%=path %>/articles/1/1">最新的</a>
                			<a href="<%=path %>/articles/2/1">热门的</a>
                			<a class="current" href="<%=path %>/articles/3/1">未被发现的</a>
                		</c:when>
                		<c:otherwise>
                			<a class="current" href="<%=path %>/articles/1/1">最新的</a>
                			<a href="<%=path %>/articles/2/1">热门的</a>
                			<a href="<%=path %>/articles/3/1">未被发现的</a>
                		</c:otherwise>
                	</c:choose>
                </nav>
            </div>

            <div id="content">
                <c:choose>
                	<c:when test="${articles.size() > 0 }">
		                <c:forEach items="${articles }" var="article">
			                <article class="post">
			                    <div class="status">
			                        <span class="answer answered-accepted" title="${article.answer_number}个答案">${article.answer_number}</span>
			                        <span class="vote" title="${article.useful + article.useless }个投票">${article.useful + article.useless }</span>
			                    </div>
			                    <div class="p-summary">
			                        <a class="author" data-toggle="tooltip" data-placement="bottom" rel="tooltip" href="<%=path %>/user/${article.user.account}" title="${article.user.account}">
			                            <img class="avatar-40" src="${pageContext.request.contextPath}/photo/${article.user.account}.jpg" alt="${article.user.account}" onerror="showErrImg(this);"/>
			                        </a>
			                        <h2>
			                            <a href="<%=path %>/article/${article.id}" title="${article.title }">${article.title }</a>
			                        </h2>
			                        <div class="meta">
			                            <span class="views">
			                                <i class="i-view"></i> ${article.visitor_volume} 次浏览</span>&nbsp;
			                                <ul class="meta-tags">
			                                    <li><i class="i-tag"></i></li>
			                                    <c:choose>
				                                    <c:when test="${article.articleSubjects.size() > 0 }">
			                							<c:forEach items="${article.articleSubjects }" var="articleSubject">
				                                    		<li><a href="#"></a>${articleSubject.subject.name },</li>
				                                    	</c:forEach>
				                                    </c:when>
			                                    </c:choose>
			                                </ul>&nbsp;
			                                <span class="datetime"><i class="i-time"></i> ${article.post_date } </span>
			                        </div>
			                        <%--
			                        <div class="p-excerpt excerpt-bg">
			                            <a class="author" data-toggle="tooltip" data-placement="bottom" rel="tooltip" title="ibusybox" href="<%=path %>/u/ibusybox" data-tip="n:ibusybox <cite>&bull;</cite> <strong>54</strong>">
			                                <img class="avatar-32" src="http://sfault-avatar.b0.upaiyun.com/401/377/4013779080-1030000000643470_small" alt="ibusybox" />
			                            </a>
			                            <p><a href="<%=path %>/q/1010000000644839/a-1020000000646091" class="readmore">阅读全部</a></p>
			                        </div>
			                         --%>
			                    </div>
			                </article>
		                </c:forEach>
		                ${pagination.getTurnPage() }
                	</c:when>
                	<c:otherwise>
                		<h2>没有分享话题。</h2>
                	</c:otherwise>
                </c:choose>
            </div>
        </div>

        <div id="secondary" class="col-md-3 hidden-xs hidden-sm">
            <aside id="profile-tab">
                <nav class="navlist-group navlist-group-right">
<%--                    <a class="navlist-group-item" href="<%=path %>/bookmarked">我收藏的内容</a>--%>
<%--                    <a class="navlist-group-item" href="<%=path %>/users/following">我关注的人</a>--%>
<%--                    <a class="navlist-group-item" href="<%=path %>/user/invitation">邀请朋友加入</a>--%>
                    <a class="navlist-group-item" href="#">我收藏的内容</a>
                    <a class="navlist-group-item" href="#">我关注的人</a>
                    <a class="navlist-group-item" href="#">邀请朋友加入</a>
                </nav>
            </aside>

            <aside class="box follow-tags">
<%--                <a href="<%=path %>/tags" class="i-more right" data-toggle="tooltip" data-placement="bottom" rel="tooltip" title="更多">更多</a>--%>
                <h3>关注标签</h3>
                <ul class="timestream">
                    <li class="nothing">还没有关注任何标签哦<br />关注了标签相关问题就会高亮显示</li>
                </ul>
            </aside>
<%--                            
            <div style="margin-top: 20px" class="ad-imgs">
                <div class="ai-item" data-adn='ad-biggod'>
                    <a href="<%=path %>/biggod" target="_blank" style="display: block; width: 220px;">
                        <img src="http://s.www.itganhuo.cn/img/3rd/orz.png?14.3.24.12" alt="拜见各路大神" title="拜见各路大神" />
                    </a>
                    <a class="close" aria-hidden="true">&times;</a>
                </div>
                <div class="ai-item" data-adn='ad-hr'>
                    <a href="<%=path %>/hiring" target="_blank" style="display: block; width: 220px;">
                        <img src="http://s.www.itganhuo.cn/img/3rd/hire-engineer.png?14.3.24.12" alt="招聘PHP/前端/移动端工程师" title="招聘PHP/前端/移动端工程师" />
                    </a>
                    <a class="close" aria-hidden="true">&times;</a>
                </div>
                <div class="ai-item" data-adn='ad-apply'>
                    <a href="<%=path %>/article/apply" target="_blank" style="display: block;"><img src="http://blog.www.itganhuo.cn/usr/uploads/2013/09/3710900358.png" alt="文章：申请内测" /></a>
                    <a class="close" aria-hidden="true">&times;</a>
                </div>
            </div>
  --%>                   
            <aside class="box">
<%--                <a href="<%=path %>/user/timeline" class="i-more right" data-toggle="tooltip" data-placement="bottom" rel="tooltip" title="更多">更多</a>--%>
                <h3>新鲜事</h3>
                <ul class="timestream show-pop-tag">
                    <li class="nothing">暂时没有新鲜事哦<br />关注了别人就会有新鲜事</li>
                </ul>
            </aside>
        </div>
    </div>
</div>

<%@ include file="common/f_footer.jsp" %>

<a id="backtop" class="mobi-hide hidden" href="#body">回顶部</a>
</body>
<script type="text/javascript">
function showErrImg(obj) {
    var errorimg = "<%=path%>/imgs/default_image.jpg";
    obj.src = errorimg;
}
$(document).ready(function() {
    $('html').click(function() {
        $(".has-dropdown, #site-nav-btn").removeClass('active');
        $('.site-nav').addClass('hidden-xs');
    });
    $(document).scroll(function() {
        if ($(this).scrollTop() > 720) {
            $('#backtop').removeClass('hidden');
        } else {
            $('#backtop').addClass('hidden');
        }
    });
});
</script>
<script>
	window._bd_share_config = {
		common : {
			bdText : 'IT干货技术分享网',	
			bdDesc : '我发现了一个值得收藏的网站，它们是一家专注提供IT工作经验分享社区的网站',	
			bdUrl : 'http://www.itganhuo.cn', 	
			bdPic : 'http://www.itganhuo.cn/imgs/default_image.jpg'
		},
		share : [{
			"bdSize" : 16
		}],
		slide : [{	   
			bdImg : 0,
			bdPos : "right",
			bdTop : 100
		}],
		image : [{
			viewType : 'list',
			viewPos : 'top',
			viewColor : 'black',
			viewSize : '16',
			viewList : ['qzone','tsina','huaban','tqq','renren']
		}],
		selectShare : [{
			"bdselectMiniList" : ['qzone','tqq','kaixin001','bdxc','tqf']
		}]
	}
	with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='+~(-new Date()/36e5)];
</script>
</html>