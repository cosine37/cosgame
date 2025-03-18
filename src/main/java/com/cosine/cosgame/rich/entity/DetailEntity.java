package com.cosine.cosgame.rich.entity;

import java.util.List;
import java.util.Map;

public class DetailEntity {
	String title;
	String desc;
	String desc2;
	String img;
	int area;
	String background;
	String areaName;
	List<String> extras;
	Map<String, String> imgStyle;
	Map<String, String> titleStyle;
	Map<String, String> descStyle;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public List<String> getExtras() {
		return extras;
	}
	public void setExtras(List<String> extras) {
		this.extras = extras;
	}
	public Map<String, String> getImgStyle() {
		return imgStyle;
	}
	public void setImgStyle(Map<String, String> imgStyle) {
		this.imgStyle = imgStyle;
	}
	public Map<String, String> getTitleStyle() {
		return titleStyle;
	}
	public void setTitleStyle(Map<String, String> titleStyle) {
		this.titleStyle = titleStyle;
	}
	public Map<String, String> getDescStyle() {
		return descStyle;
	}
	public void setDescStyle(Map<String, String> descStyle) {
		this.descStyle = descStyle;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getDesc2() {
		return desc2;
	}
	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}
}
