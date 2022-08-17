<%@page import="vo.*"%>
<%@page import="service.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "service.*" %>
<%@ page import = "java.util.*" %>

<%

	request.setCharacterEncoding("utf-8");
	
	if(session.getAttribute("user") == null) { 
	response.sendRedirect(request.getContextPath()+"/loginForm.jsp");		
	System.out.println("고객으로 로그인하세요");
	return;
} 		

	//페이징
		int currentPage = 1;
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		final int ROW_PER_PAGE = 10;
		
		GoodsService goodsService = new GoodsService();
		List<Goods> list = new ArrayList<Goods>();
		list = goodsService. getGoodsListByPage(ROW_PER_PAGE, currentPage);
		System.out.println("상품목록보기");
	
	int lastPage = goodsService.getGoodsListLastPage(ROW_PER_PAGE );
	

	
%>


<!-- View 태그 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>상품 목록</h1>
	<div>
		<a href=""> 인기순 </a>
		<a href=""> 판매량순 </a>
		<a href=""> 낮은가격순 </a>
		<a href=""> 높은가격순 </a>
		<a href=""> 최신순 </a>
	</div>
	
	<table border="1">
	<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>가격</th>
					<th>등록일</th>
					<th>품절여부</th>
				</tr>
		</thead>	
		<tbody>
		<tr>
			<%
				for(Goods g : list) {
			%>
					<tr> 
					<td><%=g.getGoodsNo()%></td>
					<td><%=g.getGoodsName()%></td>
					<td><%=g.getGoodsPrice()%></td>
					<td><%=g.getCreateDate()%></td>
					<td><%=g.getSoldOut()%></td>
				
							
			<%		
				}		
			%>
			</tbody>
		</table>
			
			<%
				if(currentPage > 1) {
			%>	
					<a href="<%=request.getContextPath()%>/customerGoodsList.jsp?currentPage=<%=currentPage-1%>" class="btn btn-dark">이전</a>
			<%
				}
				if(currentPage < lastPage) {
			%>	
					<a href="<%=request.getContextPath()%>/customerGoodsList.jsp?currentPage=<%=currentPage+1%>" class="btn btn-dark">다음</a>
			<%
				}
			%>
			
			<a href="<%=request.getContextPath()%>/index.jsp">처음으로</a>
		

</body>
</html>