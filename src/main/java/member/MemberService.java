package member;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.*;

import java.sql.Connection;

public class MemberService {
	 private MemberDao memberDao = new MemberDao();

	    // 사용자 정보 업데이트 로직
	    public int updateMember(Member member) {
	    	Connection conn = getConnection();
	        int result = memberDao.updateMember(conn, member);

	        // 트랜잭션 처리
	        if (result > 0) {
	            commit(conn);
	        } else {
	            rollback(conn);
	        }

	        close(conn);
	        return result;
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
