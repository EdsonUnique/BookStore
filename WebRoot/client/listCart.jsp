<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>购物车页面</title>
</head>
<body>
	<table align="center" style="text-align: center;" frame="border" border="1px">
		<tr>
			<td>商品名</td>
			<td>商品图片</td>
			<td>单价</td>
			<td>数量</td>
			<td>小计</td>
			<td>操作</td>
		</tr>
		
		<c:forEach var="me" items="${cart.map}">
			<tr>
				<td>${me.value.book.name }</td>
				<td>
					<img alt="${me.value.book.name }" src="${pageContext.request.contextPath }/${me.value.book.image}" width="30px"/>
				</td>
				<td>${me.value.book.price }</td>
				<td>${me.value.num }</td>
				<td>${me.value.sum }</td>
				<td>
					<a href="#">删除</a>
					<a href="#">增加</a>
				</td>
			</tr>
		</c:forEach>
		
		<tr>
			<td colspan="6" style="text-align: right;">
				总计：${cart.totalprice }&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath }/client/MakeOrdersServlet" >确认下单</a>
			</td>
		</tr>
		
	</table>
	
	
</body>
</html>