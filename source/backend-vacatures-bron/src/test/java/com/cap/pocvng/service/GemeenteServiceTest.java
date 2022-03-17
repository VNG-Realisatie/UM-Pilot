package com.cap.pocvng.service;

import com.cap.pocvng.entity.Vacature;
import com.cap.pocvng.repository.VacatureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class GemeenteServiceTest {

    @Mock
    VacatureRepository repository;

    @InjectMocks
    GemeenteService service;

    private static List<Vacature> vacatureList;
    private static final String ID1 = "ID1";
    private static final String ID2 = "ID2";
    private static final String OIN = "123456789";

    @BeforeEach
    void setUp() {
        Vacature vacature1 = new Vacature();
        vacature1.setIdVacature(ID1);
        Vacature vacature2 = new Vacature();
        vacature2.setIdVacature(ID2);
        vacatureList = List.of(vacature1, vacature2);
    }

    @Test
    void saveAllSuccess() {
        when(repository.saveAll(vacatureList)).thenReturn(vacatureList);

        List<Vacature> result = service.saveAll(vacatureList, OIN);

        assertThat(result).hasSameElementsAs(vacatureList);
    }

}