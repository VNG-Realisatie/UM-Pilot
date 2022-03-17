package com.cap.pocvng.service;

import com.cap.pocvng.entity.Werkzoekende;
import com.cap.pocvng.repository.WerkzoekendeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GemeenteServiceTest {

    @Mock
    WerkzoekendeRepository repository;

    @InjectMocks
    GemeenteService service;


    private static Werkzoekende werkzoekende1;
    private static Werkzoekende werkzoekende2;
    private static List<Werkzoekende> werkzoekendeList;
    private static final String ID1 = "ID1";
    private static final String ID2 = "ID2";
    private static final String OIN = "123456789";


    @BeforeEach
    void setUp() {
        werkzoekende1 = new Werkzoekende();
        werkzoekende1.setIdWerkzoekende(ID1);
        werkzoekende2 = new Werkzoekende();
        werkzoekende2.setIdWerkzoekende(ID2);
        werkzoekendeList = List.of(werkzoekende1, werkzoekende2);
    }


    @Test
    void saveAllSuccess() {
        when(repository.saveAll(werkzoekendeList)).thenReturn(werkzoekendeList);

        List<Werkzoekende> result = service.saveAll(werkzoekendeList, OIN);

        assertThat(result).hasSameElementsAs(werkzoekendeList);
    }




}