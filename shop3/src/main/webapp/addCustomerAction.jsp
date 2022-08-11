<%@page import="service.*"%>
<%@page import="vo.Customer"%>
<%@page import="repository.CustomerDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("utf-8");
	String customerId = request.getParameter("customerId");
	String customerPass = request.getParameter("customerPass");
	String customerName = request.getParameter("customerName");
	String customerPhone = request.getParameter("customerPhone");
	String customerAddress = request.getParameter("customerAddress");
	
	System.out.println(customerId);
	System.out.println(customerPass);
	System.out.println(customerName);
	System.out.println(customerPhone);
	System.out.println(customerAddress);



   Customer paramCustomer = new Customer();

   paramCustomer.setCustomerId(customerId);
   paramCustomer.setCustomerPass(customerPass);
   paramCustomer.setCustomerName(customerName);
   paramCustomer.setCustomerAddress(customerAddress);
   paramCustomer.setCustomerTelephone(customerPhone);

   CustomerService insertService = new CustomerService();
   insertService.addCustomer(paramCustomer);


	response.sendRedirect(request.getContextPath()+"/loginForm.jsp");


%>