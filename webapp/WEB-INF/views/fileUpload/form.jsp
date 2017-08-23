<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<title>Mysite</title>
</head>
<body>
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="content">
			<div id="guestbook" style="margin:50px auto; width:500px; ">
				<h1 style="margin-bottom:20px">파일 업로드 예제</h1>
				
				<form method="post" action="${pageContext.request.contextPath }/fileUpload/result" enctype="multipart/form-data">
					<label>email:</label>
					<input type="text" name="email" value="bit@gmail.com">
					<br><br>
					
					<label>파일1:</label>
					<input type="file" name="file">
					<br><br>
				
					
					<input type="submit" value="파일업로드">
				</form>
			</div>				
		</div>


		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div><!-- /container -->
</body>
</html>		
		
