package com.udd.naucnacentrala.elasticsearch.service;

import java.util.List;
import java.util.Map;

import com.udd.naucnacentrala.elasticsearch.ScientificPaperIndexUnit;

public interface ElasticSearchService {

	List<ScientificPaperIndexUnit> searchByOneField(String field, String value);
	List<ScientificPaperIndexUnit> searchByMultipleFields(Map<String, String> json);
	List<ScientificPaperIndexUnit> searchByMultipleOptionalFields(Map<String, Object> json);
	List<ScientificPaperIndexUnit> searchByMoreLikeThis(Long id);
	List<ScientificPaperIndexUnit> searchByGeoPoint(Double latitude, Double longitude);
}
