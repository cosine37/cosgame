package com.cosine.cosgame.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cosine.cosgame.rich.Board;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class RichController {
	@RequestMapping(value="/rich", method = RequestMethod.GET)
	public String rich() {
		return "richMain";
	}
	@RequestMapping(value="/richcreategame", method = RequestMethod.GET)
	public String oinkCreateGame() {
		return "richCreateGame";
	}
	@RequestMapping(value="/richgame", method = RequestMethod.GET)
	public String oinkGame() {
		return "richGame";
	}
	@RequestMapping(value="/rich/newboard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> newBoard(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		board.addPlayer(username);
		board.setLord(username);
		board.genBoardId();
		board.setStatus(Consts.PREGAME);
		board.storeToDB();
		session.setAttribute("boardId", board.getId());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/rich/startgame", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> startGame(HttpServletRequest request, @RequestParam List<Integer> settings){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			if (board.isLord(username)) {
				board.startGameUDB(settings);
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
}
