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

import com.cosine.cosgame.pokerworld.account.Account;
import com.cosine.cosgame.pokerworld.account.Shop;
import com.cosine.cosgame.pokerworld.account.Transaction;
import com.cosine.cosgame.pokerworld.Board;
import com.cosine.cosgame.pokerworld.Player;
import com.cosine.cosgame.pokerworld.entity.BoardEntity;
import com.cosine.cosgame.pokerworld.entity.AccountEntity;
import com.cosine.cosgame.pokerworld.Consts;
import com.cosine.cosgame.pokerworld.Meta;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class PokerworldController {
	@RequestMapping(value="/pokerworld", method = RequestMethod.GET)
	public String pokerworld() {
		return "pokerworldMain";
	}
	@RequestMapping(value="/pokerworldcreategame", method = RequestMethod.GET)
	public String pokerworldCreateGame() {
		return "pokerworldCreateGame";
	}
	@RequestMapping(value="/pokerworldgame", method = RequestMethod.GET)
	public String pokerworldGame() {
		return "pokerworldGame";
	}
	@RequestMapping(value="/pokerworld/newboard", method = RequestMethod.POST)
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
	@RequestMapping(value="/pokerworld/allboards", method = RequestMethod.GET)
	public ResponseEntity<StringEntity> allBoards(HttpServletRequest request){
		Meta meta = new Meta();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		StringEntity entity = meta.getBoardIdsAsStringEntity(username);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/pokerworld/setboardid", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setboardid(HttpServletRequest request, @RequestParam String boardId) {
		HttpSession session = request.getSession(true);
		session.setAttribute("boardId", boardId);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/pokerworld/join", method = RequestMethod.POST)
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
	@RequestMapping(value="/pokerworld/kick", method = RequestMethod.POST)
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
	@RequestMapping(value="/pokerworld/dismiss", method = RequestMethod.POST)
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
	@RequestMapping(value="/pokerworld/startgame", method = RequestMethod.POST)
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
				board.updateBasicDB();
				board.updateCardsDB();
				board.updatePlayers();
				board.updateDB("sequences", board.getSequences());
				board.updateDB("rawHidden", board.getRawHidden());
				board.updateDB("gameMode", board.getGameMode());
				board.updateDB("biggestRank", board.getBiggestRank());
				board.updateDB("extraCards", board.getExtraCards());
				board.updateDB("totalRounds", board.getTotalRounds());
				board.updateDB("fiveTenBonus", board.isFiveTenBonus());
				board.updateDominantDB();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/pokerworld/claimdominant", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> claimDominant(HttpServletRequest request, @RequestParam String dominantSuit, @RequestParam int numDominant){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				board.claimDominant(dominantSuit, numDominant, p.getInnerId());
				board.updateDominantDB();
				board.updatePlayer(username);
			}
			
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/pokerworld/enddistribute", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> endDistribute(HttpServletRequest request){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				//TODO: change this to confirm by Player
				board.endDistribute(p.getInnerId());
				board.updateBasicDB();
				board.updatePlayers();
				board.updateCardsDB();
				board.updateDB("firstPlayer", board.getFirstPlayer());
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/pokerworld/playcards", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> playCards(HttpServletRequest request, @RequestParam List<Integer> playedIndex){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.play(playedIndex);
				board.updateBasicDB();
				board.updatePlayers();
				board.updateCardsDB();
				board.updateDominantDB();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokerworld/confirmendgame", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> confirmEndGame(HttpServletRequest request){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			if (board.getLord().contentEquals(username)) {
				board.confirmEndGame();
				board.updateBasicDB();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokerworld/confirmendturn", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> confirmEndTurn(HttpServletRequest request){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			board.confirmNextTurn(username);
			board.updateBasicDB();
			board.updatePlayers();
			board.updateCardsDB();
			/*
			Player p = board.getPlayerByName(username);
			if (p != null) {
				board.hide(p.getInnerId(),playedIndex);
				board.updateBasicDB();
				board.updatePlayers();
				board.updateCardsDB();
				board.updateDB("rawHidden", board.getRawHidden());
			}
			*/
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/pokerworld/confirmhide", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> confirmHide(HttpServletRequest request, @RequestParam List<Integer> playedIndex){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				board.hide(p.getInnerId(),playedIndex);
				board.updateBasicDB();
				board.updatePlayers();
				board.updateCardsDB();
				board.updateDB("rawHidden", board.getRawHidden());
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokerworld/wizard/bid", method = RequestMethod.PUT)
	public ResponseEntity<StringEntity> bidWizard(HttpServletRequest request, @RequestParam int bid){
		StringEntity entity = new StringEntity();
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		if (board.exists(boardId)) {
			board.getFromDB(boardId);
			Player p = board.getPlayerByName(username);
			if (p != null) {
				p.addBid(bid);
				if (board.allBid()) {
					board.setStatus(Consts.PLAYCARDS);
				}
				board.updateBasicDB();
				board.updatePlayers();
				//board.updateCardsDB();
			}
		} else {
			board.setId("NE");
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokerworld/getboard", method = RequestMethod.GET)
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
	
	
	
	
	
	
	
	
	
	
	
	// account handles
	@RequestMapping(value="/pokerworld/dailyreward", method = RequestMethod.POST)
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
	
	
	@RequestMapping(value="/pokerworld/accountinfo", method = RequestMethod.GET)
	public ResponseEntity<AccountEntity> accountInfo(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		AccountEntity entity = account.toAccountEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokerworld/cleanaccount", method = RequestMethod.POST)
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
	
	@RequestMapping(value="/pokerworld/dig", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> dig(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Shop shop = new Shop();
		Account account = new Account();
		account.getFromDB(username);
		String rewardMsg = shop.dig(account);
		List<String> ls = new ArrayList<>();
		ls.add(rewardMsg);
		StringEntity entity = new StringEntity();
		entity.setValue(ls);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokerworld/openchest", method = RequestMethod.POST)
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
	
	@RequestMapping(value="/pokerworld/buy", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> buy(HttpServletRequest request, @RequestParam int option) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		Shop shop = new Shop();
		shop.buy(account, option);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pokerworld/chooseskin", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> chooseSkin(HttpServletRequest request, @RequestParam int skinId) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		account.chooseSkin(skinId);
		account.updateAccountDB(username);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/pokerworld/cancelchooseskin", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> cancelChooseSkin(HttpServletRequest request, @RequestParam int skinId) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Account account = new Account();
		account.getFromDB(username);
		account.cancelChooseSkin(skinId);
		account.updateAccountDB(username);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
}
