<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>IT干货技术分享网-后台管理中心</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/backmain.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyuiUtil.js"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',border:false" style="height: 40px; overflow: hidden;background-color: rgb(106, 136, 189);">
<!-- 			<form id="search_form">
				<table class="datagrid-toolbar" style="width: 100%;height: 100%">
					<tr>
						<td align="right">名称</td>
						<td><input type="text" name="name">
						</td>
						<td align="right">群组</td>
						<td><input type="text" name="group">
						</td>
						<td><a class="easyui-linkbutton" onclick="dataSearch();">查询</a> <a class="easyui-linkbutton" onclick="cleanSearch();">清空</a></td>
					</tr>
				</table>
			</form>
 -->		</div>
		<div data-options="region:'center',border:false" title="查询" style="overflow: hidden;">
			<table id=list_datagrid></table>
		</div>
		<div id="add_dialog">
			<form id="add_form">
				<table style="width: 100%">
					<tr>
						<td>权限名称：</td>
						<td><input type="text" name="permission" class="easyui-validatebox" data-options="required:true">
						</td>
						<td>权限描述：</td>
						<td><input type="text" name="description" class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<td>是否有效：</td>
						<td><input type="radio" name="available" value="0">无效</td>
						<td><input type="radio" name="available" value="1">有效</td>
						<td><input type="text" name="id" value="" style="display: none"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	var add_dialog;
	$(function(){
		$('#list_datagrid').datagrid({
			loadMsg : '数据加载中...',
			url : '<%=path%>/admin/getPermissionsListJson',
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 100
			}, {
				field : 'permission',
				title : '权限名称',
				width : 100,
				sortable : true
			}, {
				field : 'description',
				title : '权限描述',
				width : 100,
				align : 'right'
			}, {
				field : 'available',
				title : '是否有效',
				width : 100,
				align : 'right'
			}
			 ] ],
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
			pageSize : 10,
			pageList : [ 10, 15, 20, 30 ],
			fit : true,
			border : false,
			fitColumns : true,
			idField : 'id',
			rownumbers : true,
			singleSelect : true
		});

	});
	
		 function update(){
            var row = $('#list_datagrid').datagrid('getSelected');
            if (row){
            	$("#add_form").form("load", row);
            	$("#add_dialog").dialog({
            		title : '修改权限',
					width : 460,
					height : 200,
					closed : false,
					cache : false,
					modal : true,
					buttons : [ {
					text : '提交',
					handler : function() {
						if ($('#add_form').form('validate')) {
							$.ajax({
								type : "POST",
								url : "${pageContext.request.contextPath}/admin/updatePermissions",
								data : $("#add_form").serialize(),
								success : function(data) {
									if (data.status == '1') {
										$('#add_dialog').dialog('close');
										list_datagrid.datagrid('load');
										$('#add_form').find('input').val('');
									}
									$.messager.show({
										title:'系统消息',
										msg:data.msg,
										timeout:5000,
										showType:'slide'
									});
								}
							});
						}
					}
				}, {
					text : '取消',
					handler : function() {
						$('#add_dialog').dialog('close');
					}
				} ]
	            	});
            }
        }

	function add() {
		$('#add_dialog').dialog({
			title : '新增权限',
			width : 460,
			height : 200,
			closed : false,
			cache : false,
			modal : true,
			buttons : [ {
				text : '提交',
				handler : function() {
					if ($('#add_form').form('validate')) {
						 $.ajax({
							type : "POST",
							url : "${pageContext.request.contextPath}/admin/addPermisstions",
							data : serializeObject($("#add_form")),
							success : function(data) {
								if (data.status == '1') {
									$('#add_dialog').dialog('close');
									list_datagrid.datagrid('load');
								}
								$.messager.show({
									title:'系统消息',
									msg:data.msg,
									timeout:5000,
									showType:'slide'
								});
							}
						}); 
					}
				}
			}, {
				text : '取消',
				handler : function() {
					$('#add_dialog').dialog('close');
				}
			} ]
		});
	}
	
	function del() {
		var rows = $('#list_datagrid').datagrid('getSelected');
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
					url : "<%=path%>/admin/delPermissionss",
					data : "id=" + rows.id,
					success : function(data) {
						$.messager.show({
							title:'系统消息',
							msg:data.msg,
							timeout:5000,
							showType:'slide'
						});
					}
				});
			}
		});
	}

	

	function dataSearch() {
		$('#list_datagrid').datagrid('load', serializeObject($('#search_form')));
	}

	function cleanSearch() {
		$('#list_datagrid').datagrid('load', {});
		$('#search_form').find('input').val('');
	}
	
	
</script>