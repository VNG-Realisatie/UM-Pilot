package com.cap.pocvng.service;

import com.cap.pocvng.config.ConfigProperties;
import com.cap.pocvng.dto.VacatureMatchesRequest;
import com.cap.pocvng.dto.VacatureMatchingProfielen;
import com.cap.pocvng.entity.MPVacature;
import com.cap.pocvng.entity.MPVacatureMatch;
import com.cap.pocvng.entity.Vacature;
import com.cap.pocvng.mapper.SimpleMapper;
import com.cap.pocvng.repository.RequestRepository;
import com.cap.pocvng.repository.VacatureRepository;
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
@ExtendWith(MockitoExtension.class)
class VumServiceTest {

    @Mock
    VacatureRepository repository;

    @Mock
    RequestRepository requestRepository;

    @Mock
    SimpleMapper mapper;

    @Autowired
    private ConfigProperties properties;

    private VumService service;
    private static int MAX_AMOUNT;
    private static VacatureMatchesRequest request;
    private static MPVacatureMatch mpVacatureMatch;
    private static Vacature vacature;
    private static final String ID1 = "ID1";
    private static final String OIN = "123456789";
    private static final String differentOIN = "987654321";



    @BeforeEach
    void setUp() {
        vacature = new Vacature();
        MPVacature mpVacature = new MPVacature();
        request = new VacatureMatchesRequest(mpVacature);
        mpVacatureMatch = new MPVacatureMatch();

        MAX_AMOUNT = properties.getMaxAmountResponse();
        service = new VumService(repository, requestRepository,  mapper, properties);
    }

    @Test
    void findByIdSuccess() {
        vacature.setOin(OIN);

        when(repository.findByIdVacatureAndOin(ID1, OIN)).thenReturn(Optional.of(vacature));

        Optional<Vacature> result = service.findById(ID1, OIN);

        assertThat(result).hasValue(vacature);
    }

    @Test
    void findByIdFailure() {
        when(repository.findByIdVacatureAndOin(ID1, OIN)).thenReturn(Optional.empty());

        Optional<Vacature> result = service.findById(ID1, OIN);

        assertThat(result).isEmpty();
    }

    @Test
    void matchesEmpty() {
        when(repository.findAll(ArgumentMatchers.<Specification<Vacature>>any())).thenReturn(List.of());

        ImmutablePair<Boolean, VacatureMatchingProfielen> result = service.match(request, OIN, differentOIN);

        assertThat(result.getLeft()).isFalse();
        assertThat(result.getRight().getMatches()).isEmpty();

    }

    @Test
    void matchesOne() {
        when(repository.findAll(ArgumentMatchers.<Specification<Vacature>>any())).thenReturn(List.of(vacature));
        when(mapper.vacatureToMPVacatureMatch(any())).thenReturn(mpVacatureMatch);

        ImmutablePair<Boolean, VacatureMatchingProfielen> result = service.match(request, OIN, differentOIN );
        assertThat(result.getLeft()).isFalse();
        assertThat(result.getRight().getMatches()).containsExactly(mpVacatureMatch);

    }

    @Test
    void matchesMaxAmount() {

        List<Vacature> vacatureList100 = new ArrayList<>(Collections.nCopies(MAX_AMOUNT, vacature));
        List<MPVacatureMatch> mPvacatureList100 = new ArrayList<>(Collections.nCopies(MAX_AMOUNT, mpVacatureMatch));


        when(repository.findAll(ArgumentMatchers.<Specification<Vacature>>any())).thenReturn(vacatureList100);
        when(mapper.vacatureToMPVacatureMatch(any())).thenReturn(mpVacatureMatch);

        ImmutablePair<Boolean, VacatureMatchingProfielen> result = service.match(request, OIN, differentOIN);
        assertThat(result.getLeft()).isTrue();
        assertThat(result.getRight().getMatches()).hasSameElementsAs(mPvacatureList100);
    }

    @Test
    void matchesMaxAmountMinusOne() {

        List<Vacature> vacatureList100 = new ArrayList<>(Collections.nCopies(MAX_AMOUNT - 1, vacature));
        List<MPVacatureMatch> mPvacatureList100 = new ArrayList<>(Collections.nCopies(MAX_AMOUNT - 1, mpVacatureMatch));


        when(repository.findAll(ArgumentMatchers.<Specification<Vacature>>any())).thenReturn(vacatureList100);
        when(mapper.vacatureToMPVacatureMatch(any())).thenReturn(mpVacatureMatch);

        ImmutablePair<Boolean, VacatureMatchingProfielen> result = service.match(request, OIN, differentOIN);
        assertThat(result.getLeft()).isFalse();
        assertThat(result.getRight().getMatches()).hasSameElementsAs(mPvacatureList100);
    }
}