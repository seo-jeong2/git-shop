<%@page import="vo.*"%>
<%@page import="java.util.*"%>
<%@page import="service.*"%>
<%@page import="repository.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	if(session.getAttribute("user") == null ) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		System.out.println("관리자로 로그인하세요.");
		return;
	}

//페이징
	int currentPage = 1;
	if (request.getParameter("currentPage") != null) {
	currentPage = Integer.parseInt(request.getParameter("currentPage"));
}
	final int rowPerPage = 10;
	
	OrdersService ordersService = new OrdersService();
	List<Orders> list = new ArrayList<Orders>();
	list = ordersService.getOrdersListByEmployee(rowPerPage, currentPage);

	int lastPage = ordersService.getOrderListByPageLastPage(rowPerPage);	

%>	
	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<div>
<!-- 메뉴 -->
	<ul>
		<li><a href = "<%=request.getContextPath()%>/employeeList.jsp">사원관리</a></li>
		<li><a href = "<%=request.getContextPath()%>/admin/adminGoodsList.jsp">상품관리</a></li>
		<li><a href = "<%=request.getContextPath()%>/admin/adminOrderList.jsp">주문관리</a></li>
		<li><a href = "<%=request.getContextPath()%>/admin/adminCustomerList.jsp">고객관리</a></li>
		<li><a href = "<%=request.getContextPath()%>/admin/adminNoticeList.jsp">공지관리</a></li>
	</ul>
	</div>

<h1>주문 관리</h1>

<body>
<table border="1">
			<thead>
				<tr>
					<th>주문번호</th>
					<th>상품번호</th>
					<th>고객아이디</th>
					<th>주문수량</th>
					<th>상품가격</th>
					<th>주문주소</th>
					<th>주문상태</th>
					<th>등록일</th>
					<th>수정일</th>
				</tr>
				</thead>
				
				<%
				
				for(Orders o  : list) {
				
				%>
					<tr>
						<td><%=o.getOrderNo()%></td>
						<td><%=o.getGoodsNo()%></td>
						<td><%=o.getCustomerId()%></td>
						<td><%=o.getOrderQuantity()%></td>
						<td><%=o.getOrderPrice()%></td>
						<td><%=o.getOrderAddress()%></td>
						<td><%=o.getOrderState()%></td>
						<td><%=o.getUpdateDate()%></td>
						<td><%=o.getCreateDate()%></td>
					</tr>	
				
				<%
				
				}
				
				%>	
				
				</table>
				<div>
			<%
				if(currentPage > 1) {
			%>	
					<a href="<%=request.getContextPath()%>/admin/adminOrderList.jsp?currentPage=<%=currentPage-1%>" class="btn btn-dark">이전</a>
			<%
			
				}
				if(currentPage < lastPage) {
			%>	
					<a href="<%=request.getContextPath()%>/admin/adminOrderList.jsp?currentPage=<%=currentPage+1%>" class="btn btn-dark">다음</a>
			<%
				}
			%>
		</div>
		
		<a href="<%=request.getContextPath()%>/index.jsp">처음으로</a>
				
</body>
</html>