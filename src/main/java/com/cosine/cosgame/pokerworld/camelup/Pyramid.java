package com.cosine.cosgame.pokerworld.camelup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pyramid {
	class DiceFace{
		int color;
		int num;
		public DiceFace(int color, int num) {
			this.color = color;
			this.num = num;
		}
		public int getColor() {
			return color;
		}
		public void setColor(int color) {
			this.color = color;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
	}
	
	class Dice {
		List<DiceFace> faces;
		
		public Dice() {
			faces = new ArrayList<>();
		}
		
		public Dice(int color, int num) {
			this();
			int i;
			for (i=1;i<=num;i++) {
				addFace(color,i);
			}
		}
		
		public Dice(int color1, int color2, int num) {
			this();
			int i;
			for (i=1;i<=num;i++) {
				addFace(color1,i);
				addFace(color2,i);
			}
		}
		
		public void addFace(int color, int num) {
			faces.add(new DiceFace(color, num));
		}

		public List<DiceFace> getFaces() {
			return faces;
		}
		
	}
	
	List<Dice> dice;
	List<DiceFace> faces;
	
	public Pyramid() {
		initialize();
	}
	
	public void initialize() {
		dice = new ArrayList<>();
		dice.add(new Dice(Consts.RED, 3));
		dice.add(new Dice(Consts.BLUE, 3));
		dice.add(new Dice(Consts.YELLOW, 3));
		dice.add(new Dice(Consts.GREEN, 3));
		dice.add(new Dice(Consts.BLACK, Consts.WHITE, 3));
		
		faces = new ArrayList<>();
	}
	
	public void roll() {
		Random rand = new Random();
		int x = rand.nextInt(dice.size());
		Dice die = dice.remove(x);
		x = rand.nextInt(die.getFaces().size());
		DiceFace face = die.getFaces().get(x);
		faces.add(face);
	}
	
	
}
