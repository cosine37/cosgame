package com.cosine.cosgame.citadels;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Ask {
	int askId;
	int askType;
	List<String> ls;
	List<List<String>> builtInfo;
	String msg;
	
	public Ask() {
		askId = 0;
		askType = 0;
		ls = new ArrayList<>();
		builtInfo = new ArrayList<>();
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
	public List<List<String>> getBuiltInfo() {
		return builtInfo;
	}

	public void setBuiltInfo(List<List<String>> builtInfo) {
		this.builtInfo = builtInfo;
	}
	public Document toDocument() {
		Document doa = new Document();
		doa.append("askId", askId);
		doa.append("askType", askType);
		doa.append("ls", ls);
		doa.append("msg", msg);
		doa.append("builtInfo", builtInfo);
		return doa;
	}
	
	public void setFromDoc(Document doa) {
		askId = doa.getInteger("askId", 0);
		askType = doa.getInteger("askType", 0);
		ls = (List<String>) doa.get("ls");
		builtInfo = (List<List<String>>) doa.get("builtInfo");
		msg = doa.getString("msg");
	}
}
