package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

public class Ask {
	
	/**
	 * Possible types:
	 * 		None: 		Do nothing
	 * 		Option: 	Deals with options
	 * 		Handchoose:	Choose card(s) from hand
	 * 		Gain		Choose card from supply
	 * 		View		View top cards
	 * 		ViewChoose	Choose card(s) from view
	 * 		ViewSort	Sort viewed cards in any order
	 * 		Throne		Play a selected cards 2 or more times
	 * 
	 */
	int type;
	int subType; // for throne
	public static final int NONE = 0;
	public static final int OPTION = 1;
	public static final int HANDCHOOSE = 2;
	public static final int GAIN = 3;
	
	public static final int THRONE = 11;
	
	String cardName;
	String msg;
	
	List<String> options;
	List<String> selectedCards;
	int ans;
	int resLevel;
	
	int upper, lower;
	String thronedCard;
	Ask thronedAsk;
	int thronedAskType;
	public static final int PLAY = 101;
	public static final int RESPONSE = 102;
	
	int restriction;
	public static final int ACTION = 1001;
	public static final int TREASURE = 1002;
	
	public Ask() {
		type = 0;
		resLevel = 0;
		ans = -1;
		lower = 0;
		upper = 0;
		cardName = "";
		msg = "";
		restriction = 0;
		
		options = new ArrayList<String>();
		selectedCards = new ArrayList<String>();
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public void setCardName(String cardName) {
		this.cardName = cardName;
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
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setOptions(List<String> options) {
		this.options = options;
	}
	
	public List<String> getOptions(){
		return options;
	}
	
	public void setSelectedCards(List<String>selectedCards) {
		this.selectedCards = selectedCards;
	}
	
	public List<String> getSelectedCards(){
		return selectedCards;
	}
	
	public void setLower(int lower) {
		this.lower = lower;
	}
	
	public int getLower() {
		return lower;
	}
	
	public void setUpper(int upper) {
		this.upper = upper;
	}
	
	public int getUpper() {
		return upper;
	}
	
	public void setAns(int ans) {
		this.ans = ans;
	}
	
	public int getAns() {
		return ans;
	}
	
	public void setResLevel(int resLevel) {
		this.resLevel = resLevel;
	}
	
	public int getResLevel() {
		return resLevel;
	}
	
	public void setThronedCard(String thronedCard) {
		this.thronedCard = thronedCard;
	}
	
	public String getThronedCard() {
		return thronedCard;
	}
	
	public void setThronedAsk(Ask thronedAsk) {
		this.thronedAsk = thronedAsk;
	}
	
	public Ask getThronedAsk() {
		return thronedAsk;
	}
	
	public void setThronedAskType(int thronedAskType) {
		this.thronedAskType = thronedAskType;
	}
	
	public int getThronedAskType() {
		return thronedAskType;
	}
	
	public void addResLevel() {
		resLevel = resLevel + 1;
	}
	
	public void parseAns(String s) {
		if (type == OPTION) {
			ans = Integer.parseInt(s);
		} else if (type == HANDCHOOSE) {
			if (s.equals("")) {
				selectedCards = new ArrayList<String>();
			} else {
				selectedCards = Arrays.asList(s.split(","));
			}
		} else if (type == THRONE) {
			thronedAsk.parseAns(s);
		} else if (type == GAIN) {
			selectedCards = new ArrayList<String>();
			if (s.equals("")) {
			} else {
				selectedCards.add(s);
			}
		}
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("type", type);
		doc.append("subType", subType);
		if (type == OPTION) {
			List<Document> doo = new ArrayList<Document>();
			for (int i=0;i<options.size();i++) {
				Document d = new Document();
				d.append("option", options.get(i));
				doo.add(d);
			}
			doc.append("msg", msg);
			doc.append("options", doo);
			doc.append("resLevel", resLevel);
			doc.append("ans", ans);
			doc.append("cardName", cardName);
		} else if (type == HANDCHOOSE) {
			List<Document> doo = new ArrayList<Document>();
			for (int i=0;i<selectedCards.size();i++) {
				Document d = new Document();
				d.append("card", selectedCards.get(i));
				doo.add(d);
			}
			doc.append("upper", upper);
			doc.append("lower", lower);
			doc.append("msg", msg);
			doc.append("selectedCards", doo);
			doc.append("resLevel", resLevel);
			doc.append("ans", ans);
			doc.append("cardName", cardName);
			doc.append("restriction", restriction);
		} else if (type == THRONE) {
			Document doa = thronedAsk.toDocument();
			doc.append("thronedAsk", doa);
			doc.append("resLevel", resLevel);
			doc.append("thronedAskType", thronedAskType);
			doc.append("thronedCard", thronedCard);
			doc.append("cardName", cardName);
		} else if (type == GAIN) {
			doc.append("msg", msg);
			doc.append("upper", upper);
			doc.append("lower", lower);
			doc.append("cardName", cardName);
			doc.append("restriction", restriction);
			doc.append("resLevel", resLevel);
			List<Document> doo = new ArrayList<Document>();
			for (int i=0;i<selectedCards.size();i++) {
				Document d = new Document();
				d.append("card", selectedCards.get(i));
				doo.add(d);
			}
			doc.append("selectedCards", doo);
		}
		return doc;
	}
	
	public void setAskFromDocument(Document doc) {
		type = (int)doc.get("type");
		subType = (int)doc.get("subType");
		if (type == NONE) {
			resLevel = 0;
		} else if (type == OPTION) {
			List<Document> doo = (List<Document>)doc.get("options");
			options = new ArrayList<String>();
			for (int i=0;i<doo.size();i++) {
				String option = (String)doo.get(i).get("option");
				options.add(option);
			}
			resLevel = (int)doc.get("resLevel");
			ans = (int)doc.get("ans");
			cardName = (String)doc.get("cardName");
			msg = (String)doc.get("msg");
		} else if (type == HANDCHOOSE) {
			List<Document> doo = (List<Document>)doc.get("selectedCards");
			selectedCards = new ArrayList<String>();
			for (int i=0;i<doo.size();i++) {
				String option = (String)doo.get(i).get("card");
				selectedCards.add(option);
			}
			upper = (int)doc.get("upper");
			lower = (int)doc.get("lower");
			resLevel = (int)doc.get("resLevel");
			ans = (int)doc.get("ans");
			cardName = (String)doc.get("cardName");
			restriction = (int)doc.get("restriction");
			msg = (String)doc.get("msg");
		} else if (type == THRONE) {
			Document doa = (Document)doc.get("thronedAsk");
			thronedAsk = new Ask();
			thronedAsk.setAskFromDocument(doa);
			resLevel = (int)doc.get("resLevel");
			thronedAskType = (int)doc.get("thronedAskType");
			thronedCard = (String)doc.get("thronedCard");
			cardName = (String)doc.get("cardName");
		} else if (type == GAIN) {
			List<Document> doo = (List<Document>)doc.get("selectedCards");
			selectedCards = new ArrayList<String>();
			for (int i=0;i<doo.size();i++) {
				String option = (String)doo.get(i).get("card");
				selectedCards.add(option);
			}
			msg = (String)doc.get("msg");
			upper = (int)doc.get("upper");
			lower = (int)doc.get("lower");
			resLevel = (int)doc.get("resLevel");
			restriction = (int)doc.get("restriction");
			cardName = (String)doc.get("cardName");
			
		}
	}
}
