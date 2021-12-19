package com.cosine.cosgame.threechaodoms;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.base.*;

public class AllRes {
	List<Card> base;
	
	public AllRes() {
		genBase();
	}
	
	public List<Card> getDeck(){
		return base;
	}
	
	public void genBase() {
		base = new ArrayList<>();
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
