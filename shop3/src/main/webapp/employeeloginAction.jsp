<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="repository.*"%>
<%@page import="service.EmployeeService"%>
<%@page import="vo.Employee"%> 
  
 <%
   
         
         
			request.setCharacterEncoding("UTF-8");
         
        	String employeeId = request.getParameter("employeeId");
        	String employeePass = request.getParameter("employeePass");
        	String employeeName = request.getParameter("employeeName");
        	
        	//디버깅
        	System.out.println("관리자아이디 : " + employeeId);
        	System.out.println("관리자비밀번호 : " + employeePass);
        	
        	
        	
        	Employee paramEmployee = new Employee();
        	paramEmployee.setEmployeeId(employeeId);
        	paramEmployee.setEmployeePass(employeePass);
        	
        	EmployeeService employeeService = new EmployeeService();
        	
        	Employee login = employeeService.loginEmployee(paramEmployee);
        	
         
        	if(login !=null) {  // LOGIN 성공
        		System.out.println("성공");		
        		session.setAttribute("user", "Employee"); 
        		session.setAttribute("id", login.getEmployeeId());
        		session.setAttribute("name", login.getEmployeeName());
        		response.sendRedirect(request.getContextPath()+"/index.jsp");
        		
        	}else {  // LOGIN 실패
        		
        		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
        		
        		System.out.println("실패");		
        	}
    %>