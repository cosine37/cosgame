package com.cosine.cosgame.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cosine.cosgame.mafia.Mafia;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class MafiaGameController {
	
	@RequestMapping(value="/mafiagame/newgame", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> newgame() {
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/mafiagame", method = RequestMethod.GET)
	public String mafiaGame() {
		return "mafiaGame";
	}
	
	@RequestMapping(value="/mafiagame/players", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> players(@RequestParam String roomId) {
		Mafia mafia = new Mafia();
		System.out.println(roomId);
		StringEntity entity = mafia.getPlayersAsStringEntity(roomId);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/mafiagame/createroom", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> createRoom(@RequestParam String lord, @RequestParam int numPlayers) {
		Mafia mafia = new Mafia();
		StringEntity entity = mafia.createRoom(lord, numPlayers);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
}
