package com.skt.member.service;
import static com.skt.common.JDBCTemplate.close;
import static com.skt.common.JDBCTemplate.commit;
import static com.skt.common.JDBCTemplate.getConnection;
import static com.skt.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.skt.board.model.vo.Board;
import com.skt.board.model.vo.BoardComment;
import com.skt.common.Template;
import com.skt.member.model.dao.MemberDao;
import com.skt.member.model.vo.Member;

public class MemberServiceImpl {
	 private MemberDao memberDao = new MemberDao();

	    // 사용자 정보 업데이트 로직
	 	public int updateMember(Member member) {
	 		SqlSession sqlSession = Template.getSqlSession();
	 		
	 		int updateUser = memberDao.updateMember(sqlSession, member);
	 		if (updateUser > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
	 		
			sqlSession.close();

			return updateUser;
	    }
	    
	    // 비밀번호 변경
	    public int updatePassword(Member member) {
	    	SqlSession sqlSession = Template.getSqlSession();
	 		
	 		int updateUser = memberDao.updatePassword(sqlSession, member);
	 		if (updateUser > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
	 		
			sqlSession.close();

			return updateUser;
	    }
	    
	    public String selectPasswordByMemId(String memId) {
	    	SqlSession sqlSession = Template.getSqlSession();
	    	String result = memberDao.selectPasswordByMemId(sqlSession, memId);
	    	
	    	return result;
	    }
	    
	    public ArrayList<Board> myPageBoard(String memId) {
	    	SqlSession sqlSession = Template.getSqlSession();
			ArrayList<Board> list = memberDao.myPageBoard(sqlSession, memId);
			
			sqlSession.close();
			
			return list;
	    }

	    public ArrayList<BoardComment> myPageComment(String memId) {
	    	SqlSession sqlSession = Template.getSqlSession();
			ArrayList<BoardComment> list = memberDao.myPageComment(sqlSession, memId);
			
			sqlSession.close();
			
			return list;
	    }
	    
	    public Member getMemberById(String memId) {
	        SqlSession sqlSession = Template.getSqlSession();
	        Member member = null;
	        try {
	            member = memberDao.getMemberById(sqlSession, memId);
	        } finally {
	            sqlSession.close();
	        }
	        
	        sqlSession.commit();
	        
	        return member;
	    }
	    
	    // 회원 삭제
	    public int deleteMember(String memId) {
	        SqlSession sqlSession = Template.getSqlSession();
	        int result = 0;
	        try {
	            result = memberDao.deleteMember(sqlSession, memId);
	            sqlSession.commit(); // 변경 사항 커밋
	        } finally {
	            sqlSession.close();
	        }
	        
	        sqlSession.commit();
	        
	        return result;
	    }
}
