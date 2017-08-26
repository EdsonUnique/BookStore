<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>head</title>
</head>
<body style="text-align: center;">
<br>
	<h1>午后书院</h1>
	
	<div style="position:absolute; left:600px;">
		<a href="${pageContext.request.contextPath }/client/IndexServlet" target="body">首页</a>
		<a href="${pageContext.request.contextPath }/client/listCart.jsp" target="body">查看购物车</a>
		<a href="${pageContext.request.contextPath }/client/OrdersServlet?method=listOrders" target="body"">查看订单</a>
	</div>
	
	<div style="position:absolute; right:0px;">
		<c:if test="${user!=null }">
		欢迎您：${user.username }
		</c:if>
		
		<c:if test="${user==null }">
			<form action="${pageContext.request.contextPath }/client/LoginServlet" method="post">
				用户名：<input type="text" name="username" size="7%">
				密码:<input type="password" name="password" size="7%">
				<input type="submit" value="登录">
				<input type="button" value="注册" onclick="javascript:window.parent.body.location.href='register.jsp'">
			</form>
		</c:if>
	</div>
</body>
</html>