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

import com.cosine.cosgame.zodiac.Board;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class ZodiacController {
	@RequestMapping(value="/zodiac", method = RequestMethod.GET)
	public String zodiac() {
		return "zodiacMain";
	}
	
	@RequestMapping(value="/zodiaccreategame", method = RequestMethod.GET)
	public String zodiacCreateGame() {
		return "zodiacCreateGame";
	}
	
	@RequestMapping(value="/zodiacgame", method = RequestMethod.GET)
	public String zodiacGame() {
		return "zodiacGame";
	}
	
	@RequestMapping(value="/zodiac/newboard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> newBoard(HttpServletRequest request){
		/*
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		board.addPlayer(username);
		board.setLord(username);
		board.genBoardId();
		board.storeToDB();
		session.setAttribute("boardId", board.getId());
		*/
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/zodiac/startgame", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> startGame(HttpServletRequest request){
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
}
