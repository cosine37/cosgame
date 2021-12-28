package com.cosine.cosgame.threechaodoms;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class ID {
	List<Integer> factions;
	
	public ID() {
		factions = new ArrayList<>();
		for (int i=0;i<4;i++) {
			factions.add(0);
		}
	}
	
	public void setFactions(int f1, int f2) {
		factions = new ArrayList<>();
		for (int i=0;i<4;i++) {
			factions.add(0);
		}
		if (f1>=0 && f1<4) {
			factions.set(f1, 1);
		}
		if (f2>=0 && f2<4) {
			factions.set(f2, 1);
		}
	}
	
	public boolean hasFaction(int x) {
		if (x>=0 && x<4) {
			if (factions.get(x) == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public int getDifficulty() {
		if (hasFaction(Consts.WEI) && hasFaction(Consts.WU)) {
			return 1;
		} else if (hasFaction(Consts.HAN) && hasFaction(Consts.WU)) {
			return 2;
		} else if (hasFaction(Consts.WEI) && hasFaction(Consts.QUN)) {
			return 3;
		} else if (hasFaction(Consts.HAN) && hasFaction(Consts.QUN)){
			return 4;
		} else if (hasFaction(Consts.HAN) && hasFaction(Consts.WEI)) {
			return 5;
		} else if (hasFaction(Consts.WU) && hasFaction(Consts.QUN)) {
			return 6;
		} else {
			return -1;
		}
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("factions", factions);
		return doc;
	}
	
	public List<Integer> getFactions() {
		return factions;
	}

	public void setFactions(List<Integer> factions) {
		this.factions = factions;
	}

	public void setFromDoc(Document doc) {
		factions = (List<Integer>) doc.get("factions");
	}
	
}
