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
		}
		return event;
	}
}
