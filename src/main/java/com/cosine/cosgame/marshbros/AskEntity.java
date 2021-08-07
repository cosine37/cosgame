package com.cosine.cosgame.marshbros;

public class AskEntity {
	String type;
	String subType;
	String msg;
	
	public AskEntity() {
		type = "0";
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
