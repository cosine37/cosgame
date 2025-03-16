package com.cosine.cosgame.rich.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Fate;
import com.cosine.cosgame.rich.Map;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.basicplaces.*;

public class MapBuilder {
	public static Map genTestMap() {
		final int height = 7;
		final int width = 10;
		final int n = (height+width-2)*2;
		
		Map map = new Map();
		map.setHeight(height);
		map.setWidth(width);
		map.setNumDice(1);
		map.setJailIndex(9);
		map.setJailZone(1);
		map.setBailCost(500);
		int i;
		for (i=0;i<n;i++){
			Place p = new Empty(i, "地点"+i);
			p.setFont("jnk", 22);
			if (i == 0) {
				p = new StartPoint(i, "钱庄");
				p.setFont("jnk", 26);
				p.setImg("qingguo/qianzhuang");
				p.setDesc("经过或停留此处领取$2000");
				p.setLandMsg("将会获得$2000");
			} else if (i == 7) {
				p = new Tax(i, "所得税", 2000);
				p.setImg("incomeTax");
				p.setDesc("支付$2000");
				p.setFont("jnk", 22);
			} else if (i == 20) {
				p = new Tax(i, "奢侈税", 1000);
				p.setImg("luxuryTax");
				p.setDesc("支付$1000");
				p.setFont("jnk", 22);
			} else if (i == 15) {
				p = new Empty(i, "免费停车场");
				p.setImg("parking");
				p.setFont("jnk", 20);
				p.setLandMsg("无事发生");
			} else if (i == 1) {
				p = new Estate(i, "琢初桥", 1, 600,500,3,new ArrayList<>(Arrays.asList(80,350,750,1700)));
			} else if (i == 2) {
				p = new Estate(i, "三锡堂码头", 1, 1000,500,3,new ArrayList<>(Arrays.asList(100,650,1350,2900)));
			} else if (i == 6) {
				p = new PersonalEvent(i, "见闻");
				p.setImg("fate");
				p.setFont("jnk", 22);
			} else if (i == 9) {
				p = new Jail(i, "监狱大门");
				p.setLandMsg("你来到了监狱大门口，但只是路过");
			} else if (i == 24) {
				p = new GoToJail(i, "入狱");
				p.setLandMsg("你将立即入狱");
			} else if (i == 11) {
				p = new Estate(i, "霸王茶姬", Consts.AREA_UTILITY, 1500,0,0,new ArrayList<>(Arrays.asList(100)));
			} else if (i == 26) {
				p = new Estate(i, "泥莲茶书院", Consts.AREA_UTILITY, 1500,0,0,new ArrayList<>(Arrays.asList(100)));
			} else if (i == 5) {
				p = new Estate(i, "雪洞巷站", Consts.AREA_STATION, 2000,0,0,new ArrayList<>(Arrays.asList(500)));
			} else if (i == 21) {
				p = new Estate(i, "涉园巷站", Consts.AREA_STATION, 2000,0,0,new ArrayList<>(Arrays.asList(500)));
			}
			map.addPlace(p);
		}
		
		map.setFateIds(new ArrayList<>(Arrays.asList(1,2,3)));
		
		return map;
	}
}
