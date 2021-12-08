package com.cosine.cosgame.threechaodoms;

import java.util.ArrayList;
import java.util.List;

public class ID {
	List<Integer> forces;
	
	public ID() {
		forces = new ArrayList<>();
		for (int i=0;i<4;i++) {
			forces.add(0);
		}
	}
	
	public void setForces(int f1, int f2) {
		forces = new ArrayList<>();
		for (int i=0;i<4;i++) {
			forces.add(0);
		}
		if (f1>=0 && f1<4) {
			forces.set(f1, 1);
		}
		if (f2>=0 && f2<4) {
			forces.set(f2, 1);
		}
	}
	
	public boolean hasForce(int x) {
		if (x>=0 && x<4) {
			if (forces.get(x) == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
