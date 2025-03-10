package com.cosine.cosgame.rich.builder;

import java.util.List;

import com.cosine.cosgame.rich.Map;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.basicplaces.*;

public class MapBuilder {
	public static Map genTestMap() {
		final int height = 7;
		final int width = 10;
		final int n = (height+width-2)*2;
		
		Map map = new Map();
		map.setHeight(height);
		map.setWidth(width);
		int i;
		for (i=0;i<n;i++){
			Place p = new Empty(i, "地点"+i);
			if (i == 0) {
				p = new StartPoint(i, "游客中心");
				p.setDesc("经过或停留此处领取$2000");
				p.setLandMsg("将会获得$2000");
			} else if (i == 7) {
				p = new Tax(i, "所得税", 2000);
			} else if (i == 20) {
				p = new Tax(i, "奢侈税", 1000);
			} else if (i == 15) {
				p = new Empty(i, "免费停车场");
				p.setLandMsg("无事发生");
			}
			map.addPlace(p);
		}
		
		return map;
	}
}
