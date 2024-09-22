package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static common.JDBCTemplate.*;

public class MemberDao {
    
	public Member getMemberById(String memId) {
    	Member member = null;
    	Connection conn = getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
        try {
            String sql = "SELECT * FROM MEMBER WHERE MEM_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                member = new Member();
                member.setMemId(rs.getString("MEM_ID"));
                member.setMemName(rs.getString("MEM_NAME"));
                member.setPhone(rs.getString("PHONE"));
                member.setAddress(rs.getString("ADDRESS"));
                member.setMemNo(rs.getString("MEM_NO"));
                member.setEmail(rs.getString("EMAIL"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	close(rs);
			close(pstmt);
        }

        return member;
    }

	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
        int result = 0;

        String sql = "UPDATE MEMBER SET mem_name = ?, phone = ?, address = ?, mem_no = ?, email = ? WHERE mem_id = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getMemName());
            pstmt.setString(2, member.getPhone());
            pstmt.setString(3, member.getAddress());
            pstmt.setString(4, member.getMemNo());
            pstmt.setString(5, member.getEmail());
            pstmt.setString(6, member.getMemId());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;
    }
	
	// 회원 정보 가져오기
    public Member getMemberById(Connection conn, String memId) {
        Member member = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = "SELECT * FROM MEMBER WHERE MEM_ID = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, memId);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                member = new Member();
                member.setMemId(rset.getString("MEM_ID"));
                member.setMemName(rset.getString("MEM_NAME"));
                member.setMemPwd(rset.getString("MEM_PWD"));
                member.setPhone(rset.getString("PHONE"));
                member.setAddress(rset.getString("ADDRESS"));
                member.setMemNo(rset.getString("MEM_NO"));
                member.setEmail(rset.getString("EMAIL"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }

        return member;
    }

    // 회원 탈퇴 (DATE_YN을 'N'으로 변경)
    public int deactivateMember(Connection conn, String memId) {
        int result = 0;
        PreparedStatement pstmt = null;

        String query = "UPDATE MEMBER SET DATE_YN = 'N' WHERE MEM_ID = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, memId);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;
    }
    
    // 비밀번호 변경
    public int updatePassword(Connection conn, String memId, String newPwd) {
        int result = 0;
        PreparedStatement pstmt = null;

        String query = "UPDATE MEMBER SET MEM_PWD = ? WHERE MEM_ID = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newPwd);
            pstmt.setString(2, memId);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;
    }

}
