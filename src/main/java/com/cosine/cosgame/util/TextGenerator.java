package com.cosine.cosgame.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class TextGenerator{
	
	private String nameFile = "files/names.cos";
	private List<String> xings;
	private List<String> mings;
	
	public void readName() {
		Path currentRelativePath = Paths.get(nameFile);
		String x = currentRelativePath.toAbsolutePath().toString();
		String flag = "";
		xings = new ArrayList<String>();
		mings = new ArrayList<String>();
		
		Scanner sc = null;
		try {
			sc = new Scanner(new File(x));
		} catch (Exception e) {
			System.out.println(x + " not found");
		}
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.equals("xing") || s.equals("ming") || s.equals("end")) {
				flag = s;
			} else {
				if (flag.equals("xing")) {
					xings.add(s);
				}
				if (flag.equals("ming")) {
					mings.add(s);
				}
			}
		}
		
	}
	
	public String generateName() {
		String ans = "";
		int xn = xings.size();
		int mn = mings.size();
		int ix = (int)(Math.random() * xn);
		int im = (int)(Math.random() * mn);
		ans = xings.get(ix) + mings.get(im);
		return ans;
	}
	
	public TextGenerator() {}
	
}