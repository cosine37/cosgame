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
	
	@RequestMapping(value="/citadelscreategame", method = RequestMethod.GET)
	public String citadelsCreateGame() {
		return "citadelsCreateGame";
	}
	
	@RequestMapping(value="/citadelsgame", method = RequestMethod.GET)
	public String citadelsGame() {
		return "citadelsGame";
	}
	
	@RequestMapping(value="/citadelsgame/newboard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> citadelsNewBoard(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		board.addPlayer(username);
		board.setLord(username);
		board.genBoardId();
		session.setAttribute("boardId", board.getId());
		board.storeToDB();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/givecrown", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> giveCrown(HttpServletRequest request, @RequestParam int crownIndex){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		board.setCrown(crownIndex);
		board.updateDB("crown", board.getCrown());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/addbot", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> addBot(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		board.addBot();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/start", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> citadelsGameStart(HttpServletRequest request) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		board.gameSetup();
		session.setAttribute("boardId", board.getId());
		board.updateDeck();
		board.updateRoles();
		board.updatePlayers();
		board.updateDB("status", board.getStatus());
		board.updateDB("curPlayer", board.getCurPlayer());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/join", method = RequestMethod.POST)
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
	
	@RequestMapping(value="/citadelsgame/setboardid", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setboardid(HttpServletRequest request, @RequestParam String boardId) {
		HttpSession session = request.getSession(true);
		session.setAttribute("boardId", boardId);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	//This controller is for debugging purpose
	@RequestMapping(value="/citadelsgame/curplayerchooserole", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> curPlayerChooseRole(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		board.botChooseRole();
		board.updateCurPlayer();
		board.updateRoles();
		board.updateDB("status", board.getStatus());
		board.updateDB("curPlayer", board.getCurPlayer());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	//This controller is for debugging purpose
	@RequestMapping(value="/citadelsgame/startturn", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> startTurn(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getPlayerByName(username) != null) {
			board.getPlayerByName(username).startTurn();
			board.updatePlayer(username);
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/endturn", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> endTurn(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getPlayerByName(username) != null) {
			board.getPlayerByName(username).endTurn();
			board.updatePlayer(username);
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="citadelsgame/chooserole", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> chooseRole(HttpServletRequest request, @RequestParam int index){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		board.botChooseRole();
		board.getPlayerByName(username).chooseRole(index);
		board.updatePlayer(username);
		board.updateRoles();
		board.updateDB("status", board.getStatus());
		board.updateDB("curPlayer", board.getCurPlayer());
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
			board.getPlayerByName(username).takeActionOption(2);
			board.updatePlayer(username);
			board.updateDB("coins", board.getCoins());
		}
		
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/seecards", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> seeCards(HttpServletRequest request) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getPlayerByName(username) != null) {
			board.getPlayerByName(username).takeActionOption(1);
			board.updatePlayer(username);
			board.updateDeck();
		}
		
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/choosecard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> chooseCard(HttpServletRequest request, @RequestParam int index){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getPlayerByName(username) != null) {
			board.getPlayerByName(username).chooseCard(index);
			board.updatePlayer(username);
			board.updateDeck();
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