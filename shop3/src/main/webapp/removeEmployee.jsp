<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vo.*"%>       
<%@page import="vo.Customer"%>    
<%@ page import = "service.*" %>


<%
	
	// 변수받아오기
	String Pass = request.getParameter("Pass");
	String employeeId = (String)session.getAttribute("id");

	
	// 디버깅
	System.out.println(Pass);
	System.out.println(employeeId);
	
	Employee paramEmployee = new Employee();
	paramEmployee.setEmployeeId(employeeId);
	paramEmployee.setEmployeePass(Pass);
	
	EmployeeService employeeService = new EmployeeService();
	employeeService.removeEmployee(paramEmployee);
	

	response.sendRedirect(request.getContextPath()+"/logout.jsp");
%>