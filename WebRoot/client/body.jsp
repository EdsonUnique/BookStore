<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>body</title>
</head>
<body>
		<div id="category" style="float: left;margin-left:230px;height:200px;width:120px;border:1px solid red;text-align: left">
			图书类别
			<ul>
				<c:forEach var="c" items="${category }">
					<li><a href="${pageContext.request.contextPath }
					/client/IndexServlet?category_id=${c.id}" target="body">${c.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		<div id="bookAndPage" style="float: left;margin-left: 180px;">
			<div id="books">
				<div id="book" >
					<c:forEach var="book" items="${page.list }">
						<div id="bookImage"  style="float: left;">
							<img alt="${book.name }" src="${pageContext.request.contextPath }/${book.image}" width="130px" height="200px" />
						</div>
						<div id="bookInfo" style="float: left;text-align: left;">
							<ul>
								<li>《${book.name }》</li>
								<li>${book.author }</li>
								<li>${book.price }</li>
								<li>
									<a href="${pageContext.request.contextPath }/client/BuyServlet?bookId=${book.id}">购买</a>
								</li>
							</ul>
						</div>
						<div style="clear:both;"></div>
						<br/>
					</c:forEach>
					
				</div>
			</div>
			<div id="page">
				<%@ include file="/jsp/page.jsp" %>
			</div>
		</div>
</body>
</html>