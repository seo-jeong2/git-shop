<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addEmployee</title>
</head>
<body>
	<h3>관리자회원가입</h3>

	<!-- id check form -->
<form action ="<%=request.getContextPath()%>/idCheckAction.jsp" method="post">
<div>
		ID 체크 
		<input type="text" name = "ckId">
		<button type ="submit">아이디중복검사</button>
		<input type=hidden name = "id" value="employee">
	</div>
</form>

	</form>
	<!-- id check form -->
	<br />
	<!-- 관리자 form -->
	<%
	String ckId = ""; // 변수 공백
	if (request.getParameter("ckId") != null) {
		ckId = request.getParameter("ckId");
	}
	%>
	<form action="<%=request.getContextPath()%>/addEmployeeAction.jsp"
		method="post">
		<table border="1">
			<tr>
				<td>ID</td>
				<td><input type="text" name="employeeId" id="employeeId"
					readonly="readonly" value="<%=ckId%>"></td>
				<!-- id check에서 검사하고 true를 받으면 넣어줌 id가 중복되지않았을 경우 -->
			</tr>
			<tr>
				<td>PASSWORD</td>
				<td><input type="password" name="employeePass"
					id="employeePass"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="employeeName" id="employeeName"></td>
			</tr>
		</table>
		<button type="submit">회원가입</button>
	</form>
	<!-- 관리자 가입 form -->
</body>
</html>