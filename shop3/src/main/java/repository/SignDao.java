package repository;

import java.sql.*;

public class SignDao {

	
		public String idCheck(Connection conn ,String id) throws Exception {
			String ckId = null;
			ResultSet rset= null;
			PreparedStatement stmt = null;
			
			String sql = "SELECT t.id\r\n"
					+ "from (SELECT customer_id id FROM customer\r\n"
					+ "UNION\r\n"
					+ "SELECT employee_id id FROM employee\r\n"
					+ "UNION\r\n"
					+ "SELECT out_id id FROM outid) t\r\n"
					+ "WHERE t.id = ?";
		
			
			System.out.println(id);
			
			
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,id);
				rset = stmt.executeQuery();
				if(rset==null) {
						rset.close();
						stmt.close();
							
				}
				else if(rset.next()) {
					ckId= rset.getString("id");
					rset.close();
					stmt.close();
				}
			
			return ckId;
		}
}