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

import com.cosine.cosgame.nothanks.Board;
import com.cosine.cosgame.nothanks.BoardEntity;
import com.cosine.cosgame.nothanks.NothanksMeta;
import com.cosine.cosgame.nothanks.Player;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class NothanksController {
	@RequestMapping(value="/nothanks", method = RequestMethod.GET)
	public String nothanks() {
		return "nothanksMain";
	}
	
	@RequestMapping(value="/nothankscreategame", method = RequestMethod.GET)
	public String nothanksCreateGame() {
		return "nothanksCreateGame";
	}
	
	@RequestMapping(value="/nothanksgame", method = RequestMethod.GET)
	public String nothanksGame() {
		return "nothanksGame";
	}
	
	@RequestMapping(value="/nothanksendgame", method = RequestMethod.GET)
	public String nothanksEndGame() {
		return "nothanksEndGame";
	}
	
	@RequestMapping(value="/nothanks/allboards", method = RequestMethod.GET)
	public ResponseEntity<StringEntity> allBoards(HttpServletRequest request){
		NothanksMeta meta = new NothanksMeta();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		StringEntity entity = meta.getBoardIdsAsStringEntity(username);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/nothanksgame/setboardid", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setboardid(HttpServletRequest request, @RequestParam String boardId) {
		HttpSession session = request.getSession(true);
		session.setAttribute("boardId", boardId);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/nothanksgame/join", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> join(HttpServletRequest request) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getPlayerByName(username) == null) {
			board.addPlayer(username);
			board.addPlayerToDB(username);
			board.updateDB("initialRevealedMoney", board.getInitialRevealedMoney());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/nothanksgame/kick", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> kick(HttpServletRequest request, @RequestParam int index) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getLord().contentEquals(username)) {
			board.removePlayerFromDB(index);
			board.updateInitialRevealedMoney();
			board.updateDB("initialRevealedMoney", board.getInitialRevealedMoney());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/nothanksgame/newboard", method = RequestMethod.POST)
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
	
	@RequestMapping(value="/nothanksgame/addbot", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> addBot(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		board.addBot();
		board.updateDB("initialRevealedMoney", board.getInitialRevealedMoney());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/nothanksgame/setstartplayer", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setStartPlayer(HttpServletRequest request,  @RequestParam int index){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getLord().contentEquals(username)) {
			board.setCurPlayer(index);
			board.updateDB("curPlayer", board.getCurPlayer());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/nothanksgame/setinitialrevealedmoney", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setInitialRevealedMoney(HttpServletRequest request,  @RequestParam int x){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getLord().contentEquals(username)) {
			if (board.getPlayers().size() * x <= 12) {
				board.setInitialRevealedMoney(x);
				board.updateDB("initialRevealedMoney", board.getInitialRevealedMoney());
			}
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/nothanksgame/dismiss", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> dismiss(HttpServletRequest request) {
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
	
	@RequestMapping(value="/nothanksgame/startgame", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> startGame(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		board.startGame();
		board.updateDeck();
		board.updatePlayers();
		board.updateDB("status", board.getStatus());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/nothanksgame/receive", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> receive(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		board.getPlayerByName(username).receive();
		board.updatePlayer(username);
		board.updateDeck();
		board.updateDB("status", board.getStatus());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/nothanksgame/send", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> send(HttpServletRequest request,  @RequestParam int x){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		board.getPlayerByName(username).send(x);
		board.updatePlayer(username);
		board.updatePlayer(x);
		board.updateDeck();
		board.updateDB("curPlayer", board.getCurPlayer());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/nothanksgame/botnextmove", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> botNextMove(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		int index = board.getCurPlayer();
		if (board.getPlayers().get(index).isBot()) {
			Player p = board.getPlayers().get(index);
			int x = p.botNextMove();
			board.updatePlayer(p.getName());
			board.updateDeck();
			if (x>=0) {
				board.updatePlayer(x);
				board.updateDB("curPlayer", board.getCurPlayer());
			} else {
				board.updateDB("status", board.getStatus());
			}
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/nothanksgame/getboard", method = RequestMethod.GET)
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
