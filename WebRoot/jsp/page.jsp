<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
当前页面：[${page.pagenum }]&nbsp;&nbsp;
		<c:if test="${page.pagenum>1}">
			<a href="${pageContext.request.contextPath }/${page.servlet }pagenum=${page.pagenum-1}">上一页</a>
		</c:if>
			<c:forEach  var="index" begin="${page.startpage}" end="${page.endpage}">
				[<a href="${pageContext.request.contextPath }/${page.servlet }pagenum=${index}">${index }</a>]
			</c:forEach>
		<c:if test="${page.pagenum<page.totalPage}">	
			<a href="${pageContext.request.contextPath }/${page.servlet }pagenum=${page.pagenum+1}">下一页</a>
		</c:if>
		&nbsp;&nbsp;
		<input type="button" value="GO" onclick="toPage()"/>
		<input type="text"  size="3%" id="goPage"/>&nbsp;&nbsp;
		<script type="text/javascript">
			function toPage(){
				var pagenum=document.getElementById("goPage").value;
				if(pagenum!=null && pagenum<=${page.totalPage} && pagenum>0){
					window.location.href="${pageContext.request.contextPath }/${page.servlet }pagenum="+pagenum;
				}else{
					alert("页码错误！");
					document.getElementById("goPage").value="";
				}
				
			}
		</script>
		总共[${page.totalPage }]页&nbsp;&nbsp;
		总共[${page.totalRecord }]条记录