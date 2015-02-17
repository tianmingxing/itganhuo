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
<link href="<%=request.getContextPath()%>/static/css/min.css" rel="stylesheet" type="text/css" media="all">
<script type="text/javascript" src="/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/analytics.js"></script>
<title>IT干货</title>
</head>
<body>
<div class='navbar'>
  <div class='navbar-inner'>
    <div class='container'>
		<a class='brand' href='/'><img src="imgs/logo.png" title="IT干货" width="128px" height="24px"/></a>
		<form id='search_form' class='navbar-search' action="/search">
			<input type='text' id='q' name='q' class='search-query span3' value=''/>
		</form>
		<ul class='nav pull-right'>
			<li><a href='#'>首页</a></li>
			<li><a href='signin.html'>注册</a></li>
			<li><a href='signin.html'>登录</a></li>
		</ul>
		<a class="btn btn-navbar" id="responsive-sidebar-trigger"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a>
	</div>
  </div>
</div>
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
					<li class="active">关于</li>
				</ul>
			</div>
			<div class="inner topic">
				<h3>FAQ</h3>
				<hr>
				<div class="topic_content">
					<p>我们是一群有为的2B青年</p>
					<p>特别感谢：张三、李四、王五</p>
				</div>
		  </div>
		</div>
	</div>
</div>
<div id='backtotop'>回到顶部</div>
<div id='footer'>
  <div id='footer_main'>
    <div class="links">
      <a class='dark' href='faq.html'>关于</a> | 
      <a class='dark' href='http://sishuok.com/' target="_blank">私塾在线</a>
    </div>
    <div class='col_fade'>
      <p>&copy; 2014 - 2014 </p>
      <p>网站版本: 0.1.0 </p>
      <p>本站服务器由 <a href="#">胜子</a> 赞助</p>
    </div>
  </div>
</div>
<div id="sidebar-mask"></div>
</body>
</html>
<script type="text/javascript">
$(document).ready(function () {
  var $nav = $('.pagination');
  var current_page = $nav.attr('current_page'); 
  if (current_page) {
    $nav.find('li').each(function(){
      var $li = $(this);
      var $a = $li.find('a');
      if($a.html() == current_page){
        $li.addClass('active');
        $a.removeAttr('href');
      }
    });
  }
});
</script>