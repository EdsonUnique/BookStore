<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>客户订单项</title>
</head>
<body>
	<table align="center" style="text-align: center;" frame="border">
		<tr>
			<td>商品名</td>
			<td>商品图片</td>
			<td>单价</td>
			<td>数量</td>
			<td>小计</td>
		</tr>
		
		<c:forEach var="item" items="${order.map}">
			<tr>
				<td>${item.value.book.name }</td>
				<td>
					<img alt="${item.value.book.name }" src="${pageContext.request.contextPath }/${item.value.book.image}" width="30px"/>
				</td>
				<td>${item.value.book.price }</td>
				<td>${item.value.num }</td>
				<td>${item.value.sum }</td>
			</tr>
		</c:forEach>
		
		<tr>
			<td colspan="6" style="text-align: right;">
				总计：${order.totalprice }&nbsp;&nbsp;
			</td>
		</tr>
		
	</table>

</body>
</html>