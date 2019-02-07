package com.udd.naucnacentrala.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udd.naucnacentrala.elasticsearch.ScientificPaperIndexUnit;
import com.udd.naucnacentrala.service.MagazineService;

@RestController
@RequestMapping(value="/api/magazine")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MagazineController {
	
	@Autowired
	private MagazineService magazineService;
	
	@Autowired
	private ElasticsearchTemplate elasticSearchTemplate;
	
	@RequestMapping(value="/getAll", 
			method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken() throws AuthenticationException {
	
		return new ResponseEntity(magazineService.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/testES", 
			method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> testES() throws AuthenticationException {
		
		SearchResponse response = elasticSearchTemplate.getClient().prepareSearch("scientificpaper").setQuery(QueryBuilders.matchQuery("author", "Marko")).get();
		
		List<Object> l = new ArrayList<Object>();
		List<Object> h = new ArrayList<Object>();
		
		for(SearchHit o : response.getHits()) {
			l.add(o.getSourceAsString());
			h.add(o.getHighlightFields());
			
			ObjectMapper objectMapper = new ObjectMapper();
			ScientificPaperIndexUnit data = null;
			try {
				System.out.println("Usao: ");
				data = objectMapper.readValue(o.getSourceAsString(), ScientificPaperIndexUnit.class);
				System.out.println(data);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(data);
			
		}
		
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	

}
