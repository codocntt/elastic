package vn.edu.hcmnlu.elastic;

import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;

import vn.edu.hcmnlu.bean.DocsMappping;

import com.google.gson.Gson;

public class DocumentOperations {
	
	private final Client client;

	public DocumentOperations(Client client) {
		this.client = client;
	}

	public void insertDocument(String index, String type, DocsMappping p){
		client.prepareIndex(index, type).setSource(new Gson().toJson(p)).execute().actionGet();
	}
	
	public void updateDocument(String index, String type, DocsMappping p){
		client.prepareUpdate(index, type, p.id).setDoc(new Gson().toJson(p)).execute().actionGet();
	}
	
	public void deleteDocument(String index, String type, String id){
		client.prepareDelete(index, type, id).execute().actionGet();
	}
	
	public DocsMappping getDocument(String index, String type, String id){
		GetResponse response = client.prepareGet(index, type, id).execute().actionGet();
		Map<String, Object> source = response.getSource();
		String data = new Gson().toJson(source);
		return new Gson().fromJson(data, DocsMappping.class);
	}
}
