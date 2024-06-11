package com.cosine.cosgame.util;

import java.util.ArrayList;
import java.util.List;

public class StringEntity {
	List<String> value;

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}
	
	public StringEntity() {
		value = new ArrayList<>();
	}
	
	public StringEntity(String s) {
		this();
		value.add(s);
	}
	
	public StringEntity(String s1, String s2) {
		this();
		value.add(s1);
		value.add(s2);
	}
	
}
