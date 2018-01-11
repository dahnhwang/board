<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		var page = '${page}';
		var mode = '${mode}';
		var keyword = '${keyword}';
		showPage(page, mode, keyword);
		
		
		$(document).on("click", "button[name='page_move']", function() {
			var page = $(this).attr("page");
			showPage(page, mode, keyword);
		});
	

	});
</script>
<style>
#listTableDiv {
	margin-left: 50px;
	margin-right: 50px;
}

td {
	text-align: left;
}

.paginationDiv {
	width: 95%;
	text-align: right;
}
</style>
</head>
<body>
	<div id="wrap">

		<div id="container col-md-12">

			<div id="listTableDiv">
				<table id="listTable" style="text-align: center" class="table"
					cellspacing="0" width="100%" data-toggle="table"
					data-select-item-name="toolbar1" data-pagination="true">
					<thead>
						<tr>
							<th>제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>첨부파일</th>
							<th>작성일시</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
			<div class="paginationDiv" id="paginationDiv">
				<ul class="pagination" id="pagination">
				</ul>
			</div>
		</div>
	</div>
</body>
</html>