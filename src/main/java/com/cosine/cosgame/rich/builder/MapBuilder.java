package com.cosine.cosgame.rich.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Fate;
import com.cosine.cosgame.rich.Map;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Settings;
import com.cosine.cosgame.rich.basicplaces.*;
import com.cosine.cosgame.rich.gta.places.*;

public class MapBuilder {
	public static Map genMap(Settings settings) {
		int mapId = settings.getMapId();
		if (mapId == 0) {
			return genQingguo(settings);
		} if (mapId == 1) {
			return genGTA(settings);
		} if (mapId == 2) {
			return genShanghai(settings);
		} 
		
		else {
			return new Map();
		}
	}
	
	public static Map genShanghai(Settings settings) {
		final int height = 11;
		final int width = 11;
		final int n = (height+width-2)*2;
		int i;
		Map map = new Map();
		map.setName("上海滩");
		map.setNameFont("jnk");
		map.setHeight(height);
		map.setWidth(width);
		map.setNumDice(1);
		map.setJailIndex(10);
		map.setJailZone(1);
		map.setBailCost(500);
		map.setMapZoom("0.95");
		map.setCenterZoom("1.05");
		map.setCenterHeight("602px");
		map.setCenterWidth("1033px");
		map.setLogHeight("880px");
		//map.setAreaColors(new ArrayList<>(Arrays.asList("","darkslategrey","orangered","DarkCyan","darkviolet","darkgreen","maroon","olive","navy")));
		map.setAreaColors(new ArrayList<>(Arrays.asList("","olive","orangered","darkslategrey","darkviolet","darkgreen","DarkCyan","maroon","navy")));
		map.setAreaNames(new ArrayList<>(Arrays.asList("","黄浦区","徐汇区","长宁区","普陀区","静安区","虹口区","杨浦区","浦东新区")));
		map.setBgms(new ArrayList<>(Arrays.asList("gta1","gta2","gta3","gta4","gta5","gta6","gta7","gta8","gta9","gta10","gta11")));
		map.setCornerNames(new ArrayList<>(Arrays.asList("","jail","","")));
		map.setUtilityName("餐饮");
		map.setStationName("地铁站");
		if (settings.getUseGTA() == 1) {
			map.setCornerNames(new ArrayList<>(Arrays.asList("","jail","","ward")));
			map.setHospitalIndex(30);
			map.setWardZone(3);
		}
		
		for (i=0;i<n;i++) {
			Place p = new Empty(i, "地点"+i);
			p.setFont("jnk", 22);
			
			if (i == 0) {
				p = new StartPoint(i, "外  滩");
				p.setFont("jnk", 24);
				p.setImg("shanghai/waitan2");
				p.setDesc("经过或停留此处领取$2000");
				p.setLandMsg("将会获得$2000");
				p.createDetail();
				p.getDetail().setTitle("外滩");
				p.getDetail().setImg("shanghai/waitan");
				p.getDetail().setDesc("经过或停留此处获得$2000。");
				p.getDetail().setDesc2("外滩位于上海市黄浦区的黄浦江畔，即外黄浦滩，为中国历史文化街区。全长1.5千米，南起延安东路，北至苏州河上的外白渡桥，东面即黄浦江，西面是旧上海金融、外贸机构的集中地。上海辟为商埠以后，外国的银行、商行、总会、报社开始在此云集，外滩成为全国乃至远东的金融中心。");
			} else if (i == 2 || i == 7 || i == 17 || i == 22 || i == 33 || i == 38 || i == 13 || i == 27) {
				p = new PersonalEvent(i, "见闻");
				p.setImg("fate");
				p.setFont("jnk", 24);
				p.createDetail();
				if (settings.getUseGTA() == 1) {
					p.getDetail().setDesc2("冷知识：点击确定之后见闻才会生效。未来有些卡牌可以改变当前的见闻。");
				}
			} else if (i == 12) {
				p = new Estate(i, "小南国", Consts.AREA_UTILITY, 1500,0,0,new ArrayList<>(Arrays.asList(80,200)));
				p.setFont("jnk", 18);
				p.setImg("eatery");
				p.createDetail();
				p.getDetail().setImg("shanghai/xiaonanguo");
				p.getDetail().setDesc("若该地被某位玩家拥有，到达后掷一次骰子，按所掷之数支付路费。"); 
				p.getDetail().setDesc2("作者语：咱也妹有恰饭啊，就不介绍了。");
			} else if (i == 28) {
				p = new Estate(i, "杏花楼", Consts.AREA_UTILITY, 1500,0,0,new ArrayList<>(Arrays.asList(80,200)));
				p.setFont("jnk", 18);
				p.setImg("eatery");
				p.createDetail();
				p.getDetail().setImg("shanghai/xinghualou");
				p.getDetail().setDesc("若该地被某位玩家拥有，到达后掷一次骰子，按所掷之数支付路费。"); 
				p.getDetail().setDesc2("作者语：咱也妹有恰饭啊，就不介绍了。");
			}
			
			else if (i == 5) {
				p = new Estate(i, "大木桥路", Consts.AREA_STATION, 2000,0,0,new ArrayList<>(Arrays.asList(250,500,1000,2000)));
				p.setFont("heiti", 18);
				p.setImg("shanghai/subway");
				p.createDetail();
				p.getDetail().setTitle("大木桥路地铁站");
				p.getDetail().setImg("shanghai/damuqiaolu");
				p.getDetail().setDesc("欢迎乘坐上海轨道交通4号线，本线为环线~"); 
				p.getDetail().setDesc2("可移动至其他地铁站。经过可以触发被动效果的地点则会触发被动效果（如人民广场），到达后无法购买且不需要支付路费。");
			} else if (i == 15) {
				p = new Estate(i, "中山公园", Consts.AREA_STATION, 2000,0,0,new ArrayList<>(Arrays.asList(250,500,1000,2000)));
				p.setFont("heiti", 18);
				p.setImg("shanghai/subway");
				p.createDetail();
				p.getDetail().setTitle("中山公园地铁站");
				p.getDetail().setImg("shanghai/zhongshangongyuan");
				p.getDetail().setDesc("欢迎乘坐上海轨道交通4号线，本线为环线~"); 
				p.getDetail().setDesc2("可移动至其他地铁站。经过可以触发被动效果的地点则会触发被动效果（如人民广场），到达后无法购买且不需要支付路费。");
			} else if (i == 25) {
				p = new Estate(i, "上海火车站", Consts.AREA_STATION, 2000,0,0,new ArrayList<>(Arrays.asList(250,500,1000,2000)));
				p.setFont("heiti", 18);
				p.setImg("shanghai/subway");
				p.createDetail();
				p.getDetail().setTitle("上海火车站地铁站");
				p.getDetail().setImg("shanghai/shanghaizhan");
				p.getDetail().setDesc("欢迎乘坐上海轨道交通4号线，本线为环线~"); 
				p.getDetail().setDesc2("可移动至其他地铁站。经过可以触发被动效果的地点则会触发被动效果（如人民广场），到达后无法购买且不需要支付路费。");
			} else if (i == 35) {
				p = new Estate(i, "世纪大道", Consts.AREA_STATION, 2000,0,0,new ArrayList<>(Arrays.asList(250,500,1000,2000)));
				p.setFont("heiti", 18);
				p.setImg("shanghai/subway");
				p.createDetail();
				p.getDetail().setTitle("世纪大道地铁站");
				p.getDetail().setImg("shanghai/shijidadao");
				p.getDetail().setDesc("欢迎乘坐上海轨道交通4号线，本线为环线~"); 
				p.getDetail().setDesc2("可移动至其他地铁站。经过可以触发被动效果的地点则会触发被动效果（如人民广场），到达后无法购买且不需要支付路费。");
			} else if (i == 1) {
				p = new Estate(i, "人民广场", 1, 2600,1500,5,new ArrayList<>(Arrays.asList(220,1100,3300,8000,9750,11500)));
				p.setImg("shanghai/renmingguangchang");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setDesc("人民广场在上海地区开埠前是一片水田。清道光二十八年（1848年），英租界扩张至此，修护城河，筑泥城，称为“泥城浜”。清同治二年（1863年），英领事馆在此建有“上海跑马厅”，要求“华人不得入内”。1954年5月31日，跑马总会大楼改为上海图书馆。1994年10月1日，上海人民广场综合改造工程竣工。广场中央有320平方米的圆形喷水池，广场北侧是上海市人民政府所在地，西北侧为上海大剧院，东北侧为上海城市规划展示馆，南侧为上海博物馆。");
			}
			
			map.addPlace(p);
		}
		
		map.setFateIds(new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,201,202,203,204)));
		if (settings.getUseGTA() == 1) {
			/*
			map.setFateIds(new ArrayList<>(Arrays.asList(1,4,5,6,7,8,9,10,11,12,13,15,16,17,18,19,20,21,22,23,24,25,26,202,203,204,
					10001,10002,10003,10004,10005,10006,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10201,10202,10203,10204,10205)));
			*/	
			map.setFateIds(new ArrayList<>(Arrays.asList(10015,10017,10018,10019)));
			map.sortCardRarity(new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,41,42,43,44,45,46,47,48,49,50,
					20001,20002,20003,20004,20005,20006,20007,20008,20009,20010)));
			map.setVehicleIds(new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27)));
		}
		return map;
	}
	
	public static Map genGTA(Settings settings) {
		final int height = 11;
		final int width = 11;
		final int n = (height+width-2)*2;
		int i;
		Map map = new Map();
		map.setName("GTA");
		map.setNameFont("tyh");
		map.setHeight(height);
		map.setWidth(width);
		map.setNumDice(1);
		map.setJailIndex(10);
		map.setJailZone(1);
		map.setBailCost(500);
		map.setMapZoom("0.95");
		map.setCenterZoom("1.05");
		map.setCenterHeight("602px");
		map.setCenterWidth("1033px");
		map.setLogHeight("880px");
		map.setAreaColors(new ArrayList<>(Arrays.asList("","darkslategrey","orangered","DarkCyan","darkviolet","darkgreen","maroon","olive","navy")));
		map.setAreaNames(new ArrayList<>(Arrays.asList("","Etobicoke·怡陶碧谷","Mississauga·密西沙加","Brampton·宾顿","Vaughan·旺市","Richmond HL·列治文山","Markham·万锦","Scarborough·士嘉堡","Toronto·多伦多")));
		map.setBgms(new ArrayList<>(Arrays.asList("gta1","gta2","gta3","gta4","gta5","gta6","gta7","gta8","gta9","gta10","gta11")));
		map.setCornerNames(new ArrayList<>(Arrays.asList("","jail","","")));
		map.setUtilityName("咖啡店");
		map.setStationName("GO车站");
		if (settings.getUseGTA() == 1) {
			map.setCornerNames(new ArrayList<>(Arrays.asList("","jail","","ward")));
			map.setHospitalIndex(30);
			map.setWardZone(3);
		}
		for (i=0;i<n;i++) {
			Place p = new Empty(i, "地点"+i);
			p.setFont("tyh", 22);
			if (i == 0) {
				p = new StartPoint(i, "金融区");
				p.setFont("tyh", 26);
				p.setImg("gta/DT2");
				p.setDesc("经过或停留此处领取$2000");
				p.setLandMsg("将会获得$2000");
				p.createDetail();
				p.getDetail().setTitle("金融区");
				p.getDetail().setImg("gta/DT");
				p.getDetail().setDesc("经过或停留此处获得$2000。");
				p.getDetail().setDesc2("金融区是多伦多建筑最密集的地区。金融区的发展开始于19世纪末期，20世纪中叶逐渐成了五大行的中心。其中，加拿大帝国商业银行（Canadian Imperial Bank of Commerce, CIBC）和多伦多道明银行（Toronto-Dominion Bank, TD）在此创立。另外三家银行也在20世纪陆陆续续将总部迁移到此地。");
			} else if (i == 2 || i == 7 || i == 17 || i == 22 || i == 33 || i == 38 || i == 12 || i == 28) {
				p = new PersonalEvent(i, "命运");
				p.setImg("fate");
				p.setFont("tyh", 22);
				p.createDetail();
				if (settings.getUseGTA() == 1) {
					p.getDetail().setDesc2("冷知识：点击确定之后命运才会生效。未来有些卡牌可以改变当前的命运。");
				}
			} else if (i == 10) {
				p = new Jail(i, "监狱大门");
				p.setImg("jailDoor");
				p.setFont("tyh", 22);
				p.setLandMsg("你来到了监狱大门口，但只是路过");
				p.createDetail();
				p.getDetail().setDesc("只是路过而已~");
			} else if (i == 30) {
				if (settings.getUseGTA() == 1) {
					p = new Hospital(i, "医院");
					p.setImg("hospital");
					p.setFont("tyh", 22);
					p.createDetail();
					p.getDetail().setDesc("你可以在此回复生命值。");
					p.getDetail().setDesc2("花费$500回复1点生命值。若你的生命值为1，你可以花费$1000回复2点生命值。");
				} else {
					p = new GoToJail(i, "入狱");
					p.setImg("goToJail");
					p.setFont("tyh", 22);
					p.setLandMsg("你将立即入狱");
					p.createDetail();
					p.getDetail().setDesc("正值六月，巡警在看到你的一瞬间，天空突然飘下了雪花，所以你被捕入狱了。入狱属于移出地图，所以你不会领取经过钱庄的$2000。");
					p.getDetail().setDesc2("难道这个游戏唯一入狱的方式就是走到这一格上？");
				}
			} else if (i==20) {
				if (settings.getUseGTA() == 1) {
					p = new CardGainer(i, "Wonderland");
					p.setImg("gta/Wonderland2");
					p.setFont("tyh", 20);
					p.setDesc("经过获得1张牌；停留获得2张牌并减少1点通缉值");
					p.setLandMsg("将会减少1点通缉值并获得2张牌（如果你有手牌空间）");
					p.createDetail();
					p.getDetail().setDesc("经过此处获得1张牌。停留此处获得2张牌并减少1点通缉值。");
					p.getDetail().setTitle("Canada's Wonderland·加拿大奇幻乐园");
					p.getDetail().setImg("gta/Wonderland");
				} else {
					p = new Empty(i, "免费停车场");
					p.setImg("parking");
					p.setFont("tyh", 20);
					p.setLandMsg("无事发生");
					p.createDetail();
					p.getDetail().setDesc("现在就是一个平平无奇的停车场，未来这一格可能有别的功效？");
				}
			} else if (i == 32) {
				p = new Estate(i, "Tim Hortons", Consts.AREA_UTILITY, 1500,0,0,new ArrayList<>(Arrays.asList(80,200)));
				p.setFont("tyh", 18);
				p.setImg("gta/TimHortons");
				p.createDetail();
				p.getDetail().setDesc("若该地被某位玩家拥有，到达后掷一次骰子，按所掷之数支付路费。"); 
				p.getDetail().setDesc2("作者语：咱也妹有恰饭啊，就不介绍了。");
			} else if (i == 3) {
				p = new Estate(i, "Second Cup", Consts.AREA_UTILITY, 1500,0,0,new ArrayList<>(Arrays.asList(80,200)));
				p.setFont("tyh", 18);
				p.setImg("gta/SecondCup");
				p.createDetail();
				p.getDetail().setDesc("若该地被某位玩家拥有，到达后掷一次骰子，按所掷之数支付路费。"); 
				p.getDetail().setDesc2("作者语：咱也妹有恰饭啊，就不介绍了。");
			} else if (i == 5) {
				p = new Estate(i, "Mimico GO", Consts.AREA_STATION, 2000,0,0,new ArrayList<>(Arrays.asList(250,500,1000,2000)));
				p.setFont("tyh", 18);
				p.setImg("gta/GO");
				p.createDetail();
				p.getDetail().setTitle("Mimico GO车站");
				p.getDetail().setImg("gta/GOTrain");
				p.getDetail().setDesc("可移动至其他GO车站。经过可以触发被动效果的地点则会触发被动效果（如金融区），到达后无法购买且不需要支付路费。"); 
				p.getDetail().setDesc2("");
			} else if (i == 15) {
				p = new Estate(i, "Malton GO", Consts.AREA_STATION, 2000,0,0,new ArrayList<>(Arrays.asList(250,500,1000,2000)));
				p.setFont("tyh", 18);
				p.setImg("gta/GO");
				p.createDetail();
				p.getDetail().setTitle("Malton GO车站");
				p.getDetail().setImg("gta/GOTrain");
				p.getDetail().setDesc("可移动至其他GO车站。经过可以触发被动效果的地点则会触发被动效果（如金融区），到达后无法购买且不需要支付路费。"); 
				p.getDetail().setDesc2("");
			} else if (i == 25) {
				p = new Estate(i, "Milliken GO", Consts.AREA_STATION, 2000,0,0,new ArrayList<>(Arrays.asList(250,500,1000,2000)));
				p.setFont("tyh", 18);
				p.setImg("gta/GO");
				p.createDetail();
				p.getDetail().setTitle("Milliken GO车站");
				p.getDetail().setImg("gta/GOTrain");
				p.getDetail().setDesc("可移动至其他GO车站。经过可以触发被动效果的地点则会触发被动效果（如金融区），到达后无法购买且不需要支付路费。"); 
				p.getDetail().setDesc2("");
			} else if (i == 35) {
				p = new Estate(i, "Danforth GO", Consts.AREA_STATION, 2000,0,0,new ArrayList<>(Arrays.asList(250,500,1000,2000)));
				p.setFont("tyh", 18);
				p.setImg("gta/GO");
				p.createDetail();
				p.getDetail().setTitle("Danforth GO车站");
				p.getDetail().setImg("gta/GOTrain");
				p.getDetail().setDesc("可移动至其他GO车站。经过可以触发被动效果的地点则会触发被动效果（如金融区），到达后无法购买且不需要支付路费。"); 
				p.getDetail().setDesc2("");
			} else if (i == 1) {
				p = new Estate(i, "Humber Bay", 1, 600,300,5,new ArrayList<>(Arrays.asList(20,100,300,900,1600,2500)));
				p.setImg("gta/HumberBay");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Humber Bay·汉伯湾");
				p.getDetail().setDesc("1809年前此地为未开发地段。1882年一个旅馆财团开发了汉伯河（Humber River）至多伦多的轮渡服务，该服务于1886年停用。1890年多伦多港湾委员会（THC）在东岸填河造陆，并修建了吊桥，从此不需要轮渡即可过河。1970年代汉伯湾公园（Humber Bay Park）在此建造。");
			} else if (i == 4) {
				p = new Estate(i, "Islington", 1, 800,500,5,new ArrayList<>(Arrays.asList(40,200,600,1800,3200,4500)));
				p.setImg("gta/Islington");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Islington·伊斯灵顿");
				p.getDetail().setDesc("该社区发源于1832年建造的蒙哥马利客栈（Montgomery's Inn）。1850年设乡，并用该客栈作为议会厅。当时镇名为美美高（Mimico）。1855年第一条从西部通往多伦多的铁路建设完毕，将美美高镇分成两部分。1860年，北部脱离美美高，成立伊斯灵顿（Islington）乡镇，因当时的蒙哥马利客栈老板的妻子出生于英国的伊斯灵顿而得名。1954年并入怡陶碧谷（Etobicoke），1998年并入多伦多。");
			} else if (i == 6) {
				p = new Estate(i, "Dixie", 2, 1600,1000,5,new ArrayList<>(Arrays.asList(120,600,1800,5000,7000,9000)));
				p.setImg("gta/Dixie");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Dixie·迪克西");
				p.getDetail().setDesc("该社区是密西沙加（Mississauga）最古老的社区之一，原名Sydenham。1865年以Beaumont Wilson Bowen Dixie命名。其为威尔士移民，在此资助建立了新教教堂。如今该区域大部分都是商业用地，密西沙加中国城也坐落于此，只有一小片是居民区。");
			} else if (i == 8) {
				p = new Estate(i, "Square One", 2, 3200,2000,5,new ArrayList<>(Arrays.asList(280,1500,4500,10000,12000,14000)));
				p.setImg("gta/SquareOne");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Square One·一号广场");
				p.getDetail().setDesc("该地原为农地，后落入商人麦拉夫连（Bruce McLaughlin）手中。1968年时，多伦多西侧的几个小镇合并成密西沙加（Mississauga）镇，麦拉夫连随即准备在该地大兴土木。1969年原密西沙加镇政府被火灾焚毁，于是麦拉夫连提供在同一片地皮上兴建新的镇政府，并在新镇政府边上兴建大型商场。一号广场于1969年动工，1973年10月3日对外开放，当时为全国最大的商场。如今广场附近拥有密西沙加艺术中心和密西沙加中央图书馆等设施，同时亦有一座巴士总站。");
			} else if (i == 9) {
				p = new Estate(i, "Erin Mills", 2, 2200,1500,5,new ArrayList<>(Arrays.asList(180,900,2500,7000,8750,10500)));
				p.setImg("gta/ErinMills");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Erin Mills·艾琳米尔");
				p.getDetail().setDesc("该区域为开发商CF（Cadillac Fairview）于1970年代起开发的新城。名称来源于南侧的Erindale，其中Erin为爱尔兰语中爱尔兰人之意，因该村的首位牧师，爱尔兰人James Magrath的家乡而得名。河流Credit River从此穿过，Credit是个法语名，意为信用。名称起源于18世纪时法国的皮草贸易商先为当地人提供物品，当地人第二年春天再提供皮草的诚信交易。");
			} else if (i == 11) {
				p = new Estate(i, "Churchville", 3, 1000,500,5,new ArrayList<>(Arrays.asList(60,300,900,2700,4000,5500)));
				p.setImg("gta/Churchville");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Churchville·丘奇维尔");
				p.getDetail().setDesc("丘奇维尔（Churchville）名称来自Amaziah Church于1815年在此建立的面粉厂，在面粉厂周围形成了小的村路。后此处陆续建造了屠宰场、皮革厂、学校、教堂等。1974年并入宾顿市（Brampton）。2022年，一场冰雹引发的洪水摧毁了此处50多幢房屋。");
			} else if (i == 13) {
				p = new Estate(i, "Heart Lake", 3, 1000,500,5,new ArrayList<>(Arrays.asList(60,300,900,2700,4000,5500)));
				p.setImg("gta/HeartLake");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Heart Lake·心湖");
				p.getDetail().setDesc("心湖（Heart Lake）是一个锅穴湖，一般认为是由埋藏在冰水沉积层内砂砾中的死冰块融化后引起塌陷而造成的。因湖形似爱心而得名，周边区域也以该湖为名。每年当地的小学生都会在湖周围植树。");
			} else if (i == 14) {
				p = new Estate(i, "Bramalea", 3, 1200,500,5,new ArrayList<>(Arrays.asList(80,400,1000,3000,4500,6000)));
				p.setImg("gta/Bramalea");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Bramalea·布兰马里");
				p.getDetail().setDesc("布兰马里（Bramalea）是宾顿市（Brampton）的新城，由开发商Bramalea Consolidated Developments建造，也是该开发商建造的第一个卫星城。");
			} else if (i == 16) {
				p = new Estate(i, "Woodbridge", 4, 1400,1000,5,new ArrayList<>(Arrays.asList(100,500,1500,4500,6250,7500)));
				p.setImg("gta/Woodbridge");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Woodbridge·木桥");
				p.getDetail().setDesc("该社区坐落旺市（Vaughan）的西南，在20世纪80年代开发，西部拥有大量的森林绿地以及高尔夫俱乐部，而东南角是400高速和407高速的交汇点，曾是地产最富裕的地区。近年来，因该区域枪击案和爆炸案频发，导致该区域的房价不复往日之荣光。");
			} else if (i == 18) {
				p = new Estate(i, "VMC", 4, 2000,1500,5,new ArrayList<>(Arrays.asList(160,800,2200,6000,8000,10000)));
				p.setImg("gta/VMC");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("VMC·工人新村");
				p.getDetail().setDesc("VMC，全称Vaughan Metropolitan Center，位于旺市（Vaughan）南侧，是旺市的新兴社区，也是旺市的研发中心和交通枢纽。因房价实惠，该区域超过一半的居民是中青年移民，大部分是尼亚加拉大学（Niagara University）和约克大学（York University）的在读学生以及教职工，还有从事包括商业、服务和制造业等各种行业的工人，所以此处也拥有工人新村的外号。");
			} else if (i == 19) {
				p = new Estate(i, "Concord", 4, 2400,1500,5,new ArrayList<>(Arrays.asList(200,1000,3000,7500,9250,11000)));
				p.setImg("gta/Concord");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Concord·康科德");
				p.getDetail().setDesc("该区域是旺市（Vaughan）的工业区，由1854年建立的邮政小镇发展而来。如今此处拥有加拿大国家铁路的编组站MacMillan Yard以及好多大的工厂、仓库、车行和汽车修理厂。2004年奥莱Vaughan Mills开张营业。目前该地仍处于大发展时期。");
			} else if (i == 21) {
				p = new Estate(i, "Rouge Woods", 5, 1400,1000,5,new ArrayList<>(Arrays.asList(100,500,1500,4500,6250,7500)));
				p.setImg("gta/RougeWoods");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Rouge Woods·红河谷");
				p.getDetail().setDesc("该社区因流经此处的红河（Rouge River）而得名，位于列治文山（Richmond Hill）中心。上世纪90年代起进行大规模开发，如今仍处于开发状态。该区域内有数量众多的公园、足球场、网球场、棒球场以及室内滑冰场等体育场馆。");
			} else if (i == 23) {
				p = new Estate(i, "Bayview Hill", 5, 3000,2000,5,new ArrayList<>(Arrays.asList(260,1300,3900,9000,11000,12750)));
				p.setImg("gta/BayviewHill");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Bayview HL·富豪山庄");
				p.getDetail().setDesc("Bayview Hill社区建于上世纪90年代初，为豪宅区域，居民大多为移民且接受过高等教育，且该区域有安省高中常年排名前十的名校Bayview Secondary School，所以该区域拥有富豪山庄的雅称。");
			} else if (i == 24) {
				p = new Estate(i, "Times Square", 5, 2600,1500,5,new ArrayList<>(Arrays.asList(220,1100,3300,8000,9750,11500)));
				p.setImg("gta/TimesSquare");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Times SQR·时代广场");
				p.getDetail().setDesc("列治文山（Richmond Hill）的时代广场（Times Square）是华人社区的美食广场和综合购物中心。90年代初，该地区迎来了大量香港投资移民，所以这一带就有了“时代广场”, “黄金商场”等有名的中国人购物、饮食中心。");
			} else if (i == 26) {
				p = new Estate(i, "Pacific Mall", 6, 2400,1500,5,new ArrayList<>(Arrays.asList(200,1000,3000,7500,9250,11000)));
				p.setImg("gta/PacificMall");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Pacific Mall·太古");
				p.getDetail().setDesc("");
			} else if (i == 27) {
				p = new Estate(i, "Unionville", 6, 2200,1500,5,new ArrayList<>(Arrays.asList(180,900,2500,7000,8750,10500)));
				p.setImg("gta/Unionville");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Unionville·渔人村");
				p.getDetail().setDesc("");
			} else if (i == 29) {
				p = new Estate(i, "Vinegar Hill", 6, 1400,1000,5,new ArrayList<>(Arrays.asList(100,500,1500,4500,6250,7500)));
				p.setImg("gta/VinegarHill");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Vinegar HL·维内加山");
				p.getDetail().setDesc("");
			} else if (i == 31) {
				p = new Estate(i, "Midland CTR", 7, 2800,1500,5,new ArrayList<>(Arrays.asList(240,1200,3600,8500,10250,12000)));
				p.setImg("gta/MidlandCenter");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Midland CTR·金钟城");
				p.getDetail().setDesc("");
			}
			else if (i == 34) {
				p = new Estate(i, "Golden Mile", 7, 1800,1000,5,new ArrayList<>(Arrays.asList(140,700,2000,5500,7500,9500)));
				p.setImg("gta/GoldenMile");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Golden Mile·黄金里");
				p.getDetail().setDesc("");
			}
			else if (i == 36) {
				p = new Estate(i, "Casa Loma", 8, 2600,1500,5,new ArrayList<>(Arrays.asList(220,1100,3300,8000,9750,11500)));
				p.setImg("gta/CasaLoma");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Casa Loma·卡萨罗马");
				p.getDetail().setDesc("");
			}
			else if (i == 37) {
				p = new Estate(i, "Eaton CTR", 8, 3500,2000,5,new ArrayList<>(Arrays.asList(350,1750,5000,11000,13000,15000)));
				p.setImg("gta/EatonCenter");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("Eaton CTR·伊顿中心");
				p.getDetail().setDesc("");
			}
			else if (i == 39) {
				p = new Estate(i, "CN Tower", 8, 4000,2000,5,new ArrayList<>(Arrays.asList(500,2000,6000,14000,17000,20000)));
				p.setImg("gta/CNTower");
				p.setFont("tyh", 16);
				p.createDetail();
				p.getDetail().setTitle("CN Tower·加国电视塔");
				p.getDetail().setDesc("");
			}
			map.addPlace(p);
		}
		map.setFateIds(new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,201,202,203,204)));
		if (settings.getUseGTA() == 1) {
			
			map.setFateIds(new ArrayList<>(Arrays.asList(1,4,5,6,8,9,10,11,12,13,15,16,17,18,19,20,21,22,23,24,25,26,202,203,204,
					10001,10002,10003,10004,10005,10006,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10201,10202,10203,10204,10205)));
					
			//map.setFateIds(new ArrayList<>(Arrays.asList(10013,10014,10015,10016)));
			map.sortCardRarity(new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,41,42,43,44,45,46,47,48,49,50,
					20001,20002,20003,20004,20005,20006,20007,20008,20009,20010)));
			map.setVehicleIds(new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26)));
			
		}
		
		return map;
	}
	
	public static Map genQingguo(Settings settings) {
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
		map.setMapZoom("1.1");
		map.setCenterZoom("1");
		map.setCenterHeight("602px");
		map.setCenterWidth("964px");
		map.setLogHeight("480px");
		map.setAreaColors(new ArrayList<>(Arrays.asList("","darkslategrey","darkgreen","darkorange","darkviolet","maroon","navy")));
		map.setAreaNames(new ArrayList<>(Arrays.asList("","东下塘区","青果巷区","清秀坊区","兴仁坊区","古村巷区","正素巷区")));
		map.setBgms(new ArrayList<>(Arrays.asList("qingguo1","qingguo2","qingguo3","qingguo4","qingguo5","qingguo6","qingguo7")));
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
				p = new Estate(i, "琢初桥", 1, 1200,500,3,new ArrayList<>(Arrays.asList(80,450,1050,3200)));
				p.setImg("qingguo/zhuochuqiao");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setDesc("有新老两座琢初桥，与新坊桥平行。以楚大夫伍参的第30世孙伍琢初的名字命名。伍琢初于1864年出生在青果巷对岸的东下塘，早年随父参与治理洪涝灾害，后被举荐为知县、知府。再奉命督办京汉铁路南段，创建汉口警察局，襄理汉阳铁厂和锰矿事务，业绩突出，又调任江苏负责江淮水灾赈务，民国后辞官回归故里。晚年资助建造该桥，琢初桥在其逝世一年后完工。");
			} else if (i == 2) {
				p = new Estate(i, "三锡堂码头", 1, 800,500,3,new ArrayList<>(Arrays.asList(45,280,850,2500)));
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
				p.getDetail().setDesc("正值六月，巡警在看到你的一瞬间，天空突然飘下了雪花，所以你被捕入狱了。入狱属于移出地图，所以你不会领取经过钱庄的$2000。");
				p.getDetail().setDesc2("难道这个游戏唯一入狱的方式就是走到这一格上？");
			} else if (i == 11) {
				p = new Estate(i, "霸王茶姬", Consts.AREA_UTILITY, 1500,0,0,new ArrayList<>(Arrays.asList(100,200)));
				p.setFont("jnk", 18);
				p.setImg("bubbleTea");
				p.createDetail();
				p.getDetail().setDesc("若该地被某位玩家拥有，到达后掷一次骰子，按所掷之数支付路费。"); 
				p.getDetail().setDesc2("作者语：咱也妹有恰饭啊，就不介绍了。");
			} else if (i == 26) {
				p = new Estate(i, "泥莲茶书院", Consts.AREA_UTILITY, 1500,0,0,new ArrayList<>(Arrays.asList(100,200)));
				p.setFont("jnk", 18);
				p.setImg("bubbleTea");
				p.createDetail();
				p.getDetail().setDesc("若该地被某位玩家拥有，到达后掷一次骰子，按所掷之数支付路费。"); 
				p.getDetail().setDesc2("作者语：咱也妹有恰饭啊，就不介绍了。");
			} else if (i == 5) {
				p = new Estate(i, "雪洞巷站", Consts.AREA_STATION, 2000,0,0,new ArrayList<>(Arrays.asList(500,1000)));
				p.setFont("jnk", 18);
				p.setImg("station");
				p.createDetail();
				p.getDetail().setImg("qingguo/xuedongxiang");
				p.getDetail().setDesc("可移动至其他游览车站。经过可以触发被动效果的地点则会触发被动效果（如钱庄），到达后无法购买且不需要支付路费。"); 
				//p.getDetail().setDesc2("雪洞巷位于半园以西，巷中建筑多为清式硬山造砖木结构。半园始建于明中，由唐荆川祖父唐贵所建，是当年“唐氏八宅”之一的贞和堂后花园。占地约为6亩，除筠星、易书、贞和、四并四堂外，还有一竹斋、分缘山房及廊轩亭榭等。园中凿有荷池，池畔置以湖石，叠石为山，亭台楼阁筑于池周。");
				p.getDetail().setDesc2("雪洞巷位于顾孝子祠以西，巷中建筑多为清式硬山造砖木结构。顾孝子祠为清代纪念孝子顾寿南而建的专祠，门厅的阴阳鱼纹具有极高的艺术价值。根据阳湖县志记载，“顾寿南，字菊友。性至孝，父病，焚香祷于天，愿灭己寿算以代；母病，割股和药以进。父母竟愈，人以为孝感格天，一时齐称顾孝子。”祠后是清贵阳知府恽鸿仪故居。");
			} else if (i == 21) {
				p = new Estate(i, "涉园巷站", Consts.AREA_STATION, 2000,0,0,new ArrayList<>(Arrays.asList(500,1000)));
				p.setFont("jnk", 18);
				p.setImg("station");
				p.createDetail();
				p.getDetail().setImg("qingguo/sheyuanxiang");
				p.getDetail().setDesc("可移动至其他游览车站。经过可以触发被动效果的地点则会触发被动效果（如钱庄），到达后无法购买且不需要支付路费。"); 
				p.getDetail().setDesc2("涉园巷位于涉园以西，涉园是著名金融家和藏书家陶湘的故居，陶氏先祖名陶人群，明朝万历年间与常州名儒刘养心联姻后迁至常州，世居青果巷。复原后的涉园中建造了藏书阁，供游客阅览。");
			}else if (i == 29) {
				p = new Estate(i, "城隍庙戏楼", 6, 3000,2000,3,new ArrayList<>(Arrays.asList(275,1900,5100,9500)));
				p.setImg("qingguo/chenghuangmiaoxilou");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setDesc("阳湖城隍庙戏楼位于青果巷，为阳湖县城隍庙附属建筑。建于清乾隆24年(1759年)，清同治、光绪年间扩建，1890年竣工。戏楼为歇山顶二层木结构，下层由麻石方柱四根支撑，内外三面皆有木雕。上屋后台为子楼三间，下层作出入口，有砖雕门框“歌舞”、“升平”题额各一方。常州城内原有3座城隍庙，阳湖县城隍庙戏楼是仅存的一座城隍庙戏楼。城隍是中国民间和道教信奉的守护地方城池之神，大多由有功于地方民众的名臣英雄充当。");
			} else if (i == 8) {
				p = new Estate(i, "礼和堂", 2, 3200,2000,3,new ArrayList<>(Arrays.asList(300,2100,5500,10500)));
				p.setImg("qingguo/lihetang");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setTitle("礼和堂 · 周有光故居");
				p.getDetail().setDesc("唐氏八宅之一，原为唐荆川曾叔祖、明代画家唐世宁居住，在清朝末年被周有光先祖购得。周有光原名周耀平，笔名有光。青年和中年时期主要从事经济、金融工作，当过经济学教授，1955年开始专职从事语言文字研究。周有光是汉语拼音方案的主要制订者，并主持制订了《汉语拼音正词法基本规则》。");
			} else if (i == 4) {
				p = new Estate(i, "梳篦博物馆", 2, 2600,1500,3,new ArrayList<>(Arrays.asList(225,1350,3875,7900)));
				p.setImg("qingguo/shubibowuguan");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setTitle("真老卜恒顺梳篦博物馆");
				p.getDetail().setDesc("常州梳篦制作技艺形成于东晋时期，迄今已有1500多年的历史，有宫梳名篦之称。齿稀的称“梳”，齿密的称“篦”，梳理头发用梳，清除发垢用篦。用骨、木、竹、角、象牙等制。梳篦是古时人手必备之物，尤其妇女，几乎梳不离身。在常州梳篦业近代的发展中，以卜恒顺、老王大昌、王大昌、汪义大四家梳篦坊店最负盛名，其中尤以卜恒顺制作的梳篦美誉度最高。恒顺梳篦店开设于明代天启年间（1622），世代相传，传至1953年有八代之久，300余年历史，但卜家技艺传承现已断代，老王大昌、王大昌、汪义大三家祖传技艺亦已失传。");
			} else if (i == 10) {
				p = new Estate(i, "贞和堂", 3, 2700,1500,3,new ArrayList<>(Arrays.asList(240,1600,4200,8250)));
				p.setImg("qingguo/zhenhetang");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setTitle("贞和堂 · 唐荆川宅");
				p.getDetail().setDesc("原名保和堂，是唐氏八宅中的主宅。贞和堂的大厅是常州保存下来的最大明代楠木厅。唐顺之，号荆川，嘉靖八年（1529年）二十三岁中进士，礼部会试第一，入翰林院任编修。与归有光、王慎中三人合称为“嘉靖三大家”。著有《荆川集》、《勾股容方圆论》等著作。荆川先生不但是有名的文学家、数学家，同时也是有名的抗倭英雄，在抗倭战斗中屡建奇功。刀枪骑射，无不娴熟。");
			} else if (i == 12) {
				p = new Estate(i, "八桂堂", 3, 1800,1000,3,new ArrayList<>(Arrays.asList(135,750,1950,5050)));
				p.setImg("qingguo/baguitang");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setDesc("唐氏八宅之一，是唐荆川高中会试状元后所建的宅子。因其在宅中种下八颗桂花树，因此得名。晚清为湖北按察使瞿赓甫（瞿秋白叔祖）所购，瞿秋白在八桂堂中的天香楼诞生，民国三十七年（1948年）为刘国钧购得。刘国钧曾在1930年集资创办常州大成纺织印染公司。");
			} else if (i == 14) {
				p = new Estate(i, "筠星堂", 3, 1500,500,3,new ArrayList<>(Arrays.asList(100,550,1250,4000)));
				p.setImg("qingguo/yunxingtang");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setDesc("唐氏八宅之一，原为唐荆川玄孙、明崇祯举人唐宇量居住，存清建回形转楼及部分明代木结构建筑，花厅及假山、石池花木等。");
			} else if (i == 16) {
				p = new Estate(i, "半园", 4, 2000,1000,3,new ArrayList<>(Arrays.asList(155,850,2250,5800)));
				p.setImg("qingguo/banyuan");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setDesc("半园始建于明中，由唐荆川祖父唐贵所建，是贞和堂后花园。占地约为6亩。园中凿有荷池，池畔置以湖石，叠石为山，亭台楼阁筑于池周。");
			} else if (i == 17) {
				p = new Estate(i, "吕宅", 4, 2200,1000,3,new ArrayList<>(Arrays.asList(175,950,2650,6350)));
				p.setImg("qingguo/lvzhai");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setTitle("吕宅 · 文化记忆馆");
				p.getDetail().setDesc("青果巷文化记忆馆位于吕宅，是了解青果巷及常州历史文化的重要窗口。赵元任艺术中心同样位于吕宅，整个展陈以“教我如何不想他”为主题，展示了赵元任先生在语言学、音乐学等方面的杰出成就。");
			} else if (i == 19) {
				p = new Estate(i, "元代天井", 5, 2400,1000,3,new ArrayList<>(Arrays.asList(200,1100,3100,6900)));
				p.setImg("qingguo/yuandaitianjing");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setDesc("“天井”井栏石用青石凿成，井壁以青砖，至今仍汲取此井之水洗用。据常州方志记载，此井系元代邑人赵云卿所开凿。赵精于地理，能知水源，经他选址开凿的井能长年不干涸，虽大旱之年水源枯竭，而此井涓涓不竭，被誉为“天井”。如今该井附近有打卡地天井阁。");
			} else if (i == 23) {
				p = new Estate(i, "史良故居", 5, 3100,2000,3,new ArrayList<>(Arrays.asList(285,2000,5300,10000)));
				p.setImg("qingguo/shiliangguju");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setDesc("史良，中国当代法学家、政治家、女权运动先驱，抗日救国会“七君子”之一。她是新中国人民司法工作的开拓者和司法行政工作的奠基人，是中国妇女运动的领袖之一。");
			} else if (i == 25) {
				p = new Estate(i, "刘仙师庙", 6, 2800,1500,3,new ArrayList<>(Arrays.asList(250,1700,4450,8600)));
				p.setImg("qingguo/liuxianshimiao");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setDesc("明万历年间曾有一刘姓神医在阳湖县城隍庙戏楼附近行医，医术高超、医德高尚，他时常为穷人看病不收钱而施药，因而终身贫困，最后郁郁而终。常州人感念这位神医，就在此造了刘仙师祠。旧时常州人有病就到刘先师祠庙里祈求保佑。");
			} else if (i == 27) {
				p = new Estate(i, "湛贻堂", 6, 3600,2000,3,new ArrayList<>(Arrays.asList(320,2500,6000,11500)));
				p.setImg("qingguo/zhanyitang");
				p.setFont("jnk", 18);
				p.createDetail();
				p.getDetail().setTitle("湛贻堂 · 赵元任故居");
				p.getDetail().setDesc("湛贻堂原为旧宅中赵元任六世祖赵翼的堂号，湛贻堂系赵元任曾祖父赵朗浦（清金华府知府）建于咸丰年间，新宅建成后，沿用“湛贻堂”堂号以承先祖之志。1892年，赵元任生于祖父赵执诒任官地天津，1900年，因祖父病故，赵元任随父母扶灵柩首次回到常州，自此在湛贻堂生活。赵元任，中国语言学家，精研北方话与吴语方言的音系。通晓8种语言和33种汉语方言，被称为“汉语言学”之父。");
			}
			
			map.addPlace(p);
		}
		
		map.setFateIds(new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,101,102,103,104,105,21,22,23,24,25,26,106,107)));
		return map;
	}
}
