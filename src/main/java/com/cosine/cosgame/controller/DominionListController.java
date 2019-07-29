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
import com.cosine.cosgame.prehandle.dominion.DominionCards;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class DominionListController {
	
	@RequestMapping(value="/ajaxdemo", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> ajaxDemo(){
		StringEntity entity = new StringEntity();
		List<String> value = new ArrayList<String>();
		DominionCards dc = new DominionCards();
		String s = dc.get();
		value.add(s);
		entity.setValue(value);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
}
