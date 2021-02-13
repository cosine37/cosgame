package com.cosine.cosgame.onenight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Manipulations {
	public static void swapRoles(Player p1, Player p2) {
		Role r1 = p1.getCurrentRole();
		Role r2 = p2.getCurrentRole();
		if (r1.exchangable() && r2.exchangable()) {
			p2.addRole(r1);
			p1.addRole(r2);
		}
	}
	
	public static void swapCenterRole(Player p, Board board, int t) {
		Role r1 = board.getCurCenterRole(t);
		Role r2 = p.getCurrentRole();
		if (r1.exchangable() && r2.exchangable()) {
			p.addRole(r1);
			board.addCenterRole(t, r2);
		}
	}
	
	public static void viewPlayerRole(Player viewer, Player p) {
		Role r = p.getCurrentRole();
		r.onView(viewer);
		int x = r.getRoleNumToShow();
		viewer.getPlayerMarks().set(p.getIndex(), x);
	}
	
	public static void peekCenterRole(Player viewer, Board board, int t) {
		if (t >= 100 && t <= 102) {
			int x = t-100;
			int y = board.getCurCenterRole(x).getRoleNumToShow();
			viewer.getCenterMarks().set(x, y);
		}
	}
	
	public static void viewCenterRole(Player viewer, Board board, int t) {
		if (t >= 100 && t <= 102) {
			int x = t-100;
			Role r = board.getCurCenterRole(x);
			r.onView(viewer);
			int y = board.getCurCenterRole(x).getRoleNumToShow();
			viewer.getCenterMarks().set(x, y);
		}
	}
	
	public static void viewCenterRoles(Player viewer, Board board, int t1, int t2) {
		if (t1 >= 100 && t1 <= 102) {
			int x = t1-100;
			Role r = board.getCurCenterRole(x);
			r.onView(viewer);
			int y = board.getCurCenterRole(x).getRoleNumToShow();
			viewer.getCenterMarks().set(x, y);
		}
		if (t2 >= 100 && t2 <= 102) {
			int x = t2-100;
			Role r = board.getCurCenterRole(x);
			r.onView(viewer);
			int y = board.getCurCenterRole(x).getRoleNumToShow();
			viewer.getCenterMarks().set(x, y);
		}
	}
	
	public static void viewCurrentRole(Player viewer) {
		Role r = viewer.getCurrentRole();
		r.onView(viewer);
		viewer.setUpdatedRole(r);
		viewer.setShowUpdatedRole(true);
	}
	
	public static void viewFinalRole(Player viewer, Board board, int initialRoleNum) {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			if (i == viewer.getIndex()) {
				continue;
			}
			Role ir = board.getPlayers().get(i).getInitialRole();
			if (ir.getRoleNum() == initialRoleNum) {
				Role cr = board.getPlayers().get(i).getCurrentRole();
				cr.onView(viewer);
				viewer.getPlayerMarks().set(i, cr.getRoleNumToShow());
			}
		}
	}
	
	public static void convertRole(Player p, Role r) {
		if (p.getCurrentRole().exchangable()) {
			p.addRole(r);
			p.setUpdatedRole(r);
		}
	}
	
	static boolean isSoleWolf(Player player, Board board) {
		if (board.isSoleWolf()) {
			int i;
			int t=0;
			for (i=0;i<board.getPlayers().size();i++) {
				if (player.getIndex() == i) {
					continue;
				}
				Player p = board.getPlayers().get(i);
				if (p.getInitialRole().getSide() == Consts.WOLF && p.getInitialRole().getRoleNum() != Consts.MINION) {
					t++;
				}
			}
			if (t == 0) {
				return true;
			}
		}
		return false;
	}
	
	public static void soleWolfHandle(Player player, Board board) {
		if (isSoleWolf(player,board)) {
			List<Role> crs = new ArrayList<>();
			List<Integer> cis = new ArrayList<>();
			for (int i=0;i<board.getCenterRoles().size();i++) {
				Role r = board.getCurCenterRole(i);
				cis.add(i);
				crs.add(r);
			}
			boolean alreadyRevealed = false;
			while (crs.size() > 0) {
				Random rand = new Random();
				int x = rand.nextInt(crs.size());
				Role r = crs.remove(x);
				int y = cis.remove(x);
				if (r.getSide() != Consts.WOLF && r.getRoleNum() != Consts.BAKER) {
					player.getCenterMarks().set(y, r.getRoleNum());
					alreadyRevealed = true;
					break;
				}
			}
			if (alreadyRevealed == false) {
				crs = new ArrayList<>();
				cis = new ArrayList<>();
				for (int i=0;i<board.getCenterRoles().size();i++) {
					Role r = board.getCurCenterRole(i);
					cis.add(i);
					crs.add(r);
				}
				while (crs.size() > 0) {
					Random rand = new Random();
					int x = rand.nextInt(crs.size());
					Role r = crs.remove(x);
					int y = cis.remove(x);
					if (r.getRoleNum() != Consts.BAKER) {
						player.getCenterMarks().set(y, r.getRoleNum());
						break;
					}
				}
			}
		}
	}
}
