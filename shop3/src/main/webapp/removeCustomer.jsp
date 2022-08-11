<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
     
<%@page import="vo.*"%>    
<%@ page import = "service.*" %>

    
<%
	
	String Pass = request.getParameter("Pass");
	String customerId = (String)session.getAttribute("id");

	

	// 디버깅
	System.out.println(Pass);
	System.out.println(customerId);
	
	Customer paramCustomer = new Customer();
    paramCustomer.setCustomerId(customerId);
    paramCustomer.setCustomerPass(Pass);


	CustomerService customerService = new CustomerService();
	customerService.removeCustomer(paramCustomer);
	

	response.sendRedirect(request.getContextPath()+"/logout.jsp");
	
	
%>
