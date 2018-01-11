/**
 * 
 */

function commentListLoad(bId) {
	var params = "bId=" + bId;
	$.ajax({

		url : 'commentList.do',
		type : 'get',
		data : params,
		dataType : 'json',
		success : function(data) {
			if (data) {
				$('#commentListDiv').empty();
				showComments(data);
			}
		}
	});
}

function showComments(list) {
	var commentList = list.commentList;
	var memberList = list.memberList;

	$.each(commentList, function(index, item) {
		var cId = item.cId;
		var cContent = item.cContent;
		var mName = memberList[index].mName;
		var cDatetime = item.cDatetime;
		var groupId = item.groupId;
		var groupSeq = item.groupSeq;
		var groupLv = item.groupLv;

		var divTag = $('<div>').addClass('commentDiv');
		$('<i class="fa fa-user" aria-hidden="true"></i>').appendTo(divTag);
		$('<span>').addClass('text-primary bold').text(mName).appendTo(divTag);
		$('<span>').text($.format.date(cDatetime, ' yy/MM/dd HH:mm:ss'))
				.appendTo(divTag);

		var replyTag = $('<a>').attr('cId', cId).attr('mName', mName).addClass(
				'reply');
		$('<i class="fa fa-reply" aria-hidden="true"></i>').appendTo(replyTag);
		replyTag.appendTo(divTag);

		$('<i class="fa fa-trash-o" aria-hidden="true"></i>').appendTo(divTag);
		$('<div>').text(cContent).appendTo(divTag);
		$('#commentListDiv').append(divTag);

	});

}