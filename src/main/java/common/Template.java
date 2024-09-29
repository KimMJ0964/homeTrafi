package common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	public static SqlSession getSqlSession() {
		// mybatis-config.xml 읽어드리기
		
		SqlSession sqlSession = null;
		
		// SqlSession 생성하기 위해서는 -> SqlSessionFactory 객체 필요
		// SqlSessionFactory 생성하기 위해서는 -> SqlSessionFactoryFactoryBuilder 필요
		
		String resource = "/mybatis-config.xml";
		
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession(false);	// true(자동커밋), false(수동커밋)
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSession;
	}
	
	public static PageInfo getPageInfo(int listCount, int currentPage, int pageLimit, int boardLimit) {
		int maxPage = (int)Math.ceil((double)listCount/boardLimit); // 가장 마지막 페이지
		int startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1; // 페이징바의 시작
		int endPage = startPage + pageLimit - 1; //페이징바의 마지막
		
		endPage = endPage > maxPage ? maxPage : endPage;
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		return pi;
	}
}

