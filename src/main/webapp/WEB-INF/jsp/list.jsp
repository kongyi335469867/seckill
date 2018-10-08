<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>商品秒杀列表</title>
	<link rel="icon" href="${pageContext.request.contextPath}/image/ico.ico" />
	<%@ include file="common/head.jsp" %>
</head>
<body>
	<div class="container" style="padding: 40px 0 0 0;">
	    <div class="panel panel-info">
	        <div class="panel-heading text-center">
	            <h2>秒杀商品列表</h2>
	        </div>
	        <div class="panel-body">
	            <table class="table table-hover table-striped">
	                <thead>
	                <tr style="font-size: 18px;">
	                    <th>商品名称</th>
	                    <th>商品库存</th>
	                    <th>开始时间</th>
	                    <th>结束时间</th>
	                    <th>创建时间</th>
	                    <th>详情信息</th>
	                </tr>
	                </thead>
	                <tbody>
	                <c:forEach items="${list}" var="sk">
	                    <tr>
	                        <td style="font-weight: bold; ">${sk.name}</td>
	                        <td>${sk.number}</td>
	                        <td style="color: #2AABD2; ">
	                            <fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	                        </td>
	                        <td style="color: #FF0000; ">
	                            <fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	                        </td>
	                        <td>
	                            <fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	                        </td>
	                        <td><a class="btn btn-info" href="${pageContext.request.contextPath}/${sk.seckillId}/detail" target="_blank">商品详情</a></td>
	                    </tr>
	                </c:forEach>
	                </tbody>
	            </table>
	
	        </div>
	    </div>
	</div>
		
	<!-- 引入jQuery文件 -->
	<script src="http://apps.bdimg.com/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- 引入 Bootstrap 核心 JavaScript 文件 -->
	<script src="http://apps.bdimg.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</body>
</html>