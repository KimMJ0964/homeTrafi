package com.skt.board.service;

import java.util.ArrayList;

import com.skt.board.model.vo.Board;
import com.skt.board.model.vo.BoardComment;
import com.skt.board.model.vo.BoardFile;

import common.PageInfo;

public interface BoardService {
	//게시판리스트 조회
		public int selectListCount();
		
		public ArrayList<Board> selectList(PageInfo pi);
		
		// 게시글 상세조회
		public Board increaseCount(int boardNo);
		
		public ArrayList<Board> selectMyPageBoardList(String loginId);
		
		public int insertBoard(Board b, BoardFile bf);
		
		public int insertComment(BoardComment boardComment);
//		
//		public ArrayList<Reply> selectReplyList(int boardNo);
}
