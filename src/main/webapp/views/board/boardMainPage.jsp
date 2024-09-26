<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.skt.common.PageInfo, java.util.ArrayList,com.skt.board.model.vo.Board" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath();
%>
<%
    PageInfo pi = (PageInfo)request.getAttribute("pi");
    ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");

    int currentPage = pi.getCurrentPage();
    int startPage = pi.getStartPage();
    int endPage = pi.getEndPage();
    int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>커뮤니티</title>

    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #ADE8F4;
        }
        .wrap {
            width: 1246px;
            margin: auto;
            border: 1px solid black;
            border-radius: 20px;
            background-color: white;
            box-shadow: 1px 1px 1px 1px rgb(203, 203, 203);
        }
        #header, #footer, #aside {
            height: 20%;
        }
        #header {
            width: 1284px;
            height: 91px;
            background-color: white;
            border-radius: 30px;
            margin: 0 auto;
            margin-top: 20px;
            margin-bottom: 20px;
            box-shadow: 1px 1px 1px 1px rgb(203, 203, 203);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        #header img {
            width: 100px;
        }

        #header .dropdown {
            margin-right: 10px;
        }

        #header button {
            margin-right: 10px;
            background-color: white;
            font-size: 20px;
            color: black;
            font-weight: bold;
            border-style: none;
        }

        #header > .dropdown > button {
            background-color: white;
            font-size: 20px;
            color: black;
            font-weight: bold;
        }

        #footer {
            margin-top: 100px;
            width: 100%;
            height: 300px;
            background-color: #03045E;
        }
        
        .main_image {
            position: relative;
        }

        .main_image_text {
            position: absolute;
            top: 30%;
            left: 50%;
            transform: translate(-50%, -50%);
            color: white;
            font-weight: 900;
            text-shadow: -1px 0px black, 0px 1px black, 1px 0px black, 0px -1px black;
            font-size:50px;
        }

        #search {
            display: flex;
            align-items: center;
            gap: 10px; /* 간격 조정 */
            margin-top: 10px;
            position: absolute;
            top: 80%;
            left: 55%;
            transform: translate( -30%, -50% );
            text-align: center;
            width: 100%;
        }

        #searchKeyword {
            background-color: rgb(231, 231, 231);
            border-radius: 40px;
            border-style: none;
            width: 40%;
            height: 35px;
            text-align: center;
        }

        .btn {
            background-color: rgb(179, 179, 179);
            border-radius: 40px;
            border-style: none;
            height: 35px;
            text-align: center;
        }

        .createBtn {
            float: right;
            border-radius: 10px;
            border-style: none;
            background-color: rgb(177, 192, 255);
            width: 90px;
            height: 35px;
            font-weight: 600;
            color: white;
        }

        .createBtn:hover {
            background-color: rgb(147, 169, 255);
        }

        .table {
            text-align: center;
        }

        th {
            background-color: #9fd7ff; /* 연한 파랑색 */
        }

        .background-img {
            width: 1221px;
            border-radius: 20px;
        }
        
        #createBoardBtn {
        	width: 150px;
        	border-style: none;
        	background-color: #CFE2FF;
        	border-radius: 10px;
        	font-weight: bold;
        	height: 40px;
        	left: 0px;
        	float: right;
        	margin-right: 20px;
        }
        
        #createBoardBtn:hover {
        	background-color: #a1c6ff;
        }

        /* 반응형 디자인 - 481px ~ 768px */
        @media (max-width: 768px) and (min-width: 481px) {
            .wrap {
                width: 100%;
                border-radius: 10px;
                box-shadow: none;
                border: none;
            }
            #header {
                width: 100%;
                height: 70px;
                margin-top: 10px;
                margin-bottom: 10px;
            }
            .main_image img {
                width: 100%;
                border-radius: 10px;
                margin-top: 20px;
            }
            .main_image_text {
                font-size: 30px;
                margin-top: 5px;
            }
            #searchKeyword {
                width: 60%;
            }
            #search {
                top: 70%;
                left: 60%;
                transform: translate(-50%, -50%);
            }
            .createBtn {
                width: 100px;
                height: 40px;
            }

            .background-img {
                width: 400px;
                border-radius: 20px;
            }
            
            #createBoardBtn {
	        	width: 120px;
	        	border-style: none;
	        	background-color: #CFE2FF;
	        	border-radius: 10px;
	        	font-weight: bold;
	        	height: 30px;
	        	left: 0px;
	        	float: right;
	        	margin-right: 20px;
	        	font-size: 11px;
	        }
        }

        /* 모바일 화면 (최대 480px 이하) */
        @media (max-width: 480px) {
            .wrap {
                width: 100%;
                margin: 0;
                border: none;
                box-shadow: none;
                padding: 0 10px;
            }
            #header {
                width: 100%;
                height: 70px;
                margin-top: 10px;
                margin-bottom: 10px;
            }
            .main_image img {
                width: 100%;
                border-radius: 10px;
            }
            .main_image_text {
                font-size: 25px;
            }
            #searchKeyword {
                width: 80%;
            }
            #search {
                top: 90%;
                left: 50%;
                transform: translate(-50%, -50%);
            }
            .createBtn {
                width: 90px;
                height: 35px;
            }

            .background-img {
                width: 400px;
                border-radius: 20px;
            }
            
            #createBoardBtn {
	        	width: 120px;
	        	border-style: none;
	        	background-color: #CFE2FF;
	        	border-radius: 10px;
	        	font-weight: bold;
	        	height: 30px;
	        	left: 0px;
	        	float: right;
	        	margin-right: 20px;
	        	font-size: 11px;
	        }
        }

    </style>
</head>
<body>
    <div id="header"></div>

    <div class="wrap">
        <div class="container mt-3">
            <div class="main_image">
                <img src="/SktTrafi/views/board/img/boardMain.png" class="background-img">
                <h1 class="main_image_text">커뮤니티</h1>
                <div id="search">
                    <div class="dropdown">
                        <button type="button" id="dropdownMenuButton" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown">
                            유형
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <li><a class="dropdown-item" href="#" onclick="updateDropdownText('작성자')">작성자</a></li>
                            <li><a class="dropdown-item" href="#" onclick="updateDropdownText('제목')">제목</a></li>
                            <li><a class="dropdown-item" href="#" onclick="updateDropdownText('작성일')">작성일</a></li>
                        </ul>
                    </div>
                    <input type="text" id="searchKeyword" placeholder="키워드 검색">
                </div>
            </div>
            <br><br>        
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>글번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>조회수</th>
                        <th>작성일</th>
                    </tr>
                </thead>
                <tbody>
                <!--
                    <tr onClick="location.href='board.jsp'">
                        <td>1</td>
                        <td>첫번째 게시글</td>
                        <td>user01</td>
                        <td>125</td>
                        <td>2024-01-01</td>
                    </tr>
                    
                     -->
                    <% if(list.isEmpty()) { %>
                    <tr>
                        <td colspan="6">존재하는 게시글이 없습니다.</td>
                    </tr>
                <% } else { %>
                    <% for(Board b : list) { %>
                        <tr onclick="clickDetailPage(<%=b.getCommNo()%>)">
                            <td><%=b.getCommNo()%></td>
                            <td><%=b.getTitle()%></td>
                            <td><%=b.getMemId()%></td>
                            <td><%=b.getViewCount()%></td>
                            <td><%=b.getCreateDate() %></td>
                        </tr>
                    <% } %>
                <% } %>
                </tbody>
            </table>
            <br>
            <button id="createBoardBtn" onClick="location.href='views/board/createBoard.jsp'">글쓰기</button>
            <br>
            <!-- Pagination -->
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center mt-3">
                    <%if(currentPage > 1) { %>
            			<button onclick="location.href='<%=contextPath%>/board.bo?cpage=<%=currentPage - 1%>'">&lt;</button>
           			 <% } %>
            		 <% for(int p = startPage; p <= endPage; p++) { %>
                			<% if(p == currentPage) { %>
                    			<button disabled><%=p%></button>
                			<% } else {%>
                    			<button onclick="location.href='<%=contextPath%>/board.bo?cpage=<%=p%>'"><%=p%></button>
               			    <% } %>
            		 <% } %>
            		 <%if(currentPage < maxPage) { %>
            				<button onclick="location.href='<%=contextPath%>/board.bo?cpage=<%=currentPage + 1%>'">&gt;</button>
        			 <% } %>
                </ul>
            </nav>
        </div>
    </div>

    <div id="footer"></div>
    <script>
    function clickDetailPage(boardNo){
        location.href = "<%=contextPath%>/detail.bo?bno=" + boardNo;
    }
    </script>
</body>
</html>