package repository;
import vo.*;
import java.sql.*;
import java.util.*;






public class OrdersDao {
   // 5-2) 주문상세보기
   public Map<String, Object> selectOrdersOne(Connection conn, int ordersNo) throws Exception {
      Map<String, Object> map =new HashMap<String, Object>();
      String sql = "SELECT\r\n"
				+ "		o.order_no,\r\n"
				+ "		o.goods_no,\r\n"
				+ "		o.customer_id,\r\n"
				+ "		o.order_quantity,\r\n"
				+ "		o.order_price,\r\n"
				+ "		o.order_addr,\r\n"
				+ "		o.order_state,\r\n"
				+ "		g.goods_no,\r\n"
				+ "		g.goods_name,\r\n"
				+ "		g.goods_price,\r\n"
				+ "		g.update_date,\r\n"
				+ "		g.create_date,\r\n"
				+ "		g.sold_out,\r\n"
				+ "		c.customer_id,\r\n"
				+ "		c.customer_name,\r\n"
				+ "		c.customer_address,\r\n"
				+ "		c.customer_telephone\r\n"
				+ "		FROM orders o INNER JOIN goods g\r\n"
				+ "		ON o.goods_no = g.goods_no \r\n"
				+ "		INNER JOIN customer c\r\n"
				+ "		ON o.customer_id = c.customer_id \r\n"
				+ "		WHERE o.order_no =1 ?	";
     
      PreparedStatement stmt = null;
      ResultSet rs =null;     
      
      try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ordersNo);
			rs =stmt.executeQuery();

			System.out.println(rs + " : rs");
			
			while(rs.next()) {
				map = new HashMap<String, Object>();	
				map.put("orderNo", rs.getInt("o.order_no"));
				map.put("goodsNo", rs.getInt("o.goods_no"));
				map.put("customerId", rs.getString("o.customer_id"));
				map.put("orderQuantity", rs.getString("o.order_quantity"));
				map.put("orderPrice", rs.getInt("o.order_price"));
				map.put("orderAddr", rs.getString("o.order_addr"));
				map.put("orderstate", rs.getString("o.order_state"));
				map.put("goodsNo", rs.getInt("g.goods_no"));
				map.put("goodsName", rs.getString("g.goods_name"));
				map.put("goodsPrice", rs.getInt("g.goods_price"));
				map.put("updateDateGd", rs.getString("g.update_date"));
				map.put("createDateGd", rs.getString("g.create_date"));
				map.put("soldOut", rs.getString("g.sold_out"));
				map.put("customerIdc", rs.getString("c.customer_id"));
				map.put("customerName", rs.getString("c.customer_name"));
				map.put("customerAddress", rs.getString("c.customer_address"));
				map.put("customerTelephone", rs.getString("c.customer_telephone"));

				System.out.print(map + " : map");	
				
			}
			
      } finally {
			if(rs!=null) {rs.close();}
			
			if(stmt!=null) {stmt.close();}
		}
			
       
      return map;
   }
   
   // 5-1) 전체 주문 목록(관리자)
   public ArrayList<Orders> selectOrdersList(Connection conn, int rowPerPage, int beginRow) throws Exception {
      
	   ArrayList<Orders> list = new ArrayList<Orders>(); // 다형성
      
      String sql = " SELECT  o.order_no  ,\r\n"
        		+ "          o.order_quantity ,\r\n"
        		+ "          o.order_price ,\r\n"
        		+ "          o.order_address ,\r\n"
        		+ "          o.update_date ,\r\n"
        		+ "          o.create_date ,\r\n"
        		+ "          g.goods_no ,\r\n"
        		+ "          g.goods_name ,\r\n"
        		+ "          g.goods_price ,\r\n"
        		+ "          g.soldOut ,\r\n"
        		+ "       FROM orders o INNER JOIN goods g \r\n"
        		+ "       ON o.goods_no = g.goods_no \r\n"
        		+ "       ORDER BY create_date DESC LIMIT ?, ?";
       
      PreparedStatement stmt = null;
      ResultSet rs =null;       
      
      System.out.println(rs + " : rs");
      
      try {
    	  
    	  list = new ArrayList<Orders>();
    	  
    	  stmt = conn.prepareStatement(sql);
    	  stmt.setInt(1, beginRow);
		  stmt.setInt(2, rowPerPage);
		  rs = stmt.executeQuery();

		System.out.println(rs + " : rs");
		
		while(rs.next()) {
			Orders orders = new Orders();
			orders.setOrderNo(rs.getInt("ordersNo"));
			orders.setGoodsNo(rs.getInt("goodsNo"));
			orders.setCustomerId(rs.getString("customerId"));
			orders.setOrderQuantity(rs.getInt("orderQuantity"));
			orders.setOrderPrice(rs.getInt("orderPrice"));
			orders.setOrderAddress(rs.getString("orderAddress"));
			orders.setOrderState(rs.getString("orderState"));
			orders.setUpdateDate(rs.getString("updateDate"));
			orders.setCreateDate(rs.getString("createDate"));
		
			
			
		}
			
		} finally {
			rs.close();
			stmt.close();
		}
		return list;
	}
			
			
			
   public int OrderslastPage(Connection conn, int rowPerPage) throws SQLException { 
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FORM goods"; 
		PreparedStatement stmt = null;
		ResultSet rs  = null;
		
		stmt = conn.prepareStatement(sql); 
		rs = stmt.executeQuery();
		
		
		if(rs != null) { 
			rs.close();
		}
		if(stmt != null) {
			stmt.close();
		}
		
		return totalCount;
	}


    
   // 고객 한명의 주문 목록(관리자, 고객) 
   public List<Map<String, Object>> selectOrdersListByCustomer(Connection conn, 
                                 String customerId, int rowPerPage, int beginRow) {

      List<Map<String, Object>> list = new ArrayList<>(); // 다형성
      
      /*
       SELECT 
          o. ,
          g. ,
       FROM orders o INNER JOIN goods g
       ON o.goods_no = g.goods_no
       WHERE customer_id = ?
       ORDER BY create_date DESC
       LIMIT ?, ?
       */
      
      return list;
   }
}

