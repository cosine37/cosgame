package com.cosine.cosgame.gravepsycho;

import com.cosine.cosgame.gravepsycho.event.*;

public class EventFactory {
	public static Event makeEvent(int x) {
		Event event = new Event();
		if (x == 0) {
			event = new Classic();
		} else if (x == 11) {
			event = new Windfall();
		}
		return event;
	}
}
