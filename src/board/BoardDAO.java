package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import common.ConnectionManager;

public class BoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//싱글톤
		static BoardDAO instance;
		public static BoardDAO getInstance() {
			if(instance==null)
			   instance=new BoardDAO();
			return instance;
		}
	
	//전체조회
	public ArrayList<BoardVO> selectAll(BoardVO boardVO) {
		BoardVO resultVO = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>(); 
		
		try {
			conn = ConnectionManager.getConnnect();
			String sql = " SELECT NO, POSTER, SUBJECT, CONTENTS, LASTPOST, VIEWS, FILENAME"
					   + " FROM BOARD"
					   + " ORDER BY NO ";
			
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultVO = new BoardVO();
				resultVO.setNo(rs.getInt(1));
				resultVO.setPoster(rs.getString(2));
				resultVO.setSubject(rs.getString(3));
				resultVO.setContents(rs.getString(4));
				resultVO.setLastpost(rs.getString(5));
				resultVO.setViews(rs.getInt(6));
				resultVO.setFilename(rs.getString(7));
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
	public BoardVO selectOne(BoardVO boardVO) {
		BoardVO resultVO = null;
		
		try {
			conn = ConnectionManager.getConnnect();
			String sql = " SELECT NO, POSTER, SUBJECT, CONTENTS, LASTPOST, VIEWS, FILENAME"
					   + " FROM BOARD"
					   + " WHERE NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,boardVO.getNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultVO = new BoardVO();
				resultVO.setNo(rs.getInt(1));
				resultVO.setPoster(rs.getString(2));
				resultVO.setSubject(rs.getString(3));
				resultVO.setContents(rs.getString(4));
				resultVO.setLastpost(rs.getString(5));
				resultVO.setViews(rs.getInt(6));
				resultVO.setFilename(rs.getString(7));
				
			} else {
				System.out.println("번호가 없습니다");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		return resultVO;
	}
	
	public void delete(BoardVO boardVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "DELETE FROM BOARD WHERE NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardVO.getNo());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 삭제됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		
	}
	
	public void update(BoardVO boardVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "UPDATE BOARD SET SUBJECT = ? WHERE NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVO.getSubject());
			pstmt.setInt(2, boardVO.getNo());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 입력됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		
	}
	
	public void insert(BoardVO boardVO) {
		try {
			//1.DB연결
		 conn = ConnectionManager.getConnnect();
			
			//2.sql 구문 실행
		 //보드 번호 조회
		 String seqSql = "select no from seq where tablename = 'board'";
		 Statement stmt = conn.createStatement();
		 ResultSet rs = stmt.executeQuery(seqSql);
		 rs.next();
		 int no = rs.getInt(1);
		 boardVO.setNo(no);
		 
		 //보드번호 업데이트
		 seqSql = "update seq set no = no + 1 where tablename = 'board'";
		 stmt = conn.createStatement();
		 stmt.executeUpdate(seqSql);
		 
		 // 게시글 등록
		 String sql = "INSERT INTO BOARD (lastpost, contents, subject, poster, no, filename)"
		 				+ " values(sysdate,?,?,?,?,?)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVO.getPoster());
			pstmt.setString(2, boardVO.getSubject());	
			pstmt.setString(3, boardVO.getContents());	
			pstmt.setInt(4, no);
			pstmt.setString(5, boardVO.getFilename());	
			pstmt.executeUpdate();
			conn.commit();
			
		} catch (Exception e1) {
			try {
				conn.rollback();			
			}
			catch(SQLException e) {
				e.printStackTrace();
			} finally {
				//4.연결해제
				ConnectionManager.close(null, pstmt, conn);
			}
		}
	}
}

