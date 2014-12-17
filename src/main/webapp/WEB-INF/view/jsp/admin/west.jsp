<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul id="tab"></ul>
<script type="text/javascript">
	$('#tab').tree({
		url : '<%=request.getContextPath()%>/admin/getMenuTreeJson',
		lines : true,
		onClick : function(node) {
			if (node.attributes != null && "" != node.attributes) {
				open(node);
			} else {
				if (node.pid != null && "0" != node.pid) {
					alert("菜单没有指定链接");
				}
			}
		}
	});
</script>