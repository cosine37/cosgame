package com.cosine.cosgame.pokewhat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AllRes {
	List<Card> allCards;
	List<Pm> allPm;
	List<String> allAvatar;
	
	public AllRes() {
		allCards = new ArrayList<>();
		allPm = new ArrayList<>();
		allAvatar = new ArrayList<>();
		genAllCards();
		genAllPm();
		genAllAvatar();
	}
	
	public List<Card> genDeck(){
		return allCards;
	}
	
	public void genAllCards() {
		
	}
	
	public void genAllPm() {
		Pm p;
		
		p = new Pm("001", "妙蛙种子");
		allPm.add(p);
		p = new Pm("002", "小火龙");
		allPm.add(p);
		p = new Pm("003", "杰尼龟");
		allPm.add(p);
		p = new Pm("004", "皮卡丘");
		allPm.add(p);
		p = new Pm("005", "皮皮");
		allPm.add(p);
		p = new Pm("006", "胖丁");
		allPm.add(p);
		p = new Pm("007", "喵喵");
		allPm.add(p);
		p = new Pm("008", "阿柏蛇");
		allPm.add(p);
		p = new Pm("009", "瓦斯弹");
		allPm.add(p);
		p = new Pm("010", "伊布");
		allPm.add(p);
		p = new Pm("011", "绿毛虫");
		allPm.add(p);
		p = new Pm("012", "小磁怪");
		allPm.add(p);
		p = new Pm("013", "耿鬼");
		allPm.add(p);
		p = new Pm("014", "呆呆兽");
		allPm.add(p);
		p = new Pm("015", "可达鸭");
		allPm.add(p);
		p = new Pm("016", "鲤鱼王");
		allPm.add(p);
		p = new Pm("017", "闪光母怪力");
		allPm.add(p);
		p = new Pm("018", "大嘴蝠");
		allPm.add(p);
		p = new Pm("019", "三地鼠");
		allPm.add(p);
		p = new Pm("020", "卡比兽");
		allPm.add(p);
		p = new Pm("021", "快龙");
		allPm.add(p);
		p = new Pm("022", "六尾");
		allPm.add(p);
		p = new Pm("023", "椰蛋树");
		allPm.add(p);
		p = new Pm("024", "小火马");
		allPm.add(p);
		p = new Pm("025", "超梦");
		allPm.add(p);
		p = new Pm("026", "刺刺耳皮丘");
		allPm.add(p);
		p = new Pm("027", "波克比");
		allPm.add(p);
		p = new Pm("028", "玛力露");
		allPm.add(p);
		p = new Pm("029", "长尾怪手");
		allPm.add(p);
		p = new Pm("030", "电龙");
		allPm.add(p);
		p = new Pm("031", "月亮伊布");
		allPm.add(p);
		p = new Pm("032", "果然翁");
		allPm.add(p);
		p = new Pm("033", "巨钳螳螂");
		allPm.add(p);
		p = new Pm("034", "梦妖");
		allPm.add(p);
		p = new Pm("035", "班基拉斯");
		allPm.add(p);
		p = new Pm("036", "沼跃鱼");
		allPm.add(p);
		p = new Pm("037", "沙奈朵");
		allPm.add(p);
		p = new Pm("038", "沙漠蜻蜓");
		allPm.add(p);
		p = new Pm("039", "阿勃梭鲁");
		allPm.add(p);
		p = new Pm("040", "美纳斯");
		allPm.add(p);
		p = new Pm("041", "可可多拉");
		allPm.add(p);
		p = new Pm("042", "脱壳忍者");
		allPm.add(p);
		p = new Pm("043", "盖欧卡");
		allPm.add(p);
		p = new Pm("044", "固拉多");
		allPm.add(p);
		p = new Pm("045", "裂空座");
		allPm.add(p);
		p = new Pm("046", "路卡利欧");
		allPm.add(p);
		p = new Pm("047", "玛狃拉");
		allPm.add(p);
		p = new Pm("048", "天蝎王");
		allPm.add(p);
		p = new Pm("049", "烈咬陆鲨");
		allPm.add(p);
		p = new Pm("050", "阿尔宙斯");
		allPm.add(p);
		p = new Pm("051", "比克提尼");
		allPm.add(p);
		p = new Pm("052", "滑滑小子");
		allPm.add(p);
		p = new Pm("053", "索罗亚克");
		allPm.add(p);
		p = new Pm("054", "达摩狒狒");
		allPm.add(p);
		p = new Pm("055", "流氓鳄");
		allPm.add(p);
		p = new Pm("056", "坚果哑铃");
		allPm.add(p);
		p = new Pm("057", "双斧战龙");
		allPm.add(p);
		p = new Pm("058", "水晶灯火灵");
		allPm.add(p);
		p = new Pm("059", "火神蛾");
		allPm.add(p);
		p = new Pm("060", "土地云");
		allPm.add(p);
		p = new Pm("061", "甲贺忍蛙");
		allPm.add(p);
		p = new Pm("062", "顽皮熊猫");
		allPm.add(p);
		p = new Pm("063", "仙子伊布");
		allPm.add(p);
		p = new Pm("064", "永恒花叶蒂");
		allPm.add(p);
		p = new Pm("065", "蒂安希");
		allPm.add(p);
		p = new Pm("066", "木木枭");
		allPm.add(p);
		p = new Pm("067", "炽焰咆哮虎");
		allPm.add(p);
		p = new Pm("068", "猫鼬探长");
		allPm.add(p);
		p = new Pm("069", "鬃岩狼人");
		allPm.add(p);
		p = new Pm("070", "穿着熊");
		allPm.add(p);
		p = new Pm("071", "谜拟Q");
		allPm.add(p);
		p = new Pm("072", "费洛美螂");
		allPm.add(p);
		p = new Pm("073", "毒贝比");
		allPm.add(p);
		p = new Pm("074", "玛机雅娜");
		allPm.add(p);
		p = new Pm("075", "美录坦");
		allPm.add(p);
		p = new Pm("076", "轰擂金刚猩");
		allPm.add(p);
		p = new Pm("077", "霜奶仙");
		allPm.add(p);
		p = new Pm("078", "鳃鱼龙");
		allPm.add(p);
		p = new Pm("079", "铝钢龙");
		allPm.add(p);
		p = new Pm("080", "多龙巴鲁托");
		allPm.add(p);
		
		p = new Pm("901", "巴达兽");
		allPm.add(p);
		p = new Pm("902", "亚古兽");
		allPm.add(p);
		p = new Pm("903", "板栗仔");
		allPm.add(p);
		p = new Pm("904", "耀西");
		allPm.add(p);
		p = new Pm("905", "星之卡比");
		allPm.add(p);
		p = new Pm("906", "西施惠");
		allPm.add(p);
		p = new Pm("907", "森喜刚");
		allPm.add(p);
		p = new Pm("908", "索尼克");
		allPm.add(p);
		p = new Pm("909", "多啦A梦");
		allPm.add(p);
		p = new Pm("910", "菜问");
		allPm.add(p);
		p = new Pm("911", "杰瑞");
		allPm.add(p);
		p = new Pm("912", "蓝猫");
		allPm.add(p);
		p = new Pm("913", "喜羊羊");
		allPm.add(p);
		p = new Pm("914", "熊二");
		allPm.add(p);
		p = new Pm("915", "糖豆人");
		allPm.add(p);
	}
	
	public void genAllAvatar() {
		int i;
		for (i=1;i<=9;i++) {
			String s = "a00" + Integer.toString(i); 
			allAvatar.add(s);
		}
		for (i=10;i<=20;i++) {
			String s = "a0" + Integer.toString(i); 
			allAvatar.add(s);
		}
		for (i=1;i<=7;i++) {
			String s = "a90" + Integer.toString(i); 
			allAvatar.add(s);
		}
	}
	
	public List<String> getAvatars() {
		int i;
		List<String> ans = new ArrayList<>();
		List<String> st1 = new ArrayList<>();
		List<String> st2 = new ArrayList<>();
		for (i=0;i<5;i++) {
			ans.add(allAvatar.get(i));
		}
		for (i=5;i<20;i++) {
			st1.add(allAvatar.get(i));
		}
		for (i=20;i<27;i++) {
			st2.add(allAvatar.get(i));
		}
		Random rand = new Random();
		for (i=0;i<8;i++) {
			int n = st1.size();
			ans.add(st1.remove(rand.nextInt(n)));
		}
		for (i=0;i<2;i++) {
			int n = st2.size();
			ans.add(st2.remove(rand.nextInt(n)));
		}
		return ans;
	}
	
	
}
