<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="service.*"%>
<%@ page import="vo.*"%>
<%
request.setCharacterEncoding("utf-8");

/*if (session.getAttribute("user") == null && session.getAttribute("active").equals("Y")) {
	response.sendRedirect(request.getContextPath() + "/index.jsp");
	System.out.println("없음");
	return;
}	
*/

int currentPage = 1;
if (request.getParameter("currentPage") != null) {
	currentPage = Integer.parseInt(request.getParameter("currentPage"));
}
final int ROW_PER_PAGE = 10;

GoodsService goodsService = new GoodsService();
List<Goods> list = new ArrayList<Goods>();
list = goodsService.getGoodsListByPage(ROW_PER_PAGE, currentPage);

int lastPage = goodsService.getlastPage(ROW_PER_PAGE);
System.out.print("lastPage : " + lastPage);	

if(list == null) {
	response.sendRedirect(request.getContextPath() + "/admin/adminIndex.jsp");
	return;
}
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
		<li><a href = "<%=request.getContextPath()%>/admin/adminIndex.jsp">사원관리</a></li>
		<li><a href = "<%=request.getContextPath()%>/admin/adminGoodsList.jsp">상품관리</a></li>
		<li><a href = "<%=request.getContextPath()%>/admin/adminOrdersList.jsp">주문관리</a></li>
		<li><a href = "<%=request.getContextPath()%>/admin/adminCustomerList.jsp">고객관리</a></li>
		<li><a href = "<%=request.getContextPath()%>/admin/adminNoticeList.jsp">공지관리</a></li>
	</ul>
	</div>

	<h1>상품 관리</h1>
	<a href="<%=request.getContextPath()%>/admin/addGoodsForm.jsp"> 상품추가</a>
	
	<body>	
		<table border="1">
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>가격</th>
					<th>등록일</th>
					<th>수정일</th>
					<th>품절여부</th>
				</tr>
			</thead>
			<tbody>
			<%
				for(Goods g : list) {
			%>
					<tr>
						<td><%=g.getGoodsNo()%></td>
						<td><a href="<%=request.getContextPath()%>/admin/adminGoodsImgOne.jsp?goodsNo=<%=g.getGoodsNo() %>">
						<%=g.getGoodsName()%></a></td>
						<td><%=g.getGoodsPrice()%></td>
						<td><%=g.getCreateDate()%></td>
						<td><%=g.getUpdateDate()%></td>
						
					<td>
									<form action="<%=request.getContextPath()%>/admin/updateGoodsSoldOutAction.jsp" method="post">
										<input type="hidden" name="goodsNo" value="<%=g.getGoodsNo()%>">
										<select name="soldOut">
											<%
												if(g.getSoldOut().equals("N")) {
											%>
													<option value="Y">Y</option>
													<option value="N" selected="selected">N</option>
											<%
												} else {
											%>
													<option value="Y" selected="selected">Y</option>
													<option value="N">N</option>
											<%
												}
											%>
										</select>
									<button type="submit" class="btn btn-dark">UPDATE</button>
									</form>
								</td>
					</tr>
			<%	
				}
			%>
		</tbody>
		</table>
		<div>
			<%
				if(currentPage > 1) {
			%>	
					<a href="<%=request.getContextPath()%>/admin/adminGoodsList.jsp?currentPage=<%=currentPage-1%>" class="btn btn-dark">이전</a>
			<%
				}
				if(currentPage < lastPage) {
			%>	
					<a href="<%=request.getContextPath()%>/admin/adminGoodsList.jsp?currentPage=<%=currentPage+1%>" class="btn btn-dark">다음</a>
			<%
				}
			%>
		</div>
	
</body>
</html>