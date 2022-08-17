package service;

import java.sql.*;
import java.util.*;
import repository.*;
import vo.*;


// 서비스가 하는 일: 트랜잭션 처리 ★★★★★★★ + action, Dao가 해서는 안되는 일 처리
public class GoodsService {


	private GoodsImgDao goodsImgDao;
	private GoodsDao goodsDao;	
		

	
	public int addGoods(Goods goods, GoodsImg goodsImg) {
		int goodsNo = 0;
		Connection conn = null;
		
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			this.goodsDao = new GoodsDao();
			
			goodsNo = goodsDao.insertGoods(conn, goods);
			// 방금 insert한 goods_no 리턴 -> action의 redirection에 씀

			System.out.println("goodsDao.insertGoods(): " + goodsNo);
			
			if(goodsNo != 0) { // goods insert에 성공한 경우
				goodsImg.setGoodsNo(goodsNo);
				this.goodsImgDao = new GoodsImgDao(); // 모든 메소드에서 쓰는 dao가 아니므로
				
				if(goodsImgDao.insertGoodsImg(conn, goodsImg) == 0) {
					goodsNo = 0; // 실패 시 goodsNo 다시 0
					System.out.println("goodsImgDao.insertGoods: " + goodsNo);

					throw new Exception();
					// 이미지 입력 실패 시 catch 절로 이동 -> rollBack
				}
			}
			
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
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
		
		return goodsNo;
	}
	
	
	public Map<String, Object> getGoodsAndImgOne(int goodsNo) {
		Map<String,Object> map = null;
		
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			GoodsDao goodsDao = new GoodsDao(); 	
			map = goodsDao.selectGoodsAndImgOne(conn, goodsNo);
			
			System.out.print(map +"<-getGoodsAndImgOne map");
			conn.commit();
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return map;
	}
	
	
	// 상품 품절 변경
		public boolean modifyGoodsSoldOut(Goods goods) {
			Connection conn = null;
			boolean result = false;
			int row = 0;
			
			try {
				conn = new DBUtil().getConnection();
				conn.setAutoCommit(false); // executeUpdate() 실행 시 자동 커밋을 막음
				
				this.goodsDao = new GoodsDao();	
				row = goodsDao.updateGoodsSoldOut(conn, goods);
				
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
	public int getLastPage(int rowPerPage) {
		
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); 
			 
			GoodsDao goodsDao = new GoodsDao(); 
			rowPerPage = goodsDao.lastPage(conn);
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


	public List<Goods> getGoodsListByPage(int rowPerPage, int currentPage) {

		Connection conn = null;
		int beginRow = (currentPage - 1) * rowPerPage;

		List<Goods> list = new ArrayList<Goods>();
		this.goodsDao = new GoodsDao();

		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);

			list = goodsDao.selectGoodsListByPage(conn, rowPerPage, beginRow);

		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	
}
