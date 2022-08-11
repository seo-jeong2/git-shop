<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addCustomer</title>
</head>
<body>
	<h3>고객회원가입</h3>

	<!-- id check form -->
	<form action="<%=request.getContextPath()%>/idCheckAction.jsp" method="post">
		<div>
			ID 체크 <input type="text" name="ckId">
			<button type="submit">아이디중복검사</button>
			<input type=hidden name="id" value="customer">
		</div>
	</form>
	<!-- id check form -->
	<br />
	<!-- 고객 가입 form -->
	<%
	String ckId = ""; // 변수 공백
	if (request.getParameter("ckId") != null) {
		ckId = request.getParameter("ckId");
	}
	%>
	<form action="<%=request.getContextPath()%>/addCustomerAction.jsp"
		method="post">
		<table border="1">
			<tr>
				<td>ID</td>
				<td><input type="text" name="customerId" id="customerId"
					readonly="readonly" value="<%=ckId%>"></td>
				<!-- id check에서 검사하고 true를 받으면 넣어줌 id가 중복되지않았을 경우 -->
			</tr>
			<tr>
				<td>PASSWORD</td>
				<td><input type="password" name="customerPass"
					id="customerPass"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="customerName" id="customerName"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="customerAddress"
					id="customerAddress"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="customerPhone" id="customerPhone"></td>
			</tr>
		</table>
		<button type="submit">회원가입</button>
	</form>
	<!-- 고객 가입 form -->
</body>
</html>