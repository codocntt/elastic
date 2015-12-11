package vn.edu.hcmnlu.upload;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;

import javax.xml.bind.DatatypeConverter;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.hcmnlu.bean.DocsMappping;
import vn.edu.hcmnlu.contants.Constants;
import vn.edu.hcmnlu.elastic.ClientConnection;
import vn.edu.hcmnlu.elastic.DocumentOperations;
import vn.edu.hcmnlu.elastic.IndicesOperations;
import vn.edu.hcmnlu.elastic.MappingOperations;

@Service
public class UploadService {
	private static final Logger logger = LoggerFactory.getLogger(UploadService.class);
	
	public boolean uploadFile(MultipartFile file){
		Client client = ClientConnection.getTransportClient();
		IndicesOperations indices = new IndicesOperations(client);
		if(!indices.checkIndexExists(Constants.INDICES)){
			indices.createIndex(Constants.INDICES);
			try {
				XContentBuilder mappingBuilder = jsonBuilder()
						 .startObject()
						 		.startObject(Constants.TYPE)
						 			.startObject("properties")
						 				.startObject("document")
						 					.field("type", "attachment")
						 					.startObject("fields")
						 						.startObject("document")
							 						.field("type", "string")
							 						.field("store", "false")
							 						.field("term_vector", "with_positions_offsets")
						 						.endObject()
						 					.endObject()
						 				.endObject()
						 			.endObject()
				             .endObject()
				          .endObject();
				
				MappingOperations mappings = new MappingOperations(client);
				mappings.createMappingTemplate(Constants.INDICES, Constants.TYPE, mappingBuilder);
			} catch (IOException e) {
				logger.error("ERROR:" + e.getMessage());
				return false;
			}
		}
		
		/*
		 * process data uploading
		 */
		
		try {
			String encoded = DatatypeConverter.printBase64Binary(file.getBytes());
			DocsMappping docs = new DocsMappping();
			docs.document = encoded;
			docs.title = "Search Engineer";
			DocumentOperations docop = new DocumentOperations(client);
			docop.insertDocument(Constants.INDICES, Constants.TYPE, docs);
		} catch (IOException e) {
			logger.error("ERROR:" + e.getMessage());
			return false;
		}
		return true;
	}
}
