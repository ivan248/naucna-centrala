package com.udd.naucnacentrala.config;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.udd.naucnacentrala.repository")
public class ElasticSearchConf {
 

    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;

    @Value("${elasticsearch.clustername}")
    private String EsClusterName;

    @Bean
    public Client client() throws Exception {

        Settings esSettings = Settings.builder()
                .put("cluster.name", EsClusterName)
                .build();

        TransportClient client = new PreBuiltTransportClient(esSettings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("host1"), 9300))
                .addTransportAddress(new TransportAddress(InetAddress.getByName("host2"), 9300));
        
        //https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }
	
//	@Bean
//	ElasticsearchOperations elasticsearchTemplate() {
//
//		
//		Settings elasticSearchSettings = 
//				Settings.builder().put("cluster.name", "myClusterName").build();
//				  
//		TransportClient client = null;
//		try {
//			client = new PreBuiltTransportClient(elasticSearchSettings)
//					.addTransportAddress(new InetSocketTransportAddress("192.168.73.186", 9300));;
//			
//			
//			
//			return new ElasticsearchTemplate(client);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};
//		
//		return new ElasticsearchTemplate(client);
//	}
}
