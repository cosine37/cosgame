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

import com.cosine.cosgame.pokewhat.Board;
import com.cosine.cosgame.pokewhat.BoardEntity;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class PokewhatController {
	@RequestMapping(value="/pokewhat", method = RequestMethod.GET)
	public String pokewhat() {
		return "pokewhatMain";
	}
	
	@RequestMapping(value="/pokewhatcreategame", method = RequestMethod.GET)
	public String pokewhatCreateGame() {
		return "pokewhatCreateGame";
	}
	
	@RequestMapping(value="/pokewhatgame", method = RequestMethod.GET)
	public String pokewhatGame() {
		return "pokewhatGame";
	}
	
	
	
	@RequestMapping(value="/pokewhatgame/newboard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> newBoard(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		board.addPlayer(username);
		board.setLord(username);
		board.genBoardId();
		board.storeToDB();
		session.setAttribute("boardId", board.getId());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokewhatgame/getboard", method = RequestMethod.GET)
	public ResponseEntity<BoardEntity> getBoard(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
		} else {
			board.setId("NE");
		}
		
		BoardEntity entity = board.toBoardEntity(username);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	
}
