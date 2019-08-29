package com.cosine.cosgame.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Board;
import com.cosine.cosgame.dominion.BoardEntity;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.CardFactory;
import com.cosine.cosgame.dominion.Pile;
import com.cosine.cosgame.dominion.Player;
import com.cosine.cosgame.dominion.PlayerEntity;
import com.cosine.cosgame.security.LoginInterceptor;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class DominionGameController {
	
	protected static final Log logger = LogFactory.getLog(DominionGameController.class);
	
	Board board;
	
	@RequestMapping(value="/dominiongame/islord", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> isLord(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		String username = (String) session.getAttribute("username");
		Board board = new Board();
		board.getBoardFromDB(boardId);
		String ans = "not Lord";
		if (board.getLord().equals(username)) {
			ans = "Lord";
		}
		StringEntity entity = new StringEntity();
		List<String> value = new ArrayList<String>();
		value.add(ans);
		entity.setValue(value);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@RequestMapping(value="/dominiongame/board/getact", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> getact(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String act = (String) session.getAttribute("act");
		StringEntity entity = new StringEntity();
		List<String> value = new ArrayList<String>();
		value.add(act);
		entity.setValue(value);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@RequestMapping(value="/dominiongame/newboard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> newboard(HttpServletRequest request, @RequestParam int numPlayers) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		StringEntity entity = new StringEntity();
		board = new Board();
		board.createBoard(username, numPlayers);
		board.randomize();
		board.storeBoardToDB();
		List<String> value = new ArrayList<String>();
		value.add(board.getBoardId());
		session.setAttribute("boardId", board.getBoardId());
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/enterboard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> enterboard(HttpServletRequest request, @RequestParam String boardId) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		board = new Board();
		board.getBoardFromDB(boardId);
		board.addPlayer(username, true);
		session.setAttribute("boardId", board.getBoardId());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/playernames", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> playernames(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		Board board = new Board();
		board.getBoardFromDB(boardId);
		StringEntity entity = new StringEntity();
		List<String> value = board.getPlayerNames();
		entity.setValue(value);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/playerstatus", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> playerstatus(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		Board board = new Board();
		board.getBoardFromDB(boardId);
		StringEntity entity = new StringEntity();
		List<String> value = new ArrayList<String>();
		for (int i=0;i<board.getPlayers().size();i++) {
			if (board.getPlayers().get(i).getIsBot()) {
				value.add("Bot Ready");
			} else {
				if (board.getPlayers().get(i).getIsGoodToGo()) {
					value.add("Ready");
				} else {
					value.add("Not Ready");
				}
			}
		}
		entity.setValue(value);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/addbot", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> addbot(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		Board board = new Board();
		board.getBoardFromDB(boardId);
		board.addBot(true);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="dominiongame/kick", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> kick(HttpServletRequest request, @RequestParam String kickedName){
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		Board board = new Board();
		board.getBoardFromDB(boardId);
		board.kick(kickedName, true);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/leaveboard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> leaveboard(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		Board board = new Board();
		board.getBoardFromDB(boardId);
		board.removeSelfFromDB();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/resign", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> resign(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		Board board = new Board();
		board.getBoardFromDB(boardId);
		board.resign(username);
		
		//board.cleanPlayerDBs();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/endgamemsg", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> endgameMsg(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		Board board = new Board();
		board.getBoardFromDB(boardId);
		String endPlayer = board.getEndPlayer();
		String endType = board.getEndType();
		String endMsg = "";
		if (endPlayer.equals(username)) {
			if (endType.equals("resign")) {
				endMsg = "You resigned";
			} else if (endType.equals("win")) {
				endMsg = "You win";
			} else {
				
			}
		} else {
			if (endType.equals("resign")) {
				endMsg = endPlayer + " resigned";
			} else if (endType.equals("win")) {
				endMsg = "You lose";
			} else {
				
			}
		}
		List<String> value = new ArrayList<String>();
		value.add(endMsg);
		for (int i=0;i<board.getPlayers().size();i++) {
			endMsg = board.getPlayers().get(i).getName();
			endMsg = endMsg + ": " + Integer.toString(board.getPlayers().get(i).getScore());
			value.add(endMsg);
		}
		StringEntity entity = new StringEntity();
		entity.setValue(value);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/randomize", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> randomize(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		board = new Board();
		board.getBoardFromDB(boardId);
		//board.baseSetup();
		board.randomize();
		board.updateSupply();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/setup", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> initialize(HttpServletRequest request){
		board = new Board();
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		String username = (String) session.getAttribute("username");
		board.getBoardFromDB(boardId);
		if (username.equals(board.getLord())) {
			board.baseSetup();
			board.setup();
			board.updateDB("status", board.getStatus());
			board.updateDB("startPlayer", board.getStartPlayer());
			board.updateDB("base", board.genBaseDocs());
			board.updateDB("players", board.genPlayerNameDoc());
			board.updateDB("kindom", board.genKindomDocs());
			board.updateDB("trash", board.getTrash().toDocument());
			board.updateDB("logs", board.getLogger().getLoggerAsDocument());
			int i;
			for (i=0;i<board.getPlayers().size();i++) {
				board.updateDB(board.getPlayers().get(i).getName(), board.genPlayerDoc(i));
			}
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@RequestMapping(value="/dominiongame/getkindom", method = RequestMethod.POST)
	public ResponseEntity<List<Pile>> getKindom(HttpServletRequest request){
		board = new Board();
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		board.getBoardFromDB(boardId);
		List<Pile> piles = board.getKindom();
		return new ResponseEntity<>(piles, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/firstcards", method = RequestMethod.POST)
	public ResponseEntity<List<Pile>> firstCards(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board = new Board();
		board.getBoardFromDB(boardId);
		List<Pile> piles = board.getAllCards(username);
		return new ResponseEntity<>(piles, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/usememorial", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> usememorial(HttpServletRequest request){
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board = new Board();
		board.getBoardFromDB(boardId);
		board.getPlayerByName(username).useMemorial();
		board.updatePlayerDB(username);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/cleanup", method = RequestMethod.POST)
	public ResponseEntity<List<Pile>> cleanup(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board = new Board();
		board.getBoardFromDB(boardId);
		board.getPlayerByName(username).cleanUp();
		board.updateDB(username, board.genPlayerDoc(username));
		List<Pile> piles = board.getPlayerByName(username).getHandAsPiles();
		return new ResponseEntity<>(piles, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/finishfirstcards", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> finishfirstcards(HttpServletRequest request){
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board = new Board();
		board.getBoardFromDB(boardId);
		board.playerGoodToGo(username);
		board.updateDB("status", board.getStatus());
		for (int i=0;i<board.getPlayers().size();i++) {
			board.updateDB(board.getPlayers().get(i).getName(), board.genPlayerDoc(i));
		}
		board.updateSupply();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@RequestMapping(value="/dominiongame/nextphase", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> nextPhase(HttpServletRequest request){
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board = new Board();
		board.getBoardFromDB(boardId);
		board.getPlayerByName(username).nextPhase();
		board.updateDB(username, board.genPlayerDoc(username));
		board.updateLogsDB();
		String phase = board.getPlayerByName(username).getPhaseAsString();
		if (phase.equals("Offturn")) {
			board.gameEndJudge();
		}
		String status = board.getStatusAsString();
		StringEntity entity = new StringEntity();
		List<String> value = new ArrayList<String>();
		value.add(phase);
		entity.setValue(value);	
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/nextplayer", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> nextPlayer(HttpServletRequest request){
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board = new Board();
		board.getBoardFromDB(boardId);
		String p1 = board.getCurrentPlayerName();
		board.nextPlayer();
		String p2 = board.getCurrentPlayerName();
		board.updateDB("currentPlayer", board.getCurrentPlayer());
		board.updateDB(p1, board.genPlayerDoc(p1));
		board.updateDB(p2, board.genPlayerDoc(p2));
		board.updateLogsDB();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/autoplaytreasure", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> autoplayTreasure(HttpServletRequest request){
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board = new Board();
		board.getBoardFromDB(boardId);
		board.getLogger().addAutoplayTreasure(board.getPlayerByName(username));
		board.getPlayerByName(username).autoplayTreasure();
		board.updatePlayerDB(username);
		board.updateLogsDB();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/ai", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> ai(HttpServletRequest request){
		HttpSession session = request.getSession();
		String boardId = (String) session.getAttribute("boardId");
		board = new Board();
		board.getBoardFromDB(boardId);
		String p1 = board.getCurrentPlayerName();
		String p2;
		if (board.getCurrentPlayerAsPlayer().getIsBot()) {
			board.getCurrentPlayerAsPlayer().goWithAI(board);
			board.nextPlayer();
			p2 = board.getCurrentPlayerName();
			board.updateDB("currentPlayer", board.getCurrentPlayer());
			board.updateDB(p1, board.genPlayerDoc(p1));
			board.updateDB(p2, board.genPlayerDoc(p2));
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@RequestMapping(value="/dominiongame/playcard", method = RequestMethod.POST)
	public ResponseEntity<Ask> playCard(HttpServletRequest request, @RequestParam String cardName){
		HttpSession session = request.getSession();
		String boardId = (String) session.getAttribute("boardId");
		String username = (String) session.getAttribute("username");
		board = new Board();
		board.getBoardFromDB(boardId);
		Player player = board.getPlayerByName(username);
		player.setBoard(board);
		Ask ask = player.play(cardName);
		if (ask.getType() == Ask.NONE) {
			if (ask.getSubType() == Ask.ATTACK) {
				board.attackHandle(player, cardName);
			}
		}
		board.updatePlayersDB();
		board.updateLogsDB();
		board.updateSupply();
		return new ResponseEntity<>(ask, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/response", method = RequestMethod.POST)
	public ResponseEntity<Ask> response(HttpServletRequest request, @RequestParam String ans){
		HttpSession session = request.getSession();
		String boardId = (String) session.getAttribute("boardId");
		String username = (String) session.getAttribute("username");
		board = new Board();
		board.getBoardFromDB(boardId);
		Player player = board.getPlayerByName(username);
		Ask a = player.getAsk();
		a.parseAns(ans);
		String cardName = a.getCardName();
		CardFactory factory = new CardFactory();
		Card card = factory.createCard(cardName);
		card.setPlayer(player);
		card.setBoard(board);
		Ask ask = card.response(a);
		player.setAsk(ask);
		board.updatePlayersDB();
		board.updateLogsDB();
		board.updateSupply();
		return new ResponseEntity<>(ask, HttpStatus.OK);
	}

	@RequestMapping(value="/dominiongame/getboard", method = RequestMethod.POST)
	public ResponseEntity<BoardEntity> getboard(HttpServletRequest request){
		HttpSession session = request.getSession();
		String boardId = (String) session.getAttribute("boardId");
		board.getBoardFromDB(boardId);
		BoardEntity entity = new BoardEntity(board);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/getplayer", method = RequestMethod.POST)
	public ResponseEntity<PlayerEntity> getplayer(HttpServletRequest request){
		HttpSession session = request.getSession();
		String boardId = (String) session.getAttribute("boardId");
		String username = (String) session.getAttribute("username");
		board.getBoardFromDB(boardId);
		Player player = board.getPlayerByName(username);
		player.autoSetPhase();
		board.updateDB(username, board.genPlayerDoc(username));
		board.updateLogsDB();
		if (player.getPhaseAsString().equals("Offturn")) {
			board.gameEndJudge();
		}
		PlayerEntity entity = new PlayerEntity(player);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/buycard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> buyCard(HttpServletRequest request, @RequestParam String cardName){
		HttpSession session = request.getSession();
		String boardId = (String) session.getAttribute("boardId");
		String username = (String) session.getAttribute("username");
		board = new Board();
		board.getBoardFromDB(boardId);
		board.getLogger().addBuyCard(username, cardName);
		board.playerBuy(board.getPlayerByName(username), board.getPileByTop(cardName));
		board.updateSupply();
		board.updatePlayerDB(username);
		board.updateLogsDB();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/gaincard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> gainCard(HttpServletRequest request, @RequestParam String cardName){
		HttpSession session = request.getSession();
		String boardId = (String) session.getAttribute("boardId");
		String username = (String) session.getAttribute("username");
		board = new Board();
		board.getBoardFromDB(boardId);
		board.getLogger().addGainCard(username, cardName);
		board.gainToPlayerFromPile(board.getPlayerByName(username), board.getPileByTop(cardName));
		board.updateSupply();
		board.updatePlayerDB(username);
		board.updateLogsDB();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
}
