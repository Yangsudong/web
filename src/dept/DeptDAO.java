package dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.ConnectionManager;

public class DeptDAO {
	//전역변수. 모든 메서드에서 공통으로 사용되는 변수
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//싱글톤
	static DeptDAO instance;
	public static DeptDAO getInstance() {
		if(instance==null)
		   instance=new DeptDAO();
		return instance;
	}
	
	public int count(DeptVO deptVO) {
		int cnt = 0;
		try {
			conn = ConnectionManager.getConnnect();
			String where = "where 1=1";
			if(deptVO.getDepartment_name() !=null) {
				where += "and department_name like '%' || ? ||  '%'";
			}
			
			String sql = "select count(*) from hr.departments " + where;
			pstmt = conn.prepareStatement(sql);
			
			int pos = 1;
			if(deptVO.getDepartment_name() !=null) {
				pstmt.setString(pos++, deptVO.getDepartment_name());
			}
			
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1); 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(conn);
		}
		return cnt;
	}
	
	//전체조회
	public ArrayList<DeptVO> selectAll(DeptVO deptVO) {
		DeptVO resultVO = null;
		ArrayList<DeptVO> list = new ArrayList<DeptVO>(); 
		
		try {
			conn = ConnectionManager.getConnnect();
			String where = "where 1=1";
			if(deptVO.getDepartment_name() !=null) {
				where += " and department_name like '%' || ? ||  '%'";
			}
			String sql = " SELECT A.* FROM( SELECT  B.*, ROWNUM RM FROM("
					   + " SELECT DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID MGR_ID, LOCATION_ID"
					   + " FROM HR.DEPARTMENTS "
					   +   where
					   + " ORDER BY DEPARTMENT_ID "
					   + " ) B) A WHERE RM BETWEEN ? AND ?";
			
			pstmt = conn.prepareStatement(sql);			
			int pos = 1;
			if(deptVO.getDepartment_name() != null) {
				pstmt.setString(pos++, deptVO.getDepartment_name());
			}
			
			pstmt.setInt(pos++, deptVO.getFirst());
			pstmt.setInt(pos++, deptVO.getLast());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultVO = new DeptVO();
				resultVO.setDepartment_id(rs.getInt(1));
				resultVO.setDepartment_name(rs.getString(2));
				resultVO.setManager_id(rs.getInt(3));
				resultVO.setLocation_id(rs.getInt(4));
				list.add(resultVO);
			} 
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		return list;
	}
	
	
	//단건조회
	public DeptVO selectOne(DeptVO deptVO) {
		DeptVO resultVO = null;
		
		try {
			conn = ConnectionManager.getConnnect();
			String sql = " SELECT DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID mgr_id, LOCATION_ID"
					   + " FROM HR.DEPARTMENTS"
					   + " WHERE DEPARTMENT_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,deptVO.getDepartment_id());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultVO = new DeptVO();
				resultVO.setDepartment_id(rs.getInt(1));
				resultVO.setDepartment_name(rs.getString(2));
				resultVO.setManager_id(rs.getInt("mgr_id"));
				resultVO.setLocation_id(rs.getInt(4));
				
//				System.out.println("DEPARTMENT_ID : " + rs.getInt(1));
//				System.out.println("DEPARTMENT_NAME : " + rs.getString(2));
//				System.out.println("MANAGER_ID : " + rs.getInt(3));
//				System.out.println("LOCATION_ID : " + rs.getInt(4));
			} else {
				System.out.println("부서번호가 없습니다");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		return resultVO;
	}
	
	public void delete(DeptVO deptVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "DELETE FROM HR.DEPARTMENTS WHERE DEPARTMENT_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptVO.getDepartment_id());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 삭제됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		
	}
	
	public void update(DeptVO deptVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "UPDATE HR.DEPARTMENTS SET DEPARTMENT_NAME = ? WHERE DEPARTMENT_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptVO.getDepartment_name());
			pstmt.setInt(2, deptVO.getDepartment_id());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 입력됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		
	}
	
	public void insert(DeptVO deptVO) {
		try {
			//1.DB연결
		 conn = ConnectionManager.getConnnect();
			
			//2.sql 구문 실행
			String sql = "INSERT INTO HR.DEPARTMENTS (DEPARTMENT_ID, DEPARTMENT_NAME, LOCATION_ID, MANAGER_ID)" 
						+ " values (?,?,?,?)";
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, deptVO.getDepartment_id());
			pstmt.setString(2, deptVO.getDepartment_name());
			pstmt.setInt(3, deptVO.getLocation_id());
			pstmt.setInt(4, deptVO.getManager_id());			
			int r = pstmt.executeUpdate();
			
			//3.결과처리
			System.out.println(r + "건이 처리됨");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//4.연결해제
			ConnectionManager.close(null, pstmt, conn);
		}
		
	}
}
