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
		}
		
		return script;
	}
}
