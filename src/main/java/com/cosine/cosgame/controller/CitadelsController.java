package com.cosine.cosgame.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Board;
import com.cosine.cosgame.citadels.BoardEntity;
import com.cosine.cosgame.citadels.CitadelsMeta;
import com.cosine.cosgame.citadels.Player;
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
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		StringEntity entity = meta.getBoardIdsAsStringEntity(username);
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
	
	@RequestMapping(value="/citadelsendgame", method = RequestMethod.GET)
	public String citadelsEndGame() {
		return "citadelsEndGame";
	}
	
	@RequestMapping(value="/citadelsgame/empty", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> empty(HttpServletRequest request){
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
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
	
	@RequestMapping(value="/citadelsgame/setendnum", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setendnum(HttpServletRequest request, @RequestParam int endnum){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		board.setFinishCount(endnum);
		board.updateDB("finishCount", board.getFinishCount());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/setregicide", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setRegicide(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.isRegicide()) {
			board.setRegicide(false);
		} else {
			board.setRegicide(true);
		}
		board.updateDB("regicide", board.isRegicide());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/setno9", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setNo9(HttpServletRequest request, @RequestParam int no9){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		board.setNo9(no9);
		board.updateDB("no9", no9);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/setuseduocolor", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setUseDuoColor(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.isUseDuoColor()) {
			board.setUseDuoColor(false);
		} else {
			board.setUseDuoColor(true);
		}
		board.updateDB("useDuoColor", board.isUseDuoColor());
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
		board.updateDB("no9", board.getNo9());
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
		board.updateDeck();
		board.updateRoles();
		board.updatePlayers();
		board.updateDB("coins", board.getCoins());
		board.updateDB("status", board.getStatus());
		board.updateDB("curPlayer", board.getCurPlayer());
		board.updateDB("roundCount", board.getRoundCount());
		board.updateLogs();
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
			board.updateDB("no9", board.getNo9());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/kick", method = RequestMethod.POST)
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
	
	@RequestMapping(value="/citadelsgame/dismiss", method = RequestMethod.POST)
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
		String name = board.getPlayers().get(board.getCurPlayer()).getName();
		board.botChooseRole();
		board.updatePlayer(name);
		board.updateRoles();
		board.updateDB("status", board.getStatus());
		board.updateDB("curPlayer", board.getCurPlayer());
		board.updateDB("curRoleNum", board.getCurRoleNum());
		board.updateDB("crown", board.getCrown());
		if (board.isCrownMoved()) {
			board.updateTrackCrownPlayers();
		}
		board.updateCurPlayer();
		board.updateLogs();
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
			board.updateLogs();
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
			board.updateCurPlayer();
			board.updateDB("status", board.getStatus());
			board.updateDB("curPlayer", board.getCurPlayer());
			board.updateDB("roundCount", board.getRoundCount());
			board.updateDB("curRoleNum", board.getCurRoleNum());
			board.updateDB("killedRole", board.getKilledRole());
			board.updateDB("stealedRole", board.getStealedRole());
			board.updateDB("crown", board.getCrown());
			board.updateDB("coins", board.getCoins());
			board.updateDB("tempRevealedTop", board.getTempRevealedTop());
			if (board.isCrownMoved()) {
				board.updateTrackCrownPlayers();
			}
			board.updateOtherAssassinPlayers();
			board.updateInsuredPlayers();
			board.updateThief();
			board.updateRoles();
			board.updateDeck();
			board.updateLogs();
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
		board.getPlayerByName(username).chooseRole(index);
		board.updatePlayer(username);
		board.updateRoles();
		board.updateDB("status", board.getStatus());
		board.updateDB("curPlayer", board.getCurPlayer());
		board.updateDB("curRoleNum", board.getCurRoleNum());
		board.updateDB("crown", board.getCrown());
		if (board.isCrownMoved()) {
			board.updateTrackCrownPlayers();
		}
		board.updateCurPlayer();
		board.updateLogs();
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
			board.updateDB("tempRevealedTop", board.getTempRevealedTop());
			board.updateDB("coins", board.getCoins());
			board.updateDeck();
			board.updateLogs();
		}
		
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/chooseskill", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> chooseSkill(HttpServletRequest request, @RequestParam int index) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getPlayerByName(username) != null) {
			Player p = board.getPlayerByName(username);
			if (board.getPlayerByName(username).getCanUseRoleSkill().get(index).contentEquals("y")) {
				Ask ask = p.getRole().chooseSkill(index);
				p.setAsk(ask);
				board.updatePlayer(username);
				board.updateLogs();
				board.updateDeck();
				board.updateDB("coins", board.getCoins());
			}
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/useskill", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> useSkill(HttpServletRequest request, @RequestParam int skillIndex, @RequestParam int roleIndex, @RequestParam int playerIndex, @RequestParam int builtIndex) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getPlayerByName(username) != null) {
			Player p = board.getPlayerByName(username);
			Ask ask = p.getRole().useSkill(skillIndex, roleIndex, playerIndex, builtIndex, 0);
			p.setAsk(ask);
			board.updatePlayer(username);
			board.updatePlayer(playerIndex);
			board.updateDB("killedRole", board.getKilledRole());
			board.updateDB("stealedRole", board.getStealedRole());
			board.updateLogs();
			board.updateDeck();
			board.updateDB("coins", board.getCoins());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/useskillonhand", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> useSkillOnHand(HttpServletRequest request, @RequestParam int skillIndex, @RequestParam String handChoices) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getPlayerByName(username) != null) {
			Player p = board.getPlayerByName(username);
			Ask ask = p.getRole().useSkillOnHand(skillIndex, handChoices);
			p.setAsk(ask);
			board.updatePlayer(username);
			board.updateLogs();
			board.updateDeck();
			board.updateDB("coins", board.getCoins());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/choosecardskill", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> chooseCardSkill(HttpServletRequest request, @RequestParam int builtIndex){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getPlayerByName(username) != null) {
			Player p = board.getPlayerByName(username);
			Ask ask = p.getBuilt().get(builtIndex).chooseSkill(builtIndex, 0);
			p.setAsk(ask);
			board.updatePlayer(username);
			board.updateLogs();
			board.updateDeck();
			board.updateDB("tempRevealedTop", board.getTempRevealedTop());
			board.updateDB("coins", board.getCoins());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/usecardskill", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> useCardSkill(HttpServletRequest request, @RequestParam int builtIndex, @RequestParam int x){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getPlayerByName(username) != null) {
			Player p = board.getPlayerByName(username);
			Ask ask = p.getBuilt().get(builtIndex).useSkill(builtIndex, x);
			p.setAsk(ask);
			board.updatePlayer(username);
			board.updateLogs();
			board.updateDeck();
			board.updateDB("tempRevealedTop", board.getTempRevealedTop());
			board.updateDB("coins", board.getCoins());
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/cancelskill", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> cancelSkill(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		if (board.getPlayerByName(username) != null) {
			Player p = board.getPlayerByName(username);
			p.cancelSkill();
			board.updatePlayer(username);
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
			board.updateDB("coins", board.getCoins());
			board.updatePlayer(username);
			board.updateDeck();
			board.updateLogs();
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
			board.updateDB("tempRevealedTop", board.getTempRevealedTop());
			board.updateDB("coins", board.getCoins());
			board.updateDeck();
			board.updateLogs();
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
		board.getFromDB(boardId);
		if (board.getPlayerByName(username) != null) {
			board.getPlayerByName(username).build(index);
			System.out.println(board.getPlayerByName(username).getCoin());
			board.updatePlayer(username);
			board.updateDB("coins", board.getCoins());
			board.updateDB("crown", board.getCrown());
			board.updateDB("firstFinished", board.isFirstFinished());
			if (board.isCrownMoved()) {
				board.updateTrackCrownPlayers();
			}
			board.updateDeck();
			board.updateLogs();
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/botnextmove", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> botnextmove(HttpServletRequest request) {
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		board.getFromDB(boardId);
		String name = board.getPlayers().get(board.getCurPlayer()).getName();
		board.botNextMove();
		board.updatePlayer(name);
		board.updateDB("status", board.getStatus());
		board.updateCurPlayer();
		board.updateDB("curPlayer", board.getCurPlayer());
		board.updateDB("curRoleNum", board.getCurRoleNum());
		board.updateDB("roundCount", board.getRoundCount());
		board.updateDB("killedRole", board.getKilledRole());
		board.updateDB("stealedRole", board.getStealedRole());
		board.updateDB("firstFinished", board.isFirstFinished());
		board.updateDB("coins", board.getCoins());
		board.updateDB("crown", board.getCrown());
		board.updateDB("tempRevealedTop", board.getTempRevealedTop());
		if (board.isCrownMoved()) {
			board.updateTrackCrownPlayers();
		}
		board.updateOtherAssassinPlayers();
		board.updateInsuredPlayers();
		board.updateThief();
		board.updateRoles();
		board.updateLogs();
		board.updateDeck();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/citadelsgame/getboard", method = RequestMethod.GET)
	public ResponseEntity<BoardEntity> citadelsGameGetBoard(HttpServletRequest request){
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