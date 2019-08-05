package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Ask {
	
	/**
	 * Possible types:
	 * 		None: 		Do nothing
	 * 		Choose: 	Choose between card options
	 * 		Trash: 		Trash cards
	 * 		Gain:		
	 * 		Discard:	
	 * 
	 */
	int type;
	public static final int NONE = 0;
	public static final int OPTION = 1;
	public static final int HANDCHOOSE = 2;
	
	String cardName;
	String msg;
	
	List<String> options;
	int ans;
	int resLevel;
	
	public Ask() {
		type = 0;
		resLevel = 0;
		ans = -1;
		
		options = new ArrayList<String>();
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
	
	public void setAns(int ans) {
		this.ans = ans;
	}
	
	public int getAns() {
		return ans;
	}
	
	public void parseAns(String s) {
		if (type == OPTION) {
			ans = Integer.parseInt(s);
		}
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("type", type);
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
			
		}
		return doc;
	}
	
	public void setAskFromDocument(Document doc) {
		type = (int)doc.get("type");
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
		}
	}
}
