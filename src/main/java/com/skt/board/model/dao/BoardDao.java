package com.skt.board.model.dao;

import static com.skt.common.JDBCTemplate.close;
import static com.skt.common.JDBCTemplate.getConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.skt.board.model.vo.Board;
import com.skt.board.model.vo.BoardFile;
import com.skt.common.PageInfo;

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
				b.setCreateDate(rset.getString("create_date"));
				b.setType(rset.getString("type"));
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
	
	public int createBoard(Connection conn, String memId, String title, String content, String postType) {
//		String sql = prop.getProperty("createBoard");
		
		 String sql = "INSERT INTO COMMUNITY (COMM_NO, MEM_ID, TITLE, CONTENT, TYPE, CREATE_DATE) "
	               + "VALUES (COMMUNITY_SEQ_NEW.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	    int rowsAffected = 0;

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, memId);
	        pstmt.setString(2, title);
	        pstmt.setString(3, content);
	        pstmt.setString(4, postType);
	        System.out.println(memId + ", " + title + ", " + content + ", " + postType);
	        
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
	            Date createDate = rs.getDate("CREATE_DATE");
	            if (createDate != null) {
	                SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd"); // 원하는 형식으로 설정
	                String formattedDate = sdf.format(createDate);
	                board.setCreateDate(formattedDate); // String으로 설정
	            } else {
	                board.setCreateDate(null); // null 처리
	            }
	            posts.add(board);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(rs); // ResultSet 닫기
	        close(pstmt); // PreparedStatement 닫기
	    }
	    return posts;
	}
	
	public int increaseCount(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public Board selectBoard(Connection conn, int boardNo) {
		ResultSet rset = null;
		Board b = null;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				b = new Board(
						rset.getInt("comm_no"),
						rset.getString("title"),
						rset.getString("mem_id"),
						rset.getString("create_date"),
						rset.getInt("view_count"),
						rset.getInt("like_count"),
						rset.getString("content"),
						rset.getString("type")
					);
			}
			System.out.println("확인" + b);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return b;
	}
	
	public int insertBoard(Connection conn, Board b) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO COMMUNITY (COMM_NO, TITLE, CONTENT, TYPE, MEM_ID, CREATE_DATE) "
	               + "VALUES (COMMUNITY_SEQ_NEW.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setString(3, b.getType());
			pstmt.setString(4, b.getMemId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertBoardFile(Connection conn, BoardFile bf) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBoardFile");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bf.getOriginName());
			pstmt.setString(2, bf.getChangeName());
			pstmt.setString(3, bf.getFilePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteBoard(Connection conn, int boardNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteBoardFile(Connection conn, int boardNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoardFile");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String[] deleteBoardFileTwo(Connection conn, int boardNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
        String[] fileDetails = new String[2];
		String sql = prop.getProperty("deleteBoardFileSelect");
		
		try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo); // 게시글 번호로 파일 조회
            rset = pstmt.executeQuery();

            if (rset.next()) {
                fileDetails[0] = rset.getString("CHANGE_NAME"); // 변경된 파일명
                fileDetails[1] = rset.getString("FILE_PATH");   // 파일 경로
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) rset.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return fileDetails; // 파일명과 경로 반환
	}

}
