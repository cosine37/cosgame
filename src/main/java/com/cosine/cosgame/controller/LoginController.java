package com.cosine.cosgame.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cosine.cosgame.login.User;
import com.cosine.cosgame.security.LoginInterceptor;
import com.cosine.cosgame.util.StringEntity;

@Controller
public class LoginController {
	protected static final Log logger = LogFactory.getLog(LoginInterceptor.class);
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login_html() {
		return "login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> logout(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.invalidate();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/login/username", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> setUsername(HttpServletRequest request, @RequestParam String username) {
		HttpSession session = request.getSession(true);
		session.setAttribute("username", username);
		session.setMaxInactiveInterval(40 * 60);
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/username", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> username(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute("username");
		List<String> value = new ArrayList<String>();
		value.add(username);
		StringEntity entity = new StringEntity();
		entity.setValue(value);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	@RequestMapping(value="/register/user", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> registerUser(@RequestParam String username, @RequestParam String encrypted){
		User user = new User(username, encrypted);
		user.storeUserToDB();
		StringEntity entity = new StringEntity();
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/register/user/exists", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> exists(@RequestParam String username){
		User user = new User(username);
		StringEntity entity = new StringEntity();
		List<String> value = new ArrayList<String>();
		if (user.exists()) {
			value.add("exists");
		} else {
			value.add("ok");
		}
		entity.setValue(value);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@RequestMapping(value="/login/verify", method = RequestMethod.POST)
	public ResponseEntity<StringEntity> verify(@RequestParam String username, @RequestParam String encrypted) {
		User user = new User(username);
		user.getEncrypted();
		StringEntity entity = user.verifyEncryptedAsStringEntity(encrypted);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
}
