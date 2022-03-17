package com.cap.pocvng.repository;

import com.cap.pocvng.entity.*;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ArgumentsProvider {

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
     * Provide arguments which should match for voorkeursland filter.
     */
    private static Stream<Arguments> VoorkeurslandArgumentsMatches() {
        Voorkeursland land1 = new Voorkeursland("NL");
        Voorkeursland land2 = new Voorkeursland("DE");

        return Stream.of(
                arguments(List.of(land1), List.of(land1)),
                arguments(List.of(land1, land2), List.of(land2)),
                arguments(List.of(land1, land2), List.of(land1, land2)),
                arguments(List.of(land1), List.of(new Voorkeursland(null))),
                arguments(List.of(land1), List.of()),
                arguments(List.of(land1), null),
                arguments(null, null)
        );
    }

    /**
     * Provide arguments which should not match for voorkeursland filter.
     */
    private static Stream<Arguments> VoorkeurslandArgumentsDoesNotMatch() {
        Voorkeursland land1 = new Voorkeursland("NL");
        Voorkeursland land2 = new Voorkeursland("DE");

        return Stream.of(
                arguments(List.of(land1), List.of(land1, land2)),
                arguments(List.of(land1), List.of(land2)),
                arguments(null, List.of(land1))
        );
    }

    /**
     * Provide arguments which should match for vervoersmiddel filter.
     */
    private static Stream<Arguments> VervoersmiddelArgumentsMatches() {
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
    private static Stream<Arguments> VervoersmiddelArgumentsDoesNotMatch() {
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
    private static Stream<Arguments> FlexibiliteitArgumentsMatches() {
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
    private static Stream<Arguments> FlexibiliteitArgumentsDoesNotMatch() {
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
    private static Stream<Arguments> WerktijdenArgumentsMatches() {
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
    private static Stream<Arguments> WerktijdenArgumentsDoesNotMatch() {
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
     * Provide arguments which should match for codeWerkEnDenkniveauWerkzoekende filter.
     */
    private static Stream<Arguments> codeWerkEnDenkniveauWerkzoekendeArgumentsMatches() {
        return Stream.of(
                arguments(7, 7),
                arguments(6, 5),
                arguments(7, null),
                arguments(null, null)
        );

    }


    /**
     * Provide arguments which should not match for codeWerkEnDenkniveauWerkzoekende filter.
     */
    private static Stream<Arguments> codeWerkEnDenkniveauWerkzoekendeArgumentsDoesNotMatch() {
        return Stream.of(
                arguments(3, 4),
                arguments(null, 4)
        );

    }

    /**
     * Provide arguments which should match for vakvaardigheid filter.
     */
    private static Stream<Arguments> VakvaardigheidArgumentsMatches() {
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
    private static Stream<Arguments> VakvaardigheidArgumentsDoesNotMatch() {
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
    private static Stream<Arguments> TaalbeheersingArgumentsMatch() {
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
    private static Stream<Arguments> TaalbeheersingArgumentsDoesNotMatch() {
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

    /**
     * Provide arguments which should match for gedragscompetentie filter.
     */
    private static Stream<Arguments> GedragscompetentieArgumentsMatches() {
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
    private static Stream<Arguments> GedragscompetentieArgumentsDoesNotMatch() {
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
     * Provide arguments which should match for rijbewijs filter.
     */
    private static Stream<Arguments> RijbewijsArgumentsMatches() {
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
    private static Stream<Arguments> RijbewijsArgumentsDoesNotMatch() {
        Rijbewijs rijbewijs1 = new Rijbewijs("B");
        Rijbewijs rijbewijs2 = new Rijbewijs("A");


        return Stream.of(
                arguments(List.of(rijbewijs1), List.of(rijbewijs2)),
                arguments(List.of(rijbewijs1), List.of(rijbewijs1, rijbewijs2)),
                arguments(null, List.of(rijbewijs2))
        );
    }

    /**
     * Provide arguments which should match for cursus filter.
     */
    private static Stream<Arguments> CursusArgumentsMatches() {
        Cursus cursus1 = new Cursus();
        cursus1.setNaamCursus("containing string something");
        Cursus cursus2 = new Cursus();
        cursus2.setNaamCursus("string");
        Cursus cursus3 = new Cursus();
        cursus3.setNaamCursus(null);


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
    private static Stream<Arguments> CursusArgumentsDoesNotMatch() {
        Cursus cursus1 = new Cursus();
        cursus1.setNaamCursus("containing string something");
        Cursus cursus2 = new Cursus();
        cursus2.setNaamCursus("completely different");
        Cursus cursus3 = new Cursus();
        cursus3.setNaamCursus("different");
        Cursus cursus4 = new Cursus();
        cursus4.setNaamCursus("nee");

        return Stream.of(
                arguments(List.of(cursus1), List.of(cursus3)),
                arguments(List.of(cursus1, cursus2), List.of(cursus3, cursus4)),
                arguments(null, List.of(cursus1))
        );
    }

    /**
     * Provide arguments which should match for opleiding filter.
     */
    private static Stream<Arguments> OpleidingArgumentsMatches() {
        OpleidingsnaamGecodeerd opleidingsnaam1 = new OpleidingsnaamGecodeerd(0L, "string");
        OpleidingsnaamGecodeerd opleidingsnaam2 = new OpleidingsnaamGecodeerd(1L, "string");
        OpleidingsnaamOngecodeerd opleidingsnaam3 = new OpleidingsnaamOngecodeerd("naam", "string1");
        OpleidingsnaamOngecodeerd opleidingsnaam4 = new OpleidingsnaamOngecodeerd("naam2", "string2");


        Opleiding opleiding1 = new Opleiding();
        opleiding1.setOpleidingsnaam(opleidingsnaam1);
        Opleiding opleiding2 = new Opleiding();
        opleiding2.setOpleidingsnaam(opleidingsnaam2);
        Opleiding opleiding3 = new Opleiding();
        opleiding3.setOpleidingsnaam(opleidingsnaam3);
        Opleiding opleiding4 = new Opleiding();
        opleiding4.setOpleidingsnaam(opleidingsnaam4);

        return Stream.of(
                arguments(List.of(opleiding1, opleiding2), List.of(opleiding1, opleiding2)),
                arguments(List.of(opleiding2), List.of(opleiding2)),
                arguments(List.of(opleiding1, opleiding2), List.of(opleiding1)),
                arguments(List.of(opleiding1), null),
                arguments(List.of(opleiding1), List.of()),

                arguments(List.of(opleiding3), List.of(opleiding3)),
                arguments(List.of(opleiding3, opleiding4), List.of(opleiding4)),
                arguments(List.of(opleiding3, opleiding4), List.of(opleiding3, opleiding4)),
                arguments(null, null)
        );
    }

    /**
     * Provide arguments which should not match for opleiding filter.
     */
    private static Stream<Arguments> OpleidingArgumentsDoesNotMatch() {
        OpleidingsnaamGecodeerd opleidingsnaam1 = new OpleidingsnaamGecodeerd(0L, "een");
        OpleidingsnaamGecodeerd opleidingsnaam2 = new OpleidingsnaamGecodeerd(1L, "twee");
        OpleidingsnaamOngecodeerd opleidingsnaam3 = new OpleidingsnaamOngecodeerd("naam", "string1");
        OpleidingsnaamOngecodeerd opleidingsnaam4 = new OpleidingsnaamOngecodeerd("iets anders", "string2");

        Opleiding opleiding1 = new Opleiding();
        opleiding1.setOpleidingsnaam(opleidingsnaam1);
        Opleiding opleiding2 = new Opleiding();
        opleiding2.setOpleidingsnaam(opleidingsnaam2);
        Opleiding opleiding3 = new Opleiding();
        opleiding3.setOpleidingsnaam(opleidingsnaam3);
        Opleiding opleiding4 = new Opleiding();
        opleiding4.setOpleidingsnaam(opleidingsnaam4);


        MPOpleiding opleiding5 = new MPOpleiding(0, 0, null, opleidingsnaam1);
        MPOpleiding opleiding6 = new MPOpleiding(1, 0, null, opleidingsnaam1);
        MPOpleiding opleiding7 = new MPOpleiding(0, 1, null, opleidingsnaam1);
        MPOpleiding opleiding8 = new MPOpleiding(1, 1, null, opleidingsnaam1);

        MPOpleiding opleiding9 = new MPOpleiding(0, 0, null, opleidingsnaam2);


        return Stream.of(
                arguments(List.of(opleiding1), List.of(opleiding9)),
                arguments(List.of(opleiding1, opleiding2), List.of(opleiding2, opleiding3)),
                arguments(List.of(opleiding1), List.of(opleiding5, opleiding9)),
                arguments(List.of(opleiding1), List.of(opleiding6)),
                arguments(List.of(opleiding1), List.of(opleiding7)),
                arguments(List.of(opleiding1), List.of(opleiding8)),
                arguments(List.of(opleiding2), List.of(opleiding5)),
                arguments(List.of(opleiding3), List.of(opleiding4)),
                arguments(List.of(opleiding4), List.of(opleiding3)),
                arguments(null, List.of(opleiding5))
        );
    }


    /**
     * Provide arguments which should match for Werkervaring filter.
     */
    private static Stream<Arguments> WerkervaringArgumentsMatches() {
        Werkervaring werkervaring1 = new Werkervaring();
        werkervaring1.setNaamOrganisatie("containing string containing");

        Werkervaring werkervaring2 = new Werkervaring();
        werkervaring2.setNaamOrganisatie("string");

        Werkervaring werkervaring3 = new Werkervaring();
        werkervaring3.setNaamOrganisatie(null);


        return Stream.of(
                arguments(List.of(werkervaring1), List.of(werkervaring1)),
                arguments(List.of(werkervaring1, werkervaring2), List.of(werkervaring2)),
                arguments(List.of(werkervaring1, werkervaring2), List.of(werkervaring1)),
                arguments(List.of(werkervaring1, werkervaring2), List.of(werkervaring2, werkervaring1)),
                arguments(List.of(werkervaring1), null),
                arguments(List.of(werkervaring1), List.of()),
                arguments(List.of(werkervaring1), List.of(werkervaring3)),
                arguments(null, null)


        );
    }

    /**
     * Provide arguments which should not match for Werkervaring filter.
     */
    private static Stream<Arguments> WerkervaringArgumentsDoesNotMatch() {
        Werkervaring werkervaring1 = new Werkervaring();
        werkervaring1.setNaamOrganisatie("containing string containing");

        Werkervaring werkervaring2 = new Werkervaring();
        werkervaring2.setNaamOrganisatie("something else");

        Werkervaring werkervaring3 = new Werkervaring();
        werkervaring3.setNaamOrganisatie("nothing matching");


        return Stream.of(
                arguments(List.of(werkervaring1), List.of(werkervaring3)),
                arguments(List.of(werkervaring1, werkervaring2), List.of(werkervaring2, werkervaring3)),
                arguments(null, List.of(werkervaring3))
        );
    }

    /**
     * Provide arguments which should match for sector filter.
     */
    private static Stream<Arguments> SectorArgumentsMatches() {
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
     * Provide arguments which should not match for sector filter.
     */
    private static Stream<Arguments> SectorArgumentsDoesNotMatch() {
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
     * Provide arguments which should match for beroepsnaam filter.
     */
    private static Stream<Arguments> BeroepsnaamArgumentsMatches() {
        BeroepsnaamOngecodeerd ongecodeerd1 = new BeroepsnaamOngecodeerd("containing string containing");
        BeroepsnaamOngecodeerd ongecodeerd2 = new BeroepsnaamOngecodeerd("string");
        BeroepsnaamGecodeerd gecodeerd1 = new BeroepsnaamGecodeerd("st", "string");
        BeroepsnaamGecodeerd gecodeerd2 = new BeroepsnaamGecodeerd("st", "containing string containing");
        BeroepsnaamGecodeerd gecodeerd3 = new BeroepsnaamGecodeerd(null, null);


        return Stream.of(
                // Ongecodeerde combinations
                arguments(List.of(ongecodeerd1), List.of(ongecodeerd1)),
                arguments(List.of(ongecodeerd1), List.of(ongecodeerd2)),
                arguments(List.of(ongecodeerd1, ongecodeerd2), List.of(ongecodeerd2)),
                arguments(List.of(ongecodeerd1, ongecodeerd2), List.of(ongecodeerd1)),
                arguments(List.of(ongecodeerd1, ongecodeerd2), List.of(ongecodeerd1, ongecodeerd2)),
                // Gecodeerde combinations
                arguments(List.of(gecodeerd1), List.of(gecodeerd1)),
                arguments(List.of(gecodeerd2), List.of(gecodeerd1)),
                arguments(List.of(gecodeerd1, gecodeerd2), List.of(gecodeerd2)),
                arguments(List.of(gecodeerd1, gecodeerd2), List.of(gecodeerd1)),
                arguments(List.of(gecodeerd1, gecodeerd2), List.of(gecodeerd1, gecodeerd2)),
                // Mix of gecodeerd and ongecodeerd.
                arguments(List.of(gecodeerd1, ongecodeerd1), List.of(gecodeerd1)),
                arguments(List.of(gecodeerd1, ongecodeerd1), List.of(ongecodeerd1)),
                arguments(List.of(gecodeerd1, ongecodeerd1), List.of(gecodeerd1, ongecodeerd1)),

                arguments(List.of(ongecodeerd1), null),
                arguments(List.of(ongecodeerd1), List.of()),
                arguments(List.of(ongecodeerd1), List.of(gecodeerd3)),

                arguments(List.of(gecodeerd1), null),
                arguments(List.of(ongecodeerd1), null),
                arguments(null, null)
        );
    }

    /**
     * Provide arguments which should not match for beroepsnaam filter.
     */
    private static Stream<Arguments> BeroepsnaamArgumentsDoesNotMatch() {

        BeroepsnaamOngecodeerd ongecodeerd1 = new BeroepsnaamOngecodeerd("containing string containing");
        BeroepsnaamOngecodeerd ongecodeerd2 = new BeroepsnaamOngecodeerd("not relevant");
        BeroepsnaamOngecodeerd ongecodeerd3 = new BeroepsnaamOngecodeerd("another one");

        BeroepsnaamGecodeerd gecodeerd1 = new BeroepsnaamGecodeerd("st", "string");
        BeroepsnaamGecodeerd gecodeerd2 = new BeroepsnaamGecodeerd("sz", "something else");
        BeroepsnaamGecodeerd gecodeerd3 = new BeroepsnaamGecodeerd("st", "not relevant");

        return Stream.of(
                arguments(List.of(ongecodeerd1), List.of(ongecodeerd2)),
                arguments(List.of(ongecodeerd1, ongecodeerd2), List.of(ongecodeerd2, ongecodeerd3)),
                arguments(null, List.of(ongecodeerd1)),

                arguments(List.of(gecodeerd1), List.of(gecodeerd2)),
                arguments(List.of(gecodeerd1), List.of(gecodeerd3)),

                arguments(List.of(gecodeerd1, gecodeerd2), List.of(gecodeerd2, gecodeerd3)),
                arguments(null, List.of(gecodeerd1))

        );
    }


}
