<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>注册</title>
</head>
<body style="text-align: center;">

	<h2>注&nbsp;&nbsp;册</h2>
	<form action="${pageContext.request.contextPath }/client/RegisterServlet" method="post">
		<table style=" text-align: center;" align="center">
			<tr>
				<td>用户名：</td>
				<td>
					<input type="text" name="username">
				</td>
			</tr>
			
			<tr>
				<td>密码:</td>
				<td>
					<input type="password" name="password">
				</td>
			</tr>
			
			<tr>
				<td>手机号码：</td>
				<td>
					<input type="text" name="phone">
				</td>
			</tr>
			
			<tr>
				<td>地址：</td>
				<td>
					<input type="text" name="address">
				</td>
			</tr>
			
			<tr>
				<td>邮箱：</td>
				<td>
					<input type="text" name="email">
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="注册" >
				</td>
			</tr>
		
		
		</table>
	</form>
</body>
</html>