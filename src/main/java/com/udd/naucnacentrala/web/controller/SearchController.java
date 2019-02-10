package com.udd.naucnacentrala.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udd.naucnacentrala.elasticsearch.service.ElasticSearchService;

@RestController
@RequestMapping(value="/api/search")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SearchController {

	@Autowired
	private ElasticSearchService elasticSearchService;
	
	@GetMapping
	@RequestMapping(value="{searchField}/{searchValue}")
	public ResponseEntity<?> searchByOneField(@PathVariable("searchField") String searchField, @PathVariable("searchValue") String searchValue) {
		
		return new ResponseEntity(elasticSearchService.searchByOneField(searchField, searchValue), HttpStatus.OK);
	}
	
	@PostMapping
	@RequestMapping(value = "multipleFields")
	public ResponseEntity<?> searchByMultipleFields(@RequestBody Map<String, String> json) {
		
		return new ResponseEntity(elasticSearchService.searchByMultipleFields(json), HttpStatus.OK);
	}
	
	@PostMapping
	@RequestMapping(value = "multipleOptionalFields")
	public ResponseEntity<?> searchByMultipleOptionalFields(@RequestBody Map<String, Object> json) {
		
		return new ResponseEntity(elasticSearchService.searchByMultipleOptionalFields(json), HttpStatus.OK);
	}
	
	@GetMapping
	@RequestMapping(value = "moreLikeThis/{id}")
	public ResponseEntity<?> searchByMoreLikeThis(@PathVariable("id") Long id) {
		
		return new ResponseEntity(elasticSearchService.searchByMoreLikeThis(id), HttpStatus.OK);
	}

	@GetMapping
	@RequestMapping(value = "geoPoint/{latitude}/{longitude}")
	public ResponseEntity<?> searchByGeoPoint(@PathVariable("latitude") Double latitude, @PathVariable("longitude") Double longitude) {
		
		return new ResponseEntity(elasticSearchService.searchByGeoPoint(latitude, longitude), HttpStatus.OK);
	}
}


