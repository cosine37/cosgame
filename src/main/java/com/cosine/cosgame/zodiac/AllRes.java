package com.cosine.cosgame.zodiac;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.zodiac.roles.*;

public class AllRes {
	public static List<Role> genRoles(int numPlayers) {
		List<Role> rolesToUse = new ArrayList<>();
		
		int i;
		for (i=0;i<numPlayers;i++) {
			Role r = RoleFactory.createRole(i);
			rolesToUse.add(r);
		}
		
		return rolesToUse;
	}
	
	public static List<Zodiac> genZodiacs(){
		int i;
		String[] names = {"鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","苟","猪"};
		List<Zodiac> zodiacs = new ArrayList<>();
		for (i=0;i<names.length;i++) {
			Zodiac z = new Zodiac();
			z.setName(names[i]);
			z.setNum(i+1);
			zodiacs.add(z);
		}
		return zodiacs;
	}
}
