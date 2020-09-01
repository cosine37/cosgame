package com.cosine.cosgame.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDBUtil {
	MongoClient mongoClient;
	MongoDatabase mongoDB;
	
	String collectionName;
	
	static final String MONGOURL = "localhost";
	
	public MongoDBUtil() {
		mongoClient = new MongoClient(MONGOURL, 27017);
	}
	
	public MongoDBUtil(String dbname) {
		mongoClient = new MongoClient(MONGOURL, 27017);
		mongoDB = mongoClient.getDatabase(dbname);
		System.out.println("connected!");
	}
	
	public void setCol(String col) {
		collectionName = col;
	}
	
	public void insert(Document doc) {
		List<Document> docs = new ArrayList<Document>();
		docs.add(doc);
		MongoCollection<Document> collection = mongoDB.getCollection(collectionName);
		collection.insertMany(docs);
	}
	
	public void insert(String col, Document doc) {
		setCol(col);
		insert(doc);
	}
	
	public void delete(String key, String value) {
		MongoCollection<Document> collection = mongoDB.getCollection(collectionName);
		collection.deleteMany(Filters.eq(key, value));
	}
	
	public Document read(String key, String value) {
		MongoCollection<Document> collection = mongoDB.getCollection(collectionName);
		Document doc = collection.find(Filters.eq(key,value)).first();
		return doc;
	}
	
	public List<String> getValues(String key) {
		MongoCollection<Document> collection = mongoDB.getCollection(collectionName);
		List<String> ans = new ArrayList<String>();
		FindIterable<Document> docs = collection.find();
		for (Document doc : docs) {
			ans.add(doc.getString(key));
		}
		return ans;
	}
	
	public List<List<String>> getListValues(String key) {
		MongoCollection<Document> collection = mongoDB.getCollection(collectionName);
		List<List<String>> ans = new ArrayList<>();
		FindIterable<Document> docs = collection.find();
		for (Document doc : docs) {
			ans.add((List<String>) doc.get(key));
		}
		return ans;
	}
	
	public List<Integer> getIntValues(String key){
		MongoCollection<Document> collection = mongoDB.getCollection(collectionName);
		List<Integer> ans = new ArrayList<Integer>();
		FindIterable<Document> docs = collection.find();
		for (Document doc : docs) {
			ans.add(doc.getInteger(key, 0));
		}
		return ans;
	}
	
	
	public void update(String key, String value, String ukey, Object uvalue) {
		MongoCollection<Document> collection = mongoDB.getCollection(collectionName);
		collection.updateOne(new Document(key, value), new Document("$set", new Document(ukey, uvalue)));
	}
	
	public void removeKey(String key, String value, String rkey) {
		MongoCollection<Document> collection = mongoDB.getCollection(collectionName);
		collection.updateOne(new Document(key, value), new Document("$unset", new Document(rkey, "")));
	}
	
	public void push(String key, String value, String ukey, Object uvalue) {
		MongoCollection<Document> collection = mongoDB.getCollection(collectionName);
		collection.updateOne(new Document(key, value), new Document("$push", new Document(ukey, uvalue)));
	}
}
