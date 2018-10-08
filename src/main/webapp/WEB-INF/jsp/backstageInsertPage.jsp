<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp"%>
<c:if test="${sessionScope.admin == null}">
	<jsp:forward page="/loginError"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
<head>
<title>修改秒杀商品信息</title>
<link rel="icon" href="${pageContext.request.contextPath}/image/ico.ico" />
<%@include file="common/head.jsp"%>
</head>
<body>
	<div class="container">
		<div class="pull-left" style="padding: 10px 0 10px 20px;">
			<a class="btn btn-success" href="${pageContext.request.contextPath}/backstageIndex">返&nbsp;&nbsp;回</a>
		</div>
		<div class="panel panel-success" style="margin: 50px 0 0 0;">
			<div class="panel-heading text-center">
				<h2>添加商品信息</h2>
			</div>
			<div class="panel-body">
				<form
					action="${pageContext.request.contextPath}/seckillInsertActive"
					method="post" class="bs-example bs-example-form" role="form">
					<table class="table table-default">
						<thead>
							<tr style="font-size: 18px;">
								<th>商品名称：</th>
								<th>商品库存：</th>
								<th>开始时间：</th>
								<th>结束时间：</th>
								<th>操作：</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<div class="input-group">
										<span class="input-group-addon">>></span> 
										<input type="text" class="form-control" placeholder="" name="name">
									</div>
								</td>
								<td>
									<div class="input-group">
										<span class="input-group-addon">>></span> 
										<input type="text" class="form-control" placeholder="" name="number">
									</div>
								</td>
								<td>
									<div class="input-group">
										<span class="input-group-addon">>></span> 
										<input type="text" class="form-control" placeholder="格式:2018-10-02 00:00:00" name="startTime">
									</div>
								</td>
								<td>
									<div class="input-group">
										<span class="input-group-addon">>></span> 
										<input type="text" class="form-control" placeholder="格式:2018-10-02 00:00:00" name="endTime">
									</div>
								</td>
								<td>
									<input type="submit" class="btn btn-info" value="新&nbsp;&nbsp;增" />
								</td>
								<c:if test="${insertmsg == 0}">
									<script type="text/javascript">
										alert("添加失败！");
									</script>
								</c:if>
								<c:if test="${insertmsg == 1}">
									<script type="text/javascript">
										alert("添加成功！");
									</script>
								</c:if>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>

	<!-- 引入jQuery文件 -->
	<script src="http://apps.bdimg.com/libs/jquery/3.2.1/jquery.min.js"></script>

	<!-- 引入 Bootstrap 核心 JavaScript 文件 -->
	<script
		src="http://apps.bdimg.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>