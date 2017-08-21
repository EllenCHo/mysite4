<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="${pageContext.request.contextPath }/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<title>ajax-My site</title>
</head>
<body>

	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					<form id="write-form" action="" method="">
						<table>
							<tr>
								<td>이름</td>
								<td><input id="name" type="text" name="name" /></td>
								<td>비밀번호</td>
								<td><input id="password" type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea id="content" name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " /></td>
							</tr>
						</table>
					</form>
					<ul id="guestbook-list">

					</ul>
				</div>
				<!-- /guestbook -->
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
	<!-- /container -->

</body>
<script type="text/javascript">
	//jsp를 한번 정리한 후에 이 코드가 실행됨(ready)
	$(document).ready(function() {
		//리스트 출력
		fetchList();
		
		//저장버튼 클릭
		$("#write-form").on("submit", function(){			//form에서 submit이라는 특별한 이벤트를 잡는것
			event.preventDefault();						//원래의 submit기능을 실행하기 때문에 그 기능을 막고 여기로 오게 하는 것
			console.log("전송버튼 클릭");
			
			var name= $("[name=name]").val();
			var password = $("[name=password]").val();
			var content = $("[name=content]").val();

			$.ajax({

				url : "${pageContext.request.contextPath }/api/gb/add", //연결할 컨트롤러
				type : "post", //전송할 방식
				/* contentType : "application/json",	*/		
				//앞은 보낼 파라미터 이름, 그 뒤는 파라미터
				data : {name: name, password : password, content : content},

				dataType : "json", //컨트롤러에서 데이터로 받을때 (json 형태로 날라옴 다른 형태도 가능함)
				success : function(guestBookVo) { //list에 값이 하나도 없어도 success로 온다
					console.log(guestBookVo);
					render(guestBookVo, "up");
					/*성공시 처리해야될 코드 작성*/
				},
				error : function(XHR, status, error) { //실패했을때 에러메세지 찍어달라는것, 통신상의 에러라던지 그런것들
					console.error(status + " : " + error);
				}
			});
		});					
	});

	function fetchList() {
		$.ajax({

			url : "${pageContext.request.contextPath }/api/gb/list", //연결할 컨트롤러
			type : "post", //전송할 방식
			/* contentType : "application/json",			//데이터를 컨트롤러에 보내야할때
			data : {name: "홍길동"}, */

			dataType : "json", //컨트롤러에서 데이터로 받을때 (json 형태로 날라옴 다른 형태도 가능함)
			success : function(guestBookList) { //list에 값이 하나도 없어도 success로 온다
				for (var i = 0; i < guestBookList.length; i++) {
					render(guestBookList[i], "down");
				}
				console.log(guestBookList)
				/*성공시 처리해야될 코드 작성*/
			},
			error : function(XHR, status, error) { //실패했을때 에러메세지 찍어달라는것, 통신상의 에러라던지 그런것들
				console.error(status + " : " + error);
			}
		});
	}
	
	function render(guestbookVo, updown) {
		var str = "";
		str += "<li>";
		str += "	<table>";
		str += "		<tr>";
		str += "			<td>[" + guestbookVo.no + "]</td>";
		str += "			<td>" + guestbookVo.name + "</td>";
		str += "			<td>" + guestbookVo.regDate + "</td>";
		str += "			<td><a href='#'> 삭제 </a> </td>";
		str += "		</tr>";
		str += "		<tr>";
		str += "			<td colspan=4>" + guestbookVo.content + "</td>";
		str += "		</tr>";
		str += "	</table>";
		str += "	<br/>";
		str += "</li>";
		
		if(updown == "down") {
			$("#guestbook-list").append(str);
		} else if(updown == "up") {
			$("#guestbook-list").prepend(str);
		} else {
			console.log("error");
		}
	}
</script>
</html>