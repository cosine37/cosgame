package com.cosine.cosgame.threechaodoms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.threechaodoms.base.*;

public class AllRes {
	List<Card> base;
	
	public AllRes() {
		genBase();
	}
	
	public List<Card> getDeck(){
		List<Card> cards = new ArrayList<>();
		List<Card> deck = new ArrayList<>();
		int i;
		for (i=0;i<base.size();i++) {
			cards.add(base.get(i));
		}
		while (cards.size()>0) {
			Random rand = new Random();
			int x = rand.nextInt(cards.size());
			Card c = cards.remove(x);
			deck.add(c);
		}
		return deck;
		//return base;
	}
	
	public void genBase() {
		base = new ArrayList<>();
		base.add(new YanLiang());
		base.add(new CaoPi());
		base.add(new CaoHong());
		base.add(new WenChou());
		base.add(new ZhuGeLiang());
		base.add(new DiaoChan());
		base.add(new DaQiao());
		base.add(new LiuBiao());
		base.add(new GanNing());
		base.add(new WeiYan());
		base.add(new ZhangHe());
		base.add(new XuSheng());
		base.add(new JiLing());
		base.add(new GuoSi());
		base.add(new DingFeng());
		base.add(new GuanYu());
		base.add(new ZhangLiao());
		base.add(new XuHuang());
		base.add(new JianYong());
		base.add(new HuangZhong());
		base.add(new WangPing());
		base.add(new ZhangFei());
		base.add(new XuChu());
		base.add(new ZhouTai());
		base.add(new ChengPu());
		base.add(new HanDang());
		base.add(new YuanShao());
		base.add(new XiaoQiao());
		base.add(new MengHuo());
		base.add(new SiMaYi());
		base.add(new ChengYu());
		base.add(new LiaoHua());
		base.add(new LvMeng());
		base.add(new SunQuan());
		base.add(new GongSunZan());
		base.add(new LiJue());
		base.add(new DongZhuo());
		base.add(new LuSu());
		base.add(new DianWei());
		base.add(new MiZhu());
		base.add(new SunCe());
		base.add(new YuJin());
		base.add(new YueJin());
		base.add(new CaoRen());
		base.add(new JiaXu());
		base.add(new MaChao());
		base.add(new ZhaoYun());
		base.add(new CaoCao());
		base.add(new LiuBei());
	}
}
