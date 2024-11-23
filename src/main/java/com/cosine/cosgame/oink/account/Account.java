package com.cosine.cosgame.oink.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.cosine.cosgame.oink.account.entity.AccountEntity;
import com.cosine.cosgame.util.MongoDBUtil;

public class Account {
	String name;
	String signature;
	int chosenAvatar;
	List<Integer> avatars;
	boolean visitedOink;
	
	MongoDBUtil dbutil;
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("chosenAvatar", chosenAvatar);
		doc.append("avatars", avatars);
		doc.append("visitedOink", visitedOink);
		doc.append("signature", signature);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		chosenAvatar = doc.getInteger("chosenAvatar", 1000);
		avatars = (List<Integer>) doc.get("avatars");
		visitedOink = doc.getBoolean("visitedOink", false);
		signature = doc.getString("signature");
		if (signature == null) signature = "";
	}
	
	public AccountEntity toAccountEntity() {
		AccountEntity entity = new AccountEntity();
		
		entity.setName(name);
		entity.setChosenAvatar(chosenAvatar);
		entity.setAvatars(avatars);
		entity.setVisitedOink(visitedOink);
		
		Map<String, String> avatarStyle = new HashMap<>();
		String backgroundImage = "url(/image/Oink/Avatar/" + Integer.toString(chosenAvatar) + ".png)";
		avatarStyle.put("background-image", backgroundImage);
		avatarStyle.put("background-size", "cover");
		entity.setChosenAvatarStyle(avatarStyle);
		
		int i;
		List<Map<String, String>> avatarStyles = new ArrayList<>();
		for (i=0;i<avatars.size();i++) {
			avatarStyle = new HashMap<>();
			backgroundImage = "url(/image/Oink/Avatar/" + Integer.toString(avatars.get(i)) + ".png)";
			avatarStyle.put("background-image", backgroundImage);
			avatarStyle.put("background-size", "cover");
			avatarStyles.add(avatarStyle);
		}
		entity.setAvatarStyles(avatarStyles);
		
		
		entity.setSignature(signature);
		String signatureDisplay = "";
		if (signature.length() < 16) {
			signatureDisplay = signature;
		} else {
			for (i=0;i<14;i++) {
				signatureDisplay = signatureDisplay + signature.charAt(i);
			}
			signatureDisplay = signatureDisplay + "...";
		}
		entity.setSignatureDisplay(signatureDisplay);
		String nameDisplay = "";
		if (name.length() < 14) {
			nameDisplay = name;
		} else {
			for (i=0;i<12;i++) {
				nameDisplay = nameDisplay + name.charAt(i);
			}
			nameDisplay = nameDisplay + "...";
		}
		entity.setNameDisplay(nameDisplay);
		
		return entity;
	}
	
	public Account() {
		cleanAccount();
		
		String dbname = "admin";
		String col = "users";
		
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void cleanAccount() {
		avatars = new ArrayList<>();
		avatars.add(1000);
		avatars.add(1001);
		avatars.add(1002);
		avatars.add(1003);
		chosenAvatar = 1000;
		visitedOink = false;
	}
	
	public boolean hasAvatar(int x) {
		for (int i=0;i<avatars.size();i++) {
			if (avatars.get(i) == x) return true;
		}
		return false;
	}
	
	public void addAvatar(int x) {
		avatars.add(x);
	}
	
	public void chooseAvatar(int x) {
		if (hasAvatar(x)) {
			chosenAvatar = x;
		}
	}
	
	public void updateSignature(String s) {
		this.signature = s;
	}
	
	public void getFromDB(String username) {
		Document userDoc = dbutil.read("username", username);
		if (userDoc.get("oink") == null) {
			name = username;
			Document doc = toDocument();
			dbutil.update("username", username, "oink", doc);
		} else {
			Document doc = (Document) userDoc.get("oink");
			setFromDoc(doc);
		}
	}
	public void updateAccountDB(String username) {
		Document doc = toDocument();
		dbutil.update("username", username, "oink", doc);
	}
	public void updateAccountDB() {
		updateAccountDB(name);
	}
}
