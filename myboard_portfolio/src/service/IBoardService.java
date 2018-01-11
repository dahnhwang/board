package service;

import java.util.HashMap;

import model.Board;

public interface IBoardService {

	public int writeBoard(Board board, int parentId); // 그냥 글쓰기

	public Board readBoard(int bId); // 글 상세조회, 조회수 ++

	public int modifyBoard(Board board); // 글 수정

	public HashMap<String, Object> getBoardList(int page, int mode, String keyword); // 게시물 내용 얻어오기

	public Board getBoard(int bId); // 게시물 목록페이지에 필요한 데이터들 주워오기

	public int deleteBoard(int bId, int mId);

}
