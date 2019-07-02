package com.cosine.cosgame.login;

import com.cosine.cosgame.util.MongoDBUtil;
import com.cosine.cosgame.util.StringEntity;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class User {
	String userid;
	String username;
	String password;
	String encrypted;
	String state;
	
	public static String PLAYER = "PLAYER";
	public static String ADMIN = "ADMIN";
	
	MongoDBUtil dbutil;
	
	public User() {
		String dbname = "admin";
		String col = "user";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
		state = "player";
	}
	
	public User(String username) {
		this();
		this.username = username;
		
	}
	
	public User(String username, String encrypted) {
		this();
		this.username = username;
		this.encrypted = encrypted;
	}
	
	public boolean verify(String password) {
		return false;
	}
	
	public boolean verifyEncrypted(String encrypted) {
		return this.encrypted == encrypted;
	}
	
	public StringEntity verifyEncryptedAsStringEntity(String encrypted) {
		boolean f = verifyEncrypted(encrypted);
		StringEntity entity = new StringEntity();
		List<String> ans = new ArrayList<String>();
		if (this.encrypted == "") {
			ans.add("user not exist");
		} else {
			if (f) {
				ans.add("verified");
			} else {
				ans.add("wrong password");
			}
		}
		
		entity.setValue(ans);
		return entity;
	}
	
	public void getEncrypted() {
		Document doc = dbutil.read("username", username);
		if (doc == null) {
			encrypted = "";
		} else {
			encrypted = (String)doc.get("encrypted");
		}
	}
	
	public void storeUserToDB() {
		Document doc = new Document();
		doc.append("username", username);
		doc.append("encrypted", encrypted);
		doc.append("state", state);
		dbutil.insert(doc);
	}
	
	
}
