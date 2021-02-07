package com.cosine.cosgame.onenight;

import com.cosine.cosgame.onenight.roles.QuoteWerewolf;

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
		int x = r.getRoleNumToShow();
		viewer.getPlayerMarks().set(p.getIndex(), x);
		r.onView(viewer);
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
			int y = board.getCurCenterRole(x).getRoleNumToShow();
			viewer.getCenterMarks().set(x, y);
			r.onView(viewer);
		}
	}
	
	public static void viewCenterRoles(Player viewer, Board board, int t1, int t2) {
		if (t1 >= 100 && t1 <= 102) {
			int x = t1-100;
			Role r = board.getCurCenterRole(x);
			int y = board.getCurCenterRole(x).getRoleNumToShow();
			viewer.getCenterMarks().set(x, y);
			r.onView(viewer);
		}
		if (t2 >= 100 && t2 <= 102) {
			int x = t2-100;
			Role r = board.getCurCenterRole(x);
			int y = board.getCurCenterRole(x).getRoleNumToShow();
			viewer.getCenterMarks().set(x, y);
			r.onView(viewer);
		}
	}
	
	public static void viewCurrentRole(Player viewer) {
		Role r = viewer.getCurrentRole();
		viewer.setUpdatedRole(r);
		viewer.setShowUpdatedRole(true);
		r.onView(viewer);
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
				viewer.getPlayerMarks().set(i, cr.getRoleNumToShow());
				cr.onView(viewer);
			}
		}
	}
	
	public static void convertRole(Player p, Role r) {
		if (p.getCurrentRole().exchangable()) {
			p.addRole(r);
			p.setUpdatedRole(r);
		}
	}
}
