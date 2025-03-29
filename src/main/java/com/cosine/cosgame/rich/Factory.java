package com.cosine.cosgame.rich;

import org.bson.Document;

import com.cosine.cosgame.rich.basicplaces.*;
import com.cosine.cosgame.rich.gta.cards.*;
import com.cosine.cosgame.rich.gta.places.*;

public class Factory {
	public static Card genCard(int x) {
		Card c = new Card();
		int id = x/100;
		int level = x%100;
		
		if (id == 1) {
			c = new Card1();
		}
		else if (id == 10) {
			c = new CardRelease();
		}
		
		else if (id == 16) {
			c = new CardNugget();
		}
		
		else if (id == 20001) {
			c = new CardSalmonBite();
		} else if (id == 20002) {
			c = new CardPoutine();
		} else if (id == 20003) {
			c = new CardNanaimoBar();
		}
		
		c.setLevel(level);
		return c;
	}
	
	public static Card genNewCard(int x) {
		return genCard(x*100);
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
			return new Fate(id,Consts.FATE_ADD,1145,"投稿时把小学作文《让我掏心掏肺的朋友》错投在医学栏目，竟意外地被《柳叶刀》发布，获得稿费$1145。","p投稿时把小学作文《让我掏心掏肺的朋友》错投在医学栏目，竟意外地被《柳叶刀》发布，p获得稿费$1145");
		} else if (id == 23) {
			return new Fate(id,Consts.FATE_ADD,520,"你在技术博客上发表的《为什么会找不到对象》被情感杂志收录，获得稿费$520。","p在技术博客上发表的《为什么会找不到对象》被情感杂志收录，获得稿费$520");
		} else if (id == 24) {
			return new Fate(id,Consts.FATE_LOSE,400,"被发小借走$400，发小发誓以后或许会还。","p被发小借走$400，发小发誓以后或许会还");
		} else if (id == 25) {
			return new Fate(id,Consts.FATE_LOSE,809,"感到呼吸困难，花费$809做全身体检，结果发现只是衣服穿反了。","p感到呼吸困难，花费$809做全身体检，结果发现只是衣服穿反了");
		} else if (id == 26) {
			return new Fate(id,Consts.FATE_LOSE,1300,"好心扶起被撞倒的老登却被老登反诬是肇事者，赔偿老登$1300。","p好心扶起被撞倒的老登却被老登反诬是肇事者，p赔偿老登$1300。");
		}
		
		
		else if (id == 101) {
			return new Fate(id,Consts.FATE_MOVE,0,"参观钱庄旧址，移动到钱庄（可领取$2000）。","p将去往钱庄旧址参观");
		} else if (id == 102) {
			return new Fate(id,Consts.FATE_MOVE,101,"口干舌燥，移动到前方最近的茶馆（需要支付路费或选择是否购买）。","因为口干舌燥，p将会移动到前方最近的茶馆");
		} else if (id == 103) {
			return new Fate(18,Consts.FATE_LOSE,555,"算命时被大师质问：“你算什么东西？”后十分抑郁，去看心理医生，付咨询费$555。","p在算命时被大师质问：“你算什么东西？”后十分抑郁，去看心理医生，付咨询费$555");
		} else if (id == 104) {
			return new Fate(id,Consts.FATE_ADD,1437,"和老师傅学习乱针绣并当场卖出，获得$1888。","p和老师傅学习乱针绣并当场卖出，获得$1888");
		} else if (id == 105) {
			return new Fate(id,Consts.FATE_CARD,10,"将捡到的钱包还给失主，没想到失主是狱警，获得狱警赠送的出狱卡。","p将捡到的钱包还给失主，没想到失主是狱警，获得狱警赠送的出狱卡");
		} else if (id == 106) {
			return new Fate(id,Consts.FATE_MOVE,102,"追着游览车玩，移动到前方最近的游览车站。","p追着游览车玩，将会移动前方最近的游览车站");
		} else if (id == 107) {
			return new Fate(id,Consts.FATE_GOTOJAIL,0,"为海外商家出口大麻糕，因机翻的商品名被海关误解，立即入狱。","p为海外商家出口大麻糕，因机翻的商品名被海关误解，p被被抓入监狱");
		}  
		
		
		else if (id == 201) {
			return new Fate(id,Consts.FATE_CARD,10,"给狱警推荐了好吃的甜甜圈，获得狱警赠送的出狱卡。","因为给狱警推荐了好吃的甜甜圈，p获得狱警赠送的出狱卡");
		}
		
		
		else if (id == 10001) {
			return new Fate(id,Consts.FATE_HPSTAR,21,"边走路边玩手机，撞到电线杆，失去1点生命值。","因为边走路边玩手机，撞到电线杆，p失去1点生命值");
		} else if (id == 10002) {
			return new Fate(id,Consts.FATE_LOSE,210600,"被疯狗咬，失去1点生命值，打狂犬病疫苗花费$600。","p被疯狗咬，失去1点生命值，打狂犬病疫苗花费$600");
		}
		
		else if (id == 10201) {
			return new Fate(id,Consts.FATE_CARD,20001,"参加试吃活动，获得商家赠送的因纽特人特产烟熏三文鱼小吃。","p参加试吃活动，获得商家赠送的因纽特人特产烟熏三文鱼小吃");
		} else if (id == 10202) {
			return new Fate(id,Consts.FATE_CARDLOSE,200020100,"购买魁北克特色小吃肉汁奶酪盖浇薯条，花费$100。","p购买魁北克特色小吃肉汁奶酪盖浇薯条，花费$100");
		} else if (id == 10203) {
			return new Fate(id,Consts.FATE_CARD,20003,"参加试吃活动，获得商家赠送的温哥华岛特色甜品纳奈莫条。","p参加试吃活动，获得商家赠送的温哥华岛特色甜品纳奈莫条");
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
}
