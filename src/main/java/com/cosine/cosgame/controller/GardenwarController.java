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

import com.cosine.cosgame.gardenwar.Board;
import com.cosine.cosgame.gardenwar.Consts;
import com.cosine.cosgame.gardenwar.Meta;
import com.cosine.cosgame.gardenwar.Player;
import com.cosine.cosgame.gardenwar.entity.BoardEntity;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class GardenwarController {
	@RequestMapping(value="/gardenwar", method = RequestMethod.GET)
	public String gardenwar() {
		return "gardenwarMain";
	}
	
	@RequestMapping(value="/gardenwarcreategame", method = RequestMethod.GET)
	public String gardenwarCreateGame() {
		return "gardenwarCreateGame";
	}
	
	@RequestMapping(value="/gardenwargame", method = RequestMethod.GET)
	public String gardenwarGame() {
		return "gardenwarGame";
	}
	@RequestMapping(value="/gardenwar/newboard", method = RequestMethod.POST)
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
	@RequestMapping(value="/gardenwar/allboards", method = RequestMethod.GET)
	public ResponseEntity<StringEntity> allBoards(HttpServletRequest request){
		Meta meta = new Meta();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		StringEntity entity = meta.getBoardIdsAsStringEntity(username);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/gardenwar/setboardid", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setboardid(HttpServletRequest request, @RequestParam String boardId) {
		HttpSession session = request.getSession(true);
		session.setAttribute("boardId", boardId);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/gardenwar/join", method = RequestMethod.POST)
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
	@RequestMapping(value="/gardenwar/kick", method = RequestMethod.POST)
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
	@RequestMapping(value="/gardenwar/startgame", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> startGame(HttpServletRequest request, @RequestParam List<Integer> settings){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			if (board.isLord(username)) {
				board.setSettings(settings);
				board.startGame();
				board.updateDB("firstPlayer", board.getFirstPlayer());
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/gardenwar/play", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> play(HttpServletRequest request, @RequestParam int x){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.playCard(x);
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/gardenwar/activate", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> activate(HttpServletRequest request, @RequestParam int x){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.activateEquip(x);
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/gardenwar/resolve", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> resolve(HttpServletRequest request, @RequestParam List<Integer> targets){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.resolveCard(targets);
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/gardenwar/autoplay", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> autoplay(HttpServletRequest request){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.autoplay();
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/gardenwar/buybasic", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> buyBasic(HttpServletRequest request, @RequestParam int x){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.buyBasic(x);
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/gardenwar/buy", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> buy(HttpServletRequest request, @RequestParam int x){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.buy(x);
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/gardenwar/nextphase", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> nextPhase(HttpServletRequest request){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.nextPhase();
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/gardenwar/getboard", method = RequestMethod.GET)
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
