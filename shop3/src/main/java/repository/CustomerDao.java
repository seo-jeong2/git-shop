package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import service.DBUtil;
import vo.Customer;

public class CustomerDao { 
		 
	// 탈퇴
	 public int deleteCustomer(Connection conn, Customer paramCustomer) throws SQLException {  // 동일한 Connection 사용
		// insertOutId와 동일한 conn 사용하므로 conn.close() X
		 int row = 0;
		 String sql =" DELETE FROM customer WHERE customer_id=? AND customer_pass=PASSWORD(?)";
		 PreparedStatement stmt = null;
		 
		 try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramCustomer.getCustomerId());			
			stmt.setString(2, paramCustomer.getCustomerPass());
			row = stmt.executeUpdate();
			
		 } finally {
			stmt.close();
		 }
 		 
		 return row;

	 }

	 // 로그인
	 public Customer selectCustomer(Connection conn, Customer paramcustomer) throws Exception {
			Customer loginCustomer = null;
			
			String sql ="SELECT customer_id customerId,customer_pass customerPass,customer_name customerName FROM customer WHERE customer_id=? AND customer_pass=PASSWORD(?)";
			PreparedStatement stmt = null;
			ResultSet rset = null;
			
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, paramcustomer.getCustomerId());
				stmt.setString(2, paramcustomer.getCustomerPass());
				rset = stmt.executeQuery();

				if (rset.next()) {
					loginCustomer = new Customer();
					loginCustomer.setCustomerId(rset.getString("customerId"));
					loginCustomer.setCustomerPass(rset.getString("customerPass"));
					loginCustomer.setCustomerName(rset.getString("customerName"));

				}
			} finally {
				stmt.close();
				rset.close();
			}
			return loginCustomer;
	 }
	 
	// insert
		public int insertCustomer(Connection conn,Customer customer) throws Exception {
			int row =0;
			
			String sql = "insert into customer (customer_id,customer_pass,customer_name,customer_address,customer_telephone,update_date,create_date) values(?,password(?),?,?,?,now(),now())";
			
			
			PreparedStatement stmt = null;
			
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,customer.getCustomerId());
				stmt.setString(2, customer.getCustomerPass());
				stmt.setString(3, customer.getCustomerName());
				stmt.setString(4, customer.getCustomerAddress());
				stmt.setString(5, customer.getCustomerTelephone());
				
				row= stmt.executeUpdate();
				
			
			}finally {
				if(stmt!=null) {stmt.close();}	
			}
			return row;
		}
}