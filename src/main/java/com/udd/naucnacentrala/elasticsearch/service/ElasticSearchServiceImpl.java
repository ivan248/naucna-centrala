package com.udd.naucnacentrala.elasticsearch.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.MoreLikeThisQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udd.naucnacentrala.elasticsearch.ScientificPaperIndexUnit;
import com.udd.naucnacentrala.elasticsearch.repository.ScientificPaperElasticSearchRepository;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

	@Autowired
	private ElasticsearchTemplate elasticSearchTemplate;
	
	@Autowired
	private ScientificPaperElasticSearchRepository elasticRepository;

	@Override
	public List<ScientificPaperIndexUnit> searchByOneField(String field, String value) {

		List<ScientificPaperIndexUnit> resultsList = new ArrayList<ScientificPaperIndexUnit>();

		System.out.println(field + value);

		HighlightBuilder hb = new HighlightBuilder();
		hb.field("pdfText");

		SearchResponse response = elasticSearchTemplate.getClient().prepareSearch("scientificpaper").highlighter(hb)
				.setQuery(QueryBuilders.matchQuery(field, value)).get();

		System.out.println(response);

		for (SearchHit o : response.getHits()) {

			ObjectMapper objectMapper = new ObjectMapper();
			ScientificPaperIndexUnit result = null;

			try {
				result = objectMapper.readValue(o.getSourceAsString(), ScientificPaperIndexUnit.class);
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

	// PhrazeQuery
	// https://www.elastic.co/guide/en/elasticsearch/reference/master/query-dsl-match-query-phrase.html

	@Override
	public List<ScientificPaperIndexUnit> searchByMultipleFields(Map<String, String> json) {

		List<ScientificPaperIndexUnit> resultsList = new ArrayList<ScientificPaperIndexUnit>();
		BoolQueryBuilder qb = QueryBuilders.boolQuery();

		for (Map.Entry<String, String> item : json.entrySet()) {
			qb.must(QueryBuilders.matchQuery(item.getKey(), item.getValue()));
		}

		SearchResponse response = elasticSearchTemplate.getClient().prepareSearch("scientificpaper").setQuery(qb).get();

		for (SearchHit o : response.getHits()) {
			ObjectMapper objectMapper = new ObjectMapper();
			ScientificPaperIndexUnit result = null;

			try {
				result = objectMapper.readValue(o.getSourceAsString(), ScientificPaperIndexUnit.class);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ScientificPaperIndexUnit> searchByMultipleOptionalFields(Map<String, Object> json) {

		List<ScientificPaperIndexUnit> resultsList = new ArrayList<ScientificPaperIndexUnit>();
		List<String> listOfOptionalFields = null;
		List<String> listOfPhraseFields = null;

		BoolQueryBuilder qb = QueryBuilders.boolQuery();
		
		System.out.println("Recieved json : " + json);
		

			

		
		if(json.toString().equals("{}")) {
			System.out.println("Empty json: " + "{}");
			qb.should(QueryBuilders.matchAllQuery());
			
		} else if (json.get("optional") != null && json.get("phrase") == null) {
			System.out.println("optional != null & phrase == null");

			listOfOptionalFields = (List) json.get("optional");

			for (Map.Entry<String, Object> item : json.entrySet()) {

				if (item.getKey().equals("optional"))
					continue;

				if (listOfOptionalFields.contains(item.getKey()))
					qb.should(QueryBuilders.matchQuery(item.getKey(), item.getValue()));
				else
					qb.must(QueryBuilders.matchQuery(item.getKey(), item.getValue()));
			}

		} else if (json.get("optional") == null && json.get("phrase") != null) {
			System.out.println("optional == null & phrase != null");

			listOfPhraseFields = (List) json.get("phrase");

			for (Map.Entry<String, Object> item : json.entrySet()) {

				if (item.getKey().equals("phrase"))
					continue;

				if (listOfPhraseFields.contains(item.getKey()))
					qb.must(QueryBuilders.matchPhraseQuery(item.getKey(), item.getValue()));
				else
					qb.must(QueryBuilders.matchQuery(item.getKey(), item.getValue()));
			}

		} else if (json.get("optional") != null && json.get("phrase") != null) {
			System.out.println("optional != null & phrase != null");

			listOfOptionalFields = (List) json.get("optional");
			listOfPhraseFields = (List) json.get("phrase");

			for (Map.Entry<String, Object> item : json.entrySet()) {

				if (item.getKey().equals("optional") || item.getKey().equals("phrase"))
					continue;

				// proveravam da li postoji bool query ako postoji onda proverim da li je i
				// phrase, ako jeste onda je phrase query nije match query
				if (listOfOptionalFields.contains(item.getKey()))
					if (listOfPhraseFields.contains(item.getKey()))
						qb.should(QueryBuilders.matchPhraseQuery(item.getKey(), item.getValue()));
					else
						qb.should(QueryBuilders.matchQuery(item.getKey(), item.getValue()));
				else if (listOfPhraseFields.contains(item.getKey()))
					qb.must(QueryBuilders.matchPhraseQuery(item.getKey(), item.getValue()));
				else
					qb.must(QueryBuilders.matchQuery(item.getKey(), item.getValue()));

				// ostatak slucajeva, kada imam npr 2 optional 3 phrase, taj treci phrase je
				// obican phrase query ali ga nije uhvatio gore

			}

			for (Map.Entry<String, Object> item : json.entrySet()) {

				if (item.getKey().equals("optional") || item.getKey().equals("phrase"))
					continue;

				for (String phrase : listOfPhraseFields) {
					if (!listOfOptionalFields.contains(phrase))
						qb.must(QueryBuilders.matchPhraseQuery(item.getKey(), item.getValue()));
				}

			}

		} else {
			System.out.println("optional == null & phrase == null");

			for (Map.Entry<String, Object> item : json.entrySet()) {

				if (item.getKey().equals("optional") || item.getKey().equals("phrase"))
					continue;

				qb.must(QueryBuilders.matchQuery(item.getKey(), (String) item.getValue()));
			}

		}

		// Dinamicki sazetak - Highlighter
		HighlightBuilder hb = new HighlightBuilder();

		for (Map.Entry<String, Object> item : json.entrySet()) {

			if (item.getKey().equals("optional") || item.getKey().equals("phrase"))
				continue;

			hb.field(item.getKey());
		}
		
		System.out.println(qb);

		SearchResponse response = elasticSearchTemplate.getClient().prepareSearch("scientificpaper").highlighter(hb)
				.setQuery(qb).get();

		for (SearchHit o : response.getHits()) {
			ObjectMapper objectMapper = new ObjectMapper();
			ScientificPaperIndexUnit result = null;

			try {
				result = objectMapper.readValue(o.getSourceAsString(), ScientificPaperIndexUnit.class);

				// Dinamicki sazetak - Highlighter
				for (String item : o.getHighlightFields().keySet()) {
					if (item.equals("pdfText"))
						result.setPdfText(o.getHighlightFields().get("pdfText").fragments()[0].string());

					if (item.equals("keywords"))
						result.setKeywords(o.getHighlightFields().get("keywords").fragments()[0].string());

					if (item.equals("title"))
						result.setTitle(o.getHighlightFields().get("title").fragments()[0].string());
				}

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

	@Override
	public List<ScientificPaperIndexUnit> searchByMoreLikeThis(Long id) {

		List<ScientificPaperIndexUnit> resultsList = new ArrayList<ScientificPaperIndexUnit>();
		String searchText = elasticRepository.findById(id).get().getPdfText();
		System.out.println("Search text: " + searchText);
		
		// tekst koji pretrazujem
		String searchArray[] = { searchText };

		// polja koja pretrazujem
		String fields[] = { "pdfText" };

		// ovde zadam id dokumenta u odnosu na koji pretrazujem
		MoreLikeThisQueryBuilder.Item[] items = { new MoreLikeThisQueryBuilder.Item("scientificpaper", "paper", id.toString()) };

		MoreLikeThisQueryBuilder qb = QueryBuilders.moreLikeThisQuery(fields, searchArray, items);
		
		// The minimum document frequency below which the terms will be ignored from the input document. Defaults to 5.
		qb.minDocFreq(1);
		// The minimum term frequency below which the terms will be ignored from the input document. Defaults to 2.
		qb.minTermFreq(1);
		// The analyzer that is used to analyze the free form text. Defaults to the analyzer associated with the first field in fields.
		qb.analyzer("serbian-analyzer");
		// The maximum number of query terms that will be selected. Increasing this value gives greater accuracy at the expense of query execution speed. Defaults to 25.
		qb.maxQueryTerms(100);
		

		
		System.out.println("\nRequest: " + qb);
		SearchResponse response = elasticSearchTemplate.getClient().prepareSearch("scientificpaper").setQuery(qb).get();

		//System.out.println(response);

		for (SearchHit o : response.getHits()) {
			ObjectMapper objectMapper = new ObjectMapper();
			ScientificPaperIndexUnit result = null;

			try {
				result = objectMapper.readValue(o.getSourceAsString(), ScientificPaperIndexUnit.class);
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

	@Override
	public List<ScientificPaperIndexUnit> searchByGeoPoint(Double latitude, Double longitude) {

		List<ScientificPaperIndexUnit> resultsList = new ArrayList<ScientificPaperIndexUnit>();

		GeoDistanceQueryBuilder qb = new GeoDistanceQueryBuilder("geo_point");

		qb.point(latitude, longitude).distance(1, DistanceUnit.KILOMETERS);

		System.out.println("GeoSearch: latitude: " + latitude + " , longitude: " + longitude); 
		System.out.println(qb);
		
		SearchResponse response = elasticSearchTemplate.getClient().prepareSearch("scientificpaper").setQuery(qb).get();

		for (SearchHit o : response.getHits()) {
			ObjectMapper objectMapper = new ObjectMapper();
			ScientificPaperIndexUnit result = null;

			try {
				result = objectMapper.readValue(o.getSourceAsString(), ScientificPaperIndexUnit.class);
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
