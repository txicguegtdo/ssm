<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>登录</title>
<link rel="stylesheet" href="<%=path%>/css/style.css">
<script src="<%=path%>/js/jquery-1.11.1.js"></script>
<style type="text/css">
body,.login-submit,.login-submit:before,.login-submit:after {
	background: #373737 url("<%=path%>/img/bg.png") 0 0 repeat;
}

.login-button:after {
	content: '';
	position: absolute;
	top: 15px;
	left: 12px;
	width: 25px;
	height: 19px;
	background: url("<%=path%>/img/arrow.png") 0 0 no-repeat;
}
</style>
</head>
<body>
	<div id="login" class="login" class="login" >
		<p>
			<label for="login">用户名</label> <input type="text" name="name"
				id="name" value="">
		</p>
		<p>
			<label for="password">密 &nbsp;&nbsp;码</label> <input type="password"
				name="password" id="password" value="">
		</p>

		<p class="login-submit">
			<button onclick="toLogin()" class="login-button">登&nbsp;&nbsp;录</button>
		</p>

		<p class="forgot-password">
			<a onclick="register()" >注&nbsp;&nbsp;册</a>&nbsp;&nbsp;<a href="#">忘记密码</a>
		</p>
	</div>
	<div  id="register" class="login" style="display:none">
		<p>
			<label for="login">用户名</label> <input type="text" name="name"
				id="name1" value="">
		</p>
		<p>
			<label for="password">密 &nbsp;&nbsp;码</label> <input type="password"
				name="password" id="password1" value="">
		</p>

		<p class="login-submit">
			<button onclick="toRegister()" class="login-button">注&nbsp;&nbsp;册</button>
		</p>
		<p class="forgot-password" >
			<a onclick="back()" >返&nbsp;&nbsp;回</a> <label id="msg" style="color:red" ></label>
		</p>
	</div>
	
</body>
<script type="text/javascript">
	function register(){
		$("#login").hide();
		$("#register").show();
		$("#name1").val("");
		$("#password1").val("");
		$("#msg").text("");
		$(document).attr("title","注册");
	}
	function back(){
		$("#login").show();
		$("#register").hide();
		$(document).attr("title","登录");
	}
	function toLogin(){
		var name = $("#name").val();
		var password = $("#password").val();
		$.post("<%=basePath%>/user/toLogin",
				{'name':name,'password':password},
				function(data){
					if(data.flag==1){
						location.href="<%=basePath%>"+data.html;
					}else{
						//window.location="<%=basePath%>"+data.html;
					}
				},
				"json"
		);
	}
	function toRegister(){
		var name = $("#name1").val();
		var password = $("#password1").val();
		if(name.length==0 || password.length==0){
			alert("用户名或密码不能为空!!");
			return;
		}
		$.post(
			"<%=basePath%>/user/register",
			{'name':name,'password':password},
			function(data){
				if(data.flag==1){
					back();
					$("#name").val(name);
					$("#password").val(password);
				}else{
					$("#msg").text("注册失败!!");
				}
			},
			"json"
		)
	}
	
</script>
</html>

