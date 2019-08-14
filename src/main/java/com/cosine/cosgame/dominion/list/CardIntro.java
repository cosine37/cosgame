package com.cosine.cosgame.dominion.list;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cosine.cosgame.util.ListReader;

public class CardIntro {
	ListReader reader;
	List<ExpansionIntro> expansionIntros;
	public CardIntro() {
		expansionIntros = new ArrayList<ExpansionIntro>();
	}
	
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

	public List<ExpansionIntro> getExpansionIntros() {
		return expansionIntros;
	}
	
}
