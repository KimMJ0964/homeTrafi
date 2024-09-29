package com.skt.board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import com.skt.board.model.vo.Board;
import com.skt.board.model.vo.BoardFile;
import com.skt.board.service.BoardServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardCreateController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println(JakartaServletFileUpload.isMultipartContent(request));
		if (JakartaServletFileUpload.isMultipartContent(request)) {

			// 1. 파일용량제한(byte)
			int fileMaxSize = 1024 * 1024 * 10; // 10mb
			int requestMaxSize = 1024 * 1024 * 20; // 20mb

			// 2. 전달된 파일을 저장시킬 폴더경로가져오기
			String savePath = request.getServletContext().getRealPath("/resource/board_upfile/");

			// 3.DiskFileItemFactory(파일을 임시로 저장) 객체 생성
			DiskFileItemFactory factory = DiskFileItemFactory.builder().get();

			// JakartaServletFileUpload http요청으로 들어온 파일데이터를 파싱 -> 개별FileItem 객체로 변환
			JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

			upload.setFileSizeMax(fileMaxSize);
			upload.setSizeMax(requestMaxSize);

			// 요청(request)으로부터 파일아이템 파싱
			List<FileItem> formItems = upload.parseRequest(request);

			// 추가할 데이터
			Board b = new Board();
			BoardFile bf = null;

			// 반복문을 통해 파일과 파라미터 정보획득
			for (FileItem item : formItems) {
				System.out.println(item);
				if (item.isFormField()) { // 일반파라미터
					switch (item.getFieldName()) {
					case "title":
						b.setTitle(item.getString(Charset.forName("utf-8")));
						break;
					case "content":
						b.setContent(item.getString(Charset.forName("utf-8")));
						break;
					case "postType":
						b.setType(item.getString(Charset.forName("utf-8")));
						break;
					}
					b.setMemId("user01");
					
					System.out.println(b.getTitle() + ", " + b.getContent() + ", " + b.getType() + ", " + b.getMemId());
				} else {
					String originName = item.getName(); // 업로드 요청한 파일명(오리지널 파일명)

					if (originName.length() > 0) { // 파일을 업로드 했을 때
						// 고유한 파일명 생성
						String tmpName = "boardFile_" + System.currentTimeMillis();
						String type = originName.substring(originName.lastIndexOf("."));
						String changeName = tmpName + type; // 서버에 저장할 파일명

						File f = new File(savePath, changeName);
						item.write(f.toPath()); // 지정한 경로에 파일 업로드

						bf = new BoardFile();
						bf.setOriginName(originName);
						bf.setChangeName(changeName);
						bf.setFilePath("resource/board_upfile/");
					}
				}
			}

			int result = new BoardServiceImpl().insertBoard(b, bf);
			if (result > 0) { // 성공 -> 게시글 목록(jsp/list.bo?cage=1)
				request.getSession().setAttribute("alertMsg", "일반게시글 작성 성공");
				response.sendRedirect(request.getContextPath() + "/board.bo?cpage=1");
			} else { // 실패 -> 업로드된 파일 삭제해주고 에러페이지
				if (bf != null) {
					new File(savePath + bf.getChangeName()).delete();
				}

				request.setAttribute("errorMsg", "일반게시글 작성 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
