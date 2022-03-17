package com.cap.pocvng.service;

import com.cap.pocvng.dto.VacatureMatchesCallbackRequest;
import com.cap.pocvng.entity.AanvraagVacature;
import com.cap.pocvng.entity.MPVacatureMatch;
import com.cap.pocvng.exception.VraagIdNotFoundException;
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

import java.io.File;
import java.io.IOException;
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
    AanvraagVacatureRepository repository;

    private VumService service;

    private AanvraagVacature aanvraagVacature;
    private VacatureMatchesCallbackRequest request;
    private MPVacatureMatch vacature1;
    private MPVacatureMatch vacature2;
    private static final String ID = "string";
    private static final String PATH = "./src/test/resources/";


    @BeforeEach
    void setUp() throws IOException {
        service = new VumService(repository);

        request = objectMapper.readValue(new File(PATH, "vacature-matches-callback-request.json"),
                VacatureMatchesCallbackRequest.class);
        aanvraagVacature = objectMapper.readValue(new File(PATH, "aanvraag-vacature.json"),
                AanvraagVacature.class);
    }


    @Test
    void handleCallbackInDb() {
        when(repository.findById(ID)).thenReturn(Optional.ofNullable(aanvraagVacature));

        service.handleCallback(request);

        verify(repository, times(1)).findById(ID);
        verify(repository, times(1)).save(aanvraagVacature);

    }

    @Test
    void handleCallbackInDbAddingVacatures() {
        when(repository.findById(ID)).thenReturn(Optional.ofNullable(aanvraagVacature));
        aanvraagVacature.setVacatures(new HashSet<>());

        assertThat(aanvraagVacature.getVacatures()).isEmpty();

        service.handleCallback(request);

        assertThat(aanvraagVacature.getVacatures()).hasSize(1);


        verify(repository, times(1)).findById(ID);
        verify(repository, times(1)).save(aanvraagVacature);

    }

    @Test
    void handleCallbackInDbAddingVacaturesToExistingVacatures() {
        when(repository.findById(ID)).thenReturn(Optional.ofNullable(aanvraagVacature));
        // To ensure the vacatures are different, put id of one of them.
        request.getMatches().getMpVacatureMatches().get(0).setId(0L);

        assertThat(aanvraagVacature.getVacatures()).hasSize(1);

        service.handleCallback(request);

        assertThat(aanvraagVacature.getVacatures()).hasSize(2);


        verify(repository, times(1)).findById(ID);
        verify(repository, times(1)).save(aanvraagVacature);

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