package boardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.ConnectionManager;

public class BoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//전체조회
	public ArrayList<BoardVO> selectAll(BoardVO boardVO) {
		BoardVO resultVO = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>(); 
		
		try {
			conn = ConnectionManager.getConnnect();
			String sql = " SELECT NO, POSTER, SUBJECT, CONTENTS, LASTPOST, VIEWS, FILENUMBER"
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
				resultVO.setLastpost(rs.getDate(5));
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
					   + " FROM MEMBERS"
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
				resultVO.setLastpost(rs.getDate(5));
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
		 String sql = "INSERT INTO BOARD VALUES(?,?,?,?,?,?,?)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardVO.getNo());
			pstmt.setString(2, boardVO.getPoster());	
			pstmt.setString(3, boardVO.getSubject());	
			pstmt.setString(4, boardVO.getContents());	
			pstmt.setDate(5, boardVO.getLastpost());	
			pstmt.setInt(6, boardVO.getViews());	
			pstmt.setString(7, boardVO.getFilename());
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

