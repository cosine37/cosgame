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

import com.cosine.cosgame.marshbros.Board;
import com.cosine.cosgame.marshbros.Meta;
import com.cosine.cosgame.marshbros.Player;
import com.cosine.cosgame.marshbros.BoardEntity;
import com.cosine.cosgame.marshbros.Consts;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class MarshbrosController {
	@RequestMapping(value="/marshbros", method = RequestMethod.GET)
	public String marshbros() {
		return "marshbrosMain";
	}
	@RequestMapping(value="/marshbroscreategame", method = RequestMethod.GET)
	public String marshbrosCreateGame() {
		return "marshbrosCreateGame";
	}
	@RequestMapping(value="/marshbrosgame", method = RequestMethod.GET)
	public String marshbrosGame() {
		return "marshbrosGame";
	}
	@RequestMapping(value="/marshbros/newboard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> newBoard(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		board.addPlayer(username);
		board.setLord(username);
		board.genBoardId();
		board.setStatus(0);
		board.storeToDB();
		session.setAttribute("boardId", board.getId());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/marshbros/allboards", method = RequestMethod.GET)
	public ResponseEntity<StringEntity> allBoards(HttpServletRequest request){
		Meta meta = new Meta();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		StringEntity entity = meta.getBoardIdsAsStringEntity(username);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/marshbros/setboardid", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setboardid(HttpServletRequest request, @RequestParam String boardId) {
		HttpSession session = request.getSession(true);
		session.setAttribute("boardId", boardId);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/marshbros/join", method = RequestMethod.POST)
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
	@RequestMapping(value="/marshbros/startgame", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> startGame(HttpServletRequest request){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			board.startGame();
			board.updateBasicDB();
			board.updatePlayers();
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/marshbros/endaction", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> endaction(HttpServletRequest request) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				if (p.getPhase() == Consts.ACTION) {
					board.addNextPhaseAsk(p);
					board.resolveAutoAsks();
					board.updateBasicDB();
					board.updatePlayers();
				}
			}
			
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/marshbros/draw", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> draw(HttpServletRequest request) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				if (p.getPhase() == Consts.REC1 || p.getPhase() == Consts.REC2) {
					board.addNextPhaseAsk(p);
					p.draw();
					board.resolveAutoAsks();
					board.updateBasicDB();
					board.updatePlayers();
				}
			}
			
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/marshbros/appoint", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> appoint(HttpServletRequest request, @RequestParam int index) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				board.addNextPhaseAsk(p);
				p.appoint(index);
				board.resolveAutoAsks();
				board.updateBasicDB();
				board.updatePlayers();
			}
			
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/marshbros/raid", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> raid(HttpServletRequest request, @RequestParam int index) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				if (p.allActioned(index)) {
					board.addNextPhaseAsk(p);
				}
				p.getArea().get(index).raid();
				board.resolveAutoAsks();
				board.updateBasicDB();
				board.updatePlayer(username);
			}
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/marshbros/attack", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> attack(HttpServletRequest request, @RequestParam int index, @RequestParam int attackPlayer, @RequestParam int attackRole) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				if (p.allActioned(index)) {
					board.addNextPhaseAsk(p);
				}
				p.getArea().get(index).attack(attackPlayer, attackRole);
				board.resolveAutoAsks();
				board.updateBasicDB();
				board.updatePlayers();
			}
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/marshbros/action", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> action(HttpServletRequest request, @RequestParam int index) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				if (p.allActioned(index)) {
					board.addNextPhaseAsk(p);
				}
				p.getArea().get(index).action();
				board.resolveAutoAsks();
				board.updateBasicDB();
				board.updatePlayers();
			}
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/marshbros/resolveaction", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> resolveAsk(HttpServletRequest request, @RequestParam int index, @RequestParam int target) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.getArea().get(index).resolveAction(target);
				board.resolveAutoAsks();
				board.updateBasicDB();
				board.updatePlayers();
			}
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/marshbros/sacrifice", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> sacrifice(HttpServletRequest request, @RequestParam int roleIndex) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.sacrifice(roleIndex);
				if (p.getArea().size()<=3) {
					board.addNextPhaseAsk(p);
				}
				board.resolveAutoAsks();
				board.updateBasicDB();
				board.updatePlayers();
			}
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/marshbros/getboard", method = RequestMethod.GET)
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
