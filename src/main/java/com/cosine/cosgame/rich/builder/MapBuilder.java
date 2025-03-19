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
		map.setName("青果巷");
		map.setNameFont("jnk");
		map.setHeight(height);
		map.setWidth(width);
		map.setNumDice(1);
		map.setJailIndex(9);
		map.setJailZone(1);
		map.setBailCost(500);
		map.setAreaColors(new ArrayList<>(Arrays.asList("","maroon","darkgreen","red","darkviolet","orange","navy")));
		map.setAreaNames(new ArrayList<>(Arrays.asList("","东下塘区","青果巷区","清秀坊区","兴仁坊区","古村巷区","正素巷区")));
		map.setCornerNames(new ArrayList<>(Arrays.asList("","jail","","")));
		map.setUtilityName("茶馆");
		map.setStationName("游览车站");
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
				p.getDetail().setDesc("现在就是一个平平无奇的停车场，未来这一格可能有别的功效？");
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
				p.getDetail().setDesc("只是路过而已~");
			} else if (i == 24) {
				p = new GoToJail(i, "入狱");
				p.setImg("goToJail");
				p.setFont("jnk", 22);
				p.setLandMsg("你将立即入狱");
				p.createDetail();
				p.getDetail().setDesc("正值六月，巡警在看到你的一瞬间，天空突然飘下了雪花，所以你被捕入狱了。");
				p.getDetail().setDesc2("难道这个游戏唯一入狱的方式就是走到这一格上？");
			} else if (i == 11) {
				p = new Estate(i, "霸王茶姬", Consts.AREA_UTILITY, 1500,0,1,new ArrayList<>(Arrays.asList(100,200)));
				p.setFont("jnk", 18);
				p.setImg("bubbleTea");
				p.createDetail();
				p.getDetail().setDesc("若该地被某位玩家拥有，到达后掷一次骰子，按所掷之数支付路费。"); 
				p.getDetail().setDesc2("作者语：咱也妹有恰饭啊，就不介绍了。");
			} else if (i == 26) {
				p = new Estate(i, "泥莲茶书院", Consts.AREA_UTILITY, 1500,0,1,new ArrayList<>(Arrays.asList(100,200)));
				p.setFont("jnk", 18);
				p.setImg("bubbleTea");
				p.createDetail();
				p.getDetail().setDesc("若该地被某位玩家拥有，到达后掷一次骰子，按所掷之数支付路费。"); 
				p.getDetail().setDesc2("作者语：咱也妹有恰饭啊，就不介绍了。");
			} else if (i == 5) {
				p = new Estate(i, "雪洞巷站", Consts.AREA_STATION, 2000,0,1,new ArrayList<>(Arrays.asList(500,1000)));
				p.setFont("jnk", 18);
				p.setImg("station");
				p.createDetail();
				p.getDetail().setImg("qingguo/xuedongxiang");
				p.getDetail().setDesc("可移动至其他游览车站。经过可以触发被动效果的地点则会触发被动效果（如钱庄），到达后无法购买且不需要支付路费。"); 
				//p.getDetail().setDesc2("雪洞巷位于半园以西，巷中建筑多为清式硬山造砖木结构。半园始建于明中，由唐荆川祖父唐贵所建，是当年“唐氏八宅”之一的贞和堂后花园。占地约为6亩，除筠星、易书、贞和、四并四堂外，还有一竹斋、分缘山房及廊轩亭榭等。园中凿有荷池，池畔置以湖石，叠石为山，亭台楼阁筑于池周。");
				p.getDetail().setDesc2("雪洞巷位于顾孝子祠以西，巷中建筑多为清式硬山造砖木结构。顾孝子祠为清代纪念孝子顾寿南而建的专祠，门厅的阴阳鱼纹具有极高的艺术价值。根据阳湖县志记载，“顾寿南，字菊友。性至孝，父病，焚香祷于天，愿灭己寿算以代；母病，割股和药以进。父母竟愈，人以为孝感格天，一时齐称顾孝子。”祠后是清贵阳知府恽鸿仪故居。");
			} else if (i == 21) {
				p = new Estate(i, "涉园巷站", Consts.AREA_STATION, 2000,0,1,new ArrayList<>(Arrays.asList(500,1000)));
				p.setFont("jnk", 18);
				p.setImg("station");
				p.createDetail();
				p.getDetail().setImg("qingguo/sheyuanxiang");
				p.getDetail().setDesc("可移动至其他游览车站。经过可以触发被动效果的地点则会触发被动效果（如钱庄），到达后无法购买且不需要支付路费。"); 
				p.getDetail().setDesc2("涉园巷位于涉园以西，涉园是著名金融家和藏书家陶湘的故居，陶氏先祖名陶人群，明朝万历年间与常州名儒刘养心联姻后迁至常州，世居青果巷。复原后的涉园中建造了藏书阁，供游客阅览。");
			}else if (i == 29) {
				p = new Estate(i, "城隍庙戏楼", 6, 4000,2000,3,new ArrayList<>(Arrays.asList(500,2250,5000,13500)));
				p.setImg("qingguo/chenghuangmiaoxilou");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setDesc("阳湖城隍庙戏楼位于青果巷，为阳湖县城隍庙附属建筑。建于清乾隆24年(1759年)，清同治、光绪年间扩建，1890年竣工。戏楼为歇山顶二层木结构，下层由麻石方柱四根支撑，内外三面皆有木雕。上屋后台为子楼三间，下层作出入口，有砖雕门框“歌舞”、“升平”题额各一方。常州城内原有3座城隍庙，阳湖县城隍庙戏楼是仅存的一座城隍庙戏楼。城隍是中国民间和道教信奉的守护地方城池之神，大多由有功于地方民众的名臣英雄充当。");
			} 
			else if (i == 8) {
				p = new Estate(i, "礼和堂", 2, 3200,2000,3,new ArrayList<>(Arrays.asList(300,1800,4500,11000)));
				p.setImg("qingguo/lihetang");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setTitle("礼和堂 · 周有光故居");
				p.getDetail().setDesc("原为唐荆川曾叔祖、明代画家唐世宁居住，在清朝末年被周有光先祖购得。周有光原名周耀平，笔名有光。青年和中年时期主要从事经济、金融工作，当过经济学教授，1955年开始专职从事语言文字研究。周有光是汉语拼音方案的主要制订者，并主持制订了《汉语拼音正词法基本规则》。");
			}
			
			map.addPlace(p);
		}
		
		map.setFateIds(new ArrayList<>(Arrays.asList(1,2,3)));
		
		return map;
	}
}
