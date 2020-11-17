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
import com.cosine.cosgame.pokewhat.Player;
import com.cosine.cosgame.pokewhat.PokewhatConsts;
import com.cosine.cosgame.pokewhat.PokewhatMeta;
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
	
	@RequestMapping(value="/pokewhatendgame", method = RequestMethod.GET)
	public String pokewhatEndGame() {
		return "pokewhatEndGame";
	}
	
	@RequestMapping(value="/pokewhat/allboards", method = RequestMethod.GET)
	public ResponseEntity<StringEntity> allBoards(HttpServletRequest request){
		PokewhatMeta meta = new PokewhatMeta();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		StringEntity entity = meta.getBoardIdsAsStringEntity(username);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokewhatgame/newboard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> newBoard(HttpServletRequest request){
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
	
	@RequestMapping(value="/pokewhatgame/setboardid", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setboardid(HttpServletRequest request, @RequestParam String boardId) {
		HttpSession session = request.getSession(true);
		session.setAttribute("boardId", boardId);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokewhatgame/join", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> join(HttpServletRequest request) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getPlayerByName(username) == null) {
			board.addPlayer(username);
			Player p = board.getPlayerByName(username);
			p.setAvatar(board.getAvatars().get(0));
			board.addPlayerToDB(username);
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokewhatgame/addbot", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> addBot(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		board.addBot();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokewhatgame/kick", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> kick(HttpServletRequest request, @RequestParam int index) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getLord().contentEquals(username)) {
			board.removePlayerFromDB(index);
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokewhatgame/changefirstplayer", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> changeFirstPlayer(HttpServletRequest request, @RequestParam int index) {
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
	
	@RequestMapping(value="/pokewhatgame/changeavatar", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> changeAvatar(HttpServletRequest request, @RequestParam int x){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		Player p = board.getPlayerByName(username);
		if (p != null) {
			p.setAvatar(board.getAvatars().get(x));
			board.updatePlayer(username);
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokewhatgame/startgame", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> startGame(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		board.startGame();
		board.updatePmToChoose();
		board.updateDeck();
		board.updateAncient();
		board.updatePlayers();
		board.updatePlayedCards();
		board.updateLogs();
		board.updateDB("status", board.getStatus());
		board.updateDB("round", board.getRound());
		board.updateDB("turn", board.getTurn());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokewhatgame/choosepm", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> choosePm(HttpServletRequest request, @RequestParam int x){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		Player p = board.getPlayerByName(username);
		if (p != null) {
			board.choosePm(p.getIndex(), x);
			board.updatePlayers();
			board.updatePmToChoose();
			board.updateDeck();
			board.updateAncient();
			board.updatePlayedCards();
			board.updateLogs();
			board.updateDB("status", board.getStatus());
			board.updateDB("round", board.getRound());
			board.updateDB("turn", board.getTurn());
			board.updateDB("curPlayer", board.getCurPlayer());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokewhatgame/botchoosepm", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> botChoosePm(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		Player p = board.getPlayers().get(board.getCurPlayer());
		if (p.isBot()) {
			board.botChoosePm();
			board.updatePlayers();
			board.updatePmToChoose();
			board.updateDeck();
			board.updateAncient();
			board.updatePlayedCards();
			board.updateLogs();
			board.updateDB("status", board.getStatus());
			board.updateDB("round", board.getRound());
			board.updateDB("turn", board.getTurn());
			board.updateDB("curPlayer", board.getCurPlayer());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokewhatgame/botnextmove", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> botNextMove(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		Player p = board.getPlayers().get(board.getCurPlayer());
		if (p.isBot()) {
			p.activelyEndTurn();
			board.updatePlayers();
			board.updateDeck();
			board.updateLogs();
			board.updateDB("curPlayer", board.getCurPlayer());
			board.updateDB("turn", board.getTurn());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokewhatgame/usemove", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> useMove(HttpServletRequest request, @RequestParam int x){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		Player p = board.getPlayerByName(username);
		if (p.getPhase() == PokewhatConsts.USEMOVE) {
			p.useMove(x);
			board.updatePlayers();
			board.updateDeck();
			board.updateAncient();
			board.updatePlayedCards();
			board.updateLogs();
			board.updateDB("curPlayer", board.getCurPlayer());
			board.updateDB("status", board.getStatus());
			board.updateDB("round", board.getRound());
			board.updateDB("turn", board.getTurn());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokewhatgame/endturn", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> endturn(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		Player p = board.getPlayerByName(username);
		if (p.getPhase() == PokewhatConsts.USEMOVE) {
			p.activelyEndTurn();
			board.updatePlayers();
			board.updateDeck();
			board.updateLogs();
			board.updateDB("curPlayer", board.getCurPlayer());
			board.updateDB("turn", board.getTurn());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokewhatgame/dismiss", method = RequestMethod.POST)
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
