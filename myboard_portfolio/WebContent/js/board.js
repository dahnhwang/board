/**
 * 
 */

function goBack() {
	window.history.back();
}

function showPage(page, mode, keyword) {
	var params = "page=" + page + "&mode=" + mode + "&keyword=" + keyword;
	$.ajax({
		url : 'boardlist.do',
		type : 'get',
		data : params,
		dataType : 'json',
		success : function(data) {

			if (data) {
				$('#listTable tbody').empty();
				$('#pagination li').empty();
				listUpload(data);
				pagenation(data);
			}
		}
	})
}

function pagenation(data) {
	/* 여기부터 페이지네이션 */
	var first = data.first;
	var start = data.start;
	var current = data.current;
	var end = data.end;
	var last = data.last;
	var li = $('<li>').appendTo('#pagination');
	if (first < start) {
		$(
				'<button type="button" class="btn btn-link" name="page_move" page="'
						+ first + '">').text("<").appendTo(li);
		$(
				'<button type="button" class="btn btn-link" name="page_move" page="'
						+ (start - 1) + '">').text("...").appendTo(li);
	}
	if (last < 6) {
		// 결과페이지 수가 5 이하인 경우
		for (var i = start; i < last + 1; i++) {
			if (i == current) {
				$(
						'<button type="button" class="btn btn-primary" name="page_move" page="'
								+ i + '">').text(i).appendTo(li);
			} else {
				$(
						'<button type="button" class="btn btn-link" name="page_move" page="'
								+ i + '">').text(i).appendTo(li);
			}
		}
	} else {
		// 결과페이지 수가 6페이지 이상인 경우
		for (var i = start; i < start + (end - start + 1); i++) {
			if (i == current) {
				$(
						'<button type="button" class="btn btn-primary" name="page_move" page="'
								+ i + '">').text(i).appendTo(li);
			} else {
				$(
						'<button type="button" class="btn btn-link" name="page_move" page="'
								+ i + '">').text(i).appendTo(li);
			}
		}
	}

	if (end < last) {
		$(
				'<button type="button" class="btn btn-link" name="page_move" page="'
						+ (end + 1) + '">').text("...").appendTo(li);
		$(
				'<button type="button" class="btn btn-link" name="page_move" page="'
						+ last + '">').text(">").appendTo(li);
	}

	/* 여기까지 페이지네이션 */
}

function listUpload(list) {
	var boardList = list.boardList;
	var memberList = list.memberList;

	$
			.each(
					boardList,
					function(index, item) {

						var tr = $('<tr>').appendTo('#listTable tbody');
						var td = $('<td>');
						var bId = item.bId;
						var title = item.title;
						var mName = memberList[index].mName;
						var fId = item.fId;
						var bDatetime = item.bDatetime;
						var hit = item.hit;
						var lv = item.groupLv;

						if (lv > 0) {
							var reply = "";
							for (var i = 0; i < lv; i++) {
								reply += "ㄴ";
							}
							title = reply + title;
						}

						var isPrivate = "";
						if (item.isPrivate === 1) {
							isPrivate = $('<i class="fa fa-lock" aria-hidden="true"></i>');
						}

						var titleTag = $('<a>').addClass("boardTitle").text(
								title).append(" ").append(isPrivate).bind(
								'click', function() {
									loginCheck(bId)
								});
						$('<td class="col-sm-4">').append(titleTag)
								.appendTo(tr);
						$('<td class="col-sm-1">').text(mName).appendTo(tr);

						$('<td class="col-sm-1">').text(hit).appendTo(tr);
						if (fId > 0) {
							$(
									'<td class="col-sm-1"><i class="fa fa-file-text-o" aria-hidden="true"></i></td>')
									.appendTo(tr);
						} else {
							$('<td class="col-sm-1">').text("").appendTo(tr);
						}
						$('<td class="col-sm-1">').text(
								$.format.date(bDatetime, 'yy/MM/dd HH:mm'))
								.appendTo(tr);

					});
}