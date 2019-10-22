package com.cosine.cosgame.minigame.xutangbo;

import java.util.ArrayList;
import java.util.List;

public class AI {
	Player p;
	Game g;
	
	public AI(Player p, Game g) {
		this.p = p;
		this.g = g;
	}
	
	public int genMove() {
		int mx = otherMaxXu();
		int mb = otherMaxBi();
		
		int nx = otherHasXu();
		int nb = otherHasBi();
		
		if (mx == 0 && mb == 0) {
			if (p.getBi() == 0) {
				if (p.getEnergy() == 0) {
					return Move.XU;
				} else if (p.getEnergy() == 1){
					return chooseFromTwo(Move.XU, Move.BO, 7, 1);
				} else if (p.getEnergy() == 2) {
					return chooseFromTwo(Move.XU, Move.BO, 15, 1);
				} else {
					return Move.DABO;
				}
			} else {
				if (p.getEnergy() == 0) {
					return chooseFromTwo(Move.XU, Move.BI, 3, 1);
				} else if (p.getEnergy() == 1) {
					return chooseFromTwo(Move.XU, Move.BI, 5, 1);
				} else if (p.getEnergy() == 2) {
					return chooseFromTwo(Move.XU, Move.BI, 7, 1);
				} else {
					return Move.DABO;
				}
			}
		} else {
			if (mx == 0) {
				if (p.getEnergy() == 0) {
					if (p.getBi() == 0) {
						return chooseFromTwo(Move.TANG, Move.XU, nb, 1);
					} else {
						int x = nb-1;
						if (x==0) x = 1;
						return chooseFromThree(Move.TANG, Move.XU, Move.BI, nb, 1, x);
					}
				} else if (p.getEnergy() == 1 || p.getEnergy() == 2) {
					if (p.getBi() == 0) {
						int x = nb-1;
						if (x==0) x = 1;
						return chooseFromThree(Move.TANG, Move.XU, Move.BO, nb, 1, x);
					} else {
						int x = nb-1;
						if (x==0) x = 1;
						List<Integer> choices = new ArrayList<>();
						List<Integer> chances = new ArrayList<>();
						choices.add(Move.TANG);
						choices.add(Move.XU);
						choices.add(Move.BI);
						choices.add(Move.BO);
						chances.add(nb);
						chances.add(1);
						chances.add(x);
						chances.add(x);
						return chooseOne(choices, chances);
					}
				} else {
					return Move.DABO;
				}
			} else if (mx == 1) {
				if (p.getEnergy() == 0) {
					if (p.getBi() == 0) {
						return chooseFromTwo(Move.TANG, Move.XU, nb, 1);
					} else {
						int x = nb-1;
						if (x==0) x = 1;
						return chooseFromThree(Move.TANG, Move.XU, Move.BI, nb, 1, x);
					}
				} else if (p.getEnergy() == 1) {
					if (p.getBi() == 0) {
						int x = nb-1;
						if (x==0) x = 1;
						return chooseFromThree(Move.TANG, Move.XU, Move.BO, nb, 1, x);
					} else {
						int x = nb-1;
						if (x==0) x = 1;
						List<Integer> choices = new ArrayList<>();
						List<Integer> chances = new ArrayList<>();
						choices.add(Move.TANG);
						choices.add(Move.XU);
						choices.add(Move.BI);
						choices.add(Move.BO);
						chances.add(nb);
						chances.add(1);
						chances.add(x);
						chances.add(x);
						return chooseOne(choices, chances);
					}
				} else if (p.getEnergy() == 2) {
					
				} else if (p.getEnergy() == 3) {
					return chooseFromThree(Move.TANG, Move.XU, Move.DABO, 1, 1, 9);
				} else {
					return Move.BOBA;
				}
			} else if (mx == 2) {
				if (p.getEnergy() == 0) {
					if (p.getBi() == 0) {
						return chooseFromTwo(Move.TANG, Move.XU, 3, 1);
					} else {
						return chooseFromThree(Move.TANG, Move.XU, Move.BI, 3, 1, 1);
					}
				} else if (p.getEnergy() == 1) {
					if (p.getBi() == 0) {
						return chooseFromThree(Move.TANG, Move.XU, Move.BO, 3, 1, 1);
					} else {
						List<Integer> choices = new ArrayList<>();
						List<Integer> chances = new ArrayList<>();
						choices.add(Move.TANG);
						choices.add(Move.XU);
						choices.add(Move.BI);
						choices.add(Move.BO);
						chances.add(3);
						chances.add(1);
						chances.add(1);
						chances.add(1);
						return chooseOne(choices, chances);
					}
				} else if (p.getEnergy() == 2) {
					if (p.getBi() == 0) {
						List<Integer> choices = new ArrayList<>();
						List<Integer> chances = new ArrayList<>();
						choices.add(Move.TANG);
						choices.add(Move.XU);
						choices.add(Move.BO);
						choices.add(Move.ZHONGBO);
						chances.add(7);
						chances.add(1);
						chances.add(1);
						chances.add(2);
						return chooseOne(choices, chances);
					} else {
						List<Integer> choices = new ArrayList<>();
						List<Integer> chances = new ArrayList<>();
						choices.add(Move.TANG);
						choices.add(Move.XU);
						choices.add(Move.BI);
						choices.add(Move.BO);
						choices.add(Move.ZHONGBO);
						chances.add(7);
						chances.add(1);
						chances.add(1);
						chances.add(1);
						chances.add(2);
						return chooseOne(choices, chances);
					}	
				} else if (p.getEnergy() == 3) {
					return chooseFromThree(Move.TANG, Move.XU, Move.DABO, 1, 1, 7);
				} else if (p.getEnergy() == 4) {
					return chooseFromThree(Move.TANG, Move.XU, Move.BOBA, 1, 1, 15);
				} else {
					return Move.JIUJIBO;
				}
			} else if (mx == 3) {
				if (p.getEnergy() == 0) {
					if (p.getBi() == 0) {
						return Move.XU;
					} else {
						return chooseFromTwo(Move.XU, Move.BI, 1, 3);
					}
				} else if (p.getEnergy() == 1 || p.getEnergy() == 2) {
					if (p.getBi() == 0) {
						return chooseFromThree(Move.DATANG, Move.XU, Move.TANG, 13, 1, 2);
					} else {
						List<Integer> choices = new ArrayList<>();
						List<Integer> chances = new ArrayList<>();
						choices.add(Move.DATANG);
						choices.add(Move.XU);
						choices.add(Move.TANG);
						choices.add(Move.BI);
						choices.add(Move.BO);
						chances.add(13);
						chances.add(1);
						chances.add(2);
						chances.add(1);
						chances.add(1);
					}
				} else if (p.getEnergy() == 3) {
					return chooseFromTwo(Move.DABO, Move.DATANG, 31, 1);
				}
			} else if (mx == 4) {
				if (p.getEnergy() < 2) {
					if (p.getBi() == 0) {
						return Move.XU;
					} else {
						return chooseFromTwo(Move.XU, Move.BI, 1, 1);
					}
				} else if (p.getEnergy() == 2) {
					if (p.getBi() == 0) {
						return chooseFromTwo(Move.XU, Move.QIANGLIETANG, 1, 15);
					} else {
						return chooseFromThree(Move.XU, Move.QIANGLIETANG, Move.BI, 1, 15, 1);
					}
				} else if (p.getEnergy() == 3) {
					if (p.getBi() == 0) {
						return chooseFromTwo(Move.XU, Move.QIANGLIETANG, 1, 15);
					} else {
						return chooseFromThree(Move.XU, Move.QIANGLIETANG, Move.BI, 1, 15, 1);
					}
				} else if (p.getEnergy() == 4) {
					return Move.BOBA;
				} else {
					return Move.JIUJIBO;
				}
			} else if (mx == 5) {
				if (p.getEnergy() == 0) {
					return Move.XU;
				} else if (p.getEnergy() == 1) {
					return Move.BO;
				} else if (p.getEnergy() == 2) {
					return Move.ZHONGBO;
				} else if (p.getEnergy() == 3) {
					return Move.DABO;
				} else if (p.getEnergy() == 4) {
					return Move.BOBA;
				} else {
					return Move.JIUJIBO;
				}
			}
		}
		return Move.TANG;
	}
	
	int chooseOne(List<Integer> choices, List<Integer> chances) {
		double total = 0;
		for (int i=0;i<chances.size();i++) {
			total = total + chances.get(i);
		}
		double temp = total * Math.random();
		int magicNum = (int)temp;
		int x = 0;
		int ans = -1;
		for (int i=0;i<chances.size();i++){
			x = x+chances.get(i);
			if (x>magicNum) {
				ans = choices.get(i);
				break;
			}
		}
		return ans;	
	}
	
	int chooseFromTwo(int choice1, int choice2, int chance1, int chance2) {
		List<Integer> choices = new ArrayList<>();
		List<Integer> chances = new ArrayList<>();
		choices.add(choice1);
		choices.add(choice2);
		chances.add(chance1);
		chances.add(chance2);
		return chooseOne(choices, chances);
	}
	
	int chooseFromThree(int choice1, int choice2, int choice3, int chance1, int chance2, int chance3) {
		List<Integer> choices = new ArrayList<>();
		List<Integer> chances = new ArrayList<>();
		choices.add(choice1);
		choices.add(choice2);
		choices.add(choice3);
		chances.add(chance1);
		chances.add(chance2);
		chances.add(chance3);
		return chooseOne(choices, chances);
	}
	
	
	int otherMaxXu() {
		int max = 0;
		for (int i=0;i<g.getPlayers().size();i++) {
			if (g.getPlayers().get(i).getName().equals(p.getName())) {
				continue;
			} else {
				if (g.getPlayers().get(i).getEnergy() > max) {
					max = g.getPlayers().get(i).getEnergy();
				}
			}
		}
		return max;
	}
	
	int otherMaxBi() {
		int max = 0;
		for (int i=0;i<g.getPlayers().size();i++) {
			if (g.getPlayers().get(i).getName().equals(p.getName())) {
				continue;
			} else {
				if (g.getPlayers().get(i).getBi() > max) {
					max = g.getPlayers().get(i).getBi();
				}
			}
		}
		return max;
	}
	
	int otherHasXu() {
		int ans = 0;
		for (int i=0;i<g.getPlayers().size();i++) {
			if (g.getPlayers().get(i).getName().equals(p.getName())) {
				continue;
			} else {
				if (g.getPlayers().get(i).getEnergy() > 0) {
					ans = ans + 1;
				}
			}
		}
		return ans;
	}
	
	int otherHasBi() {
		int ans = 0;
		for (int i=0;i<g.getPlayers().size();i++) {
			if (g.getPlayers().get(i).getName().equals(p.getName())) {
				continue;
			} else {
				if (g.getPlayers().get(i).getBi() > 0) {
					ans = ans + 1;
				}
			}
		}
		return ans;
	}
	
}
