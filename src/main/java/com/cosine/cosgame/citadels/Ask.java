package com.cosine.cosgame.citadels;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Ask {
	int askId;
	int askType;
	List<String> ls;
	String msg;
	
	public Ask() {
		askId = 0;
		askType = 0;
		ls = new ArrayList<>();
		msg = "";
	}
	
	public int getAskId() {
		return askId;
	}
	public void setAskId(int askId) {
		this.askId = askId;
	}
	public int getAskType() {
		return askType;
	}
	public void setAskType(int askType) {
		this.askType = askType;
	}
	public List<String> getLs() {
		return ls;
	}
	public void setLs(List<String> ls) {
		this.ls = ls;
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Document toDocument() {
		Document doa = new Document();
		doa.append("askId", askId);
		doa.append("askType", askType);
		doa.append("ls", ls);
		doa.append("msg", msg);
		return doa;
	}
	
	public void setFromDoc(Document doa) {
		askId = doa.getInteger("askId", 0);
		askType = doa.getInteger("askType", 0);
		ls = (List<String>) doa.get("ls");
		msg = doa.getString("msg");
	}
}
