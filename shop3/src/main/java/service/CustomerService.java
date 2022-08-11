package service;

import java.sql.Connection;
import java.sql.SQLException;

import repository.CustomerDao;
import repository.OutIdDao;
import vo.Customer;
import vo.OutId;

public class CustomerService {

	// 회원탈퇴 액션페이지에서 호출되는 메서드
	public boolean removeCustomer(Customer paramCustomer) {

		Connection conn = null;

		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); // executeUpdate() 실행 시 자동 커밋을 막음

			CustomerDao customerDao = new CustomerDao();
			if (customerDao.deleteCustomer(conn, paramCustomer) != 1) {
				throw new Exception();
			}
			OutIdDao OutIdDao = new OutIdDao();
			if (OutIdDao.insertOutId(conn, paramCustomer.getCustomerId()) != 1) {

				throw new Exception();
			}

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace(); // console에 예외메세지 출력
			try {
				conn.rollback(); // 1 or 2 실행 시 예외가 발생하면 현재 conn 실행쿼리 모두 롤백
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return true;
	}

	public Customer loginCustomer(Customer paramCustomer) {

		Connection conn = null;
		Customer customer = new Customer();

		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			// executeUpdate() 실행 시 자동 커밋을 막음

			CustomerDao customerDao = new CustomerDao();
			customer = customerDao.selectCustomer(conn, paramCustomer);

			conn.commit();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return customer;

	}

	public boolean addCustomer(Customer paramCustomer) {
		Connection conn = null;
		
		
		try{
		conn = new DBUtil().getConnection();
		conn.setAutoCommit(false);// executeUpdate() 실행시 자동 커밋을 막음
		
		CustomerDao customerDao= new CustomerDao();
		customerDao.insertCustomer(conn, paramCustomer);
		
	
		conn.commit();
		}catch(Exception e) {
			e.printStackTrace(); // console에 예외메세지 출력
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
		
	}

}
