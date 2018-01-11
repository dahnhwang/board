package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IBoardDao;
import dao.IBoardHistoryDao;
import dao.ICommentDao;
import dao.IMemberDao;
import model.Board;
import model.BoardHistory;
import model.Member;

@Service
public class BoardService implements IBoardService {

	@Autowired
	private IBoardDao boardDao;
	@Autowired
	private IBoardHistoryDao historyDao;
	@Autowired
	private ICommentDao commentDao;
	@Autowired
	private IMemberDao memberDao;

	@Override
	public int writeBoard(Board board, int parentId) {
		// parentId가 0이면 원글 0이 아니면 답글
		int writeBoardResult = 0;
		if (parentId == 0) {
			boardDao.insertBoard(board);
			int newBId = board.getbId();
			board.setGroupId(newBId);
			writeBoardResult = boardDao.updateBoard(board);
		} else {
			// 답글처리를 하기 위해서는
			// 일단 부모글의 그룹아이디를 내 것에 셋팅을 해야하고
			// 부모글의 그룹시퀀스 +1을 내 것에 셋팅을 해야하고
			// 그룹레벨을 부모글 +1을 해서 셋팅을 해야 함
			Board parentBoard = boardDao.selectBoard(parentId);
			board.setGroupId(parentBoard.getGroupId());
			board.setGroupSeq(parentBoard.getGroupSeq() + 1);
			board.setGroupLv(parentBoard.getGroupLv() + 1);
			// 그 다음에 시퀀스값이 나보다 같거나 큰 애들을 하나씩 +1해줘야 함
			HashMap<String, Object> params = new HashMap<>();
			params.put("groupId", board.getGroupId());
			params.put("groupSeq", board.getGroupSeq());
			boardDao.updateGroupSeq(params);
			// 그 다음에 내 데이터를 저장하러 감
			writeBoardResult = boardDao.insertBoard(board);
		}
		return writeBoardResult;
	}

	@Override
	public Board readBoard(int bId) {
		Board board = boardDao.selectBoard(bId);
		board.setHit(board.getHit() + 1);
		boardDao.updateBoard(board);
		return board;
	}

	@Override
	public int modifyBoard(Board board) {
		board.setIsUpdated(board.getIsUpdated() + 1);
		boardDao.updateBoard(board);
		BoardHistory historyBoard = new BoardHistory(board);
		int modifyResult = historyDao.insertBoardHistory(historyBoard);
		return modifyResult;
	}

	@Override
	public HashMap<String, Object> getBoardList(int page, int mode, String keyword) {
		// 여기서 얼만큼씩 페이지를 나눠서 보여줄건지 등의 정보를 함께 정해서 보내줘야 한다..
		int skip = (page - 1) * 10;
		int count = 10;
		HashMap<String, Object> params = new HashMap<>();
		params.put("skip", skip);
		params.put("count", count);
		params.put("mode", mode);
		if (mode == 1) { // 제목검색 1번
			params.put("title", keyword);
		} else if (mode == 2) { // 내용검색 2번
			params.put("bContent", keyword);
		}
		HashMap<String, Object> results = new HashMap<>();
		List<Board> boardList = boardDao.selectList(params);
		List<Member> memberList = new ArrayList<>();
		for (Board b : boardList) {
			Member member = new Member();
			member = memberDao.selectMember(b.getmId());
			memberList.add(member);
		}
		int first = 1;
		int start = (page - 1) / 5 * 5 + 1;
		int last = 0;
		if (mode == 0) {
			last = (boardDao.getBoardCount() - 1) / 10 + 1;
		} else {
			last = (boardDao.getSearchListCount(params) - 1) / 10 + 1;
		}
		int end = (((page - 1) / 5) + 1) * 5;
		// 만약 전체목록 페이지가 3장밖에 없으면 last는 3, end값은 5가 나오게 된다.
		// 따라서 end값이 last값보다 큰 경우 last값을 end 값에 대입한다.
		end = end > last ? last : end;
		results.put("first", first);
		results.put("start", start);
		results.put("last", last);
		results.put("end", end);
		results.put("current", page);
		results.put("boardList", boardList);
		results.put("memberList", memberList);
		return results;
	}

	@Override
	public Board getBoard(int bId) {
		Board board = boardDao.selectBoard(bId);
		return board;
	}

	@Override
	public int deleteBoard(int bId, int mId) {
		int deleteResult = 0;
		if (commentDao.countComment(bId) == 0) {
			Board board = boardDao.selectBoard(bId);
			board.setIsDeleted(1);
			boardDao.updateBoard(board);
			BoardHistory historyBoard = new BoardHistory(board, mId);
			deleteResult = historyDao.insertBoardHistory(historyBoard);
		} else {
			deleteResult = -1; // -1인 경우 코멘트가 있어 삭제 불가
		}

		return deleteResult;
	}

}
