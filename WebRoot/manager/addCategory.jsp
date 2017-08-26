<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加分类</title>
</head>
<body >
<br>
	<form action="${pageContext.request.contextPath }/manager/CategoryServlet?method=addCategory" method="post">
		
		<table  style="text-align: center;" align="center" frame="border" border="1" width="60%">
			<tr>
				<td>分类名称：
				<td>
					<input type="text" name="name"/>
				</td>
			</tr>
			
			<tr>
				<td>分类描述：
				<td>
					<textarea rows="5" cols="30" name="description"></textarea>
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