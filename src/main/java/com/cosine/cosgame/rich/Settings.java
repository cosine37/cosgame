package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.rich.entity.SettingsEntity;

public class Settings {
	List<Integer> settingsList;
	
	public SettingsEntity toSettingsEntity() {
		SettingsEntity entity = new SettingsEntity();
		entity.setMode(getMode());
		entity.setStartMoney(getStartMoney());
		entity.setStartSalary(getStartSalary());
		entity.setEndCondition(getEndCondition());
		
		return entity;
	}
	
	public Settings() {
		settingsList = new ArrayList<>();
	}
	
	public Settings(List<Integer> settingsList) {
		this.settingsList = settingsList;
	}
	
	public List<Integer> getSettings() {
		return settingsList;
	}
	
	public int get(int x) {
		if (x<settingsList.size()) {
			return settingsList.get(x);
		} else {
			return -1;
		}
	}
	
	public int getMode() {
		return get(Consts.SETTINGS_MODE);
	}
	
	public int getStartMoney() {
		return get(Consts.SETTINGS_START_MONEY);
	}
	
	public int getStartSalary() {
		return get(Consts.SETTINGS_START_SALARY);
	}
	
	public int getEndCondition() {
		return get(Consts.SETTINGS_END_CONDITION);
	}
	
	public int getFirstPlayer() {
		return get(Consts.SETTINGS_FIRST_PLAYER);
	}
}
