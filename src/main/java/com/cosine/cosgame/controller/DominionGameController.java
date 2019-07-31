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

import com.cosine.cosgame.dominion.Board;
import com.cosine.cosgame.dominion.Pile;
import com.cosine.cosgame.dominion.Player;
import com.cosine.cosgame.security.LoginInterceptor;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class DominionGameController {
	
	protected static final Log logger = LogFactory.getLog(LoginInterceptor.class);
	
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
		board.storeBoardToDB();
		List<String> value = new ArrayList<String>();
		value.add(board.getBoardId());
		session.setAttribute("boardId", board.getBoardId());
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
		String boardId = (String) session.getAttribute("boardId");
		Board board = new Board();
		board.getBoardFromDB(boardId);
		board.resign();
		
		// TODO: maybe get the final cards before cleaning db
		//board.cleanPlayerDBs();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/randomize", method = RequestMethod.POST)
	public void randomize() {
		board.randomize();
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
			board.randomize();
			board.setup();
			board.updateDB("status", board.getStatus());
			board.updateDB("startPlayer", board.getStartPlayer());
			board.updateDB("base", board.genBaseDocs());
			board.updateDB("players", board.genPlayerNameDoc());
			board.updateDB("kindom", board.genKindomDocs());
			int i;
			for (i=0;i<board.getPlayers().size();i++) {
				board.updateDB(board.getPlayers().get(i).getName(), board.genPlayerDoc(i));
			}
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/getbase", method = RequestMethod.POST)
	public ResponseEntity<List<Pile>> getBase(HttpServletRequest request){
		board = new Board();
		HttpSession session = request.getSession(true);
		String boardId = (String) session.getAttribute("boardId");
		board.getBoardFromDB(boardId);
		List<Pile> piles = board.getBase();
		return new ResponseEntity<>(piles, HttpStatus.OK);
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
	
	@RequestMapping(value="/dominiongame/gethand", method = RequestMethod.POST)
	public ResponseEntity<List<Pile>> gethand(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board = new Board();
		board.getBoardFromDB(boardId);
		List<Pile> piles = board.getPlayerByName(username).getHandAsPiles();
		return new ResponseEntity<>(piles, HttpStatus.OK);
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
		board.updateDB(username, board.genPlayerDoc(username));
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/getstatus", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> getstatus(HttpServletRequest request){
		HttpSession session = request.getSession();
		String boardId = (String) session.getAttribute("boardId");
		board = new Board();
		board.getBoardFromDB(boardId);
		int status = board.getStatus();
		StringEntity entity = new StringEntity();
		List<String> value = new ArrayList<String>();
		String s;
		switch (status) {
			case 0:
				s = "before";
				break;
			case 1:
				s = "first cards";
				break;
			case 2:
				s = "in game";
				break;
			case 3:
				s = "end game";
				break;
			default:
				s = "unknown";
				break;
		}
		value.add(s);
		entity.setValue(value);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/getphase", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> getPhase(HttpServletRequest request){
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String boardId = (String) session.getAttribute("boardId");
		board = new Board();
		board.getBoardFromDB(boardId);
		String phase = board.getPlayerByName(username).getPhaseAsString();
		StringEntity entity = new StringEntity();
		List<String> value = new ArrayList<String>();
		value.add(phase);
		entity.setValue(value);
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
		String phase = board.getPlayerByName(username).getPhaseAsString();
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
			board.getCurrentPlayerAsPlayer().goWithAI();
			board.nextPlayer();
			p2 = board.getCurrentPlayerName();
			board.updateDB("currentPlayer", board.getCurrentPlayer());
			board.updateDB(p1, board.genPlayerDoc(p1));
			board.updateDB(p2, board.genPlayerDoc(p2));
		}
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/addons", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> addons(HttpServletRequest request){
		HttpSession session = request.getSession();
		String boardId = (String) session.getAttribute("boardId");
		String username = (String) session.getAttribute("username");
		board = new Board();
		board.getBoardFromDB(boardId);
		String action = Integer.toString(board.getPlayerByName(username).getAction());
		String buy = Integer.toString(board.getPlayerByName(username).getBuy());
		String coin = Integer.toString(board.getPlayerByName(username).getCoin());
		List<String> value = new ArrayList<String>();
		value.add(action);
		value.add(buy);
		value.add(coin);
		StringEntity entity = new StringEntity();
		entity.setValue(value);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/playcard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> playCard(HttpServletRequest request, @RequestParam String cardName){
		HttpSession session = request.getSession();
		String boardId = (String) session.getAttribute("boardId");
		String username = (String) session.getAttribute("username");
		board = new Board();
		board.getBoardFromDB(boardId);
		board.getPlayerByName(username).play(cardName);
		board.updatePlayerDB(username);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/buycard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> buyCard(HttpServletRequest request, @RequestParam String cardName){
		HttpSession session = request.getSession();
		String boardId = (String) session.getAttribute("boardId");
		String username = (String) session.getAttribute("username");
		board = new Board();
		board.getBoardFromDB(boardId);
		board.playerBuy(board.getPlayerByName(username), board.getPileByTop(cardName));
		board.updateSupply();
		board.updatePlayerDB(username);
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
		board.gainToPlayerFromPile(board.getPlayerByName(username), board.getPileByTop(cardName));
		board.updateSupply();
		board.updatePlayerDB(username);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
}
