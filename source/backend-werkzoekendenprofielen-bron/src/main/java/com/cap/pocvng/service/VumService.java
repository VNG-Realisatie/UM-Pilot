package com.cap.pocvng.service;

import com.cap.pocvng.config.ConfigProperties;
import com.cap.pocvng.dto.WerkzoekendeMatchingProfielen;
import com.cap.pocvng.dto.WerkzoekendeProfielMatchesRequest;
import com.cap.pocvng.entity.MPWerkzoekendeMatch;
import com.cap.pocvng.entity.Werkzoekende;
import com.cap.pocvng.mapper.SimpleMapper;
import com.cap.pocvng.repository.WerkzoekendeRepository;
import com.cap.pocvng.repository.WerkzoekendeSpecifications;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class VumService {

    private final WerkzoekendeRepository werkzoekendeRepository;

    private final SimpleMapper mapper;

    private static int MAX_AMOUNT;

    @Autowired
    public VumService(WerkzoekendeRepository werkzoekendeRepository, SimpleMapper mapper, ConfigProperties properties) {
        this.werkzoekendeRepository = werkzoekendeRepository;
        this.mapper = mapper;
        MAX_AMOUNT = properties.getMaxAmountResponse();
    }

    /**
     * Find Werkzoekende with given ID.
     *
     * @param idWerkzoekende id of Werkzoekende to be found.
     * @return Werkzoekende with given ID.
     */
    public Optional<Werkzoekende> findById(String idWerkzoekende, String oin) {
        return werkzoekendeRepository.findByIdWerkzoekendeAndOin(idWerkzoekende, oin);
    }


    /**
     * Find all Werkzoekende who match the given request.
     *
     * @param matchesRequest the criteria used to filter the Werkzoekende from the DB.
     * @param toOin          the OIN of the municipality.
     * @return Pair of boolean and matching profiles. The boolean is true if the matching profiles exceed the MAX_AMOUNT.
     * Else the boolean is false.
     */
    public ImmutablePair<Boolean, WerkzoekendeMatchingProfielen> match(WerkzoekendeProfielMatchesRequest matchesRequest, String toOin) {
        // Get the specifications based on the matchesRequest.
        Specification<Werkzoekende> werkzoekendeSpecifications = WerkzoekendeSpecifications
                .createWerkZoekendeSpecification(matchesRequest.getVraagObject(), toOin);
        // Search the DB with these specifications.
        List<Werkzoekende> werkzoekenden = werkzoekendeRepository.findAll(werkzoekendeSpecifications);
        // Shuffle and limit werkzoekenden.
        List<Werkzoekende> limitedWerkzoekenden = pickRandom(werkzoekenden, MAX_AMOUNT);
        // use mapper to map Werkzoekende to MPWerkzoekendeMatch.
        List<MPWerkzoekendeMatch> mpWerkzoekendenmatch = limitedWerkzoekenden
                .stream()
                .map(mapper::werkzoekendeToMPWerkzoekendeMatch)
                .collect(Collectors.toList());
        return new ImmutablePair<>(werkzoekenden.size() >= MAX_AMOUNT, new WerkzoekendeMatchingProfielen(mpWerkzoekendenmatch));
    }

    /**
     * Util function to randomly pick max amount of werkzoekende coming from the DB.
     *
     * @param werkzoekendeList List to be limited.
     * @param maxAmount        Max amount.
     * @return Shuffled and limited list of werkzoekende.
     */
    public List<Werkzoekende> pickRandom(List<Werkzoekende> werkzoekendeList, int maxAmount) {
        // Already less than max amount.
        if (werkzoekendeList.size() <= maxAmount) {
            return werkzoekendeList;
        }
        //Shuffle in O(n).
        Collections.shuffle(werkzoekendeList);
        // Pick max amount from new list.
        return werkzoekendeList.stream().limit(maxAmount).collect(Collectors.toList());

    }
}
