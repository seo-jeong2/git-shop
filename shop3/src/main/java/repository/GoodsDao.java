package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


import vo.Goods;

public class GoodsDao {
	
	 // 고객 상품리스트 페이지에서 사용 >>> 쿠팡같은 상품 상세보기
    public List<Map<String, Object>> selectcustomerGoodsListByPage(Connection conn, int rowPerPage, int beginRow) throws SQLException {
       List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();

       
       /*
        고객의 판매량수 많은 것 부터 >> 판매량수니까 원래는 COUNT(*) 인데 
        손님이 1주문량에 물건 n개일수잇으니까 SUM!
              
        SELECT g.goods_no goodsNo,
        g.goods_name goodsName,
        g.goods_price goodsPrice,
        gi.filename fileName        
        FROM
        goods g LEFT JOIN (SELECT goods_no, SUM(order_quantity) sumNum
                       FROM orders
                       GROUP BY goods_no) t 
                       ON g.goods_no = t.goods_no
                          INNER JOIN goods_img gi
                          ON g.goods_no = gi.goods_no
       ORDER BY IFNULL(t.sumNum, 0) DESC
     
      
        한번도 안팔린 상품 나왔으면 족헷으니까>>> 왼쪽 >>> LEFT JOIN
        그걸 ORDER BY해봤자 0개 / 오른쪽은 존재 안한다면 null로 나온대 >>> 주문량 0..
        
        실무에선 테이블 조인이 엄청 많아서 / 그 조인과의 집계결과를 또 조인하기도 하고 UNION도 있고 등등 300~400줄 온대
        */
       
       /*
       품절이어도 품절로 뜸
       SELECT
       g.goods_no goodsNo,
       g.goods_name goodsName,
       g.goods_price goodsPrice,
       g.sold_out soldOut
       FROM goods g 
       INNER JOIN goods_img gi ON g.goods_no = gi.goods_no
       ORDER BY crate_date LIMIT ?,?;                 
       */
       
       String sql = "SELECT g.goods_no goodsNo,\r\n"
             + "          g.goods_name goodsName,\r\n"
             + "          g.goods_price goodsPrice,\r\n"
             + "          gi.filename fileName        \r\n"
             + "          FROM\r\n"
             + "          goods g LEFT JOIN (SELECT goods_no, SUM(order_quantity) sumNum\r\n"
             + "                         FROM orders\r\n"
             + "                         GROUP BY goods_no) t \r\n"
             + "                         ON g.goods_no = t.goods_no\r\n"
             + "                            INNER JOIN goods_img gi\r\n"
             + "                            ON g.goods_no = gi.goods_no\r\n"
             + "         ORDER BY IFNULL(t.sumNum, 0) DESC";
       
       PreparedStatement stmt = null;
       ResultSet rs =null;         
       
       stmt = conn.prepareStatement(sql);
       rs = stmt.executeQuery();
       
       while(rs.next()) {
          Map<String, Object> map = new HashMap<String, Object>();
       }
       if(rs != null) {
          rs.close();
       }
       if(stmt != null) {
          stmt.close();
       }
       return list;
    }
 
	
	
	
	
	public int insertGoods(Connection conn, Goods goods) throws Exception {
		// row가 아니라 방금 입력한 goods_no(value)를 return -> jdbc 메소드 이용
		int goodsNo = 0;
		String sql = "insert into goods(goods_name, goods_price, sold_out, update_date, create_date)"
				+ " values (?, ?, ?, now(), now())";
		
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		// RETURN_GENERATED_KEYS == 1 --> 두 번의 쿼리 실행
		// 1) insert 
		// 2) select last_ai_key from table
		
		stmt.setString(1, goods.getGoodsName());
		stmt.setInt(2, goods.getGoodsPrice());
		stmt.setString(3, goods.getSoldOut());
		
		stmt.executeUpdate(); // insert 
		ResultSet rs = stmt.getGeneratedKeys(); // return 값 
		
		if(rs.next()) {
			goodsNo = rs.getInt(1);
			System.out.println("GoodsDao.insertGoods: "+ goodsNo);
			// getGeneratedKeys가 반환하는 컬럼명을 알 순 없지만
			// 첫번째라는 것은 알 수 있으므로 rs.getInt(1)
		}
		
		if(rs != null) {
			rs.close();
		}
		
		if(stmt != null) {
			stmt.close();
		}
		
		return goodsNo;
	}
	
	
	// 상품 soldout 변경
		public int updateGoodsSoldOut(Connection conn, Goods goods) throws SQLException {
			int row = 0;
			String sql = "UPDATE goods SET sold_out = ? WHERE goods_no = ?";
			PreparedStatement stmt = null;

			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, goods.getSoldOut());
				stmt.setInt(2, goods.getGoodsNo());
				
				row = stmt.executeUpdate();
			}finally {
				if(stmt!=null) {
					stmt.close();
				}
			}
			return row;
		}
	
	public Map<String,Object> selectGoodsAndImgOne(Connection conn, int goodsNo) throws SQLException{
		Map<String,Object> map =new HashMap<String, Object>();
		String sql = "SELECT g.*, gi.* FROM goods g INNER JOIN goods_img gi ON g.goods_no=gi.goods_no WHERE g.goods_no=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, goodsNo);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				map.put("goodsNo", rs.getInt("g.goods_no"));
				map.put("goodsName", rs.getString("g.goods_name"));
				map.put("goodsPrice", rs.getInt("g.goods_price"));
				map.put("goodsUpdateDate", rs.getString("g.update_date"));
				map.put("goodsCreateDate", rs.getString("g.create_date"));
				map.put("soldOut", rs.getString("g.sold_out"));
				map.put("imgFileName", rs.getString("gi.filename"));
				map.put("imgOriginFileName", rs.getString("gi.origin_filename"));
				map.put("imgContentType", rs.getString("gi.content_type"));
				map.put("imgCreateDate", rs.getString("gi.create_date"));
			}
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
		return map;
	}
	

	public List<Goods> selectGoodsListByPage(Connection conn, int rowPerPage, int beginRow) throws SQLException {
			
		List<Goods> list = new ArrayList<Goods>();
		
		String sql = "SELECT goods_no goodsNo, goods_name goodsName, goods_price goodsPrice, update_date updateDate, create_date createDate, sold_out soldOut FROM goods ORDER BY goods_no DESC limit ?,?";
		PreparedStatement stmt = null;
		ResultSet rset = null;
		

//		 SELECT goods_no goodsNo FROM goods ORDER BY goods_no DESC LIMIT ?,?

		
		try {
			
			list = new ArrayList<Goods>();
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rset = stmt.executeQuery();
			
			while(rset.next()) {
				Goods goods = new Goods();	
				goods.setGoodsNo(rset.getInt("goodsNo"));
				goods.setGoodsName(rset.getString("goodsName"));
				goods.setGoodsPrice(rset.getInt("goodsPrice"));
				goods.setUpdateDate(rset.getString("updateDate"));
				goods.setCreateDate(rset.getString("createDate"));
				goods.setSoldOut(rset.getString("soldOut"));
				
				list.add(goods);
				
			}
			
		} finally {
			rset.close();
			stmt.close();
		}
		return list;
	}
	
	public int lastPage(Connection conn) throws SQLException { 
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

}
