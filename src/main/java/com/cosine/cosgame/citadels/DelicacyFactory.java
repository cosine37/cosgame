package com.cosine.cosgame.citadels;

import com.cosine.cosgame.citadels.sckx.delicacy.*;

public class DelicacyFactory {
	public static Delicacy createDelicacy(String img) {
		Delicacy delicacy;
		if (img.contentEquals("d001")) {
			delicacy = new Peach();
		} else if (img.contentEquals("d101")) {
			delicacy = new SesameCake();
		} else if (img.contentEquals("d501")) {
			delicacy = new Fish();
		} else if (img.contentEquals("d301")) {
			delicacy = new EggTart();
		} else if (img.contentEquals("d201")) {
			delicacy = new Layers();
		} else if (img.contentEquals("d002")) {
			delicacy = new Wine();
		} else if (img.contentEquals("d102")) {
			delicacy = new Tea();
		} else if (img.contentEquals("d103")) {
			delicacy = new DriedRaddish();
		}
			
		
		else {
			delicacy = new Delicacy();
		}
		
		
		return delicacy;
	}
}
