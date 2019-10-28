package com.cosine.cosgame.controller.minigames;

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

import com.cosine.cosgame.minigame.xutangbo.Game;
import com.cosine.cosgame.minigame.xutangbo.GameEntity;
import com.cosine.cosgame.minigame.xutangbo.Mega;
import com.cosine.cosgame.minigame.xutangbo.Player;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class XuTangBoController {
	@RequestMapping(value="/minigame/xutangbo", method = RequestMethod.GET)
	public String xutangbo() {
		return "minigames/xutangbo";
	}
	
	@RequestMapping(value="/minigame/xutangbo/new", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> xutangboNew(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Game game = new Game();
		game.newGame(username);
		game.storeGameToDB();
		session.setAttribute("xtbgameid", game.getId());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/minigame/xutangbo/games", method = RequestMethod.GET)
	public ResponseEntity<StringEntity> xutangboGames() {
		Mega mega = new Mega();
		StringEntity entity = mega.getGamesAsStringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/minigame/xutangbo/players", method = RequestMethod.GET)
	public ResponseEntity<StringEntity> xutangboPlayers(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String id = (String) session.getAttribute("xtbgameid");
		Game game = new Game();
		game.getGameFromDB(id);
		List<Player> players = game.getPlayers();
		List<String> value = new ArrayList<>();
		for (int i=0;i<players.size();i++) {
			value.add(players.get(i).getName());
		}
		StringEntity entity = new StringEntity();
		entity.setValue(value);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/minigame/xutangbo/getgame", method = RequestMethod.GET)
	public ResponseEntity<GameEntity> xutangboGetGame(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String id = (String) session.getAttribute("xtbgameid");
		String username = (String) session.getAttribute("username");
		Game game = new Game();
		game.getGameFromDB(id);
		game.autoNextStep();
		game.updateDB("status", game.getStatus());
		game.updateAllPlayersDB();
		game.updateCounter();
		game.updateLogs();
		GameEntity entity = new GameEntity(game);
		entity.shouldDisableMove(username);
		entity.setIsDead(username);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/minigame/xutangbo/addbot", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> xutangboAddBot(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String id = (String) session.getAttribute("xtbgameid");
		Game game = new Game();
		game.getGameFromDB(id);
		game.addBotToDB();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/minigame/xutangbo/kick", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> xutangboKick(HttpServletRequest request, @RequestParam String kickedName) {
		HttpSession session = request.getSession(true);
		String id = (String) session.getAttribute("xtbgameid");
		Game game = new Game();
		game.getGameFromDB(id);
		game.kickFromDB(kickedName);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/minigame/xutangbo/start", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> xutangboStart(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String id = (String) session.getAttribute("xtbgameid");
		Game game = new Game();
		game.getGameFromDB(id);
		game.start();
		game.updateDB("status", game.getStatus());
		game.updateAllPlayersDB();
		game.updateCounter();
		game.updateLogs();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/minigame/xutangbo/usemove", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> xutangboUseMove(HttpServletRequest request, @RequestParam int moveId) {
		HttpSession session = request.getSession(true);
		String id = (String) session.getAttribute("xtbgameid");
		String username = (String) session.getAttribute("username");
		Game game = new Game();
		game.getGameFromDB(id);
		game.getPlayerByName(username).setCurMove(moveId);
		game.judge();
		game.updateDB("status", game.getStatus());
		game.updateAllPlayersDB();
		game.updateCounter();
		game.updateLogs();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
}
