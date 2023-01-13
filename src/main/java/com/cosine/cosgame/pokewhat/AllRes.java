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
		final int XS = 50;
		final int S = 80;
		final int M = 120;
		final int L = 180;
		final int XL = 250;
		p = new Pm("001", "妙蛙种子", S);
		allPm.add(p);
		p = new Pm("002", "小火龙", S);
		allPm.add(p);
		p = new Pm("003", "杰尼龟", S);
		allPm.add(p);
		p = new Pm("004", "皮卡丘", S);
		allPm.add(p);
		p = new Pm("005", "皮皮", S);
		allPm.add(p);
		p = new Pm("006", "胖丁", S);
		allPm.add(p);
		p = new Pm("007", "喵喵", S);
		allPm.add(p);
		p = new Pm("008", "阿柏蛇", M);
		allPm.add(p);
		p = new Pm("009", "瓦斯弹", M);
		allPm.add(p);
		p = new Pm("010", "伊布", S);
		allPm.add(p);
		p = new Pm("011", "绿毛虫", XS);
		allPm.add(p);
		p = new Pm("012", "小磁怪", S);
		allPm.add(p);
		p = new Pm("013", "耿鬼", L);
		allPm.add(p);
		p = new Pm("014", "呆呆兽", M);
		allPm.add(p);
		p = new Pm("015", "可达鸭", S);
		allPm.add(p);
		p = new Pm("016", "鲤鱼王", S);
		allPm.add(p);
		p = new Pm("017", "闪光母怪力", L);
		allPm.add(p);
		p = new Pm("018", "大嘴蝠", L);
		allPm.add(p);
		p = new Pm("019", "三地鼠", XL);
		allPm.add(p);
		p = new Pm("020", "卡比兽", XL);
		allPm.add(p);
		p = new Pm("021", "快龙", XL);
		allPm.add(p);
		p = new Pm("022", "六尾", M);
		allPm.add(p);
		p = new Pm("023", "椰蛋树", XL);
		allPm.add(p);
		p = new Pm("024", "小火马", M);
		allPm.add(p);
		p = new Pm("025", "超梦", XL);
		allPm.add(p);
		p = new Pm("026", "刺刺耳皮丘", XS);
		allPm.add(p);
		p = new Pm("027", "波克比", S);
		allPm.add(p);
		p = new Pm("028", "玛力露", S);
		allPm.add(p);
		p = new Pm("029", "长尾怪手", M);
		allPm.add(p);
		p = new Pm("030", "电龙", L);
		allPm.add(p);
		p = new Pm("031", "月亮伊布", M);
		allPm.add(p);
		p = new Pm("032", "果然翁", L);
		allPm.add(p);
		p = new Pm("033", "巨钳螳螂", L);
		allPm.add(p);
		p = new Pm("034", "梦妖", M);
		allPm.add(p);
		p = new Pm("035", "班基拉斯", XL);
		allPm.add(p);
		p = new Pm("036", "沼跃鱼", M);
		allPm.add(p);
		p = new Pm("037", "沙奈朵", L);
		allPm.add(p);
		p = new Pm("038", "沙漠蜻蜓", L);
		allPm.add(p);
		p = new Pm("039", "阿勃梭鲁", L);
		allPm.add(p);
		p = new Pm("040", "美纳斯", L);
		allPm.add(p);
		p = new Pm("041", "可可多拉", S);
		allPm.add(p);
		p = new Pm("042", "脱壳忍者", M);
		allPm.add(p);
		p = new Pm("043", "盖欧卡", XL);
		allPm.add(p);
		p = new Pm("044", "固拉多", XL);
		allPm.add(p);
		p = new Pm("045", "裂空坐", XL);
		allPm.add(p);
		p = new Pm("046", "路卡利欧", L);
		allPm.add(p);
		p = new Pm("047", "玛狃拉", L);
		allPm.add(p);
		p = new Pm("048", "天蝎王", L);
		allPm.add(p);
		p = new Pm("049", "烈咬陆鲨", XL);
		allPm.add(p);
		p = new Pm("050", "阿尔宙斯", XL);
		allPm.add(p);
		p = new Pm("051", "比克提尼", S);
		allPm.add(p);
		p = new Pm("052", "滑滑小子", S);
		allPm.add(p);
		p = new Pm("053", "索罗亚克", L);
		allPm.add(p);
		p = new Pm("054", "达摩狒狒", L);
		allPm.add(p);
		p = new Pm("055", "流氓鳄", L);
		allPm.add(p);
		p = new Pm("056", "坚果哑铃", L);
		allPm.add(p);
		p = new Pm("057", "双斧战龙", XL);
		allPm.add(p);
		p = new Pm("058", "水晶灯火灵", M);
		allPm.add(p);
		p = new Pm("059", "火神蛾", L);
		allPm.add(p);
		p = new Pm("060", "土地云", L);
		allPm.add(p);
		p = new Pm("061", "甲贺忍蛙", XL);
		allPm.add(p);
		p = new Pm("062", "顽皮熊猫", M);
		allPm.add(p);
		p = new Pm("063", "仙子伊布", M);
		allPm.add(p);
		p = new Pm("064", "永恒花叶蒂", M);
		allPm.add(p);
		p = new Pm("065", "蒂安希", L);
		allPm.add(p);
		p = new Pm("066", "木木枭", S);
		allPm.add(p);
		p = new Pm("067", "炽焰咆哮虎", XL);
		allPm.add(p);
		p = new Pm("068", "猫鼬探长", M);
		allPm.add(p);
		p = new Pm("069", "鬃岩狼人", L);
		allPm.add(p);
		p = new Pm("070", "穿着熊", L);
		allPm.add(p);
		p = new Pm("071", "谜拟Q", M);
		allPm.add(p);
		p = new Pm("072", "费洛美螂", L);
		allPm.add(p);
		p = new Pm("073", "毒贝比", M);
		allPm.add(p);
		p = new Pm("074", "玛机雅娜", L);
		allPm.add(p);
		p = new Pm("075", "美录坦", S);
		allPm.add(p);
		p = new Pm("076", "轰擂金刚猩", L);
		allPm.add(p);
		p = new Pm("077", "霜奶仙", M);
		allPm.add(p);
		p = new Pm("078", "鳃鱼龙", XL);
		allPm.add(p);
		p = new Pm("079", "铝钢龙", XL);
		allPm.add(p);
		p = new Pm("080", "多龙巴鲁托", XL);
		allPm.add(p);
		p = new Pm("081", "买噶袋兽", L);
		allPm.add(p);
		p = new Pm("082", "拉普拉斯", XL);
		allPm.add(p);
		p = new Pm("083", "百变怪", S);
		allPm.add(p);
		p = new Pm("084", "大岩蛇", XL);
		allPm.add(p);
		p = new Pm("085", "树才怪", L);
		allPm.add(p);
		p = new Pm("086", "请假王", XL);
		allPm.add(p);
		p = new Pm("087", "向尾喵", S);
		allPm.add(p);
		p = new Pm("088", "帕奇利兹", S);
		allPm.add(p);
		p = new Pm("089", "火衣机", S);
		allPm.add(p);
		p = new Pm("090", "洗衣机", S);
		allPm.add(p);
		p = new Pm("091", "泡沫栗鼠", S);
		allPm.add(p);
		p = new Pm("092", "风妖精", M);
		allPm.add(p);
		p = new Pm("093", "三首恶龙", L);
		allPm.add(p);
		p = new Pm("094", "美洛耶塔", M);
		allPm.add(p);
		p = new Pm("095", "基格尔德", XL);
		allPm.add(p);
		p = new Pm("096", "卡璞·呜呜", L);
		allPm.add(p);
		p = new Pm("097", "纸御剑", S);
		allPm.add(p);
		p = new Pm("098", "狐大盗", M);
		allPm.add(p);
		p = new Pm("099", "冰砌鹅", L);
		allPm.add(p);
		p = new Pm("100", "熊徒弟", M);
		allPm.add(p);
		
		p = new Pm("101", "格洛丘", L);
		allPm.add(p);
		p = new Pm("102", "锚尾鲨", L);
		allPm.add(p);
		p = new Pm("103", "火球海狮", M);
		allPm.add(p);
		p = new Pm("104", "电脑斧", L);
		allPm.add(p);
		p = new Pm("105", "冰狼人", M);
		allPm.add(p);
		p = new Pm("106", "麒麟麒", XL);
		allPm.add(p);
		p = new Pm("107", "异种大舌贝", M);
		allPm.add(p);
		p = new Pm("108", "多边兽1.5型", S);
		allPm.add(p);
		p = new Pm("109", "巫咒稻人", S);
		allPm.add(p);
		p = new Pm("110", "铃铛喵", M);
		allPm.add(p);
		
		p = new Pm("111", "菊草叶", S);
		allPm.add(p);
		p = new Pm("112", "火球鼠", S);
		allPm.add(p);
		p = new Pm("113", "小锯鳄", S);
		allPm.add(p);
		p = new Pm("114", "圈圈熊", S);
		allPm.add(p);
		p = new Pm("115", "煤炭龟", M);
		allPm.add(p);
		p = new Pm("116", "咕妞妞", S);
		allPm.add(p);
		p = new Pm("117", "长耳兔", L);
		allPm.add(p);
		p = new Pm("118", "吉利蛋", L);
		allPm.add(p);
		p = new Pm("119", "小猫怪", S);
		allPm.add(p);
		p = new Pm("120", "谢米", M);
		allPm.add(p);
		p = new Pm("121", "龙头地鼠", M);
		allPm.add(p);
		p = new Pm("122", "劈斩司令", L);
		allPm.add(p);
		p = new Pm("123", "摔角鹰人", L);
		allPm.add(p);
		p = new Pm("124", "托戈德玛尔", S);
		allPm.add(p);
		p = new Pm("125", "莫鲁贝可", S);
		allPm.add(p);
		p = new Pm("126", "梦幻", M);
		allPm.add(p);
		p = new Pm("127", "洛奇亚", XL);
		allPm.add(p);
		p = new Pm("128", "凤王", XL);
		allPm.add(p);
		p = new Pm("129", "时拉比", S);
		allPm.add(p);
		p = new Pm("130", "雷吉艾勒奇", XL);
		allPm.add(p);
		p = new Pm("131", "三位一体酋雷姆", XL);
		allPm.add(p);
		
		p = new Pm("132", "海星星", S);
		allPm.add(p);
		p = new Pm("133", "化石翼龙", XL);
		allPm.add(p);
		p = new Pm("134", "卡蒂狗", M);
		allPm.add(p);
		p = new Pm("135", "乌波", S);
		allPm.add(p);
		p = new Pm("136", "壶壶", M);
		allPm.add(p);
		p = new Pm("137", "幕下力士", S);
		allPm.add(p);
		p = new Pm("138", "彷徨夜灵", M);
		allPm.add(p);
		p = new Pm("139", "席多蓝恩", XL);
		allPm.add(p);
		p = new Pm("140", "铁蚁", M);
		allPm.add(p);
		p = new Pm("141", "烈箭鹰", L);
		allPm.add(p);
		p = new Pm("142", "黏美龙", XL);
		allPm.add(p);
		p = new Pm("143", "铁火辉夜", XL);
		allPm.add(p);
		p = new Pm("144", "爆肌蚊", XL);
		allPm.add(p);
		p = new Pm("145", "钢铠鸦", L);
		allPm.add(p);
		p = new Pm("146", "颤弦蝾螈", L);
		allPm.add(p);
		p = new Pm("147", "长毛巨魔", L);
		allPm.add(p);
		p = new Pm("148", "布拨", S);
		allPm.add(p);
		p = new Pm("149", "红莲铠骑", L);
		allPm.add(p);
		p = new Pm("150", "苍炎刃鬼", L);
		allPm.add(p);
		p = new Pm("151", "电肚蛙", M);
		allPm.add(p);
		p = new Pm("152", "浩大鲸", XL);
		allPm.add(p);
		p = new Pm("153", "故勒顿", XL);
		allPm.add(p);
		p = new Pm("154", "密勒顿", XL);
		allPm.add(p);
		
		p = new Pm("901", "巴达兽", S);
		allPm.add(p);
		p = new Pm("902", "亚古兽", M);
		allPm.add(p);
		p = new Pm("903", "板栗仔", S);
		allPm.add(p);
		p = new Pm("904", "耀西", M);
		allPm.add(p);
		p = new Pm("905", "星之卡比", S);
		allPm.add(p);
		p = new Pm("906", "西施惠", M);
		allPm.add(p);
		p = new Pm("907", "森喜刚", L);
		allPm.add(p);
		p = new Pm("908", "索尼克", M);
		allPm.add(p);
		p = new Pm("909", "多啦A梦", M);
		allPm.add(p);
		p = new Pm("910", "菜问", M);
		allPm.add(p);
		p = new Pm("911", "杰瑞", S);
		allPm.add(p);
		p = new Pm("912", "蓝猫", M);
		allPm.add(p);
		p = new Pm("913", "喜羊羊", M);
		allPm.add(p);
		p = new Pm("914", "熊二", XL);
		allPm.add(p);
		p = new Pm("915", "糖豆人", M);
		allPm.add(p);
		p = new Pm("916", "尼莫", S);
		allPm.add(p);
		p = new Pm("917", "瓦力", M);
		allPm.add(p);
		p = new Pm("918", "米奇", M);
		allPm.add(p);
		p = new Pm("919", "仙人掌兽", XL);
		allPm.add(p);
		p = new Pm("920", "V仔兽", M);
		allPm.add(p);
	}
	
	public void genAllAvatar() {
		int i;
		for (i=1;i<=9;i++) {
			String s = "a00" + Integer.toString(i); 
			allAvatar.add(s);
		}
		for (i=10;i<=37;i++) {
			String s = "a0" + Integer.toString(i); 
			allAvatar.add(s);
		}
		for (i=1;i<=9;i++) {
			String s = "a90" + Integer.toString(i); 
			allAvatar.add(s);
		}
		for (i=10;i<=15;i++) {
			String s = "a9" + Integer.toString(i); 
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
		for (i=5;i<37;i++) {
			st1.add(allAvatar.get(i));
		}
		for (i=37;i<52;i++) {
			st2.add(allAvatar.get(i));
		}
		Random rand = new Random();
		for (i=0;i<13;i++) {
			int n = st1.size();
			ans.add(st1.remove(rand.nextInt(n)));
		}
		for (i=0;i<3;i++) {
			int n = st2.size();
			ans.add(st2.remove(rand.nextInt(n)));
		}
		return ans;
	}
	
	public List<Pm> getAllPm() {
		return allPm;
	}
}
