package com.cosine.cosgame.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cosine.cosgame.dominion.list.CardIntro;
import com.cosine.cosgame.dominion.list.ExpansionIntro;

@Controller
public class DominionCardInfoController {
	@RequestMapping(value="/dominioninfo/allcardintro", method = RequestMethod.GET)
	public ResponseEntity<List<ExpansionIntro>> allCardInfo(){
		CardIntro intro = new CardIntro();
		intro.readCardIntro();
		intro.setExpansionIntros();
		return new ResponseEntity<>(intro.getExpansionIntros(), HttpStatus.OK);
	}
	
	
}
