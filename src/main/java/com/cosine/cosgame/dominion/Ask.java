package com.cosine.cosgame.dominion;

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
	String type;
	String subtype;
	int lower, upper; // bounds.  eg. trash 1 to 3
	int downto; // for discard downto attacks
	boolean dnamed; // differently named
	String cardType; // eg. gain a treasure card
	
	public Ask() {
		type = "none";
	}
}
