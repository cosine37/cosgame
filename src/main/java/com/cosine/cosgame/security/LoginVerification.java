package com.cosine.cosgame.security;

import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.util.MongoDBUtil;

public class LoginVerification {
	MongoDBUtil dbUtil;
	public LoginVerification() {
		String dbname = "admin";
		String col = "users";
		dbUtil = new MongoDBUtil(dbname);
		dbUtil.setCol(col);
	}
	
	public boolean verify(String username, String password) {
		Document doc = dbUtil.read("username", username);
		if (doc != null) {
			String p = (String) doc.get("password");
			return p.equals(password);
		} else {
			return false;
		}
	}
	
	public boolean exists(String username) {
		Document doc = dbUtil.read("username", username);
		return (doc!=null);
	}
	
	public String getPassword(String username) {
		Document doc = dbUtil.read("username", username);
		if (doc != null) {
			return "";
		} else {
			return (String) doc.get("password");
		}
	}
}
