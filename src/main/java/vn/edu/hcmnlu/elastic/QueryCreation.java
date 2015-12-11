package vn.edu.hcmnlu.elastic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;

import vn.edu.hcmnlu.bean.DocsMappping;


public class QueryCreation {
	
	public List<DocsMappping> responseData(String index, String type, String keyword) {
		
		List<DocsMappping> arr = new ArrayList<DocsMappping>();
		
		// set properties of resource
		Client client = ClientConnection.getTransportClient();
		
		//prepare Query
		QueryBuilder queryBuilder = QueryBuilders.boolQuery()
							.should(QueryBuilders.matchQuery("document", keyword));
		
		
		SearchResponse response = client.prepareSearch(index).setTypes(type).setQuery(queryBuilder).addFields("title").execute().actionGet();
		for(SearchHit hit : response.getHits().getHits()){
			Map<String, SearchHitField> maps = hit.getFields();
			String value = maps.get("title").value();
			DocsMappping p = new DocsMappping();
			p.title = value;
			arr.add(p);
		}
		System.out.println("done");
		return arr;
	}
}
