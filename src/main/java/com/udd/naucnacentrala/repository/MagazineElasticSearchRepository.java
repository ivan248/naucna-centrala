package com.udd.naucnacentrala.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.udd.naucnacentrala.web.dto.MagazineDTO;

@Repository
public interface MagazineElasticSearchRepository extends ElasticsearchRepository<MagazineDTO, Long> {

}
