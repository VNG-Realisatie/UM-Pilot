package com.cap.pocvng.service;

import com.cap.pocvng.dto.WerkzoekendeProfielMatchesCallbackRequest;
import com.cap.pocvng.entity.AanvraagWerkzoekende;
import com.cap.pocvng.exception.VraagIdNotFoundException;
import com.cap.pocvng.repository.AanvraagWerkzoekendeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@JsonTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class VumServiceTest {

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    AanvraagWerkzoekendeRepository repository;

    private VumService service;

    private AanvraagWerkzoekende aanvraagWerkzoekende;
    private WerkzoekendeProfielMatchesCallbackRequest request;
    private static final String ID = "string";
    private static final String PATH = "./src/test/resources/";

    @BeforeEach
    void setUp() {
        try {
            service = new VumService(repository);
            request = objectMapper.readValue(new File(PATH, "werkzoekende-profiel-matches-callback-request.json"),
                    WerkzoekendeProfielMatchesCallbackRequest.class);
            aanvraagWerkzoekende = objectMapper.readValue(new File(PATH, "aanvraag-werkzoekende.json"),
                    AanvraagWerkzoekende.class);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    void handleCallbackInDb() {
        when(repository.findById(ID)).thenReturn(Optional.ofNullable(aanvraagWerkzoekende));

        service.handleCallback(request);

        verify(repository, times(1)).findById(ID);
        verify(repository, times(1)).save(aanvraagWerkzoekende);

    }

    @Test
    void handleCallbackInDbAddingWerkzoekende() {
        when(repository.findById(ID)).thenReturn(Optional.ofNullable(aanvraagWerkzoekende));
        aanvraagWerkzoekende.setWerkzoekenden(new HashSet<>());

        assertThat(aanvraagWerkzoekende.getWerkzoekenden()).isEmpty();

        service.handleCallback(request);

        assertThat(aanvraagWerkzoekende.getWerkzoekenden()).hasSize(1);


        verify(repository, times(1)).findById(ID);
        verify(repository, times(1)).save(aanvraagWerkzoekende);

    }

    @Test
    void handleCallbackInDbAddingWerkzoekendeToExistingWerkzoekende() {
        when(repository.findById(ID)).thenReturn(Optional.ofNullable(aanvraagWerkzoekende));
        // To ensure the werkzoekenden are different, put id of one of them.
        request.getMatches().getMpWerkzoekendeMatches().get(0).setId(0L);

        assertThat(aanvraagWerkzoekende.getWerkzoekenden()).hasSize(1);

        service.handleCallback(request);

        assertThat(aanvraagWerkzoekende.getWerkzoekenden()).hasSize(2);


        verify(repository, times(1)).findById(ID);
        verify(repository, times(1)).save(aanvraagWerkzoekende);

    }


    @Test
    void handleCallbackNotInDb() {

        when(repository.findById(ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            service.handleCallback(request);
        }).isInstanceOf(VraagIdNotFoundException.class)
                .hasMessage("400 BAD_REQUEST \"Vraag ID niet gevonden\"");

    }
}