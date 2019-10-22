package com.cosine.cosgame.minigame.xutangbo;

import org.bson.Document;

public class Log {
	// Types
	public static final int COUNTER = 0;
	public static final int USEMOVE = 1;
	public static final int AFTEREFFECT = 2;
	public static final int PUREMSG = 3;
	
	// SubTypes
	public static final int GAINENERGY = 10;
	public static final int FAINTED = 11;
	public static final int BAOSI = 12;
	public static final int USEENERGY = 13;
	public static final int USEBI = 14;
	
	int type;
	int subType;
	String name;
	int moveId;
	int round;
	int step;
	int num;
	String msg;
	
	public Log() {
		
	}
	
	public String toString() {
		if (type == COUNTER) {
			return "Round " + round + ", Step " + step;
		} else if (type == USEMOVE) {
			Move move = new Move(moveId);
			return name + " uses " + move.getMoveName();
		} else if (type == AFTEREFFECT) {
			if (subType == GAINENERGY) {
				return name + " gains an energy";
			} else if (subType == FAINTED) {
				return name + " fainted";
			} else if (subType == BAOSI) {
				return name + " baosi";
			} else if (subType == USEENERGY) {
				return name + " consumes " + num + " energy";
			} else if (subType == USEBI) {
				return name + " consumes a bi";
			}
		} else if (type == PUREMSG) {
			return msg;
		}
		return "";
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoveId() {
		return moveId;
	}

	public void setMoveId(int moveId) {
		this.moveId = moveId;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("type", type);
		if (type == COUNTER) {
			doc.append("subType", subType);
			doc.append("round", round);
			doc.append("step", step);
		} else if (type == USEMOVE) {
			doc.append("name", name);
			doc.append("moveId", moveId);
		} else if (type == AFTEREFFECT) {
			doc.append("subType", subType);
			doc.append("name", name);
			if (subType == USEENERGY) {
				doc.append("num", num);
			}
		} else if (type == PUREMSG) {
			doc.append("msg", msg);
		}
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		type = doc.getInteger("type");
		if (type == COUNTER) {
			subType = doc.getInteger("subType");
			round = doc.getInteger("round");
			step = doc.getInteger("step");
		} else if (type == USEMOVE) {
			name = doc.getString("name");
			moveId = doc.getInteger("moveId");
		} else if (type == AFTEREFFECT) {
			subType = doc.getInteger("subType");
			name = doc.getString("name");
			if (subType == USEENERGY) {
				num = doc.getInteger("num");
			}
		} else if (type == PUREMSG) {
			msg = doc.getString("msg");
		}
	}
	
}
