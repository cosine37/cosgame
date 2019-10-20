package com.cosine.cosgame.controller.minigames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cosine.cosgame.minigame.xutangbo.Game;
import com.cosine.cosgame.minigame.xutangbo.Mega;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class XuTangBoController {
	@RequestMapping(value="/minigame/xutangbo", method = RequestMethod.GET)
	public String xutangbo() {
		return "minigames/xutangbo";
	}
	
	@RequestMapping(value="/minigame/xutangbonew", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> xutangboNew(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		Game game = new Game();
		game.newGame(username);
		game.storeGameToDB();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/minigame/xutangbogames", method = RequestMethod.GET)
	public ResponseEntity<StringEntity> xutangboGames() {
		Mega mega = new Mega();
		StringEntity entity = mega.getGamesAsStringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
}
