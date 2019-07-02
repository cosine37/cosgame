package com.cosine.cosgame.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cosine.cosgame.dominion.Board;
import com.cosine.cosgame.dominion.Pile;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class DominionGameController {
	
	Board board;
	
	@RequestMapping(value="/dominiongame/newgame", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> newgame() {
		StringEntity entity = new StringEntity();
		//entity.setValue("nothing");
		board = new Board();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/randomize", method = RequestMethod.POST)
	public void randomize() {
		board.randomize();
	}
	
	@RequestMapping(value="/dominiongame/getbase", method = RequestMethod.POST)
	public ResponseEntity<List<Pile>> getBase(){
		board = new Board();
		List<Pile> piles = board.getBase();
		return new ResponseEntity<>(piles, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame/getkindom", method = RequestMethod.POST)
	public ResponseEntity<List<Pile>> getKindom(){
		board = new Board();
		List<Pile> piles = board.getKindom();
		return new ResponseEntity<>(piles, HttpStatus.OK);
	}
}
