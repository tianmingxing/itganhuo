<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>头像修改</title>
   	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta name="description" content="IT干货技术分享网-会员信息修改">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="author" content="IT干货技术分享网-会员信息修改">
	<meta content="_csrf" name="csrf-param">
	<meta content="gqpb25GFirs5rHHq/KylANDoUyNTk46Ey0Dng=" name="csrf-token">
	<link rel="icon" href="#">
	<link href="<%=request.getContextPath()%>/css/min.css" rel="stylesheet" type="text/css" media="all">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/analytics.js"></script>
	</head>
  <%@ include file="../common/header.jsp" %>
  <body>
   <div id="altContent">
    <style type="text/css">
	html, body { height:100%; overflow:hidden; }
	body { margin:0; }
	</style>
	<script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
	<script type="text/javascript">
	window.onload=function(){
 		 xiuxiu.embedSWF("altContent",5,"100%","100%");
  		/*第1个参数是加载编辑器div容器，第2个参数是编辑器类型，第3个参数是div容器宽，第4个参数是div容器高*/
  		xiuxiu.setUploadURL("http://www.itganhuo.cn/user/uploadImg");//修改为上传接收图片程序地址
  		/*回调函数*/
		xiuxiu.onUploadResponse = function (data)
		{
				var strs = new Array(); 
				strs = data.split(",");
				if(strs[0] == 'success'){
					alert("更新头像成功,点击返回用户中心");
					window.location.href = "http://www.itganhuo.cn/user/" + strs[1]; 				
				} else {
					alert("更新头像失败，请再试一下吧");
					return;
				}
				
		}
	}
	</script>
   </div>
   <%@ include file="../common/footer.jsp" %>
  </body>
</html>
