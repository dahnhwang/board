package dao;

import java.util.HashMap;
import java.util.List;

import model.Board;

public interface IBoardDao {

	// 검색조건을 걸었을 때 해당 검색에 관련된 전체 게시글의 수를 알아야 함
	public int getSearchListCount(HashMap<String, Object> params);

	// 전체 게시글을 보여주기도 하고, 동시에 특정검색을 넣은 게시글을 보여주기도 할 것이므로 파라미터를 해쉬맵으로 받음
	public List<Board> selectList(HashMap<String, Object> params);

	// 얘는 페이징처리를 위해 전체 게시글의 수를 알아야 하니까 필요함
	// 비공개게시글을 빼고 카운트를 해야 함
	public int getBoardCount();

	public Board selectBoard(int bId);

	public int insertBoard(Board board);

	// 얘는 게시글에 답글 올라올 때 순서 처리를 위해 필요함
	public int updateGroupSeq(HashMap<String, Object> params);

	// 조회수 늘릴때, 글 수정 또는 삭제
	public int updateBoard(Board board);

}
