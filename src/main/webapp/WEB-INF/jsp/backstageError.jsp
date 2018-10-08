<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp"%>
<title>访问出错</title>
<link rel="icon" href="${pageContext.request.contextPath}/image/error.ico" />
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

.bd {
	background-color: #f2f2f2;
	position: relative;
}

#container {
	position: fixed;
	left: 30px;
	right: 30px;
	top: 30px;
	bottom: 30px;
	height: 500px;
	background-color: #fff;
	border: 1px solid #ccc;
	border-radius: 15px;
}

#box {
	text-align: center;
	color: #aaa;
	font-size: 40px;
	margin-top: 12%;
}
</style>
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var height = $(document).height() - 0
		px;
		$("#container").css("height", height);
	});
</script>
</head>
<body class="bd">
	<div id="container">
		<div id="box">
			o(╥﹏╥) o 出错啦！<br /> 无相关访问权限！<br /> 请使用正确方式进行登录访问...
		</div>
	</div>
</body>
</html>