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
		} else if (img.contentEquals("SiMaYi")) {
			c = new SiMaYi();
		} else if (img.contentEquals("MengHuo")) {
			c = new MengHuo();
		} else if (img.contentEquals("XiaoQiao")) {
			c = new XiaoQiao();
		} else if (img.contentEquals("YuanShao")) {
			c = new YuanShao();
		} else if (img.contentEquals("HanDang")) {
			c = new HanDang();
		} else if (img.contentEquals("ChengPu")) {
			c = new ChengPu();
		} else if (img.contentEquals("ZhouTai")) {
			c = new ZhouTai();
		} else if (img.contentEquals("XuChu")) {
			c = new XuChu();
		} else if (img.contentEquals("ZhangFei")) {
			c = new ZhangFei();
		} else if (img.contentEquals("WangPing")) {
			c = new WangPing();
		} else if (img.contentEquals("WeiYan")) {
			c = new WeiYan();
		} else if (img.contentEquals("JianYong")) {
			c = new JianYong();
		} else if (img.contentEquals("XuHuang")) {
			c = new XuHuang();
		} else if (img.contentEquals("ZhangLiao")) {
			c = new ZhangLiao();
		} else if (img.contentEquals("GuanYu")) {
			c = new GuanYu();
		} else if (img.contentEquals("DingFeng")) {
			c = new DingFeng();
		} else if (img.contentEquals("GuoSi")) {
			c = new GuoSi();
		} else if (img.contentEquals("JiLing")) {
			c = new JiLing();
		} else if (img.contentEquals("XuSheng")) {
			c = new XuSheng();
		} else if (img.contentEquals("ZhangHe")) {
			c = new ZhangHe();
		} else if (img.contentEquals("HuangZhong")) {
			c = new HuangZhong();
		} else if (img.contentEquals("GanNing")) {
			c = new GanNing();
		} else if (img.contentEquals("LiuBiao")) {
			c = new LiuBiao();
		} else if (img.contentEquals("DaQiao")) {
			c = new DaQiao();
		} else if (img.contentEquals("DiaoChan")) {
			c = new DiaoChan();
		} else if (img.contentEquals("ZhuGeLiang")) {
			c = new ZhuGeLiang();
		} else if (img.contentEquals("WenChou")) {
			c = new WenChou();
		} else if (img.contentEquals("CaoHong")) {
			c = new CaoHong();
		} else if (img.contentEquals("CaoPi")) {
			c = new CaoPi();
		} else if (img.contentEquals("YanLiang")) {
			c = new YanLiang();
		} else if (img.contentEquals("TaiShiCi")) {
			c = new TaiShiCi();
		} else if (img.contentEquals("ZhangZhao")) {
			c = new ZhangZhao();
		} else if (img.contentEquals("YuanShu")) {
			c = new YuanShu();
		} else if (img.contentEquals("ZhuRong")) {
			c = new ZhuRong();
		} else if (img.contentEquals("SunShangXiang")) {
			c = new SunShangXiang();
		} else if (img.contentEquals("XunYou")) {
			c = new XunYou();
		} else if (img.contentEquals("XiaHouDun")) {
			c = new XiaHouDun();
		} else if (img.contentEquals("LingTong")) {
			c = new LingTong();
		} else if (img.contentEquals("LvBu")) {
			c = new LvBu();
		} else if (img.contentEquals("XuShu")) {
			c = new XuShu();
		} else if (img.contentEquals("CaiWenJi")) {
			c = new CaiWenJi();
		} else if (img.contentEquals("MaSu")) {
			c = new MaSu();
		} else if (img.contentEquals("FaZheng")) {
			c = new FaZheng();
		} else if (img.contentEquals("YuJi")) {
			c = new YuJi();
		} else if (img.contentEquals("HuaTuo")) {
			c = new HuaTuo();
		} else if (img.contentEquals("ZuoCi")) {
			c = new ZuoCi();
		}
		
		
		else if (img.contentEquals("LiuHong")) {
			c = new LiuHong();
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
