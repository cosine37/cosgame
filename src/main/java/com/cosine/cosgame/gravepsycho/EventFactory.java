package com.cosine.cosgame.gravepsycho;

import com.cosine.cosgame.gravepsycho.event.*;

public class EventFactory {
	public static Event makeEvent(int x) {
		Event event = new Event();
		if (x == 0) {
			event = new Classic();
		} else if (x == 11) {
			event = new Windfall();
		} else if (x == 13) {
			event = new Relic();
		} else if (x == 5) {
			event = new Danger();
		} else if (x == 8) {
			event = new Disappear();
		} else if (x == 3) {
			event = new Pride();
		} else if (x == 14) {
			event = new Panic();
		} else if (x == 9) {
			event = new Focus();
		} else if (x == 1) {
			event = new WealOrWoe();
		} else if (x == 2) {
			event = new Search();
		} else if (x == 16) {
			event = new Rain();
		} else if (x == 6) {
			event = new Impatient();
		} else if (x == 18) {
			event = new Echo();
		} else if (x == 17) {
			event = new Guard();
		} else if (x == 19) {
			event = new Valuable();
		} else if (x == 20) {
			event = new Certify();
		} else if (x == 21) {
			event = new Raider();
		} else if (x == 22) {
			event = new Dodge();
		} else if (x == 24) {
			event = new Pilfer();
		} else if (x == 23) {
			event = new Greed();
		}
		return event;
	}
}
