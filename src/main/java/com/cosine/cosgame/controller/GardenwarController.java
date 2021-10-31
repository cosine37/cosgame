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

import com.cosine.cosgame.util.StringEntity;

@Controller
public class GardenwarController {
	@RequestMapping(value="/gardenwar", method = RequestMethod.GET)
	public String gardenwar() {
		return "gardenwarMain";
	}
	
	@RequestMapping(value="/gardenwarcreategame", method = RequestMethod.GET)
	public String gardenwarCreateGame() {
		return "gardenwarCreateGame";
	}
	
	@RequestMapping(value="/gardenwargame", method = RequestMethod.GET)
	public String gardenwarGame() {
		return "gardenwarGame";
	}
	
}
