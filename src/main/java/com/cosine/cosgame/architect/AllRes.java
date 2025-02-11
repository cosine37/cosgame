package com.cosine.cosgame.architect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AllRes {
	List<Card> cardDeck;
	List<Card> bonusCards;
	List<Card> kx1Cards;
	List<Building> buildingDeck;
	
	public AllRes() {
		cardDeck = new ArrayList<>();
		buildingDeck = new ArrayList<>();
		bonusCards = new ArrayList<>();
		kx1Cards = new ArrayList<>();
		genDeck();
		genBonusCards();
		genBuildings();
		genKX1Cards();
	}
	
	public void genKX1Cards() {
		Card card;
		
		card = new Card();
		card.setName("觉醒矿工");
		card.setImg("AwakenMiner");
		card.setTypeWithQuote(Consts.AWAKENING);
		card.setSubType(Consts.WORKER);
		String[] c20 = {"awakenwoodcutter01", "awakenwoodcutter02", "awakenwoodcutter03"};
		String[] r20 = {"awakenwoodcutter04", "awakenwoodcutter05", "awakenwoodcutter06"};
		card.setQuotes(c20, r20);
		card.addProvideRes(Consts.STONE, 1);
		card.setNumAwaken(1);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("觉醒法师");
		card.setImg("AwakenMage");
		card.setTypeWithQuote(Consts.AWAKENING);
		card.setSubType(Consts.MAGICIAN);
		String[] c19 = {"awakenmage01", "awakenmage02", "awakenmage03"};
		String[] r19 = {"awakenmage04", "awakenmage05"};
		card.setQuotes(c19, r19);
		card.setNumUpgrade(1);
		card.setNumAwaken(2);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("章承恩");
		card.setImg("ZhangChengEn");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c18 = {"zhangchengen01", "zhangchengen02"};
		String[] r18 = {"zhangchengen03", "zhangchengen04"};
		card.setQuotes(c18, r18);
		card.addNeedRes(Consts.STONE, 4);
		card.addProvideRes(Consts.GOLD, 3);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("吕布");
		card.setImg("LvBu");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c17 = {"lvbu01", "lvbu02", "lvbu03"};
		String[] r17 = {"lvbu04", "lvbu05", "lvbu06", "lvbu07"};
		card.setQuotes(c17, r17);
		card.addNeedRes(Consts.WOOD, 5);
		card.addProvideRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.IRON, 1);
		card.addProvideRes(Consts.STONE, 1);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("杨超越");
		card.setImg("YangChaoYue");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c16 = {"yangchaoyue01"};
		String[] r16 = {"yangchaoyue02", "yangchaoyue03"};
		card.setQuotes(c16, r16);
		card.addNeedRes(Consts.WOOD, 3);
		card.addProvideRes(Consts.IRON, 1);
		card.addProvideRes(Consts.WOOD, 1);
		card.addResOn(Consts.WOOD);
		card.addResOn(Consts.WOOD);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("矮人矿工");
		card.setImg("Saboteur");
		card.setTypeWithQuote(Consts.WORKER);
		card.addProvideRes(Consts.STONE, 1);
		card.addResOn(Consts.STONE);
		card.addResOn(Consts.WOOD);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("女僵尸");
		card.setImg("FemaleZombie");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c15 = {"femalezombie01", "femalezombie02", "femalezombie03"};
		String[] r15 = {"femalezombie04", "femalezombie05", "femalezombie06"};
		card.setQuotes(c15, r15);
		card.addNeedRes(Consts.STONE, 3);
		card.addProvideRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.IRON, 1);
		card.addProvideRes(Consts.WOOD, 1);
		card.addResOn(Consts.STONE);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("封飙");
		card.setImg("FengBiao");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c14 = {"fengbiao01", "fengbiao02"};
		String[] r14 = {"fengbiao04", "fengbiao03"};
		card.setQuotes(c14, r14);
		card.addNeedRes(Consts.STONE, 1);
		card.addNeedRes(Consts.WOOD, 2);
		card.addProvideRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.WOOD, 1);
		card.addResOn(Consts.STONE);
		card.addResOn(Consts.WOOD);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("振涛");
		card.setImg("ZhenTao");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c13 = {"zhentao01", "zhentao02", "zhentao03"};
		String[] r13 = {"zhentao04", "zhentao05", "zhentao06"};
		card.setQuotes(c13, r13);
		card.addNeedRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.STONE, 2);
		card.addProvideRes(Consts.WOOD, 1);
		card.addResOn(Consts.GOLD);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("宋老虎");
		card.setImg("TigerSong");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c12 = {"tigersong01", "tigersong02"};
		String[] r12 = {"tigersong03", "tigersong04"};
		card.setQuotes(c12, r12);
		card.addNeedRes(Consts.GOLD, 2);
		card.addProvideRes(Consts.IRON, 1);
		card.addProvideRes(Consts.STONE, 3);
		card.addProvideRes(Consts.WOOD, 1);
		card.addResOn(Consts.GOLD);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("邢道荣");
		card.setImg("XingDaoRong");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c11 = {"xingdaorong01", "xingdaorong02", "xingdaorong03"};
		String[] r11 = {"xingdaorong04", "xingdaorong05", "xingdaorong06"};
		card.setQuotes(c11, r11);
		card.addNeedRes(Consts.IRON, 1);
		card.addProvideRes(Consts.STONE, 1);
		card.addProvideRes(Consts.WOOD, 2);
		card.addResOn(Consts.IRON);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("潘凤");
		card.setImg("PanFeng");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c10 = {"panfeng01", "panfeng02"};
		String[] r10 = {"panfeng03", "panfeng04"};
		card.setQuotes(c10, r10);
		card.addNeedRes(Consts.IRON, 2);
		card.addProvideRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.STONE, 1);
		card.addProvideRes(Consts.WOOD, 1);
		card.addResOn(Consts.IRON);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("程书林");
		card.setImg("MianJin");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c9 = {"mianjin01"};
		String[] r9 = {"mianjin02"};
		card.setQuotes(c9, r9);
		card.addNeedRes(Consts.WOOD, 2);
		card.addProvideRes(Consts.WOOD, 5);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("韩红");
		card.setImg("HanHong");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c8 = {"hanhong01"};
		String[] r8 = {"hanhong02"};
		card.setQuotes(c8, r8);
		card.addNeedRes(Consts.STONE, 2);
		card.addNeedRes(Consts.IRON, 1);
		card.addProvideRes(Consts.GOLD, 2);
		card.addProvideRes(Consts.WOOD, 1);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("吴强");
		card.setImg("GaoYin");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c7 = {"gaoyin01"};
		String[] r7 = {"gaoyin02"};
		card.setQuotes(c7, r7);
		card.addNeedRes(Consts.GOLD, 1);
		card.addNeedRes(Consts.IRON, 1);
		card.addProvideRes(Consts.STONE, 5);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("唐林康");
		card.setImg("BoLan");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c6 = {"bolan01"};
		String[] r6 = {"bolan02"};
		card.setQuotes(c6, r6);
		card.addNeedRes(Consts.IRON, 3);
		card.addProvideRes(Consts.IRON, 4);
		card.addProvideRes(Consts.WOOD, 1);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("森林法师");
		card.setImg("ForestMage");
		card.setTypeWithQuote(Consts.SPECIALMAGICIAN);
		String[] c5 = {"awakenmage01", "awakenmage02", "awakenmage03"};
		String[] r5 = {"awakenmage04", "awakenmage05"};
		card.setQuotes(c5, r5);
		card.addNeedRes(Consts.WOOD, 1);
		card.setNumUpgrade(2);
		card.addResOn(Consts.WOOD);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("丁凯乐");
		card.setImg("LeLe");
		card.setTypeWithQuote(Consts.SPECIALMAGICIAN);
		String[] c4 = {"lele01"};
		String[] r4 = {"lele02"};
		card.setQuotes(c4, r4);
		card.addNeedRes(Consts.STONE, 1);
		card.setNumUpgrade(4);
		card.addResOn(Consts.STONE);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("李根");
		card.setImg("LiGen");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c3 = {"ligen01"};
		String[] r3 = {"ligen02"};
		card.setQuotes(c3, r3);
		card.addNeedRes(Consts.STONE, 2);
		card.addProvideRes(Consts.STONE, 3);
		card.addProvideRes(Consts.WOOD, 1);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("何胜");
		card.setImg("HeSheng");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c2 = {"hesheng01"};
		String[] r2 = {"hesheng02"};
		card.setQuotes(c2, r2);
		card.addNeedRes(Consts.GOLD, 2);
		card.addProvideRes(Consts.GOLD, 3);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("见习骑士");
		card.setImg("Page");
		card.setTypeWithQuote(Consts.MAGICIAN);
		card.setSubType(Consts.WORKER);
		String[] c1 = {"oldmage01", "oldmage02", "oldmage03"};
		String[] r1 = {"oldmage04", "oldmage05", "oldmage06"};
		card.setQuotes(c1, r1);
		card.setNumUpgrade(2);
		card.addProvideRes(Consts.WOOD, 2);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("铁甲法师");
		card.setImg("IronMage");
		card.setTypeWithQuote(Consts.MAGICIAN);
		card.setNumUpgrade(2);
		card.addResOn(Consts.IRON);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("铁甲木工");
		card.setImg("IronWoodCutter");
		card.setTypeWithQuote(Consts.WORKER);
		card.addProvideRes(Consts.WOOD, 2);
		card.addResOn(Consts.IRON);
		kx1Cards.add(card);
		
		card = new Card();
		card.setName("老年木工");
		card.setImg("OldWoodcutter");
		card.setTypeWithQuote(Consts.WORKER);
		card.addProvideRes(Consts.WOOD, 1);
		card.addResOn(Consts.GOLD);
		kx1Cards.add(card);
	}
	
	public void genBonusCards() {
		Card card;
		
		card = new Card();
		card.setName("谭警官");
		card.setImg("TanQiao");
		card.setTypeWithQuote(Consts.SPECIALTRADER);
		String[] c5 = {"tanqiao01", "tanqiao02"};
		String[] r5 = {"tanqiao03", "tanqiao04"};
		card.setQuotes(c5, r5);
		card.addNeedRes(Consts.WOOD, 3);
		card.addProvideRes(Consts.STONE, 2);
		card.addProvideResAlt(Consts.IRON, 1);
		bonusCards.add(card);
		
		card = new Card();
		card.setName("三轮大爷");
		card.setImg("ErXianQiao");
		card.setTypeWithQuote(Consts.TRADER);
		card.setSubType(Consts.WORKER);
		String[] c4 = {"erxianqiao01", "erxianqiao02", "erxianqiao03"};
		String[] r4 = {"erxianqiao02", "erxianqiao03", "erxianqiao04", "erxianqiao05"};
		card.setQuotes(c4, r4);
		card.addNeedRes(Consts.WOOD, 4);
		card.addProvideRes(Consts.STONE, 2);
		card.addProvideRes(Consts.IRON, 1);
		bonusCards.add(card);
		
		card = new Card();
		card.setName("觉醒木工");
		card.setImg("AwakenWoodCutter");
		card.setTypeWithQuote(Consts.AWAKENING);
		card.setSubType(Consts.WORKER);
		String[] c3 = {"awakenwoodcutter01", "awakenwoodcutter02", "awakenwoodcutter03"};
		String[] r3 = {"awakenwoodcutter04", "awakenwoodcutter05", "awakenwoodcutter06"};
		card.setQuotes(c3, r3);
		card.addProvideRes(Consts.WOOD, 2);
		card.setNumAwaken(1);
		bonusCards.add(card);
		
		card = new Card();
		card.setName("觉醒法师");
		card.setImg("AwakenMage");
		card.setTypeWithQuote(Consts.AWAKENING);
		card.setSubType(Consts.MAGICIAN);
		String[] c2 = {"awakenmage01", "awakenmage02", "awakenmage03"};
		String[] r2 = {"awakenmage04", "awakenmage05"};
		card.setQuotes(c2, r2);
		card.setNumUpgrade(2);
		card.setNumAwaken(1);
		bonusCards.add(card);
		
		card = new Card();
		card.setName("流浪法师");
		card.setImg("WanderingMage");
		card.setTypeWithQuote(Consts.MAGICIAN);
		card.setSubType(Consts.WORKER);
		String[] c1 = {"oldmage01", "oldmage02", "oldmage03"};
		String[] r1 = {"oldmage04", "oldmage05", "oldmage06"};
		card.setQuotes(c1, r1);
		card.setNumUpgrade(1);
		card.addProvideRes(Consts.IRON, 1);
		bonusCards.add(card);
		
		card = new Card();
		card.setName("流浪法师");
		card.setImg("WanderingMage");
		card.setTypeWithQuote(Consts.MAGICIAN);
		card.setSubType(Consts.WORKER);
		card.setQuotes(c1, r1);
		card.setNumUpgrade(1);
		card.addProvideRes(Consts.STONE, 1);
		bonusCards.add(card);
	}
	
	public void genDeck() {
		Card card;
		
		card = new Card();
		card.setName("一姬");
		card.setImg("YiChi");
		card.setTypeWithQuote(Consts.TRADERALT02);
		String[] c34 = {"yichi01", "yichi02", "yichi03"};
		String[] r34 = {"yichi04", "yichi05"};
		card.setQuotes(c34, r34);
		card.addNeedRes(Consts.GOLD, 2);
		card.addProvideRes(Consts.IRON, 2);
		card.addProvideRes(Consts.STONE, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("老冯");
		card.setImg("Feng");
		card.setTypeWithQuote(Consts.TRADERALT01);
		String[] c33 = {"feng01", "feng02", "feng03", "feng04", "feng05"};
		String[] r33 = {"feng06", "feng07", "feng08", "feng09", "feng10"};
		card.setQuotes(c33, r33);
		card.addNeedRes(Consts.IRON, 1);
		card.addProvideRes(Consts.STONE, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("阿嬷");
		card.setImg("Amo");
		card.setTypeWithQuote(Consts.TRADERALT03);
		String[] c32 = {"amo01","amo02"};
		String[] r32 = {"amo03","amo04"};
		card.setQuotes(c32, r32);
		card.addNeedRes(Consts.STONE, 2);
		card.addProvideRes(Consts.IRON, 1);
		card.addProvideRes(Consts.WOOD, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("王境泽");
		card.setImg("ZhenXiang");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c31 = {"zhenxiang01"};
		String[] r31 = {"zhenxiang02"};
		card.setQuotes(c31, r31);
		card.addNeedRes(Consts.WOOD, 2);
		card.addProvideRes(Consts.STONE, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("瑞克");
		card.setImg("Rick");
		card.setTypeWithQuote(Consts.TRADERALT03);
		String[] c30 = {"rick01"};
		String[] r30 = {"rick02"};
		card.setQuotes(c30, r30);
		card.addNeedRes(Consts.STONE, 3);
		card.addProvideRes(Consts.IRON, 1);
		card.addProvideRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.WOOD, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("日本农民");
		card.setImg("Japan");
		card.setTypeWithQuote(Consts.WORKER);
		String[] c29 = {"jinkela04", "jinkela05"};
		String[] r29 = {"jinkela06"};
		card.setQuotes(c29, r29);
		card.addProvideRes(Consts.WOOD, 1);
		card.addProvideRes(Consts.STONE, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("非洲农民");
		card.setImg("Africa");
		card.setTypeWithQuote(Consts.WORKER);
		String[] c28 = {"jinkela01", "jinkela02"};
		String[] r28 = {"jinkela03"};
		card.setQuotes(c28, r28);
		card.addProvideRes(Consts.WOOD, 2);
		card.addProvideRes(Consts.STONE, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("乌兰图雅");
		card.setImg("TuYa");
		card.setTypeWithQuote(Consts.TRADERALT01);
		String[] c27 = {"tuya01", "tuya02"};
		String[] r27 = {"tuya03"};
		card.setQuotes(c27, r27);
		card.addNeedRes(Consts.IRON, 2);
		card.addProvideRes(Consts.GOLD, 2);
		cardDeck.add(card);

		card = new Card();
		card.setName("杜海皇");
		card.setImg("HaiHuang");
		card.setTypeWithQuote(Consts.TRADERALT03);
		String[] c26 = {"haihuang01", "haihuang02", "haihuang03"};
		String[] r26 = {"haihuang04", "haihuang05"};
		card.setQuotes(c26, r26);
		card.addNeedRes(Consts.STONE, 2);
		card.addProvideRes(Consts.IRON, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("马保国");
		card.setImg("MaBaoGuo");
		card.setTypeWithQuote(Consts.TRADERALT02);
		String[] c25 = {"mabaoguo01", "mabaoguo02", "mabaoguo03"};
		String[] r25 = {"mabaoguo04", "mabaoguo05", "mabaoguo06"};
		card.setQuotes(c25, r25);
		card.addNeedRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.IRON, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("冬泳怪鸽");
		card.setImg("AoLiGei");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c24 = {"aoligei01", "aoligei02"};
		String[] r24 = {"aoligei03", "aoligei04", "aoligei05"};
		card.setQuotes(c24, r24);
		card.addNeedRes(Consts.STONE, 3);
		card.addProvideRes(Consts.IRON, 2);
		card.addProvideRes(Consts.WOOD, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("风小逸");
		card.setImg("TaoTao");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c23 = {"taotao01"};
		String[] r23 = {"taotao02", "taotao03"};
		card.setQuotes(c23, r23);
		card.addNeedRes(Consts.WOOD, 4);
		card.addProvideRes(Consts.IRON, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("悦悦大叔");
		card.setImg("GieGie");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c22 = {"giegie01"};
		String[] r22 = {"giegie02"};
		card.setQuotes(c22, r22);
		card.addNeedRes(Consts.IRON, 2);
		card.addProvideRes(Consts.WOOD, 2);
		card.addProvideRes(Consts.STONE, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("林参谋");
		card.setImg("Lin");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c21 = {"lin01"};
		String[] r21 = {"lin02"};
		card.setQuotes(c21, r21);
		card.addNeedRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.WOOD, 2);
		card.addProvideRes(Consts.STONE, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("鲁班七号");
		card.setImg("LuBan");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c20 = {"luban01", "luban02", "luban06"};
		String[] r20 = {"luban03", "luban04", "luban05"};
		card.setQuotes(c20, r20);
		card.addNeedRes(Consts.WOOD, 3);
		card.addProvideRes(Consts.GOLD, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("羊巴鲁");
		card.setImg("YangBaLu");
		card.setTypeWithQuote(Consts.WORKER);
		String[] c19 = {"yangbalu01", "yangbalu02"};
		String[] r19 = {"yangbalu03"};
		card.setQuotes(c19, r19);
		card.addProvideRes(Consts.WOOD, 1);
		card.addProvideRes(Consts.IRON, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("黄金矿工");
		card.setImg("GoldMiner");
		card.setTypeWithQuote(Consts.WORKER);
		String[] c18 = {"goldminer01", "goldminer05"};
		String[] r18 = {"goldminer02", "goldminer04", "goldminer03"};
		card.setQuotes(c18, r18);
		card.addProvideRes(Consts.GOLD, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("雪王");
		card.setImg("Snow");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c17 = {"snow01", "snow03"};
		String[] r17 = {"snow02", "snow04"};
		card.setQuotes(c17, r17);
		card.addNeedRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.STONE, 1);
		card.addProvideRes(Consts.WOOD, 1);
		card.addProvideRes(Consts.IRON, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("阿瑋");
		card.setImg("Wei");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c16 = {"wei01", "wei02"};
		String[] r16 = {"wei03", "wei04"};
		card.setQuotes(c16, r16);
		card.addNeedRes(Consts.IRON, 1);
		card.addProvideRes(Consts.WOOD, 1);
		card.addProvideRes(Consts.STONE, 2);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("杰哥");
		card.setImg("Jie");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c15 = {"jie01", "jie02", "jie06"};
		String[] r15 = {"jie04", "jie05", "jie03"};
		card.setQuotes(c15, r15);
		card.addNeedRes(Consts.WOOD, 2);
		card.addProvideRes(Consts.IRON, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("孙笨");
		card.setImg("SunBen");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c14 = {"sunben01", "sunben02", "sunben03"};
		String[] r14 = {"sunben04", "sunben05", "sunben06"};
		card.setQuotes(c14, r14);
		card.addNeedRes(Consts.GOLD, 2);
		card.addProvideRes(Consts.STONE, 1);
		card.addProvideRes(Consts.WOOD, 1);
		card.addProvideRes(Consts.IRON, 3);
		cardDeck.add(card);
		
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
		card.addNeedRes(Consts.STONE, 2);
		card.addProvideRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.WOOD, 2);
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
		card.setImg("Merchant01");
		card.setTypeWithQuote(Consts.TRADER);
		card.addNeedRes(Consts.WOOD, 1);
		card.addNeedRes(Consts.STONE, 1);
		card.addProvideRes(Consts.GOLD, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("金币商人");
		card.setImg("Merchant03");
		card.setTypeWithQuote(Consts.TRADERALT02);
		card.addNeedRes(Consts.GOLD, 1);
		card.addProvideRes(Consts.STONE, 3);
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
		card.setName("刘华强");
		card.setImg("HuaQiang");
		card.setTypeWithQuote(Consts.TRADER);
		String[] c3 = {"huaqiang01", "huaqiang02", "huaqiang03", "huaqiang04", "huaqiang08", "huaqiang09"};
		String[] r3 = {"huaqiang05", "huaqiang06", "huaqiang07", "huaqiang10", "huaqiang11", "huaqiang12", "huaqiang13"};
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
	
	public List<Card> getShuffledCardDeck(List<Integer> settings){
		List<Card> ans = new ArrayList<>();
		List<Card> tb = new ArrayList<>();
		int i;
		for (i=0;i<cardDeck.size();i++) {
			tb.add(cardDeck.get(i));
		}
		boolean includeBonus = false;
		boolean includeKX1 = false;
		for (i=0;i<settings.size();i++) {
			if (i == Consts.BONUSCARDINDEX) {
				if (settings.get(i) == 1) {
					includeBonus = true;
				}
			}
			if (i == Consts.KX1CARDINDEX) {
				if (settings.get(i) == 1) {
					includeKX1 = true;
				}
			}
		}
		if (includeBonus) {
			for (i=0;i<bonusCards.size();i++) {
				tb.add(bonusCards.get(i));
			}
		}
		//includeKX1 = true;
		if (includeKX1) {
			for (i=0;i<kx1Cards.size();i++) {
				tb.add(kx1Cards.get(i));
			}
		}
		List<Card> tempResOnRevealCards = new ArrayList<>();
		while (tb.size()>0) {
			int n = tb.size();
			Random rand = new Random();
			int x = rand.nextInt(n);
			Card b = tb.remove(x);
			if (b.getResOn().size() > 0 && ans.size() < 3) {
				tempResOnRevealCards.add(b);
			} else {
				ans.add(b);
				if (ans.size() == 3) {
					while (tempResOnRevealCards.size()>0) {
						Card tc = tempResOnRevealCards.remove(0);
						tb.add(tc);
					}
				}
			}
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
	public List<Card> getBonusCards() {
		return bonusCards;
	}
	public void setBonusCards(List<Card> bonusCards) {
		this.bonusCards = bonusCards;
	}
	public List<Card> getKx1Cards() {
		return kx1Cards;
	}
	public void setKx1Cards(List<Card> kx1Cards) {
		this.kx1Cards = kx1Cards;
	}
	
}
