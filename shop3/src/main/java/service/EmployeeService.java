package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import repository.EmployeeDao;
import repository.GoodsDao;
import repository.OutIdDao;
import vo.Customer;
import vo.Employee;
import vo.Goods;

public class EmployeeService {

	public boolean removeEmployee(Employee paramEmployee) {

		Connection conn = null;

		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); // executeUpdate() 실행 시 자동 커밋을 막음

			EmployeeDao employeeDao = new EmployeeDao();
			if (employeeDao.deleteEmployee(conn, paramEmployee) != 1) {
				throw new Exception();
			}
			OutIdDao OutIdDao = new OutIdDao();
			if (OutIdDao.insertOutId(conn, paramEmployee.getEmployeeId()) != 1) {

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

	public Employee loginEmployee(Employee paramEmployee) {
		Connection conn = null;

		Employee employee = new Employee();

		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);

			EmployeeDao employeeDao = new EmployeeDao();
			employee = employeeDao.selectEmployee(conn, paramEmployee);

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
		return employee;

	}

	public boolean addEmpolyee(Employee paramEmpolyee) {
		Connection conn = null;

		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);// executeUpdate() 실행시 자동 커밋을 막음

			EmployeeDao employeeDao = new EmployeeDao();
			employeeDao.insertEmployee(conn, paramEmpolyee);

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace(); // console에 예외메세지 출력
			try {
				conn.rollback();
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
	
	// 활동여부
			public boolean modifyEmployeeActive(Employee employee) {
				Connection conn = null;
				boolean result = false;
				int row = 0;
				
				try {
					conn = new DBUtil().getConnection();
					conn.setAutoCommit(false); // executeUpdate() 실행 시 자동 커밋을 막음
					
					EmployeeDao employeeDao = new EmployeeDao();
					row = employeeDao.updateEmployeeActive(conn, employee);
					
					if(row != 1) { // 쿼리문이 정상적으로 적용되었는지 확인 후 아닐 시 예외처리
						throw new Exception();
					} else {
						result = true;
					}		
					conn.commit();		
				} catch (Exception e) {
					e.printStackTrace(); // console에 예외메세지 출력
					try {
						conn.rollback(); // 예외를 던지지말고 감싸야함
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return result;
			}
	
	
	public ArrayList<Employee> getEmployeeList(int rowPerPage, int currentPage) {

		ArrayList<Employee> list = new ArrayList<Employee>();
		Connection conn = null;
		int beginRow = (currentPage -1 ) * rowPerPage;
		
		
		
		try {
			conn = new DBUtil().getConnection();
			EmployeeDao employeeDao = new EmployeeDao();
			
			list = employeeDao.SelectEmployeeList(conn, rowPerPage, beginRow);
			
			System.out.println(list +"사원목록");
			
			if(list==null) {
				throw new Exception();
			}
				conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
		return list;
		
	}
	
	public int getEmployeeLastPage(int rowPerPage) {
		
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); 
			 
			EmployeeDao employeeDao = new EmployeeDao(); 
			rowPerPage = employeeDao.EmployeelastPage(conn);
			System.out.print(rowPerPage +" : rowPerPage");
		
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
					
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rowPerPage;	
		
		
	}

}
