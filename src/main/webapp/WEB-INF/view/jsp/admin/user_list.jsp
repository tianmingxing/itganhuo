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
						<td align="right">帐号</td>
						<td><input type="text" name="account">
						</td>
						<td align="right">用户姓名</td>
						<td><input type="text" name="name">
						</td>
						<td align="right">性别</td>
						<td><input type="text" name="sex">
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
						<td>父编号：</td>
						<td><input type="text" name="pid" value="0" class="easyui-validatebox" data-options="required:true">
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
						<td><input type="text" name="iconCls" value=""></td>
						<td>菜单链接：</td>
						<td><input type="text" name="attributes" value=""></td>
					</tr>
					<tr>
						<td>排序：</td>
						<td><input type="text" name="sort" value=""></td>
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
			url : '<%=path%>/admin/getUserListJson',
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 100
			}, {
				field : 'account',
				title : '帐号',
				width : 100,
				sortable : true
			}, {
				field : 'isLock',
				title : '是否锁定',
				width : 100,
				align : 'right'
			}, {
				field : 'name',
				title : '用户姓名',
				width : 100,
				align : 'right'
			}, {
				field : 'sex',
				title : '性别',
				width : 100,
				align : 'right'
			}, {
				field : 'email',
				title : '邮箱',
				width : 100,
				align : 'right'
			}, {
				field : 'qq',
				title : 'QQ',
				width : 100,
				align : 'right'
			}, {
				field : 'phone',
				title : '手机',
				width : 100,
				align : 'right'
			},{
				field : 'postDate',
				title : '注册日期',
				width : 100,
				align : 'right'
			}, {
				field : 'type',
				title : '账号类型',
				width : 100,
				align : 'right'
			}, {
				field : 'lastLoginIp',
				title : '最后登录ip',
				width : 100,
				align : 'right'
			},{
				field : 'lastLoginDate',
				title : '最后登录日期',
				width : 100,
				align : 'right'
			},{
				field : 'img',
				title : '头像',
				width : 100,
				align : 'right'
			} ] ],
			/* toolbar : [ {
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
					alert('edit')
				}
			} ],
 */			pagination : true,
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

	<%-- function add() {
		$('#add_dialog').dialog({
			title : '新增菜单',
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
							url : "<%=path%>/admin/addMenu",
							data : $("#add_form").serialize(),
							success : function(data) {
								if (data.status == '1') {
									$('#add_dialog').dialog('close');
								}
								$.messager.show({
									title:'系统消息',
									msg:data.msg,
									timeout:5000,
									showType:'slide'
								});
								$('#list_datagrid').datagrid('load');
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
		console.info(rows);
		if (rows.length > 1) {
			alert('同时只能删除一条记录');
		}
		$.ajax({
			type : "POST",
			url : "<%=path%>/admin/delMenu",
			data : "id=" + rows.id,
			success : function(data) {
				$.messager.show({
					title:'系统消息',
					msg:data.msg,
					timeout:5000,
					showType:'slide'
				});
				$('#list_datagrid').datagrid('load');
			}
		});
	} --%>

	function dataSearch() {
		$('#list_datagrid').datagrid('load', serializeObject($('#search_form')));
	}

	function cleanSearch() {
		$('#list_datagrid').datagrid('load', {});
		$('#search_form').find('input').val('');
	}
	
	
</script>