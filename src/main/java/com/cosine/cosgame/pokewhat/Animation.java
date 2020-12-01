package com.cosine.cosgame.pokewhat;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Animation {
	int id;
	int type;
	List<Frame> frames;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<Frame> getFrames() {
		return frames;
	}
	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}
	
	public List<List<String>> getFrameTargets(){
		int i,j;
		List<List<String>> ans = new ArrayList<>();
		for (i=0;i<frames.size();i++) {
			List<String> ls = new ArrayList<>();
			for (j=0;j<frames.get(i).getTargets().size();j++) {
				ls.add(Integer.toString(frames.get(i).getTargets().get(j)));
			}
			ans.add(ls);
		}
		return ans;
	}
	
	public List<String> getFrameType(){
		int i;
		List<String> ans = new ArrayList<>();
		for (i=0;i<frames.size();i++) {
			ans.add(Integer.toString(frames.get(i).getType()));
		}
		return ans;
	}
	
	public List<String> getFrameTime(){
		int i;
		List<String> ans = new ArrayList<>();
		for (i=0;i<frames.size();i++) {
			ans.add(Integer.toString(frames.get(i).getTime()));
		}
		return ans;
	}
	
	public List<String> getFrameImg(){
		int i;
		List<String> ans = new ArrayList<>();
		for (i=0;i<frames.size();i++) {
			ans.add(frames.get(i).getImg());
		}
		return ans;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("type", type);
		List<Document> lof = new ArrayList<>();
		for (int i=0;i<frames.size();i++) {
			lof.add(frames.get(i).toDocument());
		}
		doc.append("frames", lof);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getInteger("id", 0);
		type = doc.getInteger("type", 0);
		List<Document> lof = (List<Document>) doc.get("frames");
		frames = new ArrayList<>();
		for (int i=0;i<lof.size();i++) {
			Frame frame = new Frame();
			frame.setFromDoc(lof.get(i));
			frames.add(frame);
		}
	}
	
}
