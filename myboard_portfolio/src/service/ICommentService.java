package service;

import java.util.HashMap;
import java.util.List;

import model.Comment;

public interface ICommentService {

	// add new comment
	public int writeComment(Comment comment, int parentId);

	// modify comment
	public void modifyComment(Comment comment);

	public void deleteComment(int cId);

	public HashMap<String, Object> getCommentList(int bId);

	// 댓글이 있는지 확인하여 해당 게시물을 삭제할 수 있는지 체크한다.
	public int getCommentNumber(int bId);

	// 대댓글이 있는지 확인하여 해당 댓글을 삭제할 수 있는지 체크한다.
	public int checkReComments(int cId);

}
