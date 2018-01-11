package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import model.Board;
import model.Comment;
import model.Member;
import service.IBoardService;
import service.ICommentService;
import service.IFileService;
import service.IMemberService;

@Controller
public class MainController {

	@Autowired
	public IBoardService boardService;

	@Autowired
	public ICommentService commentService;

	@Autowired
	public IMemberService memberService;

	@Autowired
	public IFileService fileService;

	@RequestMapping("main.do")
	public ModelAndView main(@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int mode,
			@RequestParam(value = "keyword", required = false) String keyword) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("page", page);
		mav.addObject("mode", mode);
		mav.addObject("keyword", keyword);
		mav.setViewName("main.tiles");
		return mav;
	}

	@RequestMapping("boardlist.do")
	public @ResponseBody Map<String, Object> boardMain(Model model,
			@RequestParam(defaultValue = "1", value = "page") int page,
			@RequestParam(defaultValue = "0", value = "mode") int mode,
			@RequestParam(defaultValue = "", value = "keyword") String keyword) {
		HashMap<String, Object> boardMap = boardService.getBoardList(page, mode, keyword);
		return boardMap;
	}

	@RequestMapping("selectboard.do")
	public ModelAndView selectBoard(@RequestParam(value = "bId", required = true) int bId, HttpSession session) {

		Board board = boardService.readBoard(bId);
		int commentNumber = commentService.getCommentNumber(bId);
		Member writer = memberService.getMember(board.getmId());
		int mId = (int) session.getAttribute("loginUser");
		ModelAndView mav = new ModelAndView();
		if (board.getfId() != 0) {
			mav.addObject("fName", fileService.getFileName((board.getfId())).get("fName"));
			mav.addObject("fSize", fileService.getFileName((board.getfId())).get("fSize"));
		}
		mav.addObject("writer", writer);
		mav.addObject("board", board);
		mav.addObject("commentNumber", commentNumber);
		mav.addObject("mId", mId);
		mav.setViewName("board.tiles");
		return mav;
	}

	@RequestMapping("downloadFile.do")
	public View download(@RequestParam(value = "fId", required = true) int fId) {
		HashMap<String, Object> fileMap = fileService.getFileName(fId);
		String filename = (String) fileMap.get("fName");
		String uri = (String) fileMap.get("fUri");
		return new DownloadView(filename, uri);
	}

	@RequestMapping("commentList.do")
	public @ResponseBody Map<String, Object> commentList(@RequestParam(required = true) int bId) {
		int commentNumber = commentService.getCommentNumber(bId);
		HashMap<String, Object> commentResult = commentService.getCommentList(bId);
		commentResult.put("commentNumber", commentNumber);
		return commentResult;
	}

	@RequestMapping(value = "commentAdd.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> commentAdd(
			@RequestParam(required = true, value = "cContent") String cContent,
			@RequestParam(value = "parentId", defaultValue = "0") int parentId,
			@RequestParam(value = "bId", required = true) int bId,
			@RequestParam(value = "mId", required = true) int mId) {
		Comment comment = new Comment();
		comment.setcContent(cContent);
		comment.setbId(bId);
		comment.setmId(mId);
		HashMap<String, Object> result = new HashMap<>();
		result.put("result", commentService.writeComment(comment, parentId));
		return result;
	}

	@RequestMapping(value = "writeBoard.do")
	public ModelAndView writeBoard(@RequestParam(defaultValue = "0", value = "bId") int bId, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mId", session.getAttribute("loginUser"));
		if (bId != 0) {
			mav.addObject("bId", bId);
		}
		mav.setViewName("writeBoard.tiles");
		return mav;
	}

	@RequestMapping(value = "write.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> write(@RequestParam(required = true, value = "title") String title,
			@RequestParam(required = true, value = "bContent") String bContent,
			@RequestParam(required = true, value = "mId") int mId,
			@RequestParam(value = "parentId", defaultValue = "0") int parentId,
			@RequestParam(value = "privacySelect", required = true) int privacySelect,
			@RequestParam(value = "bPwd", required = true) String bPwd, @RequestParam("file") MultipartFile file,
			HttpSession session) {
		Board board = new Board();
		if (!file.isEmpty()) {
			String originalFileName = file.getOriginalFilename();
			String path = "/home/dh/fileUpload/";
			String url = path + originalFileName;
			Long fileSize = file.getSize();
			try {
				file.transferTo(new File(url));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.File saveFile = new model.File();
			saveFile.setfName(originalFileName);
			saveFile.setfUri(url);
			saveFile.setfSize(fileSize);
			board.setfId(fileService.saveFile(saveFile));
		}
		board.setIsPrivate(privacySelect);
		board.setTitle(title);
		board.setbContent(bContent);
		board.setmId(mId);
		board.setbPwd(bPwd);
		Map<String, Object> map = new HashMap<>();
		map.put("result", boardService.writeBoard(board, parentId));
		map.put("bId", board.getbId());
		return map;
	}

	@RequestMapping(value = "modifyBoardCheck.do")
	public @ResponseBody Map<String, Object> modifyBoardCheck(@RequestParam(required = true, value = "bId") int bId,
			@RequestParam(required = true, value = "bPwd") String bPwd, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		map.put("mId", session.getAttribute("loginUser"));
		Board board = boardService.getBoard(bId);
		if (board.getbPwd().equals(bPwd)) {
			map.put("result", 1);
			map.put("bId", bId);
			map.put("board", board);
		} else {
			map.put("result", 0);
		}
		return map;
	}

	@RequestMapping(value = "modifyBoard.do")
	public ModelAndView modifyBoard(@RequestParam(required = true, value = "bId") int bId,
			@RequestParam(required = true, value = "mId") int mId) {
		ModelAndView mav = new ModelAndView();
		Board board = boardService.getBoard(bId);
		mav.addObject("bId", bId);
		mav.addObject("mId", mId);
		mav.addObject("board", board);
		mav.setViewName("modifyBoard.tiles");
		return mav;
	}

	@RequestMapping(value = "modify.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modify(@RequestParam(required = true, value = "title") String title,
			@RequestParam(required = true, value = "bContent") String bContent,
			@RequestParam(required = true, value = "mId") int mId,
			@RequestParam(required = true, value = "bId") int bId,
			@RequestParam(value = "privacySelect", required = true) int privacySelect,
			@RequestParam("file") MultipartFile file, HttpSession session) {
		Board board = boardService.getBoard(bId);
		if (!file.isEmpty()) {
			String originalFileName = file.getOriginalFilename();
			String path = "/home/dh/fileUpload/";
			String url = path + originalFileName;
			Long fileSize = file.getSize();
			try {
				file.transferTo(new File(url));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.File saveFile = new model.File();
			saveFile.setfName(originalFileName);
			saveFile.setfUri(url);
			saveFile.setfSize(fileSize);
			board.setfId(fileService.saveFile(saveFile));
		}
		board.setTitle(title);
		board.setbContent(bContent);
		board.setIsPrivate(privacySelect);
		Map<String, Object> map = new HashMap<>();
		map.put("result", boardService.modifyBoard(board));
		map.put("bId", bId);
		return map;
	}

	@RequestMapping(value = "deleteBoardCheck.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteBoardCheck(@RequestParam(required = true, value = "bId") int bId,
			@RequestParam(required = true, value = "bPwd") String bPwd, HttpSession session) {
		int mId = (int) session.getAttribute("loginUser");
		Map<String, Object> map = new HashMap<>();
		map.put("mId", mId);
		Board board = boardService.getBoard(bId);
		if (board.getbPwd().equals(bPwd)) {
			if (boardService.deleteBoard(bId, mId) == 1) {
				map.put("result", 1);
			} else if (boardService.deleteBoard(bId, mId) == -1) {
				map.put("result", -1);
			}
		} else {
			map.put("result", 0);
		}
		return map;
	}

}
