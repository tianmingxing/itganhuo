<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path %>/css/style.css" />
<link rel="stylesheet" href="<%=path %>/css/responsive.css" />
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container wrap">
 <h1>IT 干货</h1>
<h2>你可以复制链接给好友</h2>
<div>
	链接地址<input type="text" value="<%=path %>/articles">

</div>
<h2>你也可以提供好友邮箱地址：</h2>
                            <input id="email" type="text" value="" name="email"><input id="buton1" type="button" class="btn btn-primary action" value="发送" onclick="PushEmail();">
                            
<h2>你也可以分享给好友</h2>
<p class="preview_note">预览效果：</p>
                            <div class="preview_container">
                                <div class="preview_share_button" id="btn_demo">
                                    <!--iframe内容或预览元素-->
                                </div>
                            </div>

</div>

<form action="<%=path %>/register" name="form1"></form>
</body>
<script type="text/javascript">
function PushEmail(){
	var a=document.getElementById('email').value;
	alert(a);
	$("#buton1").attr("class","btn");
	
	
	 if(a=='') {
		  alert('必须输入email！');
		  document.getElementById('email').focus();
		  return false;
		 } 
	 	$("#form1").submit();
}



</script>
</html>