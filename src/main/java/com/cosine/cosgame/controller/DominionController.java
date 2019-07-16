package com.cosine.cosgame.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cosine.cosgame.dominion.Board;
import com.cosine.cosgame.dominion.DominionMega;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class DominionController {
	@RequestMapping(value="/dominion", method = RequestMethod.GET)
	public String dominion() {
		return "dominionMain";
	}
	
	@RequestMapping(value="/dominionboards", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> dominionBoards(){
		DominionMega mega = new DominionMega();
		StringEntity entity = mega.getBoardIdsAsStringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/dominiongame", method = RequestMethod.GET)
	public String dominionGame() {
		return "dominionGame";
	}
	
	@RequestMapping(value="/dominionboard", method = RequestMethod.GET)
	public String dominionBoard() {
		return "dominionBoard";
	}
}
