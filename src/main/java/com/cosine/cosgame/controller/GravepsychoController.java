package com.cosine.cosgame.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cosine.cosgame.gravepsycho.Board;
import com.cosine.cosgame.gravepsycho.BoardEntity;
import com.cosine.cosgame.gravepsycho.GravepsychoMeta;
import com.cosine.cosgame.gravepsycho.Player;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class GravepsychoController {
	@RequestMapping(value="/gravepsycho", method = RequestMethod.GET)
	public String gravepsycho() {
		return "gravepsychoMain";
	}
	
	@RequestMapping(value="/gravepsychocreategame", method = RequestMethod.GET)
	public String gravepsychoCreateGame() {
		return "gravepsychoCreateGame";
	}
	
	@RequestMapping(value="/gravepsychogame", method = RequestMethod.GET)
	public String gravepsychoGame() {
		return "gravepsychoGame";
	}
	
	@RequestMapping(value="/gravepsychoendgame", method = RequestMethod.GET)
	public String gravepsychoEndGame() {
		return "gravepsychoEndGame";
	}
	
	@RequestMapping(value="/gravepsycho/allboards", method = RequestMethod.GET)
	public ResponseEntity<StringEntity> allBoards(HttpServletRequest request){
		GravepsychoMeta meta = new GravepsychoMeta();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		StringEntity entity = meta.getBoardIdsAsStringEntity(username);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/gravepsycho/newboard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> newBoard(HttpServletRequest request) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		board.addPlayer(username);
		board.setLord(username);
		board.genBoardId();
		board.newBoard();
		board.storeToDB();
		session.setAttribute("boardId", board.getId());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/gravepsycho/setboardid", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setboardid(HttpServletRequest request, @RequestParam String boardId) {
		HttpSession session = request.getSession(true);
		session.setAttribute("boardId", boardId);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/gravepsycho/join", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> join(HttpServletRequest request) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getPlayerByName(username) == null) {
			board.addPlayer(username);
			board.addPlayerToDB(username);
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/gravepsycho/startgame", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> startGame(HttpServletRequest request, @RequestParam int useEvent) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		board.startGame(useEvent);
		board.updateDB("status", board.getStatus());
		board.updateDB("round", board.getRound());
		board.updateDB("useEvent", board.isUseEvent());
		board.updateDB("event", board.getEvent().getNum());
		board.updateDeck();
		board.updateTreasures();
		board.updatePlayers();
		board.updateRevealed();
		board.updateLogs();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/gravepsycho/dismiss", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> dismiss(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getLord().contentEquals(username)) {
			board.dismiss();
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/gravepsycho/decision", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> decision(HttpServletRequest request,  @RequestParam int x) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		board.playerDecide(username, x);
		board.updateDB("status", board.getStatus());
		board.updateDB("round", board.getRound());
		board.updateDB("leftover", board.getLeftover());
		board.updateDB("event", board.getEvent().getNum());
		board.updateDeck();
		board.updateTreasures();
		board.updateRevealed();
		board.updatePlayers();
		board.updateRemoved();
		board.updateLogs();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/gravepsycho/setavatar", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setAvatar(HttpServletRequest request,  @RequestParam String x) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		Player p = board.getPlayerByName(username);
		if (p != null) {
			p.setAvatar(x);
			board.updatePlayer(username);
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/gravepsycho/getboard", method = RequestMethod.GET)
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
