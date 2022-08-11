<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//세션 유효성 검정 코드

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div><!-- 상단메뉴  -->
<ul>
	<li><a href="<%=request.getContextPath()%>/employeeList.jsp">사원관리</a></li>
	<li><a href="<%=request.getContextPath()%>/admin/adminGoodsList.jsp">상품관리</a></li>
	<li><a href="<%=request.getContextPath()%>/admin/adminOrderList.jsp">주문관리</a></li>
	<li><a href="<%=request.getContextPath()%>/admin/adminCustomerList.jsp">고객관리</a></li>
	<li><a href="<%=request.getContextPath()%>/admin/adminNoticeList.jsp">공지관리</a></li>
</ul>
</div>
<!-- 메인내용 -->
<h1>사원관리</h1>
<div>
	<!-- 사원목록 , active값 수정 -->
</div>
</body>
</html>