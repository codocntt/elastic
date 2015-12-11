package vn.edu.hcmnlu.elastic;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import vn.edu.hcmnlu.contants.Constants;


public class ClientConnection {
	
	public static Client getTransportClient(){
		Settings settings = ImmutableSettings.settingsBuilder().put(Constants.CLUSTER_KEY, Constants.CLUSTER_NAME).build();
		TransportClient client = new TransportClient(settings);
		client.addTransportAddress(new InetSocketTransportAddress(Constants.HOST_NAME, Constants.HOST_PORT));
		return client;
	}
}
