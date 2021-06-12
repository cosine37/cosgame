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

import com.cosine.cosgame.marshbros.Board;
import com.cosine.cosgame.marshbros.Meta;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class MarshbrosController {
	@RequestMapping(value="/marshbros", method = RequestMethod.GET)
	public String marshbros() {
		return "marshbrosMain";
	}
	@RequestMapping(value="/marshbroscreategame", method = RequestMethod.GET)
	public String marshbrosCreateGame() {
		return "marshbrosCreateGame";
	}
	@RequestMapping(value="/marshbrosgame", method = RequestMethod.GET)
	public String marshbrosGame() {
		return "marshbrosGame";
	}
	@RequestMapping(value="/marshbros/newboard", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> newBoard(HttpServletRequest request){
		Board board = new Board();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		board.addPlayer(username);
		board.setLord(username);
		board.genBoardId();
		board.storeToDB();
		session.setAttribute("boardId", board.getId());
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	@RequestMapping(value="/marshbros/allboards", method = RequestMethod.GET)
	public ResponseEntity<StringEntity> allBoards(HttpServletRequest request){
		Meta meta = new Meta();
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		StringEntity entity = meta.getBoardIdsAsStringEntity(username);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
}
