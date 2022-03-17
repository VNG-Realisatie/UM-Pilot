package com.cap.pocvng.repository;


import com.cap.pocvng.entity.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Specifications that are used to filter Vacature based on request criteria.
 * <p>
 * Indications are an exact match. If asked for 2 = Nee, then vacatures with indication 2 = Nee are returned.
 * <p>
 * Descriptions are a like match. If asked for "string" to be contained in a description, than vacatures containing
 * this string, such as "this is a text with string", are returned.
 * <p>
 * Scaling attributes, such as code attributes, are matched greater/less than or equal to. If asked for taalbeheersing
 * 3 = Goed, than vacatures having 2 = Redelijk are also returned.
 * <p>
 * Vacatures with attributes "Niet van toepassing" are also returned with any request.
 */
@Component
public class VacatureSpecifications {

    private VacatureSpecifications() {
    }

    public static Specification<Vacature> createVacatureSpecification(MPVacature searchCriteria, String oin) {
        return oinSpecification(oin)
                .and(codeWerkEnDenkniveauMinimaal(searchCriteria.getCodeWerkEnDenkniveauMinimaal()))
                .and(indicatieLdrRegistratie(searchCriteria.getIndicatieLdrRegistratie()))
                .and(sluitingsDatumVacature(searchCriteria.getSluitingsDatumVacature()))
                .and(sollicitatiewijze(searchCriteria.getSollicitatiewijze()))
                .and(adresHouding(searchCriteria.getWerkgever()))
                .and(sectorWerkgever(searchCriteria.getWerkgever()))
                .and(sector(searchCriteria.getSector()))
                .and(arbeidsVoorwaarden(searchCriteria.getArbeidsVoorwaarden()))
                .and(contractvorm(searchCriteria.getContractvorm()))
                .and(beroep(searchCriteria.getBeroep()))
                .and(werkervaring(searchCriteria.getWerkervaring()))
                .and(rijbewijs(searchCriteria.getRijbewijs()))
                .and(vervoermiddel(searchCriteria.getVervoermiddel()))
                .and(flexibiliteit(searchCriteria.getFlexibiliteit()))
                .and(werktijden(searchCriteria.getWerktijden()))
                .and(cursus(searchCriteria.getCursus()))
                .and(opleiding(searchCriteria.getOpleiding()))
                .and(gedragscompetentie(searchCriteria.getGedragscompetentie()))
                .and(vakvaardigheid(searchCriteria.getVakvaardigheid()))
                .and(taalbeheersing(searchCriteria.getTaalbeheersing()));
    }

    private static Specification<Vacature> oinSpecification(String oin) {
        return (root, query, builder) -> builder.equal(root.get(Vacature_.oin), oin);

    }

    private static Specification<Vacature> codeWerkEnDenkniveauMinimaal(String codeWerkEnDenkniveauMinimaal) {
        return (root, query, builder) -> {
            if (codeWerkEnDenkniveauMinimaal == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get(Vacature_.codeWerkEnDenkniveauMinimaal), codeWerkEnDenkniveauMinimaal);
        };
    }

    private static Specification<Vacature> indicatieLdrRegistratie(Integer indicatieLdrRegistratie) {
        return (root, query, builder) -> {
            if (indicatieLdrRegistratie == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get(Vacature_.indicatieLdrRegistratie), indicatieLdrRegistratie);
        };
    }

    private static Specification<Vacature> sluitingsDatumVacature(LocalDate sluitingsDatumVacature) {
        return (root, query, builder) -> {
            if (sluitingsDatumVacature == null) {
                return builder.conjunction();
            }
            return builder.greaterThanOrEqualTo(root.get(Vacature_.sluitingsDatumVacature), sluitingsDatumVacature);
        };
    }

    private static Specification<Vacature> sollicitatiewijze(List<MPSollicitatiewijze> sollicitatiewijze) {
        return (root, query, builder) -> {
            if (sollicitatiewijze == null) {
                return builder.conjunction();
            }

            // Create subquery to count the amount of matches.
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Vacature> r = subquery.correlate(root);

            ListJoin<Vacature, Sollicitatiewijze> join = r.join(Vacature_.sollicitatiewijze);

            List<Predicate> predicateList = new ArrayList<>();

            for (MPSollicitatiewijze swijze : sollicitatiewijze) {

                Predicate pred1 = builder.conjunction();

                if (swijze.getCodeSollicitatiewijze() != null) {
                    pred1 = builder.equal(join.get(Sollicitatiewijze_.codeSollicitatiewijze), swijze.getCodeSollicitatiewijze());
                }

                // Build conjunction.
                predicateList.add(pred1);
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            // If matches are gte to request, return true.
            return builder.greaterThanOrEqualTo(subquery, (long) sollicitatiewijze.size());
        };
    }

    private static Specification<Vacature> adresHouding(MPWerkgever werkgever) {
        return (root, query, builder) -> {
            if (werkgever == null || werkgever.getAdresHouding() == null) {
                return builder.conjunction();
            }
            List<MPAdresHouding> adressen = werkgever.getAdresHouding();

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Vacature> r = subquery.correlate(root);

            List<Predicate> predicateList = new ArrayList<>();

            ListJoin<Werkgever, AdresHouding> path = r
                    .join(Vacature_.werkgever)
                    .join(Werkgever_.adresHouding);


            for (MPAdresHouding adresHouding : adressen) {
                Predicate pred1 = builder.conjunction();
                Predicate pred2 = builder.conjunction();
                Predicate pred3 = builder.conjunction();
                MPAdresBuitenland adres = adresHouding.getAdres();


                if (adresHouding.getCodeFunctieAdres() != null) {
                    pred1 = builder.equal(path.get(AdresHouding_.codeFunctieAdres), adresHouding.getCodeFunctieAdres());
                }


                if (adres != null && adres.getAdresBuitenland() != null && adres.getAdresBuitenland().getLandencodeIso() != null) {
                    String isocode = adres.getAdresBuitenland().getLandencodeIso();
                    Path<String> isocodeDB = builder.treat(path.join(AdresHouding_.adres), AdresBuitenland.class).get(AdresBuitenland_.adresBuitenland).get(AdresBuitenlandImpl_.landencodeIso);
                    // Either the entity is a AdresBuitenland with corresponding isocode
                    pred2 = builder.equal(isocodeDB, isocode);
                    // Or the entity is a AdresNederland with a request of isocode NL
                    pred3 = builder.and(builder.equal(path.join(AdresHouding_.adres).type(), builder.literal(AdresNederland.class)), builder.equal(builder.literal(isocode), builder.literal("NL")));
                }
                // Conjunction of pred 1 and a disjunction of pred2 and pred3
                predicateList.add(builder.and(pred1, builder.or(pred2, pred3)));
            }


            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            return builder.greaterThanOrEqualTo(subquery, (long) adressen.size());
        };
    }


    private static Specification<Vacature> sectorWerkgever(MPWerkgever werkgever) {

        return (root, query, builder) -> {
            if (werkgever == null || werkgever.getSector() == null) {
                return builder.conjunction();
            }

            List<SectorBeroepsEnBedrijfsleven> sectoren = werkgever.getSector();

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Vacature> r = subquery.correlate(root);

            List<Predicate> predicateList = new ArrayList<>();
            ListJoin<Werkgever, SectorBeroepsEnBedrijfsleven> path = r
                    .join(Vacature_.werkgever)
                    .join(Werkgever_.sector);

            for (SectorBeroepsEnBedrijfsleven sec : sectoren) {
                Predicate pred1 = builder.conjunction();
                if (sec.getCodeSbi() != null) {
                    pred1 = builder.equal(path.get(SectorBeroepsEnBedrijfsleven_.codeSbi), sec.getCodeSbi());
                }
                predicateList.add(pred1);
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            return builder.greaterThanOrEqualTo(subquery, (long) sectoren.size());


        };
    }


    private static Specification<Vacature> sector(SectorBeroepsEnBedrijfsleven sector) {
        return (root, query, builder) -> {
            if (sector == null || sector.getCodeSbi() == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get(Vacature_.sector).get(SectorBeroepsEnBedrijfsleven_.codeSbi), sector.getCodeSbi());
        };
    }

    private static Specification<Vacature> arbeidsVoorwaarden(MPArbeidsVoorwaarden arbeidsVoorwaarden) {
        return (root, query, builder) -> {

            if (arbeidsVoorwaarden == null) {
                return builder.conjunction();
            }

            Path<ArbeidsVoorwaarden> path = root.get(Vacature_.arbeidsVoorwaarden);


            Predicate pred1 = builder.conjunction();
            Predicate pred2 = builder.conjunction();

            // If datumEindeWerkzaamheden > request DatumAanvangWerkzaamheden then return true;
            if (arbeidsVoorwaarden.getDatumEindeWerkzaamheden() != null) {
                pred1 = builder.greaterThanOrEqualTo(path.get(ArbeidsVoorwaarden_.datumEindeWerkzaamheden), arbeidsVoorwaarden.getDatumAanvangWerkzaamheden());
            }
            if (arbeidsVoorwaarden.getSalarisIndicatie() != null) {
                pred2 = builder.equal(path.get(ArbeidsVoorwaarden_.salarisIndicatie), arbeidsVoorwaarden.getSalarisIndicatie());
            }


            return builder.and(pred1,pred2);
        };
    }

    private static Specification<Vacature> contractvorm(List<Contractvorm> contractvorm) {
        return (root, query, builder) -> {
            if (contractvorm == null) {
                return builder.conjunction();
            }

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Vacature> r = subquery.correlate(root);

            ListJoin<Vacature, Contractvorm> join = r.join(Vacature_.contractvorm);
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
                predicateList.add(builder.and(pred1, pred2));
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            return builder.greaterThanOrEqualTo(subquery, (long) contractvorm.size());
        };
    }


    private static Specification<Vacature> beroep(Beroepsnaam beroep) {
        return (root, query, builder) -> {
            if (beroep == null) {
                return builder.conjunction();
            }

            Path<Beroepsnaam> path = root.get(Vacature_.beroep);

            Predicate pred1 = builder.conjunction();
            Predicate pred2 = builder.conjunction();

            Path<BeroepsnaamGecodeerd> gecodeerdPath = builder.treat(path, BeroepsnaamGecodeerd.class);
            Path<BeroepsnaamOngecodeerd> ongecodeerdPath = builder.treat(path, BeroepsnaamOngecodeerd.class);

            if (beroep instanceof BeroepsnaamGecodeerd) {
                BeroepsnaamGecodeerd castedBeroep = ((BeroepsnaamGecodeerd) beroep);
                if (castedBeroep.getCodeBeroepsnaam() != null) {
                    pred1 = builder.equal(gecodeerdPath.get(BeroepsnaamGecodeerd_.codeBeroepsnaam), castedBeroep.getCodeBeroepsnaam());
                }
                if (castedBeroep.getOmschrijvingBeroepsnaam() != null) {
                    pred2 = builder.like(gecodeerdPath.get(BeroepsnaamGecodeerd_.omschrijvingBeroepsnaam), "%" + castedBeroep.getOmschrijvingBeroepsnaam() + "%");
                }
            } else {
                BeroepsnaamOngecodeerd castedBeroep = ((BeroepsnaamOngecodeerd) beroep);
                /*
                If the request is a BeroepsnaamOngecodeerd, then the naamBeroepOngecodeerd can also match Vacatures with
                BeroepsnaamGecodeerd on field omschrijvingBeroepsnaam
                */
                if (castedBeroep.getNaamBeroepOngecodeerd() != null) {
                    pred1 = builder.or(
                            builder.like(ongecodeerdPath.get(BeroepsnaamOngecodeerd_.naamBeroepOngecodeerd), "%" + castedBeroep.getNaamBeroepOngecodeerd() + "%"),
                            builder.like(gecodeerdPath.get(BeroepsnaamGecodeerd_.omschrijvingBeroepsnaam), "%" + castedBeroep.getNaamBeroepOngecodeerd() + "%"));

                }
            }
            return builder.and(pred1, pred2);
        };
    }

    private static Specification<Vacature> werkervaring(List<Werkervaring> werkervaring) {
        return (root, query, builder) -> {
            if (werkervaring == null) {
                return builder.conjunction();
            }

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Vacature> r = subquery.correlate(root);

            ListJoin<Vacature, Werkervaring> join = r.join(Vacature_.werkervaring);
            List<Predicate> predicateList = new ArrayList<>();

            for (Werkervaring werkerv : werkervaring) {
                Predicate pred1 = builder.conjunction();
                if (werkerv.getAantalJarenWerkzaamInBeroep() != null) {
                    pred1 = builder.greaterThanOrEqualTo(join.get(Werkervaring_.aantalJarenWerkzaamInBeroep), werkerv.getAantalJarenWerkzaamInBeroep());
                }
                predicateList.add(pred1);
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            return builder.greaterThanOrEqualTo(subquery, (long) werkervaring.size());
        };
    }

    private static Specification<Vacature> rijbewijs(List<Rijbewijs> rijbewijs) {
        return (root, query, builder) -> {
            if (rijbewijs == null) {
                return builder.conjunction();
            }

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Vacature> r = subquery.correlate(root);

            List<Predicate> predicateList = new ArrayList<>();
            ListJoin<Vacature, Rijbewijs> path = r.join(Vacature_.rijbewijs);

            for (Rijbewijs rijbws : rijbewijs) {
                Predicate pred1 = builder.conjunction();
                if (rijbws.getCodeSoortRijbewijs() != null) {
                    pred1 = builder.equal(path.get(Rijbewijs_.codeSoortRijbewijs), rijbws.getCodeSoortRijbewijs());
                }
                predicateList.add(pred1);
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            return builder.greaterThanOrEqualTo(subquery, (long) rijbewijs.size());
        };
    }

    private static Specification<Vacature> vervoermiddel(List<MPVervoermiddel> vervoermiddel) {
        return (root, query, builder) -> {
            if (vervoermiddel == null) {
                return builder.conjunction();
            }

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Vacature> r = subquery.correlate(root);

            List<Predicate> predicateList = new ArrayList<>();
            ListJoin<Vacature, Vervoermiddel> join = r.join(Vacature_.vervoermiddel);

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
                predicateList.add(builder.and(pred1, pred2));
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            return builder.greaterThanOrEqualTo(subquery, (long) vervoermiddel.size());
        };
    }

    private static Specification<Vacature> flexibiliteit(Flexibiliteit flexibiliteit) {
        return (root, query, builder) -> {
            if (flexibiliteit == null) {
                return builder.conjunction();
            }

            Path<Flexibiliteit> path = root.get(Vacature_.flexibiliteit);

            Predicate pred1 = builder.conjunction();
            Predicate pred2 = builder.conjunction();
            Predicate pred3 = builder.conjunction();
            Predicate pred4 = builder.conjunction();

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

    private static Specification<Vacature> werktijden(Werktijden werktijden) {
        return (root, query, builder) -> {
            if (werktijden == null) {
                return builder.conjunction();
            }

            Path<Werktijden> path = root.get(Vacature_.werktijden);
            Predicate pred1 = builder.conjunction();
            Predicate pred2 = builder.conjunction();
            Predicate pred3 = builder.conjunction();

            if (werktijden.getAantalWerkurenPerWeekMinimaal() != null) {
                pred1 = builder.greaterThanOrEqualTo(path.get(Werktijden_.aantalWerkurenPerWeekMinimaal), werktijden.getAantalWerkurenPerWeekMinimaal());
            }
            if (werktijden.getAantalWerkurenPerWeekMaximaal() != null) {
                pred2 = builder.lessThanOrEqualTo(path.get(Werktijden_.aantalWerkurenPerWeekMaximaal), werktijden.getAantalWerkurenPerWeekMaximaal());
            }
            if (werktijden.getIndicatieKantoortijden() != null) {
                pred3 = builder.equal(path.get(Werktijden_.indicatieKantoortijden), werktijden.getIndicatieKantoortijden());
            }

            return builder.and(pred1, builder.and(pred2, pred3));
        };
    }

    private static Specification<Vacature> cursus(List<Cursus> cursus) {
        return (root, query, builder) -> {
            if (cursus == null) {
                return builder.conjunction();
            }

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Vacature> r = subquery.correlate(root);

            List<Predicate> predicateList = new ArrayList<>();
            ListJoin<Vacature, Cursus> path = r.join(Vacature_.cursus);

            for (Cursus crs : cursus) {
                Predicate pred1 = builder.conjunction();
                if (crs.getNaamCursus() != null) {
                    pred1 = builder.like(path.get(Cursus_.naamCursus), "%" + crs.getNaamCursus() + "%");
                }
                predicateList.add(pred1);
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            return builder.greaterThanOrEqualTo(subquery, (long) cursus.size());
        };
    }

    private static Specification<Vacature> opleiding(List<MPOpleiding> opleiding) {
        return (root, query, builder) -> {
            if (opleiding == null) {
                return builder.conjunction();
            }

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Vacature> r = subquery.correlate(root);

            List<Predicate> predicateList = new ArrayList<>();
            ListJoin<Vacature, Opleiding> path = r.join(Vacature_.opleiding);


            for (MPOpleiding opld : opleiding) {
                Predicate pred1 = builder.conjunction();
                Predicate pred2 = builder.conjunction();
                Predicate pred3 = builder.conjunction();
                Predicate pred4 = builder.conjunction();

                if (opld.getCodeNiveauOpleiding() != null) {
                    pred1 = builder.greaterThanOrEqualTo(path.get(Opleiding_.codeNiveauOpleiding), opld.getCodeNiveauOpleiding());
                }
                // Indicatie 8 = Niet van Toepassing. This should also return true regardless of value vacature.
                if (opld.getIndicatieDiploma() != null && opld.getIndicatieDiploma() != 8) {
                    pred2 = builder.equal(path.get(Opleiding_.indicatieDiploma), opld.getIndicatieDiploma());
                }

                Path<Opleidingsnaam> opleidingPath = path.get(Opleiding_.opleidingsnaam);

                // Depending on subclass, have different predicate.
                if (opld.getOpleidingsnaam() instanceof MPOpleidingsnaamOngecodeerd) {
                    Path<OpleidingsnaamOngecodeerd> treatedPath = builder.treat(opleidingPath, OpleidingsnaamOngecodeerd.class);
                    MPOpleidingsnaamOngecodeerd opleidingsnaam = (MPOpleidingsnaamOngecodeerd) opld.getOpleidingsnaam();
                    String requestOngecodeerd = opleidingsnaam.getNaamOpleidingOngecodeerd();
                    if (requestOngecodeerd != null) {
                        pred3 = builder.like(treatedPath.get(OpleidingsnaamOngecodeerd_.naamOpleidingOngecodeerd), "%" + requestOngecodeerd + "%");
                    }
                } else if (opld.getOpleidingsnaam() instanceof OpleidingsnaamGecodeerd) {
                    OpleidingsnaamGecodeerd opleidingsnaam = (OpleidingsnaamGecodeerd) opld.getOpleidingsnaam();
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

            return builder.greaterThanOrEqualTo(subquery, (long) opleiding.size());
        };
    }

    private static Specification<Vacature> gedragscompetentie(List<Gedragscompetentie> gedragscompetentie) {
        return (root, query, builder) -> {
            if (gedragscompetentie == null) {
                return builder.conjunction();
            }


            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Vacature> r = subquery.correlate(root);


            List<Predicate> predicateList = new ArrayList<>();
            ListJoin<Vacature, Gedragscompetentie> path = r.join(Vacature_.gedragscompetentie);


            for (Gedragscompetentie gedragscomp : gedragscompetentie) {

                Predicate pred1 = builder.conjunction();
                Predicate pred2 = builder.conjunction();
                Predicate pred3 = builder.conjunction();

                if (gedragscomp.getCodeGedragscompetentie() != null) {
                    pred1 = builder.equal(path.get(Gedragscompetentie_.codeGedragscompetentie), gedragscomp.getCodeGedragscompetentie());
                }
                if (gedragscomp.getOmschrijvingGedragscompetentie() != null) {
                    pred2 = builder.like(path.get(Gedragscompetentie_.omschrijvingGedragscompetentie), "%" + gedragscomp.getOmschrijvingGedragscompetentie() + "%");
                }
                if (gedragscomp.getCodeBeheersingGedragscompetentie() != null) {
                    pred3 = builder.lessThanOrEqualTo(path.get(Gedragscompetentie_.codeBeheersingGedragscompetentie), gedragscomp.getCodeBeheersingGedragscompetentie());
                }

                Predicate conjunction = builder.and(pred1, builder.and(pred2, pred3));
                predicateList.add(conjunction);
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            return builder.greaterThanOrEqualTo(subquery, (long) gedragscompetentie.size());
        };
    }

    private static Specification<Vacature> vakvaardigheid(List<Vakvaardigheid> vakvaardigheid) {
        return (root, query, builder) -> {
            if (vakvaardigheid == null) {
                return builder.conjunction();
            }

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Vacature> r = subquery.correlate(root);

            ListJoin<Vacature, Vakvaardigheid> path = r.join(Vacature_.vakvaardigheid);
            List<Predicate> predicateList = new ArrayList<>();

            for (Vakvaardigheid vak : vakvaardigheid) {
                Predicate pred1 = builder.conjunction();
                if (vak.getOmschrijving() != null) {
                    pred1 = builder.like(path.get(Vakvaardigheid_.omschrijving), "%" + vak.getOmschrijving() + "%");
                }
                predicateList.add(pred1);
            }

            subquery.where(builder.or(predicateList.toArray(new Predicate[0])));
            subquery.select(builder.count(builder.literal(1)));

            return builder.greaterThanOrEqualTo(subquery, (long) vakvaardigheid.size());
        };
    }


    private static Specification<Vacature> taalbeheersing(List<Taalbeheersing> taalbeheersing) {
        return (root, query, builder) -> {
            if (taalbeheersing == null) {
                return builder.conjunction();
            }

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Vacature> r = subquery.correlate(root);

            List<Predicate> predicateList = new ArrayList<>();
            ListJoin<Vacature, Taalbeheersing> path = r.join(Vacature_.taalbeheersing);

            for (Taalbeheersing taal : taalbeheersing) {

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
            return builder.greaterThanOrEqualTo(subquery, (long) taalbeheersing.size());
        };
    }
}
