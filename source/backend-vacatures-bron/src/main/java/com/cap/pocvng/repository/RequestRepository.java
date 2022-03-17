package com.cap.pocvng.repository;

import com.cap.pocvng.dto.ElkEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends ElasticsearchRepository<ElkEntity, String> {
}
