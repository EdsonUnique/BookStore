<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加图书</title>
</head>
<body >
<br>
	<form action="${pageContext.request.contextPath }/manager/BookServlet?method=addBook" method="post" enctype="multipart/form-data">
		
		<table  style="text-align: center;" align="center" frame="border" border="1" width="60%">
			<tr>
				<td>图书名称：
				<td>
					<input type="text" name="name"/>
				</td>
			</tr>
			
			<tr>
				<td>图书作者：
				<td>
					<input type="text" name="author"/>
				</td>
			</tr>
			
			<tr>
				<td>图书价格：
				<td>
					<input type="text" name="price"/>
				</td>
			</tr>
			
			<tr>
				<td>图书图片：
				<td>
					<input type="file" name="image"/>
				</td>
			</tr>
			
			<tr>
				<td>图书描述：
				<td>
					<textarea rows="5" cols="30" name="description"></textarea>
				</td>
			</tr>
			
			<tr>
				<td>图书分类：</td>
				<td>
					<select name="category_id">
						<c:forEach var="b" items="${book_category }">
							<option  value="${b.id }"/> ${b.name } 
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="添加"/><br>
				</td>
			</tr>
		</table>
		
	</form>
		
</body>
</html>