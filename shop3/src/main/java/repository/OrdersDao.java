package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Customer;

public class OrdersDao {
   // 5-2) 주문상세보기
   public Map<String, Object> selectOrdersOne(Connection conn, int ordersNo) {
      Map<String, Object> map = new HashMap<Map<String,Object>();
      String sql = " SELECT\r\n"
      		+ "          o.order_no  ,\r\n"
      		+ "          o.order_quantity ,\r\n"
      		+ "          o.order_price ,\r\n"
      		+ "          o.order_address ,\r\n"
      		+ "          o.update_date ,\r\n"
      		+ "          o.create_date ,\r\n"
      		+ "          g.goods_no ,\r\n"
      		+ "          g.goods_name ,\r\n"
      		+ "          g.goods_price ,\r\n"
      		+ "          g.soldOut ,\r\n"
      		+ "          c.customer_id ,\r\n"
      		+ "       FROM orders o INNERE JOIN goods g\r\n"
      		+ "       ON o.goods_no = g.goods_no\r\n"
      		+ "                            INNER JOIN customer c\r\n"
      		+ "                            ON o.customer_id = c.customer_id\r\n"
      		+ "       WHERE o.orders_no = ?";
     
     
      
       
      return map;
   }
   
   // 5-1) 전체 주문 목록(관리자)
   public List<Map<String, Object>> selectOrdersList(Connection conn, int rowPerPage, int beginRow) {
      List<Map<String, Object>> list = new ArrayList<>(); // 다형성
      
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
      
      try {
			stmt = conn.prepareStatement(sql);
			stmt.
			rs = stmt.executeQuery();
      
}
     
      
      
      
      
      
      
    }
   // 2-1) 고객 한명의 주문 목록(관리자, 고객) 
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

