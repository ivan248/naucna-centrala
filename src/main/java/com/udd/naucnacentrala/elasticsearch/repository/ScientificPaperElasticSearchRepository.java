package com.udd.naucnacentrala.elasticsearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.udd.naucnacentrala.elasticsearch.ScientificPaperDTO;

@Repository
public interface ScientificPaperElasticSearchRepository extends ElasticsearchRepository<ScientificPaperDTO, Long>{

	List<ScientificPaperDTO> findByAuthor(String name);
}
