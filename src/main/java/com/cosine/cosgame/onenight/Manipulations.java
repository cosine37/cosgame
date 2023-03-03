package com.cosine.cosgame.onenight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Manipulations {
	public static void swapRoles(Player p1, Player p2) {
		Role r1 = p1.getCurrentRole();
		Role r2 = p2.getCurrentRole();
		if (r1.exchangable() && r2.exchangable() && (!p1.hasSentinel()) && (!p2.hasSentinel())) {
			r1.onExchange();
			r2.onExchange();
			p2.addRole(r1);
			p1.addRole(r2);
		}
	}
	
	public static void swapCenterRole(Player p, Board board, int t) {
		Role r1 = board.getCurCenterRole(t);
		Role r2 = p.getCurrentRole();
		if (r1.exchangable() && r2.exchangable() && (!p.hasSentinel())) {
			r1.onExchange();
			r2.onExchange();
			p.addRole(r1);
			board.addCenterRole(t, r2);
		}
	}
	
	public static void viewPlayerRole(Player viewer, Player p) {
		if (!p.hasSentinel()) {
			Role r = p.getCurrentRole();
			r.onView(viewer);
			int x = r.getRoleNumToShow();
			viewer.getPlayerMarks().set(p.getIndex(), x);
			r.afterView(viewer);
		}
	}
	
	public static void viewPlayerRolePoisoned(Player viewer, Player p, boolean canHaveSelf) {
		if (!p.hasSentinel()) {
			int x,y;
			List<Role> allRoles = getAllRoles(viewer.getBoard());
			while (allRoles.size()>0) {
				Random rand = new Random();
				x = rand.nextInt(allRoles.size());
				Role r = allRoles.remove(x);
				if (r.getRoleNum() == viewer.getInitialRole().getRoleNum()) {
					if (canHaveSelf == false) {
						continue;
					}
				}
				r.onViewPoisoned(viewer);
				y = r.getRoleNumToShow();
				viewer.getPlayerMarks().set(p.getIndex(), y);
				r.afterViewPoisoned(viewer);
				break;
			}
		}
	}
	
	public static void viewPlayerRolePoisoned(Player viewer, Player p) {
		 viewPlayerRolePoisoned(viewer, p, false);
	}
	
	public static void peekCenterRole(Player viewer, Board board, int t) {
		if (t >= 100 && t <= 102) {
			int x = t-100;
			Role r = board.getCurCenterRole(x);
			int y = r.getRoleNumToShow();
			r.onView(viewer);
			viewer.getCenterMarks().set(x, y);
			r.afterView(viewer);
		}
	}
	
	public static void viewCenterRole(Player viewer, Board board, int t) {
		if (t >= 100 && t <= 102) {
			int x = t-100;
			Role r = board.getCurCenterRole(x);
			r.onView(viewer);
			int y = board.getCurCenterRole(x).getRoleNumToShow();
			viewer.getCenterMarks().set(x, y);
			r.afterView(viewer);
		}
	}
	
	public static void viewCenterRolePoisoned(Player viewer, Board board, int t, boolean canHaveSelf) {
		if (t >= 100 && t <= 102) {
			t = t-100;
			int x,y;
			List<Role> allRoles = getAllRoles(viewer.getBoard());
			while (allRoles.size()>0) {
				Random rand = new Random();
				x = rand.nextInt(allRoles.size());
				Role r = allRoles.remove(x);
				if (r.getRoleNum() == viewer.getInitialRole().getRoleNum()) {
					if (canHaveSelf == false) {
						continue;
					}
				}
				r.onViewPoisoned(viewer);
				y = r.getRoleNumToShow();
				viewer.getCenterMarks().set(t, y);
				r.afterViewPoisoned(viewer);
				break;
			}
		}
	}
	
	public static void viewCenterRolePoisoned(Player viewer, Board board, int t) {
		viewCenterRolePoisoned(viewer, board, t, false);
	}
	
	public static void viewCenterRoles(Player viewer, Board board, int t1, int t2) {
		if (t1 >= 100 && t1 <= 102) {
			int x = t1-100;
			Role r = board.getCurCenterRole(x);
			r.onView(viewer);
			int y = board.getCurCenterRole(x).getRoleNumToShow();
			viewer.getCenterMarks().set(x, y);
			r.afterView(viewer);
		}
		if (t2 >= 100 && t2 <= 102) {
			int x = t2-100;
			Role r = board.getCurCenterRole(x);
			r.onView(viewer);
			int y = board.getCurCenterRole(x).getRoleNumToShow();
			viewer.getCenterMarks().set(x, y);
			r.afterView(viewer);
		}
	}
	
	public static void viewCenterRolesPoisoned(Player viewer, Board board, int t1, int t2, boolean canHaveSelf) {
		List<Role> allRoles = getAllRoles(viewer.getBoard());
		int x,y;
		if (t1 >= 100 && t1 <= 102) {
			t1 = t1-100;
			while (allRoles.size()>0) {
				Random rand = new Random();
				x = rand.nextInt(allRoles.size());
				Role r = allRoles.remove(x);
				if (r.getRoleNum() == viewer.getInitialRole().getRoleNum()) {
					if (canHaveSelf == false) {
						continue;
					}
				}
				r.onViewPoisoned(viewer);
				y = r.getRoleNumToShow();
				viewer.getCenterMarks().set(t1, y);
				r.afterViewPoisoned(viewer);
				break;
			}
		}
		if (t2 >= 100 && t2 <= 102) {
			t2 = t2-100;
			while (allRoles.size()>0) {
				Random rand = new Random();
				x = rand.nextInt(allRoles.size());
				Role r = allRoles.remove(x);
				if (r.getRoleNum() == viewer.getInitialRole().getRoleNum()) {
					if (canHaveSelf == false) {
						continue;
					}
				}
				r.onViewPoisoned(viewer);
				y = r.getRoleNumToShow();
				viewer.getCenterMarks().set(t2, y);
				r.afterViewPoisoned(viewer);
				break;
			}
		}
	}
	
	public static void viewCenterRolesPoisoned(Player viewer, Board board, int t1, int t2) {
		viewCenterRolesPoisoned(viewer, board, t1, t2, false);
	}
	
	public static void viewCurrentRole(Player viewer) {
		if (!viewer.hasSentinel()) {
			Role r = viewer.getCurrentRole();
			viewer.setUpdatedRole(r);
			r.onView(viewer);
			viewer.setShowUpdatedRole(true);
			r.afterView(viewer);
		}
	}
	
	public static void viewCurrentRolePoisoned(Player viewer, boolean canHaveSelf) {
		if (!viewer.hasSentinel()) {
			int x,y;
			List<Role> allRoles = getAllRoles(viewer.getBoard());
			Random rand = new Random();
			if (canHaveSelf) {
				x = rand.nextInt(10000);
				if (x%3 < 2) {
					Role r = viewer.getInitialRole();
					viewer.setUpdatedRole(r);
					r.onViewPoisoned(viewer);
					viewer.setShowUpdatedRole(true);
					r.afterViewPoisoned(viewer);
					return;
				}
			}
			while (allRoles.size()>0) {
				x = rand.nextInt(allRoles.size());
				Role r = allRoles.remove(x);
				if (r.getRoleNum() == viewer.getInitialRole().getRoleNum()) {
					if (canHaveSelf == false) {
						continue;
					}
				}
				viewer.setUpdatedRole(r);
				r.onViewPoisoned(viewer);
				viewer.setShowUpdatedRole(true);
				r.afterViewPoisoned(viewer);
				break;
			}
		}
	}
	
	public static void viewCurrentRolePoisoned(Player viewer) {
		viewCurrentRolePoisoned(viewer, false);
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
				cr.afterView(viewer);
			}
		}
	}
	
	public static void viewFinalRolePoisoned(Player viewer, Board board, int initialRoleNum) {
		Random rand = new Random();
		int i;
		List<Integer> ls = new ArrayList<>();
		for (i=0;i<board.getPlayers().size();i++) {
			ls.add(i);
		}
		int x = rand.nextInt(board.getPlayers().size()+2);
		if (x<board.getPlayers().size()-1) {
			while (ls.size()>0) {
				x = rand.nextInt(ls.size());
				int y = ls.remove(x);
				if (y == viewer.getIndex()) {
					continue;
				}
				int z = rand.nextInt(10000);
				if (z%3 < 2) {
					viewer.getPlayerMarks().set(y, initialRoleNum);
				} else {
					List<Role> allRoles = getAllRoles(board);
					x = rand.nextInt(allRoles.size());
					Role r = allRoles.remove(x);
					r.onViewPoisoned(viewer);
					viewer.getPlayerMarks().set(y, r.getRoleNumToShow());
					r.afterViewPoisoned(viewer);
					break;
				}
				break;
			}
		}
		
		
	}
	
	public static void convertRole(Player p, Role r) {
		if (p.getCurrentRole().exchangable() && (!p.hasSentinel())) {
			p.addRole(r);
			p.setUpdatedRole(r);
		}
	}
	
	public static void convertStatus(Player p, Status s) {
		s.setPlayer(p);
		s.setBoard(p.getBoard());
		p.addStatus(s);
	}
	
	public static void swapStatuses(Player p1, Player p2) {
		Status s1 = p1.getCurrentStatus();
		Status s2 = p2.getCurrentStatus();
		p2.addStatus(s1);
		p1.addStatus(s2);
	}
	
	public static void viewCurrentStatus(Player viewer) {
		Status s = viewer.getCurrentStatus();
		viewer.setUpdatedStatus(s);
	}
	
	public static void viewPlayerStatus(Player viewer, Player p) {
		Status s = p.getCurrentStatus();
		int x = s.getNum();
		viewer.getStatusMarks().set(p.getIndex(), x);
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
				} else if (p.getInitialRole().getRoleNum() == Consts.SHEEPWOLF) {
					t++;
				}
			}
			if (t == 0) {
				return true;
			}
		}
		return false;
	}
	
	public static void wolfVision(Player player, Board board) {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			if (player.getIndex() == i) {
				continue;
			}
			Player p = board.getPlayers().get(i);
			if (p.getInitialRole().getSide() == Consts.WOLF && p.getInitialRole().getRoleNum() != Consts.MINION) {
				player.getPlayerMarks().set(i, p.getInitialRole().getRoleNumToShow());
			} else if (p.getInitialRole().getRoleNum() == Consts.SHEEPWOLF) {
				player.getPlayerMarks().set(i, p.getInitialRole().getRoleNumToShow());
			}
		}
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
	
	static List<Role> getAllRoles(Board board){
		int i;
		List<Role> allRoles = new ArrayList<>();
		for (i=0;i<board.getRolesThisGame().size();i++) {
			allRoles.add(board.getRolesThisGame().get(i));
		}
		return allRoles;
	}
}
