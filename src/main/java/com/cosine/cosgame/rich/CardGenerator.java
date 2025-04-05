package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

public class CardGenerator {
	int uncommonCount;
	int rareCount;
	int epicCount;
	
	Player player;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("uncommonCount",uncommonCount);
		doc.append("rareCount",rareCount);
		doc.append("epicCount",epicCount);
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		uncommonCount = doc.getInteger("uncommonCount",0);
		rareCount = doc.getInteger("rareCount",0);
		epicCount = doc.getInteger("epicCount",0);
	}
	
	public CardGenerator() {
		uncommonCount = Consts.GUARANTEE_UNCOMMON;
		rareCount = Consts.GUARANTEE_RARE;
		epicCount = Consts.GUARANTEE_EPIC;
	}
	
	public Card generateACard() {
		// Step 1: randomize rarity
		Random rand = new Random();
		int x = rand.nextInt(100);
		int rarity = 0;
		if (x<5) {
			rarity = 3;
		} else if (x<15) {
			rarity = 2;
		} else if (x<40) {
			rarity = 1;
		} else {
			rarity = 0;
		}
		
		// Step 2: deal with guarantee
		if (epicCount == 0) {
			rarity = 3;
		} else if (rareCount == 0) {
			rarity = 2;
		} else if (uncommonCount == 0) {
			rarity = 1;
		}
		
		// Step 3: based on rarity, count down
		if (rarity == 3) {
			uncommonCount = Consts.GUARANTEE_UNCOMMON;
			rareCount = Consts.GUARANTEE_RARE;
			epicCount = Consts.GUARANTEE_EPIC;
		} else if (rarity == 2) {
			uncommonCount = Consts.GUARANTEE_UNCOMMON;
			rareCount = Consts.GUARANTEE_RARE;
			epicCount--;
		} else if (rarity == 1) {
			uncommonCount = Consts.GUARANTEE_UNCOMMON;
			rareCount--;
			epicCount--;
		} else {
			uncommonCount--;
			rareCount--;
			epicCount--;
		}
		
		// Step 4: get the card pool
		List<Integer> cardPool = new ArrayList<>();
		if (rarity == 3) {
			cardPool = player.getBoard().getMap().getEpicCardIds();
		} else if (rarity == 2) {
			cardPool = player.getBoard().getMap().getRareCardIds();
		} else if (rarity == 1) {
			cardPool = player.getBoard().getMap().getUncommonCardIds();
		} else {
			cardPool = player.getBoard().getMap().getCommonCardIds();
		}
		
		// Step 5: generate a card
		if (cardPool.size()>0) {
			int y = rand.nextInt(cardPool.size());
			return Factory.genNewCard(cardPool.get(y));
		} else {
			return null;
		}
	}
	
	public int getUncommonCount() {
		return uncommonCount;
	}
	public void setUncommonCount(int uncommonCount) {
		this.uncommonCount = uncommonCount;
	}
	public int getRareCount() {
		return rareCount;
	}
	public void setRareCount(int rareCount) {
		this.rareCount = rareCount;
	}
	public int getEpicCount() {
		return epicCount;
	}
	public void setEpicCount(int epicCount) {
		this.epicCount = epicCount;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
}
