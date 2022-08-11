<%@page import="service.GoodsService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "service.CustomerService" %>
<%@ page import = "java.util.*" %>

<%
	// Controller : java class <- Servlet
	// 로퍼페이지랑 커렌
	
	// 행이 아니라 열로 출력할거래!
	int rowPerPage = 20;
	if(request.getParameter("rowPerPage") !=null) {
		rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
	}
	
	int currentPage = 1;	//				┌1이 아니면과의 동일한 표현
	if(request.getParameter("currentPage") !=null) {
		rowPerPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	
	GoodsService goodsService = new GoodsService();
	//customerService.getcustomerGoodsListByPage(rowPerPage, currentPage);
	
	// list						┌모델값
	List<Map<String, Object>> list = goodsService.getcustomerGoodsListByPage(rowPerPage, currentPage);

	// 뷰는 모델값을 출력하는 역할 / 모델까지 현재 컨트롤러가 갖고있음 / 나중엔 뷰도 분리함
	
	
%>

<!--  

	View를 분리시 나중에 / servlet (※) jsp로 분리 중간에 중개(=연결)기술 (※)이 필요 
	중개(=연결)기술 (※) = forword
	forword는 저 두개를 겹치는거래 뭔 사라진다는데? 서블릿에 있던걸 뷰로 다 보내버리고 서블릿이 사라지나봐
	매개값에 다 넣어서 그걸 뷰로 보내버리나봐 

	forword(request, response) 만 있으면 된대. 브라우저에서 넘길 기술은 오직 request, response뿐
	request, response 이거 받아서 View가 그대로 쓴대	
	모델값은 주로 request안에 넣는대. request.setAttribute();, 담길땐 전부 Object타입
	
	
	웹아키텍쳐의 저장공간
	1. 페이지 
	2. 리퀘스트 (포워딩해도 유지됨) request.setAttribute();
	3. 세션 (내요청이 살아있을 때까지 살아있음) session.setAttribute();
	4. 톰캣(context에 저장) >>> 모든 페이지에서 이 변수를 읽을 수 있다
	>>> context의 내장객체 이름은 application (리퀘스트와 세션은 각각 영어로 그 이름인데)
	
	
	Cf
	redirect는 자기만의 request, response를 만들어서 쓴댕
	
-->
<!-- View 태그 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  for / if  >> 나중엔 이거 대체하는 기술 배운대 : 커스텀태그(JSTL & EL )-->
	<!--  └ 아니 분리했다는데 꺽쇄넣어서 자바나오니까 너무 불편하니까 -->
	<!--  html만 쓰면 커스텀태그 못쓴대 >> 결론은 jsp는 쓸 수밖에 없대
	 jsp는 자바를 쓸 수 있기때문에 전용 view는 아니래 
	 jsp는 서블릿과 같다는데?
	 html도 아니고 jsp도 아닌데 막 태그도 되고 뭔가 이것저것 다 되는게 있나봐 >>> 뷰템플릿
	 실무는 뷰템플릿인데 우리는 jsp쓰다 끝날듯
	-->
	<div>
		<a href=""> 인기순 </a>
		<a href=""> 판매량순 </a>
		<a href=""> 낮은가격순 </a>
		<a href=""> 높은가격순 </a>
		<a href=""> 최신순 </a>
	</div>
	<table border="1">
		<tr>
			<%
				int i=1;
				for(Map<String, Object> m : list) {
			%>
					<td> 
					<div>
						<a href="">						
						<img src='<%=request.getContextPath()%>/upload/<%=m.get("filename")%>' width="200" height="200">
						</a>
					</div>
					<div><%=m.get("goodsName")%></div>
					<div><%=m.get("goodsPrice")%></div>
					<!--  리뷰 갯수 >>> 리뷰테이블과 조인을 해야겠지?? -->
					</td>
							
			<%		
					if(i%4==0) {
			%>
						</tr><tr>
			<%			
					}
						i++; 
						// 4개찍고 tr들어가고 4개찍고 tr들어가고...(쿠팡식 상품보기 참조) 
						// 근데 4로 넘어가지 않는 경우도있으니 그때의 td를 추가해야한대 list.size가져온대; 들어도 ㅁ모르겠음 ㅎㅎㅎㅎㅎㅎ
				}// 여기까진 일단 4로 나눠진 후 td다 찍힌 상태		
				
					// 헷갈리면 달력참고하래
					int tdCnt = 4 - (list.size() %4); // (19%4 = 3... 1이 더 필요하대. 그 가라 빈공간 채워서 마지막 공간도 완료하는 그런건가봐)
					if(tdCnt ==4) {
						tdCnt = 0;
					}
				
						for(int j=0; j<tdCnt; j++) {
			%>
						<td>&nbsp;</td> <!--  부족한만큼 가라 공간 채우기 -->
			
			<%				
						}
			%>
			
			<!--  이 과정이 끝나면 한 페이지당 20개씩 채울 수 있을거래 -->
		</tr>
	</table>
	
		<!--  페이징 + 상품검색 -->

</body>
</html>