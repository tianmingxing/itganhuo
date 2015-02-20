<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link href="<%=path %>/static/css/min.css" rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet" href="<%=path %>/static/js/plugin/ueditor/themes/default/css/ueditor.min.css" type="text/css">
<link rel="stylesheet" href="<%=path %>/static/js/plugin/autocomplete/jquery-ui.css" type="text/css">
<script type="text/javascript" src="<%=path %>/static/js/plugin/ueditor/third-party/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/analytics.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/plugin/ueditor/ueditor.all.min.js"></script>
<title>发帖-用户中心-IT干货技术分享网</title>
</head>
<body>
<%@ include file="../common/header.jsp" %>

<div id='main'>
<%--
	<div id='sidebar'>
	
		<div class="panel">
			<div class="header">
			  <span class="col_fade">Markdown 语法参考</span>
			</div>
			<div class="inner">
			  <ol>
				<li><tt>### 单行的标题</tt></li>
				<li><tt>**粗体**</tt></li>
				<li><tt>`console.log('行内代码')`</tt></li>
				<li><tt>```js\n code \n```</tt> 标记代码块</li>
				<li><tt>[内容](链接)</tt></li>
				<li><tt>![文字说明](图片链接)</tt></li>
			  </ol>
			  <span><a href="http://www.ituring.com.cn/article/775" target="_blank">Markdown 文档</a></span>
			</div>
		</div>
		
		<div class="panel">
			<div class="header">
			  <span class="col_fade">分享发布指南</span>
			</div>
			<div class="inner">
			  <ol>
				<li>尽量把话题要点浓缩到标题里</li>
				<li>给话题选择合适的标签能增加浏览</li>
			  </ol>
			</div>
		</div>
   </div> --%>
	<div id="content" style="margin: 0;">
		<div class="panel">
			<div class="header">
				<ol class="breadcrumb">
					<li><a href="/">主页</a><span class="divider">/</span></li>
					<li class="active">发布话题</li>
				</ol>
			</div>
			<div class="inner post">
				<form id="create_topic_form" >
					<fieldset>
						<input type="text" name="title" maxlength="50" style="width: 98%;">
						<div class="markdown_in_editor">
							<script id="container" name="content" type="text/plain"></script>
							<div style="margin-top: 5px;">
								<input type="text" id="project">&nbsp;<input type="button" onclick="affix();" value="贴上" >
								<input type="hidden" id="j_subject" name="subject" value="">
								<div id="j_label"></div>
							</div>
							<div class="editor_buttons">
								<button id="submit_btn" type="button" class="btn btn-primary">提交</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</div>
<div id='backtotop'>回到顶部</div>
<div id="sidebar-mask"></div>

<%@ include file="../common/footer.jsp" %>

</body>
<script type="text/javascript" src="<%=path %>/static/js/plugin/autocomplete/jquery-ui.js"></script>
<script type="text/javascript">
	//
	jQuery(function() {
		var cache = {};
		jQuery("#project").autocomplete({
			minLength : 2,
			source : function(request, response) {
				var term = request.term;
				if (term in cache) {
					response(cache[term]);
					return;
				}
				var str = "term=" + encodeURI(encodeURI(term));
				jQuery.getJSON("<%=path %>/user/findLabel", str, function(data, status, xhr) {
					cache[term] = data;
					response(data);
				});
			}
		});
	});

	//
	function affix() {
		var project = jQuery("#project").val();
		if (project == null || project == '') {
			alert('老兄，你这是在逗我吗？');
		} else {
			var subject = jQuery('#j_subject').val(), str = '';
			var ary = subject.split(',');
			for (var i = 0; i < ary.length; i++) {
				if (project == ary[i]) {
					alert('你已经加了一个相同的标签了');
					return;
				}
			}
			if (subject == null || subject == '') {
				str = project;
			} else {
				str = subject + "," + project;
			}
			jQuery('#j_subject').val(str);
			jQuery('#j_label').append('<span class="label">' + project + '</span>&nbsp;');
			jQuery("#project").val('');
		}
	}

	//
	var ue = UE.getEditor('container');
	
	function checkForm() {
		jQuery('#submit_btn').attr('disabled', true);
		jQuery('#submit_btn').text('提交中...');
		var title = jQuery('#create_topic_form [name=title]').val(), j_subject = jQuery('#j_subject').val();
		var context = ue.getContentTxt();
		if (title == null || title == '') {
			alert('请填写标题');
			jQuery('#create_topic_form [name=title]').focus();
			jQuery('#submit_btn').attr('disabled', false);
			jQuery('#submit_btn').text('提交');
			return false;
		}
		if (context == null || context == '') {
			alert('请填写有实际意义的内容');
			ue.focus();
			jQuery('#submit_btn').attr('disabled', false);
			jQuery('#submit_btn').text('提交');
			return false;
		}
		if (j_subject == null || j_subject == '') {
			alert('请填写文章对应的标签');
			jQuery('#project').focus();
			jQuery('#submit_btn').attr('disabled', false);
			jQuery('#submit_btn').text('提交');
			return false;
		} else if (j_subject.indexof('，') != -1) {
			alert('多个标签之间请用英文逗号分隔');
			jQuery('#project').focus();
			jQuery('#submit_btn').attr('disabled', false);
			jQuery('#submit_btn').text('提交');
			return false;
		}
		return true;
	}
	
	function submitForm() {
		if (!submit_btn()) {
			return;
		}
		jQuery.post('<%=path %>/user/share', jQuery('#create_topic_form').serialize(), function(data){
			if (data.status == '0000') {
				window.location.href = '<%=path %>/user/' + data.appendInfo;
			} else {
				alert(data.message);
			}
		});
	}
</script>
</html>