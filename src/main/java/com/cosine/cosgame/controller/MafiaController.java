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
public class MafiaController {
	@RequestMapping(value="/mafia", method = RequestMethod.GET)
	public String mafia() {
		return "mafiaMain";
	}
	
	@RequestMapping(value="/mafiarooms", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> mafiaRooms(){
		Mafia mafia = new Mafia();
		StringEntity entity = mafia.getRoomIdsAsStringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/mafiarules", method = RequestMethod.GET)
	public String mafiaRules() {
		return "mafiaRules";
	}
	
	
	
}