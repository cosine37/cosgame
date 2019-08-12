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
	public static final int NONE = 0;
	public static final int OPTION = 1;
	public static final int HANDCHOOSE = 2;
	public static final int GAIN = 3;
	public static final int VIEW = 4;
	public static final int REACTION = 5;
	
	public static final int THRONE = 11;
	
	int subType; // for view
	public static final int CHOOSE = 51;
	public static final int REARRANGE = 52;
	public static final int ATTACK = 30;
	public static final int ATTACKBLOCK = 31;
	
	String cardName;
	String msg;
	String attackName; // for attackblock
	
	List<String> options;
	List<String> selectedCards;
	List<String> viewedCards;
	List<String> viewedCardsImage;
	List<Integer> selectedRevealed;
	boolean showAsPile; // for view
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
		showAsPile = false;
		
		options = new ArrayList<String>();
		selectedCards = new ArrayList<String>();
		viewedCards = new ArrayList<String>();
		viewedCardsImage = new ArrayList<String>();
		selectedRevealed = new ArrayList<Integer>();
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
	
	public void setViewedCards(List<String>viewedCards) {
		this.viewedCards = viewedCards;
	}
	
	public List<String> getViewedCards(){
		return viewedCards;
	}
	
	public void setViewedCardsImage(List<String>viewedCardsImage) {
		this.viewedCardsImage = viewedCardsImage;
	}
	
	public List<String> getViewedCardsImage(){
		return viewedCardsImage;
	}
	
	public void setSelectedRevealed(List<Integer> selectedRevealed) {
		this.selectedRevealed = selectedRevealed;
	}
	
	public List<Integer> getSelectedRevealed(){
		return selectedRevealed;
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
	
	public void setRestriction(int restriction) {
		this.restriction = restriction;
	}
	
	public int getRestriction() {
		return restriction;
	}
	
	public void setAttackName(String attackName) {
		this.attackName = attackName;
	}
	
	public String getAttackName() {
		return attackName;
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
		} else if (type == VIEW) {
			if (subType == OPTION) {
				ans = Integer.parseInt(s);
			} else if (subType == CHOOSE) {
				if (s.equals("")) {
					selectedRevealed = new ArrayList<Integer>();
				} else {
					selectedRevealed = new ArrayList<Integer>();
					List<String> tsl = Arrays.asList(s.split(","));
					for (int i=0;i<tsl.size();i++) {
						selectedRevealed.add(Integer.parseInt(tsl.get(i)));
					}
					System.out.println("selectedRevealed = "+ selectedRevealed.toString());
				}
			} else if (subType == REARRANGE) {
				if (s.equals("")) {
					selectedRevealed = new ArrayList<Integer>();
				} else {
					selectedRevealed = new ArrayList<Integer>();
					List<String> tsl = Arrays.asList(s.split(","));
					for (int i=0;i<tsl.size();i++) {
						selectedRevealed.add(Integer.parseInt(tsl.get(i)));
					}
				}
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
		} else if (type == VIEW) {
			doc.append("msg", msg);
			doc.append("showAsPile", showAsPile);
			List<Document> doo = new ArrayList<Document>();
			for (int i=0;i<viewedCards.size();i++) {
				Document d = new Document();
				d.append("card", viewedCards.get(i));
				doo.add(d);
			}
			doc.append("viewedCards", doo);
			doo = new ArrayList<Document>();
			for (int i=0;i<viewedCardsImage.size();i++) {
				Document d = new Document();
				d.append("image", viewedCardsImage.get(i));
				doo.add(d);
			}
			doc.append("viewedCardsImage", doo);
			if (subType == OPTION) {
				doo = new ArrayList<Document>();
				for (int i=0;i<options.size();i++) {
					Document d = new Document();
					d.append("option", options.get(i));
					doo.add(d);
				}
				doc.append("options", doo);
				doc.append("ans", ans);
			} else if (subType == CHOOSE) {
				doo = new ArrayList<Document>();
				for (int i=0;i<selectedRevealed.size();i++) {
					Document d = new Document();
					d.append("index", selectedRevealed.get(i));
					doo.add(d);
				}
				doc.append("selectedRevealed", doo);
				doc.append("upper", upper);
				doc.append("lower", lower);
			} else if (subType == REARRANGE) {
				doo = new ArrayList<Document>();
				for (int i=0;i<selectedRevealed.size();i++) {
					Document d = new Document();
					d.append("index", selectedRevealed.get(i));
					doo.add(d);
				}
				doc.append("selectedRevealed", doo);
			}
			doc.append("resLevel", resLevel);
			doc.append("restriction", restriction);
			doc.append("cardName", cardName);
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
		} else if (type == VIEW) {
			msg = (String)doc.get("msg");
			showAsPile = (boolean)doc.get("showAsPile");
			viewedCards = new ArrayList<String>();
			List<Document> doo = (List<Document>)doc.get("viewedCards");
			for (int i=0;i<doo.size();i++) {
				String cardName = (String)doo.get(i).get("card");
				viewedCards.add(cardName);
			}
			viewedCardsImage = new ArrayList<String>();
			doo = (List<Document>)doc.get("viewedCardsImage");
			for (int i=0;i<doo.size();i++) {
				String cardImage = (String)doo.get(i).get("image");
				viewedCardsImage.add(cardImage);
			}
			if (subType == OPTION) {
				doo = (List<Document>)doc.get("options");
				options = new ArrayList<String>();
				for (int i=0;i<doo.size();i++) {
					String option = (String)doo.get(i).get("option");
					options.add(option);
				}
				ans = (int)doc.get("ans");
			} else if (subType == CHOOSE) {
				doo = (List<Document>)doc.get("selectedRevealed");
				selectedRevealed = new ArrayList<Integer>();
				for (int i=0; i<doo.size();i++){
					int cardIndex = (int)doo.get(i).get("index");
					selectedRevealed.add(cardIndex);
				}
				upper = (int)doc.get("upper");
				lower = (int)doc.get("lower");
			} else if (subType == REARRANGE) {
				doo = (List<Document>)doc.get("selectedRevealed");
				selectedRevealed = new ArrayList<Integer>();
				for (int i=0; i<doo.size();i++){
					int cardIndex = (int)doo.get(i).get("index");
					selectedRevealed.add(cardIndex);
				}
			}
			resLevel = (int)doc.get("resLevel");
			restriction = (int)doc.get("restriction");
			cardName = (String)doc.get("cardName");
		}
	}
}
