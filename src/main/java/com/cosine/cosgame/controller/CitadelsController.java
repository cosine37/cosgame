package com.cosine.cosgame.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cosine.cosgame.citadels.Board;
import com.cosine.cosgame.citadels.BoardEntity;
import com.cosine.cosgame.citadels.CitadelsMeta;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class CitadelsController {
	@RequestMapping(value="/citadels", method = RequestMethod.GET)
	public String citadels() {
		return "citadelsMain";
	}
	
	@RequestMapping(value="/citadels/allboards", method = RequestMethod.GET)
	public ResponseEntity<StringEntity> allBoards(HttpServletRequest request){
		CitadelsMeta meta = new CitadelsMeta();
		StringEntity entity = meta.getBoardIdsAsStringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame", method = RequestMethod.GET)
	public String citadelsGame() {
		return "citadelsGame";
	}
	
	@RequestMapping(value="/citadelsgame/start", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> citadelsGameStart(HttpServletRequest request) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		board.addPlayer(username);
		board.addPlayer("p2");
		board.addPlayer("p3");
		board.addPlayer("p4");
		board.gameSetup();
		session.setAttribute("boardId", board.getId());
		board.storeToDB();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/taketwocoins", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> takeTwoCoins(HttpServletRequest request) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getPlayerByName(username) != null) {
			board.getPlayerByName(username).addCoin(2);
			board.updatePlayer(username);
			board.updateDB("coins", board.getCoins());
		}
		
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/build", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> build(HttpServletRequest request, @RequestParam int index) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		//System.out.println(boardId);
		board.getFromDB(boardId);
		//System.out.println(board.getPlayerByName(username).getCoin());
		if (board.getPlayerByName(username) != null) {
			board.getPlayerByName(username).build(index);
			System.out.println(board.getPlayerByName(username).getCoin());
			board.updatePlayer(username);
			board.updateDB("coins", board.getCoins());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/getboard", method = RequestMethod.GET)
	public ResponseEntity<BoardEntity> citadelsGameGetBoard(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		//System.out.println(board.getPlayerByName(username).getCoin());
		BoardEntity entity = board.toBoardEntity(username);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
}
