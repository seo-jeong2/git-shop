package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Customer;
import vo.Employee;

public class EmployeeDao {

	
	// 사원 active 변경
		public int updateEmployeeActive(Connection conn, Employee employee) throws SQLException {
			int row = 0;
			String sql = "UPDATE employee SET active = ? WHERE employee_id = ?";
			PreparedStatement stmt = null;

			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, employee.getActive());
				stmt.setString(2, employee.getEmployeeId());
				
				row = stmt.executeUpdate();
			}finally {
				if(stmt!=null) {
					stmt.close();
				}
			}
			return row;
		}
	
	
	
	// 탈퇴
	public int deleteEmployee(Connection conn, Employee paramEmployee) throws SQLException {

		int row = 0;
		String sql = "DELETE FROM employee WHERE employee_id=? AND employee_pass=PASSWORD(?)";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramEmployee.getEmployeeId());
			stmt.setString(2, paramEmployee.getEmployeePass());
			row = stmt.executeUpdate();
		} finally {
			stmt.close();
		}
		return row;
	}

	// 로그인
	public Employee selectEmployee(Connection conn, Employee paramemployee) throws SQLException {
		Employee loginEmployee = null;

		String sql = "SELECT employee_id employeeId,employee_pass employeePass, employee_name employeeName FROM employee WHERE employee_id=? AND employee_pass=PASSWORD(?) AND active='Y'";
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramemployee.getEmployeeId());
			stmt.setString(2, paramemployee.getEmployeePass());
			rset = stmt.executeQuery();

			if (rset.next()) {
				loginEmployee = new Employee();
				loginEmployee.setEmployeeId(rset.getString("employeeId"));
				loginEmployee.setEmployeePass(rset.getString("employeePass"));
				loginEmployee.setEmployeeName(rset.getString("employeeName"));

			}

		} finally {
			stmt.close();
			rset.close();
		}

		return loginEmployee;
	}

	public int insertEmployee(Connection conn, Employee employee) throws Exception {
		int row = 0;

		String sql = "insert into employee (employee_id,employee_pass,employee_name,update_date,create_date) values(?,password(?),?,now(),now())";

		PreparedStatement stmt = null;

		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, employee.getEmployeeId());
			stmt.setString(2, employee.getEmployeePass());
			stmt.setString(3, employee.getEmployeeName());

			row = stmt.executeUpdate();

		} finally {
			if (stmt != null) { stmt.close();
			}
		}
		return row;
	}
	
	/*
	public List<Employee> selectEmployeeList(Connection conn, int rowPerPage, int beginRow) throws Exception{
		ArrayList<Employee> employeeList = new ArrayList<>();
		ResultSet rs =  null;
		PreparedStatement stmt = null;
		
		try {
			String sql = "select employee_id employeeId, employee_pass employeePw, employee_name employeeName, update_date updateDate, create_date createDate, active from employee limit ?,?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Employee e = new Employee();
				e.setEmployeeId(rs.getString("employeeId"));
				e.setEmployeePass(rs.getString("employeePw"));
				e.setEmployeeName(rs.getString("employeeName"));
				e.setUpdateDate(rs.getString("updateDate"));
				e.setCreateDate(rs.getString("createDate"));
				e.setActive(rs.getString("active"));
				
				employeeList.add(e);
			}
		
		} finally {
			if(rs != null) {
				rs.close();
			}
			
			if(stmt != null) {
				stmt.close();
			}
		}
		return employeeList;
	}
	*/
	public int countAllEmployee(Connection conn) throws Exception {
		int lastPage = 0;
		String sql = "SELECT COUNT(*) cnt FROM employee";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if(rs.next()) {
				lastPage = rs.getInt("cnt");
			}
			
		} finally {
			
			if(rs != null) {
				rs.close();
			}
			
			if(stmt != null) {
				stmt.close();
			}
		}
				
		return lastPage;
	}
	
	// 리스트
	
public int EmployeelastPage(Connection conn) throws SQLException {
		
		String sql ="SELECT COUNT(*) FROM employee";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt("COUNT(*)");
			}
			
			System.out.print(totalCount +"Employee의 dao");
			
		}finally {
			
			if(rs!=null) {rs.close();}
			if(rs!=null) {stmt.close();}
		}
		
		return totalCount;
		
	}
	
	

	// 사원 상세보기
	// 그 테이블의 값들을 전부 가져와야함
	public ArrayList<Employee> SelectEmployeeList(Connection conn, int rowPerPage, int beginRow) throws SQLException  {
		ArrayList<Employee> list = new ArrayList<Employee>();
		
		/*
		 SELECT 
		 employee_id,
		 employee_pass,
		 employee_name,
		 update_date,
		 create_date,
		 active
		 FROM employee
		 ORDER BY crate_date DESC LIMIT ?, ?;
		 */
		
		
	

		
		String sql =" SELECT \r\n"
				+ "		 employee_id,\r\n"
				+ "		 employee_pass,\r\n"
				+ "		 employee_name,\r\n"
				+ "		 update_date,\r\n"
				+ "		 create_date,\r\n"
				+ "		 active\r\n"
				+ "		 FROM employee\r\n"
				+ "		 ORDER BY create_date DESC LIMIT ?, ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getString("employee_id"));
				employee.setEmployeePass(rs.getString("employee_pass"));
				employee.setEmployeeName(rs.getString("employee_name"));
				employee.setUpdateDate(rs.getString("update_date"));
				employee.setCreateDate(rs.getString("create_date"));
				employee.setActive(rs.getString("active"));
				list.add(employee);
				
				System.out.println(list+ "<- Employee의 list<-");
			}
			
		} finally {
			if(rs!=null) {rs.close();}  			
			if(stmt!=null) {stmt.close();}
		}
		return list;
		
	}
	
	
	
}