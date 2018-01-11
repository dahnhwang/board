<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/comment.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						var bId = '${board.bId}';
						var mId = '${mId}';
						var cId = '';
						var mName = '';
						commentListLoad(bId);

						/* 목록버튼 클릭 시 메인으로*/
						$(document).on('click', '#toListBtn', function() {
							location.href = 'main.do';
						});
						/* 삭제버튼 클릭 시 모달팝업 */
						$(document).on('click', '#deleteBoardBtn', function() {
							$('#ModalLabel').text('비밀번호 입력');
							$('#ModalForm > label').text('게시글 비밀번호를 입력해주세요');
							$('#ModalBtn').on('click', function() {
								var bPwd = SHA256($('#pwdInput').val());
								$.ajax({
									url : 'deleteBoardCheck.do',
									type : 'post',
									data : {
										bId : bId,
										bPwd : bPwd
									},
									dataType : 'json',
									success : function(data) {
										if (data.result == 1) {
											alert('게시글이 삭제되었습니다');
											location.href = "main.do";
										} else if (data.result == 0) {
											alert('글 비밀번호가 일치하지 않습니다');
										} else if (data.result == -1) {
											alert('게시글에 댓글이 있는 경우 삭제할 수 없습니다');
										}
									}
								});
							});
						});

						/* 수정버튼 클릭 시 모달팝업 */
						$(document)
								.on(
										'click',
										'#modifyBoardBtn',
										function() {
											$('#ModalLabel').text('비밀번호 입력');
											$('#ModalForm > label').text(
													'게시글 비밀번호를 입력해주세요');
											$('#ModalBtn')
													.on(
															'click',
															function() {
																var bPwd = SHA256($(
																		'#pwdInput')
																		.val());
																$
																		.ajax({
																			url : 'modifyBoardCheck.do',
																			type : 'post',
																			data : {
																				bId : bId,
																				bPwd : bPwd
																			},
																			dataType : 'json',
																			success : function(
																					data) {
																				if (data.result == 1) {
																					location.href = "modifyBoard.do?bId="
																							+ bId
																							+ "&mId="
																							+ mId;
																				} else if (data.result == 0) {
																					alert('글 비밀번호가 일치하지 않습니다');
																				}
																			}
																		});
															});
										});

						/* 답글쓰기 버튼 클릭 시 페이지 이동 */
						$(document).on('click', '#writeBoardBtn', function() {
							location.href = "writeBoard.do?bId=" + bId;
						});

						/* 대댓글작성 버튼 기능 */
						$(document).on(
								'click',
								'.reply',
								function() {
									cId = $(this).attr('cId');
									mName = $(this).attr('mName');
									$('#commentWriteDiv > textarea').attr(
											'placeholder',
											'@' + mName + '님의 댓글에 대댓글을 작성합니다')
											.focus();
								});

						/* 댓글작성 버튼 기능 */
						$('#commentBtn')
								.on(
										'click',
										function() {
											var cContent = $('#cContent').val();

											$
													.ajax({
														url : 'commentAdd.do',
														type : 'post',
														data : {
															cContent : cContent,
															parentId : cId,
															bId : bId,
															mId : mId
														},
														dataType : 'json',
														success : function(data) {
															if (data) {
																if (data.result == 1) {
																	$(
																			'#commentWriteDiv > textarea')
																			.val(
																					'');
																	commentListLoad(bId);
																}
															}
														}
													});
										});

					});
</script>
<style>
#boardTableDiv {
	margin-left: 50px;
	margin-right: 50px;
}

#boardContentDiv {
	margin-top: 20px;
	padding-top: 20px;
	border-top: 2px solid #D0D0D0;
	padding-bottom: 20px;
	margin-bottom: 20px;
	border-bottom: 1px solid #D0D0D0;
}

#commentWriteDiv {
	margin-top: 20px;
}

.commentDiv {
	margin-top: 20px;
	padding: 20px;
	background-color: #f9f9f9;
	margin-bottom: 20px;
}

#boardDiv h1 {
	color: gray;
}

#boardFileDiv {
	padding-bottom: 20px;
	margin-bottom: 20px;
	border-bottom: 1px solid #D0D0D0;
}

.space {
	height: 5px;
}
</style>
</head>
<body>


	<div id="wrap">
		<div id="container">

			<div id="boardTableDiv">
				<h1>${board.title }
					<c:choose>
						<c:when test="${board.isPrivate == 1}">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</c:when>
						<c:otherwise>
							<i class="fa fa-unlock" aria-hidden="true"></i>
						</c:otherwise>
					</c:choose>
				</h1>
				<div>
					By <span class="text-primary bold"><b>${writer.mName }</b></span>
					&nbsp;(
					<fmt:formatDate value="${board.bDatetime }"
						pattern="yyyy/MM/dd hh:mm:ss" />
					)&nbsp;, read by <b>${board.hit }</b> people
				</div>

				<div id="boardContentDiv">${board.bContent }</div>
				<c:choose>
					<c:when test="${board.fId != 0}">
						<div id="boardFileDiv">
							<span class="text-muted">첨부파일</span> <a
								href="downloadFile.do?fId=${board.fId }">${fName } (${fSize }
								Bytes)</a>
						</div>
					</c:when>
				</c:choose>
				<div id="contentEndDiv">
					<div class="pull-left">
						<button type="button" class="btn btn-link" id="countComment"
							id="writeBoardBtn">댓글 ${commentNumber } 개</button>
					</div>
					<div class="pull-right">
						<button type="button" class="btn btn-link" id="writeBoardBtn">답글쓰기</button>
						<button type="button" class="btn btn-link" id="modifyBoardBtn"
							data-target="#Modal" data-toggle="modal">수정</button>
						<button type="button" class="btn btn-link" id="deleteBoardBtn"
							data-target="#Modal" data-toggle="modal">삭제</button>
						<button type="button" class="btn btn-link" id="toListBtn">목록</button>
					</div>
					<div class="clearfix"></div>
				</div>
				<div id="commentListDiv"></div>
				<div id="commentWriteDiv">
					<textarea id="cContent" class="form-control" rows="3"
						placeholder="댓글 내용을 작성해주세요"></textarea>
					<div class="space"></div>
					<button type="button" class="btn btn-primary btn-block"
						id="commentBtn">댓글 입력</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>