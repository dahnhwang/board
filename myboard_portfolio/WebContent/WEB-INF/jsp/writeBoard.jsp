<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {

		/* 글 입력 후 모달팝업(글 비밀번호 입력) */
		$(document).on('click', '#boardBtn', function() {
			$('#ModalLabel').text('비밀번호 입력');
			$('#ModalForm > label').text('게시글 비밀번호를 입력해주세요');
			$('#ModalBtn').on('click', function(event) {
				event.preventDefault();
				var bPwd = SHA256($('#pwdInput').val());
				var formData = new FormData($("#writeForm")[0]);
				formData.append("bPwd", bPwd);
				$.ajax({
					url : 'write.do',
					type : 'post',
					enctype : 'multipart/form-data',
					data : formData,
					processData : false,
					cache : false,
					contentType : false,
					dataType : 'json',
					success : function(data) {
						if (data.result == 1) {
							var bId = data.bId;
							alert('게시물이 성공적으로 등록되었습니다');
							location.href = "selectboard.do?bId=" + bId;
						}
					},
					error : function(error) {
						alert("파일 업로드에 실패하였습니다.");
					}
				});
			})
		});

	});
</script>
<style>
#boardTableDiv {
	margin-left: 50px;
	margin-right: 50px;
}

#boardPrivacyDiv {
	margin-bottom: 20px;
}

#boardContentDiv {
	margin-top: 20px;
	padding-top: 20px;
	border-top: 2px solid #D0D0D0;
	padding-bottom: 20px;
	margin-bottom: 20px;
	border-bottom: 1px solid #D0D0D0;
}

#boardFileDiv {
	padding-bottom: 20px;
	margin-bottom: 20px;
	border-bottom: 1px solid #D0D0D0;
}
</style>
</head>
<body>
	<div id="wrap">
		<div id="container">
			<div id="boardTableDiv">
				<form method="post" enctype="multipart/form-data" id="writeForm">
					<input type="hidden" name="mId" value="${mId }"> <input
						type="hidden" name="parentId" value="${bId }">
					<div class="form-group">

						<input type="text" class="form-control form-control-lg"
							id="boardTitle" placeholder="제목을 입력해주세요" name="title">
					</div>
					<div id="boardContentDiv">
						<textarea class="form-control" id="boardContent" rows="5"
							placeholder="내용을 입력해주세요" name="bContent"></textarea>
					</div>
					<div id="boardFileDiv">

						<label class="custom-file"> <input type="file" id="file2"
							class="custom-file-input" name="file"><span
							class="custom-file-control"></span>
						</label>
					</div>
					<div id="boardPrivacyDiv">
						<select class="form-control" id="privacySelect"
							name="privacySelect">
							<option value="0">공개</option>
							<option value="1">비공개</option>
						</select>
					</div>
					<div id="boardBtnDiv">
						<button type="button" class="btn btn-primary btn-block btn-lg"
							data-target="#Modal" data-toggle="modal" id="boardBtn">게시글
							입력</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>