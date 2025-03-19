package com.cosine.cosgame.rich;

import java.util.HashMap;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.rich.basicplaces.Estate;
import com.cosine.cosgame.rich.entity.DetailEntity;

public class Detail {
	String title;
	String desc;
	String desc2;
	String img;
	int area;
	String background;
	List<String> extras;
	
	Place place;
	
	public Document toDocument(){
		Document doc = new Document();
		doc.append("title",title);
		doc.append("desc",desc);
		doc.append("desc2", desc2);
		doc.append("img",img);
		doc.append("area",area);
		doc.append("background",background);
		doc.append("extras",extras);
		return doc;
	}
	public void setFromDoc(Document doc){
		title = doc.getString("title");
		desc = doc.getString("desc");
		desc2 = doc.getString("desc2");
		img = doc.getString("img");
		area = doc.getInteger("area",0);
		background = doc.getString("background");
		extras = (List<String>)doc.get("extras");
	}
	public DetailEntity toDetailEntity(){
		DetailEntity entity = new DetailEntity();
		entity.setTitle(title);
		entity.setDesc(desc);
		entity.setDesc2(desc2);
		entity.setImg(img);
		entity.setArea(area);
		entity.setBackground(background);
		entity.setExtras(extras);
		
		HashMap<String, String> imgStyle = new HashMap<>();
		if (place.getType() == Consts.PLACE_FATE) {
			int lastFateId = place.getBoard().getLastFateId();
			Fate fate = Factory.genFate(lastFateId);
			if (fate != null) {
				imgStyle.put("background-image", "url(/image/Rich/fate/" + lastFateId + ".png)");
				entity.setDesc(fate.getContent());
			} else {
				imgStyle.put("background-image", "url(/image/Rich/" + img + ".png)");
				entity.setDesc("下次又会发生什么呢？");
			}
		} else {
			imgStyle.put("background-image", "url(/image/Rich/" + img + ".png)");
		}
		entity.setImgStyle(imgStyle);
		
		String areaName = "";
		Map map = place.getBoard().getMap();
		HashMap<String, String> titleStyle = new HashMap<>();
		titleStyle.put("font-family", place.getFontFamily());
		if (place.getType() == Consts.PLACE_ESTATE && area<100) {
			titleStyle.put("background-color", map.getAreaColors().get(area));
			titleStyle.put("color", "white");
			areaName = map.getAreaNames().get(area);
		}
		entity.setTitleStyle(titleStyle);
		entity.setAreaName(areaName);
		
		HashMap<String, String> descStyle = new HashMap<>();
		entity.setDescStyle(descStyle);
		
		
		return entity;
	}
	
	public Detail(Place place) {
		this.place = place;
		apply();
	}
	
	public void apply() {
		title = place.getName();
		if (place.getDesc() != null && place.getDesc().length()>0) desc = place.getDesc() + "。";
		img = place.getImg();
		if (place.getType() == Consts.PLACE_ESTATE) {
			Estate e = (Estate) place;
			area = e.getArea();
		}
	}
	
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
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	public String getDesc2() {
		return desc2;
	}
	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}
}
