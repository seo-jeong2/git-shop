package repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrdersDao {
   // 5-2) 주문상세보기
   public Map<String, Object> selectOrdersOne(Connection conn, int ordersNo) {
      Map<String, Object> map = null;
      /*
       SELECT
          o. ,
          g. ,
          c. ,
       FROM orders o INNERE JOIN goods g
       ON o.goods_no = g.goods_no
                            INNER JOIN customer c
                            ON o.customer_id = c.customer_id
       WHERE o.orders_no = ?
       */
      return map;
   }
   
   // 5-1) 전체 주문 목록(관리자)
   public List<Map<String, Object>> selectOrdersList(Connection conn, int rowPerPage, int beginRow) {
      List<Map<String, Object>> list = new ArrayList<>(); // 다형성
      
      /*
       SELECT 
          o. ,
          g. ,
       FROM orders o INNER JOIN goods g
       ON o.goods_no = g.goods_no 
       ORDER BY create_date DESC
       LIMIT ?, ?
       */
      
      return list;
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

