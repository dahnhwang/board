<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(document).ready(function() {
		$('#searchBtn').on('click', function() {
			var mode = 1; /* 일단 제목검색만 하자.. */
			var keyword = $('#search').val();
			location.href = 'main.do?mode=' + mode + '&keyword=' + keyword;
		});

		/* 모달팝업 */
		$('#Modal').on('show.bs.modal', function(event) {
			/* var button = $(event.relatedTarget) // Button that triggered the modal
			var recipient = button.data('whatever') // Extract info from data-* attributes
			// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
			// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
			var modal = $(this)
			modal.find('.modal-title').text('New message to ' + recipient)
			modal.find('.modal-body input').val(recipient) */
			$('.modal-header > button').on('click', function() {
				$('#ModalForm > input').val('');
			});
			$('#ModalClose').on('click', function() {
				$('#ModalForm > input').val('');
			});
		})
	});
</script>
</head>
<body>
	<!-- 모달 -->
	<div class="modal fade" id="Modal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="ModalLabel"></h2>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group" id="ModalForm">
							<label for="recipient-name" class="col-form-label"></label> <input
								type="password" class="form-control" id="pwdInput">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="ModalClose" class="btn btn-secondary"
						data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-primary" id="ModalBtn">확인</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 네비게이션 -->
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="main.do">FREE BOARD</a>
			<div class="navbar-header">
				<button class="navbar-toggle collapsed" data-toggle="collapse"
					data-target="#target">
					<span class="sr-only">Toogle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="target">

				<div class="navbar-form navbar-right main_search_form">
					<input type="text" class="form-control main_search_input"
						placeholder="Search" id="search" /> <input id="searchBtn"
						type="button" class="form-control main_search_button btn-primary"
						placeholder="Submit" value="검색" />
				</div>

				<ul class="nav navbar-nav navbar-right main_cart_btn">
					<c:choose>
						<c:when test="${empty loginUser}">
							<li><a id="anchor-login-navi" class="loginBtn"
								href="loginForm.do">로그인</a></li>
							<li><a href="joinForm.do">회원가입</a></li>
						</c:when>
						<c:otherwise>
							<li><a id="anchor-logout-navi" href="logout.do">로그아웃</a></li>
							<li><a href="writeBoard.do">글쓰기</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>

	</div>
</body>
</html>