package com.cosine.cosgame.oink.west;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AllRes {
	public static List<Card> genDeck(){
		List<Card> deck = new ArrayList<>();
		deck.add(new Card(15,"观世音菩萨","如来佛祖的得意徒弟之一，取经事业的具体组织者，数次拯救取经团队。"));
		deck.add(new Card(14,"红孩儿","号称圣婴大王，牛魔王与铁扇公主之子，曾在火焰山修炼三百年，炼成了三昧真火。"));
		deck.add(new Card(13,"孙悟空","天生地产的石猴，唐僧的大徒弟，神通广大但经常张口就来。最喜欢别人称其为弼马温。"));
		deck.add(new Card(12,"牛魔王","自称平天大圣，孙悟空的结拜兄弟，铁扇公主的丈夫，红孩儿的父亲。"));
		deck.add(new Card(11,"哪吒","托塔天王李靖与殷夫人的第三太子，法身形态是三头六臂。持有数种兵器。"));
		deck.add(new Card(10,"铁扇公主","牛魔王的妻子，因宝物芭蕉扇可以降雨，百姓因而把她拜为铁扇仙。"));
		deck.add(new Card(9,"九头虫","入赘碧波潭的万圣龙王家中，后盗走祭赛国金光寺塔顶的舍利。"));
		deck.add(new Card(8,"猪八戒","唐僧的二徒弟，原为天蓬元帅，错投猪胎。好吃懒做，爱分行李，最喜欢别人称其为呆子。"));
		deck.add(new Card(7,"如意真仙","红孩儿的叔叔，持一把如意钩子，占领女儿国南面的落胎泉。"));
		deck.add(new Card(6,"万圣公主","碧波潭万圣龙王之女，与九头虫合谋盗取祭赛国金光寺塔顶的舍利。"));
		deck.add(new Card(5,"沙僧","唐僧的三徒弟，卷帘大将下凡，负责看行李。常说“师父又被妖怪给抓走啦”，“大师兄说的对呀”。"));
		deck.add(new Card(4,"玉面狐狸","牛魔王的小妾，住在积雷山摩云洞。"));
		deck.add(new Card(3,"白龙马","西海龙王敖闰的第三个儿子，几乎都是作为唐僧的坐骑存在。撒泡尿就治好了朱紫国国王的玉玉症。"));
		deck.add(new Card(2,"女儿国国王","西梁女国的国王，姓名不详，对唐僧一见钟情，想招唐僧为王。"));
		deck.add(new Card(1,"唐僧","金蝉子转世，取经团队的师父，顽固迂腐，不明事理，爱念紧箍咒，还老被抓，是团队的拖油瓶。"));
		return deck;
	}
	
	public static List<Card> genShuffledDeck(){
		List<Card> td = genDeck();
		List<Card> deck = new ArrayList<>();
		Random rand = new Random();
		while (td.size()>0) {
			int x = rand.nextInt(td.size());
			Card c = td.remove(x);
			deck.add(c);
		}
		return deck;
	}
}
