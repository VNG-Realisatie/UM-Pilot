package com.cap.pocvng.mapper;

import com.cap.pocvng.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest // required to autowire objectmapper
@ActiveProfiles("test")
class SimpleMapperTest {

    private static SimpleMapperImpl mapper;

    private static Vacature vacature;
    private static Vacature vacatureBuitenland;


    private static MPVacatureMatch result;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String PATH = "./src/test/resources/";

    @BeforeEach
    void setUp() {
        mapper = new SimpleMapperImpl();
        try {
            // get the vacature as an object from the json file.
            vacature = objectMapper.readValue(new File(PATH, "vacature.json"), Vacature.class);
            vacatureBuitenland = objectMapper.readValue(new File(PATH, "vacature-buitenland.json"), Vacature.class);
            result = mapper.vacatureToMPVacatureMatch(vacature);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void vacatureToMPVacatureMatch() {
        assertThat(result.getIdVacature()).isEqualTo(vacature.getIdVacature());
        assertThat(result.getCodeWerkEnDenkniveauMinimaal()).isEqualTo(vacature.getCodeWerkEnDenkniveauMinimaal());
        assertThat(result.getIndicatieLdrRegistratie()).isEqualTo(vacature.getIndicatieLdrRegistratie());
        assertThat(result.getSluitingsDatumVacature()).isEqualTo(vacature.getSluitingsDatumVacature());
        assertThat(result.getSector()).isEqualTo(vacature.getSector());
        assertThat(result.getContractvorm()).hasSameElementsAs(vacature.getContractvorm());
        assertThat(result.getWerkervaring()).hasSameElementsAs(vacature.getWerkervaring());
        assertThat(result.getRijbewijs()).hasSameElementsAs(vacature.getRijbewijs());
        assertThat(result.getVervoermiddel()).hasSameElementsAs(vacature.getVervoermiddel());
        assertThat(result.getFlexibiliteit()).isEqualTo(vacature.getFlexibiliteit());
        assertThat(result.getWerktijden()).isEqualTo(vacature.getWerktijden());
        assertThat(result.getCursus()).isEqualTo(vacature.getCursus());
        assertThat(result.getGedragscompetentie()).hasSameElementsAs(vacature.getGedragscompetentie());
        assertThat(result.getVakvaardigheid()).hasSameElementsAs(vacature.getVakvaardigheid());
        assertThat(result.getTaalbeheersing()).hasSameElementsAs(vacature.getTaalbeheersing());

    }

    @Test
    void sollicitatiewijzeToMPSollicitatiewijze() {
        Sollicitatiewijze input = vacature.getSollicitatiewijze().get(0);
        MPSollicitatiewijze result = mapper.sollicitatiewijzeToMPSollicitatiewijze(input);
        assertThat(result.getCodeSollicitatiewijze()).isEqualTo(input.getCodeSollicitatiewijze());
    }

    @Test
    void werkgeverSectorToMPWerkgeverSector() {
        Werkgever input = vacature.getWerkgever();
        MPWerkgever result = mapper.werkgeverToMPWerkgever(input);

        assertThat(result.getSector()).hasSameElementsAs(input.getSector());
    }

    @Test
    void werkgeverAdreshoudingToMPWerkgeverAdreshouding() {
        AdresHouding input = vacature.getWerkgever().getAdresHouding().get(0);
        MPAdresHouding result = mapper.adresHoudingToMPAdresHouding(input);

        assertThat(result.getCodeFunctieAdres()).isEqualTo(input.getCodeFunctieAdres());
    }


    @Test
    void adresNederlandToMPAdresBuitenland() {
        AdresNederland input = (AdresNederland) vacature.getWerkgever().getAdresHouding().get(0).getAdres();
        MPAdresBuitenland result = mapper.adresNederlandToMPAdresBuitenland(input);

        assertThat(result.getAdresBuitenland().getLandencodeIso()).isEqualTo("NL");
    }

    @Test
    void adresBuitenlandToMPAdresBuitenland() {
        AdresBuitenland input = (AdresBuitenland) vacatureBuitenland.getWerkgever().getAdresHouding().get(0).getAdres();
        MPAdresBuitenland result = mapper.adresBuitenlandToMPAdresBuitenland(input);

        assertThat(result.getAdresBuitenland().getLandencodeIso()).isEqualTo("ST");

    }


    @Test
    void arbeidsvoorwaardenToMPArbeidsvoorwaarden() {
        assertThat(result.getArbeidsVoorwaarden().getDatumAanvangWerkzaamheden())
                .isEqualTo(vacature.getArbeidsVoorwaarden().getDatumAanvangWerkzaamheden());
        assertThat(result.getArbeidsVoorwaarden().getDatumEindeWerkzaamheden())
                .isEqualTo(vacature.getArbeidsVoorwaarden().getDatumEindeWerkzaamheden());
        assertThat(result.getArbeidsVoorwaarden().getSalarisIndicatie())
                .isEqualTo(vacature.getArbeidsVoorwaarden().getSalarisIndicatie());

    }


    @Test
    void beroepsnaamGecodeerd() {
        String codeBeroep = "0";
        String omschrijvingBeroepsnaam = "iets";

        vacature.setBeroep(new BeroepsnaamGecodeerd(codeBeroep, omschrijvingBeroepsnaam));
        result = mapper.vacatureToMPVacatureMatch(vacature);

        assertThat(result.getBeroep()).isInstanceOf(BeroepsnaamGecodeerd.class);

        BeroepsnaamGecodeerd resultBeroep = (BeroepsnaamGecodeerd) result.getBeroep();

        assertThat(resultBeroep.getCodeBeroepsnaam()).isEqualTo(codeBeroep);
        assertThat(resultBeroep.getOmschrijvingBeroepsnaam()).isEqualTo(omschrijvingBeroepsnaam);

    }

    @Test
    void beroepsnaamOngecodeerd() {
        String omschrijvingBeroepsnaam = "iets";

        vacature.setBeroep(new BeroepsnaamOngecodeerd(omschrijvingBeroepsnaam));
        result = mapper.vacatureToMPVacatureMatch(vacature);

        assertThat(result.getBeroep()).isInstanceOf(BeroepsnaamOngecodeerd.class);

        BeroepsnaamOngecodeerd resultBeroep = (BeroepsnaamOngecodeerd) result.getBeroep();

        assertThat(resultBeroep.getNaamBeroepOngecodeerd()).isEqualTo(omschrijvingBeroepsnaam);
    }

    @Test
    void opleidingToMPOpleiding() {

        Opleiding input = vacature.getOpleiding().get(0);
        MPOpleiding result = mapper.opleidingToMPOpleiding(input);

        assertThat(result.getIndicatieDiploma()).isEqualTo(input.getIndicatieDiploma());
        assertThat(result.getCodeNiveauOpleiding()).isEqualTo(input.getCodeNiveauOpleiding());

    }

    @Test
    void opleidingGecodeerd() {

        Long codeOpleidingsnaam = 1234L;
        String omschrijvingOpleidingsnaam = "iets";

        OpleidingsnaamGecodeerd input = new OpleidingsnaamGecodeerd(codeOpleidingsnaam, omschrijvingOpleidingsnaam);

        vacature.getOpleiding().get(0).setOpleidingsnaam(input);
        result = mapper.vacatureToMPVacatureMatch(vacature);

        assertThat(result.getOpleiding().get(0).getOpleidingsnaam()).isInstanceOf(OpleidingsnaamGecodeerd.class);

        OpleidingsnaamGecodeerd resultCoded = (OpleidingsnaamGecodeerd) result.getOpleiding().get(0).getOpleidingsnaam();

        assertThat(resultCoded.getCodeOpleidingsnaam()).isEqualTo(input.getCodeOpleidingsnaam());
        assertThat(resultCoded.getOmschrijvingOpleidingsnaam()).isEqualTo(input.getOmschrijvingOpleidingsnaam());
    }

    @Test
    void opleidingOngecodeerd() {

        String naamOpleidingsnaam = "iets123";
        String omschrijvingOpleidingsnaam = "iets";

        OpleidingsnaamOngecodeerd input = new OpleidingsnaamOngecodeerd(naamOpleidingsnaam, omschrijvingOpleidingsnaam);

        vacature.getOpleiding().get(0).setOpleidingsnaam(input);
        result = mapper.vacatureToMPVacatureMatch(vacature);

        assertThat(result.getOpleiding().get(0).getOpleidingsnaam()).isInstanceOf(MPOpleidingsnaamOngecodeerd.class);

        MPOpleidingsnaamOngecodeerd resultCoded = (MPOpleidingsnaamOngecodeerd) result.getOpleiding().get(0).getOpleidingsnaam();

        assertThat(resultCoded.getNaamOpleidingOngecodeerd()).isEqualTo(input.getNaamOpleidingOngecodeerd());

    }


}