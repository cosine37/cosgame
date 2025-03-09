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
			map.addPlace(p);
		}
		
		return map;
	}
}
