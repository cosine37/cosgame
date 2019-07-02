package com.cosine.cosgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String empty() {
		return "redirect:index";
	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

}
