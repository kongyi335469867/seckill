<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp" %>
<c:if test="${sessionScope.admin == null}">
	<jsp:forward page="/loginError"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
<head>
	<title>用户管理</title>
	<link rel="icon" href="${pageContext.request.contextPath}/image/ico.ico" />
	<%@include file="common/head.jsp" %>
</head>
<body>
	<div class="container">
		<div class="pull-left" style="padding: 10px 0 10px 20px;">
			<a class="btn btn-success" href="${pageContext.request.contextPath}/backstageIndex">返&nbsp;&nbsp;回</a>
		</div>
	    <div class="panel panel-success" style="margin: 50px 0 0 0;">
	        <div class="panel-heading text-center">
	            <h2>用户秒杀记录详情>></h2>
	        </div>
	        <div class="panel-body">
	            <table class="table table-hover table-striped">
	                <thead>
	                <tr style="font-size: 16px;">
	                    <th>用户手机号:</th>
	                    <th>用户秒杀时间:</th>
	                    <th>货物状态:</th>
	                    <th>商品名称:</th>
	                    <th>此商品秒杀开始时间:</th>
	                    <th>此商品秒杀结束时间:</th>
	                    <th>操作:</th>
	                </tr>
	                </thead>
	                <tbody>
	                <c:forEach items="${userskList}" var="usersk">
	                    <tr>
	                        <td style="font-weight: bold; color:#0000FF; ">${usersk.userPhone}</td>
	                        <td>
	                            <fmt:formatDate value="${usersk.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	                        </td>
	                        <td style="font-weight: bold; color: #0000FF; ">
	                        	<c:if test="${usersk.state == -1 }"><div style="color: #FF0000; ">秒杀无效</div></c:if>
	                        	<c:if test="${usersk.state == 0 }"><div style="color: #49A449; ">成功秒杀</div></c:if>
	                        	<c:if test="${usersk.state == 1 }"><div style="color: #00FF00; ">已付款</div></c:if>
	                        	<c:if test="${usersk.state == 2 }"><div style="color: #FF00FF; ">已发货</div></c:if>
	                        </td>
	                        <td style="color:#0000FF; ">${usersk.seckill.name}</td>
	                        <td>
	                            <fmt:formatDate value="${usersk.seckill.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	                        </td>
	                        <td>
	                            <fmt:formatDate value="${usersk.seckill.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	                        </td>
	                        <td>
	                        	<c:if test="${usersk.state == -1 }">
	                        		<div>
	                        			<a class="btn btn-danger" href="javacript:void(0);" onclick="javascript:alert('//TODO');">删&nbsp;&nbsp;&nbsp;除</a>
	                        		</div>
	                        	</c:if>
	                        	<c:if test="${usersk.state == 0 }">
	                        		<div>
	                        			<a class="btn btn-info" href="javacript:void(0);" onclick="javascript:alert('//TODO');">提&nbsp;&nbsp;&nbsp;醒</a>
	                        		</div>
	                        	</c:if>
	                        	<c:if test="${usersk.state == 1 }">
	                        		<div>
	                        			<a class="btn btn-primary" href="javacript:void(0);" onclick="javascript:alert('//TODO');">发&nbsp;&nbsp;&nbsp;货</a>
	                        		</div>
	                        	</c:if>
	                        	<c:if test="${usersk.state == 2 }">
	                        		<div style="cursor: not-allowed">
	                        			<a class="btn btn-success" href="javacript:void(0);" disabled="disabled">已发货</a>
	                        		</div>
	                        	</c:if>
	                        </td>
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