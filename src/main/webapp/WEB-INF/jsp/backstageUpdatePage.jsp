<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp" %>
<c:if test="${sessionScope.admin == null}">
	<jsp:forward page="/loginError"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
<head>
	<title>修改商品信息</title>
	<link rel="icon" href="${pageContext.request.contextPath}/image/ico.ico" />
	<%@include file="common/head.jsp" %>
</head>
<body>
	<div class="container">
		<div class="pull-left" style="padding: 10px 0 10px 20px;">
			<a class="btn btn-success" href="${pageContext.request.contextPath}/backstageIndex">返&nbsp;&nbsp;回</a>
		</div>
	    <div class="panel panel-warning" style="margin: 50px 0 0 0;">
	        <div class="panel-heading text-center">
	            <h2>修改秒杀商品信息</h2>
	        </div>
	        <div class="panel-body">
	        	<form action="${pageContext.request.contextPath}/${seckill.seckillId}/seckillUpdateActive" method="post">
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
						            <input type="text" class="form-control" value="${seckill.name}" name="name">
						        </div>
	                        </td>
	                        <td>
	                        	<div class="input-group">
						            <span class="input-group-addon">>></span>
						            <input type="text" class="form-control" value="${seckill.number}" name="number">
						        </div>
	                        </td>
	                        <td>
	                        	<div class="input-group">
						            <span class="input-group-addon">>></span>
						            <input type="text" class="form-control"  name="startTime"
						            	value="<fmt:formatDate value='${seckill.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"
						            />
						        </div>
	                        </td>
	                        <td>
	                        	<div class="input-group">
						            <span class="input-group-addon">>></span>
						            <input type="text" class="form-control" name="endTime"
						            	value="<fmt:formatDate value='${seckill.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"
						            />
						        </div>
	                        </td>
	                        <td>
	                        	<input type="submit" class="btn btn-warning" value="修&nbsp;&nbsp;改" />
	                        </td>
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
	<script src="http://apps.bdimg.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<script src="https://cdn.bootcss.com/moment.js/2.18.1/moment-with-locales.min.js"></script>  
	<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>  
	
</body>
</html>