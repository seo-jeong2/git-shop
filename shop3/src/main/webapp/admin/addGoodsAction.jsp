<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.oreilly.servlet.*" %>    
<%@ page import = "com.oreilly.servlet.multipart.*" %>   
<%@ page import = "service.GoodsService" %> 
<%@ page import = "vo.Goods" %> 
<%@ page import = "vo.*" %> 
<%@ page import = "java.io.File" %>

<%
//업로드 폴더 정적 지정
// String dir = "c:\\upload"; 
// 업로드 폴더 동적 지정
	String dir = request.getServletContext().getRealPath("/upload");
	System.out.println(dir);

	
	int max = 10 * 1024 * 1024; // 1024byte -> 1kbyte, 1024kbyte -> 1mbyte
	MultipartRequest mRequest = new MultipartRequest(request, dir, max, "utf-8", new DefaultFileRenamePolicy());
	
	String goodsName = mRequest.getParameter("goodsName");
	int goodsPrice = Integer.parseInt(mRequest.getParameter("goodsPrice"));
	
	String soldOut = mRequest.getParameter("soldOut");
	String fileName = mRequest.getFilesystemName("imgFile"); // dir파일이름
	String contentType = mRequest.getContentType("imgFile");//파일타입확인
	String OriginalFileName = mRequest.getOriginalFileName("imgFile"); //원본파일이름
	
	System.out.println(goodsName + "goodsName"); 
	System.out.println(goodsPrice + "goodsPrice");
	System.out.println(soldOut +"soldOut" );
	System.out.println(fileName );
	System.out.println(soldOut);
	
	 if(!(contentType.equals("image/gif") || contentType.equals("image/png") || contentType.equals("image/jpg")|| contentType.equals("image/jpeg"))) {
	      // 이미 업로드된 파일을 삭제
	      File f = new File(dir+"\\"+fileName);
	      if(f.exists()) {
	         f.delete();
	      }
	      String errorMsg = URLEncoder.encode("이미지파일만 업로드 가능", "utf-8");
	      response.sendRedirect(request.getContextPath()+"/addGoodsFrom.jsp?errorMsg="+errorMsg);
	      return;
	   }
	

   Goods goods = new Goods();
   GoodsImg goodsImg = new GoodsImg();

   goods.setGoodsName(goodsName);
   goods.setGoodsPrice(goodsPrice);
   goods.setSoldOut(soldOut);
   goodsImg.setFileName(fileName);
   goodsImg.setContentType(contentType);
   goodsImg.setOriginFileName(OriginalFileName);
   

   GoodsService goodsservice = new GoodsService();
   int row = goodsservice.addGoods(goods, goodsImg);


   if(row != 0){
	      System.out.println("이미지 업로드 성공");
	      response.sendRedirect(request.getContextPath() + "/admin/adminGoodsList.jsp");
	   } else {
	      System.out.println("이미지 업로드 실패");
	      response.sendRedirect(request.getContextPath() + "/admin/addGoodsForm.jsp?errorMsg=img insert Fail");
	   
	   }


%>