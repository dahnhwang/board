package dao;

import java.util.HashMap;
import java.util.List;

import model.Comment;

public interface ICommentDao {

	public List<Comment> selectCommentById(int bId);

	// board에 달린 댓글의 수를 카운트하기 위한 함수
	public int countComment(int bId);

	public int insertComment(Comment comment);

	public int updateComment(Comment comment);

	public int deleteComment(int cId);

	public int updateGroupSeq(HashMap<String, Object> params); // 대댓글 다는 경우 시퀀스 관리

	public Comment selectOne(int cId); // 대댓글 다는 경우 부모의 정보를 확인하기 위함

	public int countReComment(HashMap<String, Object> params);

}
