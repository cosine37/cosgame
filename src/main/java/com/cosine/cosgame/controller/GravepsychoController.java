package com.cosine.cosgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GravepsychoController {
	@RequestMapping(value="/gravepsycho", method = RequestMethod.GET)
	public String gravepsycho() {
		return "gravepsychoMain";
	}
	
	@RequestMapping(value="/gravepsychocreategame", method = RequestMethod.GET)
	public String gravepsychoCreateGame() {
		return "gravepsychoCreateGame";
	}
	
	@RequestMapping(value="/gravepsychogame", method = RequestMethod.GET)
	public String gravepsychoGame() {
		return "gravepsychoGame";
	}
}
