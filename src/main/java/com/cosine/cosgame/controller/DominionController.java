package com.cosine.cosgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DominionController {
	@RequestMapping(value="/dominion", method = RequestMethod.GET)
	public String dominion() {
		return "dominionMain";
	}
	
	@RequestMapping(value="/dominiongame", method = RequestMethod.GET)
	public String dominionGame() {
		return "dominionGame";
	}
}
