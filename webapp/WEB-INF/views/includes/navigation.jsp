<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

		<div id="navigation">
			<ul>
				<li><a href="${pageContext.request.contextPath }/main">홈</a></li>
				<li><a href="${pageContext.request.contextPath }/gb/list">방명록</a></li>
				<li><a href="${pageContext.request.contextPath }/gb/list2">ajax 방명록</a></li>
				<li><a href="${pageContext.request.contextPath }/board/list?currNo=1">게시판</a></li>
				<li><a href="${pageContext.request.contextPath }/replyboard/list?currNo=1">계층형 게시판</a></li>
			</ul>
		</div> <!-- /navigation -->