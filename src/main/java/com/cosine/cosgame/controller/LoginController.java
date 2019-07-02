package com.cosine.cosgame.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cosine.cosgame.login.User;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class LoginController {
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String index() {
		return "login";
	}
	
	@RequestMapping(value="/login/verify", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> verify(@RequestParam String username, @RequestParam String encrypted) {
		User user = new User(username);
		user.getEncrypted();
		StringEntity entity = user.verifyEncryptedAsStringEntity(encrypted);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
}
