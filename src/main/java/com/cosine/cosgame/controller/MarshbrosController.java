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
	
}
