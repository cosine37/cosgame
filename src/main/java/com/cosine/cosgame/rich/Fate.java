package com.cosine.cosgame.rich;

public class Fate {
	int id;
	int type;
	int value;
	String content;
	String conversation;
	
	public Fate(int id, int type, int value, String content, String conversation) {
		this.id = id;
		this.type = type;
		this.value = value;
		this.content = content;
		this.conversation = conversation;
	}
	
	public void apply(Player p) {
		if (type == Consts.FATE_ADD) {
			p.addMoney(value);
		} else if (type == Consts.FATE_LOSE) {
			p.loseMoney(value);
		} else if (type == Consts.FATE_GOTOJAIL) {
			p.goToJail();
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getConversation() {
		return conversation;
	}
	public void setConversation(String conversation) {
		this.conversation = conversation;
	}
	
	
}
