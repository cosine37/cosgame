package com.cosine.cosgame.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cosine.cosgame.tabs.AllTabs;
import com.cosine.cosgame.tabs.Tab;

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
	
	@RequestMapping(value="/alltabs", method = RequestMethod.GET)
	public ResponseEntity<List<Tab>> allTabs() {
		return new ResponseEntity<>(AllTabs.getAllTabs(), HttpStatus.OK);
	}

}
