package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import service.CustomerService;
import vo.OutId;

public class OutIdDao {
	
	// 탈퇴회원의 아이디를 입력 
	public int insertOutId(Connection conn, String customerId) throws SQLException {
		// CustomerDao와 동일한 conn 사용하므로 conn.close() 하면안됨
		int row = 0;

		String sql = "INSERT INTO outid (out_id, out_date) VALUES (?, NOW())";
		PreparedStatement stmt=null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerId);
			
			row = stmt.executeUpdate();
			
		} finally {
			stmt.close();			
		}
		
		return row;
		
	}
}