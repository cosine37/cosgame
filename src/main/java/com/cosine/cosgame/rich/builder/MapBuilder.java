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
		map.setAreaColors(new ArrayList<>(Arrays.asList("","maroon","darkgreen","red","darkviolet","orange","navy")));
		map.setAreaNames(new ArrayList<>(Arrays.asList("","东下塘区","青果巷区","清秀坊区","兴仁坊区","古村巷区","正素巷区")));
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
				p.createDetail();
				p.getDetail().setTitle("钱庄旧址");
				p.getDetail().setImg("qingguo/qianzhuang2");
				p.getDetail().setDesc("经过或停留此处获得$2000。");
				p.getDetail().setDesc2("这个钱庄旧址最早为浙江钱庄在常州设立的分支机构，现存临街楼厅。钱庄内尚存浙江银行“钱箱实物”，是清末民初常州经济金融业发展的见证。");
			} else if (i == 7) {
				p = new Tax(i, "所得税", 600);
				p.setImg("incomeTax");
				p.setDesc("支付$600");
				p.setFont("jnk", 22);
				p.createDetail();
			} else if (i == 20) {
				p = new Tax(i, "奢侈税", 800);
				p.setImg("luxuryTax");
				p.setDesc("支付$800");
				p.setFont("jnk", 22);
				p.createDetail();
			} else if (i == 15) {
				p = new Empty(i, "免费停车场");
				p.setImg("parking");
				p.setFont("jnk", 20);
				p.setLandMsg("无事发生");
				p.createDetail();
			} else if (i == 1) {
				p = new Estate(i, "琢初桥", 1, 600,500,3,new ArrayList<>(Arrays.asList(80,350,750,1700)));
				p.setImg("qingguo/zhuochuqiao");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setDesc("有新老两座琢初桥，与新坊桥平行。以楚大夫伍参的第30世孙伍琢初的名字命名。伍琢初于1864年出生在青果巷对岸的东下塘，早年随父参与治理洪涝灾害，后被举荐为知县、知府。再奉命督办京汉铁路南段，创建汉口警察局，襄理汉阳铁厂和锰矿事务，业绩突出，又调任江苏负责江淮水灾赈务，民国后辞官回归故里。晚年资助建造该桥，琢初桥在其逝世一年后完工。");
			} else if (i == 2) {
				p = new Estate(i, "三锡堂码头", 1, 1000,500,3,new ArrayList<>(Arrays.asList(100,650,1350,2900)));
				p.setImg("qingguo/sanxitang");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setDesc("明万历年间（1573~1619）汪氏先祖汪康候，从徽州府休宁县上溪口迁徙至常州府，三传至汪继堪，于清康熙年间（1662－1722）迁至青果巷“三锡堂”居住，又经七代传至清代进士汪赞纶。“三锡堂”是汪氏堂号，其来源是“帝命三锡宪邦文武”。汪赞纶56岁中进士，在常州开办了典当行“济和典”，并在盛宣怀的劝说下资助沪宁铁路施工。1908年沪宁铁路全线贯通。");
			} else if (i == 3 || i == 6 || i == 13 || i == 18 || i == 22 || i == 28) {
				p = new PersonalEvent(i, "见闻");
				p.setImg("fate");
				p.setFont("jnk", 22);
				p.createDetail();
			} else if (i == 9) {
				p = new Jail(i, "监狱大门");
				p.setImg("jailDoor");
				p.setFont("jnk", 22);
				p.setLandMsg("你来到了监狱大门口，但只是路过");
				p.createDetail();
			} else if (i == 24) {
				p = new GoToJail(i, "入狱");
				p.setImg("goToJail");
				p.setFont("jnk", 22);
				p.setLandMsg("你将立即入狱");
				p.createDetail();
			} else if (i == 11) {
				p = new Estate(i, "霸王茶姬", Consts.AREA_UTILITY, 1500,0,0,new ArrayList<>(Arrays.asList(100)));
				p.setFont("jnk", 18);
				p.setImg("bubbleTea");
				p.createDetail();
			} else if (i == 26) {
				p = new Estate(i, "泥莲茶书院", Consts.AREA_UTILITY, 1500,0,0,new ArrayList<>(Arrays.asList(100)));
				p.setFont("jnk", 18);
				p.setImg("bubbleTea");
				p.createDetail();
			} else if (i == 5) {
				p = new Estate(i, "雪洞巷站", Consts.AREA_STATION, 2000,0,0,new ArrayList<>(Arrays.asList(500)));
				p.setFont("jnk", 18);
				p.setImg("station");
				p.createDetail();
			} else if (i == 21) {
				p = new Estate(i, "涉园巷站", Consts.AREA_STATION, 2000,0,0,new ArrayList<>(Arrays.asList(500)));
				p.setFont("jnk", 18);
				p.setImg("station");
				p.createDetail();
			}else if (i == 29) {
				p = new Estate(i, "城隍庙戏楼", 6, 4000,200,3,new ArrayList<>(Arrays.asList(500,2250,5000,13500)));
				p.setFont("jnk", 18);
				p.createDetail();
			} 
			map.addPlace(p);
		}
		
		map.setFateIds(new ArrayList<>(Arrays.asList(1,2,3)));
		
		return map;
	}
}
