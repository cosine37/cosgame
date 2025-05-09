package com.cosine.cosgame.oink.flip7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Factory {
	public static Card genCard(int num) {
		List<String> names = new ArrayList<>(Arrays.asList("矿泉水","冰美式","汽水","甜甜圈","纸杯蛋糕","苹果派","奶酪通心粉","炸鸡","玉米卷","热狗","汉堡","薯条","披萨"));
		Card c = new Card();
		if (num < names.size()) {
			c = new Card(num, names.get(num));
		}
		return c;
	}
}
