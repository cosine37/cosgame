package com.cosine.cosgame.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {
	String path;
	Scanner sc;
	List<List<String>> content;
	
	public CsvReader() {
		sc = null;
	}
	
	public void setPath(String path) {
		this.path = path;
		Path currentRelativePath = Paths.get(path);
		String x = currentRelativePath.toAbsolutePath().toString();
		try {
			sc = new Scanner(new File(x));
		} catch (Exception e) {
			System.out.println(x + " not found");
		}
	}
	
	public void read() {
		content = new ArrayList<>();
		while (sc.hasNext()) {
			String line = sc.next();
			List<String> lst = new ArrayList<>();
			int quote = 0;
			String s = "";
			for (int i=0;i<line.length();i++) {
				if (line.charAt(i) == ',' && quote == 0) {
					if (s.contentEquals("")) {
						
					} else {
						lst.add(s);
						s = "";
					}
				} else {
					if (line.charAt(i) == '"') {
						quote = 1-quote;
					} else {
						s = s + line.charAt(i);
					}
				}
			}
			content.add(lst);
		}
	}
	
	public void printContent() {
		for (int i=0;i<content.size();i++) {
			for (int j=0;j<content.get(i).size();j++) {
				System.out.print(content.get(i).get(j) + "    ");
			}
			System.out.println();
		}
	}
}
