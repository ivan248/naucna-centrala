package com.udd.naucnacentrala.elasticsearch.service;

import java.util.List;
import java.util.Map;

import com.udd.naucnacentrala.elasticsearch.ScientificPaperDTO;

public interface ElasticSearchService {

	List<ScientificPaperDTO> searchByOneField(String field, String value);
	List<ScientificPaperDTO> searchByMultipleFields(Map<String, String> json);
	List<ScientificPaperDTO> searchByMultipleOptionalFields(Map<String, Object> json);
	List<ScientificPaperDTO> searchByMoreLikeThis(String similarText);
	List<ScientificPaperDTO> searchByGeoPoint(Long longitude, Long latitude);
}
