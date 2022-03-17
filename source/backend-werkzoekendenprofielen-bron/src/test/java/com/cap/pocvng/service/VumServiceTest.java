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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
// TODO: get rid of springboottest if possible to inject ConfigProperties without entire application.
@ExtendWith(MockitoExtension.class)
class VumServiceTest {

    @Mock
    WerkzoekendeRepository werkzoekendeRepository;

    @Mock
    RequestRepository requestRepository;

    @Mock
    SimpleMapper mapper;

    @Autowired
    private ConfigProperties properties;

    private VumService service;
    private static int MAX_AMOUNT;
    private static WerkzoekendeProfielMatchesRequest request;
    private static MPWerkzoekendeMatch mpWerkzoekendeMatch;
    private static Werkzoekende werkzoekende;
    private static final String ID1 = "ID1";
    private static final String OIN = "123456789";
    private static final String OIN2 = "987654321";



    @BeforeEach
    void setUp() {
        werkzoekende = new Werkzoekende();
        MPWerkzoekende mpWerkzoekende = new MPWerkzoekende();
        request = new WerkzoekendeProfielMatchesRequest(mpWerkzoekende);
        mpWerkzoekendeMatch = new MPWerkzoekendeMatch();

        MAX_AMOUNT = properties.getMaxAmountResponse();
        service = new VumService(werkzoekendeRepository, mapper, properties);
    }

    @Test
    void findByIdSuccess() {
        werkzoekende.setOin(OIN);

        when(werkzoekendeRepository.findByIdWerkzoekendeAndOin(ID1, OIN)).thenReturn(Optional.of(werkzoekende));

        Optional<Werkzoekende> result = service.findById(ID1, OIN);

        assertThat(result).hasValue(werkzoekende);
    }

    @Test
    void findByIdFailure() {
        when(werkzoekendeRepository.findByIdWerkzoekendeAndOin(ID1, OIN)).thenReturn(Optional.empty());

        Optional<Werkzoekende> result = service.findById(ID1, OIN);

        assertThat(result).isEmpty();
    }

    @Test
    void matchesEmpty() {
        when(werkzoekendeRepository.findAll(ArgumentMatchers.<Specification<Werkzoekende>>any())).thenReturn(List.of());

        ImmutablePair<Boolean, WerkzoekendeMatchingProfielen> result = service.match(request, OIN);

        assertThat(result.getLeft()).isFalse();
        assertThat(result.getRight().getMatches()).isEmpty();

    }

    @Test
    void matchesOne() {
        when(werkzoekendeRepository.findAll(ArgumentMatchers.<Specification<Werkzoekende>>any())).thenReturn(List.of(werkzoekende));
        when(mapper.werkzoekendeToMPWerkzoekendeMatch(any())).thenReturn(mpWerkzoekendeMatch);

        ImmutablePair<Boolean, WerkzoekendeMatchingProfielen> result = service.match(request, OIN);
        assertThat(result.getLeft()).isFalse();
        assertThat(result.getRight().getMatches()).containsExactly(mpWerkzoekendeMatch);

    }

    @Test
    void matchesMaxAmount() {

        List<Werkzoekende> werkzoekendeList100 = new ArrayList<>(Collections.nCopies(MAX_AMOUNT, werkzoekende));
        List<MPWerkzoekendeMatch> mPwerkzoekendeList100 = new ArrayList<>(Collections.nCopies(MAX_AMOUNT, mpWerkzoekendeMatch));


        when(werkzoekendeRepository.findAll(ArgumentMatchers.<Specification<Werkzoekende>>any())).thenReturn(werkzoekendeList100);
        when(mapper.werkzoekendeToMPWerkzoekendeMatch(any())).thenReturn(mpWerkzoekendeMatch);

        ImmutablePair<Boolean, WerkzoekendeMatchingProfielen> result = service.match(request, OIN);
        assertThat(result.getLeft()).isTrue();
        assertThat(result.getRight().getMatches()).hasSameElementsAs(mPwerkzoekendeList100);
    }

    @Test
    void matchesMaxAmountMinusOne() {

        List<Werkzoekende> werkzoekendeList100 = new ArrayList<>(Collections.nCopies(MAX_AMOUNT - 1, werkzoekende));
        List<MPWerkzoekendeMatch> mPwerkzoekendeList100 = new ArrayList<>(Collections.nCopies(MAX_AMOUNT - 1, mpWerkzoekendeMatch));


        when(werkzoekendeRepository.findAll(ArgumentMatchers.<Specification<Werkzoekende>>any())).thenReturn(werkzoekendeList100);
        when(mapper.werkzoekendeToMPWerkzoekendeMatch(any())).thenReturn(mpWerkzoekendeMatch);

        ImmutablePair<Boolean, WerkzoekendeMatchingProfielen> result = service.match(request, OIN);
        assertThat(result.getLeft()).isFalse();
        assertThat(result.getRight().getMatches()).hasSameElementsAs(mPwerkzoekendeList100);
    }
}