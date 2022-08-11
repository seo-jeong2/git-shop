<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ page import = "vo.*" %>    
<%@ page import = "vo.*" %>  
      
<%
	if(session.getAttribute("user") == null) { 
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");		
		return;
	} 		
%>    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body><!-- 고객인지 스텝인지 구분을 해야하는데 -->
	<%=session.getAttribute("user") %> 
	<br>
	<%=session.getAttribute("id") %> 
	<br>
	<%=session.getAttribute("name") %> 
	<br>
	<%
	if(session.getAttribute("user").equals("Employee")) {

	%>
	<a href="<%=request.getContextPath()%>/logout.jsp"> 로그아웃 </a>
	<a href="<%=request.getContextPath()%>/remove<%=session.getAttribute("user")%>Form.jsp"> 회원탈퇴 </a>
	<a href="<%=request.getContextPath()%>/employeeList.jsp">사원관리</a>
	<a href="<%=request.getContextPath()%>/admin/adminGoodsList.jsp">상품관리</a>
	<a href="<%=request.getContextPath()%>/admin/adminOrderList.jsp">주문관리</a>
	<a href="<%=request.getContextPath()%>/admin/adminCustomerList.jsp">고객관리</a>
	<a href="<%=request.getContextPath()%>/admin/adminNoticeList.jsp">공지관리</a>
	<% 
	
	}
	
	%>
	
</body>
</html>