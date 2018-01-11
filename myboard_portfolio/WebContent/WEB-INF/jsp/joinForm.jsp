<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/member.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		/* var result = checkAll();
		if (result == true) {
			$('#join_btn').attr('disabled', false);
		} else {
			$('#join_btn').attr('disabled', true);
		} */

		$('#join_btn').on('click', function(e) {

			e.preventDefault();

			var pwd = SHA256($('#pwd').val());
			var email = $('#email').val();
			var name = $('#name').val();

			$.ajax({
				url : 'join.do',
				type : 'POST',
				dataType : 'json',
				data : {
					name : name,
					email : email,
					pwd : pwd
				},
				success : function(data) {
					if (data.result == 1) {
						alert("회원가입 성공! 로그인해주세요");
						$(location).attr('href', 'loginForm.do');
					} else {
						alert("Error : Please retry");
					}
				}
			}); /* ajax 끝 */
		}); /* 회원가입 버튼 끝 */

	});
</script>
<style>
.wrapper {
	margin-top: 80px;
	margin-left: 50px;
	margin-right: 50px;
}

#joinForm {
	max-width: 100%;
	margin: 0 auto;
}
</style>
</head>
<body>

	<div id="wrap">
		<div id="container">
			<div class="wrapper">


				<form id="joinForm" name="joinForm">

					<h1 class="form-signin-heading">환영합니다</h1>
					<div class="form-group">
						<label for="email">Email</label> <span id="email_msg"></span><input
							type="email" class="form-control" id="email"
							placeholder="이메일을 입력하세요" name="email">
					</div>

					<div class="form-group">
						<label for="name">Name</label> <span id="name_msg"></span><input
							type="name" class="form-control" placeholder="이름을 입력하세요"
							name="name" id="name">

					</div>


					<div class="form-group">
						<label for="pwd">Password</label> <span id="pwd_msg"></span><input
							type="password" class="form-control" id="pwd"
							placeholder="비밀번호를 입력하세요" name="pwd">
					</div>


					<div class="form-group">
						<label for="repass">Re-Password</label> <span id="repwd_msg"></span><input
							type="password" class="form-control" id="repass"
							placeholder="비밀번호를 다시 입력하세요" name="pwd_check">
					</div>

					<div id="joinFormBtn">
						<button type="submit" class="btn btn-primary" id="join_btn"
							onclick="return checkAll()">회원가입</button>
						<button type="reset" class="btn btn-danger">취소</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>