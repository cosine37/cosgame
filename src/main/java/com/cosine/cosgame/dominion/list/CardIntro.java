package com.cosine.cosgame.dominion.list;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cosine.cosgame.util.CsvReader;

public class CardIntro {
	CsvReader reader;
	List<ExpansionIntro> expansionIntros;
	
	public CardIntro() {
		reader = new CsvReader();
		expansionIntros = new ArrayList<>();
	}
	
	public void readCardIntro() {
		String fileName = "files/dominionCards.csv";
		reader.setPath(fileName);
		reader.read();
	}
	
	public void setExpansionIntros() {
		List<List<String>> content = reader.getContent();
		int flag = 0;
		final int EXPANSION = 1;
		final int INTRO = 2;
		final int CARD = 3;
		ExpansionIntro intro = new ExpansionIntro();
		for (int i=0;i<content.size();i++) {
			String first = content.get(i).get(0);
			if (first.equals("Expansion")) {
				if (flag != 0) {
					expansionIntros.add(intro);
				}
				intro = new ExpansionIntro();
				flag = EXPANSION;
				String expName = content.get(i).get(1);
				intro.setName(expName);
				continue;
			} else if (first.equals("Intro")) {
				flag = INTRO;
				continue;
			} else if (first.equals("Card")) {
				flag = CARD;
				continue;
			} else {
				if (flag == INTRO) {
					intro.setIntro(content.get(i).get(0));
				} if (flag == CARD) {
					intro.add(content.get(i));
				}
				continue;
			}
		}
		if (intro != null) {
			expansionIntros.add(intro);
		}
	}
	/*
	public void readCardIntro() {
		String filename = "files/dominionDesc.cos";
		Path currentRelativePath = Paths.get(filename);
		String x = currentRelativePath.toAbsolutePath().toString();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(x));
		} catch (Exception e) {
			System.out.println(x + " not found");
		}
		
		String newExpansionFlag = "*****";
		ExpansionIntro expIntro = new ExpansionIntro();
		int flag = 0;
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			if (s.equals(newExpansionFlag)) {
				if (expIntro.getCards().size()>0) {
					expansionIntros.add(expIntro);
				}
				expIntro = new ExpansionIntro();
				flag = 1;
			} else if (flag == 1){
				expIntro.setIntro(s);
				flag = 2;
			} else {
				List<String> ls = new ArrayList<String>();
				ls.add(s);
				for (int i=0;i<6;i++) {
					s = sc.nextLine();
					ls.add(s);
				}
			}
		}
		
	}
	*/
	public List<ExpansionIntro> getExpansionIntros() {
		return expansionIntros;
	}
	
}
