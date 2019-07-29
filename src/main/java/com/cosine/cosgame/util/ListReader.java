package com.cosine.cosgame.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListReader {
	int blockSize; // number of lines per block
	String path;
	Scanner sc;
	public ListReader() {
		sc = null;
	}
	
	public ListReader(int blockSize) {
		this.blockSize = blockSize;
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
	
	public List<String> readBlock(){
		List<String> lst = new ArrayList<String>();
		while (sc.hasNext()) {
			String s = sc.next();
			System.out.println(s);
		}
		
		return lst;
	}
	
}
