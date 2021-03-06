<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<title>Mysite</title>
</head>
<body>
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="wrapper">
			<div id="content">
				<div id="user">
	
					<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath }/user/join">
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value="" />
	
						<label class="block-label" for="email">이메일</label>
						<input id="email" name="email" type="text" value="" />
						<input id="button" type="button" value="id 중복체크">
						<label id="check"></label>
						
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="" />
						
						<fieldset>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
							<label>남</label> <input type="radio" name="gender" value="male">
						</fieldset>
						
						<fieldset>
							<legend>약관동의</legend>
							<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
							<label>서비스 약관에 동의합니다.</label>
						</fieldset>
						
						<input id="submit" type="submit" value="가입하기">
						
					</form>
					
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
		<script type="text/javascript">
			$("#button").on("click", function(){
				var email = $("#email").val();
				
				if(email == ""){
					$("#check").html("<font color='blue'><strong>이메일을 작성해주세요.</strong></font>");
				} else {
					$.ajax({
	
						url : "${pageContext.request.contextPath }/user/check", //연결할 컨트롤러
						type : "post", //전송할 방식
						//contentType : "application/json",			//데이터가 많을때는 알맹이만 보내는것이 나으므로 json으로 보냄
						//앞은 보낼 파라미터 이름, 그 뒤는 파라미터
						data : {email : email},			
						
						//dataType : "json", //컨트롤러에서 데이터로 받을때 (json 형태로 날라옴 다른 형태도 가능함)
						success : function(email) { //list에 값이 하나도 없어도 success로 온다
							if(email == ""){
								$("#check").text("사용할 수 있는 이메일입니다.");
							} else {
								$("#check").html("<font color='red'><strong>사용중인 이메일입니다.</strong></font>");
							}
							/*성공시 처리해야될 코드 작성*/
						},
						error : function(XHR, status, error) { //실패했을때 에러메세지 찍어달라는것, 통신상의 에러라던지 그런것들
							console.error(status + " : " + error);
						}
					});
				}
			});
			
			/* $("#join-form").on("submit", function(){
				event.preventDefault();						//원래의 submit기능을 실행하기 때문에 그 기능을 막고 여기로 오게 하는 것
				
				var name = $("#name").val();
				var email = $("#email").val();
				var check = $("#check").text();
				var password = $("[name = password]").val();
				
				console.log(check);
				if(name != "" && email != "" && password != "" && check == "사용할 수 있는 이메일입니다.") {
					console.log("가입")
					var userVo = {
							name : name,
							password : password,
							email : email,
							gender : $("[name=gender]").val()
					}
					
					$.ajax({

						url : "${pageContext.request.contextPath }/user/join", //연결할 컨트롤러
						type : "post", //전송할 방식
						//contentType : "application/json",			//데이터가 많을때는 알맹이만 보내는것이 나으므로 json으로 보냄
						//앞은 보낼 파라미터 이름, 그 뒤는 파라미터
						data : userVo,			
						
						//dataType : "json", //컨트롤러에서 데이터로 받을때 (json 형태로 날라옴 다른 형태도 가능함)
						success : function() { 
							console.log("가입성공");
							
						},
						error : function(XHR, status, error) { //실패했을때 에러메세지 찍어달라는것, 통신상의 에러라던지 그런것들
							console.error(status + " : " + error);
						}
					});
				} else {
					alert("모든 항목을 채워넣거나 이메일 중복을 확인해주세요.");
				}
				
				
			}); */
			
			
		</script>
	</div><!-- /container -->
</body>
</html>		
		
