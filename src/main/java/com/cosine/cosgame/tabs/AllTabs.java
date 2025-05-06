package com.cosine.cosgame.tabs;

import java.util.ArrayList;
import java.util.List;

public class AllTabs {
	public static List<Tab> getAllTabs() {
		List<Tab> allTabs = new ArrayList<>();
		allTabs.add(new Tab("主页", "index", "rgb(0,100,100)"));
		allTabs.add(new Tab("富饶龙城", "citadels", "rgb(0,0,102)"));
		allTabs.add(new Tab("不了谢谢", "nothanks", "rgb(12,137,24)"));
		allTabs.add(new Tab("宝可纳尼", "pokewhat", "rgb(249,51,24)"));
		allTabs.add(new Tab("盗墓惊魂", "gravepsycho", "rgb(23,44,60)", "rgb(230,179,61)"));
		allTabs.add(new Tab("狼人不杀", "onenight", "rgb(126,33,18)"));
		allTabs.add(new Tab("雕梁画栋", "architect", "rgb(160,32,240)"));
		//allTabs.add(new Tab("水泊传说", "marshbros", "rgb(218,165,32)"));
		allTabs.add(new Tab("扑克世界", "pokerworld", "goldenrod"));
		//allTabs.add(new Tab("花园战争", "gardenwar", "darkgreen"));
		allTabs.add(new Tab("抽象小品", "oink", "rgb(0,150,255)"));
		allTabs.add(new Tab("富豪游戏", "rich", "darkgreen"));
		allTabs.add(new Tab("回转寿司", "sushi", "rgb(10,34,0)"));
		return allTabs;
	}
}
