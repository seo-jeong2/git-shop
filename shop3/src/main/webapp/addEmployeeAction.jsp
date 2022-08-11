<%@page import="service.*"%>
<%@page import="repository.EmployeeDao"%>
<%@page import="vo.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

request.setCharacterEncoding("utf-8");
String employeeId = request.getParameter("employeeId");
String employeePass = request.getParameter("employeePass");
String employeeName = request.getParameter("employeeName");

System.out.print(employeeId);
System.out.print(employeePass);
System.out.print(employeeName);




   Employee paramEmpolyee = new Employee();

   paramEmpolyee.setEmployeeId(employeeId);
   paramEmpolyee.setEmployeePass(employeePass);
   paramEmpolyee.setEmployeeName(employeeName);


   EmployeeService insertService = new EmployeeService();
   insertService.addEmpolyee(paramEmpolyee);


response.sendRedirect(request.getContextPath()+"/loginForm.jsp");



%>