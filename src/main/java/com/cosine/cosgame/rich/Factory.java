package com.cosine.cosgame.rich;

import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.rich.basicplaces.*;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.eco.news.*;
import com.cosine.cosgame.rich.gta.cards.*;
import com.cosine.cosgame.rich.gta.places.*;

public class Factory {
	public static Card genCard(int x) {
		Card c = new Card();
		int id = x/100;
		int level = x%100;
		
		if (id == 0) {
			c = new CardStay();
		} else if (id == 1) {
			c = new Card1();
		} else if (id == 2) {
			c = new Card2();
		} else if (id == 3) {
			c = new Card3();
		} else if (id == 4) {
			c = new Card4();
		} else if (id == 5) {
			c = new Card5();
		} else if (id == 6) {
			c = new Card6();
		} else if (id == 7) {
			c = new CardRemoteDice();
		}
		
		else if (id == 9) {
			c = new CardVehicleCoupon();
		} else if (id == 10) {
			c = new CardRelease();
		} else if (id == 11) {
			c = new CardP1();
		} else if (id == 12) {
			c = new CardP2();
		} else if (id == 13) {
			c = new CardP3();
		} else if (id == 14) {
			c = new CardDiamond();
		} else if (id == 15) {
			c = new CardPearl();
		} else if (id == 16) {
			c = new CardNugget();
		} else if (id == 17) {
			c = new CardBaseball();
		} else if (id == 18) {
			c = new CardBrick();
		} else if (id == 19) {
			c = new CardCurlingStone();
		} else if (id == 20) {
			c = new CardFromNothing();
		} else if (id == 21) {
			c = new CardRumor();
		} else if (id == 22) {
			c = new CardLittleEssay();
		} else if (id == 23) {
			c = new CardRefuseRent();
		} else if (id == 24) {
			c = new CardDividend();
		} else if (id == 25) {
			c = new CardHealthInsurance();
		} else if (id == 26) {
			c = new CardDeathCart();
		} else if (id == 27) {
			c = new CardGTA();
		} else if (id == 28) {
			c = new CardIbaka();
		} else if (id == 29) {
			c = new CardThunder();
		} else if (id == 30) {
			c = new CardDinosaur();
		} else if (id == 31) {
			c = new CardKyogre();
		} else if (id == 32) {
			c = new CardDart();
		} else if (id == 33) {
			c = new CardShuriken();
		} else if (id == 34) {
			c = new CardFrisbee();
		} else if (id == 35) {
			c = new CardPainted();
		} else if (id == 36) {
			c = new CardFakeDown();
		} else if (id == 37) {
			c = new CardDowngrade();
		} else if (id == 38) {
			c = new CardOccupy();
		} else if (id == 39) {
			c = new CardAlterContract();
		} else if (id == 40) {
			c = new CardTeleport();
		} else if (id == 41) {
			c = new CardBuild();
		} else if (id == 42) {
			c = new CardRobotWorker();
		} else if (id == 43) {
			c = new CardBuyEstate();
		} else if (id == 44) {
			c = new CardSubstitute();
		} else if (id == 45) {
			c = new CardTelescope();
		} else if (id == 46) {
			c = new CardHydroPump();
		} else if (id == 47) {
			c = new CardJavelin();
		} else if (id == 48) {
			c = new CardAllRich();
		} else if (id == 49) {
			c = new CardAllPoor();
		} else if (id == 50) {
			c = new CardMipha();
		}
		else if (id == 54) {
			c = new CardFakeCry();
		} else if (id == 55) {
			c = new CardCutpurse();
		} else if (id == 56) {
			c = new CardHarsh();
		} else if (id == 57) {
			c = new CardFlyingThief();
		} else if (id == 58) {
			c = new CardCP3();
		} else if (id == 59) {
			c = new CardBuildCar();
		} else if (id == 60) {
			c = new CardKaka();
		} else if (id == 61) {
			c = new CardZhiHeng();
		} else if (id == 62) {
			c = new CardGraverobber();
		} else if (id == 63) {
			c = new CardTanners();
		} else if (id == 64) {
			c = new CardArsenal();
		} else if (id == 65) {
			c = new CardTruck();
		} else if (id == 66) {
			c = new CardTrick();
		} else if (id == 67) {
			c = new CardBlessings();
		}
		
		else if (id == 95) {
			c = new CardFiveGoals();
		}
		
		else if (id == 9000) {
			c = new CardSuccessBook();
		} else if (id == 9001) {
			c = new CardGift();
		} else if (id == 9002) {
			c = new CardBlindBox();
		} else if (id == 9003) {
			c = new CardPanda();
		}
		
		else if (id == 10001) {
			c = new CardPanFriedBun();
		} else if (id == 10002) {
			c = new CardCatfish();
		} else if (id == 10003) {
			c = new CardSteamedBun();
		} else if (id == 10004) {
			c = new CardRibRiceCake();
		} else if (id == 10005) {
			c = new CardDreamBlue();
		} else if (id == 10006) {
			c = new CardDoubleHappiness();
		} else if (id == 10007) {
			c = new CardVanishingCream();
		} else if (id == 10008) {
			c = new CardDomesticGoose();
		} else if (id == 10009) {
			c = new CardYellowWine();
		} else if (id == 10010) {
			c = new CardPearSyrupCandy();
		}
		
		
		else if (id == 20001) {
			c = new CardSalmonBite();
		} else if (id == 20002) {
			c = new CardPoutine();
		} else if (id == 20003) {
			c = new CardNanaimoBar();
		} else if (id == 20004) {
			c = new CardHawaiianPizza();
		} else if (id == 20005) {
			c = new CardIceWine();
		} else if (id == 20006) {
			c = new CardDuMaurier();
		} else if (id == 20007) {
			c = new CardMacRetro();
		} else if (id == 20008) {
			c = new CardCanadaGoose();
		} else if (id == 20009) {
			c = new CardBloodyCaesar();
		} else if (id == 20010) {
			c = new CardPurdy();
		}
		
		c.setLevel(level);
		return c;
	}
	
	public static Card genNewCard(int x) {
		return genCard(x*100);
	}
	
	public static News genNews(int id) {
		News news = new News();
		if (id == 1) {
			news = new NewsTaxReturn();
		} else if (id == 2) {
			news = new NewsHappyCity();
		} else if (id == 3) {
			news = new NewsPainCity();
		} else if (id == 4) {
			news = new NewsPeachGarden();
		} else if (id == 5) {
			news = new NewsStationCaveIn();
		} else if (id == 6) {
			news = new NewsArrows();
		} else if (id == 7) {
			news = new NewsGust();
		} else if (id == 8) {
			news = new NewsVampire();
		} else if (id == 9) {
			news = new NewsPanda();
		} else if (id == 10) {
			news = new NewsGifts();
		} else if (id == 11) {
			news = new NewsCarRace();
		} else if (id == 12) {
			news = new NewsCarStop();
		} else if (id == 13) {
			news = new NewsCarInsurance();
		} else if (id == 14) {
			news = new NewsCarDistribute();
		} else if (id == 15) {
			news = new NewsCarTrain();
		} else if (id == 16) {
			news = new NewsCarBurn();
		} else if (id == 17) {
			news = new NewsTakeEstate();
		} else if (id == 18) {
			news = new NewsEarthquake();
		} else if (id == 19) {
			news = new NewsPollution();
		} else if (id == 20) {
			news = new NewsAngel();
		}
		return news;
	}
	
	public static Place genPlace(Document doc, Board board) {
		Place p = null;
		int type = doc.getInteger("type", 0);
		if (type == Consts.PLACE_EMPTY) {
			p = new Empty(doc, board);
		} else if (type == Consts.PLACE_STARTPOINT) {
			p = new StartPoint(doc, board);
		} else if (type == Consts.PLACE_TAX) {
			p = new Tax(doc, board);
		} else if (type == Consts.PLACE_ESTATE) {
			p = new Estate(doc, board);
		} else if (type == Consts.PLACE_FATE) {
			p = new PersonalEvent(doc, board);
		} else if (type == Consts.PLACE_JAIL) {
			p = new Jail(doc, board);
		} else if (type == Consts.PLACE_GOTOJAIL) {
			p = new GoToJail(doc, board);
		} else if (type == Consts.PLACE_HOSPITAL) {
			p = new Hospital(doc, board);
		} else if (type == Consts.PLACE_CARDGAINER) {
			p = new CardGainer(doc, board);
		}
		return p;
	}
	
	public static Fate genFate(int id) {
		Fate fate = null;
		if (id == 1) {
			return new Fate(id,Consts.FATE_ADD,100,"在路边捡到$100。","p在路边捡到了$100");
		} else if (id == 2) {
			return new Fate(id,Consts.FATE_LOSE,200,"对着警察吐痰，被罚款$200。","p因为对着警察吐痰，被罚款$200");
		} else if (id == 3) {
			return new Fate(id,Consts.FATE_GOTOJAIL,0,"酒后闹事，立即入狱。","p因为喝酒闹事，被抓入监狱");
		} else if (id == 4) {
			return new Fate(id,Consts.FATE_ADD,2000,"获得陌生人的错误转账$2000。","p获得陌生人的错误转账$2000");
		} else if (id == 5) {
			return new Fate(id,Consts.FATE_ADD,777,"获得银行的利息$777。","p获得获得银行的利息$777");
		} else if (id == 6) {
			return new Fate(id,Consts.FATE_HOUSEREPAIR,250,"房屋维修，每有一级建筑，支付$250。","房屋维修，p每有一级建筑，支付$250");
		} else if (id == 7) {
			return new Fate(id,Consts.FATE_RECEIVEEVERY,158,"过生日，每名其他玩家交给你$158作为礼物。","p过生日，每名其他玩家交给p$158作为礼物");
		} else if (id == 8) {
			return new Fate(id,Consts.FATE_GIVEEVERY,88,"给朋友发送节日红包，交给每名其他玩家$88。","p给每名其他玩家发送价值$88的红包");
		} else if (id == 9) {
			return new Fate(id,Consts.FATE_LOSE,721,"遗失钱包，损失$721。","p的钱包丢了，损失了$721");
		} else if (id == 10) {
			return new Fate(id,Consts.FATE_ADD,1,"坐在路边休息时被当成乞丐，获得路人施舍的$1。","p坐在路边休息时被当成乞丐，获得路人施舍的$1");
		} else if (id == 11) {
			return new Fate(id,Consts.FATE_LOSE,2000,"银行卡被盗刷，损失$2000。","p的银行卡被盗刷，损失$2000");
		} else if (id == 12) {
			return new Fate(id,Consts.FATE_ADD,300,"发票中奖获得$300。","p的发票中奖，获得$300");
		} else if (id == 13) {
			return new Fate(id,Consts.FATE_PROPERTYTAX,175,"付地税，每有一个地产，支付$175。","付地税，p每有一个地产，支付$175");
		} else if (id == 14) {
			return new Fate(id,Consts.FATE_LOSE,600,"被路边的野狗挠，打狂犬疫苗花费$600。","p被路边的野狗挠，打狂犬疫苗花费$600");
		} else if (id == 15) {
			return new Fate(id,Consts.FATE_LOSE,1024,"往运河里乱扔垃圾，罚款$1024。","p往运河里乱扔垃圾，罚款$1024");
		} else if (id == 16) {
			return new Fate(id,Consts.FATE_LOSE,1450,"手机掉入马桶，花费$1450购买新手机。","p的手机掉入了马桶，花费$1450购买新手机");
		} else if (id == 17) {
			return new Fate(id,Consts.FATE_LOSE,1500,"在路人的劝说下投资一个“稳赚不赔”的理财产品，但对方拿了钱之后跑路了，损失$1500。","p在路人的劝说下投资一个“稳赚不赔”的理财产品，但对方拿了钱之后跑路了，p损失$1500");
		} else if (id == 18) {
			return new Fate(id,Consts.FATE_ADD,666,"在网红景点直播后空翻，获得粉丝打赏的$666。","p在网红景点直播后空翻，获得粉丝打赏的$666");
		} else if (id == 19) {
			return new Fate(id,Consts.FATE_ADD,996,"被拉去表演脱口秀，获得出场费$996。","p被拉去表演脱口秀，获得出场费$996");
		} else if (id == 20) {
			return new Fate(id,Consts.FATE_ADD,1437,"在路边捡到一颗金杨桃，倒卖后获得$1437。","p在路边捡到一颗金杨桃，倒卖后获得$1437");
		} else if (id == 21) {
			return new Fate(id,Consts.FATE_ADD,200,"发小归还十年前借的$200，即使你已经完全忘了这件事。","p的发小归还十年前借的$200，即使p已经完全忘了这件事");
		} else if (id == 22) {
			return new Fate(id,Consts.FATE_ADD,1145,"小学时投稿的作文《让我掏心掏肺的朋友》竟意外地被《柳叶刀》周刊发布，获得稿费$1145。","p小学时投稿的作文《让我掏心掏肺的朋友》竟意外地被《柳叶刀》周刊发布，p获得稿费$1145");
		} else if (id == 23) {
			return new Fate(id,Consts.FATE_ADD,520,"你在技术博客上发表的《为什么会找不到对象》被情感杂志收录，获得稿费$520。","p在技术博客上发表的《为什么会找不到对象》被情感杂志收录，获得稿费$520");
		} else if (id == 24) {
			return new Fate(id,Consts.FATE_LOSE,400,"被发小借走$400，发小发誓以后或许会还。","p被发小借走$400，发小发誓以后或许会还");
		} else if (id == 25) {
			return new Fate(id,Consts.FATE_LOSE,809,"感到呼吸困难，花费$809做全身体检，结果发现只是衣服穿反了。","p感到呼吸困难，花费$809做全身体检，结果发现只是衣服穿反了");
		} else if (id == 26) {
			return new Fate(id,Consts.FATE_LOSE,1300,"好心扶起被撞倒的老登却被老登反诬是肇事者，赔偿老登$1300。","p好心扶起被撞倒的老登却被老登反诬是肇事者，p赔偿老登$1300");
		}
		
		
		else if (id == 101) {
			return new Fate(id,Consts.FATE_MOVE,0,"参观钱庄旧址，移动到钱庄（可领取$2000）。","p将去往钱庄旧址参观");
		} else if (id == 102) {
			return new Fate(id,Consts.FATE_MOVE,101,"口干舌燥，移动到前方最近的茶馆（需要支付路费或选择是否购买）。","因为口干舌燥，p将会移动到前方最近的茶馆");
		} else if (id == 103) {
			return new Fate(18,Consts.FATE_LOSE,555,"算命时被大师质问：“你算什么东西？”后十分抑郁，去看心理医生，付咨询费$555。","p在算命时被大师质问：“你算什么东西？”后十分抑郁，去看心理医生，付咨询费$555");
		} else if (id == 104) {
			return new Fate(id,Consts.FATE_ADD,1888,"和老师傅学习乱针绣并当场卖出，获得$1888。","p和老师傅学习乱针绣并当场卖出，获得$1888");
		} else if (id == 105) {
			return new Fate(id,Consts.FATE_CARD,10,"将捡到的钱包还给失主，没想到失主是狱警，获得狱警赠送的出狱卡。","p将捡到的钱包还给失主，没想到失主是狱警，获得狱警赠送的出狱卡");
		} else if (id == 106) {
			return new Fate(id,Consts.FATE_MOVE,102,"追着游览车玩，移动到前方最近的游览车站（需要支付路费或选择是否购买）。","p追着游览车玩，将会移动前方最近的游览车站");
		} else if (id == 107) {
			return new Fate(id,Consts.FATE_GOTOJAIL,0,"为海外商家出口大麻糕，因机翻的商品名被海关误解，立即入狱。","p为海外商家出口大麻糕，因机翻的商品名被海关误解，p被被抓入监狱");
		} 
		
		
		
		else if (id == 201) {
			return new Fate(id,Consts.FATE_CARD,10,"给狱警推荐了好吃的甜甜圈，获得狱警赠送的出狱卡。","因为给狱警推荐了好吃的甜甜圈，p获得狱警赠送的出狱卡");
		} else if (id == 202) {
			return new Fate(id,Consts.FATE_MOVE,0,"去五大行实习，移动到金融区（可领取$2000）。","p将去往金融区的五大行实习");
		} else if (id == 203) {
			return new Fate(id,Consts.FATE_MOVE,101,"咖啡店推出你还没喝过的季节限定，移动到前方最近的咖啡店（需要支付路费或选择是否购买）。","咖啡店推出p还没喝过的季节限定，p将会移动到最近的咖啡店");
		} else if (id == 204) {
			return new Fate(id,Consts.FATE_MOVE,102,"作为资深车迷，一路追着庞巴迪双层车拍车，移动到前方最近的GO车站（需要支付路费或选择是否购买）。","作为资深车迷，p一路追着庞巴迪双层车拍车，将会移动到前方最近的GO车站");
		} 
		
		
		else if (id == 10001) {
			return new Fate(id,Consts.FATE_HPSTAR,21,"边走路边玩手机，撞到电线杆，失去1点生命值。","因为边走路边玩手机，撞到电线杆，p失去1点生命值");
		} else if (id == 10002) {
			return new Fate(id,Consts.FATE_LOSE,210600,"被疯狗咬，失去1点生命值，打狂犬病疫苗花费$600。","p被疯狗咬，失去1点生命值，打狂犬病疫苗花费$600");
		} else if (id == 10003) {
			return new Fate(id,Consts.FATE_HPSTAR,4121,"参与无偿献血，减少1点生命值和1点通缉值。","p参与无偿献血，减少1点生命值和1点通缉值");
		} else if (id == 10004) {
			return new Fate(id,Consts.FATE_CARDLOSE,90000386,"购买一本名为《成功学》的书，花费$386。","p购买了一本名为《成功学》的书，花费$386");
		} else if (id == 10005) {
			return new Fate(id,Consts.FATE_LOSE,210493,"玩游戏被对手暴虐，一拳打碎显示屏，失去1点生命值，更换显示屏花费$493。","p玩游戏被对手暴虐，一拳打碎显示屏，失去1点生命值，更换显示屏花费$493");
		} else if (id == 10006) {
			return new Fate(id,Consts.FATE_HPSTAR,23,"被闪电击中，失去3点生命值。","p被闪电击中，失去3点生命值");
		} else if (id == 10007) {
			return new Fate(id,Consts.FATE_HPSTAR,32,"大晚上边唱跳边打篮球，被邻居举报后收到律师函警告，增加2点通缉值。","p大晚上边唱跳边打篮球，被邻居举报后收到律师函警告，p增加2点通缉值");
		} else if (id == 10008) {
			return new Fate(id,Consts.FATE_CARDHPSTAR,900141,"在社区担任生命教育的志工，减少1点通缉值并获得小朋友赠送的礼物。","p在社区担任生命教育的志工，减少1点通缉值并获得小朋友赠送的礼物");
		} else if (id == 10009) {
			return new Fate(id,Consts.FATE_VEHICLEHPSTAR,10000,"与朋友参观车展，没想到朋友当场买下两辆车并送你一辆，老板大气！","p与朋友参观车展，没想到p的朋友当场买下两辆车并送p一辆，老板大气");
		} else if (id == 10010) {
			return new Fate(id,Consts.FATE_VEHICLEHPSTAR,9990031,"故意不小心把共享单车骑回家，但是决定将错就错，增加1点通缉值。","p故意不小心把共享单车骑回家，但是决定将错就错，p增加1点通缉值");
		} else if (id == 10011) {
			return new Fate(id,Consts.FATE_ADD,211001,"参与魔术表演获得出场费$1001，但是表演帽子戏法时被兔子咬伤，失去1点生命值。","p参与魔术表演获得出场费$1001，但是表演帽子戏法时被兔子咬伤，p失去1点生命值");
		} else if (id == 10012) {
			return new Fate(id,Consts.FATE_ADD,220250,"参与地狱辣挑战获得冠军，奖励$250，但赛后一泻千里，失去2点生命值。","p参与地狱辣挑战获得冠军，奖励$250，但p赛后一泻千里，失去2点生命值");
		} else if (id == 10013) {
			return new Fate(id,Consts.FATE_HPSTAR,31,"对着警察吐痰，增加1点通缉值。","p因为对着警察吐痰，增加了1点通缉值");
		} else if (id == 10014) {
			return new Fate(id,Consts.FATE_HPSTAR,3122,"凌空抽射时没踢中球却踢伤自己的膝盖，失去2点生命值，但裁判却认为是假摔，出示红牌，增加1点通缉值。","p凌空抽射时没踢中球却踢伤自己的膝盖，失去2点生命值，但裁判却认为是假摔，出示红牌，p增加1点通缉值");
		} else if (id == 10015) {
			return new Fate(id,Consts.FATE_CARDHPSTAR,900222,"购买盲盒时要求路人帮忙砍一刀，路人表示没听说过这么奇怪的要求但照做了，失去2点生命值并免费获得盲盒。","p购买盲盒时要求路人帮忙砍一刀，路人表示没听说过这么奇怪的要求但照做了，p失去2点生命值并免费获得盲盒");
		} else if (id == 10016) {
			return new Fate(id,Consts.FATE_ADD,330999,"你兄弟带你晚没有晚过的船新版本的真人版，你觉得是兄弟就要来砍他，一刀下去获得$999和3点通缉值。","p的兄弟带p晚没有晚过的船新版本的真人版，p觉得是兄弟就要来砍他，一刀下去获得$999和3点通缉值");
		} else if (id == 10017) {
			return new Fate(id,Consts.FATE_RECEIVEEVERYCARD,0,"过生日，每名其他玩家交给你一张随机手牌作为礼物。","p过生日，每名其他玩家交给p一张随机手牌作为礼物");
		} else if (id == 10018) {
			return new Fate(id,Consts.FATE_LOSECARDHPSTAR,10023,"被吸血老太婆攻击，失去3点生命值，逃跑时在慌乱中丢失一张手牌。","p被吸血老太婆攻击，失去3点生命值");
		} else if (id == 10019) {
			return new Fate(id,Consts.FATE_LOSEHAND,0,"背包被老鹰抓走，失去所有手牌。","p的背包被老鹰抓走，失去所有手牌");
		} else if (id == 10020) {
			return new Fate(id,Consts.FATE_HPSTARSTOP,21,"不小心掉入坑内，失去1点生命值，且下回合骰子点数为0。","p不小心掉入坑内，失去1点生命值，且p下回合骰子点数为0");
		} else if (id == 10021) {
			return new Fate(id,Consts.FATE_ADDSALARY,500,"研究出时间复杂度为O(1)的排序算法，大大优化公司产品的效率，加薪$500。","p因研究出时间复杂度为O(1)的排序算法，大大优化公司产品的效率，加薪$500");
		} else if (id == 10022) {
			return new Fate(id,Consts.FATE_LOSESALARY,100,"在开会时吐槽PHP是世界上最差的语言，激怒领导，降薪$100。","p因在开会时吐槽PHP是世界上最差的语言，激怒领导，降薪$100");
		} else if (id == 10023) {
			return new Fate(id,Consts.FATE_ADDSALARY,100,"在工作时玩Cosgame被领导发现，没想到领导也非常喜欢并要求组织团建，加薪$100。","p在工作时玩Cosgame被领导发现，没想到领导也非常喜欢并要求p组织团建，加薪$100");
		} 
		
		else if (id == 10201) {
			return new Fate(id,Consts.FATE_CARD,20001,"参加试吃活动，获得商家赠送的因纽特人特产烟熏三文鱼小吃。","p参加试吃活动，获得商家赠送的因纽特人特产烟熏三文鱼小吃");
		} else if (id == 10202) {
			return new Fate(id,Consts.FATE_CARDLOSE,200020100,"购买魁北克特色小吃肉汁奶酪盖浇薯条，花费$100。","p购买魁北克特色小吃肉汁奶酪盖浇薯条，花费$100");
		} else if (id == 10203) {
			return new Fate(id,Consts.FATE_CARD,20003,"参加试吃活动，获得商家赠送的温哥华岛特色甜品纳奈莫条。","p参加试吃活动，获得商家赠送的温哥华岛特色甜品纳奈莫条");
		} else if (id == 10204) {
			return new Fate(id,Consts.FATE_CARDHPSTAR,2000431,"制作安大略出产的带有菠萝的夏威夷披萨被视为异端，增加1点通缉值。","p制作安大略出产的带有菠萝的夏威夷披萨被视为异端，增加1点通缉值");
		} else if (id == 10205) {
			return new Fate(id,Consts.FATE_HPSTAR,33,"周日在Yonge街拖行已经归天的马，增加3点通缉值。","周日，p因在Yonge街拖行已经归天的马，增加3点通缉值");
		}
		
		// vehicle tweaks
		else if (id == 910001) {
			return new Fate(id,Consts.FATE_VEHICLEHPSTAR,20021,"汽车撞到电线杆，失去载具和1点生命值。","p的汽车撞到电线杆，p失去载具和1点生命值");
		} else if (id == 910006) {
			return new Fate(id,Consts.FATE_VEHICLEHPSTAR,20023,"汽车被闪电击中，失去载具和3点生命值。","p的汽车被闪电击中，p失去载具和3点生命值");
		} else if (id == 910009) {
			return new Fate(id,Consts.FATE_LOSE,150,"把汽车停在公厕门口，被罚款$150。","p把汽车停在公厕门口，被罚款$150");
		} else if (id == 910010) {
			return new Fate(id,Consts.FATE_ADD,946,"在汽车上表演杂技，获得路人打赏的$946。","p在汽车上表演杂技，获得路人打赏的$946");
		} else if (id == 910020) {
			return new Fate(id,Consts.FATE_LOSESTOP,567,"汽车爆胎，更换轮胎花费$567，且下回合骰子点数为0。","p汽车爆胎，更换轮胎花费$567，且p下回合骰子点数为0");
		}
		
		return fate;
	}
	
	public static Avatar genAvatar(int id) {
		Avatar avatar = null;
		if (id == -1) {
			avatar = new Avatar(9999,"未解锁");
		} else if (id == 0) {
			avatar = new Avatar(0,"蓝猫");
		} else if (id == 1) {
			avatar = new Avatar(1,"淘气");
		} else if (id == 2) {
			avatar = new Avatar(2,"菲菲");
		} else if (id == 3) {
			avatar = new Avatar(3,"咖喱");
		} else if (id == 4) {
			avatar = new Avatar(4,"肥仔");
		} else if (id == 5) {
			avatar = new Avatar(5,"鸡大婶");
		} else if (id == 6) {
			avatar = new Avatar(6,"甜妞");
		} else if (id == 7) {
			avatar = new Avatar(7,"啦啦");
		}
		return avatar;
	}
	
	public static Vehicle genVehicle(int id) {
		Vehicle v = new Vehicle();
		if (id == 1) {
			v = new Vehicle(id, "利兰Mini 1000");
		} else if (id == 2) {
			v = new Vehicle(id, "特斯拉Cybertruck");
		} else if (id == 3) {
			v = new Vehicle(id, "宝马X3");
		} else if (id == 4) {
			v = new Vehicle(id, "丰田RAV4");
		} else if (id == 5) {
			v = new Vehicle(id, "凯迪拉克OV");
		} else if (id == 6) {
			v = new Vehicle(id, "大众Taos");
		} else if (id == 7) {
			v = new Vehicle(id, "宾利欧陆GT");
		} else if (id == 8) {
			v = new Vehicle(id, "奥迪A8");
		} else if (id == 9) {
			v = new Vehicle(id, "保时捷911");
		} else if (id == 10) {
			v = new Vehicle(id, "兰博基尼Urus");
		} else if (id == 11) {
			v = new Vehicle(id, "汗血宝马");
		} else if (id == 12) {
			v = new Vehicle(id, "密勒顿");
		} else if (id == 13) {
			v = new Vehicle(id, "哈雷Street Rod");
		} else if (id == 14) {
			v = new Vehicle(id, "大众桑塔纳");
		} else if (id == 15) {
			v = new Vehicle(id, "福特F-150");
		} else if (id == 16) {
			v = new Vehicle(id, "奔驰AMG S 63");
		} else if (id == 17) {
			v = new Vehicle(id, "本田思域");
		} else if (id == 18) {
			v = new Vehicle(id, "现代伊兰特");
		} else if (id == 19) {
			v = new Vehicle(id, "尼桑Rogue");
		} else if (id == 20) {
			v = new Vehicle(id, "雪佛兰索罗德");
		} else if (id == 21) {
			v = new Vehicle(id, "一汽夏利");
		} else if (id == 22) {
			v = new Vehicle(id, "奇瑞QQ");
		} else if (id == 23) {
			v = new Vehicle(id, "垃圾车");
		} else if (id == 24) {
			v = new Vehicle(id, "洗冰车");
		} else if (id == 25) {
			v = new Vehicle(id, "马自达CX-5");
		} else if (id == 26) {
			v = new Vehicle(id, "凯旋TR6");
		} else if (id == 27) {
			v = new Vehicle(id, "BRT");
		} else if (id == 28) {
			v = new Vehicle(id, "奥拓");
		}
		
		
		
		else if (id == 999) {
			v = new Vehicle(id, "共享单车");
		}
		return v;
	}
	
	public static Vehicle genRandomVehicle(List<Integer> ls) {
		Random rand = new Random();
		int x = rand.nextInt(ls.size());
		return genVehicle(ls.get(x));
	}
}
