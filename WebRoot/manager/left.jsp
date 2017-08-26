<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>left</title>

</head>
<body style="text-align: center;">
	<br>
	<ul>
		<li>
			<a href="#">分类管理</a>
			<a href="${pageContext.request.contextPath }/manager/addCategory.jsp" target="body">添加分类</a>
			<a href="${pageContext.request.contextPath }/manager/CategoryServlet?method=listAllCategory" target="body">查看分类</a> 
		
		</li>
		<li>
			<a href="#"  target="body">图书管理</a>
			<a href="${pageContext.request.contextPath }/manager/BookServlet?method=addBookUI"  target="body">添加图书</a><br>
			<a href="${pageContext.request.contextPath }/manager/BookServlet?method=listAllBook"  target="body">查看所有图书</a><br><br>
		</li>
		<li>
			<a href="#"  target="body">订单管理</a><br><br>
			<a href="${pageContext.request.contextPath }/manager/OrderServlet?method=inSend"  target="body">已发货订单</a><br>
			<a href="${pageContext.request.contextPath }/manager/OrderServlet?method=outSend"  target="body">未发货订单</a><br>
		</li>	
	</ul>
</body>
</html>