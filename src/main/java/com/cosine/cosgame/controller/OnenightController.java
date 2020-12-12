package com.cosine.cosgame.controller;

import java.util.List;

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
public class OnenightController {
	@RequestMapping(value="/onenight", method = RequestMethod.GET)
	public String onenight() {
		return "onenightMain";
	}
	
	@RequestMapping(value="/onenightcreategame", method = RequestMethod.GET)
	public String onenightCreateGame() {
		return "onenightCreateGame";
	}
	
	@RequestMapping(value="/onenightgame", method = RequestMethod.GET)
	public String onenightGame() {
		return "onenightGame";
	}
	
}