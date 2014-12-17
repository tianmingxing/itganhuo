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
			<form id="search_form">
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
		</div>
		<div data-options="region:'center',border:false" title="查询" style="overflow: hidden;">
			<table id=list_datagrid></table>
		</div>
		<div id="add_dialog">
			<form id="add_form">
				<table style="width: 100%">
					<tr>
						<td>名称：</td>
						<td><input type="text" name="name" class="easyui-validatebox" data-options="required:true">
						</td>
						<td>描述：</td>
						<td><input type="text" name="description" class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<td>群组：</td>
						<td><input type="text" name="group" ></td>
						<td>排序：</td>
						<td><input type="text" name="sort" ></td>
						<td><input type="text" name="id" style="display: none"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
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
			url : '<%=path%>/admin/getDictionariesListJson',
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 100
			}, {
				field : 'name',
				title : '名称',
				width : 100,
				sortable : true
			}, {
				field : 'description',
				title : '描述',
				width : 100,
				align : 'right'
			}, {
				field : 'group',
				title : '群组',
				width : 100,
				align : 'right'
			}, {
				field : 'order',
				title : '排序',
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
            		title : '修改数据字典',
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
								url : "${pageContext.request.contextPath}/admin/updateDictionaries",
								data : {
								name : $("#add_form[name='name']").val(),
								description : $("#add_form[name='description']").val(),
								group : $("#add_form[name='group']").val(),
								sort : $("#add_form[name='sort']").val(),
								id : $("#add_form[name='id']").val(),
								},
								success : function(data) {
									var obj = $.parseJSON(data);
									if (obj.status == '1') {
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
			title : '新增数据字典',
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
							url : "${pageContext.request.contextPath}/admin/addDictionaries",
							data : {
								name : $("#add_form[name='name']").val(),
								description : $("#add_form[name='description']").val(),
								group : $("#add_form[name='group']").val(),
								sort : $("#add_form[name='sort']").val()
							},
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
					url : "<%=path%>/admin/delDictionaries",
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