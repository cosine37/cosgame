package com.cosine.cosgame.threechaodoms;

import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.threechaodoms.base.*;

public class CardFactory {
	public static Card makeCard(String img) {
		Card c = new Card();
		if (img.contentEquals("blankspace")) {
			c = new BlankSpaceCard();
		} else if (img.contentEquals("LiuBei")) {
			c = new LiuBei();
		} else if (img.contentEquals("CaoCao")) {
			c = new CaoCao();
		} else if (img.contentEquals("ZhaoYun")) {
			c = new ZhaoYun();
		} else if (img.contentEquals("MaChao")) {
			c = new MaChao();
		} else if (img.contentEquals("CaoRen")) {
			c = new CaoRen();
		} else if (img.contentEquals("JiaXu")) {
			c = new JiaXu();
		} else if (img.contentEquals("DianWei")) {
			c = new DianWei();
		} else if (img.contentEquals("MiZhu")) {
			c = new MiZhu();
		} else if (img.contentEquals("SunCe")) {
			c = new SunCe();
		} else if (img.contentEquals("YuJin")) {
			c = new YuJin();
		} else if (img.contentEquals("YueJin")) {
			c = new YueJin();
		} else if (img.contentEquals("LuSu")) {
			c = new LuSu();
		} else if (img.contentEquals("LvMeng")) {
			c = new LvMeng();
		} else if (img.contentEquals("SunQuan")) {
			c = new SunQuan();
		} else if (img.contentEquals("DongZhuo")) {
			c = new DongZhuo();
		} else if (img.contentEquals("GongSunZan")) {
			c = new GongSunZan();
		} else if (img.contentEquals("LiJue")) {
			c = new LiJue();
		} else if (img.contentEquals("ChengYu")) {
			c = new ChengYu();
		} else if (img.contentEquals("LiaoHua")) {
			c = new LiaoHua();
		}
		return c;
	}
	
	public static Card makeCard(Document doc) {
		String img = doc.getString("img");
		//int where = doc.getInteger("where", -1);
		List<Integer> extraBits = (List<Integer>) doc.get("extraBits");
		Card c = makeCard(img);
		//c.setWhere(where);
		c.setExtraBits(extraBits);
		return c;
	}
}
