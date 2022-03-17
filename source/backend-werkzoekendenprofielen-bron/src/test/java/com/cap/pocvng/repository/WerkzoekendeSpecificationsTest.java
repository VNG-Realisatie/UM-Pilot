package com.cap.pocvng.repository;

import com.cap.pocvng.config.ConfigProperties;
import com.cap.pocvng.dto.WerkzoekendeMatchingProfielen;
import com.cap.pocvng.dto.WerkzoekendeProfielMatchesRequest;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(ConfigProperties.class)
//Obtain additional beans for integration test.
@ComponentScan(basePackageClasses = {VumService.class, SimpleMapper.class})
@ActiveProfiles("test")
class WerkzoekendeSpecificationsTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VumService service;


    private static final String OIN = "123456789";
    private static final String OIN2 = "987654321";
    private static final String ID = "someid";
    private static WerkzoekendeProfielMatchesRequest werkzoekendeRequest;
    private static MPWerkzoekende requestWerkzoekende;
    private static Werkzoekende werkzoekende;

    @BeforeEach
    void setUp() {

        werkzoekende = new Werkzoekende();
        werkzoekende.setArbeidsmarktkwalificatie(new Arbeidsmarktkwalificatie());
        requestWerkzoekende = new MPWerkzoekende();
        requestWerkzoekende.setArbeidsmarktkwalificatie(new MPArbeidsmarktkwalificatie());
        werkzoekendeRequest = new WerkzoekendeProfielMatchesRequest(requestWerkzoekende);

        // Set werkzoekende OIN to static OIN to test the specifications in isolation of OIN.
        werkzoekende.setOin(OIN);
        werkzoekende.setIdWerkzoekende(ID);
    }

    /**
     * Util method to persist and assert empty result.
     */
    private void assertEmpty() {
        entityManager.persistAndFlush(werkzoekende);
        // Find all werkzoekende with static OIN and modified request.
        ImmutablePair<Boolean, WerkzoekendeMatchingProfielen> result = service.match(werkzoekendeRequest, OIN);
        assertThat(result.getRight().getMatches()).isEmpty();
    }

    /**
     * Util method to persist and assert a match.
     */
    private void assertFound() {
        entityManager.persistAndFlush(werkzoekende);
        // Find all werkzoekende with static OIN and modified request.
        ImmutablePair<Boolean, WerkzoekendeMatchingProfielen> result = service.match(werkzoekendeRequest, OIN);
        assertThat(result.getRight().getMatches()).hasSize(1);
    }


    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#IndicatieArgumentsMatches")
    void IndicatieLdrRegistratieMatches(Integer indicatie1, Integer indicatie2) {
        werkzoekende.setIndicatieLdrRegistratie(indicatie1);
        requestWerkzoekende.setIndicatieLdrRegistratie(indicatie2);
        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#IndicatieArgumentsDoesNotMatch")
    void IndicatieLdrRegistratieDoesNotMatch(Integer indicatie1, Integer indicatie2) {
        werkzoekende.setIndicatieLdrRegistratie(indicatie1);
        requestWerkzoekende.setIndicatieLdrRegistratie(indicatie2);
        assertEmpty();
    }


    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#IndicatieArgumentsMatches")
    void IndicatieBeschikbaarheidsContractgegevensMatches(Integer indicatie1, Integer indicatie2) {
        werkzoekende.setIndicatieBeschikbaarheidContactgegevens(indicatie1);
        requestWerkzoekende.setIndicatieBeschikbaarheidContactgegevens(indicatie2);
        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#IndicatieArgumentsDoesNotMatch")
    void IndicatieBeschikbaarheidsContractgegevensDoesNotMatch(Integer indicatie1, Integer indicatie2) {
        werkzoekende.setIndicatieBeschikbaarheidContactgegevens(indicatie1);
        requestWerkzoekende.setIndicatieBeschikbaarheidContactgegevens(indicatie2);
        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#VoorkeurslandArgumentsMatches")
    void VoorkeurslandMatches(List<Voorkeursland> firstList, List<Voorkeursland> secondList) {
        werkzoekende.setVoorkeursland(firstList);
        requestWerkzoekende.setVoorkeursland(secondList);
        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#VoorkeurslandArgumentsDoesNotMatch")
    void VoorkeurslandDoesNotMatch(List<Voorkeursland> firstList, List<Voorkeursland> secondList) {
        werkzoekende.setVoorkeursland(firstList);
        requestWerkzoekende.setVoorkeursland(secondList);
        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#VervoersmiddelArgumentsMatches")
    void VervoermiddelMatches(Integer firstIndication, Integer secondIndication) {
        Vervoermiddel middel1 = new Vervoermiddel();
        middel1.setIndicatieBeschikbaarVoorUitvoeringWerk(firstIndication);
        middel1.setIndicatieBeschikbaarVoorWoonWerkverkeer(secondIndication);
        werkzoekende.setVervoermiddel(List.of(middel1));
        requestWerkzoekende.setVervoermiddel(List.of(middel1));

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#VervoersmiddelArgumentsDoesNotMatch")
    void VervoermiddelDoesNotMatch(List<Vervoermiddel> firstList, List<MPVervoermiddel> secondList) {
        werkzoekende.setVervoermiddel(firstList);
        requestWerkzoekende.setVervoermiddel(secondList);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#FlexibiliteitArgumentsMatches")
    void FlexibiliteitMatches(Flexibiliteit flex1, Flexibiliteit flex2) {
        werkzoekende.setFlexibiliteit(flex1);
        requestWerkzoekende.setFlexibiliteit(flex2);

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#FlexibiliteitArgumentsDoesNotMatch")
    void FlexibiliteitDoesNotMatch(Flexibiliteit flex1, Flexibiliteit flex2) {
        werkzoekende.setFlexibiliteit(flex1);
        requestWerkzoekende.setFlexibiliteit(flex2);

        assertEmpty();

    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#WerktijdenArgumentsMatches")
    void WerktijdenMatches(Werktijden tijd1, Werktijden tijd2) {
        werkzoekende.setWerktijden(tijd1);
        requestWerkzoekende.setWerktijden(tijd2);

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#WerktijdenArgumentsDoesNotMatch")
    void WerktijdenDoesNotMatch(Werktijden tijd1, Werktijden tijd2) {
        werkzoekende.setWerktijden(tijd1);
        requestWerkzoekende.setWerktijden(tijd2);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#ContractvormArgumentsMatches")
    void ContractvormMatches(List<Contractvorm> firstList, List<Contractvorm> secondList) {
        werkzoekende.setContractvorm(firstList);
        requestWerkzoekende.setContractvorm(secondList);

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#ContractvormArgumentsDoesNotMatch")
    void ContractvormDoesNotMatch(List<Contractvorm> firstList, List<Contractvorm> secondList) {
        werkzoekende.setContractvorm(firstList);
        requestWerkzoekende.setContractvorm(secondList);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#codeWerkEnDenkniveauWerkzoekendeArgumentsMatches")
    void codeWerkEnDenkniveauWerkzoekendeMatches(Integer codeWerkzoekende, Integer codeRequest) {
        werkzoekende.getArbeidsmarktkwalificatie().setCodeWerkEnDenkniveauWerkzoekende(codeWerkzoekende);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setCodeWerkEnDenkniveauWerkzoekende(codeRequest);

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#codeWerkEnDenkniveauWerkzoekendeArgumentsDoesNotMatch")
    void codeWerkEnDenkniveauWerkzoekendeDoesNotMatch(Integer codeWerkzoekende, Integer codeRequest) {
        werkzoekende.getArbeidsmarktkwalificatie().setCodeWerkEnDenkniveauWerkzoekende(codeWerkzoekende);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setCodeWerkEnDenkniveauWerkzoekende(codeRequest);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#VakvaardigheidArgumentsMatches")
    void VakvaardigheidMatches(List<Vakvaardigheid> firstList, List<Vakvaardigheid> secondList) {
        werkzoekende.getArbeidsmarktkwalificatie().setVakvaardigheid(firstList);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setVakvaardigheid(secondList);

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#VakvaardigheidArgumentsDoesNotMatch")
    void VakvaardigheidDoesNotMatch(List<Vakvaardigheid> firstList, List<Vakvaardigheid> secondList) {
        werkzoekende.getArbeidsmarktkwalificatie().setVakvaardigheid(firstList);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setVakvaardigheid(secondList);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#TaalbeheersingArgumentsMatch")
    void TaalbeheersingMatches(List<Taalbeheersing> firstList, List<Taalbeheersing> secondList) {
        werkzoekende.getArbeidsmarktkwalificatie().setTaalbeheersing(firstList);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setTaalbeheersing(secondList);

        assertFound();
    }


    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#TaalbeheersingArgumentsDoesNotMatch")
    void TaalbeheersingDoesNotMatch(List<Taalbeheersing> firstList, List<Taalbeheersing> secondList) {
        werkzoekende.getArbeidsmarktkwalificatie().setTaalbeheersing(firstList);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setTaalbeheersing(secondList);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#GedragscompetentieArgumentsMatches")
    void GedragscompetentieMatches(List<Gedragscompetentie> firstList, List<Gedragscompetentie> secondList) {
        werkzoekende.getArbeidsmarktkwalificatie().setGedragscompetentie(firstList);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setGedragscompetentie(secondList);

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#GedragscompetentieArgumentsDoesNotMatch")
    void GedragscompetentieDoesNotMatch(List<Gedragscompetentie> firstList, List<Gedragscompetentie> secondList) {
        werkzoekende.getArbeidsmarktkwalificatie().setGedragscompetentie(firstList);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setGedragscompetentie(secondList);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#RijbewijsArgumentsMatches")
    void RijbewijsMatches(List<Rijbewijs> firstList, List<Rijbewijs> secondList) {
        werkzoekende.getArbeidsmarktkwalificatie().setRijbewijs(firstList);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setRijbewijs(secondList);

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#RijbewijsArgumentsDoesNotMatch")
    void RijbewijsDoesNotMatch(List<Rijbewijs> firstList, List<Rijbewijs> secondList) {
        werkzoekende.getArbeidsmarktkwalificatie().setRijbewijs(firstList);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setRijbewijs(secondList);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#CursusArgumentsMatches")
    void CursusMatches(List<Cursus> firstList, List<MPCursus> secondList) {
        werkzoekende.getArbeidsmarktkwalificatie().setCursus(firstList);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setCursus(secondList);

        assertFound();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#CursusArgumentsDoesNotMatch")
    void CursusDoesNotMatch(List<Cursus> firstList, List<MPCursus> secondList) {
        werkzoekende.getArbeidsmarktkwalificatie().setCursus(firstList);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setCursus(secondList);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#OpleidingArgumentsMatches")
    void OpleidingMatches(List<Opleiding> firstList, List<MPOpleiding> secondList) {
        werkzoekende.getArbeidsmarktkwalificatie().setOpleiding(firstList);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setOpleiding(secondList);

        assertFound();
        cleanOpleiding(firstList);
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#OpleidingArgumentsDoesNotMatch")
    void OpleidingDoesNotMatch(List<Opleiding> firstList, List<MPOpleiding> secondList) {
        werkzoekende.getArbeidsmarktkwalificatie().setOpleiding(firstList);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setOpleiding(secondList);

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
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#WerkervaringArgumentsMatches")
    void WerkervaringMatches(List<Werkervaring> firstList, List<MPWerkervaring> secondList) {
        werkzoekende.getArbeidsmarktkwalificatie().setWerkervaring(firstList);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setWerkervaring(secondList);

        assertFound();
    }

    @ParameterizedTest //
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#WerkervaringArgumentsDoesNotMatch")
    void WerkervaringDoesNotMatch(List<Werkervaring> firstList, List<MPWerkervaring> secondList) {
        werkzoekende.getArbeidsmarktkwalificatie().setWerkervaring(firstList);
        requestWerkzoekende.getArbeidsmarktkwalificatie().setWerkervaring(secondList);

        assertEmpty();
    }

    @ParameterizedTest //
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#SectorArgumentsMatches")
    void SectorMatches(List<SectorBeroepsEnBedrijfsleven> firstList, List<SectorBeroepsEnBedrijfsleven> secondList) {
        werkzoekende.setSector(firstList);
        requestWerkzoekende.setSector(secondList);

        assertFound();
    }

    @ParameterizedTest //
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#SectorArgumentsDoesNotMatch")
    void SectorDoesNotMatch(List<SectorBeroepsEnBedrijfsleven> firstList, List<SectorBeroepsEnBedrijfsleven> secondList) {
        werkzoekende.setSector(firstList);
        requestWerkzoekende.setSector(secondList);

        assertEmpty();
    }

    @ParameterizedTest
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#BeroepsnaamArgumentsMatches")
    void BeroepsnaamMatches(List<Beroepsnaam> firstList, List<Beroepsnaam> secondList) {
        werkzoekende.setBemiddelingsberoep(firstList);
        requestWerkzoekende.setBemiddelingsberoep(secondList);

        assertFound();
        cleanBeroep(firstList);
    }

    @ParameterizedTest // TODO: fix Beroepsnaam matching to eagerly
    @MethodSource("com.cap.pocvng.repository.ArgumentsProvider#BeroepsnaamArgumentsDoesNotMatch")
    void BeroepsnaamDoesNotMatch(List<Beroepsnaam> firstList, List<Beroepsnaam> secondList) {
        werkzoekende.setBemiddelingsberoep(firstList);
        requestWerkzoekende.setBemiddelingsberoep(secondList);

        assertEmpty();
        cleanBeroep(firstList);
    }

    private void cleanBeroep(List<Beroepsnaam> firstList) {
        //clean id for next test
        if (firstList != null) {
            for (Beroepsnaam beroepsnaam : firstList) {
                beroepsnaam.setId(null);
            }
        }
    }


}
