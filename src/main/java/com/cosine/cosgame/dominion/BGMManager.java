package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.dominion.oriental.Oriental;
import com.cosine.cosgame.util.BGM;
import com.cosine.cosgame.util.StringEntity;

public class BGMManager {
	List<BGM> bgms;
	
	List<BGM> regular;
	List<BGM> oriental;
	
	BGM Radetzky;
	BGM Turca;
	BGM Tritsch;
	
	BGM JingZhongBaoGuo;
	BGM JiuLongZan;
	BGM LongDeChuanRen;
	
	public BGMManager() {
		buildBGMs();
		buildLists();
	}
	
	public List<BGM> generateBGMs(Board board){
		List<BGM> ans = regular;
		int orientalCount = 0;
		for (int i=0;i<board.getKindom().size();i++) {
			if (isOriental(board.getKindom().get(i))) {
				orientalCount = orientalCount+1;
			}
		}
		if (orientalCount >= 5) {
			ans = oriental;
		}
		return ans;
	}
	
	public StringEntity generateBGMsAsStringEntity(Board board) {
		StringEntity entity = new StringEntity();
		List<String> value = new ArrayList<>();
		List<BGM> bgms = generateBGMs(board);
		for (int i=0;i<bgms.size();i++) {
			value.add(bgms.get(i).getUrl());
		}
		entity.setValue(value);
		return entity;
	}
	
	boolean isOriental(Pile pile) {
		Expansion oriental = new Oriental();
		for (int i=0;i<oriental.getPiles().size();i++) {
			if (pile.getName().equals(oriental.getPiles().get(i).getName())) {
				return true;
			}
		}
		return false;
	}
	
	public void buildLists() {
		regular = new ArrayList<>();
		regular.add(Radetzky);
		regular.add(Turca);
		regular.add(Tritsch);
		
		oriental = new ArrayList<>();
		oriental.add(JingZhongBaoGuo);
		oriental.add(JiuLongZan);
		oriental.add(LongDeChuanRen);
	}
	
	public void buildBGMs() {
		Radetzky = new BGM();
		Radetzky.setTitle("Radetzky Marsch");
		Radetzky.setUrl("/sound/BGM/RadetzkyMarsch.mp3");
		Turca = new BGM();
		Turca.setTitle("Rondo Alla Turca");
		Turca.setUrl("/sound/BGM/RondoAllaTurca.mp3");
		Tritsch = new BGM();
		Tritsch.setTitle("Tritsch Tratsch Polka");
		Tritsch.setUrl("/sound/BGM/TritschTratschPolka.mp3.mp3");
		
		JingZhongBaoGuo = new BGM();
		JingZhongBaoGuo.setTitle("Jing Zhong Bao Guo");
		JingZhongBaoGuo.setUrl("/sound/BGM/Jingzhongbaoguo.mp3");
		JiuLongZan = new BGM();
		JiuLongZan.setTitle("JiuLongZan");
		JiuLongZan.setUrl("/sound/BGM/Jiulongzan.mp3");
		LongDeChuanRen = new BGM();
		LongDeChuanRen.setTitle("Long De Chuan Ren");
		LongDeChuanRen.setUrl("/sound/BGM/Longdechuanren.mp3");
	}
	
	public Document toDocument() {
		Document doc = new Document();
		List<Document> bgmsDocs = new ArrayList<>();
		for (int i=0;i<bgms.size();i++) {
			Document d = new Document();
			d.append("bgm", bgms.get(i).toDocument());
			bgmsDocs.add(d);
		}
		doc.append("bgms", bgmsDocs);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		bgms = new ArrayList<>();
		List<Document> bgmsDocs = (List<Document>) doc.get("bgms");
		for (int i=0;i<bgmsDocs.size();i++) {
			Document d = (Document) bgmsDocs.get(i).get("bgm");
			BGM bgm = new BGM();
			bgm.setBGMFromDoc(d);
		}
	}

}
