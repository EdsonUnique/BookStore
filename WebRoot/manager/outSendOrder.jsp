<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>未发货订单</title>
</head>
<body >
	未发货订单信息<br><br>
	<table frame="border" border="1" width="80%" style="text-align: center;" align="center">
		<tr>
			<td>订单号</td>
			<td>下单时间</td>
			<td>总价</td>
			<td>订单状态</td>
			<td>客户名</td>
			<td>订单明细</td>
		</tr>
	
		<c:forEach var="order" items="${orders }">
			<tr>
				<td>${order.id }</td>
				<td>${order.orderTime }</td>
				<td>${order.totalprice}</td>
			
				<c:choose>
					<c:when test="${order.state==false}">
						<td>未发货</td>
					</c:when>
					<c:otherwise>
						<td>已发货</td>
					</c:otherwise>
				</c:choose>
				
				
				<td>${order.user.username }</td>
				<td>
					<a href="${pageContext.request.contextPath }/manager/OrderServlet?method=listOrderItems&order_id=${order.id}">订单明细</a>
				</td>
			</tr>
		
		</c:forEach>
	</table>
	
	<br><br><br><br><br>
	客户信息<br><br>
	<table  frame="border" border="1" width="80%" style="text-align: center;" align="center">
		<tr>
			<td>客户名</td>
			<td>手机号</td>
			<td>地址</td>
			<td>订单号</td>
			<td>操作</td>
		</tr>
	
		<c:forEach var="order" items="${orders }">
			<tr>
				<td>${order.user.username }</td>
				<td>${order.user.phone }</td>
				<td>${order.user.address}</td>
				<td>${order.id }</td>
				<td>
					<a href="${pageContext.request.contextPath }/manager/OrderServlet?method=updateState&state=true&order_id=${order.id}">确认发货</a>
				</td>
			</tr>
		
		</c:forEach>
	
	</table>


</body>
</html>