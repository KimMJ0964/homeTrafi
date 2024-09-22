<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>커뮤니티메인</title>

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
            box-shadow: 1px 1px 1px 1px rgb(203, 203, 203)
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
            box-shadow: 1px 1px 1px 1px rgb(203, 203, 203)
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

        table {
            width: 100%;
            border-collapse: separate;
            border-radius: 15px;
            overflow: hidden; /* 둥근 모서리를 적용하기 위해 필요 */
        }
        td, th {
            padding: 10px;
            border: 1px solid rgb(68, 68, 68);
            border-radius: 10px; /* 셀의 테두리 둥글게 */
        }
        .title {
            font-size: 24px;
            font-weight: bold;
            text-align: center;
        }
        .info {
            font-size: 14px;
            text-align: center;
        }
        .content {
            padding: 20px;
        }
        .comment-section {
            margin-top: 20px;
        }
        textarea {
            width: 100%;
            height: 100px;
        }
    </style>
</head>
<body>
    <div id="header"></div>
    <div class="wrap">
        <table>
            <!-- 제목 -->
            <tr>
                <td colspan="3" class="title">게시글 제목</td>
            </tr>
        
            <!-- 닉네임, 조회수, 좋아요 수 -->
            <tr>
                <td class="info">닉네임: 사용자</td>
                <td class="info">조회수: 123</td>
                <td class="info">좋아요: 45</td>
            </tr>
        
            <!-- 게시판 내용 -->
            <tr>
                <td colspan="3" class="content">
                    <p>이곳에 게시글 내용이 들어갑니다. 원하는 내용을 입력하세요.</p>
                </td>
            </tr>
        
            <!-- 댓글 창 -->
            <tr class="comment-section">
                <td colspan="3">
                    <h3>댓글</h3>
                    <textarea placeholder="댓글을 입력하세요"></textarea>
                    <button type="submit">댓글 작성</button>
                </td>
            </tr>
        </table>
        </div>
    </div>

    <script>
        function updateDropdownText(selectedText) {
            document.getElementById('dropdownMenuButton').textContent = selectedText;
        }
    </script>
</body>
</html>