package com.cap.pocvng.repository;

import com.cap.pocvng.entity.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ArgumentsProvider {

    /**
     * Provide arguments which should match for codeWerkEnDenkniveauMinimaal filter.
     */
    private static Stream<Arguments> codeWerkEnDenkniveauMinimaalArgumentsMatches(){

        return Stream.of(
                arguments("1", "1"),
                arguments("2", "2"),
                arguments("1", null),
                arguments("2", null),
                arguments(null, null)
        );
    }

    /**
     * Provide arguments which should not match for codeWerkEnDenkniveauMinimaal filter.
     */
    private static Stream<Arguments> codeWerkEnDenkniveauMinimaalArgumentsDoesNotMatch(){

        return Stream.of(
                arguments("1", "2"),
                arguments("2", "1"),
                arguments(null, "1"),
                arguments(null, "2")

        );
    }

    /**
     * Provide arguments which should match for indication filters.
     */
    private static Stream<Arguments> IndicatieArgumentsMatches() {

        return Stream.of(
                arguments(1, 1),
                arguments(2, 2),
                arguments(1, null),
                arguments(2, null),
                arguments(null, null)
        );
    }

    /**
     * Provide arguments which should not match for indication filters.
     */
    private static Stream<Arguments> IndicatieArgumentsDoesNotMatch() {


        return Stream.of(
                arguments(1, 2),
                arguments(2, 1),
                arguments(null, 1),
                arguments(null, 2)
        );
    }

    /**
     * Provide arguments which should match for sluitingsDatumVacature filters.
     */
    private static Stream<Arguments> sluitingsDatumVacatureArgumentsMatches() {
        return Stream.of(
                arguments(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 1)),
                arguments(LocalDate.of(2022, 1, 1), LocalDate.of(2021, 12, 30)),
                arguments(LocalDate.of(2022, 1, 1), null),
                arguments(null, null)
        );
    }

    /**
     * Provide arguments which should not match for sluitingsDatumVacature filters.
     */
    private static Stream<Arguments> sluitingsDatumVacatureArgumentsDoesNotMatch() {
        return Stream.of(
                arguments(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 2)),
                arguments(null, LocalDate.of(2022, 1, 2))
        );
    }

    /**
     * Provide arguments which should match for sollicitatiewijze filter.
     */
    private static Stream<Arguments> sollicitatiewijzeArgumentsMatches() {
        Sollicitatiewijze swijze1 = new Sollicitatiewijze("1", new Webadres());
        Sollicitatiewijze swijze2 = new Sollicitatiewijze("2", new Webadres());

        MPSollicitatiewijze swijze3 = new MPSollicitatiewijze("1");
        MPSollicitatiewijze swijze4 = new MPSollicitatiewijze("2");


        return Stream.of(
                arguments(List.of(swijze1), List.of(swijze3)),
                arguments(List.of(swijze1, swijze2), List.of(swijze3)),
                arguments(List.of(swijze1, swijze2), List.of(swijze3, swijze4)),
                arguments(List.of(swijze1), List.of(new MPSollicitatiewijze(null))),
                arguments(List.of(swijze1), List.of()),
                arguments(List.of(swijze1), null),
                arguments(null, null)
        );
    }

    /**
     * Provide arguments which should not match for sollicitatiewijze filter.
     */
    private static Stream<Arguments> sollicitatiewijzeArgumentsDoesNotMatch() {
        Sollicitatiewijze swijze1 = new Sollicitatiewijze("1", new Webadres());
        Sollicitatiewijze swijze2 = new Sollicitatiewijze("2", new Webadres());

        MPSollicitatiewijze swijze3 = new MPSollicitatiewijze("1");
        MPSollicitatiewijze swijze4 = new MPSollicitatiewijze("2");

        return Stream.of(
                arguments(List.of(swijze1), List.of(swijze3, swijze4)),
                arguments(List.of(swijze1), List.of(swijze4)),
                arguments(null, List.of(swijze3))
        );
    }

    /**
     * Provide arguments which should match for adreshouding filter.
     */
    private static Stream<Arguments> adresHoudingArgumentsMatches() {
        AdresHouding adres1 = new AdresHouding("s", null, null,  new AdresNederland());
        AdresHouding adres2 = new AdresHouding("t", null, null,  new AdresNederland());
        AdresBuitenlandImpl adresFr = new AdresBuitenlandImpl();
        adresFr.setLandencodeIso("FR");
        AdresHouding adres4 = new AdresHouding("s", null, null, new AdresBuitenland(adresFr));
        MPAdresHouding adres5 = new MPAdresHouding("s",   new MPAdresBuitenland(new MPAdresBuitenlandImpl("NL")));
        MPAdresHouding adres6 = new MPAdresHouding("t",   new MPAdresBuitenland(new MPAdresBuitenlandImpl("NL")));
        MPAdresHouding adres7 = new MPAdresHouding("s",   new MPAdresBuitenland(new MPAdresBuitenlandImpl("FR")));



        return Stream.of(
                arguments(List.of(adres1), List.of(adres5)),
                arguments(List.of(adres1, adres2), List.of(adres5)),
                arguments(List.of(adres1, adres2), List.of(adres5, adres6)),
                arguments(List.of(adres4), List.of(adres7)),

                arguments(List.of(adres1), List.of(new MPAdresHouding(null, null))),
                arguments(List.of(adres1), List.of()),
                arguments(List.of(adres1), null),
                arguments(null, null)
        );
    }

    /**
     * Provide arguments which should not match for adreshouding filter.
     */
    private static Stream<Arguments> adresHoudingArgumentsDoesNotMatch() {
        AdresHouding adres1 = new AdresHouding("s", null, null,  new AdresNederland());
        AdresBuitenlandImpl adresFr = new AdresBuitenlandImpl();
        adresFr.setLandencodeIso("FR");
        AdresHouding adres4 = new AdresHouding("s", null, null, new AdresBuitenland(adresFr));
        MPAdresHouding adres5 = new MPAdresHouding("s",   new MPAdresBuitenland(new MPAdresBuitenlandImpl("NL")));
        MPAdresHouding adres6 = new MPAdresHouding("t",   new MPAdresBuitenland(new MPAdresBuitenlandImpl("NL")));
        MPAdresHouding adres7 = new MPAdresHouding("s",   new MPAdresBuitenland(new MPAdresBuitenlandImpl("FR")));

        return Stream.of(
                arguments(List.of(adres1), List.of(adres5, adres6)),
                arguments(List.of(adres1), List.of(adres6)),
                arguments(List.of(adres1), List.of(adres7)),
                arguments(null, List.of(adres5))
        );
    }

    /**
     * Provide arguments which should match for sector werkgever filter.
     */
    private static Stream<Arguments> sectorWerkgeverArgumentsMatches() {
        SectorBeroepsEnBedrijfsleven sector1 = new SectorBeroepsEnBedrijfsleven(5);
        SectorBeroepsEnBedrijfsleven sector2 = new SectorBeroepsEnBedrijfsleven(6);
        SectorBeroepsEnBedrijfsleven sector3 = new SectorBeroepsEnBedrijfsleven(null);


        return Stream.of(
                arguments(List.of(sector1), List.of(sector1)),
                arguments(List.of(sector1, sector2), List.of(sector2)),
                arguments(List.of(sector1, sector2), List.of(sector1)),
                arguments(List.of(sector1, sector2), List.of(sector1, sector2)),
                arguments(List.of(sector1), null),
                arguments(List.of(sector1), List.of()),
                arguments(List.of(sector1), List.of(sector3)),
                arguments(null, null)
        );
    }

    /**
     * Provide arguments which should not match for sector werkgever filter.
     */
    private static Stream<Arguments> sectorWerkgeverArgumentsDoesNotMatch() {
        SectorBeroepsEnBedrijfsleven sector1 = new SectorBeroepsEnBedrijfsleven(5);
        SectorBeroepsEnBedrijfsleven sector2 = new SectorBeroepsEnBedrijfsleven(6);
        SectorBeroepsEnBedrijfsleven sector3 = new SectorBeroepsEnBedrijfsleven(7);


        return Stream.of(
                arguments(List.of(sector1), List.of(sector2)),
                arguments(List.of(sector1, sector2), List.of(sector2, sector3)),
                arguments(null, List.of(sector1))
        );
    }

    /**
     * Provide arguments which should match for sector filter.
     */
    private static Stream<Arguments> sectorArgumentsMatches() {
        SectorBeroepsEnBedrijfsleven sector1 = new SectorBeroepsEnBedrijfsleven(5);
        SectorBeroepsEnBedrijfsleven sector2 = new SectorBeroepsEnBedrijfsleven(6);

        return Stream.of(
                arguments(sector1, sector1),
                arguments(sector2, sector2),
                arguments(sector1, null),
                arguments(sector2, null),
                arguments(null, null)

        );
    }

    /**
     * Provide arguments which should not match for sector filter.
     */
    private static Stream<Arguments> sectorArgumentsDoesNotMatch() {
        SectorBeroepsEnBedrijfsleven sector1 = new SectorBeroepsEnBedrijfsleven(5);
        SectorBeroepsEnBedrijfsleven sector2 = new SectorBeroepsEnBedrijfsleven(6);

        return Stream.of(
                arguments(sector1, sector2),
                arguments(sector2, sector1),
                arguments(null, sector1),
                arguments(null, sector2)
        );
    }


    /**
     * Provide arguments which should match for arbeidsVoorwaarden filter.
     */
    private static Stream<Arguments> arbeidsVoorwaardenArgumentsMatches() {
        ArbeidsVoorwaarden arb1 = new ArbeidsVoorwaarden(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 2, 1), null, "2000");
        ArbeidsVoorwaarden arb2 = new ArbeidsVoorwaarden(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 3, 1), null, "2020");

        MPArbeidsVoorwaarden arb3 = new MPArbeidsVoorwaarden(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 2, 1),  "2000");
        MPArbeidsVoorwaarden arb4 = new MPArbeidsVoorwaarden(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 3, 1),  "2020");


        return Stream.of(
                arguments(arb1, arb3),
                arguments(arb2, arb4),
                arguments(arb1, null),
                arguments(arb2, null),
                arguments(null, null)

        );
    }

    /**
     * Provide arguments which should not match for arbeidsVoorwaarden filter.
     */
    private static Stream<Arguments> arbeidsVoorwaardenArgumentsDoesNotMatch() {
        ArbeidsVoorwaarden arb1 = new ArbeidsVoorwaarden(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 2, 1), null, "2000");

        MPArbeidsVoorwaarden arb3 = new MPArbeidsVoorwaarden(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 2, 1),  "2000");
        MPArbeidsVoorwaarden arb4 = new MPArbeidsVoorwaarden(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 2, 1),  "2020");
        MPArbeidsVoorwaarden arb5 = new MPArbeidsVoorwaarden(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 6, 1),  "2000");


        return Stream.of(
                arguments(arb1, arb4),
                arguments(arb1, arb5),
                arguments(null, arb3),
                arguments(null, arb5)
        );
    }

    /**
     * Provide arguments which should match for contractvorm filter.
     */
    private static Stream<Arguments> ContractvormArgumentsMatches() {
        Contractvorm vorm1 = new Contractvorm("B", "01");
        Contractvorm vorm2 = new Contractvorm("O", "02");
        Contractvorm vorm3 = new Contractvorm(null, null);

        return Stream.of(
                arguments(List.of(vorm1), List.of(vorm1)),
                arguments(List.of(vorm1, vorm2), List.of(vorm1, vorm2)),
                arguments(List.of(vorm1, vorm2), List.of(vorm2)),
                arguments(List.of(vorm1), List.of()),
                arguments(List.of(vorm1), List.of(vorm3)),
                arguments(null, null)

        );

    }

    /**
     * Provide arguments which should not match for contractvorm filter.
     */
    private static Stream<Arguments> ContractvormArgumentsDoesNotMatch() {
        Contractvorm vorm1 = new Contractvorm("B", "01");
        Contractvorm vorm2 = new Contractvorm("O", "02");

        return Stream.of(
                arguments(List.of(vorm1), List.of(vorm2)),
                arguments(List.of(vorm1), List.of(vorm1, vorm2)),
                arguments(null, List.of(vorm2))
        );

    }

    /**
     * Provide arguments which should match for beroepsnaam filter.
     */
    private static Stream<Arguments> beroepsnaamArgumentsMatches() {
        BeroepsnaamOngecodeerd ongecodeerd1 = new BeroepsnaamOngecodeerd("containing string containing");
        BeroepsnaamOngecodeerd ongecodeerd2 = new BeroepsnaamOngecodeerd("string");
        BeroepsnaamGecodeerd gecodeerd1 = new BeroepsnaamGecodeerd("st", "string");
        BeroepsnaamGecodeerd gecodeerd2 = new BeroepsnaamGecodeerd("st", "containing string containing");
        BeroepsnaamGecodeerd gecodeerd3 = new BeroepsnaamGecodeerd(null, null);


        return Stream.of(
                // Ongecodeerde combinations
                arguments(ongecodeerd1, ongecodeerd1),
                arguments(ongecodeerd1, ongecodeerd2),
                arguments(ongecodeerd2, ongecodeerd2),
                // Gecodeerde combinations
                arguments(gecodeerd1, gecodeerd1),
                arguments(gecodeerd2, gecodeerd1),
                arguments(gecodeerd2, gecodeerd2),

                // Mix of gecodeerd and ongecodeerd.
                arguments(gecodeerd1, ongecodeerd2),

                arguments(ongecodeerd1, null),
                arguments(ongecodeerd1, gecodeerd3),

                arguments(gecodeerd1, null),
                arguments(null, null)
        );
    }

    /**
     * Provide arguments which should not match for beroepsnaam filter.
     */
    private static Stream<Arguments> beroepsnaamArgumentsDoesNotMatch() {

        BeroepsnaamOngecodeerd ongecodeerd1 = new BeroepsnaamOngecodeerd("containing string containing");
        BeroepsnaamOngecodeerd ongecodeerd2 = new BeroepsnaamOngecodeerd("not relevant");
        BeroepsnaamOngecodeerd ongecodeerd3 = new BeroepsnaamOngecodeerd("another one");

        BeroepsnaamGecodeerd gecodeerd1 = new BeroepsnaamGecodeerd("st", "string");
        BeroepsnaamGecodeerd gecodeerd2 = new BeroepsnaamGecodeerd("sz", "string");
        BeroepsnaamGecodeerd gecodeerd3 = new BeroepsnaamGecodeerd("st", "not relevant");

        return Stream.of(
                arguments(ongecodeerd1, ongecodeerd2),
                arguments( ongecodeerd2, ongecodeerd3),
                arguments(null, ongecodeerd1),

                arguments(gecodeerd1, gecodeerd2),
                arguments(gecodeerd1, gecodeerd3),

                arguments(null, gecodeerd1)
        );
    }

    /**
     * Provide arguments which should match for werkervaring filter.
     */
    private static Stream<Arguments> werkervaringArgumentsMatches() {
        Werkervaring werkervaring1 = new Werkervaring(10);
        Werkervaring werkervaring2 = new Werkervaring(20);
        Werkervaring werkervaring3 = new Werkervaring(30);

        return Stream.of(
                arguments(List.of(werkervaring1), List.of(werkervaring1)),
                arguments(List.of(werkervaring2), List.of(werkervaring1)),
                arguments(List.of(werkervaring1, werkervaring2), List.of(werkervaring1, werkervaring2)),
                arguments(List.of(werkervaring1, werkervaring2), List.of(werkervaring2)),
                arguments(List.of(werkervaring1), List.of()),
                arguments(List.of(werkervaring3), List.of(werkervaring1)),
                arguments(null, null)

        );

    }

    /**
     * Provide arguments which should not match for werkervaring filter.
     */
    private static Stream<Arguments> werkervaringArgumentsDoesNotMatch() {
        Werkervaring werkervaring1 = new Werkervaring(10);
        Werkervaring werkervaring2 = new Werkervaring(20);
        Werkervaring werkervaring3 = new Werkervaring(30);

        return Stream.of(
                arguments(List.of(werkervaring1), List.of(werkervaring2)),
                arguments(List.of(werkervaring1), List.of(werkervaring2, werkervaring3)),
                arguments(null, List.of(werkervaring2))
        );

    }

    /**
     * Provide arguments which should match for rijbewijs filter.
     */
    private static Stream<Arguments> rijbewijsArgumentsMatches() {
        Rijbewijs rijbewijs1 = new Rijbewijs("B");
        Rijbewijs rijbewijs2 = new Rijbewijs("A");
        Rijbewijs rijbewijs3 = new Rijbewijs(null);


        return Stream.of(
                arguments(List.of(rijbewijs1), List.of(rijbewijs1)),
                arguments(List.of(rijbewijs1, rijbewijs2), List.of(rijbewijs2)),
                arguments(List.of(rijbewijs1, rijbewijs2), List.of(rijbewijs1, rijbewijs2)),
                arguments(List.of(rijbewijs1), null),
                arguments(List.of(rijbewijs1), List.of(rijbewijs3)),
                arguments(List.of(rijbewijs1), List.of()),
                arguments(null, null)

        );
    }

    /**
     * Provide arguments which should not match for rijbewijs filter.
     */
    private static Stream<Arguments> rijbewijsArgumentsDoesNotMatch() {
        Rijbewijs rijbewijs1 = new Rijbewijs("B");
        Rijbewijs rijbewijs2 = new Rijbewijs("A");


        return Stream.of(
                arguments(List.of(rijbewijs1), List.of(rijbewijs2)),
                arguments(List.of(rijbewijs1), List.of(rijbewijs1, rijbewijs2)),
                arguments(null, List.of(rijbewijs2))
        );
    }

    /**
     * Provide arguments which should match for vervoersmiddel filter.
     */
    private static Stream<Arguments> vervoersmiddelArgumentsMatches() {
        Vervoermiddel middel1 = new Vervoermiddel();

        return Stream.of(
                arguments(0, 0),
                arguments(0, 1),
                arguments(1, 0),
                arguments(1, 1),
                arguments(0, null),
                arguments(1, null),
                arguments(null, null)

        );
    }

    /**
     * Provide arguments which should not match for vervoersmiddel filter.
     */
    private static Stream<Arguments> vervoersmiddelArgumentsDoesNotMatch() {
        Vervoermiddel middel1 = new Vervoermiddel();
        middel1.setIndicatieBeschikbaarVoorUitvoeringWerk(0);
        middel1.setIndicatieBeschikbaarVoorWoonWerkverkeer(0);

        Vervoermiddel middel2 = new Vervoermiddel();
        middel2.setIndicatieBeschikbaarVoorUitvoeringWerk(0);
        middel2.setIndicatieBeschikbaarVoorWoonWerkverkeer(1);

        Vervoermiddel middel3 = new Vervoermiddel();
        middel3.setIndicatieBeschikbaarVoorUitvoeringWerk(1);
        middel3.setIndicatieBeschikbaarVoorWoonWerkverkeer(0);

        Vervoermiddel middel4 = new Vervoermiddel();
        middel4.setIndicatieBeschikbaarVoorUitvoeringWerk(1);
        middel4.setIndicatieBeschikbaarVoorWoonWerkverkeer(1);

        return Stream.of(
                arguments(List.of(middel1), List.of(middel2)),
                arguments(List.of(middel1), List.of(middel2)),
                arguments(List.of(middel1), List.of(middel2)),
                arguments(List.of(middel2), List.of(middel1)),
                arguments(List.of(middel2), List.of(middel3)),
                arguments(List.of(middel2), List.of(middel4)),
                arguments(List.of(middel3), List.of(middel1)),
                arguments(List.of(middel3), List.of(middel2)),
                arguments(List.of(middel3), List.of(middel4)),
                arguments(List.of(middel4), List.of(middel1)),
                arguments(List.of(middel4), List.of(middel2)),
                arguments(List.of(middel4), List.of(middel3)),
                arguments(null, List.of(middel1))
        );
    }

    /**
     * Provide arguments which should match for flexibiliteit filter.
     */
    private static Stream<Arguments> flexibiliteitArgumentsMatches() {
        Flexibiliteit flex1 = new Flexibiliteit(9, 0, LocalDate.of(2020, 1, 20), LocalDate.of(2020, 1, 25));
        Flexibiliteit flex2 = new Flexibiliteit(9, 0, LocalDate.of(2020, 1, 21), LocalDate.of(2020, 1, 24));
        Flexibiliteit flex3 = new Flexibiliteit(9, 0, LocalDate.of(2020, 1, 20), LocalDate.of(2020, 1, 25));
        Flexibiliteit flex4 = new Flexibiliteit(null, null, null, null);
        return Stream.of(
                arguments(flex1, flex1),
                arguments(flex1, flex2),
                arguments(flex1, flex3),
                arguments(flex1, flex4),
                arguments(flex1, null),
                arguments(null, null)
        );
    }

    /**
     * Provide arguments which should not match for flexibiliteit filter.
     */
    private static Stream<Arguments> flexibiliteitArgumentsDoesNotMatch() {
        Flexibiliteit flex1 = new Flexibiliteit(9, 0, LocalDate.of(2020, 1, 20), LocalDate.of(2020, 1, 25));
        Flexibiliteit flex2 = new Flexibiliteit(9, 1, LocalDate.of(2020, 1, 20), LocalDate.of(2020, 1, 25));
        Flexibiliteit flex3 = new Flexibiliteit(9, 0, LocalDate.of(2020, 1, 20), LocalDate.of(2020, 1, 26));
        Flexibiliteit flex4 = new Flexibiliteit(9, 0, LocalDate.of(2020, 1, 19), LocalDate.of(2020, 1, 25));

        return Stream.of(
                arguments(flex1, flex2),
                arguments(flex1, flex2),
                arguments(flex1, flex3),
                arguments(flex1, flex4),
                arguments(null, flex2)
        );
    }

    /**
     * Provide arguments which should match for werktijden filter.
     */
    private static Stream<Arguments> werktijdenArgumentsMatches() {
        Werktijden werktijden1 = new Werktijden(30, 40, 0);
        Werktijden werktijden2 = new Werktijden(29, 40, 0);
        Werktijden werktijden3 = new Werktijden(30, 41, 0);
        Werktijden werktijden4 = new Werktijden(30, 40, 0);
        Werktijden werktijden5 = new Werktijden(null, null, null);

        return Stream.of(
                arguments(werktijden1, werktijden1),
                arguments(werktijden1, werktijden2),
                arguments(werktijden1, werktijden3),
                arguments(werktijden1, werktijden4),
                arguments(werktijden1, werktijden5),
                arguments(werktijden1, null),
                arguments(null, null)
        );
    }

    /**
     * Provide arguments which should not match for werktijden filter.
     */
    private static Stream<Arguments> werktijdenArgumentsDoesNotMatch() {
        Werktijden werktijden1 = new Werktijden(30, 40, 0);
        Werktijden werktijden2 = new Werktijden(31, 40, 0);
        Werktijden werktijden3 = new Werktijden(30, 39, 0);
        Werktijden werktijden4 = new Werktijden(30, 40, 1);

        return Stream.of(
                arguments(werktijden1, werktijden2),
                arguments(werktijden1, werktijden3),
                arguments(werktijden1, werktijden4),
                arguments(null, werktijden1)
        );
    }

    /**
     * Provide arguments which should match for cursus filter.
     */
    private static Stream<Arguments> cursusArgumentsMatches() {
        Cursus cursus1 = new Cursus("containing string something");
        Cursus cursus2 = new Cursus("string");
        Cursus cursus3 = new Cursus(null);

        return Stream.of(
                arguments(List.of(cursus1), List.of(cursus1)),
                arguments(List.of(cursus1), List.of(cursus2)),
                arguments(List.of(cursus1, cursus2), List.of(cursus2)),
                arguments(List.of(cursus1, cursus2), List.of(cursus1)),
                arguments(List.of(cursus1, cursus2), List.of(cursus1, cursus2)),
                arguments(List.of(cursus1), List.of()),
                arguments(List.of(cursus1), List.of(cursus3)),
                arguments(List.of(cursus1), null),
                arguments(null, null)
        );
    }

    /**
     * Provide arguments which should not match for cursus filter.
     */
    private static Stream<Arguments> cursusArgumentsDoesNotMatch() {
        Cursus cursus1 = new Cursus("containing string something");
        Cursus cursus2 = new Cursus("completely different");
        Cursus cursus3 = new Cursus("different");
        Cursus cursus4 = new Cursus("nee");

        return Stream.of(
                arguments(List.of(cursus1), List.of(cursus3)),
                arguments(List.of(cursus1, cursus2), List.of(cursus3, cursus4)),
                arguments(null, List.of(cursus1))
        );
    }

    /**
     * Provide arguments which should match for opleiding filter.
     */
    private static Stream<Arguments> opleidingArgumentsMatches() {
        OpleidingsnaamGecodeerd opleidingsnaam1 = new OpleidingsnaamGecodeerd(0L, "string");
        OpleidingsnaamGecodeerd opleidingsnaam2 = new OpleidingsnaamGecodeerd(1L, "string");
        OpleidingsnaamOngecodeerd opleidingsnaam3 = new OpleidingsnaamOngecodeerd("naam", "string1");
        OpleidingsnaamOngecodeerd opleidingsnaam4 = new OpleidingsnaamOngecodeerd("naam2", "string2");
        MPOpleidingsnaamOngecodeerd opleidingsnaam5 = new MPOpleidingsnaamOngecodeerd("naam");
        MPOpleidingsnaamOngecodeerd opleidingsnaam6 = new MPOpleidingsnaamOngecodeerd("naam2");

        Opleiding opleiding1 = new Opleiding(1, 0, opleidingsnaam1);
        Opleiding opleiding2 = new Opleiding(1,0, opleidingsnaam2);
        Opleiding opleiding3 = new Opleiding(1, 0, opleidingsnaam3);
        Opleiding opleiding4 = new Opleiding(1,0, opleidingsnaam4);

        MPOpleiding opleiding5 = new MPOpleiding(1,0 , opleidingsnaam1);
        MPOpleiding opleiding6 = new MPOpleiding(1,0 , opleidingsnaam2);
        MPOpleiding opleiding7 = new MPOpleiding(1,0 , opleidingsnaam5);
        MPOpleiding opleiding8 = new MPOpleiding(1,0 , opleidingsnaam6);




        return Stream.of(
                arguments(List.of(opleiding1, opleiding2), List.of(opleiding5, opleiding6)),
                arguments(List.of(opleiding2), List.of(opleiding6)),
                arguments(List.of(opleiding1, opleiding2), List.of(opleiding5)),
                arguments(List.of(opleiding1), null),
                arguments(List.of(opleiding1), List.of()),

                arguments(List.of(opleiding3), List.of(opleiding7)),
                arguments(List.of(opleiding3, opleiding4), List.of(opleiding7)),
                arguments(List.of(opleiding3, opleiding4), List.of(opleiding7, opleiding8)),
                arguments(null, null)
        );
    }

    /**
     * Provide arguments which should not match for opleiding filter.
     */
    private static Stream<Arguments> opleidingArgumentsDoesNotMatch() {
        OpleidingsnaamGecodeerd opleidingsnaam1 = new OpleidingsnaamGecodeerd(0L, "string");
        OpleidingsnaamGecodeerd opleidingsnaam2 = new OpleidingsnaamGecodeerd(1L, "string");
        OpleidingsnaamOngecodeerd opleidingsnaam3 = new OpleidingsnaamOngecodeerd("naam", "string1");
        OpleidingsnaamOngecodeerd opleidingsnaam4 = new OpleidingsnaamOngecodeerd("iets anders", "string2");
        MPOpleidingsnaamOngecodeerd opleidingsnaam5 = new MPOpleidingsnaamOngecodeerd("naam");
        MPOpleidingsnaamOngecodeerd opleidingsnaam6 = new MPOpleidingsnaamOngecodeerd("iets anders");

        Opleiding opleiding1 = new Opleiding(1, 0, opleidingsnaam1);
        Opleiding opleiding2 = new Opleiding(1,0, opleidingsnaam2);
        Opleiding opleiding3 = new Opleiding(1, 0, opleidingsnaam3);
        Opleiding opleiding4 = new Opleiding(1,0, opleidingsnaam4);

        MPOpleiding opleiding5 = new MPOpleiding(1,0 , opleidingsnaam1);
        MPOpleiding opleiding6 = new MPOpleiding(2,0 , opleidingsnaam1);
        MPOpleiding opleiding7 = new MPOpleiding(1,1 , opleidingsnaam1);
        MPOpleiding opleiding8 = new MPOpleiding(2,1 , opleidingsnaam1);



        MPOpleiding opleiding9 = new MPOpleiding(1,0 , opleidingsnaam2);
        MPOpleiding opleiding10 = new MPOpleiding(1,0 , opleidingsnaam5);
        MPOpleiding opleiding11 = new MPOpleiding(1,0 , opleidingsnaam6);

        return Stream.of(
                arguments(List.of(opleiding1), List.of(opleiding9)),
                arguments(List.of(opleiding1), List.of(opleiding5, opleiding9)),
                arguments(List.of(opleiding1), List.of(opleiding6)),
                arguments(List.of(opleiding1), List.of(opleiding7)),
                arguments(List.of(opleiding1), List.of(opleiding8)),
                arguments(List.of(opleiding2), List.of(opleiding5)),
                arguments(List.of(opleiding3), List.of(opleiding11)),
                arguments(List.of(opleiding4), List.of(opleiding10)),
                arguments(null, List.of(opleiding5))
        );
    }

    /**
     * Provide arguments which should match for gedragscompetentie filter.
     */
    private static Stream<Arguments> gedragscompetentieArgumentsMatches() {
        Gedragscompetentie gedrag1 = new Gedragscompetentie(24100, "containing string containing", 1);
        Gedragscompetentie gedrag2 = new Gedragscompetentie(24100, "string", 1);
        Gedragscompetentie gedrag3 = new Gedragscompetentie(null, null, null);


        return Stream.of(
                arguments(List.of(gedrag1), List.of(gedrag1)),
                arguments(List.of(gedrag1), List.of(gedrag2)),
                arguments(List.of(gedrag1, gedrag2), List.of(gedrag1, gedrag2)),
                arguments(List.of(gedrag1, gedrag2), List.of(gedrag2)),
                arguments(List.of(gedrag1), null),
                arguments(List.of(gedrag1), List.of(gedrag3)),
                arguments(List.of(gedrag1), List.of()),
                arguments(null, null)

        );
    }

    /**
     * Provide arguments which should not match for gedragscompetentie filter.
     */
    private static Stream<Arguments> gedragscompetentieArgumentsDoesNotMatch() {
        Gedragscompetentie gedrag1 = new Gedragscompetentie(24100, "string", 2);
        Gedragscompetentie gedrag2 = new Gedragscompetentie(24100, "not containing containing", 9);
        Gedragscompetentie gedrag3 = new Gedragscompetentie(24101, "string", 2);
        Gedragscompetentie gedrag4 = new Gedragscompetentie(24100, "string", 1);


        return Stream.of(
                arguments(List.of(gedrag1), List.of(gedrag2)),
                arguments(List.of(gedrag1), List.of(gedrag3)),
                arguments(List.of(gedrag1), List.of(gedrag4)),
                arguments(List.of(gedrag1), List.of(gedrag1, gedrag2)),
                arguments(null, List.of(gedrag2))
        );
    }

    /**
     * Provide arguments which should match for vakvaardigheid filter.
     */
    private static Stream<Arguments> vakvaardigheidArgumentsMatches() {
        Vakvaardigheid vak1 = new Vakvaardigheid("containing string containing");
        Vakvaardigheid vak2 = new Vakvaardigheid("string2");
        Vakvaardigheid vak3 = new Vakvaardigheid("string");
        Vakvaardigheid vak4 = new Vakvaardigheid(null);


        return Stream.of(
                arguments(List.of(vak1), List.of(vak1)),
                arguments(List.of(vak1), List.of(vak3)),
                arguments(List.of(vak1, vak2), List.of(vak1, vak2)),
                arguments(List.of(vak1, vak2), List.of(vak3)),
                arguments(List.of(vak1), null),
                arguments(List.of(vak1), List.of()),
                arguments(List.of(vak1), List.of(vak4)),
                arguments(null, null)


        );
    }

    /**
     * Provide arguments which should not match for vakvaardigheid filter.
     */
    private static Stream<Arguments> vakvaardigheidArgumentsDoesNotMatch() {
        Vakvaardigheid vak1 = new Vakvaardigheid("string");
        Vakvaardigheid vak2 = new Vakvaardigheid("string2");
        Vakvaardigheid vak3 = new Vakvaardigheid("only string");

        return Stream.of(
                arguments(List.of(vak1), List.of(vak2)),
                arguments(List.of(vak1, vak2), List.of(vak3)),
                arguments(null, List.of(vak2))
        );

    }

    /**
     * Provide arguments which should match for taalbeheersing filter.
     */
    private static Stream<Arguments> taalbeheersingArgumentsMatch() {
        Taalbeheersing taal1 = new Taalbeheersing("aaa", 4, 4, 4, 4);
        Taalbeheersing taal2 = new Taalbeheersing("bbb", 3, 3, 3, 3);
        Taalbeheersing taal3 = new Taalbeheersing(null, null, null, null, null);

        return Stream.of(
                arguments(List.of(taal1), List.of(taal1)),
                arguments(List.of(taal1, taal2), List.of(taal1, taal2)),
                arguments(List.of(taal1, taal2), List.of(taal2)),
                arguments(List.of(taal1), null),
                arguments(List.of(taal1), List.of()),
                arguments(List.of(taal1), List.of(taal3)),
                arguments(null, null)

        );

    }

    /**
     * Provide arguments which should not match for taalbeheersing filter.
     */
    private static Stream<Arguments> taalbeheersingArgumentsDoesNotMatch() {
        Taalbeheersing taal1 = new Taalbeheersing("aaa", 3, 3, 3, 3);
        Taalbeheersing taal2 = new Taalbeheersing("aaa", 4, 3, 3, 3);
        Taalbeheersing taal3 = new Taalbeheersing("aaa", 3, 4, 3, 3);
        Taalbeheersing taal4 = new Taalbeheersing("aaa", 3, 3, 4, 3);
        Taalbeheersing taal5 = new Taalbeheersing("aaa", 3, 3, 3, 4);
        Taalbeheersing taal6 = new Taalbeheersing("bbb", 4, 4, 4, 4);

        return Stream.of(
                arguments(List.of(taal1), List.of(taal2)),
                arguments(List.of(taal1), List.of(taal3)),
                arguments(List.of(taal1), List.of(taal4)),
                arguments(List.of(taal1), List.of(taal5)),
                arguments(List.of(taal1), List.of(taal6)),
                arguments(List.of(taal1), List.of(taal1, taal6)),
                arguments(null, List.of(taal1))
        );

    }


}
