package com.cap.pocvng.repository;

import com.cap.pocvng.entity.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Specifications that are used to filter Werkzoekende based on request criteria.
 * <p>
 * Indications are an exact match. If asked for 2 = Nee, then werkzoekenden with indication 2 = Nee are returned.
 * <p>
 * Descriptions are a like match. If asked for "string" to be contained in a description, than werkzoekenden containing
 * this string, such as "this is a text with string", are returned.
 * <p>
 * Scaling attributes, such as code attributes, are matched greater/less than or equal to. If asked for taalbeheersing
 * 2 = Redelijk, than werkzoekenden having 3 = Goed are also returned.
 * <p>
 * Werkzoekenden with attributes "Niet van toepassing" are also returned with any request.
 */
@Component
public class WerkzoekendeSpecifications {
    //TODO: subquery count bit iffy if Werkzoekende has one field twice because request checks size.

    private WerkzoekendeSpecifications() {
    }

    /**
     * Returns a Specification which applies to the searchCriteria given.
     *
     * @param searchCriteria criteria on which to filter on.
     * @return Specification which applies to the criteria.
     */
    public static Specification<Werkzoekende> createWerkZoekendeSpecification(MPWerkzoekende searchCriteria, String oin) {
        return oinSpecification(oin)
                .and(indicatieLdrRegistratie(searchCriteria.getIndicatieLdrRegistratie()))
                .and(indicatieBeschikbaarheidContactgegevens(searchCriteria.getIndicatieBeschikbaarheidContactgegevens()))
                .and(voorkeursland(searchCriteria.getVoorkeursland()))
                .and(vervoermiddel(searchCriteria.getVervoermiddel()))
                .and(flexibiliteit(searchCriteria.getFlexibiliteit()))
                .and(werkttijden(searchCriteria.getWerktijden()))
                .and(contractvorm(searchCriteria.getContractvorm()))
                .and(codeWerkEnDenkniveauWerkzoekende(searchCriteria.getArbeidsmarktkwalificatie()))
                .and(vakvaardigheid(searchCriteria.getArbeidsmarktkwalificatie()))
                .and(taalbeheersing(searchCriteria.getArbeidsmarktkwalificatie()))
                .and(gedragscompetentie(searchCriteria.getArbeidsmarktkwalificatie()))
                .and(rijbewijs(searchCriteria.getArbeidsmarktkwalificatie()))
                .and(cursus(searchCriteria.getArbeidsmarktkwalificatie()))
                .and(opleiding(searchCriteria.getArbeidsmarktkwalificatie()))
                .and(werkervaring(searchCriteria.getArbeidsmarktkwalificatie()))
                .and(sector(searchCriteria.getSector()))
                .and(beroep(searchCriteria.getBemiddelingsberoep()));
    }

    private static Specification<Werkzoekende> oinSpecification(String oin) {
        return (root, query, builder) -> builder.equal(root.get(Werkzoekende_.oin), oin);
    }

    private static Specification<Werkzoekende> indicatieLdrRegistratie(Integer indicatie) {
        return (root, query, builder) -> {
            if (indicatie == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get(Werkzoekende_.indicatieLdrRegistratie), indicatie);
        };
    }

    private static Specification<Werkzoekende> indicatieBeschikbaarheidContactgegevens(Integer indicatie) {
        return (root, query, builder) -> {
            if (indicatie == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get(Werkzoekende_.indicatieBeschikbaarheidContactgegevens), indicatie);
        };
    }

    private static Specification<Werkzoekende> voorkeursland(List<Voorkeursland> voorkeursland) {
        return (root, query, builder) -> {
            if (voorkeursland == null) {
                return builder.conjunction();
            }

            // Filter out null and landencode being null.
            List<Voorkeursland> nullFilteredVoorkeursland = voorkeursland.stream()
                    .filter(x -> x != null && x.getLandencodeIso() != null)
                    .collect(Collectors.toList());

            // only return if werkzoekende has all voorkeurslanden requested by keeping count.
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Werkzoekende> r = subquery.correlate(root);
            subquery.where(r.join(Werkzoekende_.voorkeursland).get(Voorkeursland_.landencodeIso).in(
                    nullFilteredVoorkeursland.stream().map(x -> x.getLandencodeIso()).collect(Collectors.toList())));
            subquery.select(builder.count(builder.literal(1)));
            return builder.greaterThanOrEqualTo(subquery, (long) nullFilteredVoorkeursland.size());

        };
    }

    private static Specification<Werkzoekende> vervoermiddel(List<MPVervoermiddel> vervoermiddel) {
        return (root, query, builder) -> {
            if (vervoermiddel == null) {
                return builder.conjunction();
            }

            // Create subquery to count the amount of matches.
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Werkzoekende> r = subquery.correlate(root);

            List<Predicate> predicateList = new ArrayList<>();

            // Join Werkzoekende with Vervoermiddel.
            ListJoin<Werkzoekende, Vervoermiddel> join = r.join(Werkzoekende_.vervoermiddel);

            for (MPVervoermiddel vervoer : vervoermiddel) {

                Predicate pred1 = builder.conjunction();
                Predicate pred2 = builder.conjunction();

                if (vervoer.getIndicatieBeschikbaarVoorUitvoeringWerk() != null) {
                    pred1 = builder.equal(join.get(Vervoermiddel_.indicatieBeschikbaarVoorUitvoeringWerk),
                            vervoer.getIndicatieBeschikbaarVoorUitvoeringWerk());
                }
                if (vervoer.getIndicatieBeschikbaarVoorWoonWerkverkeer() != null) {
                    pred2 = builder.equal(join.get(Vervoermiddel_.indicatieBeschikbaarVoorWoonWerkverkeer),
                            vervoer.getIndicatieBeschikbaarVoorWoonWerkverkeer());
                }

                // Build conjunction.
                predicateList.add(builder.and(pred1, pred2));
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            // If matches are gte to request, return true.
            return builder.greaterThanOrEqualTo(subquery, (long) vervoermiddel.size());
        };
    }

    private static Specification<Werkzoekende> flexibiliteit(Flexibiliteit flexibiliteit) {
        return (root, query, builder) -> {
            if (flexibiliteit == null) {
                return builder.conjunction();
            }

            Path<Flexibiliteit> path = root.get(Werkzoekende_.flexibiliteit);

            Predicate pred1 = builder.conjunction();
            Predicate pred2 = builder.conjunction();
            Predicate pred3 = builder.conjunction();
            Predicate pred4 = builder.conjunction();

            // coderegiostraal gte than request.
            // Indicatie equal to request.
            // Aanvang beschikbaar lte date of request
            // datum einde beschikbaar gte date of request.

            if (flexibiliteit.getCodeRegiostraal() != null) {
                pred1 = builder.greaterThanOrEqualTo(path.get(Flexibiliteit_.codeRegiostraal), flexibiliteit.getCodeRegiostraal());
            }
            if (flexibiliteit.getIndicatieOnregelmatigWerkOfPloegendienst() != null) {
                pred2 = builder.equal(path.get(Flexibiliteit_.indicatieOnregelmatigWerkOfPloegendienst), flexibiliteit.getIndicatieOnregelmatigWerkOfPloegendienst());
            }
            if (flexibiliteit.getDatumAanvangBeschikbaarVoorWerk() != null) {
                pred3 = builder.lessThanOrEqualTo(path.get(Flexibiliteit_.datumAanvangBeschikbaarVoorWerk), flexibiliteit.getDatumAanvangBeschikbaarVoorWerk());
            }
            if (flexibiliteit.getDatumEindeBeschikbaarVoorWerk() != null) {
                pred4 = builder.greaterThanOrEqualTo(path.get(Flexibiliteit_.datumEindeBeschikbaarVoorWerk), flexibiliteit.getDatumEindeBeschikbaarVoorWerk());
            }

            return builder.and(pred1, builder.and(pred2, builder.and(pred3, pred4)));
        };
    }


    private static Specification<Werkzoekende> werkttijden(Werktijden werktijden) {
        return (root, query, builder) -> {
            if (werktijden == null) {
                return builder.conjunction();
            }

            // Werktijden minimaal gte to request
            // Werktijden maximaal lte to request
            Path<Werktijden> path = root.get(Werkzoekende_.werktijden);
            Predicate pred1 = builder.greaterThanOrEqualTo(path.get(Werktijden_.aantalWerkurenPerWeekMinimaal), werktijden.getAantalWerkurenPerWeekMinimaal());
            Predicate pred2 = builder.lessThanOrEqualTo(path.get(Werktijden_.aantalWerkurenPerWeekMaximaal), werktijden.getAantalWerkurenPerWeekMaximaal());
            Predicate pred3 = builder.equal(path.get(Werktijden_.indicatieKantoortijden), werktijden.getIndicatieKantoortijden());

            // if not in request, then return true.
            if (werktijden.getAantalWerkurenPerWeekMinimaal() == null) {
                pred1 = builder.conjunction();
            }
            if (werktijden.getAantalWerkurenPerWeekMaximaal() == null) {
                pred2 = builder.conjunction();
            }
            if (werktijden.getIndicatieKantoortijden() == null) {
                pred3 = builder.conjunction();
            }

            return builder.and(pred1, builder.and(pred2, pred3));
        };
    }

    private static Specification<Werkzoekende> contractvorm(List<Contractvorm> contractvorm) {
        return (root, query, builder) -> {
            if (contractvorm == null) {
                return builder.conjunction();
            }

            // Create subquery to count the amount of matches.
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Werkzoekende> r = subquery.correlate(root);

            ListJoin<Werkzoekende, Contractvorm> join = r.join(Werkzoekende_.contractvorm);

            List<Predicate> predicateList = new ArrayList<>();

            for (Contractvorm vorm : contractvorm) {

                Predicate pred1 = builder.conjunction();
                Predicate pred2 = builder.conjunction();

                if (vorm.getCodeTypeArbeidscontract() != null) {
                    pred1 = builder.equal(join.get(Contractvorm_.codeTypeArbeidscontract), vorm.getCodeTypeArbeidscontract());
                }
                if (vorm.getCodeTypeOvereenkomst() != null) {
                    pred2 = builder.equal(join.get(Contractvorm_.codeTypeOvereenkomst), vorm.getCodeTypeOvereenkomst());
                }

                // Build conjunction.
                predicateList.add(builder.and(pred1, pred2));
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            // If matches are gte to request, return true.
            return builder.greaterThanOrEqualTo(subquery, (long) contractvorm.size());
        };
    }

    private static Specification<Werkzoekende> codeWerkEnDenkniveauWerkzoekende(MPArbeidsmarktkwalificatie arbeidsmarktkwalificatie) {
        return (root, query, builder) -> {

            if (arbeidsmarktkwalificatie == null || arbeidsmarktkwalificatie.getCodeWerkEnDenkniveauWerkzoekende() == null) {
                return builder.conjunction();
            }

            Integer codeWerkEnDenkniveauWerkzoekende = arbeidsmarktkwalificatie.getCodeWerkEnDenkniveauWerkzoekende();

            return builder.greaterThanOrEqualTo(root.get(Werkzoekende_.arbeidsmarktkwalificatie).get(Arbeidsmarktkwalificatie_.codeWerkEnDenkniveauWerkzoekende), codeWerkEnDenkniveauWerkzoekende);
        };
    }

    private static Specification<Werkzoekende> vakvaardigheid(MPArbeidsmarktkwalificatie arbeidsmarktkwalificatie) {
        return (root, query, builder) -> {
            if (arbeidsmarktkwalificatie == null || arbeidsmarktkwalificatie.getVakvaardigheid() == null
            ) {
                return builder.conjunction();
            }

            List<Vakvaardigheid> vakvaardigheid = arbeidsmarktkwalificatie.getVakvaardigheid();

            // Create subquery to count the amount of matches.
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Werkzoekende> r = subquery.correlate(root);

            Join<Werkzoekende, Arbeidsmarktkwalificatie> path = r.join(Werkzoekende_.arbeidsmarktkwalificatie);

            List<Predicate> predicateList = new ArrayList<>();

            // make a predicate for every string in vakvaardigheid
            for (Vakvaardigheid vak : vakvaardigheid) {
                Predicate pred1 = builder.conjunction();
                if (vak.getOmschrijving() != null) {
                    pred1 = builder.like(path.join(Arbeidsmarktkwalificatie_.vakvaardigheid).get(Vakvaardigheid_.omschrijving), "%" + vak.getOmschrijving() + "%");

                }
                predicateList.add(pred1);
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            // If matches are gte to request, return true.
            return builder.greaterThanOrEqualTo(subquery, (long) vakvaardigheid.size());
        };
    }

    private static Specification<Werkzoekende> taalbeheersing(MPArbeidsmarktkwalificatie arbeidsmarktkwalificatie) {
        return (root, query, builder) -> {
            if (arbeidsmarktkwalificatie == null || arbeidsmarktkwalificatie.getTaalbeheersing() == null) {
                return builder.conjunction();
            }
            List<Taalbeheersing> taalbeheersingen = arbeidsmarktkwalificatie.getTaalbeheersing();

            //Create subquery to count the amount of matches.
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Werkzoekende> r = subquery.correlate(root);

            // List to store your predicates in.
            List<Predicate> predicateList = new ArrayList<>();

            ListJoin<Arbeidsmarktkwalificatie, Taalbeheersing> path = r
                    .join(Werkzoekende_.arbeidsmarktkwalificatie)
                    .join(Arbeidsmarktkwalificatie_.taalbeheersing);


            for (Taalbeheersing taal : taalbeheersingen) {

                Predicate pred1 = builder.conjunction();
                Predicate pred2 = builder.conjunction();
                Predicate pred3 = builder.conjunction();
                Predicate pred4 = builder.conjunction();
                Predicate pred5 = builder.conjunction();

                if (taal.getCodeTaal() != null) {
                    pred1 = builder.equal(path.get(Taalbeheersing_.codeTaal), taal.getCodeTaal());
                }
                if (taal.getCodeNiveauTaalbeheersingMondeling() != null) {
                    pred2 = builder.greaterThanOrEqualTo(path.get(Taalbeheersing_.codeNiveauTaalbeheersingMondeling), taal.getCodeNiveauTaalbeheersingMondeling());
                }
                if (taal.getCodeNiveauTaalbeheersingSchriftelijk() != null) {
                    pred3 = builder.greaterThanOrEqualTo(path.get(Taalbeheersing_.codeNiveauTaalbeheersingSchriftelijk), taal.getCodeNiveauTaalbeheersingSchriftelijk());
                }
                if (taal.getCodeNiveauTaalbeheersingLezen() != null) {
                    pred4 = builder.greaterThanOrEqualTo(path.get(Taalbeheersing_.codeNiveauTaalbeheersingLezen), taal.getCodeNiveauTaalbeheersingLezen());
                }
                if (taal.getCodeNiveauTaalbeheersingLuisteren() != null) {
                    pred5 = builder.greaterThanOrEqualTo(path.get(Taalbeheersing_.codeNiveauTaalbeheersingLuisteren), taal.getCodeNiveauTaalbeheersingLuisteren());
                }

                Predicate conjunction = builder.and(pred1, builder.and(pred2, builder.and(pred3), builder.and(pred4, pred5)));
                predicateList.add(conjunction);
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            // If matches are gte to request, return true.
            return builder.greaterThanOrEqualTo(subquery, (long) taalbeheersingen.size());
        };
    }

    private static Specification<Werkzoekende> gedragscompetentie(MPArbeidsmarktkwalificatie arbeidsmarktkwalificatie) {
        return (root, query, builder) -> {
            if (arbeidsmarktkwalificatie == null || arbeidsmarktkwalificatie.getGedragscompetentie() == null) {
                return builder.conjunction();
            }

            List<Gedragscompetentie> gedragscompetenties = arbeidsmarktkwalificatie.getGedragscompetentie();

            //Create subquery to count the amount of matches.
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Werkzoekende> r = subquery.correlate(root);


            // List to store your predicates in.
            List<Predicate> predicateList = new ArrayList<>();

            ListJoin<Arbeidsmarktkwalificatie, Gedragscompetentie> path = r
                    .join(Werkzoekende_.arbeidsmarktkwalificatie)
                    .join(Arbeidsmarktkwalificatie_.gedragscompetentie);

            for (Gedragscompetentie gedragscompetentie : gedragscompetenties) {

                Predicate pred1 = builder.conjunction();
                Predicate pred2 = builder.conjunction();
                Predicate pred3 = builder.conjunction();

                if (gedragscompetentie.getCodeGedragscompetentie() != null) {
                    pred1 = builder.equal(path.get(Gedragscompetentie_.codeGedragscompetentie), gedragscompetentie.getCodeGedragscompetentie());
                }
                if (gedragscompetentie.getOmschrijvingGedragscompetentie() != null) {
                    // Match on any omschrijving which contains request omschrijving.
                    pred2 = builder.like(path.get(Gedragscompetentie_.omschrijvingGedragscompetentie), "%" + gedragscompetentie.getOmschrijvingGedragscompetentie() + "%");
                }
                if (gedragscompetentie.getCodeBeheersingGedragscompetentie() != null) {
                    pred3 = builder.lessThanOrEqualTo(path.get(Gedragscompetentie_.codeBeheersingGedragscompetentie), gedragscompetentie.getCodeBeheersingGedragscompetentie());
                }

                Predicate conjunction = builder.and(pred1, builder.and(pred2, pred3));
                predicateList.add(conjunction);
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            // If matches are gte to request, return true.
            return builder.greaterThanOrEqualTo(subquery, (long) gedragscompetenties.size());
        };
    }

    private static Specification<Werkzoekende> rijbewijs(MPArbeidsmarktkwalificatie arbeidsmarktkwalificatie) {
        return (root, query, builder) -> {
            if (arbeidsmarktkwalificatie == null || arbeidsmarktkwalificatie.getRijbewijs() == null) {
                return builder.conjunction();
            }

            //Create subquery to count the amount of matches.
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Werkzoekende> r = subquery.correlate(root);


            List<Rijbewijs> rijbewijzen = arbeidsmarktkwalificatie.getRijbewijs();

            // List to store your predicates in.
            List<Predicate> predicateList = new ArrayList<>();


            ListJoin<Arbeidsmarktkwalificatie, Rijbewijs> path = r
                    .join(Werkzoekende_.arbeidsmarktkwalificatie)
                    .join(Arbeidsmarktkwalificatie_.rijbewijs);

            for (Rijbewijs rijbewijs : rijbewijzen) {
                Predicate pred1 = builder.conjunction();

                if (rijbewijs.getCodeSoortRijbewijs() != null) {
                    pred1 = builder.equal(path.get(Rijbewijs_.codeSoortRijbewijs), rijbewijs.getCodeSoortRijbewijs());
                }

                predicateList.add(pred1);

            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            return builder.greaterThanOrEqualTo(subquery, (long) rijbewijzen.size());

        };
    }

    private static Specification<Werkzoekende> cursus(MPArbeidsmarktkwalificatie arbeidsmarktkwalificatie) {
        return (root, query, builder) -> {
            if (arbeidsmarktkwalificatie == null || arbeidsmarktkwalificatie.getCursus() == null) {
                return builder.conjunction();
            }

            //Create subquery to count the amount of matches.
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Werkzoekende> r = subquery.correlate(root);


            List<MPCursus> cursussen = arbeidsmarktkwalificatie.getCursus();
            List<Predicate> predicateList = new ArrayList<>();

            ListJoin<Arbeidsmarktkwalificatie, Cursus> path = r
                    .join(Werkzoekende_.arbeidsmarktkwalificatie)
                    .join(Arbeidsmarktkwalificatie_.cursus);

            for (MPCursus cursus : cursussen) {
                Predicate pred1 = builder.conjunction();

                if (cursus.getNaamCursus() != null) {
                    pred1 = builder.like(path.get(MPCursus_.naamCursus), "%" + cursus.getNaamCursus() + "%");
                }
                predicateList.add(pred1);
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            return builder.greaterThanOrEqualTo(subquery, (long) cursussen.size());
        };
    }

    private static Specification<Werkzoekende> opleiding(MPArbeidsmarktkwalificatie arbeidsmarktkwalificatie) {
        return (root, query, builder) -> {
            if (arbeidsmarktkwalificatie == null || arbeidsmarktkwalificatie.getOpleiding() == null) {
                return builder.conjunction();
            }

            //Create subquery to count the amount of matches.
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Werkzoekende> r = subquery.correlate(root);


            List<MPOpleiding> opleidingen = arbeidsmarktkwalificatie.getOpleiding();
            List<Predicate> predicateList = new ArrayList<>();

            ListJoin<Arbeidsmarktkwalificatie, Opleiding> path = r
                    .join(Werkzoekende_.arbeidsmarktkwalificatie)
                    .join(Arbeidsmarktkwalificatie_.opleiding);

            for (MPOpleiding opleiding : opleidingen) {
                Predicate pred1 = builder.conjunction();
                Predicate pred2 = builder.conjunction();
                Predicate pred3 = builder.conjunction();
                Predicate pred4 = builder.conjunction();

                if (opleiding.getCodeNiveauOpleiding() != null) {
                    pred1 = builder.greaterThanOrEqualTo(path.get(MPOpleiding_.codeNiveauOpleiding), opleiding.getCodeNiveauOpleiding());
                }
                // Indicatie 8 = Niet van Toepassing. This should also return true regardless of value werkzoekende.
                if (opleiding.getIndicatieDiploma() != null && opleiding.getIndicatieDiploma() != 8) {
                    pred2 = builder.equal(path.get(MPOpleiding_.indicatieDiploma), opleiding.getIndicatieDiploma());
                }

                Path<Opleidingsnaam> opleidingPath = path.get(MPOpleiding_.opleidingsnaam);

                // Depending on subclass, have different predicate.
                if (opleiding.getOpleidingsnaam() instanceof OpleidingsnaamOngecodeerd) {
                    Path<OpleidingsnaamOngecodeerd> treatedPath = builder.treat(opleidingPath, OpleidingsnaamOngecodeerd.class);
                    OpleidingsnaamOngecodeerd opleidingsnaam = (OpleidingsnaamOngecodeerd) opleiding.getOpleidingsnaam();
                    String requestOngecodeerd = opleidingsnaam.getNaamOpleidingOngecodeerd();
                    String requestOmschrijving = opleidingsnaam.getOmschrijvingOpleiding();
                    if (requestOngecodeerd != null) {
                        pred3 = builder.like(treatedPath.get(OpleidingsnaamOngecodeerd_.naamOpleidingOngecodeerd), "%" + requestOngecodeerd + "%");
                    }
                    if (requestOmschrijving != null) {
                        pred4 = builder.like(treatedPath.get(OpleidingsnaamOngecodeerd_.omschrijvingOpleiding), "%" + requestOmschrijving + "%");
                    }
                } else if (opleiding.getOpleidingsnaam() instanceof OpleidingsnaamGecodeerd) {
                    OpleidingsnaamGecodeerd opleidingsnaam = (OpleidingsnaamGecodeerd) opleiding.getOpleidingsnaam();
                    Path<OpleidingsnaamGecodeerd> treatedPath = builder.treat(opleidingPath, OpleidingsnaamGecodeerd.class);
                    Long requestGecodeerd = opleidingsnaam.getCodeOpleidingsnaam();
                    String requestOmschrijving = opleidingsnaam.getOmschrijvingOpleidingsnaam();
                    if (requestGecodeerd != null) {
                        pred3 = builder.equal(treatedPath.get(OpleidingsnaamGecodeerd_.codeOpleidingsnaam), requestGecodeerd);
                    }
                    if (requestOmschrijving != null) {
                        pred4 = builder.like(treatedPath.get(OpleidingsnaamGecodeerd_.omschrijvingOpleidingsnaam), "%" + requestOmschrijving + "%");
                    }
                }
                predicateList.add(builder.and(pred1, builder.and(pred2, builder.and(pred3, pred4))));
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            return builder.greaterThanOrEqualTo(subquery, (long) opleidingen.size());
        };
    }

    private static Specification<Werkzoekende> werkervaring(MPArbeidsmarktkwalificatie arbeidsmarktkwalificatie) {
        return (root, query, builder) -> {
            if (arbeidsmarktkwalificatie == null || arbeidsmarktkwalificatie.getWerkervaring() == null) {
                return builder.conjunction();
            }

            //Create subquery to count the amount of matches.
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Werkzoekende> r = subquery.correlate(root);

            List<MPWerkervaring> werkervaringen = arbeidsmarktkwalificatie.getWerkervaring();
            List<Predicate> predicateList = new ArrayList<>();

            ListJoin<Arbeidsmarktkwalificatie, Werkervaring> path = r
                    .join(Werkzoekende_.arbeidsmarktkwalificatie)
                    .join(Arbeidsmarktkwalificatie_.werkervaring);

            for (MPWerkervaring werkervaring : werkervaringen) {

                Predicate pred1 = builder.conjunction();
                if (werkervaring.getNaamOrganisatie() != null) {
                    pred1 = builder.like(path.get(MPWerkervaring_.naamOrganisatie), "%" + werkervaring.getNaamOrganisatie() + "%");
                }
                Predicate pred2 = builder.conjunction();
                Predicate pred3 = builder.conjunction();

                Path<Beroepsnaam> beroepPath = path.get(MPWerkervaring_.beroep);
                if (werkervaring.getBeroep() instanceof BeroepsnaamOngecodeerd) {
                    // Downcast to BeroepsnaamOngecodeerd
                    Path<BeroepsnaamOngecodeerd> treatedPath = builder.treat(beroepPath, BeroepsnaamOngecodeerd.class);
                    BeroepsnaamOngecodeerd beroepsnaam = (BeroepsnaamOngecodeerd) werkervaring.getBeroep();
                    if (beroepsnaam.getNaamBeroepOngecodeerd() != null) {
                        pred2 = builder.like(treatedPath.get(BeroepsnaamOngecodeerd_.naamBeroepOngecodeerd), "%" + beroepsnaam.getNaamBeroepOngecodeerd() + "%");
                    }
                } else if (werkervaring.getBeroep() instanceof BeroepsnaamGecodeerd) {
                    // Downcast to BeroepsnaamGecodeerd
                    Path<BeroepsnaamGecodeerd> treatedPath = builder.treat(beroepPath, BeroepsnaamGecodeerd.class);
                    BeroepsnaamGecodeerd beroepsnaam = (BeroepsnaamGecodeerd) werkervaring.getBeroep();
                    if (beroepsnaam.getCodeBeroepsnaam() != null) {
                        pred2 = builder.equal(treatedPath.get(BeroepsnaamGecodeerd_.codeBeroepsnaam), beroepsnaam.getCodeBeroepsnaam());
                    }
                    if (beroepsnaam.getOmschrijvingBeroepsnaam() != null) {
                        pred3 = builder.like(treatedPath.get(BeroepsnaamGecodeerd_.omschrijvingBeroepsnaam), "%" + beroepsnaam.getOmschrijvingBeroepsnaam() + "%");
                    }
                }
                predicateList.add(builder.and(pred1, builder.and(pred2, pred3)));
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            return builder.greaterThanOrEqualTo(subquery, (long) werkervaringen.size());

        };
    }

    private static Specification<Werkzoekende> sector(List<SectorBeroepsEnBedrijfsleven> sector) {
        return (root, query, builder) -> {
            if (sector == null) {
                return builder.conjunction();
            }

            //Create subquery to count the amount of matches.
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Werkzoekende> r = subquery.correlate(root);

            ListJoin<Werkzoekende, SectorBeroepsEnBedrijfsleven> path = r.join(Werkzoekende_.sector);
            List<Predicate> predicateList = new ArrayList<>();

            for (SectorBeroepsEnBedrijfsleven sec : sector) {
                Predicate pred1 = builder.conjunction();
                if (sec.getCodeSbi() != null) {
                    pred1 = builder.equal(path.get(SectorBeroepsEnBedrijfsleven_.codeSbi), sec.getCodeSbi());
                }
                predicateList.add(pred1);
            }
            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            return builder.greaterThanOrEqualTo(subquery, (long) sector.size());
        };
    }

    private static Specification<Werkzoekende> beroep(List<Beroepsnaam> bemiddelingsberoep) {

        return (root, query, builder) -> {
            if (bemiddelingsberoep == null) {
                return builder.conjunction();
            }

            //Create subquery to count the amount of matches.
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Werkzoekende> r = subquery.correlate(root);

            List<Predicate> predicateList = new ArrayList<>();

            ListJoin<Werkzoekende, Beroepsnaam> path = r
                    .join(Werkzoekende_.bemiddelingsberoep);

            for (Beroepsnaam beroepsnaam : bemiddelingsberoep) {
                Predicate pred1 = builder.conjunction();
                Predicate pred2 = builder.conjunction();

                if (beroepsnaam instanceof BeroepsnaamOngecodeerd) {
                    // Downcast to BeroepsnaamOngecodeerd
                    Path<BeroepsnaamOngecodeerd> treatedPath = builder.treat(path, BeroepsnaamOngecodeerd.class);
                    BeroepsnaamOngecodeerd beroepsnaamOngecodeerd = (BeroepsnaamOngecodeerd) beroepsnaam;
                    if (beroepsnaamOngecodeerd.getNaamBeroepOngecodeerd() != null) {
                        pred2 = builder.like(treatedPath.get(BeroepsnaamOngecodeerd_.naamBeroepOngecodeerd), "%" + beroepsnaamOngecodeerd.getNaamBeroepOngecodeerd() + "%");
                    }
                } else if (beroepsnaam instanceof BeroepsnaamGecodeerd) {
                    // Downcast to BeroepsnaamGecodeerd
                    Path<BeroepsnaamGecodeerd> treatedPath = builder.treat(path, BeroepsnaamGecodeerd.class);
                    BeroepsnaamGecodeerd beroepsnaamGecodeerd = (BeroepsnaamGecodeerd) beroepsnaam;
                    if (beroepsnaamGecodeerd.getCodeBeroepsnaam() != null) {
                        pred1 = builder.equal(treatedPath.get(BeroepsnaamGecodeerd_.codeBeroepsnaam), beroepsnaamGecodeerd.getCodeBeroepsnaam());
                    }
                    if (beroepsnaamGecodeerd.getOmschrijvingBeroepsnaam() != null) {
                        pred2 = builder.like(treatedPath.get(BeroepsnaamGecodeerd_.omschrijvingBeroepsnaam), "%" + beroepsnaamGecodeerd.getOmschrijvingBeroepsnaam() + "%");
                    }
                }
                predicateList.add(builder.and(pred1, pred2));
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            return builder.greaterThanOrEqualTo(subquery, (long) bemiddelingsberoep.size());
        };
    }


}
