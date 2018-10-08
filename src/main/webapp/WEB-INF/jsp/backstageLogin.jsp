<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp"%>
<title>秒杀系统-后台登录</title>
<link rel="icon" href="${pageContext.request.contextPath}/image/ico.ico" />
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
body {
	background: white;
	font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei",
		Arial;
	color: #222;
	font-size: 14px;
}

* {
	padding: 0px;
	margin: 0px;
}

a {
	text-decoration: none;
}

/*  */
.top_div {
	background: rgba(29, 161, 241, 0.60);
	width: 100%;
	height: 300px;
}

/* 输入框样式 */
.ipt {
	border: 1px solid #d3d3d3;
	padding: 10px 10px;
	width: 290px;
	border-radius: 4px;
	padding-left: 35px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

/* 鼠标聚焦事件，聚焦在输入框 */
.ipt:focus {
	border-color: #66afe9;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6)
}

/* 登录名输入框图片加载样式 */
.u_logo {
	background: url("${pageContext.request.contextPath}/image/username.png")
		no-repeat;
	padding: 10px 10px;
	position: absolute;
	top: 28px;
	left: 65px;
}

/* 密码输入框图片加载样式 */
.p_logo {
	background: url("${pageContext.request.contextPath}/image/password.png")
		no-repeat;
	padding: 10px 10px;
	position: absolute;
	top: 12px;
	left: 65px;
}

/* 登录按钮 */
#btn {
	background-color: rgba(29, 161, 241, 0.60);
	padding: 0 80px;
	border-radius: 6px;
	border: 1px solid rgb(26, 117, 152);
	border-image: none;
	color: rgb(255, 255, 255);
	font-size: 16px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="top_div"></div>
	<div
		style="background: rgb(255, 255, 255); margin: -130px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 275px; text-align: center; border-radius: 14px;">
		<h3>秒杀系统 - 管理员入口</h3>
		<form action="${pageContext.request.contextPath}/adminValidate"
			method="post">
			<p style="padding: 15px 0px 10px; position: relative;">
				<span class="u_logo"></span> <input class="ipt" type="text"
					placeholder="登录名" value="" name="aname" autofocus="autofocus">
			</p>
			<p style="position: relative;">
				<span class="p_logo"></span> <input class="ipt" id="password"
					type="password" placeholder="密码" value="" name="apassword">
			</p>
			<div
				style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
				<p style="margin: 8px 20px;">
					<input type="submit" id="btn" value="登&nbsp;&nbsp;录" />
				</p>
			</div>
			<c:if test="${error == 0}">
				<script type="text/javascript">
					alert("登录失败:此账号无权限登入！");
				</script>
			</c:if>
			<c:if test="${error == -1}">
				<script type="text/javascript">
					alert("登录失败:账号或密码错误！");
				</script>
			</c:if>
		</form>
	</div>
</body>
</html>