package com.cosine.cosgame.architect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AllRes {
	List<Card> cardDeck;
	List<Building> buildingDeck;
	
	public AllRes() {
		cardDeck = new ArrayList<>();
		buildingDeck = new ArrayList<>();
		genDeck();
		genBuildings();
	}
	
	
	public void genDeck() {
		Card card;
		
		card = new Card();
		card.setName("蓝猫");
		card.setImg("Bluecat");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c13 = {"bluecat01", "bluecat02", "bluecat03"};
		String[] r13 = {"bluecat04", "bluecat05"};
		card.setQuotes(c13, r13);
		card.addNeedRes(Consts.STONE, 3);
		card.addProvideRes(Consts.IRON, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("康熙");
		card.setImg("KangXi");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c12 = {"kx01", "kx02", "kx03", "kx04"};
		String[] r12 = {"kx05", "kx06", "kx07", "kx08"};
		card.setQuotes(c12, r12);
		card.addNeedRes(Consts.IRON, 3);
		card.addProvideRes(Consts.GOLD, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("孙尚香");
		card.setImg("SunShangXiang");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c11 = {"ssx01", "ssx02", "ssx03", "ssx04"};
		String[] r11 = {"ssx05", "ssx06", "ssx07", "ssx08"};
		card.setQuotes(c11, r11);
		card.addNeedRes(Consts.IRON, 1);
		card.addNeedRes(Consts.WOOD, 2);
		card.addProvideRes(Consts.GOLD, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("王司徒");
		card.setImg("WangLang");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c10 = {"wanglang01", "wanglang02", "wanglang03", "wanglang04"};
		String[] r10 = {"wanglang05", "wanglang06", "wanglang07", "wanglang08"};
		card.setQuotes(c10, r10);
		card.addNeedRes(Consts.WOOD, 5);
		card.addProvideRes(Consts.IRON, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("马6");
		card.setImg("Ma6");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c9 = {"ma601"};
		String[] r9 = {"ma602", "ma603"};
		card.setQuotes(c9, r9);
		card.addNeedRes(Consts.WOOD, 5);
		card.addProvideRes(Consts.GOLD, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("元首");
		card.setImg("Headman");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c8 = {"headman01", "headman02", "headman03", "headman04"};
		String[] r8 = {"headman05", "headman06", "headman07", "headman08"};
		card.setQuotes(c8, r8);
		card.addNeedRes(Consts.STONE, 3);
		card.addProvideRes(Consts.GOLD, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("豆狸");
		card.setImg("DouLi");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c7 = {"ac01", "ac02", "ac03"};
		String[] r7 = {"ac04", "ac05", "ac06"};
		card.setQuotes(c7, r7);
		card.addNeedRes(Consts.WOOD, 3);
		card.addProvideRes(Consts.STONE, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("粒狸");
		card.setImg("LiLi");
		card.setTypeWithQuote(Consts.TRADER);
		card.setQuotes(c7, r7);
		card.addNeedRes(Consts.WOOD, 3);
		card.addProvideRes(Consts.STONE, 1);
		card.addProvideRes(Consts.IRON, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("疯狂戴夫");
		card.setImg("CrazyDave");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c6 = {"dave01", "dave02", "dave03"};
		String[] r6 = {"dave04", "dave05", "dave06"};
		card.setQuotes(c6, r6);
		card.addNeedRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.IRON, 1);
		card.addProvideRes(Consts.WOOD, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("呀哈哈");
		card.setImg("Korok");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c5 = {"korok01"};
		String[] r5 = {"korok02"};
		card.setQuotes(c5, r5);
		card.addNeedRes(Consts.STONE, 1);
		card.addProvideRes(Consts.WOOD, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("村民");
		card.setImg("Villager");
		card.setTypeWithQuote(Consts.TRADERALT03);
		card.addNeedRes(Consts.STONE, 3);
		card.addProvideRes(Consts.IRON, 1);
		card.addProvideRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.WOOD, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("村民");
		card.setImg("Villager");
		card.setTypeWithQuote(Consts.TRADERALT03);
		card.addNeedRes(Consts.STONE, 2);
		card.addProvideRes(Consts.IRON, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("村民");
		card.setImg("Villager");
		card.setTypeWithQuote(Consts.TRADERALT03);
		card.addNeedRes(Consts.STONE, 2);
		card.addProvideRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.WOOD, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("村民");
		card.setImg("Villager");
		card.setTypeWithQuote(Consts.TRADERALT03);
		card.addNeedRes(Consts.STONE, 3);
		card.addProvideRes(Consts.IRON, 2);
		card.addProvideRes(Consts.WOOD, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("村民");
		card.setImg("Villager");
		card.setTypeWithQuote(Consts.TRADERALT03);
		card.addNeedRes(Consts.STONE, 2);
		card.addProvideRes(Consts.IRON, 1);
		card.addProvideRes(Consts.WOOD, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("商人");
		card.setImg("Merchant02");
		card.setTypeWithQuote(Consts.TRADERALT01);
		card.addNeedRes(Consts.IRON, 2);
		card.addProvideRes(Consts.GOLD, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("商人");
		card.setImg("Merchant02");
		card.setTypeWithQuote(Consts.TRADERALT01);
		card.addNeedRes(Consts.IRON, 1);
		card.addProvideRes(Consts.WOOD, 1);
		card.addProvideRes(Consts.STONE, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("商人");
		card.setImg("Merchant02");
		card.setTypeWithQuote(Consts.TRADERALT01);
		card.addNeedRes(Consts.IRON, 1);
		card.addProvideRes(Consts.STONE, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("商人");
		card.setImg("Merchant02");
		card.setTypeWithQuote(Consts.TRADERALT01);
		card.addNeedRes(Consts.IRON, 2);
		card.addProvideRes(Consts.WOOD, 2);
		card.addProvideRes(Consts.STONE, 1);
		card.addProvideRes(Consts.GOLD, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("商人");
		card.setImg("Merchant02");
		card.setTypeWithQuote(Consts.TRADERALT01);
		card.addNeedRes(Consts.IRON, 2);
		card.addProvideRes(Consts.WOOD, 2);
		card.addProvideRes(Consts.STONE, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("商人");
		card.setImg("Merchant01");
		card.setTypeWithQuote(Consts.TRADER);
		card.addNeedRes(Consts.WOOD, 2);
		card.addProvideRes(Consts.STONE, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("商人");
		card.setImg("Merchant01");
		card.setTypeWithQuote(Consts.TRADER);
		card.addNeedRes(Consts.WOOD, 1);
		card.addNeedRes(Consts.STONE, 1);
		card.addProvideRes(Consts.GOLD, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("商人");
		card.setImg("Merchant01");
		card.setTypeWithQuote(Consts.TRADER);
		card.addNeedRes(Consts.WOOD, 2);
		card.addProvideRes(Consts.IRON, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("商人");
		card.setImg("Merchant01");
		card.setTypeWithQuote(Consts.TRADER);
		card.addNeedRes(Consts.WOOD, 4);
		card.addProvideRes(Consts.IRON, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("商人");
		card.setImg("Merchant01");
		card.setTypeWithQuote(Consts.TRADER);
		card.addNeedRes(Consts.WOOD, 3);
		card.addProvideRes(Consts.GOLD, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("金币商人");
		card.setImg("Merchant03");
		card.setTypeWithQuote(Consts.TRADERALT02);
		card.addNeedRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.IRON, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("金币商人");
		card.setImg("Merchant03");
		card.setTypeWithQuote(Consts.TRADERALT02);
		card.addNeedRes(Consts.GOLD, 2);
		card.addProvideRes(Consts.STONE, 1);
		card.addProvideRes(Consts.WOOD, 1);
		card.addProvideRes(Consts.IRON, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("金币商人");
		card.setImg("Merchant03");
		card.setTypeWithQuote(Consts.TRADERALT02);
		card.addNeedRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.STONE, 1);
		card.addProvideRes(Consts.WOOD, 1);
		card.addProvideRes(Consts.IRON, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("金币商人");
		card.setImg("Merchant03");
		card.setTypeWithQuote(Consts.TRADERALT02);
		card.addNeedRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.STONE, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("金币商人");
		card.setImg("Merchant03");
		card.setTypeWithQuote(Consts.TRADERALT02);
		card.addNeedRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.WOOD, 2);
		card.addProvideRes(Consts.STONE, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("樵夫");
		card.setImg("Hiker");
		card.setTypeWithQuote(Consts.WORKER);
		card.addProvideRes(Consts.WOOD, 1);
		card.addProvideRes(Consts.STONE, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("樵夫");
		card.setImg("Hiker");
		card.setTypeWithQuote(Consts.WORKER);
		card.addProvideRes(Consts.WOOD, 2);
		card.addProvideRes(Consts.STONE, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("樵夫");
		card.setImg("Hiker");
		card.setTypeWithQuote(Consts.WORKER);
		card.addProvideRes(Consts.WOOD, 1);
		card.addProvideRes(Consts.IRON, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("高级木工");
		card.setImg("Lumberjack");
		card.setTypeWithQuote(Consts.WORKER);
		card.addProvideRes(Consts.WOOD, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("大象");
		card.setImg("Elephant");
		card.setTypeWithQuote(Consts.WORKER);
		String[] c1 = {"elephant01"};
		String[] r1 = {"elephant01"};
		card.setQuotes(c1, r1);
		card.addProvideRes(Consts.WOOD, 4);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("矿工");
		card.setImg("Miner");
		card.setTypeWithQuote(Consts.WORKER);
		card.addProvideRes(Consts.GOLD, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("矿工");
		card.setImg("Miner");
		card.setTypeWithQuote(Consts.WORKER);
		card.addProvideRes(Consts.IRON, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("矿工");
		card.setImg("Miner");
		card.setTypeWithQuote(Consts.WORKER);
		card.addProvideRes(Consts.STONE, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("高级法师");
		card.setImg("Magician");
		card.setTypeWithQuote(Consts.MAGICIAN);
		card.setNumUpgrade(3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("油田");
		card.setImg("YouTian");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c2 = {"youtian01", "youtian03"};
		String[] r2 = {"youtian02", "youtian04"};
		card.setQuotes(c2, r2);
		card.addNeedRes(Consts.IRON, 2);
		card.addProvideRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.STONE, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("金币商人");
		card.setImg("Merchant03");
		card.setTypeWithQuote(Consts.TRADERALT02);
		card.addNeedRes(Consts.GOLD, 2);
		card.addProvideRes(Consts.IRON, 2);
		card.addProvideRes(Consts.STONE, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("刘华强");
		card.setImg("HuaQiang");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c3 = {"huaqiang01", "huaqiang02", "huaqiang03", "huaqiang04"};
		String[] r3 = {"huaqiang05", "huaqiang06", "huaqiang07"};
		card.setQuotes(c3, r3);
		card.addNeedRes(Consts.IRON, 1);
		card.addProvideRes(Consts.WOOD, 4);
		card.addProvideRes(Consts.STONE, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("郝哥");
		card.setImg("Gua");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c4 = {"gua01", "gua02", "gua03"};
		String[] r4 = {"gua04", "gua05", "gua06"};
		card.setQuotes(c4, r4);
		card.addNeedRes(Consts.WOOD, 4);
		card.addProvideRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.IRON, 1);
		cardDeck.add(card);
		
	}
	
	public void genBuildings() {
		Building b;
		
		b = new Building();
		b.setName("长城");
		b.setImg("TheGreatWall");
		b.setPrice(1,1,1,3);
		b.setScore(20);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("布达拉宫");
		b.setImg("Potala");
		b.setPrice(1,1,3,1);
		b.setScore(18);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("金字塔");
		b.setImg("Pyramid");
		b.setPrice(1,3,1,1);
		b.setScore(16);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("空中花园");
		b.setImg("HangingGarden");
		b.setPrice(3,1,1,1);
		b.setScore(14);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("兵马俑");
		b.setImg("Terracotta");
		b.setPrice(0,2,2,2);
		b.setScore(19);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("圣巴西利亚");
		b.setImg("StBasil");
		b.setPrice(2,0,2,2);
		b.setScore(17);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("比萨斜塔");
		b.setImg("Pisa");
		b.setPrice(2,2,0,2);
		b.setScore(15);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("白宫");
		b.setImg("Whitehouse");
		b.setPrice(2,2,2,0);
		b.setScore(13);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("故宫");
		b.setImg("ForbiddenCity");
		b.setPrice(0,0,0,5);
		b.setScore(20);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("卢浮宫");
		b.setImg("Louvre");
		b.setPrice(0,0,2,3);
		b.setScore(18);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("大本钟");
		b.setImg("BigBen");
		b.setPrice(0,0,3,2);
		b.setScore(17);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("斗兽场");
		b.setImg("Colosseum");
		b.setPrice(0,2,0,3);
		b.setScore(16);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("泰姬陵");
		b.setImg("Taj");
		b.setPrice(0,0,0,4);
		b.setScore(16);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("埃菲尔铁塔");
		b.setImg("Eiffel");
		b.setPrice(0,0,5,0);
		b.setScore(15);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("豪宅");
		b.setImg("Villa");
		b.setPrice(0,2,1,1);
		b.setScore(12);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("豪宅");
		b.setImg("Villa");
		b.setPrice(1,1,1,1);
		b.setScore(12);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("豪宅");
		b.setImg("Villa");
		b.setPrice(1,0,2,1);
		b.setScore(12);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("大教堂");
		b.setImg("Cathedral");
		b.setPrice(0,2,0,2);
		b.setScore(12);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("大教堂");
		b.setImg("Cathedral");
		b.setPrice(0,3,0,2);
		b.setScore(14);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("寺庙");
		b.setImg("Temple");
		b.setPrice(2,0,0,3);
		b.setScore(14);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("工厂");
		b.setImg("Factory");
		b.setPrice(0,0,2,2);
		b.setScore(14);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("工厂");
		b.setImg("Factory");
		b.setPrice(0,2,3,0);
		b.setScore(13);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("工厂");
		b.setImg("Factory");
		b.setPrice(0,0,4,0);
		b.setScore(12);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("工厂");
		b.setImg("Factory");
		b.setPrice(2,0,3,0);
		b.setScore(11);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("庄园");
		b.setImg("Manor");
		b.setPrice(2,0,0,2);
		b.setScore(10);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("庄园");
		b.setImg("Manor");
		b.setPrice(3,0,0,2);
		b.setScore(11);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("庄园");
		b.setImg("Manor");
		b.setPrice(2,1,0,1);
		b.setScore(9);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("木屋");
		b.setImg("Cabin");
		b.setPrice(2,2,0,0);
		b.setScore(6);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("木屋");
		b.setImg("Cabin");
		b.setPrice(3,2,0,0);
		b.setScore(7);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("木屋");
		b.setImg("Cabin");
		b.setPrice(2,0,2,0);
		b.setScore(8);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("石屋");
		b.setImg("StoneHouse");
		b.setPrice(2,3,0,0);
		b.setScore(8);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("木屋");
		b.setImg("Cabin");
		b.setPrice(3,0,2,0);
		b.setScore(9);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("石屋");
		b.setImg("StoneHouse");
		b.setPrice(0,4,0,0);
		b.setScore(8);
		buildingDeck.add(b);
		b = new Building();
		
		b = new Building();
		b.setName("石屋");
		b.setImg("StoneHouse");
		b.setPrice(0,5,0,0);
		b.setScore(10);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("铁匠铺");
		b.setImg("Smithy");
		b.setPrice(0,2,2,0);
		b.setScore(10);
		buildingDeck.add(b);
		
		b = new Building();
		b.setName("铁匠铺");
		b.setImg("Smithy");
		b.setPrice(0,3,2,0);
		b.setScore(12);
		buildingDeck.add(b);
	}
	
	public Card getWoodCutter() {
		Card card = new Card();
		card.setName("伐木工");
		card.setImg("WoodCutter");
		card.setTypeWithQuote(Consts.WORKER);
		card.addProvideRes(Consts.WOOD, 2);
		
		return card;
	}
	
	public Card getMage() {
		Card card = new Card();
		card.setName("法师");
		card.setImg("Mage");
		card.setTypeWithQuote(Consts.MAGICIAN);
		card.setNumUpgrade(2);
		
		return card;
	}
	
	public List<Card> getShuffledCardDeck(){
		List<Card> ans = new ArrayList<>();
		List<Card> tb = new ArrayList<>();
		int i;
		for (i=0;i<cardDeck.size();i++) {
			tb.add(cardDeck.get(i));
		}
		while (tb.size()>0) {
			int n = tb.size();
			Random rand = new Random();
			int x = rand.nextInt(n);
			Card b = tb.remove(x);
			ans.add(b);
		}
		return ans;
	}
	
	public List<Building> getShuffledBuildingDeck(){
		List<Building> ans = new ArrayList<>();
		List<Building> tb = new ArrayList<>();
		int i;
		for (i=0;i<buildingDeck.size();i++) {
			tb.add(buildingDeck.get(i));
		}
		while (tb.size()>0) {
			int n = tb.size();
			Random rand = new Random();
			int x = rand.nextInt(n);
			Building b = tb.remove(x);
			ans.add(b);
		}
		return ans;
	}

	public List<Card> getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(List<Card> cardDeck) {
		this.cardDeck = cardDeck;
	}

	public List<Building> getBuildingDeck() {
		return buildingDeck;
	}

	public void setBuildingDeck(List<Building> buildingDeck) {
		this.buildingDeck = buildingDeck;
	}
	
}
