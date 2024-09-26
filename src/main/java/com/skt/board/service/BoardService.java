package com.skt.board.service;

import static com.skt.common.JDBCTemplate.*;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.skt.board.model.dao.BoardDao;
import com.skt.board.model.dao.BoardFileDao;
import com.skt.board.model.vo.Board;
import com.skt.board.model.vo.BoardFile;
import com.skt.common.PageInfo;

import jakarta.servlet.http.HttpServletRequest;


public class BoardService {
	
	public int selectListCount() {
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCount(conn);
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> selectList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectList(conn, pi);
		close(conn);
		
		return list;
	}
	
	public int createBoard(String memId, String title, String content, String postType) {
		Connection conn = getConnection();
        
        // DAO를 통해 DB에 게시글 생성 요청
        int isCreate = 0;
        isCreate = new BoardDao().createBoard(conn, memId, title, content, postType);
        
        return isCreate;
    }
	
	public List<Board> getUserPosts(String memId) {
        return new BoardDao().getPostsByMemberId(memId);
    }
	
	public Board increaseCount(int boardNo) {
		Connection conn = getConnection();
		
		BoardDao bDao = new BoardDao();
		int result = bDao.increaseCount(conn, boardNo);
		
		Board b = null;
		if(result > 0) {
			commit(conn);
			b = bDao.selectBoard(conn, boardNo);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return b;
	}
	
	public int insertBoard(Board b, BoardFile bf) {
		Connection conn = getConnection();
		BoardDao bDao = new BoardDao();
		
		int result1 = bDao.insertBoard(conn, b);
		int result2 = 1;
		
		if(bf != null) {
			result2 = bDao.insertBoardFile(conn, bf);
		}
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result1 *  result2;
	}
	
	public int deleteBoard(HttpServletRequest request, int boardNo) {
		Connection conn = getConnection();
		BoardDao bDao = new BoardDao();
		
		int result = bDao.deleteBoard(conn, boardNo);
		String[] result3 = bDao.deleteBoardFileTwo(conn, boardNo);
		int result2 = bDao.deleteBoardFile(conn, boardNo);
		
		String changeName = result3[0]; // 변경된 파일명
        String filePath = result3[1];   // 파일 경로
         
        String savePath = request.getServletContext().getRealPath("/" + filePath);
        
        System.out.println(savePath + changeName);
         // 서버에서 파일 삭제
        File file = new File(savePath + changeName);
		
		if(result > 0) {
			 if (file.exists()) {
                 if (file.delete()) {
                     System.out.println("파일 삭제 성공: " + savePath + changeName);
                 } else {
                     System.out.println("파일 삭제 실패: " + savePath + changeName);
                 }
             } else {
                 System.out.println("파일이 존재하지 않습니다: " + savePath + changeName);
             }
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
	
		return result;
	}
	
	public BoardFile fileDownload(int boardNo) {
		Connection conn = getConnection();
		BoardFileDao bfDao = new BoardFileDao();
		
		BoardFile bf = bfDao.fileDownload(conn, boardNo);
	}
}
