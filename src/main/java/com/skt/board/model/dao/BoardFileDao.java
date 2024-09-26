package com.skt.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.skt.board.model.vo.BoardFile;

public class BoardFileDao {
	
	BoardFile fileDownload(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
        ResultSet rset = null;
        BoardFile file = null; // 조회된 파일 정보를 담을 객체
        String sql = "SELECT FILE_NO, REF_BNO, ORIGIN_NAME, CHANGE_NAME, FILE_PATH, UPLOAD_DATE " +
                     "FROM COMMUNITY_FILE " +
                     "WHERE REF_BNO = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo); // boardNo 값 바인딩
            rset = pstmt.executeQuery();

            if (rset.next()) {
                file = new BoardFile();  // 파일 객체 생성
                file.setFileNo(rset.getInt("FILE_NO"));            // 파일 번호
                file.setRefBNo(rset.getInt("REF_BNO"));            // 참조하는 게시글 번호
                file.setOriginName(rset.getString("ORIGIN_NAME")); // 원본 파일명
                file.setChangeName(rset.getString("CHANGE_NAME")); // 변경된 파일명
                file.setFilePath(rset.getString("FILE_PATH"));     // 파일 경로
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

        return file;  // 조회된 파일 정보 반환
    }
	
}
