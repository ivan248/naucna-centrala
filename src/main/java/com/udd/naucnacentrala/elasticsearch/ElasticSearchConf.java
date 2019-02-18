package com.udd.naucnacentrala.elasticsearch;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.udd.naucnacentrala.repository")
public class ElasticSearchConf {

	@Bean
	public Client client() throws Exception {

		Settings esSettings = Settings.builder().build();

		@SuppressWarnings("resource")
		TransportClient client = new PreBuiltTransportClient(esSettings);

		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws Exception {

		ElasticsearchOperations est = new ElasticsearchTemplate(client());
		est.createIndex(ScientificPaperIndexUnit.class);

		return est;
	}

}
