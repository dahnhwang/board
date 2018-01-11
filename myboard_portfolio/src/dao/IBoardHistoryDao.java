package dao;

import model.BoardHistory;

public interface IBoardHistoryDao {
	
	//얘는 쓰기만 할 수 있다.
	public int insertBoardHistory(BoardHistory boardHistory);

}
