<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>客户订单</title>
</head>
<body style="text-align: center;">
	
	<h2>客户订单</h2>
	<table frame="border" border="1" width="60%" align="center">
		<tr>
			<td>订单号</td>
			<td>下单时间</td>
			<td>订单状态</td>
			<td>总价</td>
			<td>操作</td>
		</tr>
		
		<c:forEach var="order" items="${orders}">
			<tr>
				<td>${order.id }</td>
				<td>${order.orderTime }</td>
				
				<c:choose>
					<c:when test="${order.state==false }">
						<td>未发货</td>
					</c:when>
					<c:otherwise>
						<td>已发货</td>
					</c:otherwise>
				</c:choose>
				
				
				<td>${order.totalprice }</td>
				<td>
					<a href="${pageContext.request.contextPath }/client/OrdersServlet?method=listOrderItems&order_id=${order.id}">订单明细</a>
				</td>
			</tr>
		
		</c:forEach>
		
	
	
	</table>
	
</body>
</html>