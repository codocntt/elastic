package vn.edu.hcmnlu.elastic;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;

public class MappingOperations {
	
	private final Client client;
	
	public MappingOperations(Client client) {
		this.client = client;
	}

	public void createMappingTemplate(String name, String documentType, XContentBuilder contentBuilder) {
		client.admin().indices().preparePutMapping(name).setType(documentType).setSource(contentBuilder).execute().actionGet();
	}
}
