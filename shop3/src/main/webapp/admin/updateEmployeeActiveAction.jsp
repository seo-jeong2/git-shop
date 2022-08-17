<%@page import="repository.EmployeeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import="service.*" %>
<%
	
	String active = request.getParameter("active");
	String employeeId = request.getParameter("employeeId");
	
	System.out.println(active);
	System.out.println(employeeId);
	
	
	Employee employee = new Employee();
	EmployeeService employeeService = new EmployeeService();
	
	employee.setActive(active);
	employee.setEmployeeId(employeeId);
	
	boolean result = employeeService.modifyEmployeeActive(employee);
	
	if(result) {
		response.sendRedirect(request.getContextPath() + "/employeeList.jsp");
		System.out.println("active 변경 성공");
	} else {
		response.sendRedirect(request.getContextPath() + "/employeeList.jsp");
		System.out.println("active 변경 실패");
	}
%>