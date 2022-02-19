package com.cosine.cosgame.belltower;

import com.cosine.cosgame.belltower.roles.troublebrewing.*;


public class ScriptFactory {
	public static Script makeScript(int scriptId) {
		Script script = new Script();
		script.setId(scriptId);
		
		if (scriptId == 1) {
			Role r;
			r = new Librarian();
			script.addTownsfolk(r);
			r = new Soldier();
			script.addTownsfolk(r);
			r = new Monk();
			script.addTownsfolk(r);
			r = new Washerwoman();
			script.addTownsfolk(r);
			r = new Investigator();
			script.addTownsfolk(r);
			r = new Chef();
			script.addTownsfolk(r);
			r = new Empath();
			script.addTownsfolk(r);
			r = new Undertaker();
			script.addTownsfolk(r);
			r = new FortuneTeller();
			script.addTownsfolk(r);
			
			r = new Imp();
			script.addDemon(r);
			
			r = new Poisoner();
			script.addMinion(r);
		}
		
		return script;
	}
}
