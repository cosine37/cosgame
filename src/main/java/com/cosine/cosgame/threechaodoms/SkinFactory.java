package com.cosine.cosgame.threechaodoms;

import org.bson.Document;

import com.cosine.cosgame.threechaodoms.shop.Transaction;

public class SkinFactory {
	public static Skin makeSkin(int id) {
		Skin s = new Skin();
		s.setId(id);
		if (id == 1) {
			s.setOriginalImg("DiaoChan");
			s.setNewImg("DiaoChan_01");
			s.setSkinName("貂蝉");
			s.setTitle("絕世的舞姬");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 2) {
			s.setOriginalImg("SunCe");
			s.setNewImg("SunCe_01");
			s.setSkinName("孫笨");
			s.setTitle("五四三二零");
			s.setPrice(new Transaction(Transaction.MONEY, -188, s));
		} else if (id == 3) {
			s.setOriginalImg("ZhuGeLiang");
			s.setNewImg("ZhuGeLiang_01");
			s.setSkinName("諸葛亮");
			s.setTitle("臥龍");
			s.setPrice(new Transaction(Transaction.MONEY, -188, s));
		} else if (id == 4) {
			s.setOriginalImg("CaoCao");
			s.setNewImg("CaoCao_01");
			s.setSkinName("曹操");
			s.setTitle("魏武帝");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 5) {
			s.setOriginalImg("LiuBei");
			s.setNewImg("LiuBei_01");
			s.setSkinName("劉備");
			s.setTitle("亂世的梟雄");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 6) {
			s.setOriginalImg("XuHuang");
			s.setNewImg("XuHuang_02");
			s.setSkinName("徐晃");
			s.setTitle("徐淑娟");
			s.setPrice(new Transaction(Transaction.MONEY, -188, s));
		} else if (id == 7) {
			s.setOriginalImg("SiMaYi");
			s.setNewImg("SiMaYi_02");
			s.setSkinName("司馬懿");
			s.setTitle("蘇大強");
			s.setPrice(new Transaction(Transaction.MONEY, -188, s));
		} else if (id == 8) {
			s.setOriginalImg("ZhaoYun");
			s.setNewImg("ZhaoYun_02");
			s.setSkinName("趙雲");
			s.setTitle("尼古拉斯趙四");
			s.setPrice(new Transaction(Transaction.MONEY, -188, s));
		} else if (id == 9) {
			s.setOriginalImg("DianWei");
			s.setNewImg("DianWei_02");
			s.setSkinName("典韦");
			s.setTitle("闪电侠九爷");
			s.setPrice(new Transaction(Transaction.MONEY, -288, s));
		} else if (id == 10) {
			s.setOriginalImg("ZhangFei");
			s.setNewImg("ZhangFei_02");
			s.setSkinName("張飛");
			s.setTitle("黑旋風");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 11) {
			s.setOriginalImg("HuangZhong");
			s.setNewImg("HuangZhong_03");
			s.setSkinName("黄忠");
			s.setTitle("卡梅倫約翰遜");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 12) {
			s.setOriginalImg("SunShangXiang");
			s.setNewImg("SunShangXiang_03");
			s.setSkinName("孫尚香");
			s.setTitle("夏紫薇");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 13) {
			s.setOriginalImg("SunCe");
			s.setNewImg("SunCe_02");
			s.setSkinName("孫策");
			s.setTitle("盜聖白玉湯");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 14) {
			s.setOriginalImg("GanNing");
			s.setNewImg("GanNing_02");
			s.setSkinName("甘寧");
			s.setTitle("獅子王辛巴");
			s.setPrice(new Transaction(Transaction.MONEY, -188, s));
		} else if (id == 15) {
			s.setOriginalImg("LuSu");
			s.setNewImg("LuSu_02");
			s.setSkinName("魯肅");
			s.setTitle("專業吊喪");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 16) {
			s.setOriginalImg("XiaoQiao");
			s.setNewImg("XiaoQiao_02");
			s.setSkinName("小喬");
			s.setTitle("黑澤志玲");
			s.setPrice(new Transaction(Transaction.MONEY, -188, s));
		} else if (id == 17) {
			s.setOriginalImg("YuanShao");
			s.setNewImg("YuanShao_01");
			s.setSkinName("袁紹");
			s.setTitle("放箭的大嘴");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 18) {
			s.setOriginalImg("DongZhuo");
			s.setNewImg("DongZhuo_02");
			s.setSkinName("董卓");
			s.setTitle("咱家命苦");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 19) {
			s.setOriginalImg("YuanShu");
			s.setNewImg("YuanShu_02");
			s.setSkinName("袁術");
			s.setTitle("高速公路");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 20) {
			s.setOriginalImg("TaiShiCi");
			s.setNewImg("TaiShiCi_01");
			s.setSkinName("太史慈");
			s.setTitle("篤烈之士");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 21) {
			s.setOriginalImg("LvMeng");
			s.setNewImg("LvMeng_01");
			s.setSkinName("吕蒙");
			s.setTitle("白衣渡江");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 22) {
			s.setOriginalImg("LuSu");
			s.setNewImg("LuSu_03");
			s.setSkinName("魯肅");
			s.setTitle("魯大師");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 23) {
			s.setOriginalImg("ZhaoYun");
			s.setNewImg("ZhaoYun_01");
			s.setSkinName("趙雲");
			s.setTitle("少年將軍");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 24) {
			s.setOriginalImg("GuanYu");
			s.setNewImg("GuanYu_01");
			s.setSkinName("關羽");
			s.setTitle("美髯公");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 25) {
			s.setOriginalImg("SunQuan");
			s.setNewImg("SunQuan_01");
			s.setSkinName("孫權");
			s.setTitle("年輕的賢君");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 26) {
			s.setOriginalImg("ZhangLiao");
			s.setNewImg("ZhangLiao_01");
			s.setSkinName("張遼");
			s.setTitle("前將軍");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 27) {
			s.setOriginalImg("MaChao");
			s.setNewImg("MaChao_02");
			s.setSkinName("馬超");
			s.setTitle("馬四連");
			s.setPrice(new Transaction(Transaction.MONEY, -188, s));
		} else if (id == 28) {
			s.setOriginalImg("LvBu");
			s.setNewImg("LvBu_02");
			s.setSkinName("吕布");
			s.setTitle("步驚雲");
			s.setPrice(new Transaction(Transaction.MONEY, -188, s));
		} else if (id == 29) {
			s.setOriginalImg("XiaHouDun");
			s.setNewImg("XiaHouDun_01");
			s.setSkinName("夏侯惇");
			s.setTitle("獨眼的羅剎");
			s.setPrice(new Transaction(Transaction.MONEY, -188, s));
		} else if (id == 30) {
			s.setOriginalImg("ZhuGeLiang");
			s.setNewImg("ZhuGeLiang_03");
			s.setSkinName("諸葛亮");
			s.setTitle("智揮猩");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 31) {
			s.setOriginalImg("HuaTuo");
			s.setNewImg("HuaTuo_02");
			s.setSkinName("華佗");
			s.setTitle("萬受無疆");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		} else if (id == 32) {
			s.setOriginalImg("YuJi");
			s.setNewImg("YuJi_01");
			s.setSkinName("于吉");
			s.setTitle("猜猜看吶");
			s.setPrice(new Transaction(Transaction.MONEY, -88, s));
		}
	
		
		else {
			s = null;
		}
		return s;
	}
	
	public static Skin makeSkin(Document doc) {
		int id = doc.getInteger("id", 0);
		boolean inUse = doc.getBoolean("inUse", false);
		Skin s = makeSkin(id);
		s.setInUse(inUse);
		return s;
	}
}
