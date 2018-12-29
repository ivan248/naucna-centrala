package com.udd.naucnacentrala.elasticsearch.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udd.naucnacentrala.elasticsearch.ScientificPaperDTO;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

	@Autowired
	private ElasticsearchTemplate elasticSearchTemplate;

	@Override
	public List<ScientificPaperDTO> searchByOneField(String field, String value) {

		List<ScientificPaperDTO> resultsList = new ArrayList<ScientificPaperDTO>();
		
		System.out.println(field + value);
		
		HighlightBuilder hb = new HighlightBuilder();
		hb.field("pdfText");

		SearchResponse response = elasticSearchTemplate.getClient().prepareSearch("scientificpaper").highlighter(hb)
				.setQuery(QueryBuilders.matchQuery(field, value)).get();

		System.out.println(response);
		
		for (SearchHit o : response.getHits()) {

			ObjectMapper objectMapper = new ObjectMapper();
			ScientificPaperDTO result = null;

			try {
				result = objectMapper.readValue(o.getSourceAsString(), ScientificPaperDTO.class);
				resultsList.add(result);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultsList;
	}

	// PhrazeQuery https://www.elastic.co/guide/en/elasticsearch/reference/master/query-dsl-match-query-phrase.html
	
	@Override
	public List<ScientificPaperDTO> searchByMultipleFields(Map<String, String> json) {

		List<ScientificPaperDTO> resultsList = new ArrayList<ScientificPaperDTO>();
		BoolQueryBuilder qb = QueryBuilders.boolQuery();

		for (Map.Entry<String, String> item : json.entrySet()) {
			qb.must(QueryBuilders.matchQuery(item.getKey(), item.getValue()));
		}
		
		SearchResponse response = elasticSearchTemplate.getClient().prepareSearch("scientificpaper").setQuery(qb).get();

		for (SearchHit o : response.getHits()) {
			ObjectMapper objectMapper = new ObjectMapper();
			ScientificPaperDTO result = null;

			try {
				result = objectMapper.readValue(o.getSourceAsString(), ScientificPaperDTO.class);
				resultsList.add(result);

			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return resultsList;
	}

	// https://stackoverflow.com/questions/25552321/or-and-and-operators-in-elasticsearch-query
	
	@Override
	public List<ScientificPaperDTO> searchByMultipleOptionalFields(Map<String, Object> json) {

		List<ScientificPaperDTO> resultsList = new ArrayList<ScientificPaperDTO>();
		List<String> listOfOptionalFields = null;
		List<String> listOfPhraseFields = null;
		
		
		BoolQueryBuilder qb = QueryBuilders.boolQuery();
		
		if(json.get("optional") != null && json.get("phrase") == null) {
			System.out.println("optional != null & phrase == null");
			
			listOfOptionalFields = (List)json.get("optional");
			
			for (Map.Entry<String, Object> item : json.entrySet()) {
				
				if(item.getKey().equals("optional"))
					continue;
				
				if(listOfOptionalFields.contains(item.getKey()))
					qb.should(QueryBuilders.matchQuery(item.getKey(), item.getValue()));
				else
					qb.must(QueryBuilders.matchQuery(item.getKey(), item.getValue()));
			}
			
		} else if(json.get("optional") == null && json.get("phrase") != null) {
			System.out.println("optional == null & phrase != null");
		
			listOfPhraseFields = (List)json.get("phrase");
			
			for (Map.Entry<String, Object> item : json.entrySet()) {
				
				if(item.getKey().equals("phrase"))
					continue;
				
				if(listOfPhraseFields.contains(item.getKey()))
					qb.must(QueryBuilders.matchPhraseQuery(item.getKey(), item.getValue()));
			}
		} else if(json.get("optional") != null && json.get("phrase") != null) {
			System.out.println("optional != null & phrase != null");
			
			listOfOptionalFields = (List)json.get("optional");
			listOfPhraseFields = (List)json.get("phrase");
			
			for (Map.Entry<String, Object> item : json.entrySet()) {
				
				if(item.getKey().equals("optional") || item.getKey().equals("phrase"))
					continue;
				
				// proveravam da li postoji bool query ako postoji onda proverim da li je i phrase, ako jeste onda je phrase query nije match query
				if(listOfOptionalFields.contains(item.getKey()))
					if(listOfPhraseFields.contains(item.getKey()))
						qb.should(QueryBuilders.matchPhraseQuery(item.getKey(), item.getValue()));
					else
						qb.should(QueryBuilders.matchQuery(item.getKey(), item.getValue()));
				else
					if(listOfPhraseFields.contains(item.getKey()))
						qb.must(QueryBuilders.matchPhraseQuery(item.getKey(), item.getValue()));
					else
						qb.must(QueryBuilders.matchQuery(item.getKey(), item.getValue()));
				
				// ostatak slucajeva, kada imam npr 2 optional 3 phrase, taj treci phrase je obican phrase query ali ga nije uhvatio gore
											
			} 
			
			for (Map.Entry<String, Object> item : json.entrySet()) {
				
				if(item.getKey().equals("optional") || item.getKey().equals("phrase"))
					continue;
				
				for(String phrase : listOfPhraseFields) {
					if(!listOfOptionalFields.contains(phrase))
						qb.must(QueryBuilders.matchPhraseQuery(item.getKey(), item.getValue()));
				}

			}
			
			
		} else {
			System.out.println("optional == null & phrase == null");
			
			for (Map.Entry<String, Object> item : json.entrySet()) {
				
				if(item.getKey().equals("optional") || item.getKey().equals("phrase"))
					continue;
				
				qb.must(QueryBuilders.matchQuery(item.getKey(), (String)item.getValue()));
			}
		
		}
		
//		HighlightBuilder hb = new HighlightBuilder();
//		hb.field("pdfText");

		SearchResponse response = elasticSearchTemplate.getClient().prepareSearch("scientificpaper").highlighter(hb).setQuery(qb).get();
		


		for (SearchHit o : response.getHits()) {
			ObjectMapper objectMapper = new ObjectMapper();
			ScientificPaperDTO result = null;

			System.out.println(o.getHighlightFields());
			
			try {
				result = objectMapper.readValue(o.getSourceAsString(), ScientificPaperDTO.class);
				//result.setPdfText(o.getHighlightFields().get("pdfText").getFragments()[0].string());
				resultsList.add(result);

			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return resultsList;
	}

}