package com.cap.pocvng.service;

import com.cap.pocvng.config.ConfigProperties;
import com.cap.pocvng.dto.WerkzoekendeMatchingProfielen;
import com.cap.pocvng.dto.WerkzoekendeProfielMatchesRequest;
import com.cap.pocvng.entity.MPWerkzoekende;
import com.cap.pocvng.entity.MPWerkzoekendeMatch;
import com.cap.pocvng.entity.Werkzoekende;
import com.cap.pocvng.mapper.SimpleMapper;
import com.cap.pocvng.repository.RequestRepository;
import com.cap.pocvng.repository.WerkzoekendeRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
// TODO: get rid of springboottest if possible to inject ConfigProperties without entire application.
@ExtendWith(MockitoExtension.class)

public class VumShuffleTest {

    @Mock
    private WerkzoekendeRepository werkzoekendeRepository;

    @Mock
    private RequestRepository requestRepository;

    @Autowired
    private SimpleMapper mapper;

    @Autowired
    private ConfigProperties properties;

    private static int MAX_AMOUNT;
    private static final String OIN = "123456789";
    private static final String OIN2 = "987654321";
    private static VumService service;
    private WerkzoekendeProfielMatchesRequest request;

    @BeforeEach
    void setUp() {
        MAX_AMOUNT = properties.getMaxAmountResponse();
        service = new VumService(werkzoekendeRepository, mapper, properties);
        request = new WerkzoekendeProfielMatchesRequest(new MPWerkzoekende());
    }

    @Test
    void returnEqualListIfSmallerOrEqualToMaxAmount() {

        List<Werkzoekende> werkzoekendeListMaxAmount = getList(MAX_AMOUNT);

        when(werkzoekendeRepository.findAll(ArgumentMatchers.<Specification<Werkzoekende>>any())).thenReturn(werkzoekendeListMaxAmount);

        ImmutablePair<Boolean, WerkzoekendeMatchingProfielen> result = service.match(request, OIN);

        List<MPWerkzoekendeMatch> resultList = result.getRight().getMatches();

        // Use ID to compare if the entities are the same.
        List<String> list1 = werkzoekendeListMaxAmount.stream().map(x -> x.getIdWerkzoekende()).collect(Collectors.toList());
        List<String> list2 = resultList.stream().map(x -> x.getIdWerkzoekende()).collect(Collectors.toList());

        assertThat(list1).isEqualTo(list2);

    }

    @Test
    void returnShuffledListIfGreaterThanMaxAmount() {

        List<Werkzoekende> werkzoekendeListMaxAmount = getList(MAX_AMOUNT + 1);
        List<Werkzoekende> originalList = new ArrayList<>(werkzoekendeListMaxAmount);

        when(werkzoekendeRepository.findAll(ArgumentMatchers.<Specification<Werkzoekende>>any())).thenReturn(werkzoekendeListMaxAmount);

        ImmutablePair<Boolean, WerkzoekendeMatchingProfielen> result = service.match(request, OIN);

        List<MPWerkzoekendeMatch> resultList = result.getRight().getMatches();

        List<String> list1 = originalList.stream().map(x -> x.getIdWerkzoekende()).collect(Collectors.toList());
        List<String> list2 = resultList.stream().map(x -> x.getIdWerkzoekende()).collect(Collectors.toList());


        // The list should be shuffled and limited to max amount.
        assertThat(list1).isNotEqualTo(list2);
        assertThat(list2).hasSize(MAX_AMOUNT);

    }

    /**
     * Utility function to get a list of Werkzoekende with n numOfElements.
     */
    List<Werkzoekende> getList(int numOfElements) {

        List<Werkzoekende> werkzoekendeList = IntStream.range(0, numOfElements)
                .mapToObj(x -> new Werkzoekende()) //
                .collect(Collectors.toList());

        // Add UUID ID to identify every entity.
        werkzoekendeList.forEach(x -> x.setIdWerkzoekende(UUID.randomUUID().toString()));

        return werkzoekendeList;
    }


}
