package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.rich.basicplaces.*;

public class AllRes {
	public static List<Place> genTestMap(){
		int i;
		List<Place> map = new ArrayList<>();
		
		Place sp = new StartPoint(0, "发薪日");
		Place prev = sp;
		map.add(sp);
		
		for (i=1;i<10;i++) {
			Place e = new Empty(i, "空格" + i);
			e.setPrev(prev);
			prev.setNext(e);
			map.add(e);
			
			prev = e;
		}
		
		// Set the last cell
		prev.setNext(sp);
		sp.setPrev(prev);
		
		return map;
	};
}
