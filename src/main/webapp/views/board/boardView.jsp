<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글</title>

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
            border: 2px solid #C8C8C8;
        }
        .comments-section {
            padding-left: 20px;
            padding-right: 20px;
            background-color: #F6F6F6;
            border: 2px solid #C8C8C8;
        }
        .comment {
            margin-top: 20px;
            padding : 5px;
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
        @media (max-width: 768px) {
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
        @media (max-width: 480px) {
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
                    <td colspan="5" style="text-align: center; border: 2px solid #C8C8C8; background-color: #ececec; font-weight: bold;">제목: 제주도/근교/맛집</td>
                </tr>
                <tr> 
                    <td style="background-color: #ececec;">아이디: test01</td>
                    <td style="background-color: #ececec;">작성일: 2024/01/01</td>
                    <td style="background-color: #ececec;">유형: QnA</td>
                    <td style="background-color: #ececec;">조회수: 20</td>
                    <td style="background-color: #ececec;">좋아요: 6</td>
                </tr>
            </table>
            <!-- Post Content -->
            <div class="post-content">
                저번 주말에 부모님과 제주도를 다녀왔는데 xx수산이 정말 친절하고 좋았어요. OO들레길도 다녀왔는데 부모님이 좋아하셔서 기분이 좋았네요. 그 후 일정은 딱히 없었는데 트레팍에서 주변에 **기념관이 있다고 해서 다녀왔습니다. 사실도 깨끗하고 조용해서 좋더라고요. 여행 코스로 추천받아서 알차게 다녀왔어요. 이 사이트 괜찮다고 생각합니다. 다른 사이트는 결제 유도하던데 여기는 그런게 없어서 괜찮은 것 같아요. 심리테스트도 부모님께 해보시라고 권해드리니 신기하다며 좋아하시네요. xx수산, OO들레길, **기념관은 조용한 곳 좋아하시는 분이 가면 좋을 것 같네요. 추천합니다!
            </div>
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
                <button class="btn btn-link" style="background-color: rgb(225, 225, 255);">답변</button>
                <button class="btn btn-link text-danger"  style="background-color: rgb(255, 225, 225);">댓글 삭제</button>
            </div>

            <div class="comment mt-4 text-muted" style="background-color: white;">
                '삭제된 댓글입니다.'
            </div>
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