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

import com.cosine.cosgame.threechaodoms.entity.AccountEntity;
import com.cosine.cosgame.threechaodoms.entity.BoardEntity;
import com.cosine.cosgame.threechaodoms.entity.CardEntity;
import com.cosine.cosgame.threechaodoms.entity.ShopEntity;
import com.cosine.cosgame.threechaodoms.shop.Account;
import com.cosine.cosgame.threechaodoms.shop.Shop;
import com.cosine.cosgame.threechaodoms.shop.Transaction;
import com.cosine.cosgame.threechaodoms.Board;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Meta;
import com.cosine.cosgame.threechaodoms.Player;
import com.cosine.cosgame.threechaodoms.Skin;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class ThreechaodomsController {
	@RequestMapping(value="/threechaodoms", method = RequestMethod.GET)
	public String threechaodoms() {
		return "threechaodomsMain";
	}
	
	@RequestMapping(value="/threechaodomscreategame", method = RequestMethod.GET)
	public String threechaodomsCreateGame() {
		return "threechaodomsCreateGame";
	}
	
	@RequestMapping(value="/threechaodomsgame", method = RequestMethod.GET)
	public String threechaodomsGame() {
		return "threechaodomsGame";
	}
	
	@RequestMapping(value="/threechaodoms/accountinfo", method = RequestMethod.GET)
	public ResponseEntity<AccountEntity> accountInfo(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		AccountEntity entity = account.toAccountEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/threechaodoms/dig", method = RequestMethod.POST)
	public ResponseEntity<CardEntity> dig(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		Shop shop = new Shop();
		CardEntity entity = shop.dig(account);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/threechaodoms/shopinfo", method = RequestMethod.GET)
	public ResponseEntity<ShopEntity> shopInfo(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Shop shop = new Shop();
		ShopEntity entity = shop.toShopEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/threechaodoms/cleanaccount", method = RequestMethod.POST)
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
	
	@RequestMapping(value="/threechaodoms/dailyreward", method = RequestMethod.POST)
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
	
	@RequestMapping(value="/threechaodoms/buyskin", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> buySkin(HttpServletRequest request, @RequestParam int id) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		Shop shop = new Shop();
		Skin s = shop.genSkin(id);
		if (s != null && account.canAffordSkin(s)) {
			account.buySkin(s);
			account.updateAccountDB(username);
		}
		
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/threechaodoms/useskin", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> useSkin(HttpServletRequest request, @RequestParam int id) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		account.useSkin(id);
		account.updateAccountDB(username);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/threechaodoms/unuseskin", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> unuseSkin(HttpServletRequest request, @RequestParam int id) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		account.unuseSkin(id);
		account.updateAccountDB(username);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/threechaodoms/newboard", method = RequestMethod.POST)
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
	@RequestMapping(value="/threechaodoms/allboards", method = RequestMethod.GET)
	public ResponseEntity<StringEntity> allBoards(HttpServletRequest request){
		Meta meta = new Meta();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		StringEntity entity = meta.getBoardIdsAsStringEntity(username);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/threechaodoms/setboardid", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setboardid(HttpServletRequest request, @RequestParam String boardId) {
		HttpSession session = request.getSession(true);
		session.setAttribute("boardId", boardId);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/threechaodoms/join", method = RequestMethod.POST)
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
	@RequestMapping(value="/threechaodoms/kick", method = RequestMethod.POST)
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
	@RequestMapping(value="/threechaodoms/startgame", method = RequestMethod.POST)
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
				//board.updateDB("firstPlayer", board.getFirstPlayer());
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/threechaodoms/setuphand", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setupHand(HttpServletRequest request, @RequestParam int jail, @RequestParam int exile){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.setupHand(jail, exile);
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/threechaodoms/play", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> playCard(HttpServletRequest request, @RequestParam int cardIndex, @RequestParam List<Integer> targets){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.playCard(cardIndex, targets);
				//System.out.println(p.getHand().size());
				//System.out.println(p.getPlay().size());
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/threechaodoms/exilecards", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> exileCards(HttpServletRequest request, @RequestParam List<Integer> targets){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.exileCards(targets);
				//System.out.println(p.getHand().size());
				//System.out.println(p.getPlay().size());
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/threechaodoms/recruit", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> recruit(HttpServletRequest request, @RequestParam int target){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.recruit(target);
				//System.out.println(p.getHand().size());
				//System.out.println(p.getPlay().size());
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/threechaodoms/discard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> discard(HttpServletRequest request, @RequestParam int target){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.discard(target);
				//System.out.println(p.getHand().size());
				//System.out.println(p.getPlay().size());
				board.updateBasicDB();
				board.updatePlayers();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/threechaodoms/getboard", method = RequestMethod.GET)
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
