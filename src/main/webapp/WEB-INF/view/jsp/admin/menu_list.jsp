<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>IT干货技术分享网-后台管理中心</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/backmain.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path %>/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/easyuiUtil.js"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',border:false" style="height: 40px; overflow: hidden;background-color: rgb(106, 136, 189);">
			<form id="search_form" action="">
				<table class="datagrid-toolbar" style="width: 100%;height: 100%">
					<tr>
						<td align="right">编号</td>
						<td><input type="text" name="id">
						</td>
						<td align="right">菜单名称</td>
						<td><input type="text" name="text">
						</td>
						<td><a class="easyui-linkbutton" onclick="dataSearch();">查询</a> <a class="easyui-linkbutton" onclick="cleanSearch();">清空</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false" title="查询" style="overflow: hidden;">
			<table id=list_datagrid></table>
		</div>
		<div id="add_div">
			<form id="add_form" action="" method="post">
				<table style="width: 100%">
					<tr>
						<td>父编号：</td>
						<td><input type="text" id="j_pid" name="pid" value="0" >
						</td>
						<td>菜单名称：</td>
						<td><input type="text" name="text" value="" class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<td>节点状态：</td>
						<td><label><input type="radio" name="state" value="open" checked="checked">展开</label> <label><input type="radio" name="state" value="closed">关闭</label>
						</td>
						<td>默认选中：</td>
						<td><label><input type="radio" name="checked" value="true" checked="checked">选中</label> <label><input type="radio" name="checked" value="false">不选中</label>
						</td>
					</tr>
					<tr>
						<td>节点图标：</td>
						<td><input type="text" name="iconCls" value="" ></td>
						<td>菜单链接：</td>
						<td><input type="text" name="attributes" value="" class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<td>排序：</td>
						<td><input type="text" name="sort" value="0" class="easyui-validatebox" data-options="required:true,validType:'numberRegex'"></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="update_div">
			<form id="update_form" action="" method="post">
				<table style="width: 100%">
					<tr>
						<td>父编号：</td>
						<td><input type="text" id="j_pid2" name="pid" value="0" >
						</td>
						<td>菜单名称：</td>
						<td><input type="text" name="text" value="" class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<td>节点状态：</td>
						<td><label><input type="radio" name="state" value="open" checked="checked">展开</label> <label><input type="radio" name="state" value="closed">关闭</label>
						</td>
						<td>默认选中：</td>
						<td><label><input type="radio" name="checked" value="true" checked="checked">选中</label> <label><input type="radio" name="checked" value="false">不选中</label>
						</td>
					</tr>
					<tr>
						<td>节点图标：</td>
						<td><input type="text" name="iconCls" value="" ></td>
						<td>菜单链接：</td>
						<td><input type="text" name="attributes" value="" class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<td>排序：</td>
						<td><input type="text" name="sort" value="0" class="easyui-validatebox" data-options="required:true,validType:'numberRegex'"></td>
						<td>编号：</td>
						<td><input type="text" name="id" readonly="readonly"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	var add_dialog, update_dialog, list_datagrid, add_form, update_form;
	$(function(){
		list_datagrid = $('#list_datagrid').datagrid({
			loadMsg : '数据加载中...',
			url : '<%=path %>/admin/getMenuListJson',
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 100
			}, {
				field : 'pid',
				title : '父编号',
				width : 100,
				sortable : true
			}, {
				field : 'text',
				title : '菜单名称',
				width : 100,
				align : 'right'
			}, {
				field : 'state',
				title : '节点状态',
				width : 100,
				align : 'right'
			}, {
				field : 'checked',
				title : '是否选中',
				width : 100,
				align : 'right'
			}, {
				field : 'iconCls',
				title : '图标',
				width : 100,
				align : 'right'
			}, {
				field : 'attributes',
				title : '菜单链接',
				width : 100,
				align : 'right'
			}, {
				field : 'sort',
				title : '排序',
				width : 100,
				align : 'right'
			} ] ],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					del();
				}
			}, '-', {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					update();
				}
			} ],
			pagination : true,
			pagePosition : 'bottom',
			pageSize : 15,
			pageList : [ 10, 15, 20, 30 ],
			fit : true,
			border : false,
			fitColumns : true,
			idField : 'id',
			rownumbers : true,
			singleSelect : true
		});
		
		add_form = $('#add_form').form({
		    url:'<%=path %>/admin/addMenu',
		    success:function(data){
		    	var obj = $.parseJSON(data);
				if (obj.status == '1') {
					add_dialog.dialog('close');
					$.messager.show({
						title:'系统消息',
						msg:obj.msg,
						timeout:5000,
						showType:'slide'
					});
					add_form.form('clear');
					list_datagrid.datagrid('load');
				} else {
					$.messager.alert('警告', obj.msg);
				}
		    }
		});

		update_form = $('#update_form').form({
		    url:'<%=path %>/admin/updateMenu',
		    success:function(data){
		    	var obj = $.parseJSON(data);
				if (obj.status == '1') {
					update_dialog.dialog('close');
					$.messager.show({
						title:'系统消息',
						msg:obj.msg,
						timeout:5000,
						showType:'slide'
					});
					update_form.form('clear');
					list_datagrid.datagrid('load');
				} else {
					$.messager.alert('警告', obj.msg);
				}
		    }
		});
	});
	
	
	
	// 新增数据
	function add() {
		// 加载tree
		$('#j_pid').combotree({
		    url: '<%=path %>/admin/getMenuTreeJson',
		    panelWidth : 200,
		    required: true
		});
		add_dialog = $('#add_div').dialog({
			title : '新增菜单',
			width : 460,
			height : 200,
			closed : false,
			modal : true,
			buttons : [ {
				text : '提交',
				handler : function() {
					add_form.submit();
				}
			}, {
				text : '取消',
				handler : function() {
					add_dialog.dialog('close');
				}
			} ]
		});
	}
	
	// 修改数据
	function update() {
		// 加载tree
		$('#j_pid2').combotree({
		    url: '<%=path %>/admin/getMenuTreeJson',
		    panelWidth : 200,
		    required: true
		});
		var row = $('#list_datagrid').datagrid('getSelected');
		if (row) {
			update_form.form("load", row);
			update_dialog = $('#update_div').dialog({
				title : '修改菜单',
				width : 460,
				height : 200,
				closed : false,
				modal : true,
				buttons : [ {
					text : '提交',
					handler : function() {
						update_form.submit();
					}
				}, {
					text : '取消',
					handler : function() {
						update_dialog.dialog('close');
					}
				} ]
			});
		} else {
			$.messager.alert('警告', '请至少选择一条记录');
		}
	}
	
	// 删除一条记录
	function del() {
		var rows = list_datagrid.datagrid('getSelected');
		if (rows == null) {
			$.messager.alert('警告', '请至少选择一条记录');
			return;
		} else if (rows.length > 1) {
			$.messager.alert('警告', '同时只能删除一条记录');
			return;
		}
		$.messager.confirm('确认对话框', '确认要删除这条记录吗？', function(r) {
			if (r) {
				$.ajax({
					type : "POST",
					url : "<%=path %>/admin/delMenu",
					data : "id=" + rows.id,
					success : function(data) {
						var obj = $.parseJSON(data);
						if (obj.status == '1') {
							$.messager.show({
								title:'系统消息',
								msg:obj.msg,
								timeout:5000,
								showType:'slide'
							});
							list_datagrid.datagrid('load');
						} else {
							$.messager.alert('警告', obj.msg);
						}
					}
				});
			}
		});
	}

	// 搜索
	function dataSearch() {
		list_datagrid.datagrid('load', {
			id : $("#search_form").find('[name=id]').val(),
			text : $("#search_form").find('[name=text]').val()
		});
	}

	//清除搜索条件
	function cleanSearch() {
		list_datagrid.datagrid('load', {});
		$('#search_form').find('input').val('');
	}
	
	
</script>