package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.ConnectionManager;

public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//전체조회
	public ArrayList<MemberVO> selectAll(MemberVO memberVO) {
		MemberVO resultVO = null;
		ArrayList<MemberVO> list = new ArrayList<MemberVO>(); 
		
		try {
			conn = ConnectionManager.getConnnect();
			String sql = " SELECT ID, PASS, GENDER, JOB, MAILYN, REASON"
					   + " FROM MEMBERS"
					   + " ORDER BY ID ";
			
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultVO = new MemberVO();
				resultVO.setId(rs.getString(1));
				resultVO.setPass(rs.getString(2));
				resultVO.setGender(rs.getString(3));
				resultVO.setJob(rs.getString(4));
				resultVO.setMailYN(rs.getString(5));
				resultVO.setReason(rs.getString(6));
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
	public MemberVO selectOne(MemberVO memberVO) {
		MemberVO resultVO = null;
		
		try {
			conn = ConnectionManager.getConnnect();
			String sql = " SELECT ID, PASS, GENDER, JOB, MAILYN, REASON"
					   + " FROM MEMBERS"
					   + " WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memberVO.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultVO = new MemberVO();
				resultVO.setId(rs.getString(1));
				resultVO.setPass(rs.getString(2));
				resultVO.setGender(rs.getString(3));
				resultVO.setJob(rs.getString(4));
				resultVO.setMailYN(rs.getString(5));
				resultVO.setReason(rs.getString(6));
				
			} else {
				System.out.println("아이디가 없습니다");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		return resultVO;
	}
	
	public void delete(MemberVO memberVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "delete from members where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberVO.getId());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 삭제됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		
	}
	
	public void update(MemberVO memberVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "update members set pass = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberVO.getPass());
			pstmt.setString(2, memberVO.getId());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 입력됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		
	}
	
	public void insert(MemberVO memberVO) {
		try {
			//1.DB연결
		 conn = ConnectionManager.getConnnect();
			
			//2.sql 구문 실행
			String sql = "insert into members values(?,?,?,?,?,?)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getPass());	
			pstmt.setString(3, memberVO.getGender());	
			pstmt.setString(4, memberVO.getJob());	
			pstmt.setString(5, memberVO.getMailYN());	
			pstmt.setString(6, memberVO.getReason());	
			int r = pstmt.executeUpdate(sql);
			
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
