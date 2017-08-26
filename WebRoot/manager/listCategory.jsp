<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>所有分类</title>
</head>
<body >
<br>
	<table  style="text-align: center;" align="center" frame="border" border="1" width="60%">
	<caption align="top">分类管理</caption>
			<tr>
				<td>分类名称</td>
				<td>分类描述</td>
				<td>操作</td>
			</tr>
			
			<c:forEach var="category" items="${list }">
			<tr>
				<td>${category.name }</td>
				<td>${category.description }</td>
				<td>
					<a href="#">修改分类</a>
					<a href="#">删除分类</a>
				</td>
			</tr>
			</c:forEach>
		</table>

		
</body>
</html>