package com.cap.pocvng.service;

import com.cap.pocvng.dto.ElkEntity;
import com.cap.pocvng.dto.WerkzoekendeProfielMatchesRequest;
import com.cap.pocvng.repository.RequestRepository;
import org.springframework.stereotype.Service;

@Service
public class ElkService {

    private final RequestRepository requestRepository;

    public ElkService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    /**
     * Save the match request in ElasticSearch
     *
     * @param matchesRequest Request to be saved
     * @param toOin          OIN of the recipient
     * @param fromOin        OIN of the sender
     */
    public void handleRequest(WerkzoekendeProfielMatchesRequest matchesRequest, String toOin, String fromOin) {
        requestRepository.save(new ElkEntity(matchesRequest, toOin, fromOin));
    }
}


