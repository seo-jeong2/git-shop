<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "vo.*" %>    

      
<%

	if(session.getAttribute("user") == null) { // 로그인안하면
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
<body>
	<h1> 관리자탈퇴 </h1>
	<form action ="<%=request.getContextPath()%>/removeEmployee.jsp" method="post">
	<table border="1">
		<tr>
			<td> 비밀번호를 입력하세요 </td>
			<td> <input type="password" name="Pass" id="Pass"></td>
			
		</tr>
	
	</table>
	<button type="submit"> 탈퇴 </button>
</form>
</body>
</html>