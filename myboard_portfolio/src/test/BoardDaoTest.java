package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.IBoardDao;
import model.Board;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test/applicationContext.xml" })
public class BoardDaoTest {

	@Autowired
	public IBoardDao boardDao;

	@Test
	public void insertBoardTest() {
		Board board = new Board();
		board.setTitle("hello");
		board.setbContent("heheheheheheh");
		board.setIsPrivate(1);
		board.setbPwd("1234");
		int result = boardDao.insertBoard(board);
		assertEquals(1, result);
	}

	@Test
	public void getBoardCountTest() {
		assertEquals(4,boardDao.getBoardCount());
	}

	@Test
	public void selectListTest() {
		HashMap<String, Object> params = new HashMap<>();
		params.put("skip", 1);
		params.put("count",1);
		boardDao.selectList(params);
		assertNotNull("skip", 1);
		assertNotNull("count",1);
	}


}
