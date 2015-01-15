<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	//打开一个新标签嵌入一个新页面
	function open(node) {
		if ($('#index_tabs').tabs('exists', node.text)) {
			$('#index_tabs').tabs('select', node.text);
		} else {
			$('#index_tabs').tabs('add', {
				title : node.text,
				content : '<iframe src="' + node.attributes + '" frameborder="0" style="border: 0; width: 100%; height: 98%;"></iframe>',
				closable : true
			});
		}
	}
	var index_tabs;
	$(function() {
		index_tabs = $('#index_tabs').tabs({
			fit : true,
			border : false,
			onContextMenu : function(e, title) {
				e.preventDefault();
				index_tabsMenu.menu('show', {
					left : e.pageX,
					top : e.pageY
				}).data('tabTitle', title);
			},
			tools : [ {
				iconCls : 'icon-reload',
				handler : function() {
					var href = index_tabs.tabs('getSelected').panel('options').href;
					if (href) {/*说明tab是以href方式引入的目标页面*/
						var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
						index_tabs.tabs('getTab', index).panel('refresh');
					} else {/*说明tab是以content方式引入的目标页面*/
						var panel = index_tabs.tabs('getSelected').panel('panel');
						var frame = panel.find('iframe');
						try {
							if (frame.length > 0) {
								for (var i = 0; i < frame.length; i++) {
									frame[i].contentWindow.document.write('');
									frame[i].contentWindow.close();
									frame[i].src = frame[i].src;
								}
								if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
									try {
										CollectGarbage();
									} catch (e) {
									}
								}
							}
						} catch (e) {
						}
					}
				}
			}, {
				iconCls : 'icon-remove',
				handler : function() {
					var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
					var tab = index_tabs.tabs('getTab', index);
					if (tab.panel('options').closable) {
						index_tabs.tabs('close', index);
					} else {
						$.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭！', 'error');
					}
				}
			} ]
		});
	});
</script>
<div id="index_tabs" class="easyui-tabs" data-options="border:false,fit:true" style="overflow: hidden;">
	<div title="信息摘要" style="overflow: hidden;">
		<table style="width: 99%; height: 120px;">
			<tr>
				<td colspan="4" style="background-color: rgb(237,237,237);">系统信息</td>
			</tr>
			<tr>
				<td align="center">当前用户</td>
				<td>深圳-小兴</td>
				<td align="center">当前版本</td>
				<td>V20140808</td>
			</tr>
			<tr>
				<td align="center">操作系统</td>
				<td>server2008</td>
				<td align="center">WEB容器</td>
				<td>Apache Tomcat 7.X</td>
			</tr>
			<tr>
				<td align="center">内存使用</td>
				<td>25000M/5000M</td>
				<td align="center">服务器时间</td>
				<td>2014年8月10日 周日</td>
			</tr>
		</table>
		<table style="width: 99%; height: 120px;">
			<tr>
				<td style="background-color: rgb(237,237,237);">最新动态</td>
			</tr>
			<tr>
				<td>平台新注册会员：shengzi168</td>
			</tr>
			<tr>
				<td>深圳-小兴发布服务器定点维护通知！</td>
			</tr>
			<tr>
				<td>会员：shengzi168发布新分享话题</td>
			</tr>
		</table>
	</div>
</div>
