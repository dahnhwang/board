package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.ICommentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test/applicationContext.xml" })
public class CommentServiceTest {

	@Autowired
	public ICommentService commentService;

	@Test
	public void countReCommentTest() {
		int cId = 1;
		assertEquals(1, commentService.checkReComments(cId));
		int cId2 = 3;
		assertEquals(0, commentService.checkReComments(cId2));
	}

}
