<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.skt.board.model.vo.Board"%>
<%
Board b = (Board) request.getAttribute("board");
String contextPath = request.getContextPath();
String testLoginSession = "user01";		// 로그인 임시 세션
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글</title>

<!-- Latest compiled and minified CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
	margin: 0;
	padding: 0;
	background-color: #ADE8F4;
}

.wrap {
	width: 100%;
	max-width: 1246px;
	margin: auto;
	border: 1px solid black;
	border-radius: 20px;
	background-color: white;
	padding: 20px;
	box-shadow: 1px 1px 1px 1px rgb(203, 203, 203);
}

#header {
	width: 1284px;
	max-width: 1284px;
	height: 91px;
	background-color: white;
	border-radius: 30px;
	margin: 20px auto;
	box-shadow: 1px 1px 1px 1px rgb(203, 203, 203);
}

.post-title {
	font-size: 16px;
	background-color: #F6F6F6;
}

.post-meta {
	font-size: 14px;
	background-color: #F6F6F6;
}

.post-meta table {
	width: 100%;
	table-layout: fixed;
	margin-top: 10px;
	border: 2px solid #C8C8C8;
}

.post-meta td {
	border: 2px solid #C8C8C8;
}

.post-content {
	font-size: 16px;
	line-height: 1.5;
	padding: 15px 0;
	background-color: white;
}

.comments-section {
	padding-left: 20px;
	padding-right: 20px;
	background-color: #F6F6F6;
	border: 2px solid #C8C8C8;
}

.comment {
	margin-top: 20px;
	padding: 5px;
}

.buttons {
	margin-top: 20px;
	display: flex;
	gap: 10px;
}

.pagination {
	margin-top: 20px;
}
/* 반응형 처리 */
@media ( max-width : 768px) {
	.wrap {
		width: 90%;
		padding: 10px;
	}
	#header {
		width: 100%;
		margin: 10px auto;
	}
	.post-meta table {
		font-size: 12px;
	}
	.post-meta td {
		padding: 5px;
	}
	.post-content {
		font-size: 14px;
	}
	.comments-section {
		padding-left: 10px;
		padding-right: 10px;
	}
}

/* 모바일 화면에서 테이블의 <td> 요소를 한 줄씩 배치 */
@media ( max-width : 480px) {
	.post-meta td {
		display: block;
		width: 100%;
		border: none;
		background-color: #ececec;
		margin-bottom: 5px;
	}
	.post-meta table {
		border: none;
	}
	.post-meta tr {
		display: block;
	}
	.pagination {
		font-size: 12px;
	}
}
</style>
</head>
<body>
	<div id="header"></div>
	<div class="wrap">
		<h1 style="text-align: center;">게시글</h1>
		<!-- Post Metadata -->
		<div class="post-meta">
			<table class="table text-center" style="margin-bottom: 0px;">
				<tr>
					<td colspan="5"
						style="text-align: center; border: 2px solid #C8C8C8; background-color: #ececec; font-weight: bold;"><%=b.getTitle()%></td>
				</tr>
				<tr>
					<td style="background-color: #ececec;">아이디: <%=b.getMemId()%></td>
					<td style="background-color: #ececec;">작성일: <%=b.getCreateDate()%></td>
					<td style="background-color: #ececec;">유형: <%=b.getType()%></td>
					<td style="background-color: #ececec;">조회수: <%=b.getViewCount()%></td>
					<td style="background-color: #ececec;">좋아요: <%=b.getLikeCount()%></td>
				</tr>
				<tr>
					<td colspan="5" style="text-align: left; padding: 10px;">
						<div class="post-content"><%=b.getContent()%></div>
					</td>
				</tr>
				<!-- 첨부파일 Row -->
				<tr>
					<td colspan="5"
						style="border: 2px solid #C8C8C8; background-color: #f9f9f9; text-align: left; padding: 10px;">
						<div class="attachment">
							<%if(at == null) { %> 
	                    		첨부파일이 없습니다. 
                    		<% } else {%>
	                   			 <a download="<%=at.getOriginName() %>" href="<%=contextPath%>/<%=at.getFilePath() + at.getChangeName()%>"><%=at.getOriginName() %></a>
                			<%} %>
						</div>
					</td>
				</tr>
				<!-- 게시글 주인 버튼 -->
				<tr>
					<td colspan="5">
						<div align="center">
							<a href="<%=contextPath %>/board.bo?cpage=1" class="btn btn-sm btn-secondary">목록가기</a>
							<%	if (testLoginSession.equals(b.getMemId())) {	%>
								<a href="<%=contextPath%>/updateBoard.bo?bno=<%=b.getCommNo()%>" class="btn btn-sm btn-warning">수정하기</a>
								<a href="<%=contextPath%>/deleteBoard.bo?bno=<%=b.getCommNo()%>" class="btn btn-sm btn-danger">삭제하기</a>
							<%	}	%>
						</div>
					</td>
				</tr>

			</table>
			<br>
		</div>

		<!-- Comment Section -->
		<div class="comments-section">
			<br>
			<h5 style="font-weight: bold;">댓글</h5>
			<div class="comment">
				<textarea class="form-control mb-3" rows="4" placeholder="댓글을 입력하세요"></textarea>
				<button class="btn btn-primary">작성 완료</button>
			</div>
			<hr>

			<div class="comment mt-4" style="background-color: white;">
				<strong>test02</strong>
				<p>잘 다녀오셨네요. 다음에 저도 가봐야겠어요.</p>
				<button class="btn btn-link"
					style="background-color: rgb(225, 225, 255);">답변</button>
				<button class="btn btn-link text-danger"
					style="background-color: rgb(255, 225, 225);">댓글 삭제</button>
			</div>

			<div class="comment mt-4 text-muted" style="background-color: white;">
				'삭제된 댓글입니다.'</div>
			<!-- Pagination -->
			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-center mt-3">
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">4</a></li>
					<li class="page-item"><a class="page-link" href="#">5</a></li>
					<li class="page-item disabled"><a class="page-link" href="#">...</a></li>
					<li class="page-item"><a class="page-link" href="#">10</a></li>
				</ul>
			</nav>
		</div>

	</div>
</body>
</html>