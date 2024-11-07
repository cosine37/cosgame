package com.cosine.cosgame.oink.grove;

import java.util.ArrayList;
import java.util.List;

public class AllRes {
	
	public static List<Role> genDefaultRoles(int numPlayers){
		List<Role> roles = new ArrayList<>();
		
		if (numPlayers >= 3)
			roles.add(new Role(2,0,0,"杰弗逊·霍普","平平无奇的马车夫，爱抽印度雪茄，有时晚上也会喝醉。","男","Hope"));
		
		roles.add(new Role(3,0,0,"爱莉丝·夏朋婕","夏朋婕太太的女儿，是一位非常漂亮的姑娘，经常被房客德雷伯先生骚扰。","女","Alice"));
		roles.add(new Role(4,0,0,"夏朋婕太太","夏朋婕公寓的房东，因德雷伯先生付的房租高，即使骚扰自己的女儿也令其居住。","女","Charpentier"));
		roles.add(new Role(5,0,0,"约瑟夫·斯坦格森","德雷伯先生的秘书，掌管德雷伯先生的开支，是个沉默寡言、有涵养的人。","男","Strangerson"));
		roles.add(new Role(6,0,0,"伊诺克·J·德雷伯","夏朋婕公寓的房客，克利夫兰的有钱人，但举止粗鲁，经常醉醺醺地回家。有一枚共济会图案的戒指。","男","Drebber"));
		roles.add(new Role(7,0,0,"索耶太太","满脸皱纹、老眼昏花的老妇人。在帮助女儿寻找丢失的结婚戒指。","女","Sawyer"));
		roles.add(new Role(8,0,0,"阿瑟·夏朋婕","夏朋婕太太的儿子，爱莉丝·夏朋婕的哥哥，服役于海军，最近正好在休假，曾拿着木棍追着德雷伯先生打。","男","Arthur"));
		
		if (numPlayers >= 4)
			roles.add(new Role(Consts.POLICE1,0,0,"雷斯垂德","苏格兰场的警探，是那些蠢货中的佼佼者。执着但死板。","男","Lestrade"));
		if (numPlayers >= 5)
			roles.add(new Role(Consts.POLICE2,0,0,"托比亚斯·格雷格森","苏格兰场的警探，是那些蠢货中的佼佼者。明明这么普通但却那么自信。","男","Gregson"));
		return roles;
	}
	
	public static List<Role> genDefaultRoles(){
		return genDefaultRoles(Consts.MAXPLAYERS);
	}
	
	public static List<Role> genRoles(int roleSet, int numPlayers){
		if (roleSet == Consts.SCARLET) {
			return genDefaultRoles(numPlayers);
		} else return new ArrayList<>();
	}
	
	public static Role getRoleByNum(int roleSet, int num) {
		List<Role> roles = new ArrayList<>();
		if (roleSet == Consts.SCARLET) {
			roles = genDefaultRoles();
		}
		
		for (int i=0;i<roles.size();i++) {
			if (num == roles.get(i).getNum()) return roles.get(i);
		}
		return null;
	}
}
