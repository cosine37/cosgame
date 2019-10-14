package com.cosine.cosgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MinigameController {
	@RequestMapping(value="/minigame", method = RequestMethod.GET)
	public String mafiaGame() {
		return "minigame";
	}

}
