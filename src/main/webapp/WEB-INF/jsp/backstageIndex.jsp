<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp" %>
<c:if test="${sessionScope.admin == null}">
	<jsp:forward page="/loginError"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
<head>
	<title>后台管理主页</title>
	<link rel="icon" href="${pageContext.request.contextPath}/image/ico.ico" />
	<%@include file="common/head.jsp" %>
</head>
<body>
	<div class="container">
		<div class="panel panel-primary pull-right" style="padding: 5px 20px; margin: 5px 0;">
			<div>
				<span style="color: #0000FF; cursor: pointer; ">${sessionScope.admin.aname}</span>
				, &nbsp;欢迎进入<b>秒杀商品系统管理平台 O_o </b> &nbsp;&nbsp;
				<a type="button" class="btn btn-success" href="${pageContext.request.contextPath}/adminExit"> 退&nbsp;&nbsp;出</a>
			</div>
		</div>
	    <div class="panel panel-primary" style="margin: 55px 0 0 0;">
	        <div class="panel-heading text-center">
	            <h2>秒杀商品列表</h2>
	        </div>
	        <div class="pull-right" style="padding:5px 10px 0 0;">
               	<a class="btn btn-primary" href="${pageContext.request.contextPath}/userManagement">用户&nbsp;管理</a>
            </div>
	        <div class="panel-body">
	            <table class="table table-hover table-striped">
	                <thead>
	                <tr style="font-size: 18px;">
	                    <th>商品名称:</th>
	                    <th>商品库存:</th>
	                    <th>开始时间:</th>
	                    <th>结束时间:</th>
	                    <th>创建时间:</th>
	                    <th>修改操作:</th>
	                    <th>删除操作:</th>
	                </tr>
	                </thead>
	                <tbody>
	                <c:forEach items="${list}" var="sk">
	                    <tr>
	                        <td style="font-weight: bold; ">${sk.name}</td>
	                        <td>${sk.number}</td>
	                        <td>
	                            <fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	                        </td>
	                        <td>
	                            <fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	                        </td>
	                        <td>
	                            <fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	                        </td>
	                        <td>
	                        	<a class="btn btn-warning" href="${pageContext.request.contextPath}/${sk.seckillId}/seckillUpdate">修改</a>
	                        </td>
	                        <td>
	                        	<a class="btn btn-danger" href="javascript:if(confirm('确实删除吗?')) location='${pageContext.request.contextPath}/${sk.seckillId}/seckillDelete'">删除</a>
	                        </td>
	                    </tr>
	                </c:forEach>
	                <c:if test="${deletemsg == 0}">
						<script type="text/javascript">
							alert("删除失败！");
						</script>
					</c:if>
					<c:if test="${deletemsg == 1}">
						<script type="text/javascript">
							alert("删除成功！");
						</script>
					</c:if>
					<c:if test="${updatemsg == 0}">
						<script type="text/javascript">
							alert("修改失败！");
						</script>
					</c:if>
					<c:if test="${updatemsg == 1}">
						<script type="text/javascript">
							alert("修改成功！");
						</script>
					</c:if>
	                </tbody>
	            </table>
				<table style="margin:0 20px 10px 20px; paddin:0; ">
					<tr>
	                 	<td class="pull-left">
	                		<a class="btn btn-primary" href="${pageContext.request.contextPath}/seckillInsert">添加&nbsp;商品</a>
	                	</td>
	                </tr>
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