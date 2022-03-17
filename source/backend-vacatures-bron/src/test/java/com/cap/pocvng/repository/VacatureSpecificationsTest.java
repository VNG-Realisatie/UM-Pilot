package com.cap.pocvng.repository;

import com.cap.pocvng.config.ConfigProperties;
import com.cap.pocvng.dto.VacatureMatchesRequest;
import com.cap.pocvng.dto.VacatureMatchingProfielen;
import com.cap.pocvng.entity.*;
import com.cap.pocvng.mapper.SimpleMapper;
import com.cap.pocvng.service.VumService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(ConfigProperties.class)
//Obtain additional beans for integration test.
@ComponentScan(basePackageClasses = {VumService.class, SimpleMapper.class})
@ActiveProfiles("test")
class VacatureSpecificationsTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VumService service;


    private static final String OIN = "123456789";
    private static final String differentOIN = "987654321";

    private static VacatureMatchesRequest vacatureRequest;
    private static MPVacature mpVacature;
    private static Vacature vacature;

    @BeforeEach
    void setUp() {
        vacature = new Vacature();
        mpVacature = new MPVacature();
        vacatureRequest = new VacatureMatchesRequest(mpVacature);

        // Set vacature OIN to static OIN to test the specifications in isolation of OIN.
        vacature.setOin(OIN);
    }

    /**
     * Util method to persist and assert empty result.
     */
    private void assertEmpty() {
        entityManager.persistAndFlush(vacature);
        // Find all vacature with static OIN and modified request.
        ImmutablePair<Boolean, VacatureMatchingProfielen> result = service.match(vacatureRequest, OIN, differentOIN);
        assertThat(result.getRight().getMatches()).isEmpty();
        entityManager.flush();
    }

    /**
     * Util method to persist and assert a match.
     */
    private void assertFound() {
        entityManager.persistAndFlush(vacature);
        // Find all vacature with static OIN and modified request.
        ImmutablePair<Boolean, VacatureMatchingProfielen> result = service.match(vacatureRequest, OIN, differentOIN);
        assertThat(result.getRight().getMatches()).hasSize(1);
    }


    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#codeWerkEnDenkniveauMinimaalArgumentsMatches")
    void codeWerkEnDenkniveauMinimaalMatches(String arg1, String arg2) {
        vacature.setCodeWerkEnDenkniveauMinimaal(arg1);
        mpVacature.setCodeWerkEnDenkniveauMinimaal(arg2);
        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#codeWerkEnDenkniveauMinimaalArgumentsDoesNotMatch")
    void codeWerkEnDenkniveauMinimaalDoesNotMatch(String arg1, String arg2) {
        vacature.setCodeWerkEnDenkniveauMinimaal(arg1);
        mpVacature.setCodeWerkEnDenkniveauMinimaal(arg2);
        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#IndicatieArgumentsMatches")
    void IndicatieLdrRegistratieMatches(Integer indicatie1, Integer indicatie2) {
        vacature.setIndicatieLdrRegistratie(indicatie1);
        mpVacature.setIndicatieLdrRegistratie(indicatie2);
        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#IndicatieArgumentsDoesNotMatch")
    void IndicatieLdrRegistratieDoesNotMatch(Integer indicatie1, Integer indicatie2) {
        vacature.setIndicatieLdrRegistratie(indicatie1);
        mpVacature.setIndicatieLdrRegistratie(indicatie2);
        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#sluitingsDatumVacatureArgumentsMatches")
    void sluitingsDatumVacatureMatches(LocalDate date1, LocalDate date2) {
        vacature.setSluitingsDatumVacature(date1);
        mpVacature.setSluitingsDatumVacature(date2);
        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#sluitingsDatumVacatureArgumentsDoesNotMatch")
    void sluitingsDatumVacatureDoesNotMatch(LocalDate date1, LocalDate date2) {
        vacature.setSluitingsDatumVacature(date1);
        mpVacature.setSluitingsDatumVacature(date2);
        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#sollicitatiewijzeArgumentsMatches")
    void sollicitatiewijzeMatches(List<Sollicitatiewijze> firstList, List<MPSollicitatiewijze> secondList) {
        vacature.setSollicitatiewijze(firstList);
        mpVacature.setSollicitatiewijze(secondList);
        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#sollicitatiewijzeArgumentsDoesNotMatch")
    void sollicitatiewijzeDoesNotMatch(List<Sollicitatiewijze> firstList, List<MPSollicitatiewijze> secondList) {
        vacature.setSollicitatiewijze(firstList);
        mpVacature.setSollicitatiewijze(secondList);
        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#adresHoudingArgumentsMatches")
    void adresHoudingMatches(List<AdresHouding> firstList, List<MPAdresHouding> secondList) {

        vacature.setWerkgever(new Werkgever());
        mpVacature.setWerkgever(new MPWerkgever());

        vacature.getWerkgever().setAdresHouding(firstList);
        mpVacature.getWerkgever().setAdresHouding(secondList);
        assertFound();

        cleanAdresHouding(firstList);
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#adresHoudingArgumentsDoesNotMatch")
    void adresHoudingDoesNotMatch(List<AdresHouding> firstList, List<MPAdresHouding> secondList) {
        vacature.setWerkgever(new Werkgever());
        mpVacature.setWerkgever(new MPWerkgever());

        vacature.getWerkgever().setAdresHouding(firstList);
        mpVacature.getWerkgever().setAdresHouding(secondList);
        assertEmpty();
        cleanAdresHouding(firstList);

    }

    private void cleanAdresHouding(List<AdresHouding> firstList) {
        //clean id for next test
        if (firstList != null) {
            for (AdresHouding adres : firstList) {
                adres.getAdres().setId(null);
            }
        }
    }

    @ParameterizedTest //
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#sectorWerkgeverArgumentsMatches")
    void SectorWerkgeverMatches(List<SectorBeroepsEnBedrijfsleven> firstList, List<SectorBeroepsEnBedrijfsleven> secondList) {

        vacature.setWerkgever(new Werkgever());
        mpVacature.setWerkgever(new MPWerkgever());

        vacature.getWerkgever().setSector(firstList);
        mpVacature.getWerkgever().setSector(secondList);

        assertFound();
    }

    @ParameterizedTest //
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#sectorWerkgeverArgumentsDoesNotMatch")
    void SectorWerkgeverDoesNotMatch(List<SectorBeroepsEnBedrijfsleven> firstList, List<SectorBeroepsEnBedrijfsleven> secondList) {
        vacature.setWerkgever(new Werkgever());
        mpVacature.setWerkgever(new MPWerkgever());

        vacature.getWerkgever().setSector(firstList);
        mpVacature.getWerkgever().setSector(secondList);

        assertEmpty();
    }

    @ParameterizedTest //
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#sectorArgumentsMatches")
    void SectorMatches(SectorBeroepsEnBedrijfsleven sect1, SectorBeroepsEnBedrijfsleven sect2) {

        vacature.setSector(sect1);
        mpVacature.setSector(sect2);

        assertFound();
    }

    @ParameterizedTest //
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#sectorArgumentsDoesNotMatch")
    void SectorDoesNotMatch(SectorBeroepsEnBedrijfsleven sect1, SectorBeroepsEnBedrijfsleven sect2) {

        vacature.setSector(sect1);
        mpVacature.setSector(sect2);

        assertEmpty();
    }

    @ParameterizedTest //
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#arbeidsVoorwaardenArgumentsMatches")
    void arbeidsVoorwaardenMatches(ArbeidsVoorwaarden arb1, MPArbeidsVoorwaarden arb2) {

        vacature.setArbeidsVoorwaarden(arb1);
        mpVacature.setArbeidsVoorwaarden(arb2);

        assertFound();
    }

    @ParameterizedTest //
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#arbeidsVoorwaardenArgumentsDoesNotMatch")
    void arbeidsVoorwaardenDoesNotMatch(ArbeidsVoorwaarden arb1, MPArbeidsVoorwaarden arb2) {

        vacature.setArbeidsVoorwaarden(arb1);
        mpVacature.setArbeidsVoorwaarden(arb2);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#ContractvormArgumentsMatches")
    void ContractvormMatches(List<Contractvorm> firstList, List<Contractvorm> secondList) {
        vacature.setContractvorm(firstList);
        mpVacature.setContractvorm(secondList);

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#ContractvormArgumentsDoesNotMatch")
    void ContractvormDoesNotMatch(List<Contractvorm> firstList, List<Contractvorm> secondList) {
        vacature.setContractvorm(firstList);
        mpVacature.setContractvorm(secondList);

        assertEmpty();
    }


    @ParameterizedTest //
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#beroepsnaamArgumentsMatches")
    void BeroepsnaamMatches(Beroepsnaam beroep1, Beroepsnaam beroep2) {
        vacature.setBeroep(beroep1);
        mpVacature.setBeroep(beroep2);

        assertFound();
        cleanBeroepsnaam(beroep1);
    }

    @ParameterizedTest //
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#beroepsnaamArgumentsDoesNotMatch")
    void BeroepsnaamDoesNotMatch(Beroepsnaam beroep1, Beroepsnaam beroep2) {
        vacature.setBeroep(beroep1);
        mpVacature.setBeroep(beroep2);

        assertEmpty();
        cleanBeroepsnaam(beroep1);

    }


    private void cleanBeroepsnaam(Beroepsnaam beroep1) {
        //clean id for next test
        if (beroep1 != null) {
            beroep1.setId(null);
        }
    }


    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#werkervaringArgumentsMatches")
    void werkervaringMatches(List<Werkervaring> firstList, List<Werkervaring> secondList) {
        vacature.setWerkervaring(firstList);
        mpVacature.setWerkervaring(secondList);

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#werkervaringArgumentsDoesNotMatch")
    void werkervaringDoesNotMatch(List<Werkervaring> firstList, List<Werkervaring> secondList) {
        vacature.setWerkervaring(firstList);
        mpVacature.setWerkervaring(secondList);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#rijbewijsArgumentsMatches")
    void RijbewijsMatches(List<Rijbewijs> firstList, List<Rijbewijs> secondList) {
        vacature.setRijbewijs(firstList);
        mpVacature.setRijbewijs(secondList);

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#rijbewijsArgumentsDoesNotMatch")
    void RijbewijsDoesNotMatch(List<Rijbewijs> firstList, List<Rijbewijs> secondList) {
        vacature.setRijbewijs(firstList);
        mpVacature.setRijbewijs(secondList);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#vervoersmiddelArgumentsMatches")
    void VervoermiddelMatches(Integer firstIndication, Integer secondIndication) {
        Vervoermiddel middel1 = new Vervoermiddel();
        middel1.setIndicatieBeschikbaarVoorUitvoeringWerk(firstIndication);
        middel1.setIndicatieBeschikbaarVoorWoonWerkverkeer(secondIndication);
        vacature.setVervoermiddel(List.of(middel1));
        mpVacature.setVervoermiddel(List.of(middel1));

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#vervoersmiddelArgumentsDoesNotMatch")
    void VervoermiddelDoesNotMatch(List<Vervoermiddel> firstList, List<MPVervoermiddel> secondList) {
        vacature.setVervoermiddel(firstList);
        mpVacature.setVervoermiddel(secondList);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#flexibiliteitArgumentsMatches")
    void FlexibiliteitMatches(Flexibiliteit flex1, Flexibiliteit flex2) {
        vacature.setFlexibiliteit(flex1);
        mpVacature.setFlexibiliteit(flex2);

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#flexibiliteitArgumentsDoesNotMatch")
    void FlexibiliteitDoesNotMatch(Flexibiliteit flex1, Flexibiliteit flex2) {
        vacature.setFlexibiliteit(flex1);
        mpVacature.setFlexibiliteit(flex2);

        assertEmpty();

    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#werktijdenArgumentsMatches")
    void WerktijdenMatches(Werktijden tijd1, Werktijden tijd2) {
        vacature.setWerktijden(tijd1);
        mpVacature.setWerktijden(tijd2);

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#werktijdenArgumentsDoesNotMatch")
    void WerktijdenDoesNotMatch(Werktijden tijd1, Werktijden tijd2) {
        vacature.setWerktijden(tijd1);
        mpVacature.setWerktijden(tijd2);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#cursusArgumentsMatches")
    void CursusMatches(List<Cursus> firstList, List<Cursus> secondList) {
        vacature.setCursus(firstList);
        mpVacature.setCursus(secondList);

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#cursusArgumentsDoesNotMatch")
    void CursusDoesNotMatch(List<Cursus> firstList, List<Cursus> secondList) {
        vacature.setCursus(firstList);
        mpVacature.setCursus(secondList);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#opleidingArgumentsMatches")
    void OpleidingMatches(List<Opleiding> firstList, List<MPOpleiding> secondList) {
        vacature.setOpleiding(firstList);
        mpVacature.setOpleiding(secondList);

        assertFound();
        cleanOpleiding(firstList);
    }

    @ParameterizedTest 
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#opleidingArgumentsDoesNotMatch")
    void OpleidingDoesNotMatch(List<Opleiding> firstList, List<MPOpleiding> secondList) {
        vacature.setOpleiding(firstList);
        mpVacature.setOpleiding(secondList);

        assertEmpty();
        cleanOpleiding(firstList);
    }

    private void cleanOpleiding(List<Opleiding> firstList) {
        //clean id for next test
        if (firstList != null) {
            for (Opleiding opleiding : firstList) {
                opleiding.getOpleidingsnaam().setId(null);
            }
        }
    }

    @ParameterizedTest //
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#gedragscompetentieArgumentsMatches")
    void GedragscompetentieMatches(List<Gedragscompetentie> firstList, List<Gedragscompetentie> secondList) {
        vacature.setGedragscompetentie(firstList);
        mpVacature.setGedragscompetentie(secondList);

        assertFound();
    }

    @ParameterizedTest //
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#gedragscompetentieArgumentsDoesNotMatch")
    void GedragscompetentieDoesNotMatch(List<Gedragscompetentie> firstList, List<Gedragscompetentie> secondList) {
        vacature.setGedragscompetentie(firstList);
        mpVacature.setGedragscompetentie(secondList);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#vakvaardigheidArgumentsMatches")
    void VakvaardigheidMatches(List<Vakvaardigheid> firstList, List<Vakvaardigheid> secondList) {
        vacature.setVakvaardigheid(firstList);
        mpVacature.setVakvaardigheid(secondList);

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#vakvaardigheidArgumentsDoesNotMatch")
    void VakvaardigheidDoesNotMatch(List<Vakvaardigheid> firstList, List<Vakvaardigheid> secondList) {
        vacature.setVakvaardigheid(firstList);
        mpVacature.setVakvaardigheid(secondList);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#taalbeheersingArgumentsMatch")
    void TaalbeheersingMatches(List<Taalbeheersing> firstList, List<Taalbeheersing> secondList) {
        vacature.setTaalbeheersing(firstList);
        mpVacature.setTaalbeheersing(secondList);

        assertFound();
    }


    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#taalbeheersingArgumentsDoesNotMatch")
    void TaalbeheersingDoesNotMatch(List<Taalbeheersing> firstList, List<Taalbeheersing> secondList) {
        vacature.setTaalbeheersing(firstList);
        mpVacature.setTaalbeheersing(secondList);

        assertEmpty();
    }

}