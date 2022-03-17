package com.cap.pocvng.service;

import com.cap.pocvng.config.ConfigProperties;
import com.cap.pocvng.dto.ElkEntity;
import com.cap.pocvng.dto.VacatureMatchesRequest;
import com.cap.pocvng.dto.VacatureMatchingProfielen;
import com.cap.pocvng.entity.MPVacatureMatch;
import com.cap.pocvng.entity.Vacature;
import com.cap.pocvng.mapper.SimpleMapper;
import com.cap.pocvng.repository.RequestRepository;
import com.cap.pocvng.repository.VacatureRepository;
import com.cap.pocvng.repository.VacatureSpecifications;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VumService {

    private final VacatureRepository repository;

    private final RequestRepository requestRepository;

    private final SimpleMapper mapper;

    private static int MAX_AMOUNT;

    public VumService(VacatureRepository repository,  RequestRepository requestRepository, SimpleMapper mapper, ConfigProperties properties) {
        this.repository = repository;
        this.requestRepository = requestRepository;
        this.mapper = mapper;
        MAX_AMOUNT = properties.getMaxAmountResponse();

    }

    public Optional<Vacature> findById(String idVacature, String oin) {
        return repository.findByIdVacatureAndOin(idVacature, oin);
    }

    public ImmutablePair<Boolean, VacatureMatchingProfielen> match(VacatureMatchesRequest matchesRequest, String toOin, String fromOin) {

        requestRepository.save(new ElkEntity(matchesRequest, toOin, fromOin));

        // Get the specifications based on the matchesRequest.
        Specification<Vacature> vacatureSpecifications = VacatureSpecifications
                .createVacatureSpecification(matchesRequest.getVraagObject(), toOin);
        // Search the DB with these specifications.
        List<Vacature> vacatures = repository.findAll(vacatureSpecifications);
        // Shuffle and limit vacatures.
        List<Vacature> limitedVacatures = pickRandom(vacatures, MAX_AMOUNT);
        // use mapper to map Vacature to MPVacatureMatch
        List<MPVacatureMatch> mpVacatureMatches = limitedVacatures
                .stream()
                .map(mapper::vacatureToMPVacatureMatch)
                .collect(Collectors.toList());
        return new ImmutablePair<>(vacatures.size() >= MAX_AMOUNT, new VacatureMatchingProfielen(mpVacatureMatches));


    }

    /**
     * Util function to randomly pick max amount of vacature coming from the DB.
     *
     * @param vacatureList List to be limited.
     * @param maxAmount    Max amount.
     * @return Shuffled and limited list of vacature.
     */
    public List<Vacature> pickRandom(List<Vacature> vacatureList, int maxAmount) {
        // Already less than max amount.
        if (vacatureList.size() <= maxAmount) {
            return vacatureList;
        }
        //Shuffle in O(n).
        Collections.shuffle(vacatureList);
        // Pick max amount from new list.
        return vacatureList.stream().limit(maxAmount).collect(Collectors.toList());

    }
}
