<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>所有图书</title>
	
</head>
<body style="text-align: center;">
<br>
		<table  style="text-align: center;" align="center" frame="border" border="1" width="60%">
			<caption align="top">图书管理</caption>
				<tr>
					<td>图书名称</td>
					<td>作者</td>
					<td>价格</td>
					<td>描述</td>
					<td>图片</td>
					<td>操作</td>
				</tr>
				
				<c:forEach var="book" items="${page.list }">
					<tr>
						<td>${book.name }</td>
						<td>${book.author }</td>
						<td>${book.price }</td>
						<td>${book.description }</td>
						<td>
							<a href="${pageContext.request.contextPath}/${book.image}">查看图片</a>
						</td>
						<td>
							<a href="#">修改图书</a>
							<a href="#">删除图书</a>
						</td>
					</tr>
				</c:forEach>
		</table>
		<br><br>
		
		<%@ include file="/jsp/page.jsp"%>
		
</body>
</html>