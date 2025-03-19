package com.cosine.cosgame.rich.basicplaces;

import org.bson.Document;

import com.cosine.cosgame.rich.Board;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;

public class InJail extends Place{
	
	public InJail(int id, String name) {
		super(id, name, Consts.PLACE_JAIL);
		img = "jail";
		//desc = "铁门啊~铁窗啊~";
		detail.apply();
		detail.setDesc("铁门啊~铁窗啊~");
		detail.setDesc2("你可以尝试越狱，或是保释。若你成功出狱，还能掷骰子移动。入狱3回合后，会强制保释出狱。入狱期间，你还是可以收路费。");
	}
	
	public InJail(Document doc, Board board) {
		super(doc, board);
	}
	
	
	

}
