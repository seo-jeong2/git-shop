<%@page import="service.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String id = request.getParameter("id");
	String ckId =request.getParameter("ckId");

	SignService existservice = new SignService();
	
	boolean check = existservice.idCheck(ckId);


	if(id.equals("customer")){
		if (!check){
		response.sendRedirect(request.getContextPath()+"/addCustomer.jsp?errorMsg=Duplicate IDs");
		}
		
		else if (check){
			response.sendRedirect(request.getContextPath()+"/addCustomer.jsp?ckId="+ckId);
		}
	}
	if(id.equals("employee")){
		if (!check){
		response.sendRedirect(request.getContextPath()+"/addEmployee.jsp?errorMsg=Duplicate IDs");
		}
		
		else if (check){
			response.sendRedirect(request.getContextPath()+"/addEmployee.jsp?ckId="+ckId);
		}
	}
	
	
	
	
%>   