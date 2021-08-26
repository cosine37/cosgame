package com.cosine.cosgame.marshbros;

import com.cosine.cosgame.marshbros.official.*;

public class CardFactory {
	public static Card createCard(String img) {
		Card c = new Card();
		
		if (img.contentEquals("XieZhen")) {
			c = new XieZhen();
		} else if (img.contentEquals("XieBao")) {
			c = new XieBao();
		} else if (img.contentEquals("GuanSheng")) {
			c = new GuanSheng();
		} else if (img.contentEquals("LiTianRun")) {
			c = new LiTianRun();
		} else if (img.contentEquals("WangLun")) {
			c = new WangLun();
		} else if (img.contentEquals("ChaoGai")) {
			c = new ChaoGai();
		} else if (img.contentEquals("Tiger")) {
			c = new Tiger();
		} else if (img.contentEquals("MuHong")) {
			c = new MuHong();
		} else if (img.contentEquals("MuChun")) {
			c = new MuChun();
		} else if (img.contentEquals("LiKui")) {
			c = new LiKui();
		} else if (img.contentEquals("HuYanZhuo")) {
			c = new HuYanZhuo();
		} else if (img.contentEquals("ZhuTong")) {
			c = new ZhuTong();
		} else if (img.contentEquals("LeiHeng")) {
			c = new LeiHeng();
		} else if (img.contentEquals("HuaRong")) {
			c = new HuaRong();
		} else if (img.contentEquals("ShiWenGong")) {
			c = new ShiWenGong();
		} else if (img.contentEquals("AnDaoQuan")) {
			c = new AnDaoQuan();
		} else if (img.contentEquals("HuangFuDuan")) {
			c = new HuangFuDuan();
		} else if (img.contentEquals("ShiQian")) {
			c = new ShiQian();
		} else if (img.contentEquals("DuanJingZhu")) {
			c = new DuanJingZhu();
		}
		return c;
	}
}
