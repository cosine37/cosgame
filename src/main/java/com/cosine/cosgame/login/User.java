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
	String role;
	
	// user role
	public static String PLAYER = "PLAYER";
	public static String ADMIN = "ADMIN";
	
	// user status
	public static int LOGOUT = 0;
	public static int LOGIN = 1;
	public static int DOMINIONROOM = 11;
	public static int DOMINIONGAME = 12;
	public static int MAFIAROOM = 21;
	public static int MAFIAGAME = 22;
	
	// redirect to the room
	String roomId;
	int status;
	
	MongoDBUtil dbutil;
	
	public User() {
		String dbname = "admin";
		String col = "user";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
		role = "player";
		status = LOGOUT;
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
		doc.append("role", role);
		doc.append("status",status);
		doc.append("roomId", roomId);
		dbutil.insert(doc);
	}
	
	
}
