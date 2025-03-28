package com.cosine.cosgame.rich.gta.places;

import org.bson.Document;

import com.cosine.cosgame.rich.Board;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;

public class Ward extends Place{
	
	public Ward(int id, String name) {
		super(id, name, Consts.PLACE_HOSPITAL);
		img = "ward";
		detail.apply();
		detail.setDesc("请各位患者不要死在走廊上~");
		detail.setDesc2("住院时不能使用绝大部分卡牌。选择治疗会回复1点生命值并立即结束回合。当你的生命值不小于1时，你可以选择出院，并支付医疗费。医疗费等于$500×你出院时的生命值。出于人道主义，你如果支付不起医疗费，我们还会给你留$1。你住院时，不能收路费。");
	}
	
	public Ward(Document doc, Board board) {
		super(doc, board);
	}
	
	
	

}
