<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IT干货技术分享网-后台管理中心</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/backmain.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui/jquery.easyui.min.js"></script>
<style type="text/css">
table {
	border-left: 1px rgb(233,233,233) solid;
	border-top: 1px rgb(233,233,233) solid;
	margin: 5px 4px 10px 4px;
	border-collapse: collapse;
}
td{
	border-right: 1px rgb(233,233,233) solid;
	border-bottom: 1px rgb(233,233,233) solid;
	padding-left: 5px;
}
</style>
</head>
<body>
	<div id="index_layout">
		<div data-options="region:'north',border:false" style="height: 70px; overflow: hidden;background-color: rgb(106, 136, 189);">
			<h1 style="color: white; margin-left: 10px;">IT干货技术分享网-后台管理中心</h1>
		</div>
		<div data-options="region:'west',href:'<%=request.getContextPath()%>/admin/west'" title="模块导航" style="width: 200px; overflow: hidden;"></div>
		<div data-options="region:'center',href:'<%=request.getContextPath()%>/admin/center'" title="admin，欢迎使用" style="overflow: hidden;"></div>
		<div data-options="region:'south',border:false" style="height: 30px; overflow: hidden;">2014 @ IT干货技术分享网版权所有</div>
	</div>

	<div id="index_tabsMenu" style="width: 120px; display: none;">
		<div title="refresh" data-options="iconCls:'transmit'">刷新</div>
		<div class="menu-sep"></div>
		<div title="close" data-options="iconCls:'delete'">关闭</div>
		<div title="closeOther" data-options="iconCls:'delete'">关闭其他</div>
		<div title="closeAll" data-options="iconCls:'delete'">关闭所有</div>
	</div>
</body>
</html>
<script type="text/javascript">
	var index_tabsMenu;
	var index_layout;
	$(function() {
		index_layout = $('#index_layout').layout({
			fit : true
		});

		index_tabsMenu = $('#index_tabsMenu').menu({
			border : false,
			onClick : function(item) {
				var curTabTitle = $(this).data('tabTitle');
				var type = $(item.target).attr('title');

				if (type === 'refresh') {
					index_tabs.tabs('getTab', curTabTitle).panel('refresh');
					return;
				}

				if (type === 'close') {
					var t = index_tabs.tabs('getTab', curTabTitle);
					if (t.panel('options').closable) {
						index_tabs.tabs('close', curTabTitle);
					}
					return;
				}

				var allTabs = index_tabs.tabs('tabs');
				var closeTabsTitle = [];

				$.each(allTabs, function() {
					var opt = $(this).panel('options');
					if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
						closeTabsTitle.push(opt.title);
					} else if (opt.closable && type === 'closeAll') {
						closeTabsTitle.push(opt.title);
					}
				});

				for (var i = 0; i < closeTabsTitle.length; i++) {
					index_tabs.tabs('close', closeTabsTitle[i]);
				}
			}
		});
	});
</script>