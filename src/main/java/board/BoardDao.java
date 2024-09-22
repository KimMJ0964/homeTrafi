package board;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import common.JDBCTemplate;
import common.PageInfo;

public class BoardDao {
private Properties prop = new Properties();
	
	public BoardDao() {
		String filePath = BoardDao.class.getResource("/db/sql/board-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectListCount(Connection conn) {
		// select -> resultset(한행) -> int
		
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	
	public ArrayList<Board> selectList(Connection conn, PageInfo pi) {
		// Select -> ResultSet(여러행) -> ArrayList<Board>
		
		ArrayList<Board> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			/*
			 * currentPage : 1 -> 1~10
			 * currentPage : 2 -> 11~20
			 * currentPage : 3 -> 21~30 
			 */
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				b.setCommNo(rset.getInt("comm_no"));
				b.setMemId(rset.getString("mem_id"));
				b.setTitle(rset.getString("title"));
				b.setViewCount(rset.getInt("view_count"));
				b.setLikeCount(rset.getInt("like_count"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int createBoard(Connection conn, String memId, String title, String content) {
//		String sql = prop.getProperty("createBoard");
		
		 String sql = "INSERT INTO COMMUNITY (COMM_NO, MEM_ID, TITLE, CONTENT, CREATE_DATE) "
	               + "VALUES (COMMUNITY_SEQ_NEW.NEXTVAL, ?, ?, ?, SYSDATE)";
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	    int rowsAffected = 0;

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, memId);
	        pstmt.setString(2, title);
	        pstmt.setString(3, content);
	        System.out.println(memId + ", " + title + ", " + content);
	        
	        rowsAffected = pstmt.executeUpdate();
	        
	        // 커밋
	        conn.commit();
	        
	        return rowsAffected; // 성공 시 반환
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // 롤백
	        try {
	            conn.rollback();
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	    } finally {
	        close(rset);
	        close(pstmt);
	    }
	    return rowsAffected;
    }
	
	public List<Board> getPostsByMemberId(String memId) {
	    List<Board> posts = new ArrayList<>();
	    String sql = "SELECT * FROM COMMUNITY WHERE MEM_ID = ?";

	    Connection conn = getConnection(); // 데이터베이스 연결
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, memId); // 매개변수 설정
	        rs = pstmt.executeQuery(); // 쿼리 실행

	        while (rs.next()) {
	            Board board = new Board();
	            board.setCommNo(rs.getInt("COMM_NO")); // 컬럼명 대문자 사용
	            board.setMemId(rs.getString("MEM_ID"));
	            board.setTitle(rs.getString("TITLE"));
	            board.setCreateDate(rs.getString("CREATE_DATE"));
	            posts.add(board);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(rs); // ResultSet 닫기
	        close(pstmt); // PreparedStatement 닫기
	        close(conn); // Connection 닫기
	    }
	    return posts;
	}

}
