<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 작성</title> 

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
            padding: 20px;
            box-shadow: 1px 1px 1px 1px rgb(203, 203, 203);
        }
        #header {
            width: 1284px;
            height: 91px;
            background-color: white;
            border-radius: 30px;
            margin: 20px auto;
            box-shadow: 1px 1px 1px 1px rgb(203, 203, 203);
        }
        .title-container {
            display: flex;
            align-items: center;
        }
        .title-container label {
            margin-right: 10px;
            white-space: nowrap;
        }
        .title-container input {
            flex-grow: 1;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        .left-buttons {
            display: flex;
            align-items: center;
        }
        button, .dropdown {
            border-radius: 10px;
            border: 2px solid #C8C8C8;
        }
        .img-button {
            margin-right: 10px;
            border: 2px solid #C8C8C8;
        }
        .back-button {
            background-color: #FF9797;
            border-color: #FF3E3E;
            color: white;
        }
        .back-button:hover {
            background-color: #ff4646;
            border-color: #ff0000;
            color: white;
        } 
        .submit-button {
            border: 2px solid #C8C8C8;
        }

        /* 반응형 스타일 */
        @media (max-width: 768px) {
            .wrap {
                width: 90%; /* 화면 좌우에 5%씩 여백 */
                margin: 0 auto;
                border-radius: 10px;
                box-shadow: 1px 1px 10px 1px rgba(0, 0, 0, 0.1);
            }
            #header {
                width: 100%;
                border-radius: 0;
                margin: 0;
                box-shadow: none;
            }
            .button-container {
                flex-direction: column;
            }
            .left-buttons {
                margin-bottom: 10px;
            }
        }

        @media (max-width: 576px) {
            .wrap {
                width: 95%; /* 작은 화면에서 더 많은 여백을 추가 */
                margin: 0 auto;
            }
            .title-container {
                flex-direction: column;
                align-items: flex-start;
            }
            .title-container label {
                margin-bottom: 10px;
            }
            .button-container {
                width: 100%;
                flex-direction: column;
            }
            .left-buttons {
                width: 100%;
                justify-content: space-between;
            }
            .right-buttons {
                width: 100%;
                display: flex;
                justify-content: space-between;
            }
        }
    </style>
</head>
<body>
    <div id="header"></div>
    <div class="wrap">
        <form action="<%=contextPath %>/board_create.bd" method="post">
            <!-- 제목 -->
            <div class="mb-3 title-container">
                <label for="title" class="form-label" style="font-weight: bold;">제목</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요" style="border: none;" required>
            </div>
            <hr>
            <!-- 내용 -->
            <div class="mb-3">
                <label for="content" class="form-label" style="font-weight: bold;">내용</label>
                <textarea class="form-control" id="content" name="content" rows="10" placeholder="내용을 입력하세요" style="border: none; resize: none; height: 450px;" required></textarea>
            </div>
            <hr>
            <!-- 버튼 섹션 -->
            <div class="button-container">
                <div class="left-buttons">
                    <button type="button" class="img-button btn btn-light">사진추가</button>
                    
                    <!-- 유형추가: 드롭다운 -->
                    <div class="dropdown">
                        <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                            유형선택
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <li><a class="dropdown-item" href="#" onclick="selectType('QnA')">QnA</a></li>
                            <li><a class="dropdown-item" href="#" onclick="selectType('여행추천')">여행추천</a></li>
                        </ul>
                    </div>
                </div>
                <div class="right-buttons">
                    <button type="button" class="back-button btn" onclick="goBack()">뒤로가기</button>
                    <button type="submit" class="submit-button btn btn-light">작성완료</button>
                </div>
            </div>
        </form>
    </div>

    <script>

        // 유형 선택 시 버튼 텍스트 변경
        function selectType(type) {
            document.getElementById('dropdownMenuButton').innerText = type;
        }

        // 뒤로 가기 버튼 클릭 시 이전 페이지로 이동
        function goBack() {
            window.history.back();
        }
    </script>
</body>
</html>