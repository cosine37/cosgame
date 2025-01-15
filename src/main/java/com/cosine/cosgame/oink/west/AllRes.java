package com.cosine.cosgame.oink.west;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AllRes {
	public static List<Card> genDeck(){
		List<Card> deck = new ArrayList<>();
		deck.add(new Card(15,"观音菩萨",""));
		deck.add(new Card(14,"红孩儿",""));
		deck.add(new Card(13,"孙悟空",""));
		deck.add(new Card(12,"牛魔王",""));
		deck.add(new Card(11,"哪吒",""));
		deck.add(new Card(10,"铁扇公主",""));
		deck.add(new Card(9,"九头虫",""));
		deck.add(new Card(8,"猪八戒",""));
		deck.add(new Card(7,"如意真仙",""));
		deck.add(new Card(6,"万圣公主",""));
		deck.add(new Card(5,"沙僧",""));
		deck.add(new Card(4,"玉面狐狸",""));
		deck.add(new Card(3,"白龙马",""));
		deck.add(new Card(2,"女儿国国王",""));
		deck.add(new Card(1,"唐僧",""));
		return deck;
	}
	
	public static List<Card> genShuffledDeck(){
		List<Card> td = genDeck();
		List<Card> deck = new ArrayList<>();
		Random rand = new Random();
		while (td.size()>0) {
			int x = rand.nextInt(td.size());
			Card c = td.remove(x);
			deck.add(c);
		}
		return deck;
	}
}
