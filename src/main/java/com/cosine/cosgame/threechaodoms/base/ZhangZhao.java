package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class ZhangZhao extends Card {
	public ZhangZhao() {
		name = "張昭";
		courtesy = "子布";
		img = "ZhangZhao";
		title = "內事不決問";
		faction = Consts.WU;
		
		desc = "王道-1或霸道+1。从隐居堆或墓地摸2张牌。";
		
		playType = Consts.CHOOSETWO;
		options.add("王道-1");
		options.add("霸道+1");
		
		
	}
	
	public List<String> getOptions2Display() {
		options2 = new ArrayList<>();
		if (board.getExile().size() == 0) {
			if (board.getTomb().size() == 0) {
				options2.add("隐居堆和墓地都没有牌，无事发生");
			} else if (board.getTomb().size() == 1) {
				options2.add("从墓地摸1张");
			} else {
				options2.add("从墓地摸2张");
			}
		} else if (board.getExile().size() == 1) {
			if (board.getTomb().size() == 0) {
				options2.add("从隐居堆摸1张");
			} else if (board.getTomb().size() == 1) {
				options2.add("从隐居堆和墓地各摸1张");
			} else {
				options2.add("从隐居堆和墓地各摸1张");
				options2.add("从墓地摸2张");
			}
		} else {
			if (board.getTomb().size() == 0) {
				options2.add("从隐居堆摸2张");
			} else if (board.getTomb().size() == 1) {
				options2.add("从隐居堆摸2张");
				options2.add("从隐居堆和墓地各摸1张");
			} else {
				options2.add("从隐居堆摸2张");
				options2.add("从隐居堆和墓地各摸1张");
				options2.add("从墓地摸2张");
			}
		}
		
		return options2;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 4) {
			int option = targets.get(0);
			int option2 = targets.get(4);
			
			if (option == 0) {
				board.moveHan(-1);
			} else if (option == 1) {
				board.moveWei(1);
			}
			
			if (board.getExile().size() == 0) {
				if (board.getTomb().size() == 0) {
					
				} else if (board.getTomb().size() == 1) {
					Card c = board.getTomb().remove(0);
					player.getHand().add(c);
				} else {
					Card c1 = board.getTomb().remove(0);
					Card c2 = board.getTomb().remove(0);
					player.getHand().add(c1);
					player.getHand().add(c2);
				}
			} else if (board.getExile().size() == 1) {
				if (board.getTomb().size() == 0) {
					Card c = board.getExile().remove(0);
					player.getHand().add(c);
				} else if (board.getTomb().size() == 1) {
					Card c1 = board.getExile().remove(0);
					Card c2 = board.getTomb().remove(0);
					player.getHand().add(c1);
					player.getHand().add(c2);
				} else {
					if (option2 == 0) {
						Card c1 = board.getExile().remove(0);
						Card c2 = board.getTomb().remove(0);
						player.getHand().add(c1);
						player.getHand().add(c2);
					} else if (option2 == 1) {
						Card c1 = board.getTomb().remove(0);
						Card c2 = board.getTomb().remove(0);
						player.getHand().add(c1);
						player.getHand().add(c2);
					}
				}
			} else {
				if (board.getTomb().size() == 0) {
					Card c1 = board.getExile().remove(0);
					Card c2 = board.getExile().remove(0);
					player.getHand().add(c1);
					player.getHand().add(c2);
				} else if (board.getTomb().size() == 1) {
					if (option2 == 0) {
						Card c1 = board.getExile().remove(0);
						Card c2 = board.getExile().remove(0);
						player.getHand().add(c1);
						player.getHand().add(c2);
					} else if (option2 == 1) {
						Card c1 = board.getExile().remove(0);
						Card c2 = board.getTomb().remove(0);
						player.getHand().add(c1);
						player.getHand().add(c2);
					}
					
				} else {
					if (option2 == 0) {
						Card c1 = board.getExile().remove(0);
						Card c2 = board.getExile().remove(0);
						player.getHand().add(c1);
						player.getHand().add(c2);
					} else if (option2 == 1) {
						Card c1 = board.getExile().remove(0);
						Card c2 = board.getTomb().remove(0);
						player.getHand().add(c1);
						player.getHand().add(c2);
					} else if (option2 == 2) {
						Card c1 = board.getTomb().remove(0);
						Card c2 = board.getTomb().remove(0);
						player.getHand().add(c1);
						player.getHand().add(c2);
					}
				}
			}
		}
	}
}
