<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="repository.*"%>
<%@page import="service.*"%>
<%@page import="vo.*"%>
    
    
<%
  
	request.setCharacterEncoding("UTF-8");

                	
    String customerId = request.getParameter("customerId");
    String customerPass = request.getParameter("customerPass");
    String customerName = request.getParameter("customerName");
                	
    //디버깅
    System.out.println("고객아이디 : " + customerId);
    System.out.println("고객비밀번호 : " + customerPass);
                	
                	
                	
    Customer paramCustomer = new Customer();
    paramCustomer.setCustomerId(customerId);
    paramCustomer.setCustomerPass(customerPass);
                	
     	
                	
    CustomerService customerService = new CustomerService();
                	
                	
    Customer login = customerService.loginCustomer(paramCustomer);
                	
                	
    if(login !=null) { // LOGIN 성공
     System.out.println("로그인 성공");		
     session.setAttribute("user", "Customer");  
     session.setAttribute("id", login.getCustomerId());
     session.setAttribute("name", login.getCustomerName());
     response.sendRedirect(request.getContextPath()+"/index.jsp");
                		
     } else { // LOGIN 실패
                		
     response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
     System.out.println("로그인 실패");		
     }
    
 %>
