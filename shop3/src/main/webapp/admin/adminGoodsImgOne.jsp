<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "vo.*" %>    
<%@ page import = "service.*" %>
<%@ page import = "repository.*" %>
    
<%
	int goodsNo = Integer.parseInt(request.getParameter("goodsNo")); 
	GoodsService goodsService = new GoodsService();
	Map<String, Object> map = goodsService.getGoodsAndImgOne(goodsNo);
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> 상품 상세보기 </h1>
<table border="1">
	<thead>
	<tr> 
		<td>번호</td>
		<td>이미지</td>
		<td>이름</td>
		<td>가격</td>
		<td>등록일</td>
		<td>수정일</td>
		<td>품절여부</td>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td><%=map.get("goodsNo")%></td>
		<td><img src="<%=request.getContextPath()%>/upload/<%=map.get("imgFileName")%>"></td>
		<td><%=map.get("goodsName")%></td>
		<td><%=map.get("goodsPrice")%></td>
		<td><%=map.get("imgCreateDate")%></td>
		<td><%=map.get("goodsUpdateDate")%></td>
		<td><%=map.get("soldOut")%></td>
	</tr>
	</tbody>	
</table>

	
</body>
</html>