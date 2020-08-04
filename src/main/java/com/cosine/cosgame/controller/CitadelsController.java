package com.cosine.cosgame.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cosine.cosgame.citadels.Board;
import com.cosine.cosgame.citadels.BoardEntity;

@Controller
public class CitadelsController {
	@RequestMapping(value="/citadels", method = RequestMethod.GET)
	public String citadels() {
		return "citadelsMain";
	}
	
	@RequestMapping(value="/citadelsgame", method = RequestMethod.GET)
	public String citadelsGame() {
		return "citadelsGame";
	}
	
	@RequestMapping(value="/citadelsgame/start", method = RequestMethod.POST)
	public ResponseEntity<BoardEntity> citadelsGameStart() {
		Board board = new Board();
		board.addPlayer("p1");
		board.addPlayer("p2");
		board.addPlayer("p3");
		board.addPlayer("p4");
		board.gameSetup();
		BoardEntity entity = board.toBoardEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
}
