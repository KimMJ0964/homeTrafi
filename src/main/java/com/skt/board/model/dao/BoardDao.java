package com.skt.board.model.dao;


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

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.skt.board.model.vo.Board;
import com.skt.board.model.vo.BoardComment;
import com.skt.board.model.vo.BoardFile;
import com.skt.common.Template;

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
	
	public int selectListCount(SqlSession sqlSession) {
		return sqlSession.selectOne("boardMapper.selectListCount");
	}
	
	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi) {
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return (ArrayList)sqlSession.selectList("boardMapper.selectList", null, rowBounds);
	}
	
	public int increaseCount(SqlSession sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.increaseCount", boardNo);
		// 업데이트되면 1출력
	}
	
	public Board selectBoard(SqlSession sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
	}
	
	public ArrayList<Board> selectMyPageBoardList(SqlSession sqlSession, String loginId) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectMyPageBoardList", loginId);
	}
	
	public int insertBoard(SqlSession sqlSession, Board b) {
		return sqlSession.insert("boardMapper.insertBoard", b);
	}
	
	public int insertBoardFile(SqlSession sqlSession, BoardFile bf) {
		return sqlSession.insert("boardMapper.insertBoardFile", bf);
	}
	
	public int insertComment(SqlSession sqlSession, BoardComment boardComment) {
		return sqlSession.insert("boardMapper.insertComment", boardComment);
	}
	
	public int deleteBoard(SqlSession sqlSession, int boardNo ) {
		return sqlSession.update("boardMapper.deleteBoard", boardNo); 
	}
	
	public int updateBoard(SqlSession sqlSession, Board b) {
		System.out.println("updateBoard" + b);
		return sqlSession.update("boardMapper.updateBoard", b);
	}
	
	public int updateBoardFile(SqlSession sqlSession, BoardFile bf) {
		System.out.println("updateBoardFile" + bf);
		return sqlSession.update("boardMapper.updateBoardFile", bf);
	}
	
	public ArrayList<BoardComment> commentList(SqlSession sqlSession, int boardNo) {
		return (ArrayList)sqlSession.selectList("boardMapper.commentList", boardNo);
	}
	
	// ---------------------- 마이바티스 --------------------------------------------
}
