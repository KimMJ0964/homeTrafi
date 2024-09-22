<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    /* 화면 가운데에 위치시키기 위한 CSS */
    .center-box {
        width: 300px;
        height: 200px;
        background-color: lightblue;
        margin: 0 auto;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    a {
        margin: 10px 0;
        text-decoration: none;
        color: black;
        font-weight: bold;
    }
</style>
</head>
<body>

<div class="center-box">
    <!-- myPage.jsp로 이동하는 링크 -->
    <form action="myPageTest.me" method="post">
	        	
	        	<button type="submit" class="btn btn-sm btn-danger" >
	        		마이페이지
	        	</button>
	        </form>

    <!-- boardMainPage.jsp로 이동하는 링크 -->
    <a href="<%=contextPath %>/board.bo?cpage=1">"Board Main Page</a>

</div>

</body>
</html>