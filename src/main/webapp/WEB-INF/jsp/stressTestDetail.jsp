<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>商品秒杀详情页(测试)</title>
	<link rel="icon" href="${pageContext.request.contextPath}/image/ico.ico" />
	<%@include file="common/head.jsp" %>
</head>
<body>
    <div class="container" style="padding: 80px 0 0 0">
        <div class="panel panel-primary text-center">
            <%-- 秒杀商品名 --%>
            <div class="pannel-heading">
                <h1>${seckill.name}</h1>
            </div>
            <div class="panel-body">
                <h2 class="text-danger">
                    <%-- 显示time图标 --%>
                    <span class="glyphicon glyphicon-time" style="margin: 10px 10px 0 0;"></span>
                    <%-- 压力测试：直接开始秒杀功能 --%>
                    <span class="glyphicon" id="seckill-box">
                    	<a class="btn btn-primary btn-lg" id="killBtn" href="${pageContext.request.contextPath }/${seckill.seckillId}/stressTestExecute">开始秒杀</a>
                    	
                    </span>
                </h2>
            </div>
        </div>
    </div>
    <%-- 登录弹出层，输入手机号码 --%>
    <%-- 使用Bootstrap模态框modal插件 --%>
    <div id="killPhoneModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title text-center" style="color: #2AABD2">
                        <span class="glyphicon glyphicon-phone"> </span> &nbsp;手&nbsp;机&nbsp;号&nbsp;登&nbsp;录:
                    </h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <input type="text" name="killPhone" id="killPhoneKey"
                                   placeholder="请正确填写手机号..." class="form-control">
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <%--验证信息--%>
                    <span id="killPhoneMessage" class="glyphicon"> </span>
                    <button type="button" id="killPhoneBtn" class="btn btn-success">
                        <span class="glyphicon glyphicon-phone"></span>
						 提交
                    </button>
                </div>
            </div>
        </div>
    </div>
</body>
<%-- 引入JQery脚本文件 --%>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<%-- 引入Bootstrap框架的JS脚本文件 --%>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<%-- 引入CDN获取的JS脚本 --%>
<%-- 引入JQuery Cookie操作插件 --%>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<%-- 引入JQuery countDown倒计时插件 --%>
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<!-- 引入外部JS文件,通过seckill.js文件控制所有动作 -->
<script src="${pageContext.request.contextPath}/js/seckill.js"></script>

</html>