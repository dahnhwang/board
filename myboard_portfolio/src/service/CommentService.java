package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.javassist.bytecode.Descriptor.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ICommentDao;
import dao.IMemberDao;
import model.Comment;
import model.Member;

@Service
public class CommentService implements ICommentService {

	@Autowired
	public ICommentDao commentDao;

	@Autowired
	public IMemberDao memberDao;

	@Override
	public int writeComment(Comment comment, int parentId) {
		int result = 0;
		if (parentId == 0) { // 걍 댓글인 경우
			commentDao.insertComment(comment);
			comment.setGroupId(comment.getcId());
			result = commentDao.updateComment(comment);
		} else { // 대댓글인 경우
			Comment parentComment = commentDao.selectOne(parentId);
			comment.setGroupId(parentComment.getGroupId());
			comment.setGroupSeq(parentComment.getGroupSeq() + 1);
			comment.setGroupLv(parentComment.getGroupLv() + 1);
			HashMap<String, Object> params = new HashMap<>();
			params.put("groupId", comment.getGroupId());
			params.put("groupSeq", comment.getGroupSeq());
			commentDao.updateGroupSeq(params);
			result = commentDao.insertComment(comment);
		}
		return result;
	}

	@Override
	public void modifyComment(Comment comment) {
		// (단, 본인이 작성한 것만. admin은 다됨)
		// 단, 대댓글이 있는 경우 삭제는 불가능하다.
		commentDao.updateComment(comment);
	}

	@Override
	public void deleteComment(int cId) {
		// checkReComments를 한 다음에 삭제한다.
		commentDao.deleteComment(cId);
	}

	@Override
	public HashMap<String, Object> getCommentList(int bId) {
		List<Comment> commentList = commentDao.selectCommentById(bId);
		List<Member> memberList = new ArrayList<>();
		for (Comment c : commentList) {
			Member member = new Member();
			member = memberDao.selectMember(c.getmId());
			memberList.add(member);
		}
		HashMap<String, Object> resultList = new HashMap<>();
		resultList.put("commentList", commentList);
		resultList.put("memberList", memberList);
		return resultList;
	}

	@Override
	public int getCommentNumber(int bId) {
		int countComments = commentDao.countComment(bId);
		return countComments;
	}

	@Override
	public int checkReComments(int cId) {
		Comment c = commentDao.selectOne(cId);
		HashMap<String, Object> params = new HashMap<>();
		params.put("groupId", c.getGroupId());
		params.put("groupSeq", c.getGroupSeq());
		params.put("groupLv", c.getGroupLv());
		int countReComments = commentDao.countReComment(params);
		return countReComments;
	}

}
