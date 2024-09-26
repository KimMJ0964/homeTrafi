package com.skt.member.service;
import static com.skt.common.JDBCTemplate.*;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;

import com.skt.common.Template;
import com.skt.member.model.dao.MemberDao;
import com.skt.member.model.vo.Member;

public class MemberServiceImpl {
	 private MemberDao memberDao = new MemberDao();

	    // 사용자 정보 업데이트 로직
	 	public Member updateMember(Member member) {
	 		SqlSession sqlSession = Template.getSqlSession();
	 		System.out.println("Service Member : " + member);
	 		Member updateUser = memberDao.updateMember(sqlSession, member);
			
			sqlSession.close();
			System.out.println("updateUser : " + updateUser);
			return updateUser;
	    }
	    
	    // 회원 정보 가져오기
	    public Member getMemberById(String memId) {
	        Connection conn = getConnection();
	        Member member = memberDao.getMemberById(conn, memId);
	        close(conn);
	        return member;
	    }

	    // 회원 탈퇴
	    public int deleteMember(String memId) {
	        Connection conn = getConnection();
	        int result = memberDao.deactivateMember(conn, memId);

	        if (result > 0) {
	            commit(conn);
	        } else {
	            rollback(conn);
	        }
	        close(conn);
	        return result;
	    }
	    
	    // 비밀번호 변경
	    public int updatePassword(String memId, String newPwd) {
	        Connection conn = getConnection();
	        int result = memberDao.updatePassword(conn, memId, newPwd);

	        if (result > 0) {
	            commit(conn);
	        } else {
	            rollback(conn);
	        }
	        close(conn);
	        return result;
	    }
}
