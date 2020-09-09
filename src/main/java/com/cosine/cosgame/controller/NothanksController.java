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

import com.cosine.cosgame.util.StringEntity;

@Controller
public class NothanksController {
	@RequestMapping(value="/nothanks", method = RequestMethod.GET)
	public String nothanks() {
		return "nothanksMain";
	}
	
	@RequestMapping(value="/nothankscreategame", method = RequestMethod.GET)
	public String nothanksCreateGame() {
		return "nothanksCreateGame";
	}
	
	@RequestMapping(value="/nothanksgame", method = RequestMethod.GET)
	public String nothanksGame() {
		return "nothanksGame";
	}
	
}
