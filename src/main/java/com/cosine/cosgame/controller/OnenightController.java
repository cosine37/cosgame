package com.cosine.cosgame.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cosine.cosgame.onenight.Board;
import com.cosine.cosgame.onenight.BoardEntity;
import com.cosine.cosgame.onenight.OnenightMeta;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class OnenightController {
	@RequestMapping(value="/onenight", method = RequestMethod.GET)
	public String onenight() {
		return "onenightMain";
	}
	
	@RequestMapping(value="/onenightcreategame", method = RequestMethod.GET)
	public String onenightCreateGame() {
		return "onenightCreateGame";
	}
	
	@RequestMapping(value="/onenightgame", method = RequestMethod.GET)
	public String onenightGame() {
		return "onenightGame";
	}
	
	@RequestMapping(value="/onenight/newboard", method = RequestMethod.POST)
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
	
	@RequestMapping(value="/onenight/allboards", method = RequestMethod.GET)
	public ResponseEntity<StringEntity> allBoards(HttpServletRequest request){
		OnenightMeta meta = new OnenightMeta();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		StringEntity entity = meta.getBoardIdsAsStringEntity(username);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/onenight/setboardid", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setboardid(HttpServletRequest request, @RequestParam String boardId) {
		HttpSession session = request.getSession(true);
		session.setAttribute("boardId", boardId);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/onenight/join", method = RequestMethod.POST)
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
	
	@RequestMapping(value="/onenightgame/addbot", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> addBot(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getLord().contentEquals(username)) {
			board.addBot();
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/onenightgame/startgame", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> startGame(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getLord().contentEquals(username)) {
			board.startGame();
			board.updatePlayers();
			board.updateDB("status", board.getStatus());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/onenightgame/setroles", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setRoles(HttpServletRequest request, @RequestParam List<Integer> roles){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getLord().contentEquals(username)) {
			board.setRolesThisGameByInt(roles);
			board.updateRolesThisGame();
			board.updateDB("canNight", board.isCanNight());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
		
	@RequestMapping(value="/onenightgame/night", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> night(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getLord().contentEquals(username) && board.isCanNight()) {
			board.distributeRoles();
			board.updateDB("status", board.getStatus());
			board.updateDB("confirmed", board.getConfirmed());
			board.updatePlayers();
			board.updateCenterRoles();
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/onenightgame/useskill", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> useSkill(HttpServletRequest request, @RequestParam List<Integer> targets){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		Player p = board.getPlayerByName(username);
		if (p != null) {
			p.getInitialRole().useSkill(targets);
			board.confirm(p.getIndex());
			board.updateDB("status", board.getStatus());
			board.updateDB("confirmed", board.getConfirmed());
			board.updatePlayers();
			board.updateCenterRoles();
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/onenightgame/confirmnight", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> confirmNight(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		Player p = board.getPlayerByName(username);
		if (p != null) {
			board.confirm(p.getIndex());
			board.updateDB("status", board.getStatus());
			board.updateDB("confirmed", board.getConfirmed());
			board.updatePlayers();
			board.updateCenterRoles();
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/onenightgame/getboard", method = RequestMethod.GET)
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