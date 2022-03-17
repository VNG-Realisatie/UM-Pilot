package com.cap.pocvng.service;

import com.cap.pocvng.config.ConfigProperties;
import com.cap.pocvng.dto.InlineResponse200;
import com.cap.pocvng.dto.InlineResponse2001;
import com.cap.pocvng.dto.VacatureMatchesRequest;
import com.cap.pocvng.dto.VacatureMatchesRequestGemeente;
import com.cap.pocvng.entity.AanvraagVacature;
import com.cap.pocvng.exception.NoMoreAccessRequestsLeftException;
import com.cap.pocvng.exception.UnprocessableException;
import com.cap.pocvng.exception.VraagIdNotFoundException;
import com.cap.pocvng.mapper.SimpleMapper;
import com.cap.pocvng.repository.AanvraagVacatureRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@JsonTest
@ActiveProfiles("test")
class GemeenteServiceTest {

    @Mock
    AanvraagVacatureRepository repository;
    @Mock
    RestTemplate restTemplate;
    @Mock
    SimpleMapper mapper;
    @Mock
    ConfigProperties properties;

    @Autowired
    ObjectMapper objectMapper;

    private GemeenteService service;

    private static final String URL = "example.com";
    private static final String VUM_URL = "example2.com";
    private static final String OIN = "123456789";
    private static final String VRAAG_ID = "somevraagid";
    private static final String VUM_ID = "somevumid";
    private static final String PATH = "./src/test/resources/";

    private VacatureMatchesRequestGemeente requestGemeente;
    private VacatureMatchesRequest request;
    private InlineResponse200 responseToMatchesRequest;
    private InlineResponse2001 responseToDetailRequest;
    private AanvraagVacature aanvraagVacature;

    @BeforeEach
    void setUp() throws IOException {
        service = new GemeenteService(repository, restTemplate, mapper, properties);

        requestGemeente = objectMapper.readValue(new File(PATH, "vacature-matches-request-gemeente.json"), VacatureMatchesRequestGemeente.class);
        request = objectMapper.readValue(new File(PATH, "vacature-matches-request.json"),
                VacatureMatchesRequest.class);
        responseToMatchesRequest = objectMapper.readValue(new File(PATH, "inline-response200.json"),
                InlineResponse200.class);
        aanvraagVacature = objectMapper.readValue(new File(PATH, "aanvraag-vacature.json"),
                AanvraagVacature.class);
        responseToDetailRequest = objectMapper.readValue(new File(PATH, "inline-response2001.json"),
                InlineResponse2001.class);

    }

    @Test
    void findByVraagIdAndOinSuccess() {
        when(repository.findByVraagIdAndOin(VRAAG_ID, OIN)).thenReturn(aanvraagVacature);

        assertThat(service.findByVraagIdAndOin(VRAAG_ID, OIN)).isEqualTo(aanvraagVacature);
    }

    @Test
    void findByVraagIdAndOinFailure() {
        when(repository.findByVraagIdAndOin(VRAAG_ID, OIN)).thenReturn(null);

        assertThatThrownBy(() -> {
            service.findByVraagIdAndOin(VRAAG_ID, OIN);
        }).isInstanceOf(VraagIdNotFoundException.class)
                .hasMessage("400 BAD_REQUEST \"Vraag ID niet gevonden\"");
    }

    @Test
    void makeRequestMatchesVumCorrect() {
        when(properties.getCallbackUrl()).thenReturn(URL);
        when(properties.getVumUrlMatches()).thenReturn(VUM_URL);
        when(mapper.vacatureMatchesRequestGemeenteToVacatureMatchesRequest(requestGemeente))
                .thenReturn(request);
        when(restTemplate.postForObject(VUM_URL, request, InlineResponse200.class))
                .thenReturn(responseToMatchesRequest);

        InlineResponse200 result = service.makeRequestMatchesVum(requestGemeente, OIN);

        assertThat(result).isEqualTo(responseToMatchesRequest);
    }

    @Test
    void makeRequestMatchesVumVraagIdAlreadyInDB() {
        responseToMatchesRequest.setVraagID(VRAAG_ID);

        when(properties.getCallbackUrl()).thenReturn(URL);
        when(properties.getVumUrlMatches()).thenReturn(VUM_URL);
        when(mapper.vacatureMatchesRequestGemeenteToVacatureMatchesRequest(requestGemeente))
                .thenReturn(request);
        when(restTemplate.postForObject(VUM_URL, request, InlineResponse200.class))
                .thenReturn(responseToMatchesRequest);
        when(repository.existsById(VRAAG_ID)).thenReturn(true);

        assertThatThrownBy(() -> {
            service.makeRequestMatchesVum(requestGemeente, OIN);
        }).isInstanceOf(UnprocessableException.class)
                .hasMessage("422 UNPROCESSABLE_ENTITY \"Vraag id bestaat al in database\"");
    }

    @Test
    void makeRequestMatchesVumNullResponse() {
        when(properties.getCallbackUrl()).thenReturn(URL);
        when(properties.getVumUrlMatches()).thenReturn(VUM_URL);
        when(mapper.vacatureMatchesRequestGemeenteToVacatureMatchesRequest(requestGemeente))
                .thenReturn(request);
        when(restTemplate.postForObject(VUM_URL, request, InlineResponse200.class))
                .thenReturn(null);

        assertThatThrownBy(() -> {
            service.makeRequestMatchesVum(requestGemeente, OIN);
        }).isInstanceOf(UnprocessableException.class)
                .hasMessage("422 UNPROCESSABLE_ENTITY \"Rest client error\"");

    }

    @Test
    void makeRequestMatchesVumRestClientException() {
        when(properties.getCallbackUrl()).thenReturn(URL);
        when(properties.getVumUrlMatches()).thenReturn(VUM_URL);
        when(mapper.vacatureMatchesRequestGemeenteToVacatureMatchesRequest(requestGemeente))
                .thenReturn(request);
        when(restTemplate.postForObject(VUM_URL, request, InlineResponse200.class))
                .thenThrow(RestClientException.class);

        assertThatThrownBy(() -> {
            service.makeRequestMatchesVum(requestGemeente, OIN);
        }).isInstanceOf(UnprocessableException.class)
                .hasMessage("422 UNPROCESSABLE_ENTITY \"Rest client error\"");

    }

    @Test
    void findAllReturnEmpty() {
        when(repository.findByOin(OIN)).thenReturn(List.of());

        assertThat(service.findAll(OIN)).isEmpty();
    }

    @Test
    void findAllReturnResult() {
        when(repository.findByOin(OIN)).thenReturn(List.of(aanvraagVacature));

        assertThat(service.findAll(OIN)).hasSameElementsAs(List.of(aanvraagVacature));
    }

    @Test
    void makeRequestDetailVacatureSuccess() {
        // Still have access left.
        aanvraagVacature.setTimesAccessLeft(10);

        when(properties.getVumUrlVumId()).thenReturn(VUM_URL);
        when(restTemplate.getForObject(VUM_URL + VUM_ID, InlineResponse2001.class))
                .thenReturn(responseToDetailRequest);
        when(repository.findByVraagIdAndOin(VRAAG_ID, OIN)).thenReturn(aanvraagVacature);

        assertThat(service.makeRequestDetailVacature(VRAAG_ID, VUM_ID, OIN)).isEqualTo(responseToDetailRequest.getVacature());
    }

    @Test
    void makeRequestDetailVacatureVraagIdNotFoundFailure() {
        when(repository.findByVraagIdAndOin(VRAAG_ID, OIN)).thenReturn(null);

        assertThatThrownBy(() -> {
            service.makeRequestDetailVacature(VRAAG_ID, VUM_ID, OIN);
        }).isInstanceOf(VraagIdNotFoundException.class)
                .hasMessage("400 BAD_REQUEST \"Vraag ID niet gevonden\"");
    }

    @Test
    void makeRequestDetailVacatureNoAccessLeftFailure() {
        when(repository.findByVraagIdAndOin(VRAAG_ID, OIN)).thenReturn(aanvraagVacature);

        assertThatThrownBy(() -> {
            service.makeRequestDetailVacature(VRAAG_ID, VUM_ID, OIN);
        }).isInstanceOf(NoMoreAccessRequestsLeftException.class)
                .hasMessage("429 TOO_MANY_REQUESTS \"limiet is overschreden voor deze vraagID\"");
    }

    @Test
    void makeRequestDetailVacatureRestClientFailure() {
        // Still have access left.
        aanvraagVacature.setTimesAccessLeft(10);

        when(properties.getVumUrlVumId()).thenReturn(VUM_URL);
        when(restTemplate.getForObject(VUM_URL + VUM_ID, InlineResponse2001.class))
                .thenThrow(RestClientException.class);
        when(repository.findByVraagIdAndOin(VRAAG_ID, OIN)).thenReturn(aanvraagVacature);

        assertThatThrownBy(() -> {
            service.makeRequestDetailVacature(VRAAG_ID, VUM_ID, OIN);
        }).isInstanceOf(UnprocessableException.class)
                .hasMessage("422 UNPROCESSABLE_ENTITY \"Rest client error\"");

        // When failure, don't drop the times access
        assertThat(aanvraagVacature.getTimesAccessLeft()).isEqualTo(10);


    }

//    @Test
//    void makeRequestDetailVacatureRestClientFailureSetTimesRemainsSame() {
//        // Still have access left.
//        aanvraagVacature.setTimesAccessLeft(10);
//
//        when(properties.getVumUrlVumId()).thenReturn(VUM_URL);
//        when(restTemplate.getForObject(VUM_URL + VUM_ID, InlineResponse2001.class))
//                .thenThrow(RestClientException.class);
//        when(repository.findByVraagId(VRAAG_ID)).thenReturn(aanvraagVacature);
//
//        service.makeRequestDetailVacature(VRAAG_ID, VUM_ID);
//
//        assertThat(aanvraagVacature.getTimesAccessLeft()).isEqualTo(10);
//
//
//    }


}