package com.skt.board.service;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.skt.board.model.dao.BoardDao;
import com.skt.board.model.vo.Board;
import com.skt.board.model.vo.BoardComment;
import com.skt.board.model.vo.BoardFile;

import common.PageInfo;
import common.Template;
import jakarta.servlet.http.HttpServletRequest;


public class BoardServiceImpl implements BoardService{
	private BoardDao bDao = new BoardDao();
	
	@Override
	public int selectListCount() {
		SqlSession sqlSession = Template.getSqlSession();
		int listCount = bDao.selectListCount(sqlSession);
		
		sqlSession.close();
		return listCount;
	}
	
	@Override
	public ArrayList<Board> selectList(PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Board> list = bDao.selectList(sqlSession, pi);
		
		sqlSession.close();
		
		return list;
	}
	
	@Override
	public Board increaseCount(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = bDao.increaseCount(sqlSession, boardNo);
		
		Board b = null;
		if (result > 0) {
			sqlSession.commit();
			b = bDao.selectBoard(sqlSession, boardNo);
		} else {
			sqlSession.rollback();
		}
		
		return b;
	}
	
	@Override
	public ArrayList<Board> selectMyPageBoardList(String loginId) {
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Board> list = bDao.selectMyPageBoardList(sqlSession, loginId);
		
		sqlSession.close();
		
		return list;
		
	}
	
	@Override
	public int insertBoard(Board b, BoardFile bf) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result1 = bDao.insertBoard(sqlSession, b);
		int result2 = 1;
		
		if(bf != null) {
			result2 = bDao.insertBoardFile(sqlSession, bf);
		}
		
		if(result1 > 0 && result2 > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		return result1 *  result2;
	}
	
	@Override
	public int insertComment(BoardComment boardComment) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = bDao.insertComment(sqlSession, boardComment);
		
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}
	
	public int deleteBoard(HttpServletRequest request, int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = bDao.deleteBoard(sqlSession, boardNo);
		
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return result;
	}
	
	public int updateBoard(Board b, BoardFile bf) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result1 = bDao.updateBoard(sqlSession, b);
		int result2 = 1;
		
		if(bf != null) {
			result2 = bDao.updateBoardFile(sqlSession, bf);
		}
		
		if(result1 > 0 && result2 > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		return result1 *  result2;
	}
	
	public ArrayList<BoardComment> commentList(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<BoardComment> list = bDao.commentList(sqlSession, boardNo);
		
		sqlSession.close();
		
		return list;
	}
	
	// ----------------- 마이바티스 이전 ------------------------
}
