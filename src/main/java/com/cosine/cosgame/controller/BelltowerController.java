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

import com.cosine.cosgame.belltower.Meta;
import com.cosine.cosgame.belltower.Player;
import com.cosine.cosgame.belltower.Board;
import com.cosine.cosgame.belltower.Consts;
import com.cosine.cosgame.belltower.entity.AccountEntity;
import com.cosine.cosgame.belltower.entity.BoardEntity;
import com.cosine.cosgame.belltower.shop.Account;
import com.cosine.cosgame.belltower.shop.Shop;
import com.cosine.cosgame.belltower.shop.Transaction;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class BelltowerController {
	@RequestMapping(value="/belltower", method = RequestMethod.GET)
	public String propnight() {
		return "belltowerMain";
	}
	
	@RequestMapping(value="/belltowercreategame", method = RequestMethod.GET)
	public String propnightCreateGame() {
		return "belltowerCreateGame";
	}
	
	@RequestMapping(value="/belltowergame", method = RequestMethod.GET)
	public String propnightGame() {
		return "belltowerGame";
	}
	@RequestMapping(value="/belltower/accountinfo", method = RequestMethod.GET)
	public ResponseEntity<AccountEntity> accountInfo(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		AccountEntity entity = account.toAccountEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/dig", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> dig(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		Shop shop = new Shop();
		String rewardMsg = shop.dig(account);
		List<String> ls = new ArrayList<>();
		ls.add(rewardMsg);
		StringEntity entity = new StringEntity();
		entity.setValue(ls);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/openchest", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> openChest(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		Shop shop = new Shop();
		List<String> ls = shop.openChest(account);
		StringEntity entity = new StringEntity();
		entity.setValue(ls);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/cleanaccount", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> cleanAccount(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		account.cleanAccount();
		account.updateAccountDB(username);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/belltower/dailyreward", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> dailyReward(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		Shop shop = new Shop();
		List<Transaction> ts = shop.dailyReward();
		account.addNewTransactions(ts);
		account.updateAccountDB(username);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/belltowerevent", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> belltowerEvent(HttpServletRequest request, @RequestParam int amount, @RequestParam String msg) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		Shop shop = new Shop();
		List<Transaction> ts = shop.belltowerEvent(amount, msg);
		account.addNewTransactions(ts);
		account.updateAccountDB(username);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/addcharacter", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> addCharacter(HttpServletRequest request, @RequestParam int charId) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		account.addCharacter(charId);
		account.updateAccountDB(username);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/newboard", method = RequestMethod.POST)
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
	@RequestMapping(value="/belltower/changedisplayname", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> changeDisplayName(HttpServletRequest request, @RequestParam String displayName){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.setDisplayName(displayName);
				//board.updateBasicDB();
				//board.updatePlayers();
				board.updatePlayer(p.getName());
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/changeicon", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> changeIcon(HttpServletRequest request, @RequestParam List<Integer> icon){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.assignIcon(icon);
				//board.updateBasicDB();
				//board.updatePlayers();
				board.updatePlayer(p.getName());
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/addbot", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> addBot(HttpServletRequest request){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			if (board.isLord(username)) {
				board.addBot();
				//board.updateBasicDB();
				//board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/startgame", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> startGame(HttpServletRequest request, @RequestParam List<Integer> settings){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			if (board.isLord(username)) {
				//board.setSettings(settings);
				board.startGame();
				board.updateDB("script", board.getScript().getId());
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/submitgroupnumbers", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> submitGroupNumbers(HttpServletRequest request, @RequestParam List<Integer> groupNumbers){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			if (board.isLord(username)) {
				//board.setSettings(settings);
				//board.startGame();
				board.setGroupCounts(groupNumbers);
				board.startFirstNight();
				board.updateDB("groupCounts",board.getGroupCounts());
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/confirmnight", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> submitNight(HttpServletRequest request, @RequestParam List<Integer> targets){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.confirmNight(targets);
				if (board.allConfirmedNight()) {
					board.endNight();
					// TODO: May be removed later;
					board.startDay();
				}
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/nominate", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> nominate(HttpServletRequest request, @RequestParam int target){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				if (p.getIndex() == board.getCurNominator()) {
					board.nominate(target);
					board.updateBasicDB();
					board.updatePlayers();
				}
				
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/vote", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> vote(HttpServletRequest request, @RequestParam boolean f){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				if (p.getIndex() == board.getCurVoter()) {
					board.vote(f);
					board.updateBasicDB();
					board.updatePlayers();
				}
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/confirmday", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> submitDay(HttpServletRequest request){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.confirmDay();
				if (board.allConfirmedDay()) {
					board.endDay();
					board.startNight();
				}
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/setboardid", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setboardid(HttpServletRequest request, @RequestParam String boardId) {
		HttpSession session = request.getSession(true);
		session.setAttribute("boardId", boardId);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/join", method = RequestMethod.POST)
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
	@RequestMapping(value="/belltower/kick", method = RequestMethod.POST)
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
	@RequestMapping(value="/belltower/allboards", method = RequestMethod.GET)
	public ResponseEntity<StringEntity> allBoards(HttpServletRequest request){
		Meta meta = new Meta();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		StringEntity entity = meta.getBoardIdsAsStringEntity(username);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/belltower/getboard", method = RequestMethod.GET)
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
