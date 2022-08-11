package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import vo.GoodsImg;

public class GoodsImgDao {

	
	public int insertGoodsImg(Connection conn, GoodsImg goodsImg) throws SQLException{
		int row = 0;
		
		
		PreparedStatement stmt = null;
		String sql = "INSERT INTO goods_img (goods_no, filename, origin_filename, content_type, create_date) VALUES (?, ?, ?, ?, NOW())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, goodsImg.getGoodsNo());
			stmt.setString(2, goodsImg.getFileName());
			stmt.setString(3, goodsImg.getOriginFileName());
			stmt.setString(4, goodsImg.getContentType());
			System.out.println(stmt + "<--stmt - insertGoodsImg");
			// 실행
			row = stmt.executeUpdate();
			
			
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
		System.out.println(row + "<-- row - insertGoodsImg");
		return row;
	}
}
		
		
		
		
		
		